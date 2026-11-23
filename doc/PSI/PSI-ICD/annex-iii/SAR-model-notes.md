# SAR Model Notes — Design Commentary

**Version 0.11** · **author: DO**
**Companion files:** `SAR-technical-specification-EN_DO_v0.11.md` ·
`SAR-model-UML-EN_DO_v0.11.puml`

---

> **What this file is.** The reasoning behind the model, one section per class:
> why each construct is shaped the way it is, and what goes wrong under the
> alternatives. The class diagram carries **structure only** and this file
> carries the argument, so that neither has to be read through the other.
>
> This is not the technical reference. That is in
> `SAR-technical-specification-EN_DO_v0.11.md` and describes the **current
> state**: what each class and attribute means. This file holds the **reasons**:
> why something is modelled one way rather than another, and what would go wrong
> under a different choice.

## Class index

[AccessProfile](#accessprofile) ·
[AcquisitionPattern](#acquisitionpattern) ·
[AssetLayer](#assetlayer) ·
[CoverageGeometry](#coveragegeometry) ·
[DeliveryProfile](#deliveryprofile) ·
[GcpSet](#gcpset) ·
[GeolocationAccuracy](#geolocationaccuracy) ·
[GeometricQuality](#geometricquality) ·
[LicensingProfile](#licensingprofile) ·
[MemberConstraint](#memberconstraint) ·
[ProcessingProfile](#processingprofile) ·
[QualityProfile](#qualityprofile) ·
[RadiometricFigure](#radiometricfigure) ·
[RadiometricQuality](#radiometricquality) ·
[Resolution](#resolution) ·
[RevisitProfile](#revisitprofile) ·
[SarProductSpecification](#sarproductspecification) ·
[SceneReference](#scenereference) ·
[ServiceCommitment](#servicecommitment) ·
[TargetProfile](#targetprofile) ·
[TaskingTier](#taskingtier)

---

## AccessProfile

**Subtype selector: `accessMode`.** Inapplicability of attributes here follows
from the **subtype**, not from a conditional rule. That is the stronger form:
a configuration mixing archive attributes with tasking attributes is
**inexpressible**, not merely forbidden.

| Subtype | What it carries |
|---|---|
| `ArchiveAccess` | archive depth, update latency, delivery latency, catalogue interface, scene references, standing access |
| `TaskingAccess` | service tiers, acquisition patterns, scheduling bounds, exclusivity |

**Both subtypes carry `orderingMode`, and the base class does not.** How the
buyer names what they want is the axis that decides whether an order carries a
geometry and a time window or a reference to a candidate pass, so both sides of
the model need it.

It is **repeated in each subtype with a different domain** rather than lifted
into the base class. A shared domain would permit `BY_SCENE` under tasking —
ordering a scene that does not exist yet — and `BY_OPPORTUNITY` under archive,
where there is nothing to propose. A field common in name only is worse than two
fields that are plainly different, which is the same reasoning that keeps
`deliveryLatency` out of the base class.

**Why cardinality `1..2`.** Because `accessMode` has cardinality `[1..2]`:
a product sold both from the archive and through tasking has **two instances** of
`AccessProfile`, one of each subtype. An archive-only product has one.

**`deliveryLatency` sits on `ArchiveAccess`, not on the base class.**
On the tasking side, delivery latency is **per service tier** — ICEYE sells 3 h,
8 h and 24 h as three tiers of the same product — so it sits in `TaskingTier`.
A single number per product would be either false, or an envelope with no
indication of which tier realises it. On the archive side it stays a single
number, because there is nothing to prioritise: the file already exists.

As a side effect the base class now carries **only the subtype selector**. That
is fine — its job is to branch, not to hold common fields that, on inspection,
turned out not to exist.

---

## AcquisitionPattern

**Subtype selector: `patternType`.** It exists to keep the two branches
symmetrical. The archive branch can express standing access to an area; without
`patternType` the tasking branch could express only the one-off case — and would
not say so, which amounts to claiming that standing access is possible only
where the images are taken anyway, without the buyer's involvement.

| Subtype | What it carries | What the buyer gets |
|---|---|---|
| `SingleAcquisition` | retask after a failed acquisition | one image within a window |
| `RecurringAcquisition` | cadence, repetition count, iteration failure policy, conditions between members | *n* images in a rhythm |
| `ContinuousAcquisition` | coverage period | whatever can be acquired within the period |

**Why subtypes rather than one field with rules.** The attribute sets are
disjoint **in three directions**: cadence is meaningless for `SINGLE` and
`CONTINUOUS`, coverage period for `SINGLE` and `RECURRING`, retask for the other
two. With a single enum field plus optional attributes, the structure would
permit cadence **and** coverage period at the same time — two mutually exclusive
ways of specifying density.

**Scope of the concept.** This concerns **acquisition recurrence** — what lands
on disk. **Billing recurrence** (when the invoice goes out, how many months the
contract binds) is an independent dimension and belongs to
`ProductOfferingPrice` and `ProductOfferingTerm`, i.e. outside this model.

Proof that the axes are independent: all four combinations occur in the market —
one image for one invoice, one image on a monthly subscription, twelve images
paid up front, twelve images with a monthly invoice.

---

## AssetLayer

**A role without presence information is ambiguous.** A flat list of roles does
not say whether a layer is always in the package or whether the buyer selects
it. Since the characteristic is marked `D · C`, the role alone would suggest
that **everything** is optional — including the image itself, which is absurd.

Hence `presence`, and hence its two values: `ALWAYS` (cannot be opted out of) and
`ON_REQUEST` (the buyer selects it at order time). The qualifier travels **with
the specific layer**, not with the field — the same principle as in `Resolution`,
`ServiceCommitment` and `TaskingTier`.

**What is deliberately absent here.** The CEOS requirement level
(`THRESHOLD` / `GOAL`) is **not entered by the vendor**. It is settled by the
standard: under `ardFamily = NRB` the list of roles required at each level is
published by CEOS and the vendor does not choose it. A writable field would allow
nonsense to be declared — `covmat` as `THRESHOLD` for NRB, which it never is. The
dependency is therefore **a mapping table in the documentation plus a validation
rule**, not an attribute.

**A consequence the previous model could not carry:** a layer required by the
standard but delivered only on request **does not satisfy conformance**. A
product offering `local-incidence-angle` as optional is not CEOS ARD Threshold
conformant, even if the catalogue says it is.

**Limit of the construct.** Layer selection is binary. The buyer can say "I want
the incidence angle map", but not "at a different resolution" or "in a different
format". No such case was found at the vendors reviewed, so it is not modelled —
but if one appeared, `AssetLayer` would have to become a configurable object
rather than merely a selectable one.

---

## CoverageGeometry

**Subtype selector: `collectionStrategy`.**

| Values | Subtype |
|---|---|
| `SPOTLIGHT`, `SLIDING_SPOTLIGHT` | `SceneCoverage` |
| `STRIPMAP`, `PARALLEL_STRIPMAP`, `SCANSAR`, `TOPSAR` | `SwathCoverage` |

The notion of a *swath* **does not exist** in scene modes — the radar does not
sweep a strip, it stares at one point while rotating the beam. Conversely, in
swath modes there is no "scene" of fixed dimensions; there is a strip from which
pieces are cut. These are therefore not two sets of the same fields under
different names.

**`footprintAreaNominal` describes the acquisition, not the delivered extent.**
With `clippingSupported = true` the delivered extent follows from the requested
area and is
bounded by `TargetProfile`. The field must not be used for area-based pricing.

**Note on domain provenance.** The numeric ranges for `swathWidth`,
`stripLengthMin`/`Max` and `footprintAreaNominal` are **estimates from observed
market values** (Sentinel-1 IW 250 km, EW 400 km; Capella and ICEYE strip
lengths), not published limits. Recorded in the inferred-values register.

---

## DeliveryProfile

`volumeMinGB`/`MaxGB` is a range for the **full acquisition**, not for a cut-out.
Clipping makes the volume proportionally smaller; adding `ON_REQUEST` layers
makes it larger. The model does not carry per-layer volume, so on an order with
extra layers the declared range ceases to hold.

`multiSceneDelivery` settles whether an order covering *n* scenes yields *n*
separate products or one seamless mosaic. A buyer building a time series takes
`SEPARATE`; one making a map backdrop takes `MOSAIC`.

**`ogcService` — four services that are not equivalent.** `WMS` and `WMTS` return
**a picture**; `WCS` returns **values**. A product served only over WMS is for
looking at, not for computing on: pixel values are inaccessible, so
`radiometricCalibrationApplied` and `backscatterConvention` become descriptive
rather than usable.

---

## GcpSet

**Buyer input with an open domain.** The same pattern as `SceneReference`: the
specification declares the **constraints** (accepted format, point count,
required accuracy), and the catalogue does not validate the contents of the
uploaded file.

This is the first case in the model where **buyer input changes a declared
product characteristic**: the vendor states one geolocation accuracy without
control points and a second, better one with them.

`gcpAccuracyRequired` is the key field. Without it, a claim of "under 1 m with
customer points" is unachievable — points measured with a phone would give a
result worse than no points at all. It is **the condition under which the seller
takes on the better number**.

---

## GeolocationAccuracy

**A composite value type instead of a rule.** A number without its metric, its
derivation basis and the terrain-correction state is incomparable. "2.5 m" and
"6 m" may well mean **the second is better**, if the first is a design budget
without terrain correction and the second a measured result after it.

**Two different `PREDICTED` values in one class** — the most common source of
confusion:

| Field | What it says |
|---|---|
| `basis = PREDICTED_BUDGET` | **where the number came from** — that it is a design budget rather than the result of a calibration campaign |
| `ephemerisClass = PREDICTED` | **for which input data it holds** — that it applies to predicted ephemeris |

The axes are independent: a **measured** value for a **predicted** orbit is the
most common case in performance reports.

**The field is `ephemerisClass`, not `orbitClass`.** The name `OrbitClass` is
already taken in this domain and means something else — in the `SAR-L1-L3` model
its values are `SSO`, `MIO`, `POLAR`, that is, **orbit type**. Two different
dimensions under one name guarantee a mistake for anyone reading both documents.

**The `UNDEFINED` value in `metric`.** Capella's 2020 guide publishes geolocation
accuracy **with no statistic at all**. Without this value such a product could be
catalogued only by **guessing** whether it is `RMSE` or `CE90` — that is,
by inserting a claim the vendor never made.

`UNDEFINED` in `metric` and `NOT_PUBLISHED` in `basis` are **not the same**. The
first: "we have a number, we do not know which statistic it represents." The
second: "the vendor publishes no number at all."

---

## GeometricQuality

PSLR and ISLR are given **separately for range and azimuth**, as in the ESA
source. They are independent, arising from separate weighting functions applied
in range and azimuth compression; Sentinel-1 declares different thresholds for
each direction.

A PSLR declaration holds **only for the weighting function declared in
`ProcessingProfile` in the same direction**. Changing the weighting function
changes PSLR and resolution **simultaneously and in opposite directions**, so
PSLR without a stated function is unverifiable.

`channelCoRegistrationAccuracy` is **a precondition of polarimetry**: with
channels offset by half a pixel, channel ratios are worthless. The field is
meaningful only when `polarizationMode ≠ SINGLE`.

---

## LicensingProfile

**Four disjoint legal axes.** A single `usageRestriction` field would conflate
three concepts at once; these do not fold into one another:

| Field | Which question it answers |
|---|---|
| `permittedPurpose` | for what **purpose** it may be used |
| `eligibleBuyerClass` | **who** may buy |
| `exportControl` | which **export-control regime** applies |
| `securityClassification` | which state **classification** |

The split was necessary because the query "available commercially to an EU buyer"
was unanswerable against a single field.

**Classification constrains the delivery channel.** `securityClassification`
narrows `deliveryChannel` — **the only place in the model where the legal layer
constrains the technical one**. A product technically capable of being served via
`OGC_SERVICE` may be barred from it by classification.

**The class is `LicensingProfile`, not `CommercialProfile`.** Nothing in it
concerns money — every field is legal. A name suggesting commerce invites a price
to be inserted here, which is exactly what the pricing layer is for.

---

## MemberConstraint

**The hole this closes is an unusual one.** Until it existed the model could
declare `interferometricUse = true` and leave the buyer with no way to act on
the declaration. That is not a fact the model failed to record — it is an
operation the model failed to permit. The product was described as *fit* for
interferometry and nothing anywhere said how to order the pair.

**Why it attaches to `RecurringAcquisition` instead of becoming a fourth
pattern.** A constraint on a *set* of acquisitions has three dimensions, and two
of them were already carried:

| Dimension | Where it is needed | Carrier |
|---|---|---|
| temporal — interval between consecutive members | recurring tasking, InSAR | `cadenceMin` / `cadenceMax` |
| quantitative — size of the set | recurring and continuous tasking | `repetitionCountMin` / `Max` |
| geometric — baselines, burst synchronisation | InSAR only | **`MemberConstraint`** |

An interferometric set with a twelve-day repeat cycle **is** a recurring order
with a cadence of twelve days, plus a condition on the perpendicular baseline.
That is not an analogy, it is the same construct with one more dimension. A
fourth subtype would have had to duplicate cadence and repetition count in order
to add three fields.

**Why `temporalBaseline` is not just the cadence again.** The two look alike and
mean different things. Cadence is what the buyer asked for; temporal baseline is
what the technique requires. A monitoring order has a cadence and no baseline
condition; a pair can carry a baseline condition tighter than any rhythm the
buyer has an opinion about. Collapsing them would make whichever is looser
unsayable.

**Every field is optional, and that is the design.** One class serves a
monitoring subscription that constrains nothing beyond its rhythm and a pair that
constrains nearly everything. A mandatory field would have forced every recurring
order to answer interferometric questions that do not apply to it.

**What this is not.** No vendor sells an interferometric pair as a configurable
product today. The conditions are published as **properties of a constellation**
— `repeatCycle`, `orbitTubeDiameter` — rather than as things a buyer sets. This
class states them from the order side, which is a position taken by this model
and not a reading of anyone's price list. `coherenceTarget` is the clearest case:
coherence depends on what is on the ground, so no seller can commit to it, and
the field says *aimed for* rather than *guaranteed*.

---

## ProcessingProfile

**`ardFamily` declares CEOS ARD conformance.** This is one of the few claims
about an EO product that is **checkable against a published document** — which is
why `ardSpecVersion` is mandatory: conformance to version 1.2 and to 1.3.1 are
two different claims.

**`pixelScale` is the only field in the model whose absence fails silently.** The
same backscatter value may arrive as linear power, amplitude (square root) or
decibels (logarithm). A recipient treating amplitude as power gets a result that
is **apparently correct and wrong by an order of magnitude**, undetectable
without outside knowledge. Every other omission shows up as missing data.

**The weighting function is one mechanism seen from three sides:** it lowers the
side lobes (improving PSLR), widens the main lobe (worsening resolution), and
changes the phase characteristic. Hence the split by direction, and hence the
obligation to state the coefficient rather than just the window name — Hamming
0.75 and Hamming 0.52 are different windows giving different PSLR.

**`speckleFilter` depends on what the product is meant to be.** Filtering
destroys phase relationships, so in a complex product it is ruled out; in a
temporal composite the phase is long gone, so filtering takes nothing away and
improves legibility.

---

## QualityProfile

`qualityValidIncidence` declares the **angular range in which all quality figures
hold** — a validity condition stated once, rather than a flag whose value a system
would have to compute at runtime.

Two further declarations apply to the whole block in the same way.
**`qualityBasis`** says where the figures come from: a design budget, a
measurement, or a commitment the seller will answer for. **`qualityAggregation`**
says how one number was arrived at where performance varies — across sub-swaths,
across incidence angles, or across the satellites of a constellation.

Both are single fields rather than qualifiers inside each of the twenty-six
quality values, and that is the point: the buyer's question is about the seller's
posture, and a posture is one statement. Both carry `NOT_STATED`, which is a real
value and not a placeholder — it records that a vendor published figures without
saying what kind they are, the same distinction `GeolocationMetric` draws with
`UNDEFINED`. Four of the five reference products can say nothing else, and that is
the finding: the industry mostly does not declare this.

What deliberately does **not** live here is a measured figure with its uncertainty
and sample count. "−20.56 dB ± 0.49 over 311 measurements" is a performance report,
and nobody filters products by sample count; the specification references the
vendor's report instead of growing a value type for it.

The class is the root of three subgroups — radiometric, geometric and
interpretability — because they have **different audiences and different validity
conditions**: the first depends on incidence angle, the second on ephemeris class
and control points, the third is a qualitative assessment. A flat table forced
the reader through twenty fields with no indication of which ones relate to which.

---

## RadiometricFigure

**A confidence qualifier as structure.** Two ESA sources give the same quantity:

| Source | Value |
|---|---|
| Product Definition (theoretical) | **1 dB at 3σ** |
| Annual Performance Report 2025 (measured) | **0.317 dB at 1σ** |

Converted: 0.317 × 3 ≈ **0.95 dB at 3σ**, i.e. essentially what the declaration
says. **The figures agree.** But anyone comparing "1 dB" against "0.317 dB"
without the multiplier concludes a threefold improvement, which is wrong.

Hence the multiplier as an **inseparable part of the value**, not a separate
field beside it.

---

## RadiometricQuality

**NESZ as a range.** NESZ varies across the swath, so a single number is the same
error that was corrected for resolution. `neszMin` is **numerically smaller**
(more negative), i.e. **better** — consistent with the `valueMin`/`valueMax`
convention in `Resolution`.

**Two ambiguity conventions, four distinct quantities.** Vendors split
ambiguities in two different ways:

| Convention | Split | Who uses it | Example values |
|---|---|---|---|
| **target-type** | distributed versus point | ESA | DTAR −22.3 dB · PTAR −32.2 dB |
| **directional** | azimuth versus range | ICEYE | separately per direction |

The 10 dB gap between DTAR and PTAR shows these are **not alternative names for
the same thing**. All four fields are therefore optional `[0..1]` — a vendor
fills in whichever they publish. A `convention` field alongside two fields would
force the buyer to know both conventions in order to read the number.

---

## Resolution

**A composite value type instead of a rule.** Structurally, a number cannot be
given without its plane and reference angle. The difference between `SLANT` and
`GROUND` is a factor of two at 30°.

**Cardinality `1..*`, one instance per axis** — because the measurement point
differs per axis. Sentinel-1 Product Definition, section 7:

> *"The resolutions in the tables correspond to an average over the minimum and
> maximum orbits, and to the following swath range positions: **near range**, for
> azimuth resolution; **mid range**, for ground range resolution."*

ICEYE gives both axes at scene centre at 30°. One instance per axis records both
cases; shared fields for both axes do not.

**Measurement point separated from aggregation (0.7).** The quotation above makes
**two independent statements at once**: *mid range* says **where in the scene**,
*averaged over the minimum and maximum orbits* says **what it was averaged over**.
A single-valued enum forced a choice between them and lost the other.

The separation also unlocked two previously inexpressible cases, both concerning
Sentinel-1: `PER_BEAM` (WV, where the WV1 and WV2 vignettes differ in resolution)
and `OVER_ANGLE_RANGE` (SLC, where resolution is given as a function of the
incidence-angle range). Both values mean the declaration **is not a single
number** but a set or a function — and without this field they look exactly like
ordinary uncertainty.

---

## RevisitProfile

**Revisit is not the repeat cycle.**

| Field | What it says |
|---|---|
| `revisitTime*` | how often a place can be **observed** from any geometry |
| `repeatCycle` | after how many days the satellite returns to **exactly the same track** |

A constellation can have six-hour revisit and **be unusable for InSAR** if it does
not control its orbital track. Conversely, a mission on a twelve-day cycle can be
excellent interferometrically while having poor revisit. InSAR requires
`repeatCycle` **and** `orbitTubeDiameter`, not revisit alone.

**Revisit needs a qualifier.** It depends on latitude (hence the mandatory
`revisitReferenceLatitude`) and on the permitted angular range — narrowing the
angles in an offering **lengthens actual revisit**. Without a reference latitude,
"daily revisit" is a marketing claim.

**`orbitTubeDiameter`, not `orbitTubeRadius`.** ESA specifies the orbital tube as
a **diameter** (Sentinel-1: 120 m RMS). The previous name systematically doubled
the interpretation of the same number.

---

## SarProductSpecification

### `productType` is a classifier, not a subtype selector

The distinction is easy to blur and worth keeping sharp:

| | What it does | Examples in the model |
|---|---|---|
| **subtype selector** | splits into **disjoint** attribute sets carried by subclasses | `accessMode`, `collectionStrategy`, `patternType` |
| **classifier** | creates no subclasses; makes some attributes inapplicable, but the sets **overlap heavily** | `productType` |

`SLC` and `GRD` share most of their attributes. The difference is that some apply
to one and not the other, and vice versa. That is an inapplicability rule, not a
structural split, so subtypes would be the wrong tool here: they would duplicate
eighty per cent of the fields.

### Three fields dependent on `productType`

`dataType` and `processingLevel` are **set by the vendor**, but
their values follow from `productType`. They are not computed by the system — the
vendor enters them deliberately, and the validation layer checks consistency. The
mapping is in the technical specification.

> **There is no `phasePreserved` field, deliberately.** It would be `true`
> exactly when `dataType ∈ {COMPLEX, PHASE_HISTORY}`, so it would **carry no
> information beyond `dataType`**. The temptation is real — phase preservation is
> the most consequential property of a SAR product and there is an urge to make it
> visible directly — but every field dependent on another is a place where the two
> can drift apart. The physical fact is documented in the `ProductType` table, and
> rule W-11 refers to `dataType`.

### Identity labels

**`resolutionClass` (F/H/M).** Without it the model **does not distinguish S1
`IW_GRDH` from `IW_GRDM`** — both are `productType = GRD` and differ only in
numbers. Yet the class letter is in the file name, in the collection identifier
and in the ESA catalogue, i.e. in the **identity of the product**. A buyer filters
on "GRDH", not on "20 m".

**`copernicusCMClass` (VHR1/VHR2).** The only **cross-vendor** resolution-class
vocabulary found — every other label of this kind is internal to a single
mission. **Source status: unverified.** ICEYE gives mode assignments; ESA does not
publish the thresholds. The field is a label quoted from the vendor, not a
checkable claim.

**`systematicProduction`.** Whether the product is generated for **every**
acquisition or only on demand. ESA generates GRD systematically, but SLC **only
for selected modes and areas**. Without this field, `archiveDepthFrom =
2014-10-03` suggests everything from that date is in the archive — and a buyer
planning an SLC time series over Africa would discover otherwise only when
querying the catalogue.

---

## SceneReference

**A bare scene identifier is ambiguous** across catalogues. Identity requires a
triple: **catalogue → collection → item**, i.e. the STAC pattern.

The domain is **open** — the product catalogue cannot validate it, because it
does not know the contents of someone else's archive. The validation layer does
so at order time. The same pattern as `GcpSet`.

This realises the principle that **the specification declares what the buyer
points at and within which limits, not what they pointed at**. The identifier of
a specific scene belongs to the order; the specification only says that such an
identifier is required and what shape it must take.

---

## ServiceCommitment

**An SLA as a composite value type.** A bare number of hours is ambiguous:
`{8 h, GUARANTEED, 99%}` and `{8 h, BEST_EFFORT}` are **two entirely different
products behind the same number** — and the difference is what the buyer pays for
in operational use.

| `commitmentLevel` | Meaning |
|---|---|
| `BEST_EFFORT` | no commitment; the figure describes a typical value |
| `TARGET` | the vendor measures and publishes attainment, with no remedy on failure |
| `GUARANTEED` | a contractual commitment with consequences on breach |

The same mechanism as in `Resolution` and `GeolocationAccuracy`: a number without
its qualifier is uninterpretable, so the qualifier becomes structurally
inseparable.

---

## TargetProfile

**This class declares limits, not the request.** The requested area is not a
characteristic and does not live here. It travels as `Product.place` with
`role = requestedAoi` - on the inquiry, the qualification item, the order item
and the inventory record. TM Forum gives a `Product` a first-class place slot
carrying a role, and a characteristic holding the same polygon would be a second
copy of one truth; the second copy is the one that goes stale. Decision D-70
settled where the area belongs, D-72 finished removing it from here.

`serviceArea` is the exception and it needs one: a `ProductSpecification` has
**no place slot at all**, so a seller's declaration about geography has nowhere
else to live. `timeOfInterest` is a characteristic for the mirror-image reason -
TM Forum has a place slot and no equivalent slot for an interval. The asymmetry
is not arbitrary; it follows the standard's own shape.

**The requested area has two different meanings** depending on the ordering mode,
which is why this class has to declare which modes the product accepts:

| Context | Meaning |
|---|---|
| `BY_SCENE` | a **cut-out** from the referenced scene — must lie inside it (W-15) |
| `BY_CONSTRAINT`, `BY_OPPORTUNITY` | a **target** — an area to cover or photograph |

With `clippingSupported = false` the buyer always receives the **full acquisition
footprint**, and a cut-out is inapplicable under `BY_SCENE` (W-16). In every mode
the area must lie inside the declared `serviceArea` (W-43).

**`timeOfInterest`.** Under tasking it is the acquisition window, under
`BY_CONSTRAINT` the archive search window, under `BY_SCENE` inapplicable (W-41),
because the scene reference already fixes the time. It is not the commitment
period: that is `requestedStartDate`/`requestedEndDate` on the order item, and a
twelve-month agreement may ask for imagery in three specific weeks inside it
(D-71).

**`serviceArea`.** Where the product may be ordered **at all**,
together with the reason the limit is drawn where it is. It stays in the
specification rather than moving onto the offering's `place`, for two
independent reasons. Technical: `ProductOffering.place` in PSID620 is a bare
`PlaceRef` - a reference with no geometry - and PSI has no Place API, so there
is nothing to point at. And substantive, which is the stronger one:
`restrictionBasis` is a **rule, not a place**.

The three kinds of limit behave differently: an orbital one is physics and will
not move, a commercial one is a decision the seller can revisit, a regulatory
one can change **without either party acting**. A buyer planning a three-year
campaign needs that difference, and the geometry alone will not tell them.

**Who sets which field:**

| Field | Who |
|---|---|
| `timeOfInterest` | the **buyer**, at order time |
| `serviceArea` | the **seller** — where ordering is possible at all |
| `targetGeometryType` | the **seller** — which geometries the product accepts |
| `targetAreaMin` / `Max` | the **seller** — bounds on the size of the requested area |
| `clippingSupported` | the **seller** — a product capability |

One configurable field and four declaring domain - and one more thing the buyer
sets which is not in this list at all, because it is not a characteristic.

**Four kinds of area** travel through the ordering chain:

| Role | Means | Lives in |
|---|---|---|
| `serviceArea` | where the product may be ordered at all | the specification, as a characteristic |
| `requestedAoi` | what the buyer asked for | inquiry, qualification, order |
| `acquisitionFootprint` | what the satellite actually imaged | the product inventory |
| `deliveredExtent` | what the buyer can actually download | the product inventory |

The last three travel as `RelatedPlaceRefOrValue.role`. Collapsing the first two
loses the refusal; collapsing the last two makes the **clip invisible**. Section
13 of the main document.

**In TMF620 the declaration and the domain are one object**, which is why the
limits sit together in this class: `targetGeometryType` and
`targetAreaMin`/`Max` describe what a request may look like, and there is no
separate "declare the bounds here, the characteristic there".

`targetAreaMin` is **the only place the model expresses an order minimum** —
TMF620 v5.0 has no `minimumQuantity` field, so the minimum is the lower bound of
the characteristic's domain (`valueFrom`).

---

## TaskingTier

**A tuple, not four loose numbers.** ICEYE sells three bundles and only three:

| `tierName` | `priority` | `minLeadTime` | `deliveryLatency` | `collectionSuccessRate` |
|---|---|---|---|---|
| Commercial 3h | `COMMERCIAL` | 12 h | 3 h | > 90% |
| Commercial 8h | `COMMERCIAL` | 12 h | 8 h | > 90% |
| Background 24h | `BACKGROUND` | 24 h | 24 h | not published |

**`minLeadTime` belongs in the tuple, not on `TaskingAccess`.** ICEYE states it
**per priority** (Table 3-2: Commercial 12 h, Background 24 h), so one number per
product would be false for one of the two tiers — the same arithmetic that
justifies the class as a whole.

It also makes a rule writable that otherwise is not. **W-42** — a tier's notice
may not exceed `maxSchedulingHorizon` — only exists because the two numbers now
sit at different levels and can be put side by side.

With flat attributes the model would declare priorities
`[COMMERCIAL, BACKGROUND]`, delivery `3–24 h` and fulfilment `1–30 d`, and thus
**permitted `BACKGROUND` with 3 h delivery**. A combination nobody sells.

Confirmed by three independent sources: ICEYE, Capella (1-Day / 3-Day / 7-Day
tiers) and OpenCosmos (thirteen named tiers, each with its own
`scheduling_priority` and `pass_limits`).

**`tierName` is the only place in the model where a proper name is allowed.** The
"no vendor proper names" rule guards against putting a trade name **in place of a
characteristic** — `acquisitionModeName` was removed precisely because the name
`Spot` says nothing about resolution. Here it is the other way round: every
characteristic of the tier sits in the tuple beside it, and the name is purely
**the label the buyer sees on the invoice and in the vendor's interface**.

**`passLimit` — one field, not two.** A daily acquisition cap assigned to the
service tier (OpenCosmos returns `pass_limits` in the contract-priorities
endpoint response). The `ContinuousAcquisition` variant looks like it needs its
own density limiter, but the documentation gives no evidence that a second,
independent limit exists. One quantity under two names is the error already found
once, at `pixelSpacing*`.

---

**Version:** 0.11 · **author:** DO
