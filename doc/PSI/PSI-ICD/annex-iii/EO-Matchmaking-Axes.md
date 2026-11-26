=begin

# EO Matchmaking Axes and Scoring Guidance

**Version:** 0.2 · **Date:** 2026-11-25
**Activity:** A102010 — EO Domain Modeling & Matchmaking Criteria
**Status:** drafted for stakeholder review

---

=end

## Where matchmaking sits

Matchmaking is **step 2 and 3** of the PSI customer journey. One further step follows that is
often confused with it, and keeping the two apart decides which attributes may be used as axes
at all.

```
 1  Mission                  what I need, where, when — no market involved
 2  Customer Inquiry         characteristics, with a precedence and a rank
     + matchmaking            fanned out to catalogues, other PSSs and providers
 3  InquiryResult[]          which offerings fit, in rank order
 4  Selection                the customer picks candidates from that list
 5  POQ                      "will you deliver THIS offering to THIS customer,
                              over THIS place, on THIS date"
 6  Pricing Discovery        "what does each cost, from the price list"
 7  Quote                    only when step 6 could not answer
 8  Stock -> 9 Order
```

There is no *offering availability discovery* step. Customer Inquiry answers what exists; POQ
answers what is deliverable, to whom, where and when. A third call between them would have to
ask the provider the question POQ already asks.

### Four kinds of inquiry, one data format {#mm-four-kinds-of-inquiry-one-data-format}

PSI-TAD distinguishes **four**, and matchmaking is only the first:

| Kind | What it is |
|---|---|
| **Matchmaking** | an automatically generated list of matching products. *"This is expected to be the default, since it is the main purpose of a PSS"* |
| **RFQ** | matchmaking showed that a provider *could* serve the request but has no offering for it; the buyer asks that provider for a tailored one |
| **ITT** | the buyer skips matchmaking, or uses a coarse pass only to pick providers, then invites a selected set to tender |
| **Change request** | an active product is to be changed; feasibility and price are checked with the provider |

Table: Four kinds of inquiry. {#tbl:mm-inquiry-kinds}

*"The general data format required to exchange inquiries is expected to be the same for all
cases, but the PSS will have to implement different processes for each use case."*

**This document is about the first kind.** The other three matter here for one reason: they are
the standard's own answer to an empty result set, alongside the [relaxation](#mm-relaxing-the-request).

### Who actually answers {#mm-who-actually-answers}

Matchmaking is **not** a lookup in the PSS's own catalogue. PSI-TAD is explicit: *"The
matchmaking process can be done by a single PSS, but may also be **distributed between multiple
PSS and/or provider systems**, which makes this **a use case for the PSID**."* Distribution is
not an edge case — it is a reason the interface exists.

| Source | What it knows | What it costs |
|---|---|---|
| **The PSS's own catalogue** | replicated specifications and offerings — what a provider can do *in general* | fast, and as stale as the replica |
| **Another PSS** | its own catalogue and its own providers, answered by *its* matchmaking | one inquiry, synchronised; the answer arrives on the other system's clock |
| **The provider's system** | the product as the provider holds it today | the best answer available before POQ, and the slowest |

Table: Who answers an inquiry. {#tbl:mm-respondents}

Three consequences follow.

**A hub may hold no catalogue at all.** In the distributed-matchmaking figure the Hub PSS's
`Catalogs` component is marked optional, with the note *"A hub may use internal catalogs or rely
on external systems only."* An axis defined as "knowable from the catalogue" is undefined for
such a hub.

**Coarse and fine are different passes.** *"The PSS could perform a coarse matchmaking first and
then contact a subset of potential providers to do a fine matchmaking."* The same attribute may
be unanswerable in the coarse pass and answerable in the fine one. Which pass an axis belongs to
is a real design question this document does not settle — [Open questions](#mm-open-questions).

**The result set is partly a function of who answered in time.** `responseTime.maximum` is
required when an inquiry is created, the PSS passes it on when forwarding, and *"in case the
provider exceeds the given deadline, its product offerings may not be considered for the issued
inquiry."* A slow provider disappears, and nothing in the result says so — gap M-6.

The mechanism is settled: **ADR015 chose "common inquiry with synchronisation"**, so one inquiry
id travels to every system, each carries its own `inquiredProvider[].state`, and the PSS
aggregates the results.

### What separates matchmaking from POQ {#mm-what-separates-matchmaking-from-poq}

Not freshness, and not the catalogue. A fine matchmaking may ask the provider's own system and
get an answer as current as POQ's. Two things separate them.

**What is asked, and whether the answer binds:**

| Step | Question | Answer |
|---|---|---|
| **2/3 Matchmaking** | What could serve this request? | indicative, from whichever source answered |
| **5 POQ** | Will you deliver *this* offering, to *this* party, over *this* place, on *this* date? | binding, from the provider, with a reason code |

Table: Matchmaking versus POQ. {#tbl:mm-matchmaking-vs-poq}

**And when each runs.** POQ comes **after the customer has chosen**, and this is not an
implementation detail:

* an inquiry can return many results, and qualifying all of them is waste;
* POQ needs a **concrete configuration** — one offering, a place, a date — which an inquiry
  need not have carried.

A PSS may qualify automatically, but only in the narrow case where the configuration is already
known and the candidate list is short enough to be worth it. When it does, one
`CheckProductOfferingQualification` carries a list of offerings, one per item, so the cost is a
single round trip rather than one per candidate.

> [!NOTE]
> **Consequence for this document.** An attribute is a matchmaking axis only if its value is
> knowable to **whoever answers the inquiry** — a weaker test than "is in the catalogue" and a
> stronger one than "the provider could find out". The section [Gates, and which layer each belongs to](#mm-gates) assigns each candidate gate to its
> proper layer, and several of them turn out **not** to belong to matchmaking at all.

## What the inquiry actually carries

The PSI `customer_inquiry` API (PSID001) carries:

| Field | Values | Meaning |
|---|--|-|
| `inquiredProduct.place[]` | `RelatedPlaceRefOrValue` | the AOI, where the buyer states one |
| `inquiredProduct.servicePeriod` | `TimePeriod` | the window |
| `inquiredProductCharacteristic[].name` | — | which characteristic |
| `inquiredProductCharacteristic[].priority` | number, **1 = most important**, ties allowed, absent = lowest | **the rank of this characteristic against the others** |
| `…[].inquiredProductCharacteristicValue[].value` | — | the value the buyer states |
| **`…[].inquiredProductCharacteristicValue[].precedence`** | **`preferred` · `minimum` · `maximum` · `alternative`** | **how to read that value** |
| `customerProfile[]` | `CustomerCharacteristic` — `name`, `value` | facts about the customer, filled by the PSS |
| `responseTime.maximum` | date-time, **required on creation** | the deadline a respondent must meet or be dropped — [Who actually answers](#mm-who-actually-answers) |
| `bundlesOnly` | boolean, default **`true`** | whether only bundles are of interest |
| `inquiredProvider[]` | `state`, `actualResponseTime` per provider | *"the list of providers that are inquired to respond with a product offering"* — [Who actually answers](#mm-who-actually-answers) |

Table: Per-characteristic fields of the customer inquiry. {#tbl:mm-inquiry-fields}

**`precedence` is not a rank.** It is a **constraint kind**: it says whether the stated value is
a target, a floor, a ceiling, or one of several acceptable substitutes. This is the field that
decides how an axis is scored, and it maps directly onto the attribute taxonomy in [How constraints combine](#mm-constraints).

**`precedence` sits on the value, not on the characteristic.** A characteristic holds an array
of `inquiredProductCharacteristicValue`, and each value carries its own `precedence`. So "2 m
preferred, 3 m at worst" is **one characteristic holding two values**, sharing one `priority` —
not two characteristics. [Expressing a target with a tolerance](#mm-expressing-a-target-with-a-tolerance) is written to match.

**`priority` orders the characteristics, and an order is all a ranking needs.** PSID001 defines
it as *"priority of this characteristic compared to others in ascending order (the most
important one is 1). Multiple characteristics can be equally important. If the attribute is not
set, the characteristic has lowest priority."* A buyer who wants resolution to count for more
than latency gives resolution the better rank. Nothing is gained by also saying *how much* more
it counts: "twice as important" is not a sentence a buyer can mean precisely, and a PSS that
knew it would still have to pick a policy. The [ranking patterns](#mm-user-needs-and-the-rankings-they-imply) are therefore expressible
exactly as they stand.

### `customerProfile` — the half the buyer does not fill in

`customerProfile` is an array of `CustomerCharacteristic`, and the schema is deliberately thin:
`name`, `description`, `valueType`, `value` — **no `precedence`, no `priority`**. The OAS
examples say what it is for: `country` with values `DE, ES, FR, …` and `securityGrade` with
`UNCLASSIFIED, RESTRICTED, CONFIDENTIAL, SECRET, TOP_SECRET`.

These are the characteristics the **PSS fills automatically from the client's profile**. They
are the inputs to the [automatic requirements](#mm-automatic-requirements), and the buyer neither types them nor can
rank them.

The separation is **structural**, and it settles more than it looks. Facts about the customer
and constraints stated by the buyer live in different arrays: an eligibility rule is never an
`inquiredProductCharacteristic`, so it carries no `precedence`, cannot be weighed against a
resolution preference, and cannot be loosened by anything that loosens characteristics.

What the separation does **not** settle is the record. `customerProfile` carries the customer's
*country*, not the exclusion *"products under this export regime are not sellable to this
buyer"*. The PSS derives the second from the first, and the derived constraint is written down
nowhere — gap M-1.

### What the buyer fills in today

A list of characteristics, each with its values, their `precedence` and the characteristic's
`priority`. That is the whole vocabulary the interface offers, and [The axes](#mm-the-axes) is the catalogue of what
may sensibly go in it.

This is a lot to ask of a buyer who says *"there is a flood here and I need to see it this
week"*. [User needs and the rankings they imply](#mm-user-needs-and-the-rankings-they-imply) writes out what that sentence means in characteristics, for six recurring cases.
Today someone — the buyer, or the PSS's own front end — has to do that translation before the
inquiry is sent. [Where this is heading](#mm-where-this-is-heading) describes where this is heading.

### What comes back

`InquiryResult` carries `productSpecification[]` (required), plus optional `productOffering[]`
and `product[]`, tied together by `inquiredProductRelationship[]` (ADR036):

| Field | Meaning |
|---|---|
| `inquiredProductRef` | which request this fulfils |
| `productOfferingRef` / `productSpecificationRef` / `productRef` | what fulfils it |
| **`coverage`** | `complete` \| `partial` — whether the offering fully meets the inquired characteristics |
| `quantity`, `totalPrice`, `servicePeriod` | commercial shape |
| **`relationshipCharacteristic[].deviation`** | how far this offering departs from what was asked |
| `note[]` | free-form text on the result |

Table: Fields of the inquiry response. {#tbl:mm-response-fields}

**The ranking is the order of the response**, with `InquiryResult.priority` making it explicit:
*"rank of a InquiryResult compared to others in ascending order (the best result is 1)"*.
Producing that order is the PSS's job — ADR022 is explicit that *"the PSS is responsible for
showing, sorting, and filtering the offers for the user in a meaningful way, including if the
offer perfectly or only partially matches the inquiry"*.

**`deviation` and `note` are prose, deliberately.** ADR022 weighed boolean deviation flags
against free text and chose free text, because a flag invites the provider to decide what counts
as a "real" deviation. The prose explains a result to the person reading it, after the PSS has
already ranked. That is the right shape for a single PSS. It becomes a problem only where one
PSS must re-rank another's results — [Who actually answers](#mm-who-actually-answers), gap M-4.

## Gates, and which layer each belongs to {#mm-gates}

A gate excludes rather than down-ranks. The mistake to avoid is applying one at the wrong layer.

### Gates the catalogue can answer — matchmaking filters {#mm-gates-the-catalogue-can-answer-matchmaking-filters}

| Gate | Attribute path | Why it excludes |
|---|---|---|
| **Service area** | `targetProfile.serviceArea[]` — `area`, `coverageType` | Each entry is a GeoJSON geometry marked `INCLUDED` or `EXCLUDED`. The buyer's AOI either lies inside the served region or it does not, and the catalogue says which |
| **Target geometry and size** | `targetGeometryType`, `targetAreaMin` / `targetAreaMax`, `minCoverageFraction` | A product that accepts polygons will not take a corridor; an AOI below the order minimum or above the maximum cannot be ordered at all |
| **Access path** | `accessProfile[].accessMode` | Archive and tasking answer different questions. A buyer needing a new acquisition is not served by an archive scene, however good |
| **Phase preservation** | `dataType ∈ {COMPLEX, PHASE_HISTORY}` | Physical. Without phase there is no interferometry and no polarimetry — no other quality compensates |
| **Interferometric fitness** | `interferometricUse` **and** `repeatCycle` **and** `orbitTubeDiameter` | Preserved phase alone is insufficient. All three must hold |
| **ARD conformance** | `ardFamily` + `ardConformanceLevel` | A conformance claim is true or false. A pipeline expecting CEOS-ARD NRB cannot use "nearly NRB" |
| **Pixel value accessibility** | `ogcService` contains `WCS`, when the channel is `OGC_SERVICE` only | Without WCS the buyer gets pictures, not values |
| **Polarimetric capability** | `polarizationMode`, `polarization` | A buyer needing channel ratios cannot use a single-pol product |

Table: Gates the catalogue can answer. {#tbl:mm-catalogue-gates}

These are declared in the catalogue, so the PSS can apply them during matchmaking.

> [!NOTE]
> **The service area carries its own reason.** `restrictionBasis` says whether a limit is
> `ORBITAL`, `COMMERCIAL` or `REGULATORY`. A regulatory exclusion is therefore already in the
> catalogue, as geometry — part of what [Requirements applied automatically — the buyer never states them](#mm-automatic-requirements) applies from the customer's profile is answered
> here, before any profile is consulted.

### Gates that depend on the delivery context — POQ, not matchmaking

| Concern | Why matchmaking cannot answer it |
|---|---|
| Is there capacity over this AOI in *this* window? | Contention and pass allocation are inventory state, and the catalogue holds no state |
| Is the required incidence angle achievable over *this* latitude, on *this* date? | Geometry depends on the target and the pass, neither of which the catalogue has |
| Does *this* candidate pass actually cover enough of the AOI? | `minCoverageFraction` is the seller's threshold; what a given pass covers is a property of that pass |

Table: Gates that depend on the delivery context. {#tbl:mm-delivery-gates}

**Where, not when.** The catalogue answers *where* a product may be ordered — [Gates the catalogue can answer — matchmaking filters](#mm-gates-the-catalogue-can-answer-matchmaking-filters) — and says
nothing about *when* a pass will exist over it. The three questions above are all the second
kind.

**"Cannot" is about binding, not about knowing.** A fine matchmaking that reaches the provider's
own system ([Who actually answers](#mm-who-actually-answers)) may answer all three and still commit to none of them. The binding answer is
a `CheckProductOfferingQualification`, whose result PSID679 describes as *"depending on catalog
rules and of the context of the interaction (defined be place, channel, party, product)"* — the
same dimensions this table is made of.

### Requirements applied automatically — the buyer never states them {#mm-automatic-requirements}

| Requirement | Attribute path | Applied from |
|-|---|-|
| Legal permission to buy | `licensingProfile.eligibleBuyerClass` | the buyer's organisation type |
| Export control regime | `licensingProfile.exportControl` | the buyer's jurisdiction |
| Security classification | `licensingProfile.securityClassification` | the buyer's clearance |
| Permitted purpose | `licensingProfile.permittedPurpose` | the stated purpose of the mission |
| Licence model | `licensingProfile.licenceModel` | what the buyer intends to do with the data |

Table: Requirements applied automatically. {#tbl:mm-automatic-requirements}

**These must not be left to the buyer to request.** A buyer does not ask "please exclude products
I am legally barred from buying" — and if they did, the request could be omitted, mistyped or
ranked. The PSS holds what it needs in `customerProfile` — `country`, `securityGrade` and their
kind — which travels with the inquiry, so **it should apply these as standing requirements on
every inquiry, whether or not any characteristic mentions them**.

Three properties follow from their living outside the characteristic list:

* **They are never ranked.** A legal exclusion carries no `priority`, because there is no amount
  of other quality that compensates for it.
* **They are never relaxed.** [Relaxing the request](#mm-relaxing-the-request) loosens *characteristics*. These are not characteristics, so
  nothing [Relaxing the request](#mm-relaxing-the-request) does can reach them.
* **Matchmaking's answer is provisional.** The authoritative one is POQ, which sees the
  contract, the quota and the clearance.

> [!IMPORTANT]
> **A provisional exclusion still has to be visible.** If a product is dropped during
> matchmaking on a legal rule, the buyer should learn that it was dropped and why — otherwise
> they never reach the POQ reason code that would have explained it, and the absence looks like
> the market simply having nothing. POQ carries `eligibilityResultReason`, *"reason for
> understanding the eligibility result, whatever the result is"*, for exactly this purpose.
> Matchmaking has no counterpart — gap M-1.

**Pool visibility is another way a product can be absent.** PSI scopes what a customer sees to
the pools they can access: a specification may be visible while no offering for it is, in which
case the buyer *"will have to request a quote since there is no visible product"*. So a product
missing from a result set may have been excluded on a legal rule, excluded by a constraint, out
of scope for the respondents that answered, dropped because its provider missed the deadline, or
simply not visible to this customer. **Five causes, one appearance** — gaps M-1 and M-6.

A second family of requirements is implied by what the buyer asked for rather than by who they
are. Asking for interferometry implies these three preconditions; asking for pixel
values over an OGC service implies `WCS`. The buyer states the goal, the PSS derives the
constraints.

## How constraints combine {#mm-constraints}

### Three sources of constraint

| Source | Stated by | Example |
|---|---|---|
| **Automatic** | nobody — the PSS applies it from `customerProfile` | the buyer's export-control jurisdiction; the three InSAR preconditions implied by asking for interferometry |
| **Explicit, hard** | buyer, `precedence: minimum` / `maximum` | "no worse than 2 m" |
| **Explicit, soft** | buyer, `precedence: preferred` / `alternative` | "I would like 2 m"; "X or C band" |

Table: Sources of constraint. {#tbl:mm-constraint-sources}

### Evaluation is a conjunction, then a ranking {#mm-conjunction}

**Stage 1 — admissibility. This is a logical AND.** Every automatic requirement and every
explicit hard bound must hold **simultaneously**. One failure excludes the offering, regardless
of how well it does on everything else.

**Priority plays no part in stage 1.** A top-ranked preference met perfectly does not excuse a
bottom-ranked bound that was violated. Rank never rescues an inadmissible offering.

**Stage 2 — ranking among the admissible.** `preferred` targets and `alternative` sets decide
the order, and `priority` decides which characteristic settles it first. The result is returned
as the order of the response.

**Stage 3 — relaxation, when stage 1 leaves too little to rank.** [Relaxing the request](#mm-relaxing-the-request). It moves within the
bounds the buyer stated and runs stage 1 again.

Two scopes of the logical operator, which are easy to conflate:

| | Operator |
|---|---|
| **Between characteristics** | **AND** — always. Every stated constraint applies |
| **Within one characteristic, among values marked `alternative`** | **OR** — any of the listed values satisfies it |

Table: Scopes of the logical operator. {#tbl:mm-operator-scopes}

### Expressing a target with a tolerance {#mm-expressing-a-target-with-a-tolerance}

The buyer's "desired value plus accepted deviation" is not one field, and it is not two
characteristics either. It is **two values of the same characteristic**, each carrying its own
`precedence`:

| Value | `precedence` | Value | Role |
|---|---|---|---|
| target | `preferred` | 2 m | drives the **order** — how close is this offering |
| bound | `maximum` | 3 m | drives **admissibility** — beyond this, excluded |

Table: A target with a tolerance, as two values of one characteristic. {#tbl:mm-target-tolerance}

Both live inside one `inquiredProductCharacteristic` and share its `priority`.

An offering at 2.5 m is admissible and ranks below one at 2.1 m. An offering at 3.5 m is
excluded and never reaches the ranking.

**This pair is also the relaxation budget.** The span from 2 m to 3 m is exactly how far the
buyer has said the PSS may go — [Relaxing the request](#mm-relaxing-the-request).

### Four kinds of attribute

| Kind | PSI `precedence` | Role in evaluation |
|---|---|---|
| **Requirement** | automatic, or `minimum`/`maximum` | stage 1 — excludes on violation, never ranked |
| **Ordered axis** | `preferred` with a target, optionally bounded | stage 2 — ordered by distance from target |
| **Set axis** | `alternative` | stage 2 — ordered by membership, optionally with a preference order |
| **Non-axis** | — | never exposed in an inquiry |

Table: Kinds of attribute and their precedence. {#tbl:mm-attribute-kinds}

**A non-axis is not an unimportant attribute.** `enl` matters greatly to image quality — but no
buyer shops by equivalent number of looks. They shop by sharpness and smoothness, of which
`enl` is a consequence. Exposing consequences invites the buyer to over-constrain the query and
receive nothing.

### Relaxing the request {#mm-relaxing-the-request}

A buyer who states eight constraints usually states them more tightly than they mean, and an
empty result set is not a useful answer. **The PSS may loosen the request rather than return
nothing** — within limits the buyer has already drawn.

PSI-TAD says it plainly: *"the more restrictive the characteristics, the higher the probability
that no suitable options will be found. To alleviate this situation, **the customer (or the
PSS)** can define their absolute minimum or maximum values"*. Offerings that do not fully meet
the request *"are referred to as **partial matches** and shall contain human-readable **notes**
describing the rationale for deviations"*.

**The budget is the `preferred`-to-bound band and nothing else.** A PSS moving inside it is
doing what the `precedence` field was defined for: a response *"should aim to fullfil the
'preferred' value(s), but may deviate in the boundaries of min/max values if specified"*. Going
past a stated bound is not relaxation, it is ignoring the buyer. Nothing in PSI suggests
dropping a characteristic altogether, and this document does not propose it.

| | Rule |
|---|---|
| **1** | **Move only within the stated band.** A `preferred` target gives way towards its `minimum`/`maximum`; a bound with no target beside it gives nothing |
| **2** | **Give in reverse rank order.** The lowest-`priority` characteristic yields first. Where ranks tie, [User needs and the rankings they imply](#mm-user-needs-and-the-rankings-they-imply) says which of the two the use case can actually afford to lose |
| **3** | **Say what was relaxed.** `relationshipCharacteristic[].deviation` and `InquiryResult.note`, which is what ADR022 chose them for |

Table: Rules for relaxing the request. {#tbl:mm-relaxation-rules}

Two things the PSS cannot reach, and both are structural rather than a matter of care. The
automatic requirements of [Requirements applied automatically — the buyer never states them](#mm-automatic-requirements) are not characteristics, so relaxation cannot touch them. And a
buyer who stated a bound with no target beside it has said there is nothing to give.

> [!NOTE]
> **The order matters more than the amount.** Giving up resolution for a buyer who wanted
> sharpness, or the deadline for a buyer who wanted speed, returns offerings that satisfy the
> query and defeat the purpose. [User needs and the rankings they imply](#mm-user-needs-and-the-rankings-they-imply) is what keeps that straight: it records what each use case
> was actually after, which `priority` alone cannot say once two characteristics tie.

**Relaxation does not survive the relay.** In distributed matchmaking ([Who actually answers](#mm-who-actually-answers)) the downstream PSS
or provider receives the characteristics and their ranks, and relaxes to its own taste within
them. The aggregator learns what each did only from prose — gap M-4.

**Relaxation is not the only remedy, and may not be the right one.** The standard's own answer
to an empty result set is to escalate: **RFQ** when a provider could serve the request but
publishes no offering for it, **ITT** when the buyer wants the market to come to them ([Four kinds of inquiry, one data format](#mm-four-kinds-of-inquiry-one-data-format)). A
buyer whose requirements are genuinely firm is better served by a tender than by a loosened
query, and the PSS should be able to say which of the two it is recommending.

## The axes {#mm-the-axes}

Each row states the attribute path, which `precedence` value a buyer would naturally use, and
which direction is better.

### Primary axes — present in most inquiries

| # | Axis | Attribute path | `precedence` | Better is | Unit |
|-|-|-----|-|-|-|
| **A1** | **Spatial resolution** | `resolution[].valueMax` per `axis` | `maximum` | lower | m |
| **A2** | **Delivery latency** | `deliveryLatency.duration` (archive) · `taskingTier[].deliveryLatency.duration` (tasking) | `maximum` | lower | h |
| **A3** | **Time to acquisition** | `taskingTier[].fulfilmentTime.duration` | `maximum` | lower | d |
| **A4** | **Orderable area** | `targetProfile.targetAreaMin` / `targetAreaMax` | the buyer's area must fall inside | — | km² |
| **A5** | **Revisit interval** | `revisitProfile.revisitTimeMax` | `maximum` | lower | h/d |

Table: Primary matchmaking axes. {#tbl:mm-primary-axes}

**A1 is the axis most likely to be used and most likely to be used wrongly** — see [Resolution — normalise the plane before comparing](#mm-normalise-resolution).

**A2 and A3 are different questions** and a buyer usually means one of them. *When will the
satellite photograph it* and *when will the file reach me* are separate in the model precisely
because vendors quote them interchangeably.

### Secondary axes — common, decisive when present

| # | Axis | Attribute path | `precedence` | Note |
|-|-|---|-|-|
| **A6** | Frequency band | `sensorProfile.frequencyBand` | `alternative` | `X` and `C` are near-substitutes for surface work, `L` is not |
| **A7** | Polarimetric capability | `polarizationMode`, `polarization` | `alternative` or `minimum` | preference order `QUAD ⊃ DUAL ⊃ SINGLE` |
| **A8** | Processing level | `processingLevel`, `productType` | `alternative` | not ordered — higher is more processed, not better |
| **A9** | Geolocation accuracy | `geolocationAccuracy.value` | `maximum` | lower is better |
| **A10** | Incidence angle | `acquisitionProfile.incidenceAngle` | buyer's angle inside the range | indicative until POQ confirms |
| **A11** | Archive depth | `archiveAccess.archiveDepthFrom` | `maximum` on the date | earlier is better |
| **A12** | Noise floor | `radiometricQuality.neszMax` | `maximum` | lower, i.e. more negative, is better |

Table: Secondary matchmaking axes. {#tbl:mm-secondary-axes}

**A12 looks specialist and is decisive for a whole class of work.** Low-backscatter targets —
calm water, dry sand, asphalt, oil slicks — disappear into the noise floor. A buyer detecting
oil spills or mapping floods over smooth water cares about NESZ more than about resolution.

### Specialist axes — rare, decisive in their niche

| # | Axis | Attribute path | Used by |
|-|-|---|--|
| **A13** | Swath width | `coverageGeometry.swathWidth` | Maritime and wide-area surveillance, where area per pass beats sharpness |
| **A14** | Acquisition pattern | `acquisitionPattern[].patternType` | Monitoring programmes needing a rhythm, not a single image |
| **A15** | Cadence | `recurringAcquisition.cadenceMin` / `cadenceMax` | Same; must contain the buyer's desired interval |
| **A16** | Exclusivity | `exclusivity`, `holdbackPeriod` | Buyers for whom competitor access to the same scene is the risk |
| **A17** | Delivery channel and format | `deliveryChannel`, `fileFormat` | Buyers with a fixed pipeline; effectively a gate when the pipeline cannot change |
| **A18** | Interpretability | `rniirs`, `informationDensity` | Visual analysts. The only single-number comparative metric, hence tempting — published by one vendor only |
| **A19** | Collection success rate | `taskingTier[].collectionSuccessRate` | Buyers with a deadline, for whom a failed acquisition is worse than a slow one |
| **A20** | Daily capacity | `taskingTier[].passLimit` | Buyers needing many acquisitions in a short window |

Table: Specialist matchmaking axes. {#tbl:mm-specialist-axes}

### Cross-vendor classification axis

| # | Axis | Attribute path | Note |
|---|---|---|---|
| **A21** | Copernicus Contributing Mission class | `copernicusCMClass` — `VHR1` \| `VHR2` | The **only cross-vendor resolution vocabulary** found in this work. It lets a buyer familiar with Copernicus categories state a resolution requirement without knowing any vendor's terminology |

Table: Cross-vendor classification axis. {#tbl:mm-cross-vendor-axis}

> [!NOTE]
> **Source caveat.** ICEYE publishes the assignment of its modes to these classes; the normative
> thresholds were not located in ESA/Copernicus documentation. An offering's value here is a
> **label quoted from the vendor**, not a verifiable claim. Usable as a coarse filter, not as a
> tie-breaker.

## Normalisation — the part that must be built {#mm-normalisation}

Six attributes are **not comparable as raw numbers across offerings**. A matchmaking engine
that skips these rules produces a ranking that looks plausible and is wrong.

**Distributed matchmaking makes this worse, not merely larger.** When answers come from several
respondents ([Who actually answers](#mm-who-actually-answers)), the rules below must run **either at every respondent identically, or at
the aggregator on values that still carry their qualifiers**. Anything else compares a
normalised figure against a raw one and produces a ranking nobody can defend. The rules have no
home today — gap M-3 — and federation is what makes that homelessness expensive.

### Resolution — normalise the plane before comparing {#mm-normalise-resolution}

A slant-plane figure is always numerically better than the ground-plane figure describing the
same product, by one over the sine of the incidence angle — **exactly two at 30°**, approaching
three at 20°.

```
if plane == SLANT:  ground_equivalent = value / sin(θ)
else:               ground_equivalent = value
```

`θ` is the incidence angle the comparison is made at — the buyer's angle, where A10 states one.
A slant figure does not depend on the angle, so it carries no `referenceAngle` of its own to
use; without a stated angle, take the lower end of the offering's `incidenceAngle` range, which
gives the worst ground equivalent.

Without this, a slant 0.25 m offering outranks a ground 0.40 m offering, when at 30° the first
is genuinely 0.50 m on the ground and therefore **worse**.

Three further conditions:

* **Compare per axis**, because range and azimuth come from different physical mechanisms.
* **Compare at the same angle.** A ground figure holds at its `referenceAngle` — a single angle,
  or an angular range with `valueMin` and `valueMax` at its two ends. Where a product carries one
  entry per beam, use the entry whose angle covers the buyer's. A near-range figure of one
  product set against a mid-swath figure of another compares geometry, not products.
* **Compare like with like.** `averagedOverOrbits` marks a figure averaged over the minimum and
  the maximum orbit rather than stated for one geometry. A mismatch is not convertible; say so in
  the result note rather than adjusting either number.

**Recommendation:** rank on `max(ground_equivalent(range), ground_equivalent(azimuth))` — the
worse of the two axes, because that is what limits what the buyer can see.

### Delivery latency — two different zero points

| Access mode | Measured from |
|---|---|
| Archive | **order placement** — the scene exists |
| Tasking | **acquisition** — the scene does not exist yet |

Table: Zero point of delivery latency by access mode. {#tbl:mm-latency-zero-point}

For a tasking offering the buyer's real wait is `fulfilmentTime + deliveryLatency`; for archive
it is `deliveryLatency` alone.

A buyer waiting for **new scenes to reach an archive** — standing access — has a third figure in
front of `deliveryLatency`: `archiveUpdateLatency`, measured from the **end of sensing** to the
moment the scene is discoverable and orderable. Downlink and processing lie inside it, the
buyer's own download does not.

**Recommendation:** normalise to **time from order to file in hand** and compare that.

### Any SLA figure — the commitment level is part of the value {#mm-any-sla-figure-the-commitment-level-is-part-of-the-value}

`{8 h, GUARANTEED, 99%}` and `{8 h, BEST_EFFORT}` are two different products behind one number.

**Recommendation:** treat `commitmentLevel` as a secondary sort key, or penalise `BEST_EFFORT`.
Do not compare a best-effort duration against a guaranteed one as if they were the same quantity.

### Revisit — comparable only at the same latitude

`revisitTimeMin` and `revisitTimeMax` hold at the latitude in `revisitReferenceLatitude`.
Revisit lengthens towards the equator. Two offerings quoting "6 hours" at 60° and at 10°
describe very different constellations.

Note also that **narrowing the accepted incidence angle range lengthens real revisit**. An inquiry constraining A10 tightly has implicitly worsened A5.

### Quality figures — qualifiers before numbers

| Figure | Qualifier | Trap |
|---|---|---|
| `radiometricAccuracyAbs` / `Rel` | `sigmaMultiplier` | 1 dB at 3σ and 0.317 dB at 1σ are **the same quality**; compared raw they look like a threefold difference |
| `geolocationAccuracy` | `metric`, `ephemerisClass`, `terrainCorrected` | `CE90` and `RMSE` are not interconvertible without distributional assumptions |
| any quality figure — A9, A12, A18 | the `qualityQualifier` entry for its group: `basis`, `aggregation` | A `MEASURED` figure and a `THEORETICAL` one are different kinds of statement, and so are a `WORST_CASE` and a `TYPICAL` one. The qualifier is declared **per group**: read the `GEOMETRIC` entry for A9, `RADIOMETRIC` for A12, `INTERPRETABILITY` for A18 — or the single `ALL` entry where the seller gave one |

Table: Qualifiers required before comparing accuracy figures. {#tbl:mm-accuracy-qualifiers}

**Recommendation:** normalise the sigma multiplier before comparing. For geolocation, **do not
convert between metrics** — compare within metric groups, or treat a mismatch as incomparable
rather than guessing. Treat the basis as a secondary sort key, as with `commitmentLevel` ([Any SLA figure — the commitment level is part of the value](#mm-any-sla-figure-the-commitment-level-is-part-of-the-value)):
between two equal figures a guaranteed one ranks above a measured one, and a measured one above
a theoretical one; `NOT_STATED`, the commonest value, says nothing either way.

### Pair-wise bounds — comparable only under the same pairing scheme {#mm-pair-wise-bounds-comparable-only-under-the-same-pairing-scheme}

The bounds of `memberConstraint` that hold between pairs — `temporalBaselineMax`,
`perpendicularBaselineMax`, `burstSyncTolerance`, `dopplerCentroidDifferenceMax` — hold between the pairs
`pairingScheme` names. The same number is a different commitment under each scheme, because
orbital drift accumulates along a long series:

| `pairingScheme` | A bound *b* holds | Between any two members of the set |
|---|---|---|
| `ALL_PAIRS` | between every pair | at most *b* — **the strictest** |
| `COMMON_REFERENCE` | between each member and the reference | at most **2b**, through the reference |
| `CONSECUTIVE` | between neighbours in time | by the same reasoning, at most (*n* − 1)·*b* across a set of *n* |

Table: Pair-wise bounds by pairing scheme. {#tbl:mm-pairing-schemes}

**Recommendation:** compare pair-wise bounds only under the same scheme. Across schemes a bound
converts one way only: an `ALL_PAIRS` bound *b* satisfies a `COMMON_REFERENCE` or `CONSECUTIVE`
request of *b*; a `COMMON_REFERENCE` bound *b* is certain to satisfy an `ALL_PAIRS` request only
of 2b or more. For a set of two all three schemes name the same pair and the question
disappears. The equality conditions (`sameTrack` and the rest) and `commonCoverageFractionMin`
hold for the whole set whatever the scheme, and compare directly.

## User needs and the rankings they imply {#mm-user-needs-and-the-rankings-they-imply}

A buyer who can name `radiometricQuality.neszMax` does not need this section. The buyer the PSS
has to serve says *"there is a flood here and I need to see it this week"*, and that sentence
constrains half a dozen axes at once.

**Today, someone has to do that translation before the inquiry is sent** — the buyer, or the
front end they are typing into. PSID001 carries characteristics and nothing else. What follows
is that translation, written out for six recurring cases: the **gates** each implies, the
**ranking** it sets, and what it can afford to give up when nothing matches ([Relaxing the request](#mm-relaxing-the-request)).

| id | User need | The sentence the buyer would say |
|---|---|---|
| **UN-1** | Emergency response and rapid mapping | "Something has happened here and I need to see it now" |
| **UN-2** | Change detection and monitoring | "Show me this place again and again, the same way each time" |
| **UN-3** | Interferometry and ground motion | "I need to know whether this ground is moving" |
| **UN-4** | Maritime and wide-area surveillance | "Show me everything on this stretch of sea" |
| **UN-5** | Infrastructure and asset inspection | "Show me this structure in detail, from this angle" |
| **UN-6** | Analysis-ready pipelines | "Feed my pipeline; it will not negotiate about the format" |

Table: User needs. {#tbl:mm-user-needs}

> [!NOTE]
> **The ranking is an order, not a weighting.** `A > B` means *B settles the order only where A
> ties*, and `A = B` means the two are equally important, which `priority` permits outright.
> Write the leftmost axis as `priority: 1` and count up, giving tied axes the same number. All
> constraints still hold as a conjunction ([Evaluation is a conjunction, then a ranking](#mm-conjunction)): an offering that wins on the leftmost axis
> and fails a bound on the rightmost is **excluded**, not ranked first. The order decides what
> an unmet **preference** costs, never whether a **requirement** can be skipped.

### UN-1 — Emergency response and rapid mapping
**Gates:** access `TASKING` · licence permits humanitarian use
**Ranking:** A3 > A2 > A19 > A1 > A4
**Gives up first:** A4, then A1, then A19. **Never A3 or A2** — a need defined by urgency that
relaxes its deadline has become a different need.

Speed dominates. A 3 m image today beats a 0.5 m image in four days. `collectionSuccessRate`
ranks unusually high because a missed acquisition is not a delay, it is a failure.

### UN-2 — Change detection and monitoring
**Gates:** `acquisitionPattern` contains `RECURRING` or `CONTINUOUS`
**Ranking:** A5 = A15 > A1 > A9
**Gives up first:** A9, then A1. **Never A5 or A15** — the rhythm is the need.

The rhythm and its reliability matter, not any single image. Geolocation accuracy ranks higher
than usual because scenes must overlay each other across the series.

### UN-3 — Interferometry and ground motion
**Gates:** phase preserved · `interferometricUse` · `repeatCycle` · `orbitTubeDiameter` — all four
**Ranking:** A5 > A1 > A12
**Gives up first:** A12, then A1. **Never the gates** — a product without preserved phase is not
a worse answer, it is no answer.

The gates do nearly all the work. A constellation with six-hour revisit that does not control
its orbital track is **excluded**, not down-ranked.

The gates have an orderable counterpart: `memberConstraint` on the recurring acquisition pattern
states, as conditions on the order, what must hold between the acquisitions of a pair or a
longer stack — the same track, pass direction and look side across the whole set; the temporal
and perpendicular baselines (`temporalBaselineMax`, `perpendicularBaselineMin` / `Max`); burst
synchronisation in the burst modes or Doppler centroid difference in stripmap and spotlight
(`burstSyncTolerance`, `dopplerCentroidDifferenceMax`); and the share of the area every member covers
(`commonCoverageFractionMin`). The pair-wise bounds hold between the pairs `pairingScheme` names
— consecutive members, each member and one reference, or every pair. So a buyer can state the
*pair* or *stack* they want and not merely filter to products capable of one. Matchmaking is
unchanged by it — the gates were never the missing half — except that two offerings' pair-wise
bounds compare only under the same scheme ([Pair-wise bounds — comparable only under the same pairing scheme](#mm-pair-wise-bounds-comparable-only-under-the-same-pairing-scheme)).

### UN-4 — Maritime and wide-area surveillance
**Ranking:** A13 > A5 > A12 > A1
**Gives up first:** A1, then A12. **Never A13** — a narrow swath does not survey an ocean.

Area per pass dominates. NESZ ranks high because vessels are detected against calm water.

### UN-5 — Infrastructure and asset inspection
**Ranking:** A1 > A9 > A10 > A5
**Gives up first:** A5, then A10, then A9. **Never A1** — sharpness is the need.

Sharpness dominates. Incidence angle appears because facade inspection needs a *specific*
viewing geometry, not merely a good one. The side the structure is seen from belongs to the
same geometry: `acquisitionProfile.orbitDirection` together with
`acquisitionProfile.observationDirection` fixes whether the scene is lit from the east or the
west, and like A10 it is confirmed per pass only at POQ.

### UN-6 — Analysis-ready pipelines
**Gates:** `ardFamily` + `ardConformanceLevel` · `deliveryChannel` and `fileFormat` fixed
**Ranking:** A2 > A1 > A4
**Gives up first:** A4, then A1, then A2. **Never the gates** — a non-conformant product is not
a relaxation, it is a product the buyer cannot open.

The gates dominate. A pipeline consuming CEOS-ARD NRB cannot use a better non-conformant product.

### When no need fits

A buyer who knows the vocabulary states characteristics directly, and the six cases above must
never be the only way in. **A user need must always be equivalent to the characteristics it
expands to**, so that an expert can reproduce one by hand and a provider can audit what was
asked on the buyer's behalf. A need that cannot be written out as [axes](#mm-the-axes) is not a need, it is a
hidden policy.

### Where this is heading {#mm-where-this-is-heading}

Nothing above is carried by the interface. The buyer pushes characteristics; the need that
produced them stays in the front end.

The evolution is to let `CustomerInquiry` carry the need itself, so the **PSS** performs the
translation rather than the buyer. Three things change when it does:

* a non-technical buyer states a goal instead of eight constraints;
* the result can be explained back in the buyer's own terms, because the need is still there to
  explain it with;
* **relaxation becomes answerable across systems** — a downstream PSS that receives the need
  knows which axis the use case can afford to lose, which ranks alone cannot tell it ([Relaxing the request](#mm-relaxing-the-request)).

That requires an agreed list, published where every PSS reads the same expansion. Who owns it
and where it lives are [open questions](#mm-open-questions).

## What the current API cannot express

Six gaps, each traceable to a field that exists or does not. Four of the six only appear once
matchmaking is distributed ([Who actually answers](#mm-who-actually-answers)); on a single PSS answering from its own catalogue, the
interface does what is asked of it.

| # | Gap | Consequence |
|---|---|---|
| **M-1** | **The inquiry carries the customer's facts, not the constraints derived from them.** `customerProfile` holds `country` and `securityGrade`; the [product-level exclusions](#mm-automatic-requirements) are derived by the PSS and recorded in no field of the inquiry or the result | The buyer cannot tell an offering that was never on the market from one excluded on a legal rule. Both look like absence. POQ has `eligibilityResultReason` for exactly this purpose; matchmaking has no counterpart |
| **M-2** | **A rank without a distance, and therefore no way to merge two of them.** `InquiryResult.priority` orders the results, but `coverage` is `complete`\|`partial` and nothing carries how well an offering matched | Within one PSS this is harmless — it ranks, and the order is the answer. Across respondents it is structural: each returns its own list numbered from 1, and merging N ordinal lists has no defined answer without a comparable quantity. The aggregating PSS must invent one, and two hubs will invent different ones |
| **M-3** | **The [normalisation rules](#mm-normalisation) have no home.** They are not in the catalogue, not in the inquiry, not in the result | Every implementation will either reinvent them or skip them, and skipping them produces a ranking that is confidently wrong. Across respondents the cost compounds: unless every system applies them identically, the aggregator compares normalised figures against raw ones |
| **M-4** | **An aggregating PSS has to re-rank on prose.** ADR022 deliberately chose free text over deviation flags, because a flag lets the provider decide what counts as a real deviation. For a single PSS that is right — it sorts, and the prose explains the result to a person | In distributed matchmaking the consumer of that prose is another system, which must place the offering in an order without being able to read it. The same choice that protects the buyer from a provider's judgement withholds from the aggregator the one thing it needs |
| **M-5** | **The ranking is built before anyone asks whether the top row is deliverable.** `CustomerInquiry` answers which offerings exist; which passes are free comes from POQ, which runs after the customer has selected and needs a configuration the inquiry need not have carried ([What separates matchmaking from POQ](#mm-what-separates-matchmaking-from-poq)) | For a buyer whose real constraint is "I need an image before Friday", **feasibility is not an axis** — and it is often the deciding one. They choose from a ranking nobody has tested, and learn at POQ that the top three have no pass in the window. The remedy is not to qualify everything, which is waste, but the buyer has no way to signal that feasibility is what they are ranking on |
| **M-6** | **A respondent that answers late is indistinguishable from one with nothing to offer.** `responseTime.maximum` is required when the inquiry is created, the PSS passes it on, and *"in case the provider exceeds the given deadline, its product offerings may not be considered for the issued inquiry"* | The result set is partly a function of who answered in time. `inquiredProvider[].state` records it per provider, so the PSS knows — but nothing ties a missing offering to a missed deadline, and the buyer sees only absence. Together with M-1, five different causes produce the same empty space ([Requirements applied automatically — the buyer never states them](#mm-automatic-requirements)) |

Table: API gaps affecting matchmaking. {#tbl:mm-api-gaps}

> [!NOTE]
> **The pattern is worth naming.** Every one of these is about **what one party knows and
> another cannot see**: the PSS's derived exclusions (M-1), one respondent's sense of fit
> (M-2, M-4), everyone's assumptions about units (M-3), the provider's calendar (M-5), and who
> stayed silent (M-6). The interface carries offerings well. It carries reasons poorly, and in a
> federation reasons are what the next system needs.

## What is not an axis, and why

Exposing these would let a buyer over-constrain a query and receive nothing, while appearing to
have asked something reasonable.

| Attribute | Why not |
|---|---|
| `enl`, `looksRange`, `looksAzimuthMin` / `Max`, `lookOverlap*`, `lookBandwidth*` | Consequences of processing choices. The real concern — sharpness against smoothness — is already carried by A1 |
| `weightingFunction*`, `weightingCoefficient*`, PSLR, ISLR | Internal to signal processing. PSLR is meaningful only relative to the weighting function it was measured under |
| `pixelDataType`, `bitsPerSample`, `pixelScale` | Integration details. They decide whether the buyer's software opens the file — a pipeline gate, not a preference |
| `pixelSpacingRange` / `Azimuth` | Routinely confused with resolution. Exposing both invites constraining the wrong one |
| `dataType`, `processingLevel` as separate fields | Both follow from `productType`. Let the buyer state one thing, not three that can contradict |
| `incidenceAngleVariation`, `squintAngleMax`, `illuminationTime` | Describe the acquisition mechanism, not a purchasable property |
| `footprintAreaNominal` | Describes the acquisition, not the delivered extent; must not be used for pricing or area matching — A4 is the area axis |
| `systematicProduction` | Relevant to whether the archive contains the product, already answered by the access gate and A11 |

Table: Attributes that are not matchmaking axes. {#tbl:mm-non-axes}

## Open questions {#mm-open-questions}

Matchmaking is to be defined as part of **CCN4**, so these are questions for that definition
rather than for this document.

1. **Which axes belong to the coarse pass and which to the fine one?** PSI-TAD allows a PSS to
   filter roughly and then ask a subset of providers properly ([Who actually answers](#mm-who-actually-answers)). An axis answerable only
   in the fine pass costs a round trip per provider; one answerable in the coarse pass costs
   nothing. Nothing here decides the split.
2. **How are ranked lists from different respondents merged?** Each returns its own
   `InquiryResult.priority` starting at 1. Without a comparable quantity there is no defined
   answer, and two hubs will produce different orders from identical inputs. Gap M-2.
3. **Where do the [normalisation rules](#mm-normalisation) run in a distributed matchmaking** — at every
   respondent or at the aggregator? [Normalisation — the part that must be built](#mm-normalisation) holds either way; the two give different answers unless
   every system implements them identically. Gap M-3.
4. **Should the result say who answered, and who did not answer in time?**
   `inquiredProvider[].state` carries it for the PSS. Whether the buyer should see it is a
   policy question: it exposes which providers were asked. Gap M-6.
5. **How is "incomparable" represented?** Two offerings quoting geolocation accuracy under
   different metrics cannot be ranked against each other. Does the PSS have a way to say so, or
   must it guess?
6. **Is price an axis in the same inquiry?** It is not in the product specification — it belongs
   to the offering, and after step 6 it belongs to pricing discovery — but it is certainly a
   precedence-bearing buyer concern.
7. **Should a buyer be able to say that feasibility is what they are ranking on?** Today the
   only answer is to qualify candidates after selecting them. Gap M-5.
8. **Does `CustomerInquiry` grow a user-need field, and who then owns the list?** [Where this is heading](#mm-where-this-is-heading). A list
   each PSS maintains privately is not interoperable; a list only CCN4 can extend will not keep
   pace with the market.
9. **Who are the reviewing stakeholders, and in what form is the review expected?** Recorded
   against task `T-A102010-US002-003`.

## Status

| | |
|---|---|
| **Sources** | the EO attribute taxonomy; PSID001 and PSID679; PSI-TAD on inquiry and pools; ADR015, ADR022, ADR036 |
| **Complete** | the [axes](#mm-the-axes), the layer each gate belongs to in [Gates, and which layer each belongs to](#mm-gates), and the [normalisation rules](#mm-normalisation), including the comparison of pair-wise bounds across pairing schemes |
| **Not reviewed** | everything. No stakeholder review has taken place |
| **Reasoned, not validated** | the [rankings](#mm-user-needs-and-the-rankings-they-imply) follow from what each use case physically requires, not from observed buyer behaviour |
| **Proposed, not agreed** | the six user needs, and the evolution in [Where this is heading](#mm-where-this-is-heading) that would put them on the interface |

Table: Status of this document. {#tbl:mm-status}
