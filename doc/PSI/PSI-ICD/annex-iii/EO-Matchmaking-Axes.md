# EO Matchmaking Axes and Scoring Guidance

**Version:** 0.11 · **Author:** DO · **Date:** 2026-11-12
**Activity:** A102010 — EO Domain Modeling & Matchmaking Criteria
**Status:** drafted for stakeholder review

---

## 1. Where matchmaking sits

Matchmaking is **step 2 and 3** of the PSI customer journey. Two further steps follow that are
often confused with it, and keeping them apart decides which attributes may be used as axes at
all.

```
 1  Mission                  what I need, where, when — no market involved
 2  Customer Inquiry         "what is on the market for these characteristics"
     + matchmaking            PSS, on catalogue data
 3  InquiryResult[]          which offerings fit and how well
 4  Availability Discovery   "which CONFIGURATIONS are deliverable in MY context"
 5  Pricing Discovery        "what does each cost, from the price list"
 6  Quote                    only when step 4/5 could not answer
 7  POQ                      "may THIS be sold to THIS customer in THIS place"
 8  Stock → 9 Order
```

**Three different questions, three different APIs, three different knowledge bases:**

| Step | Question | Who answers, from what |
|---|---|---|
| **2/3 Matchmaking** | What is on the market for these characteristics? | PSS, from **catalogue data** — what a provider can do *in general* |
| **4 Availability** | Can this be delivered over *my* area, in *my* window? | Provider, from **technical inventory** — capacity, coverage, contention |
| **7 POQ** | May this be sold to *this* customer? | Provider, from the **commercial relationship** — contract, quota, clearance |

This matters because a catalogue does not know context. A specification saying the product
covers 1–10 000 km² does not say that *this* AOI is coverable next Tuesday. Matchmaking can
only filter on what the catalogue knows; everything else belongs to step 4 or 7.

> **Consequence for this document.** An attribute is a matchmaking axis only if its value is
> knowable from the catalogue. Section 3 assigns each candidate gate to its proper layer, and
> several of them turn out **not** to belong to matchmaking at all.

---

## 2. What the inquiry actually carries

The PSI `customer_inquiry` API (PSID001) carries, per characteristic:

| Field | Values | Meaning |
|---|---|---|
| `inquiredProductCharacteristic[].name` | — | which characteristic |
| `inquiredProductCharacteristic[].value` | — | the value the buyer states |
| **`inquiredProductCharacteristic[].precedence`** | **`preferred` · `minimum` · `maximum` · `alternative`** | **how to read that value** |
| `bundlesOnly` | boolean, default **`true`** | whether only bundles are of interest |
| `priority` | integer, 1 = most important | **rides inside `customerProfile`**, not on the characteristic |
| `inquiredProvider[]` | per-provider state | the ITT carrier |

**`precedence` is not a weight.** It is a **constraint kind**: it says whether the stated value
is a target, a floor, a ceiling, or one of several acceptable substitutes. This is the field
that decides how an axis is scored, and it maps directly onto the attribute taxonomy in §4.

**The numeric importance is `priority`, and it is one number for the whole inquiry**, carried
as a customer characteristic. There is **no per-characteristic weighting** in the current API —
a consequence that §8 returns to, because it makes the precedence patterns of §7 aspirational
rather than directly implementable.

### What comes back

`InquiryResult` carries `productSpecification[]` (required), plus optional `productOffering[]`
and `product[]`, tied together by `inquiredProductRelationship[]` (ADR036):

| Field | Meaning |
|---|---|
| `inquiredProductRef` | which request this fulfils |
| `productOfferingRef` / `productSpecificationRef` / `productRef` | what fulfils it |
| **`coverage`** | `complete` \| `partial` |
| `quantity`, `totalPrice`, `servicePeriod` | commercial shape |
| **`relationshipCharacteristic[].deviation`** | how far this offering departs from what was asked |

Two properties of this structure shape everything below.

**`deviation` is free text.** ADR022 rejected boolean deviation flags for trust reasons and
settled on a free-text `note` plus `precedence`. A ranking engine cannot sort on prose.

**There is no score field.** `coverage` is two-valued and `totalPrice` is a price. Nothing in
the result carries *how well* an offering matched. The rank exists only in the order the PSS
returns rows.

---

## 3. Gates, and which layer each belongs to

A gate excludes rather than down-ranks. The mistake to avoid is applying one at the wrong layer.

### 3.1 Gates the catalogue can answer — matchmaking filters

| Gate | Attribute path | Why it excludes |
|---|---|---|
| **Access path** | `accessProfile[].accessMode` | Archive and tasking answer different questions. A buyer needing a new acquisition is not served by an archive scene, however good |
| **Phase preservation** | `dataType ∈ {COMPLEX, PHASE_HISTORY}` | Physical. Without phase there is no interferometry and no polarimetry — no other quality compensates |
| **Interferometric fitness** | `interferometricUse` **and** `repeatCycle` **and** `orbitTubeDiameter` | Rule W-11: preserved phase alone is insufficient. All three must hold |
| **ARD conformance** | `ardFamily` + `ardConformanceLevel` | A conformance claim is true or false. A pipeline expecting CEOS-ARD NRB cannot use "nearly NRB" |
| **Pixel value accessibility** | `ogcService` contains `WCS`, when the channel is `OGC_SERVICE` only | Rule W-23: without WCS the buyer gets pictures, not values |
| **Polarimetric capability** | `polarizationMode`, `polarization` | A buyer needing channel ratios cannot use a single-pol product |

These are declared in the catalogue, so the PSS can apply them during matchmaking.

### 3.2 Gates that depend on the delivery context — availability discovery

| Concern | Why matchmaking cannot answer it |
|---|---|
| Is *this* AOI coverable? | The catalogue states an orderable area range, not whether a specific polygon is served |
| Is there capacity in *this* window? | Contention and pass allocation are inventory state, not catalogue state |
| Is the required incidence angle achievable over *this* latitude? | Geometry depends on the target, which the catalogue does not have |

Matchmaking can **narrow** on the catalogue's declared ranges — axes A4 and A10 below — but the
answer is indicative. The binding answer comes from `POST /productOfferingAvailability`.

### 3.3 Requirements applied automatically — the buyer never states them

| Requirement | Attribute path | Applied from |
|---|---|---|
| Legal permission to buy | `licensingProfile.eligibleBuyerClass` | the buyer's organisation type |
| Export control regime | `licensingProfile.exportControl` | the buyer's jurisdiction |
| Security classification | `licensingProfile.securityClassification` | the buyer's clearance |
| Permitted purpose | `licensingProfile.permittedPurpose` | the stated purpose of the mission |
| Licence model | `licensingProfile.licenceModel` | what the buyer intends to do with the data |

**These must not be left to the buyer to request.** A buyer does not ask "please exclude
products I am legally barred from buying" — and if they did, the request could be omitted,
mistyped or weighted. The PSS holds what it needs in `customerProfile`, which travels with the
inquiry, so **it should apply these as standing requirements on every inquiry, whether or not
any characteristic mentions them**.

Two properties follow, and both matter:

* **They are never weighted.** A legal exclusion carries no `priority`, because there is no
  amount of other quality that compensates for it.
* **Matchmaking's answer is provisional.** The authoritative one is POQ, which sees the
  contract, the quota and the clearance. The same reasoning that puts `marketSegment` and
  `isSellable` into POQ rather than availability discovery applies here.

> **A provisional exclusion still has to be visible.** If a product is dropped during
> matchmaking on a legal rule, the buyer should learn that it was dropped and why — otherwise
> they never reach the POQ reason code that would have explained it, and the absence looks like
> the market simply having nothing.

A second family of requirements is implied by what the buyer asked for rather than by who they
are. Asking for interferometry implies the three preconditions of rule W-11; asking for pixel
values over an OGC service implies `WCS`. The buyer states the goal, the PSS derives the
constraints.

---

## 4. How constraints combine

### 4.1 Three sources of constraint

| Source | Stated by | Example |
|---|---|---|
| **Automatic** | nobody — the PSS applies it | the buyer's export-control jurisdiction; the three InSAR preconditions implied by asking for interferometry |
| **Explicit, hard** | buyer, `precedence: minimum` / `maximum` | "no worse than 2 m" |
| **Explicit, soft** | buyer, `precedence: preferred` / `alternative` | "I would like 2 m"; "X or C band" |

### 4.2 Evaluation is a conjunction, then a ranking

**Stage 1 — admissibility. This is a logical AND.** Every automatic requirement and every
explicit hard bound must hold **simultaneously**. One failure excludes the offering, regardless
of how well it does on everything else.

**Priority plays no part in stage 1.** A high-priority preference met perfectly does not excuse
a low-priority bound that was violated. Rank never rescues an inadmissible offering.

**Stage 2 — ranking among the admissible.** `preferred` targets and `alternative` sets
contribute to a score, and `priority` says how much each contributes.

Two scopes of the logical operator, which are easy to conflate:

| | Operator |
|---|---|
| **Between characteristics** | **AND** — always. Every stated constraint applies |
| **Within one characteristic marked `alternative`** | **OR** — any of the listed values satisfies it |

### 4.3 Expressing a target with a tolerance

The buyer's "desired value plus accepted deviation" is not one field. It is **two entries for
the same characteristic**:

| Entry | `precedence` | Value | Role |
|---|---|---|---|
| target | `preferred` | 2 m | drives the **score** — how close is this offering |
| bound | `maximum` | 3 m | drives **admissibility** — beyond this, excluded |

An offering at 2.5 m is admissible and scores below one at 2.1 m. An offering at 3.5 m is
excluded and never reaches the ranking.

### 4.4 Four kinds of attribute

| Kind | PSI `precedence` | Role in evaluation |
|---|---|---|
| **Requirement** | automatic, or `minimum`/`maximum` with no relaxation intended | stage 1 — excludes on violation, never weighted |
| **Ordered axis** | `preferred` with a target, optionally bounded | stage 2 — scored by distance from target |
| **Set axis** | `alternative` | stage 2 — scored by membership, optionally with a preference order |
| **Non-axis** | — | never exposed in an inquiry |

**A non-axis is not an unimportant attribute.** `enl` matters greatly to image quality — but no
buyer shops by equivalent number of looks. They shop by sharpness and smoothness, of which
`enl` is a consequence. Exposing consequences invites the buyer to over-constrain the query and
receive nothing.

---

## 5. The axes

Each row states the attribute path, which `precedence` value a buyer would naturally use, and
which direction is better.

### 5.1 Primary axes — present in most inquiries

| # | Axis | Attribute path | `precedence` | Better is | Unit |
|---|---|---|---|---|---|
| **A1** | **Spatial resolution** | `resolution[].valueMax` per `axis` | `maximum` | lower | m |
| **A2** | **Delivery latency** | `deliveryLatency.duration` (archive) · `taskingTier[].deliveryLatency.duration` (tasking) | `maximum` | lower | h |
| **A3** | **Time to acquisition** | `taskingTier[].fulfilmentTime.duration` | `maximum` | lower | d |
| **A4** | **Orderable area** | `targetProfile.targetAreaMin` / `targetAreaMax` | the buyer's area must fall inside | — | km² |
| **A5** | **Revisit interval** | `revisitProfile.revisitTimeMax` | `maximum` | lower | h/d |

**A1 is the axis most likely to be used and most likely to be used wrongly** — see §6.1.

**A2 and A3 are different questions** and a buyer usually means one of them. *When will the
satellite photograph it* and *when will the file reach me* are separate in the model precisely
because vendors quote them interchangeably.

### 5.2 Secondary axes — common, decisive when present

| # | Axis | Attribute path | `precedence` | Note |
|---|---|---|---|---|
| **A6** | Frequency band | `sensorProfile.frequencyBand` | `alternative` | `X` and `C` are near-substitutes for surface work, `L` is not |
| **A7** | Polarimetric capability | `polarizationMode`, `polarization` | `alternative` or `minimum` | preference order `QUAD ⊃ DUAL ⊃ SINGLE` |
| **A8** | Processing level | `processingLevel`, `productType` | `alternative` | not ordered — higher is more processed, not better |
| **A9** | Geolocation accuracy | `geolocationAccuracy.value` | `maximum` | lower is better |
| **A10** | Incidence angle | `acquisitionProfile.incidenceAngle` | buyer's angle inside the range | indicative until availability confirms |
| **A11** | Archive depth | `archiveAccess.archiveDepthFrom` | `maximum` on the date | earlier is better |
| **A12** | Noise floor | `radiometricQuality.neszMax` | `maximum` | lower, i.e. more negative, is better |

**A12 looks specialist and is decisive for a whole class of work.** Low-backscatter targets —
calm water, dry sand, asphalt, oil slicks — disappear into the noise floor. A buyer detecting
oil spills or mapping floods over smooth water cares about NESZ more than about resolution.

### 5.3 Specialist axes — rare, decisive in their niche

| # | Axis | Attribute path | Used by |
|---|---|---|---|
| **A13** | Swath width | `coverageGeometry.swathWidth` | Maritime and wide-area surveillance, where area per pass beats sharpness |
| **A14** | Acquisition pattern | `acquisitionPattern[].patternType` | Monitoring programmes needing a rhythm, not a single image |
| **A15** | Cadence | `recurringAcquisition.cadenceMin` / `cadenceMax` | Same; must contain the buyer's desired interval |
| **A16** | Exclusivity | `exclusivity`, `holdbackPeriod` | Buyers for whom competitor access to the same scene is the risk |
| **A17** | Delivery channel and format | `deliveryChannel`, `fileFormat` | Buyers with a fixed pipeline; effectively a gate when the pipeline cannot change |
| **A18** | Interpretability | `rniirs`, `informationDensity` | Visual analysts. The only single-number comparative metric, hence tempting — published by one vendor only |
| **A19** | Collection success rate | `taskingTier[].collectionSuccessRate` | Buyers with a deadline, for whom a failed acquisition is worse than a slow one |
| **A20** | Daily capacity | `taskingTier[].passLimit` | Buyers needing many acquisitions in a short window |

### 5.4 Cross-vendor classification axis

| # | Axis | Attribute path | Note |
|---|---|---|---|
| **A21** | Copernicus Contributing Mission class | `copernicusCMClass` — `VHR1` \| `VHR2` | The **only cross-vendor resolution vocabulary** found in this work. It lets a buyer familiar with Copernicus categories state a resolution requirement without knowing any vendor's terminology |

> **Source caveat.** ICEYE publishes the assignment of its modes to these classes; the normative
> thresholds were not located in ESA/Copernicus documentation. An offering's value here is a
> **label quoted from the vendor**, not a verifiable claim. Usable as a coarse filter, not as a
> tie-breaker.

---

## 6. Normalisation — the part that must be built

Five attributes are **not comparable as raw numbers across offerings**. A matchmaking engine
that skips these rules produces a ranking that looks plausible and is wrong.

### 6.1 Resolution — normalise the plane before comparing

A slant-plane figure is always numerically better than the ground-plane figure describing the
same product, by one over the sine of the incidence angle — **exactly two at 30°**, approaching
three at 20°.

```
if plane == SLANT:  ground_equivalent = value / sin(referenceAngle)
else:               ground_equivalent = value
```

Without this, a slant 0.25 m offering outranks a ground 0.40 m offering, when at 30° the first
is genuinely 0.50 m on the ground and therefore **worse**.

Two further conditions: **compare per axis**, because range and azimuth come from different
physical mechanisms; and **compare at a stated point**, because `measurementPoint` and
`measurementAggregation` say where in the scene the figure holds.

**Recommendation:** rank on `max(ground_equivalent(range), ground_equivalent(azimuth))` — the
worse of the two axes, because that is what limits what the buyer can see.

### 6.2 Delivery latency — two different zero points

| Access mode | Measured from |
|---|---|
| Archive | **order placement** — the scene exists |
| Tasking | **acquisition** — the scene does not exist yet |

For a tasking offering the buyer's real wait is `fulfilmentTime + deliveryLatency`; for archive
it is `deliveryLatency` alone.

**Recommendation:** normalise to **time from order to file in hand** and compare that.

### 6.3 Any SLA figure — the commitment level is part of the value

`{8 h, GUARANTEED, 99%}` and `{8 h, BEST_EFFORT}` are two different products behind one number.

**Recommendation:** treat `commitmentLevel` as a secondary sort key, or penalise `BEST_EFFORT`.
Do not compare a best-effort duration against a guaranteed one as if they were the same quantity.

### 6.4 Revisit — comparable only at the same latitude

`revisitTimeMin` and `revisitTimeMax` hold at the latitude in `revisitReferenceLatitude`.
Revisit lengthens towards the equator. Two offerings quoting "6 hours" at 60° and at 10°
describe very different constellations.

Note also that **narrowing the accepted incidence angle range lengthens real revisit** (rule
W-10). An inquiry constraining A10 tightly has implicitly worsened A5.

### 6.5 Radiometric and geolocation accuracy — qualifiers before numbers

| Figure | Qualifier | Trap |
|---|---|---|
| `radiometricAccuracyAbs` / `Rel` | `sigmaMultiplier` | 1 dB at 3σ and 0.317 dB at 1σ are **the same quality**; compared raw they look like a threefold difference |
| `geolocationAccuracy` | `metric`, `basis`, `ephemerisClass`, `terrainCorrected` | A `PREDICTED_BUDGET` figure and a `MEASURED_CALVAL` figure are different kinds of statement; `CE90` and `RMSE` are not interconvertible without distributional assumptions |

**Recommendation:** normalise the sigma multiplier before comparing. For geolocation, **do not
convert between metrics** — compare within metric groups, or treat a mismatch as incomparable
rather than guessing.

---

## 7. Precedence patterns by buyer profile

These follow from what each use case physically requires. **They are not directly expressible
in the current API** — see gap M-1 — so they are offered as the ranking the PSS should apply,
not as something the buyer can state.

> **What `>>` means here.** It means *contributes more to the score in stage 2*, not
> *overrides*. All constraints still hold as a conjunction (§4.2): an offering that wins on the
> leftmost axis and fails a bound on the rightmost is **excluded**, not ranked first. The
> ordering decides how much an unmet **preference** costs, never whether a **requirement** can
> be skipped.

### 7.1 Emergency response and rapid mapping
**Gates:** access `TASKING` · licence permits humanitarian use
**Ranking:** A3 >> A2 >> A19 > A1 > A4

Speed dominates. A 3 m image today beats a 0.5 m image in four days. `collectionSuccessRate`
ranks unusually high because a missed acquisition is not a delay, it is a failure.

### 7.2 Change detection and monitoring
**Gates:** `acquisitionPattern` contains `RECURRING` or `CONTINUOUS`
**Ranking:** A5 = A15 >> A1 > A9

The rhythm and its reliability matter, not any single image. Geolocation accuracy ranks higher
than usual because scenes must overlay each other across the series.

### 7.3 Interferometry
**Gates:** phase preserved · `interferometricUse` · `repeatCycle` · `orbitTubeDiameter` — all four
**Ranking:** A5 >> A1 > A12

The gates do nearly all the work. A constellation with six-hour revisit that does not control
its orbital track is **excluded**, not down-ranked.

From version 0.11 the gates have an **orderable** counterpart. Until then a buyer could filter
down to products fit for interferometry and still had no way to state the pair they wanted;
`memberConstraint` on the recurring acquisition pattern carries the same track, the temporal
baseline and the perpendicular baseline as conditions on the order. Matchmaking is unchanged by
this — the gates were never the missing half.

### 7.4 Maritime and wide-area surveillance
**Ranking:** A13 >> A5 > A12 >> A1

Area per pass dominates. NESZ ranks high because vessels are detected against calm water.

### 7.5 Infrastructure and asset inspection
**Ranking:** A1 >> A9 > A10 >> A5

Sharpness dominates. Incidence angle appears because facade inspection needs a *specific*
viewing geometry, not merely a good one.

### 7.6 Analysis-ready pipelines
**Gates:** `ardFamily` + `ardConformanceLevel` · `deliveryChannel` and `fileFormat` fixed
**Ranking:** A2 > A1 > A4

The gates dominate. A pipeline consuming CEOS-ARD NRB cannot use a better non-conformant product.

---

## 8. What the current API cannot express

Nine gaps, each traceable to a field that exists or does not. These are the matchmaking
counterpart of the PSI gap register.

| # | Gap | Consequence |
|---|---|---|
| **M-1** | **No per-characteristic weighting.** `precedence` is a constraint kind (`preferred`/`minimum`/`maximum`/`alternative`), and `priority` is a single number for the whole inquiry, carried in `customerProfile` | A buyer cannot say "latency matters three times more than resolution". Stage 1 is unaffected — the conjunction of requirements works without weights — but every stage-2 ranking pattern in §7 has to be **inferred by the PSS**, not stated by the buyer |
| **M-1b** | **No field marks a constraint as automatic.** The requirements of §3.3 are applied by the PSS from `customerProfile`, but the inquiry has no way to record that they were applied | The buyer cannot tell an offering that was never on the market from one excluded on a legal rule. Both look like absence |
| **M-2** | **`deviation` in the result is free text.** ADR022 rejected boolean deviation flags for trust reasons, settling on a free-text `note` plus `precedence` | A ranking engine cannot sort on prose. How far an offering departs from the request is visible to a human and invisible to the sort |
| **M-3** | **No score field in `InquiredProductRelationship`.** `coverage` is `complete`\|`partial`, `totalPrice` is a price | Match quality survives only as row order. Two offerings both marked `partial` are indistinguishable, and the reason for the rank is unrecoverable |
| **M-4** | **The normalisation rules of §6 have no home.** They are not in the catalogue, not in the inquiry, not in the result | Today they live nowhere, so every implementation will either reinvent them or skip them. Skipping them produces a ranking that is confidently wrong |
| **M-5** | **`priority` rides in `customerProfile`, which is in `_FVO`** — the client supplies it | On PSS-PSS a broker raises the inquiry for its own customer, so the field must be writable. But nothing documents whether the PSS must overwrite or reject a client-supplied `priority`. A matching input the client controls is a matching input the client can game |
| **M-6** | **ADR022: "the order management API only allows the booking of complete offers"** | A partial match can be reported and ranked but **not ordered**. A well-ranked `coverage: partial` row is a dead end unless order management changes |
| **M-7** | **A legal bound looks like any other bound.** `precedence: maximum` expresses both "no worse than 2 m, but I'd stretch" and "export control forbids this" | Nothing marks a constraint as non-negotiable. If the PSS ever weights rather than filters, an attractive offering can outrank a legal exclusion |
| **M-8** *(0.10)* | **The inquiry cannot ask about feasibility.** `CustomerInquiry` answers **which offerings exist**, not **which passes are free** — the latter needs a concrete Check POQ, one per candidate offering | For a buyer whose real constraint is “I need an image before Friday”, **feasibility is not an axis** — and it is often the deciding one. The ranking is built on product parameters and then founders on the fact that the top-ranked offering has no pass in that window |
| **M-9** *(0.10)* | **The qualification result has three colours, the inquiry result has none.** `qualificationItemResult` distinguishes `green` / `yellow` / `red`, while `InquiryResult` carries `coverage: complete`\|`partial`, which is about **area coverage**, not about quality validity | A pass that is sellable but lies outside `qualityValidIncidence` is indistinguishable in the inquiry from a fully compliant one. A buyer ranking on resolution will rank highly an offering whose **published figures do not apply** to the only available pass |

> **M-7 is the one with the worst failure mode.** The other six degrade ranking quality; this
> one can produce a recommendation that must not be acted on. Section 3.3 is the mitigation:
> keep licence, export control, security classification and permitted purpose in POQ, and treat
> any matchmaking-level filtering on them as a convenience that POQ still has to confirm.

## 9. What is not an axis, and why

Exposing these would let a buyer over-constrain a query and receive nothing, while appearing to
have asked something reasonable.

| Attribute | Why not |
|---|---|
| `enl`, `looksRange`, `looksAzimuth`, `lookOverlap*`, `lookBandwidth*` | Consequences of processing choices. The real concern — sharpness against smoothness — is already carried by A1 |
| `weightingFunction*`, `weightingCoefficient*`, PSLR, ISLR | Internal to signal processing. PSLR is meaningful only relative to the weighting function it was measured under (rule W-32) |
| `pixelDataType`, `bitsPerSample`, `pixelScale` | Integration details. They decide whether the buyer's software opens the file — a pipeline gate, not a preference |
| `pixelSpacingRange` / `Azimuth` | Routinely confused with resolution. Exposing both invites constraining the wrong one |
| `dataType`, `processingLevel` as separate fields | Both follow from `productType`. Let the buyer state one thing, not three that can contradict |
| `incidenceAngleVariation`, `squintAngleMax`, `illuminationTime` | Describe the acquisition mechanism, not a purchasable property |
| `footprintAreaNominal` | Describes the acquisition, not the delivered extent; must not be used for pricing or area matching — A4 is the area axis |
| `systematicProduction` | Relevant to whether the archive contains the product, already answered by the access gate and A11 |

---

## 10. Attribute-to-axis coverage

Every attribute falls into exactly one category.

| Category | Count | Share |
|---|---|---|
| Gate — catalogue | 6 | 4% |
| Gate — POQ (eligibility) | 5 | 3% |
| Gate — availability context | 3 | 2% |
| Ordered axis | 13 | 9% |
| Set axis | 8 | 6% |
| Non-axis | 109 | 76% |

**Three quarters of the namespace is not matchmaking material, and that is the expected
result.** A product specification exists to describe a product completely; an inquiry exists to
narrow a choice. Conflating them produces either an unusable inquiry form with 153 fields, or a
specification too thin to describe what the buyer receives.

---

## 11. Open questions for stakeholder review

1. **Does the PSS filter or weight on eligibility attributes?** If licence, export control and
   security classification are weighted rather than filtered, an attractive offering can
   outrank a legal exclusion. Gap M-7; mitigation in §3.3.
2. **Where do the normalisation rules of §6 live** — in the PSS matching engine, in an adapter,
   or in the product specification as derived attributes? Gap M-4.
3. **Is per-characteristic weighting worth adding to PSID001?** Gap M-1 makes every pattern in
   §7 a PSS-side inference. Adding a weight would move it to where the buyer can state it — at
   the cost of extending the API.
4. **How is "incomparable" represented?** Two offerings quoting geolocation accuracy under
   different metrics cannot be ranked against each other. Does the PSS have a way to say so, or
   must it guess?
5. **Should `coverage: partial` rows be returned at all**, given ADR022 forbids ordering them?
   Gap M-6. Returning them is honest; returning them without saying they cannot be ordered is
   not.
6. **Is price an axis in the same inquiry?** It is not in the product specification — it belongs
   to the offering, and after step 4/5 it belongs to pricing discovery — but it is certainly a
   precedence-bearing buyer concern.
7. **Who are the reviewing stakeholders, and in what form is the review expected?** Recorded
   against task `T-A102010-US002-003`.

---

## 12. Status

Drafted from `EO-Attribute-Taxonomy-Baseline-EN_DO_v0.11.md`, with the PSI interaction model
taken from the POQ/Quote analysis of 2026-09-08 (`customer_inquiry` field semantics, ADR022,
ADR036, the journey with availability and pricing discovery).

Axis definitions, the layer assignment of gates and the normalisation rules are complete. **The
stakeholder review has not taken place**, the ranking patterns of §7 are reasoned from physical
requirements rather than validated against buyer behaviour, and seven of them cannot be stated
in the current API at all.
