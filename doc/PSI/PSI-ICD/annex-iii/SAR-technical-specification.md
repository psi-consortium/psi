=begin

# SAR Product Model — Technical Specification

**Version 0.11** · **author: DO**
**Class diagram:** `SAR-model-UML-EN_DO_v0.11.puml`

---

=end

## Conventions

**Attribute role** — who sets the value:

| Marker | Meaning |
|---|---|
| `D` | set by the **seller** when publishing the specification |
| `C` | supplied by the **buyer** at order time, within the declared domain |
| `C!` | supplied by the buyer, **mandatory** — the order is incomplete without it |
| `D*` | the seller sets the value **based on another attribute**; the dependency is stated in the dependency table |

Table: Attribute role markers. {#tbl:spec-role-markers}

**Cardinality** — an attribute with no stated cardinality is **mandatory**
(exactly one value). `[0..1]` and `[0..*]` mark optional attributes; `[1..*]`
marks a mandatory multi-valued one.

**The "Source" column** states where the attribute came from: which document,
which vendor's product, or which physical property. Confidence markers:

| Marker | Meaning |
|---|---|
| `FV` | attribute and domain confirmed in a document we read |
| `PV` | attribute confirmed, **domain estimated** from observed market values |
| `NV` | attribute inferred, with no confirmation in a normative document |

Table: Source confidence markers. {#tbl:spec-confidence-markers}

## Model structure

The model describes **one product specification** in the TM Forum sense: the
full namespace of characteristics of a SAR imaging service, from which a given
offering selects a subset and narrows the domains.

### Class inventory

| Class | Kind | Cardinality at root | What it describes |
|---|-|---|--|
| `SarProductSpecification` | main | — | root: classification and identity labels |
| `AccessProfile` | abstract | `1..2` | how the product is obtained |
| `ArchiveAccess` | subtype | — | access to the archive |
| `TaskingAccess` | subtype | — | ordering new acquisitions |
| `TaskingTier` | value type | `1..*` in `TaskingAccess` | one tasking service tier |
| `AcquisitionPattern` | abstract | `1..*` in `TaskingAccess` | acquisition rhythm |
| `SingleAcquisition` | subtype | — | one-off acquisition |
| `RecurringAcquisition` | subtype | — | recurring acquisitions |
| `ContinuousAcquisition` | subtype | — | standing access over a period |
| `MemberConstraint` | value type | `0..1` in `RecurringAcquisition` | conditions between the acquisitions of a set |
| `RevisitProfile` | group | `1` | how often the target can be revisited |
| `TargetProfile` | group | `1` | what the buyer points at, and within which limits |
| `SensorProfile` | group | `1` | instrument physics |
| `AcquisitionProfile` | group | `1` | acquisition geometry |
| `CoverageGeometry` | abstract | `1` in `AcquisitionProfile` | spatial extent |
| `SwathCoverage` | subtype | — | swath-based coverage |
| `SceneCoverage` | subtype | — | scene-based coverage |
| `Resolution` | value type | `1..*` | resolution, one instance per axis |
| `ProcessingProfile` | group | `1` | what was done to the signal |
| `StandardConformance` | value type | `0..*` in `ProcessingProfile` | conformance to a standard |
| `QualityProfile` | group | `0..1` | root of the quality block |
| `RadiometricQuality` | subgroup | `1` in `QualityProfile` | radiometric quality |
| `GeometricQuality` | subgroup | `1` in `QualityProfile` | geometric quality |
| `InterpretabilityQuality` | subgroup | `0..1` in `QualityProfile` | interpretability |
| `RadiometricFigure` | value type | `2` in `RadiometricQuality` | radiometric value with its sigma multiplier |
| `GeolocationAccuracy` | value type | `1..2` in `GeometricQuality` | geolocation accuracy with qualifiers |
| `GcpSet` | value type | `0..1` in `GeometricQuality` | buyer-supplied control points |
| `DeliveryProfile` | group | `1` | how the product reaches the buyer |
| `AssetLayer` | value type | `1..*` in `DeliveryProfile` | one raster layer |
| `CompositeProfile` | group | `0..1` | temporal composite parameters |
| `LicensingProfile` | group | `1` | legal terms |
| `SceneReference` | value type | `0..*` in `ArchiveAccess` | pointer to an archive scene |

Table: Class inventory. {#tbl:spec-class-inventory}

### Subtype selectors

Three attributes select a subclass and **thereby** settle which attributes apply:

| Attribute | Class | Values → subtype |
|---|---|---|
| `accessMode` | `AccessProfile` | `ARCHIVE` → `ArchiveAccess` · `TASKING` → `TaskingAccess` |
| `patternType` | `AcquisitionPattern` | `SINGLE` → `SingleAcquisition` · `RECURRING` → `RecurringAcquisition` · `CONTINUOUS` → `ContinuousAcquisition` |
| `collectionStrategy` | `CoverageGeometry` | `SPOTLIGHT`, `SLIDING_SPOTLIGHT` → `SceneCoverage` · `STRIPMAP`, `PARALLEL_STRIPMAP`, `SCANSAR`, `TOPSAR` → `SwathCoverage` |

Table: Subtype selectors. {#tbl:spec-subtype-selectors}

`productType` is **not** a subtype selector. It is a classifier: it makes some
attributes inapplicable, but the attribute sets for different values overlap
heavily, so it creates no subclasses.

## Root class: SarProductSpecification

The root of the model. Carries the product classification and the labels by
which a buyer finds it in a catalogue.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|---|-|---|
| `sensorType` | enum | `SAR` | `D` | Sensor family. One permitted value in this specification; the field exists as an extension point towards an optical model and for alignment with `eo:sensorType` |
| `productType` | enum | see [ProductType](#spec-producttype) | `D · C` | **Main classifier.** Encodes three things at once: whether phase is preserved, the geometry the pixels sit in, and whether terrain relief was accounted for |
| `resolutionClass` | enum `[0..1]` | `F` \| `H` \| `M` | `D` | ESA resolution class for `GRD` products, setting the degree of multi-looking applied. Distinguishes `IW_GRDH` from `IW_GRDM` |
| `copernicusCMClass` | enum `[0..1]` | `VHR1` \| `VHR2` | `D` | Copernicus Contributing Mission class |
| `systematicProduction` | Boolean | `true` \| `false` | `D` | Whether the product is generated for **every** acquisition or only on demand |
| `dataType` | enum | `COMPLEX` \| `AMPLITUDE` \| `PHASE_HISTORY` \| `GEOPHYSICAL` | `D*` | Class of data in the file |
| `processingLevel` | enum | `L0` \| `L1A` \| `L1B` \| `L1C` \| `L2` \| `L3` | `D*` | Processing level in NASA/ESA nomenclature |

Table: SarProductSpecification attributes. {#tbl:spec-sarproductspecification-attributes}

**Dependencies and provenance**

| Attribute | Depends on | Source |
|---|---|---|
| `sensorType` | — | `FV` OGC 10-157r4, element `eo:sensorType` |
| `productType` | — | `FV` STAC `product:type`; values collected from ESA, ICEYE, Capella, ASF |
| `resolutionClass` | applicable **only** when `productType = GRD` (W-37) | `FV` Sentinel-1 SAR Technical Guide, Level-1 Product Formatting |
| `copernicusCMClass` | — | `NV` ICEYE, row *ESA Copernicus Contributing Mission Class*. **ESA does not publish the class thresholds** — a label quoted from the vendor |
| `systematicProduction` | — | `Fc` SentiWiki: S1 `GRD` produced systematically, `SLC` only for selected modes and areas |
| `dataType` | follows from `productType` — [mapping](#spec-producttype-dependent-fields) | `FV` follows from product physics |
| `processingLevel` | follows from `productType` — [mapping](#spec-producttype-dependent-fields) | `NV` NASA EOSDIS Data Processing Levels; the SAR equivalents of `L1A`/`L1B`/`L1C` are **our own construction**, since ESA collapses them into a single `L1` |

Table: SarProductSpecification dependencies and provenance. {#tbl:spec-sarproductspecification-dependencies}

## Product access

### AccessProfile *(abstract)*

Cardinality `1..2` at the root: a product sold **both** from the archive **and**
through tasking has two instances, one of each subtype.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|--|-|---|
| `accessMode` | enum | `ARCHIVE` \| `TASKING` | `D · C` | Subtype selector |

Table: AccessProfile attributes. {#tbl:spec-accessprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `accessMode` | selects the subtype; every attribute of both subtypes depends on it structurally | `FV` the distinction is present in all vendors reviewed |

Table: AccessProfile dependencies and provenance. {#tbl:spec-accessprofile-dependencies}

### ArchiveAccess {#spec-archiveaccess}

Access to scenes that **already exist**.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|--|-|-|
| `orderingMode` | enum `[1..*]` | `BY_SCENE` \| `BY_CONSTRAINT` | `D` | How the buyer **names** what they want: by pointing at named scenes, or by stating an area and a period |
| `standingAccess` | Boolean | `true` \| `false` | `D` | Whether a standing claim on an area is possible, receiving new scenes as they arrive instead of ordering each time |
| `catalogueInterface` | enum `[1..*]` | `STAC_API` \| `OGC_CSW` \| `OGC_API_RECORDS` \| `VENDOR_API` \| `WEB_UI_ONLY` | `D` | How to search the archive **before** purchase; decides whether discovery can be automated |
| `archiveDepthFrom` | Date | date | `D` | How far back the vendor's archive reaches |
| `includesHistoricalArchive` | Boolean | `true` \| `false` | `D` | Whether **this product** grants access to that full depth, or only to scenes created after purchase |
| `archiveUpdateLatency` | `ServiceCommitment` | 1 h – 30 d | `D` | How long a new scene takes to enter the archive after acquisition |
| `deliveryLatency` | `ServiceCommitment` | 1 h – 14 d | `D · C` | From order placement to file delivery |
| `sourceSceneRef` | `SceneReference` `[0..*]` | open domain | `C!` | Identification of specific source scenes |

Table: ArchiveAccess attributes. {#tbl:spec-archiveaccess-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `orderingMode` | `BY_SCENE` makes `sourceSceneRef` mandatory (W-13) and `timeOfInterest` inapplicable (W-41); `BY_CONSTRAINT` is the reverse. The same axis appears in `TaskingAccess`, over a different domain | `FV` patterns present at ESA, ICEYE and Capella |
| `standingAccess` | independent of `orderingMode`: how a request is phrased and whether access is one-off or standing are two different axes (D-64). When `true`, rule W-38 excludes `exclusivity = PERMANENT` on the tasking side | `FV` subscription models of commercial vendors |
| `catalogueInterface` | distinct from `metadataStandard`: this concerns **search before** purchase, that concerns **metadata of the delivered file** | `FV` CEOS OpenSearch Best Practice; OpenCosmos (STAC API) |
| `archiveDepthFrom` | does **not** imply `includesHistoricalArchive = true` — see below | `FV` ESA: S1 archive from 2014-10-03 |
| `includesHistoricalArchive` | independent of `archiveDepthFrom` | `FV` subscription models of commercial vendors |
| `archiveUpdateLatency` | — | `FV` ESA publishes archive ingestion latency |
| `deliveryLatency` | on the tasking side the equivalent sits in `TaskingTier`, because it differs per tier | `FV` all vendors |
| `sourceSceneRef` | mandatory under `BY_SCENE`, inapplicable under `BY_CONSTRAINT` (W-13); when a requested area is given as well, rule W-15 applies | `FV` STAC identity pattern |

Table: ArchiveAccess dependencies and provenance. {#tbl:spec-archiveaccess-dependencies}

> [!IMPORTANT]
> **`archiveDepthFrom` versus `includesHistoricalArchive`.** These are not the
> same thing. The first states **how deep the vendor's archive goes**; the second
> states **whether this particular product grants access to that depth**. A
> subscription product may serve only scenes created after the subscription
> starts, even though the archive itself reaches back to 2014. Without the second
> field, a buyer seeing `2014-10-03` would reasonably assume they are buying
> access to all of it.

### TaskingAccess {#spec-taskingaccess}

Ordering **new** acquisitions.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|--|-|---|
| `orderingMode` | enum `[1..2]` | `BY_CONSTRAINT` \| `BY_OPPORTUNITY` | `D` | How the buyer **names** the acquisition: by stating constraints, or by picking a candidate pass |
| `maxSchedulingHorizon` | `Duration` | days to a year | `D` | How far ahead a **commitment** can be placed |
| `feasibilityHorizon` | `Duration` | up to three months | `D` | How far ahead candidate passes can be **searched for**. Must not be shorter than the above (W-44) |
| `exclusivity` | enum | `NONE` \| `DELAYED` \| `PERMANENT` | `D · C` | Whether acquired data enters the public archive |
| `holdbackPeriod` | `Duration` `[0..1]` | 0 – 24 months | `D · C` | Publication deferral period |

Table: TaskingAccess attributes. {#tbl:spec-taskingaccess-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `orderingMode` | `BY_OPPORTUNITY` means the buyer first receives candidate passes and orders one of them - carried in PSID by Check POQ (D-69), with the chosen pass referenced from `productOfferingQualificationItem` on the order item. **Narrower domain than on the archive side**: `BY_SCENE` would mean ordering a scene that does not exist yet | `FV` STAPI (Opportunity); Capella and OpenCosmos both expose pass search before ordering |
| `maxSchedulingHorizon` | must be ≥ the `minLeadTime` of **every** service tier (W-42) | `FV` Capella: rolling one-week window; OpenCosmos: 14 days |
| `exclusivity` | `DELAYED` makes `holdbackPeriod` mandatory (W-24); `PERMANENT` combined with `CONTINUOUS` is contradictory (W-38) | `FV` Capella documents optional exclusivity |
| `holdbackPeriod` | mandatory **only** under `exclusivity = DELAYED` | `FV` Capella: 30 days and 1 year |

Table: TaskingAccess dependencies and provenance. {#tbl:spec-taskingaccess-dependencies}

### TaskingTier *(value type)*

One tasking service tier. Cardinality `1..*` — a product typically offers several.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|-|-|--|
| `tierName` | String | open domain | `D` | The vendor's name for the tier, as the buyer sees it |
| `priority` | enum | see [TaskingPriority](#spec-taskingpriority) | `D · C` | Precedence when capacity is short |
| `minLeadTime` | `Duration` | 1 h – 7 d | `D` | How much notice an order **at this tier** requires |
| `fulfilmentTime` | `ServiceCommitment` | 1 – 30 d | `D` | Within what time from window opening the image will be **acquired** |
| `deliveryLatency` | `ServiceCommitment` | 1 h – 14 d | `D` | From acquisition to file delivery |
| `collectionSuccessRate` | Percent | 0 – 100 | `D` | Share of orders actually fulfilled at this tier |
| `passLimit` | Integer `[0..1]` | 1 – 100 | `D` | Daily acquisition cap assigned to this tier |

Table: TaskingTier attributes. {#tbl:spec-taskingtier-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `tierName` | — | `FV` ICEYE ("Commercial 3h"), Capella ("1-Day"), OpenCosmos (13 named tiers) |
| `priority` | — | `FV` ICEYE: `COMMERCIAL`/`BACKGROUND`; Capella: five levels |
| `minLeadTime` | must be ≤ `minAcquisitionWindowWidth` in `AcquisitionPattern` and ≤ `maxSchedulingHorizon` (W-42) | `FV` ICEYE Table 3-2: 12 h for `COMMERCIAL`, 24 h for `BACKGROUND` - that is, **per priority**, not per product. This is the evidence that moved the field here (D-67) |
| `fulfilmentTime` | distinct from `deliveryLatency`: one governs when the satellite images, the other when the file arrives | `FV` Capella: 1-Day / 3-Day / 7-Day tiers |
| `deliveryLatency` | as above | `FV` ICEYE: 3 h / 8 h for `COMMERCIAL`, 24 h for `BACKGROUND` |
| `collectionSuccessRate` | a fleet statistic, **not a guarantee** for an individual order | `PV` ICEYE reports > 90% for `COMMERCIAL`; the 0–100 domain is taken without narrowing |
| `passLimit` | the only density limiter in the model; `ContinuousAcquisition` has **none of its own** | `FV` OpenCosmos: `pass_limits` in the contract-priorities endpoint response |

Table: TaskingTier dependencies and provenance. {#tbl:spec-taskingtier-dependencies}

## Acquisition patterns

### AcquisitionPattern *(abstract)*

Cardinality `1..*` in `TaskingAccess`.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|--|-|--|
| `patternType` | enum | `SINGLE` \| `RECURRING` \| `CONTINUOUS` | `D · C` | Subtype selector |
| `minAcquisitionWindowWidth` | `Duration` | 6 h – 7 d | `D` | Minimum acquisition window width |

Table: AcquisitionPattern attributes. {#tbl:spec-acquisitionpattern-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `patternType` | selects the subtype | `FV` OpenCosmos: manual / automated / **recurrent** / **systematic** modes |
| `minAcquisitionWindowWidth` | must be ≥ `minLeadTime`; under `RECURRING` it applies to **each iteration separately** | `FV` Capella: `Window Open` / `Window Close` |

Table: AcquisitionPattern dependencies and provenance. {#tbl:spec-acquisitionpattern-dependencies}

> [!NOTE]
> A window that is too narrow gives the scheduler nowhere to find a pass — hence
> a minimum declared by the vendor.

### SingleAcquisition

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|---|---|---|
| `retaskOnFailure` | Boolean | `true` \| `false` | `D · C` | Whether a failed acquisition is retried |

Table: SingleAcquisition attributes. {#tbl:spec-singleacquisition-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `retaskOnFailure` | applicable **only** under `patternType = SINGLE`; under `RECURRING` the role is played by `iterationFailurePolicy` | `FV` Capella, ICEYE |

Table: SingleAcquisition dependencies and provenance. {#tbl:spec-singleacquisition-dependencies}

### RecurringAcquisition

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|--|-|-|
| `cadenceMin` | `Duration` | 1 d – 12 months | `D · C!` | Shortest permitted interval between iterations |
| `cadenceMax` | `Duration` | 1 d – 12 months | `D` | Longest permitted interval |
| `repetitionCountMin` | Integer | 2 – 365 | `D · C!` | Smallest number of repetitions |
| `repetitionCountMax` | Integer | 2 – 365 | `D` | Largest number of repetitions |
| `iterationFailurePolicy` | enum | `RETRY_IN_SLOT` \| `ROLL_TO_NEXT` \| `SKIP` | `D` | What happens when one iteration fails |
| `memberConstraint` | `MemberConstraint` `[0..1]` | — | `D · C` | Conditions between the acquisitions of the set ([MemberConstraint](#spec-memberconstraint)) |

Table: RecurringAcquisition attributes. {#tbl:spec-recurringacquisition-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `cadenceMin` | **must not be shorter than `revisitTimeMax`** from `RevisitProfile` at the target's latitude (W-33) | `PV` OpenCosmos: weekly … yearly frequencies. Domain estimated |
| `cadenceMax` | ≥ `cadenceMin` | `PV` as above |
| `repetitionCountMin` / `Max` | — | `PV` OpenCosmos: maximum child-request count. Domain estimated |
| `iterationFailurePolicy` | mandatory under `RECURRING` (W-34) | `NV` **constructed from first principles** — no vendor documents this, and the difference matters to the buyer |
| `memberConstraint` | optional; absent means the set is constrained only by rhythm and count | `FV` the interferometric case is documented; the construction is this model's |

Table: RecurringAcquisition dependencies and provenance. {#tbl:spec-recurringacquisition-dependencies}

**Meaning of `iterationFailurePolicy` values:**

| Value | What happens | What the buyer gets |
|---|---|---|
| `RETRY_IN_SLOT` | retried within the same slot | declared number of images, rhythm preserved |
| `ROLL_TO_NEXT` | the iteration moves to the next slot | declared number of images, **rhythm shifted** |
| `SKIP` | the iteration is lost | **fewer images**, rhythm preserved |

Table: Meaning of iterationFailurePolicy values. {#tbl:spec-iteration-failure-policy}

### ContinuousAcquisition

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|--|-|---|
| `coveragePeriodMin` | `Duration` | 7 d – 12 months | `D · C!` | Shortest period for which standing access can be bought |
| `coveragePeriodMax` | `Duration` | 7 d – 60 months | `D` | Longest such period |

Table: ContinuousAcquisition attributes. {#tbl:spec-continuousacquisition-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `coveragePeriodMin` / `Max` | acquisition density is limited by `passLimit` in `TaskingTier`, not by this class | `PV` OpenCosmos: systematic tasking with a mandatory window. Domain estimated |

Table: ContinuousAcquisition dependencies and provenance. {#tbl:spec-continuousacquisition-dependencies}

> [!NOTE]
> The seller declares the **permitted range** of the period; the buyer supplies
> a concrete value at order time — for example "standing access to this area for
> 6 months". The same shape as `holdbackPeriod`.

### MemberConstraint *(value type)* {#spec-memberconstraint}

Cardinality `0..1` in `RecurringAcquisition`.

Conditions that hold **between** the acquisitions of the set, rather than within
any one of them. This is the construction that turns `interferometricUse` from a
declaration of suitability into an ordering path: until it existed the model
could say a product was fit for interferometry and give the buyer no way to
order the pair.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|-|-|---|
| `sameTrack` | Boolean `[0..1]` | `true` \| `false` | `D · C` | All acquisitions from the same orbital track |
| `samePassDirection` | Boolean `[0..1]` | `true` \| `false` | `D · C` | All ascending, or all descending |
| `sameLookSide` | Boolean `[0..1]` | `true` \| `false` | `D · C` | All looking the same way |
| `temporalBaseline` | `DurationRange` `[0..1]` | 1 d – 1 y | `D · C` | Permitted time between consecutive acquisitions |
| `perpendicularBaselineMax` | Decimal · m `[0..1]` | 0 – 5000 | `D · C` | Largest permitted perpendicular baseline |
| `burstSyncTolerance` | Decimal · ms `[0..1]` | 0 – 100 | `D` | Largest permitted burst synchronisation error, TOPSAR modes |
| `coherenceTarget` | Decimal `[0..1]` | 0 – 1 | `D` | Coherence the seller aims for across the set |

Table: MemberConstraint attributes. {#tbl:spec-memberconstraint-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `sameTrack` | the condition `repeatCycle` and `orbitTubeDiameter` describe from the constellation side, stated here from the order side | `FV` same sources as those two attributes |
| `samePassDirection` / `sameLookSide` | — | `PV` standard interferometric practice; **no vendor documents them as orderable** |
| `temporalBaseline` | constrains more tightly than `cadenceMin` / `cadenceMax`, which express the buyer's rhythm rather than a physical condition | `FV` Sentinel-1 twelve-day repeat cycle |
| `perpendicularBaselineMax` | bounded in practice by `orbitTubeDiameter` | `PV` **domain estimated** from the critical-baseline limit, not from a price list |
| `burstSyncTolerance` | TOPSAR modes only; requires `dataType ∈ {COMPLEX, PHASE_HISTORY}` (W-45) | `PV` `SAR-L1-L3`. **Exact vendor figures not verified in this work** |
| `coherenceTarget` | a target, not a commitment, unless a `ServiceCommitment` says otherwise | `NV` **constructed from first principles** — coherence depends on what is on the ground, which no seller controls, so none publishes a figure |

Table: MemberConstraint dependencies and provenance. {#tbl:spec-memberconstraint-dependencies}

> [!NOTE]
> **Why no new pattern subtype.** An interferometric pair is a
> `RecurringAcquisition` with `repetitionCount` of two and a cadence equal to the
> repeat cycle, plus these conditions. The temporal dimension of a constraint on a
> set was already `cadenceMin` / `cadenceMax` and the quantitative one already
> `repetitionCountMin` / `Max`; only the geometric dimension was missing, and it
> attaches to the existing class.

## Revisit and target

### RevisitProfile

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|-|-|---|
| `revisitTimeMin` | `Duration` | 1 h – 30 d | `D` | Shortest interval between observations of the same place |
| `revisitTimeMax` | `Duration` | 1 h – 30 d | `D` | Longest interval |
| `revisitReferenceLatitude` | Decimal · ° | 0 – 90 | `D` | **The latitude at which the stated figures hold** |
| `repeatCycle` | `Duration` `[0..1]` | 1 – 60 d | `D` | After how many days the satellite returns to **exactly the same track** |
| `orbitTubeDiameter` | Decimal · m `[0..1]` | 10 – 1000 | `D` | Diameter of the tube within which the orbital track is maintained |

Table: RevisitProfile attributes. {#tbl:spec-revisitprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `revisitTimeMin` / `Max` | narrowing `lookAngle` or `incidenceAngle` in an offering **lengthens** actual revisit (W-10); constrains `cadenceMin` (W-33) | `FV` all vendors |
| `revisitReferenceLatitude` | without it, revisit figures are uninterpretable | `FV` polar-orbit physics: revisit grows towards the equator |
| `repeatCycle` | required when `interferometricUse = true` (W-11). **Distinct from revisit** | `FV` ESA: S1 has a 12-day cycle |
| `orbitTubeDiameter` | required when `interferometricUse = true` (W-11) | `FV` ESA: *"orbital tube of a **diameter** of 120 m (RMS)"* |

Table: RevisitProfile dependencies and provenance. {#tbl:spec-revisitprofile-dependencies}

> [!IMPORTANT]
> **Revisit is not the repeat cycle.** A constellation can have six-hour revisit
> and still be unusable for interferometry if it does not control its orbital
> track. Conversely, a mission on a twelve-day cycle can be excellent
> interferometrically while having poor revisit.

### TargetProfile

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|-|-|---|
| `serviceArea` | `ServiceArea` `[0..*]` | — | `D` | Where the product may be ordered **at all**, and why the limit is drawn where it is |
| `timeOfInterest` | `TimePeriod` `[0..1]` | — | `C!` | The stretch of time the request is about: acquisition window under tasking, archive search window under `BY_CONSTRAINT` |
| `targetGeometryType` | enum `[1..*]` | `POINT` \| `POLYGON` \| `CORRIDOR` | `D` | Which geometries the product accepts |
| `targetAreaMin` | Decimal · km² | 1 – 25,000 | `D` | Smallest orderable area |
| `targetAreaMax` | Decimal · km² | — | `D` | Largest area in a single order |
| `clippingSupported` | Boolean | `true` \| `false` | `D` | Whether the delivered extent can be narrowed to the requested area |
| `minCoverageFraction` | Decimal | 0 – 1 | `D · C` | The smallest fraction of the requested area for which the seller will offer a pass. The buyer narrows it on the order |

Table: TargetProfile attributes. {#tbl:spec-targetprofile-attributes}

> [!IMPORTANT]
> **The requested area is not in this table.** `TargetProfile` declares the
> *limits* on what may be asked for; the polygon itself travels as
> `Product.place` with `role = requestedAoi` on the inquiry, the qualification
> item, the order item and the inventory record (D-70, completed in D-72).
> Carrying it as a characteristic as well would be a second copy of one truth.
> `serviceArea` is the exception because a `ProductSpecification` has no place
> slot at all; `timeOfInterest` is a characteristic for the mirror-image reason,
> there being no first-class slot for an interval.

| Attribute | Depends on | Source |
|--|---|--|
| `serviceArea` | the requested area must lie inside some `INCLUDED` entry and outside every `EXCLUDED` one (W-43). It stays in the specification rather than on the offering's `place`, because `ProductOffering.place` in PSID620 is a bare `PlaceRef` with no geometry, and because `restrictionBasis` is a **rule, not a place** (D-68) | `FV` PSID620 OAS, checked in the schema |
| `timeOfInterest` | inapplicable when `BY_SCENE` is the only ordering mode (W-41) - the scene reference already fixes the time. **Not the same thing as `requestedStartDate`/`requestedEndDate`** on an order item: that is the commitment period, this is the imaging interval (D-71) | `FV` STAPI `datetime`; `archiveDepthFrom` did **not** play this role |
| `targetGeometryType` | sets the permitted form of the requested area | `FV` Capella: `POINT` and `POLYGON_AOI`; corridor mode at infrastructure-focused vendors |
| `targetAreaMin` | **the only place the model expresses an order minimum** — TMF620 v5.0 has no `minimumQuantity` field | `FV` TMF620 OAS v5.0.0, checked in the schema |
| `targetAreaMax` | — | `PV` domain estimated |
| `clippingSupported` | `false` → the buyer always receives the full acquisition footprint | `FV` ESA and commercial vendors offer clipping |

Table: TargetProfile dependencies and provenance. {#tbl:spec-targetprofile-dependencies}

**The two meanings of the requested area** (`place[role=requestedAoi]`), which
is why `TargetProfile` has to say which ordering modes it accepts:

| Context | Meaning | Validation |
|---|---|---|
| `orderingMode = BY_SCENE` | a **cut-out** from the referenced scene | must lie inside that scene's footprint (W-15); inapplicable when `clippingSupported = false` (W-16) |
| `BY_CONSTRAINT` and `BY_OPPORTUNITY` | a **target** — an area to cover or photograph | no containment requirement |

Table: Meaning of the requested area by ordering mode. {#tbl:spec-requested-area}

In every mode it must lie inside the declared `serviceArea` (W-43), which is a
limit the seller sets and the ordering mode does not affect.

> [!NOTE]
> **Four kinds of area.** `serviceArea` is the seller's declaration and lives in
> the specification; `requestedAoi`, `acquisitionFootprint` and `deliveredExtent`
> are facts about one transaction and travel as `RelatedPlaceRefOrValue.role` —
> the last two in the product inventory. See section 13 of the main document.

## Sensor and acquisition

### SensorProfile

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|--|-|---|
| `frequencyBand` | enum | `X` \| `C` \| `S` \| `L` \| `P` | `D` | Radar frequency band |
| `centerFrequency` | Decimal · GHz | 0.4 – 12.0 | `D` | Centre frequency |
| `bandwidth` | Decimal · GHz | 0.01 – 1.5 | `D` | Transmitted signal bandwidth |
| `polarization` | enum `[1..4]` | `HH` \| `VV` \| `HV` \| `VH` | `D · C` | Transmit and receive polarisation combinations |
| `polarizationMode` | enum | `SINGLE` \| `DUAL` \| `TWIN` \| `QUAD` | `D* · C` | Polarimetric mode |
| `observationDirection` | enum `[1..*]` | `LEFT` \| `RIGHT` | `D · C` | Which side of the track the radar looks |

Table: SensorProfile attributes. {#tbl:spec-sensorprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `frequencyBand` | sets penetration depth: `X` — surface, `L` — through tree canopy | `FV` physics: wavelength versus scatterer size |
| `centerFrequency` | must lie within `frequencyBand` | `FV` all vendors |
| `bandwidth` | **sets resolution in the `range` direction** — wider band, better resolution | `FV` physics: range resolution = c / (2 · B) |
| `polarization` | only the combinations enumerated in OGC `sar.xsd` are permitted (W-27); cardinality must agree with `polarizationMode` (W-28) | `FV` OGC 10-157r4, schema `omeo/1.0/sar.xsd` |
| `polarizationMode` | the seller sets it consistently with the cardinality of `polarization`: 1 → `SINGLE`, 2 → `DUAL` or `TWIN`, 4 → `QUAD` | `FV` OGC `sar.xsd`: values `S`, `D`, **`T`**, `Q` |
| `observationDirection` | — | `FV` OGC `antennaLookDirection`; STAC `sar:observation_direction` |

Table: SensorProfile dependencies and provenance. {#tbl:spec-sensorprofile-dependencies}

> [!NOTE]
> **`DUAL` versus `TWIN`.** Both mean two channels, but `DUAL` records them
> **simultaneously** while `TWIN` records them **alternately, pulse by pulse**.
> The difference matters for polarimetry and channel co-registration.

### AcquisitionProfile

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|--|-|--|
| `collectionStrategy` | enum | see [CollectionStrategy](#spec-collectionstrategy) | `D · C` | How the beam sweeps; subtype selector for `CoverageGeometry` |
| `incidenceAngle` | `AngleRange` | 5 – 60° | `D · C` | Angle of the wave against vertical **on the ground** |
| `incidenceAngleVariation` | Decimal · ° | 0 – 25 | `D` | Spread of the angle **within a single scene** |
| `lookAngle` | `AngleRange` | 5 – 55° | `D · C` | Angle to the target measured **at the satellite** |
| `orbitDirection` | enum `[1..*]` | `ASCENDING` \| `DESCENDING` | `D · C` | Pass direction |
| `squintAngleMax` | Decimal · ° `[0..1]` | 0 – 45 | `D` | How far the beam can be steered along track |
| `illuminationTime` | `Duration` | no declared bound | `D` | How long the radar keeps one point inside its beam |

Table: AcquisitionProfile attributes. {#tbl:spec-acquisitionprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `collectionStrategy` | selects the `CoverageGeometry` subtype | `FV` nomenclature shared across vendors |
| `incidenceAngle` | narrowing it in an offering lengthens revisit (W-10); `qualityValidIncidence` declares the sub-range in which quality figures hold | `FV` ESA, ICEYE |
| `incidenceAngleVariation` | distinct from the orderable range — this is variation **across a single image** | `FV` OGC `incidenceAngleVariation` |
| `lookAngle` | **not convertible** to `incidenceAngle` without a geometric model including orbital altitude | `FV` Capella uses look angle; ESA and ICEYE use incidence angle |
| `orbitDirection` | matters for multi-temporal stacks — scenes from opposite directions have different shadow geometry | `FV` STAC `sat:orbit_state` |
| `squintAngleMax` | — | `FV` spotlight modes require beam steering |
| `illuminationTime` | **the source of azimuth resolution** — the longer a point is illuminated, the longer the synthetic aperture. No ceiling is declared: observed values run from about 0.8 s for a Sentinel-1 IW burst, through 2–3 s in stripmap, to 10–25 s in spotlight and dwell modes. Sub-second values need `MILLISECOND` ([Duration](#spec-duration)) | `FV` SAR physics; figures `PV` observed, not vendor-declared |

Table: AcquisitionProfile dependencies and provenance. {#tbl:spec-acquisitionprofile-dependencies}

## Coverage geometry and resolution

### CoverageGeometry *(abstract)*

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|---|---|---|
| `footprintAreaNominal` | Decimal · km² | — | `D` | Nominal **acquisition** area |

Table: CoverageGeometry attributes. {#tbl:spec-coveragegeometry-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `footprintAreaNominal` | describes the acquisition, **not the delivered extent**; with `clippingSupported = true` the delivered extent follows from the requested area | `PV` derivable from coverage dimensions; domain estimated |

Table: CoverageGeometry dependencies and provenance. {#tbl:spec-coveragegeometry-dependencies}

### SwathCoverage

When `collectionStrategy ∈ {STRIPMAP, PARALLEL_STRIPMAP, SCANSAR, TOPSAR}`.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|---|---|---|
| `swathWidth` | Decimal · km | 5 – 500 | `D` | Swath width across track |
| `subSwathCount` | Integer | 1 – 6 | `D` | Number of sub-swaths making up the swath |
| `stripLengthMin` | Decimal · km | 10 – 600 | `D · C` | Shortest orderable strip |
| `stripLengthMax` | Decimal · km | 10 – 600 | `D` | Longest orderable strip |

Table: SwathCoverage attributes. {#tbl:spec-swathcoverage-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `swathWidth` | a wider swath means worse resolution at the same transmit power | `PV` ESA: IW 250 km, EW 400 km. **Domain 5–500 km estimated** from observed products |
| `subSwathCount` | > 1 means a multi-beam mode; raises `incidenceAngleVariation` | `FV` ESA: IW has 3 sub-swaths, EW has 5 |
| `stripLengthMin` / `Max` | — | `PV` **domain estimated**; vendors do not publish limits |

Table: SwathCoverage dependencies and provenance. {#tbl:spec-swathcoverage-dependencies}

### SceneCoverage

When `collectionStrategy ∈ {SPOTLIGHT, SLIDING_SPOTLIGHT}`.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|---|---|---|
| `sceneWidth` | Decimal · km | 2 – 30 | `D` | Scene width, set by the beam |
| `sceneLengthMin` | Decimal · km | 2 – 50 | `D · C` | Shortest orderable scene length |
| `sceneLengthMax` | Decimal · km | 2 – 50 | `D` | Longest orderable length |

Table: SceneCoverage attributes. {#tbl:spec-scenecoverage-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `sceneWidth` | — | `PV` Capella, ICEYE: scenes of 5–10 km. Domain estimated |
| `sceneLengthMin` / `Max` | under `SPOTLIGHT` an offering sets `valueFrom = valueTo`, thereby expressing a fixed length | `FV` Capella: `Scene Length` 5 km for Spot, 50 km for Site |

Table: SceneCoverage dependencies and provenance. {#tbl:spec-scenecoverage-dependencies}

### Resolution *(value type)*

Cardinality `1..*` — **one instance per axis**.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|--|-|---|
| `axis` | enum | `RANGE` \| `AZIMUTH` | `D` | Which axis the declaration concerns |
| `valueMin` | Decimal · m | 0.05 – 100 | `D` | Best attainable resolution on this axis |
| `valueMax` | Decimal · m | 0.05 – 100 | `D` | Worst |
| `plane` | enum | `SLANT` \| `GROUND` | `D` | In which plane it was measured |
| `referenceAngle` | Decimal · ° | 5 – 60 | `D` | At which incidence angle the figure was computed |
| `measurementPoint` | enum | `NEAR_RANGE` \| `MID_RANGE` \| `FAR_RANGE` \| `SCENE_CENTRE` | `D` | **Where in the scene** the figure holds |
| `measurementAggregation` | enum | `SINGLE` \| `AVERAGED_OVER_ORBITS` \| `PER_BEAM` \| `OVER_ANGLE_RANGE` | `D` | **What it was averaged over**, or how it is distributed |

Table: Resolution attributes. {#tbl:spec-resolution-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `axis` | range resolution follows from `bandwidth`, azimuth from `illuminationTime` | `FV` STAC: separate `sar:resolution_range` and `_azimuth` fields |
| `valueMin` / `Max` | — | `FV` all vendors publish ranges |
| `plane` | `SLANT` is always numerically better than `GROUND` by a factor of 1/sin(incidence angle) — exactly two at 30° | `FV` ICEYE: "Focusing Plane" |
| `referenceAngle` | without it a `GROUND` value is uninterpretable | `FV` ICEYE uses 30° |
| `measurementPoint` | ESA declares a **different point per axis** | `FV` S1 Product Definition §7 |
| `measurementAggregation` | `PER_BEAM` and `OVER_ANGLE_RANGE` mean the declaration **is not a single number** but a set or a function | `FV` S1 Product Definition: WV per beam, SLC over an angle range |

Table: Resolution dependencies and provenance. {#tbl:spec-resolution-dependencies}

> [!NOTE]
> **The ESA rule that requires both of the last two fields:** *"The resolutions
> in the tables correspond to an average over the minimum and maximum orbits, and
> to the following swath range positions: **near range**, for azimuth resolution;
> **mid range**, for ground range resolution."* That is two independent
> statements — where in the scene, and what it was averaged over.

## Processing

### ProcessingProfile

**Multi-looking**

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|-|-|---|
| `looksRange` | Integer | 1 – 30 | `D · C` | Looks averaged in the range direction |
| `looksAzimuth` | Integer | 1 – 30 | `D · C` | Looks averaged in azimuth |
| `looksAzimuthMax` | Integer `[0..1]` | 1 – 30 | `D` | Upper bound when the number of looks is chosen **adaptively** |
| `enl` | Decimal | 1 – 500 | `D` | Equivalent number of looks — the **actual** smoothing |
| `lookOverlapRange` | Decimal `[0..1]` | 0 – 0.9 | `D` | Look overlap in range |
| `lookOverlapAzimuth` | Decimal `[0..1]` | 0 – 0.9 | `D` | Look overlap in azimuth |
| `lookBandwidthRange` | Decimal · Hz `[0..1]` | — | `D` | Bandwidth of a single look in range |
| `lookBandwidthAzimuth` | Decimal · Hz `[0..1]` | — | `D` | Bandwidth of a single look in azimuth |

Table: ProcessingProfile attributes: multi-looking. {#tbl:spec-processingprofile-multilooking}

**Spectral weighting**

| Attribute | Type · cardinality | Domain | Role | Meaning |
|----|--|--|-|--|
| `weightingFunctionRange` | enum | `UNIFORM` \| `HAMMING` \| `HANN` \| `TAYLOR` \| `KAISER` \| `OTHER` | `D` | Spectral weighting function in range |
| `weightingFunctionAzimuth` | enum | as above | `D` | Separately for azimuth |
| `weightingCoefficientRange` | Decimal `[0..1]` | 0.3 – 1.0 | `D` | Function parameter in range |
| `weightingCoefficientAzimuth` | Decimal `[0..1]` | 0.3 – 1.0 | `D` | Function parameter in azimuth |

Table: ProcessingProfile attributes: spectral weighting. {#tbl:spec-processingprofile-weighting}

**Sampling and geometry**

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|---|-|--|
| `pixelSpacingRange` | Decimal · m | 0.1 – 100 | `D · C` | Pixel grid spacing in range |
| `pixelSpacingAzimuth` | Decimal · m | 0.1 – 100 | `D · C` | Grid spacing in azimuth |
| `geometryProjection` | enum | `SLANT_RANGE` \| `GROUND_RANGE_SENSOR` \| `MAP_PROJECTED` | `D · C` | Which geometry the pixels are laid out in |
| `terrainHandling` | enum | see [TerrainHandling](#spec-terrainhandling) | `D · C` | How terrain relief was handled |
| `elevationModel` | enum | `NONE` \| `SRTM_30` \| `SRTM_90` \| `COP_DEM_GLO_30` \| `COP_DEM_GLO_90` \| `ASTER_GDEM` \| `NATIONAL` \| `CUSTOMER_SUPPLIED` \| `OTHER` | `D · C` | Which elevation model was used |
| `mapProjectionFamily` | enum | `UTM_WGS84` \| `POLAR_STEREOGRAPHIC` \| `GEOGRAPHIC_WGS84` \| `NATIONAL_GRID` \| `OTHER` | `D · C` | Projection family |
| `epsgCode` | Integer | EPSG code | `D · C` | Precise coordinate reference system |

Table: ProcessingProfile attributes: sampling and geometry. {#tbl:spec-processingprofile-sampling}

**Pixel values**

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|--|-|-|
| `radiometricCalibrationApplied` | Boolean | `true` \| `false` | `D` | Whether calibration was **applied to the pixels** or supplied as look-up tables |
| `backscatterConvention` | enum `[1..*]` | `DN` \| `beta0` \| `sigma0` \| `gamma0` | `D · C` | What the values are referenced to |
| `pixelScale` | enum | `POWER` \| `AMPLITUDE` \| `DECIBEL` | `D · C` | In which representation the values arrive |
| `pixelDataType` | enum | `UINT8` \| `UINT16` \| `INT16` \| `FLOAT32` \| `CINT16` \| `CFLOAT32` | `D` | Numeric encoding of the pixel |
| `bitsPerSample` | Integer | 8 – 32 | `D` | Bits per sample |
| `speckleFilter` | enum | `NONE` \| `LEE` \| `REFINED_LEE` \| `GAMMA_MAP` \| `OTHER` | `D · C` | Whether a smoothing filter was applied |

Table: ProcessingProfile attributes: pixel values. {#tbl:spec-processingprofile-pixel-values}

**Fitness and conformance**

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|--|-|-|
| `interferometricUse` | Boolean | `true` \| `false` | `D` | Whether the product is fit for interferometry |
| `ardFamily` | enum | `NRB` \| `POL` \| `ORB` \| `GSLC` \| `INSAR` \| `CB` \| `NONE` | `D` | Conformance to a CEOS ARD family |
| `ardConformanceLevel` | enum | `THRESHOLD` \| `GOAL` \| `NONE` | `D` | Conformance level |
| `ardSpecVersion` | String | e.g. `1.3.1` | `D` | CEOS ARD specification version |
| `standardConformance` | `StandardConformance` `[0..*]` | see [StandardConformance](#spec-standardconformance) | `D` | Conformance to other standards |

Table: ProcessingProfile attributes: fitness and conformance. {#tbl:spec-processingprofile-conformance}

**Dependencies and provenance**

| Attribute | Depends on | Source |
|---|--|---|
| `looksRange` / `looksAzimuth` | under `dataType = COMPLEX` both must be 1 (W-03); under `AMPLITUDE` ≥ 1 (W-04) | `FV` STAC `sar:looks_range` / `_azimuth` |
| `looksAzimuthMax` | — | `FV` Capella: *"number of looks is adaptively selected"* |
| `enl` | always **smaller** than `looksRange × looksAzimuth`, because adjacent looks are correlated | `FV` STAC `sar:looks_equivalent_number`; ESA |
| `lookOverlap*` | affects `enl` | `FV` ESA: 0.250 × 0.250 for GRD HR |
| `lookBandwidth*` | — | `FV` ESA publishes per beam |
| `weightingFunction*` | **conditions the validity of `peakSideLobeRatio*` and `integratedSideLobeRatio*`** (W-32) | `FV` ICEYE: `window_function_range` / `_azimuth`; ESA: Hamming |
| `weightingCoefficient*` | Hamming 0.75 and Hamming 0.52 are **different windows** with different PSLR | `FV` ESA: `Range/Azimuth Hamming Weighting Coefficient`, varying 0.52–0.75 |
| `pixelSpacing*` | **not the same as resolution** — resolution says what can be told apart, spacing says how densely it was sampled | `FV` ASF HyP3 lets the buyer pick 10/20/30 m; STAC `sar:pixel_spacing_*` |
| `geometryProjection` | `MAP_PROJECTED` makes `mapProjectionFamily` and `epsgCode` mandatory (W-02) | `FV` ICEYE, Capella |
| `terrainHandling` | `DEM_*` makes `elevationModel` mandatory (W-01); **the only field distinguishing `GEC` from `GTC`** | `FV` ESA, Capella, ASF |
| `elevationModel` | **accuracy in mountains depends on this, not on the declared `geolocationAccuracy`** | `FV` ASF RTC: Copernicus GLO-30 |
| `mapProjectionFamily` / `epsgCode` | mandatory under `MAP_PROJECTED` | `FV` STAC `proj:code` |
| `radiometricCalibrationApplied` | `false` requires the `calibration-lut` role in `assetLayer` (W-07) | `FV` ESA GRD ships look-up tables; ICEYE calibrates 7 of 9 modes |
| `backscatterConvention` | `DN` means no physical calibration | `FV` STAC expresses this through the asset role |
| `pixelScale` | **mandatory under `dataType = COMPLEX`** (W-31). Orthogonal to `backscatterConvention` — six combinations from the same data | `FV` ASF HyP3 (`scale`), ICEYE |
| `pixelDataType` | `CINT16` and `CFLOAT32` only under `dataType = COMPLEX` | `FV` STAC `data_type` |
| `bitsPerSample` | — | `FV` STAC `raster:bits_per_sample`; ICEYE: 16 |
| `speckleFilter` | mutually exclusive with phase preservation — filtering destroys phase relationships | `FV` ICEYE, ASF |
| `interferometricUse` | requires `dataType ∈ {COMPLEX, PHASE_HISTORY}` **and** `repeatCycle` **and** `orbitTubeDiameter` (W-11) | `FV` InSAR physics |
| `ardFamily` | `≠ NONE` makes `ardConformanceLevel` and `ardSpecVersion` mandatory (W-25) and forces the presence of layers (W-36) | `FV` CEOS-ARD SAR PFS v1.3.1 |
| `ardConformanceLevel` | determines which `assetLayer` roles must carry `presence = ALWAYS` | `FV` CEOS-ARD |
| `ardSpecVersion` | mandatory when conformance is claimed — versions 1.2 and 1.3.1 are different claims | `FV` CEOS-ARD |
| `standardConformance` | every element requires `version` (W-30) | `FV` Capella declares SICD 1.2.1 |

Table: ProcessingProfile dependencies and provenance. {#tbl:spec-processingprofile-dependencies}

> [!IMPORTANT]
> **`pixelScale` is the only field in the model whose absence fails silently.**
> The same backscatter value may arrive as linear power, amplitude (square root)
> or decibels (logarithm). A recipient treating amplitude as power gets a result
> that is **apparently correct and wrong by an order of magnitude**. Every other
> omission shows up as missing data.

### StandardConformance *(value type)* {#spec-standardconformance}

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|---|---|---|
| `standard` | enum | `SICD` \| `SIDD` \| `CPHD` \| `STAC` \| `OGC_EO` \| `CEOS_ARD` | `D` | Which standard the product conforms to |
| `version` | String | e.g. `1.2.1` | `D` | Version of that standard |

Table: StandardConformance attributes. {#tbl:spec-standardconformance-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `standard` | `SICD`, `SIDD` and `CPHD` are **NGA** standards, not OGC | `FV` NGA; Capella declares SICD |
| `version` | **mandatory** (W-30) — `SICD` without a version does not say whether 1.1, 1.2 or 1.3 is meant | `FV` Capella: `NGA.STND.0024-1_1.2.1` |

Table: StandardConformance dependencies and provenance. {#tbl:spec-standardconformance-dependencies}

## Quality

### QualityProfile

Root of the quality block, cardinality `0..1`.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|--|-|---|
| `qualityBasis` | enum | `THEORETICAL` \| `MEASURED` \| `GUARANTEED` \| `NOT_STATED` | `D` | **Where every figure in this block comes from.** One declaration for the whole block |
| `qualityAggregation` | enum | `WORST_CASE` \| `TYPICAL` \| `RANGE` \| `MINIMUM_ACROSS_FLEET` \| `NOT_STATED` | `D` | **How a single figure was arrived at** where performance varies across sub-swaths, angles or satellites |
| `qualityValidIncidence` | `AngleRange` | 5 – 60° | `D` | **The angular range in which all quality figures hold** |

Table: QualityProfile attributes. {#tbl:spec-qualityprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `qualityBasis` | applies to every figure in `radiometricQuality`, `geometricQuality` and `interpretabilityQuality`. `NOT_STATED` records that the vendor published figures without saying what kind they are - the same distinction `GeolocationMetric.UNDEFINED` draws, and four of the five reference products can say nothing else | `FV` ESA states its published values are theoretical; most vendors state nothing |
| `qualityAggregation` | `MINIMUM_ACROSS_FLEET` exists because a constellation's figures may hold for its best satellite or for all of them | `PV` no vendor declares this; the field makes the silence visible |
| `qualityValidIncidence` | a subset of `incidenceAngle`; choosing an angle outside it raises a warning (W-05) | `FV` ICEYE: *Performant Incidence Range* versus *Time Dominant*; Capella: standard / extended / custom |

Table: QualityProfile dependencies and provenance. {#tbl:spec-qualityprofile-dependencies}

### RadiometricQuality

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|-|-|-|
| `neszMin` | Decimal · dB | −35 – −5 | `D` | Best (most negative) noise floor across the swath |
| `neszMax` | Decimal · dB | −35 – −5 | `D` | Worst |
| `radiometricAccuracyAbs` | `RadiometricFigure` | 0.1 – 3.0 dB | `D` | Deviation from truth, **excluding** speckle |
| `radiometricAccuracyRel` | `RadiometricFigure` | 0.05 – 1.0 dB | `D` | Repeatability between passes |
| `radiometricResolution` | Decimal · dB `[0..1]` | 0.5 – 5.0 | `D` | Uncertainty arising **from** speckle |
| `azimuthAmbiguityRatio` | Decimal · dB `[0..1]` | −40 – −15 | `D` | Azimuth ambiguities |
| `rangeAmbiguityRatio` | Decimal · dB `[0..1]` | −40 – −15 | `D` | Range ambiguities |
| `distributedTargetAmbiguityRatio` | Decimal · dB `[0..1]` | −40 – −15 | `D` | DTAR — distributed target |
| `pointTargetAmbiguityRatio` | Decimal · dB `[0..1]` | −40 – −15 | `D` | PTAR — point target |

Table: RadiometricQuality attributes. {#tbl:spec-radiometricquality-attributes}

| Attribute | Depends on | Source |
|---|--|-|
| `neszMin` / `Max` | determines which surfaces drown in noise | `FV` ESA, ICEYE |
| `radiometricAccuracyAbs` | requires `sigmaMultiplier` (W-29) | `FV` ESA: 1 dB @ 3σ |
| `radiometricAccuracyRel` | **an order of magnitude better** than absolute — a buyer building a time series looks only at this one | `FV` ESA: 0.1 dB |
| `radiometricResolution` | distinct from accuracy, which **excludes** speckle | `FV` ESA Product Definition |
| `azimuthAmbiguityRatio` / `rangeAmbiguityRatio` | **directional** convention | `FV` ICEYE |
| `distributedTargetAmbiguityRatio` / `pointTargetAmbiguityRatio` | **target-type** convention — not alternative names for the previous pair | `FV` ESA: DTAR −22.3 dB, PTAR −32.2 dB |

Table: RadiometricQuality dependencies and provenance. {#tbl:spec-radiometricquality-dependencies}

> [!NOTE]
> **Four ambiguity fields, not two plus a convention field.** The 10 dB gap
> between DTAR and PTAR shows these are different quantities. A vendor fills in
> whichever they publish; a `convention` field would force the buyer to know both
> conventions in order to read the number.

### GeometricQuality

| Attribute | Type · cardinality | Domain | Role | Meaning |
|----|--|-|-|-|
| `geolocationAccuracy` | `GeolocationAccuracy` | 0.01 – 30 m | `D` | Pixel position accuracy without control points |
| `channelCoRegistrationAccuracy` | Decimal · pixel `[0..1]` | 0.01 – 1.0 | `D` | Offset between polarisation channels |
| `peakSideLobeRatioRange` | Decimal · dB `[0..1]` | −35 – −10 | `D` | PSLR in range |
| `peakSideLobeRatioAzimuth` | Decimal · dB `[0..1]` | −35 – −10 | `D` | PSLR in azimuth |
| `integratedSideLobeRatioRange` | Decimal · dB `[0..1]` | −30 – −5 | `D` | ISLR in range |
| `integratedSideLobeRatioAzimuth` | Decimal · dB `[0..1]` | −30 – −5 | `D` | ISLR in azimuth |
| `gcpSupported` | Boolean | `true` \| `false` | `D` | Whether the product accepts buyer-supplied control points |
| `gcpFormatAccepted` | enum `[0..*]` | `CSV` \| `SHAPEFILE` \| `GEOJSON` \| `VENDOR_TEMPLATE` | `D` | Accepted control-point file formats |
| `gcpMinCount` | Integer `[0..1]` | 3 – 100 | `D` | Minimum number of points |
| `gcpMaxCount` | Integer `[0..1]` | 3 – 100 | `D` | Maximum number of points |
| `gcpAccuracyRequired` | Decimal · m `[0..1]` | 0.01 – 5.0 | `D` | Required accuracy of the buyer's points |
| `geolocationAccuracyWithGcp` | `GeolocationAccuracy` `[0..1]` | 0.01 – 30 m | `D` | Accuracy **after** applying the points |
| `gcpSet` | `GcpSet` `[0..1]` | open domain | `C` | The submitted control points |

Table: GeometricQuality attributes. {#tbl:spec-geometricquality-attributes}

| Attribute | Depends on | Source |
|---|---|-|
| `geolocationAccuracy` | — | `FV` ESA: ALE 7 m (NRT); ICEYE reports RMSE |
| `channelCoRegistrationAccuracy` | meaningful **only** when `polarizationMode ≠ SINGLE` | `FV` a precondition of polarimetry |
| `peakSideLobeRatio*` | holds **only** for the `weightingFunction*` declared in the same direction (W-32) | `FV` ESA: Range PSLR < −21.2 dB for IW; ICEYE: −20 dB with Taylor |
| `integratedSideLobeRatio*` | as above | `FV` ESA |
| `gcpSupported` | `true` unlocks the remaining GCP fields | `FV` commercial vendors |
| `gcpFormatAccepted`, `gcpMinCount`, `gcpMaxCount`, `gcpAccuracyRequired` | all condition the validity of `geolocationAccuracyWithGcp` (W-22) | `FV` commercial vendors |
| `geolocationAccuracyWithGcp` | holds **only** when the submitted points meet those conditions | `FV` commercial vendors |
| `gcpSet` | the only buyer input in this class | `FV` open-domain input pattern |

Table: GeometricQuality dependencies and provenance. {#tbl:spec-geometricquality-dependencies}

### InterpretabilityQuality

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|-|-|---|
| `informationDensity` | Decimal · bit/m² `[0..1]` | 1 – 2000 | `D` | How much information a unit of area carries |
| `rniirs` | Decimal `[0..1]` | 1 – 9 | `D` | How much an analyst can extract from the image |

Table: InterpretabilityQuality attributes. {#tbl:spec-interpretabilityquality-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `informationDensity` | ICEYE reports **the same quantity** under the name RGIQE | `FV` ICEYE performance tables |
| `rniirs` | Radar National Imagery Interpretability Rating Scale; higher is better | `FV` ICEYE: 5.5 for Spot, 7.2 for Dwell Precise |

Table: InterpretabilityQuality dependencies and provenance. {#tbl:spec-interpretabilityquality-dependencies}

## Delivery

### DeliveryProfile

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|--|--|-|-|
| `fileFormat` | **`FileFormatSpec`** `[1..*]` | see [FileFormatSpec](#spec-fileformatspec) | `D · C` | The format pairings the product is delivered in - container plus the metadata that comes with it |
| `metadataStandardExtensions` | String `[0..*]` | e.g. `sar`, `sat`, `proj` | `D` | Which standard extensions are populated |
| `assetLayer` | `AssetLayer` `[1..*]` | see [AssetLayer](#spec-assetlayer) | `D · C` | Raster layers in the package |
| `volumeMinGB` | Decimal · GB | 0.05 – 60 | `D` | Smallest typical delivery volume |
| `volumeMaxGB` | Decimal · GB | 0.05 – 60 | `D` | Largest |
| `maxBandFileSize` | Decimal · GB | 0.001 – 60 | `D` | **Hard ceiling on a single band file.** A different quantity from the two above, which bound the whole package |
| `deliveryChannel` | enum `[1..*]` | `S3` \| `HTTPS` \| `SFTP` \| `API` \| `OGC_SERVICE` \| `PHYSICAL_MEDIA` | `D · C` | How the buyer receives the data |
| `ogcService` | enum `[0..*]` | `WMS` \| `WMTS` \| `WCS` \| `WFS` | `D` | Which OGC services, when the channel is `OGC_SERVICE` |
| `multiSceneDelivery` | enum `[1..*]` | `SEPARATE` \| `MOSAIC` | `D · C` | Whether *n* scenes arrive separately or as one mosaic |

Table: DeliveryProfile attributes. {#tbl:spec-deliveryprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `fileFormat` | `NITF` is an NGA standard, not OGC. A **pairing** rather than two parallel lists, because a vendor running a current and a legacy set has two pairings, and two lists imply combinations that do not exist | `FV` all vendors; ICEYE runs COG with STAC alongside GeoTIFF with proprietary XML |
| `maxBandFileSize` | decides whether the recipient must handle BigTIFF | `FV` Sentinel-1 guarantees no GeoTIFF band in a product slice exceeds 2 GB |
| `metadataStandardExtensions` | — | `FV` STAC: `sar` 1.3.2, `sat`, `proj`, `view` |
| `assetLayer` | an order must contain every `ALWAYS` role (W-39); adding `ON_REQUEST` roles invalidates `volume*` (W-40) | `FV` STAC `item_assets` at collection level |
| `volumeMinGB` / `MaxGB` | **the range applies to the full acquisition**, not to a cut-out | `FV` ICEYE, ESA |
| `deliveryChannel` | narrowed by `securityClassification` (W-19); `OGC_SERVICE` alone without `WCS` makes pixel values inaccessible (W-23) | `FV` all vendors |
| `ogcService` | **WMS and WMTS return a picture, WCS returns values** | `FV` OGC service standards |
| `multiSceneDelivery` | `MOSAIC` requires scenes from a permitted time window (W-17) | `FV` commercial vendors |

Table: DeliveryProfile dependencies and provenance. {#tbl:spec-deliveryprofile-dependencies}

### AssetLayer *(value type)* {#spec-assetlayer}

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|---|---|---|---|
| `role` | enum | see [AssetRole](#spec-assetrole) | `D` | What the layer contains |
| `presence` | enum | `ALWAYS` \| `ON_REQUEST` | `D` | Whether the layer is always in the package or the buyer selects it |

Table: AssetLayer attributes. {#tbl:spec-assetlayer-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `role` | roles required by CEOS ARD must carry `presence = ALWAYS` (W-36) — see [mapping](#spec-ceos-ard-roles) | `FV` STAC SAR Extension, *Best Practices, Asset Roles* |
| `presence` | `ON_REQUEST` means the buyer selects the layer at order time | `PV` ASF HyP3 lets the buyer choose the DEM, incidence-angle map, scattering-area map and layover/shadow mask. **Unverified for ICEYE and Capella** |

Table: AssetLayer dependencies and provenance. {#tbl:spec-assetlayer-dependencies}

## Composite and licensing

### CompositeProfile

Present only when `processingLevel = L3`.

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|---|-|-|
| `temporalAggregation` | enum | `DAILY` \| `WEEKLY` \| `TEN_DAY` \| `MONTHLY` \| `SEASONAL` \| `ANNUAL` \| `CUSTOM` | `D · C` | Temporal aggregation period |
| `aggregationPeriod` | `Duration` | 1 d – 5 years | `D · C` | Exact period under `CUSTOM` |
| `gridReferenceSystem` | enum | `MGRS` \| `UTM` \| `GEOGRAPHIC` \| `EASE_GRID_2` \| `OTHER` | `D · C` | Grid the variables are mapped onto |
| `gridSpacing` | Decimal · m | 10 – 25,000 | `D · C` | Grid cell size |
| `normalizedIncidenceAngle` | Decimal · ° | 5 – 60 | `D` | Reference angle all contributing acquisitions were normalised to |
| `geophysicalVariable` | enum | `BACKSCATTER` \| `COHERENCE` \| `SOIL_MOISTURE` \| `WIND_SPEED` \| `WAVE_HEIGHT` \| `OTHER` | `D` | Which variable the composite carries |
| `gapFillingMethod` | enum | `NONE` \| `TEMPORAL_INTERPOLATION` \| `SPATIAL_INTERPOLATION` \| `NEAREST_VALID` \| `OTHER` | `D` | How cells without observations were filled |
| `minSourceAcquisitions` | Integer | 1 – 365 | `D` | Declared minimum of source acquisitions per cell |

Table: CompositeProfile attributes. {#tbl:spec-compositeprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `temporalAggregation` | the presence of the whole class depends on `processingLevel = L3` (S-09) | `FV` NASA EOSDIS: L3A as periodic summaries |
| `aggregationPeriod` | mandatory under `CUSTOM` | `FV` derived |
| `gridReferenceSystem` / `gridSpacing` | NASA's definition of L3 rests on a uniform grid | `FV` NASA EOSDIS |
| `normalizedIncidenceAngle` | **necessary**, because backscatter depends strongly on angle — without normalisation the mosaic shows striping along orbit tracks | `FV` scattering physics |
| `geophysicalVariable` | — | `PV` collected from market products |
| `gapFillingMethod` | — | `NV` **no source** — a construct of this model |
| `minSourceAcquisitions` | affects composite quality | `FV` declared by composite vendors |

Table: CompositeProfile dependencies and provenance. {#tbl:spec-compositeprofile-dependencies}

### LicensingProfile

| Attribute | Type · cardinality | Domain | Role | Meaning |
|---|-|--|-|---|
| `licenceModel` | enum | `OPEN_DATA` \| `SINGLE_USER` \| `MULTI_USER` \| `ENTERPRISE` \| `RESELL_PERMITTED` | `D` | Which rights the buyer receives |
| `eulaTemplate` | enum | `STANDARD` \| `GOVERNMENT` \| `MULTI` \| `CUSTOM` | `D` | Which document the buyer signs |
| `permittedPurpose` | enum `[1..*]` | `COMMERCIAL` \| `NON_COMMERCIAL` \| `RESEARCH` \| `HUMANITARIAN` | `D` | For what purpose the data may be used |
| `eligibleBuyerClass` | enum `[1..*]` | `ANY` \| `GOVERNMENT_ONLY` \| `ACCREDITED_ONLY` | `D` | Who may buy |
| `exportControl` | enum `[1..*]` | `NONE` \| `ITAR` \| `EAR` \| `EU_DUAL_USE` | `D` | Which export-control regime applies |
| `securityClassification` | enum | `UNCLASSIFIED` \| `CONTROLLED` \| `CLASSIFIED` | `D` | Classification assigned by a state authority |

Table: LicensingProfile attributes. {#tbl:spec-licensingprofile-attributes}

| Attribute | Depends on | Source |
|---|---|---|
| `licenceModel` | — | `FV` STAC `license` (SPDX in 1.1); vendor practice |
| `eulaTemplate` | **the name of the document, not its content** — distinct from `licenceModel`, which describes the rights | `FV` vendor practice |
| `permittedPurpose` | — | `FV` ESA and commercial vendor licences |
| `eligibleBuyerClass` | `CLASSIFIED` excludes `ANY` (W-20) | `FV` vendor licences |
| `exportControl` | — | `FV` ITAR, EAR, EU dual-use regulation |
| `securityClassification` | **narrows `deliveryChannel`** (W-19) — the only place where the legal layer constrains the technical one | `FV` OGC `eo:securityConstraints` |

Table: LicensingProfile dependencies and provenance. {#tbl:spec-licensingprofile-dependencies}

## Simple value types

### TimePeriod {#spec-timeperiod}
| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `startDateTime` | DateTime `[0..1]` | — | Beginning of the interval. Absent means unbounded in the past |
| `endDateTime` | DateTime `[0..1]` | — | End of the interval. Absent means unbounded in the future |

Table: TimePeriod attributes. {#tbl:spec-timeperiod-attributes}

Used by `timeOfInterest`.

**Source:** `FV` TMF `TimePeriod`, read in PSID620 and PSID622 - the field
description says outright *"a deadline (endDateTime only), a startDateTime only,
or both"*, so **an open interval is supported natively**.

> [!NOTE]
> **Why a pair rather than an ISO 8601 interval string.** STAC, STAPI and OGC
> API Features require `2026-10-01T00:00:00Z/..`. Letting that into the inside
> of PSI would give one quantity **two encodings within one standard** - which
> is where two copies of one truth drift apart. The conversion lives at the
> STAC/STAPI adapter only, which is where another party's format is adopted
> anyway. Decision **D-66**.

### ServiceArea

| Attribute | Type | Domain | Meaning |
|--|--|---|--|
| `area` | **GeoJSON** | schema `https://geojson.org/schema/GeoJSON.json` | The geometry this entry covers |
| `coverageType` | enum | `INCLUDED` \| `EXCLUDED` | Whether the geometry is served or withheld |
| `restrictionBasis` | enum | `ORBITAL` \| `COMMERCIAL` \| `REGULATORY` | Why the limit is drawn where it is |
| `note` | String `[0..1]` | open domain | Names the instrument of the restriction, where there is one |

Table: ServiceArea attributes. {#tbl:spec-servicearea-attributes}

**Source:** `FV` a construct of this model, forced by PSID620
(`ProductOffering.place` is a bare `PlaceRef`) and by the absence of a Place API
in PSI.

> [!NOTE]
> **What `restrictionBasis` is for.** The three kinds of limit **behave
> differently**: an orbital one is physics and will not move, a commercial one
> is a decision the seller can revisit, a regulatory one can change **without
> either party acting**. A buyer planning a three-year campaign needs that
> difference; the geometry alone will not tell them.
>
> Written as inclusions and exclusions rather than one polygon, because a
> worldwide product with two embargoes is two exclusions - cutting the holes by
> hand loses **why** the hole is there.

### FileFormatSpec *(value type)* {#spec-fileformatspec}

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `format` | enum | `COG` \| `GEOTIFF` \| `NITF` \| `HDF5` \| `NETCDF` \| `PNG` | Raster container format |
| `metadataFormat` | enum | `STAC` \| `OGC_EO` \| `VENDOR_PROPRIETARY` \| `NONE` | Metadata standard delivered with this format |
| `deprecated` | Boolean `[0..1]` | — | Whether the vendor maintains this pairing for compatibility only. Absent means current |

Table: FileFormatSpec attributes. {#tbl:spec-fileformatspec-attributes}

Used by `fileFormat` in `DeliveryProfile`.

**Source:** `FV` a construct of this model, forced by a vendor practice the previous
shape could not express: ICEYE runs COG with STAC JSON as its current set and GeoTIFF
with proprietary XML as legacy. A list of formats beside a single metadata standard
cannot say which goes with which, and a list beside a list implies four combinations
where two exist.

> [!NOTE]
> **Why `deprecated` and not `isLegacy`.** "Legacy" is an observation about age; a
> format can be old and fully supported. `deprecated` is an obligation about the
> future - it tells the buyer not to build a new integration on this pairing, which is
> what the buyer acts on. It is also the term a reader already knows from OpenAPI, JSON
> Schema and STAC, and none of the model's other nine boolean fields carries an `is`
> prefix.
>
> **No sunset date.** A deprecation without a date says "sometime", and a buyer signing
> a multi-year agreement would want to know when. No vendor publishes one; the carrier
> for it, should that change, is `validFor`, which the model already has. Inventing a
> field for a declaration nobody makes is the same error as declaring a domain nobody
> stated.

### Duration {#spec-duration}
A **length** of time with no position on the calendar. An absolute interval is
`TimePeriod` ([TimePeriod](#spec-timeperiod)) and the two are never interchanged.

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `amount` | Integer | ≥ 0 | Number of units |
| `unit` | enum | `MILLISECOND` \| `SECOND` \| `MINUTE` \| `HOUR` \| `DAY` \| `WEEK` \| `MONTH` \| `YEAR` | Unit |

Table: Duration attributes. {#tbl:spec-duration-attributes}

**Source:** `FV` a construct of this model — it lets an offering narrow by range,
which an enum of tiers ("fast delivery", "standard") does not allow.

> [!NOTE]
> **Why the list reaches down to `MILLISECOND`.** `amount` is an integer, and
> the shortest real value in this domain is sub-second: a Sentinel-1
> interferometric-wide burst illuminates a point for roughly 0.8 s, so without
> a finer unit `illuminationTime` could not be stated for the mode that
> produces most of the world's free SAR imagery. Decision **D-73**.

### AngleRange

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `min` | Decimal · ° | 0 – 90 | Lower bound |
| `max` | Decimal · ° | 0 – 90 | Upper bound |

Table: AngleRange attributes. {#tbl:spec-anglerange-attributes}

Used by `incidenceAngle`, `lookAngle`, `qualityValidIncidence`.

**Source:** `FV` a construct of this model. Incidence angle and look angle are
**not automatically convertible**, hence separate fields of the same type rather
than one field.

### DurationRange

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `min` | `Duration` | — | Lower bound |
| `max` | `Duration` | — | Upper bound |

Table: DurationRange attributes. {#tbl:spec-durationrange-attributes}

Used by `temporalBaseline`.

**Source:** `FV` a construct of this model, named for the same reason `AngleRange`
is: a range used in more than one place is a type, not an anonymous pair of
fields. It cannot be carried on a single TMF characteristic, because its two
bounds would land on two separate ones — see R-07 and O-25.

### ServiceCommitment

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `duration` | `Duration` | — | How much time |
| `commitmentLevel` | enum | `BEST_EFFORT` \| `TARGET` \| `GUARANTEED` | How strong the commitment is |
| `achievementRate` | Percent `[0..1]` | 0 – 100 | Declared attainment rate |

Table: ServiceCommitment attributes. {#tbl:spec-servicecommitment-attributes}

| `commitmentLevel` value | Meaning |
|---|---|
| `BEST_EFFORT` | no commitment; the figure describes a typical value |
| `TARGET` | the vendor measures and publishes attainment, but with no remedy on failure |
| `GUARANTEED` | a contractual commitment with consequences on breach |

Table: commitmentLevel values. {#tbl:spec-commitmentlevel-values}

`achievementRate` becomes mandatory under `TARGET` and `GUARANTEED` (W-12).

**Source:** `FV` vendor SLA practice; `{8 h, GUARANTEED, 99%}` and
`{8 h, BEST_EFFORT}` are two different products behind the same number.

### RadiometricFigure

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `value` | Decimal · dB | 0.01 – 5 | The value |
| `sigmaMultiplier` | Integer | 1 \| 2 \| 3 | Standard-deviation multiple |

Table: RadiometricFigure attributes. {#tbl:spec-radiometricfigure-attributes}

**Source:** `FV` the ESA Product Definition states 1 dB @ 3σ, the Annual
Performance Report 2025 states 0.317 dB @ 1σ. Converted: 0.317 × 3 ≈ 0.95 dB @ 3σ —
**the figures agree**, and without the multiplier they look like a threefold
difference.

### GeolocationAccuracy

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `value` | Decimal · m | 0.01 – 30 | The value |
| `metric` | enum | `UNDEFINED` \| `CE90` \| `CE95` \| `LE90` \| `RMSE` \| `SIGMA_1` \| `SIGMA_3` | Which statistic it represents |
| `basis` | enum | `PREDICTED_BUDGET` \| `MEASURED_CALVAL` \| `NOT_PUBLISHED` | Where the figure came from |
| `terrainCorrected` | Boolean | `true` \| `false` | Whether it is post terrain correction |
| `ephemerisClass` | enum | `PREDICTED` \| `RESTITUTED` \| `PRECISE` | For which ephemeris class it holds |

Table: GeolocationAccuracy attributes. {#tbl:spec-geolocationaccuracy-attributes}

**Source:** `FV` ESA marks its declaration `(NRT)`, i.e. for predicted ephemeris;
ICEYE reports RMSE after terrain correction; Capella (2020) publishes a figure
**with no statistic** — hence `UNDEFINED`.

> [!IMPORTANT]
> **`basis = PREDICTED_BUDGET` and `ephemerisClass = PREDICTED` are two
> different things.** The first says where the figure came from; the second says
> for which input data it holds. A **measured** value for a **predicted** orbit
> is the most common case in performance reports.

### SceneReference

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `catalogueRef` | URI | open | Which catalogue |
| `collectionId` | String | open | Which collection within it |
| `itemId` | String | open | Which item within that collection |

Table: SceneReference attributes. {#tbl:spec-scenereference-attributes}

**Source:** `FV` the STAC identity pattern. A bare scene identifier is ambiguous
across catalogues.

### GcpSet

| Attribute | Type | Domain | Meaning |
|---|---|---|---|
| `reference` | URI | open | Pointer to the uploaded file |
| `format` | enum | `CSV` \| `SHAPEFILE` \| `GEOJSON` \| `VENDOR_TEMPLATE` | File format |
| `epsgCode` | Integer | EPSG code | Coordinate system of the points |
| `pointCount` | Integer | 3 – 100 | How many points |
| `declaredAccuracy` | Decimal · m | 0.01 – 5.0 | Declared accuracy of the point measurements |

Table: GcpSet attributes. {#tbl:spec-gcpset-attributes}

All fields carry role `C!` — the buyer supplies them. Validation: W-22.

**Source:** `FV` commercial vendors offering geolocation improvement from
customer control points.

## Enumerations

### ProductType {#spec-producttype}

| Code | Expansion | `dataType` | phase preserved | `processingLevel` |
|---|---|---|---|---|
| `RAW` | Raw / unfocused | `RAW` | — | `L0` |
| `SSC` | Single-look Slant-range Complex | `COMPLEX` | `true` | `L1A` |
| `SGC` | Single-look Ground projected Complex | `COMPLEX` | `true` | `L1A` |
| `SLC` | Single Look Complex | `COMPLEX` | `true` | `L1A` |
| `CPHD` | Compensated Phase History Data | `PHASE_HISTORY` | `true` | `L1A` |
| `MGD` | Multilooked Ground-range Detected | `AMPLITUDE` | `false` | `L1B` |
| `GRD` | Ground Range Detected | `AMPLITUDE` | `false` | `L1B` |
| `GEC` | Geocoded Ellipsoid Corrected | `AMPLITUDE` | `false` | `L1C` |
| `GTC` | Geocoded Terrain Corrected | `AMPLITUDE` | `false` | `L1C` |
| `RTC` | Radiometrically Terrain Corrected | `AMPLITUDE` | `false` | `L1C` |
| `OCN` | Ocean — geophysical variables | `GEOPHYSICAL` | `false` | `L2` |
| `COMPOSITE` | Gridded temporal composite | `GEOPHYSICAL` | `false` | `L3` |

Table: ProductType codes. {#tbl:spec-producttype-codes}

**Source:** `FV` STAC `product:type`; values collected from ESA, ICEYE, Capella
and ASF. The `processingLevel` assignment is `NV` — see the note on
`processingLevel`.

### CollectionStrategy {#spec-collectionstrategy}

Beam sweep strategy. **Subtype selector for `CoverageGeometry`.**

| Code | How the radar collects | Subtype | Characteristic |
|---|---|---|---|
| `STRIPMAP` | beam fixed relative to the platform, sweeps a swath | `SwathCoverage` | resolution constant along the strip |
| `PARALLEL_STRIPMAP` | several strips in parallel | `SwathCoverage` | wider coverage at the same resolution |
| `SCANSAR` | beam hops between sub-swaths | `SwathCoverage` | wide coverage at the cost of azimuth resolution |
| `TOPSAR` | beam steers along track within a burst | `SwathCoverage` | like ScanSAR but without scalloping |
| `SPOTLIGHT` | beam tracks a single point | `SceneCoverage` | best azimuth resolution, small extent |
| `SLIDING_SPOTLIGHT` | beam tracks a point while advancing | `SceneCoverage` | a compromise between spotlight and stripmap |

Table: CollectionStrategy codes. {#tbl:spec-collectionstrategy-codes}

**Source:** `FV` nomenclature shared by ESA, ICEYE and Capella. The notion of a
*swath* does not exist in scene modes, and the notion of a *scene with fixed
dimensions* does not exist in swath modes — hence the split into subtypes.

### ProcessingLevel

| Code | Meaning |
|---|---|
| `L0` | raw, unfocused data |
| `L1A` | complex data, radar geometry |
| `L1B` | detected data, sensor ground geometry |
| `L1C` | geocoded data |
| `L2` | geophysical variables |
| `L3` | composite on a uniform space-time grid |

Table: ProcessingLevel codes. {#tbl:spec-processinglevel-codes}

**Source:** `NV` NASA EOSDIS. The SAR equivalents of `L1A`/`L1B`/`L1C` are **our
own construction** — ESA collapses them into a single `L1`.

### TaskingPriority {#spec-taskingpriority}

| Code | Meaning | Source |
|---|---|---|
| `BACKGROUND` | as-available, **may be pre-empted** | `FV` ICEYE |
| `COMMERCIAL` | FIFO prioritisation, statistical success > 90% | `FV` ICEYE |
| `STANDARD` | base tier | `FV` Capella, OpenCosmos |
| `PRIORITY` | elevated | `FV` Capella, OpenCosmos |
| `URGENT` | highest commercial | `FV` Capella |
| `EMERGENCY` | humanitarian / crisis | `FV` OpenCosmos (`HUMANITARIAN`, level 5) |

Table: TaskingPriority codes. {#tbl:spec-taskingpriority-codes}

> [!NOTE]
> The set is a **union of different vendors' values**, not an abstract scale.
> Mapping onto a numeric scale would require equivalence assumptions that the
> documentation does not support.

### TerrainHandling {#spec-terrainhandling}

| Code | What was done with relief | Example |
|---|---|---|
| `NONE` | nothing — radar geometry | all SLC products |
| `ELLIPSOID_FLAT` | projection onto the ellipsoid, no height | ICEYE GRD |
| `ELLIPSOID_SCENE_HEIGHT` | projection at a single averaged scene height | Capella GEC |
| `ELLIPSOID_AZIMUTH_VARYING` | height varies along track, constant across | Sentinel-1 GRD |
| `DEM_GEOMETRIC` | orthorectification with an elevation model | Capella GEO |
| `DEM_RADIOMETRIC` | geometric **and** radiometric slope correction | ASF RTC, OPERA RTC-S1 |

Table: TerrainHandling codes. {#tbl:spec-terrainhandling-codes}

**Source:** `FV` ESA, Capella, ASF. **The only field distinguishing `GEC` from
`GTC`.**

### AssetRole {#spec-assetrole}

| Code | What the layer contains |
|---|---|
| `amplitude` | detected image in ground geometry |
| `magnitude` | complex image with amplitude and phase |
| `beta0` | backscatter referenced to the slant plane |
| `sigma0` | backscatter referenced to the ground surface |
| `gamma0` | backscatter referenced to the plane perpendicular to the line of sight |
| `local-incidence-angle` | local incidence angle map |
| `ellipsoid-incidence-angle` | ellipsoid incidence angle map |
| `gamma-to-sigma-ratio` | gamma-to-sigma ratio map |
| `scattering-area` | scattering area map |
| `noise-power` | noise power map |
| `covmat` | normalised polarimetric covariance matrix |
| `prd` | polarimetric decomposition |
| `mask` | quality, layover and shadow mask |
| `calibration-lut` | calibration look-up tables |

Table: AssetRole codes. {#tbl:spec-assetrole-codes}

**Source:** `FV` STAC SAR Extension, *Best Practices, Asset Roles*; extended with
the roles CEOS ARD requires.

### Remaining enumerations

| Enumeration | Values |
|---|---|
| `SensorType` | `SAR` *(the only value in this specification)* |
| `DataType` | `RAW` \| `COMPLEX` \| `AMPLITUDE` \| `PHASE_HISTORY` \| `GEOPHYSICAL` |
| `AccessMode` | `ARCHIVE` \| `TASKING` |
| `OrderingMode` | `BY_SCENE` \| `BY_CONSTRAINT` \| `BY_OPPORTUNITY` *(domain narrowed per access subtype - see [ArchiveAccess](#spec-archiveaccess) and [TaskingAccess](#spec-taskingaccess))* |
| `CoverageType` | `INCLUDED` \| `EXCLUDED` |
| `RestrictionBasis` | `ORBITAL` \| `COMMERCIAL` \| `REGULATORY` |
| `CatalogueInterface` | `STAC_API` \| `OGC_CSW` \| `OGC_API_RECORDS` \| `VENDOR_API` \| `WEB_UI_ONLY` |
| `AcquisitionPatternType` | `SINGLE` \| `RECURRING` \| `CONTINUOUS` |
| `IterationFailurePolicy` | `RETRY_IN_SLOT` \| `ROLL_TO_NEXT` \| `SKIP` |
| `Exclusivity` | `NONE` \| `DELAYED` \| `PERMANENT` |
| `TargetGeometryType` | `POINT` \| `POLYGON` \| `CORRIDOR` |
| `FrequencyBand` | `X` \| `C` \| `S` \| `L` \| `P` |
| `Polarization` | `HH` \| `VV` \| `HV` \| `VH` |
| `PolarizationMode` | `SINGLE` \| `DUAL` \| `TWIN` \| `QUAD` |
| `ObservationDirection` | `LEFT` \| `RIGHT` |
| `OrbitDirection` | `ASCENDING` \| `DESCENDING` |
| `ResolutionAxis` | `RANGE` \| `AZIMUTH` |
| `ResolutionPlane` | `SLANT` \| `GROUND` |
| `MeasurementPoint` | `NEAR_RANGE` \| `MID_RANGE` \| `FAR_RANGE` \| `SCENE_CENTRE` |
| `MeasurementAggregation` | `SINGLE` \| `AVERAGED_OVER_ORBITS` \| `PER_BEAM` \| `OVER_ANGLE_RANGE` |
| `WeightingFunction` | `UNIFORM` \| `HAMMING` \| `HANN` \| `TAYLOR` \| `KAISER` \| `OTHER` |
| `GeometryProjection` | `SLANT_RANGE` \| `GROUND_RANGE_SENSOR` \| `MAP_PROJECTED` |
| `ElevationModel` | `NONE` \| `SRTM_30` \| `SRTM_90` \| `COP_DEM_GLO_30` \| `COP_DEM_GLO_90` \| `ASTER_GDEM` \| `NATIONAL` \| `CUSTOMER_SUPPLIED` \| `OTHER` |
| `MapProjectionFamily` | `UTM_WGS84` \| `POLAR_STEREOGRAPHIC` \| `GEOGRAPHIC_WGS84` \| `NATIONAL_GRID` \| `OTHER` |
| `BackscatterRef` | `DN` \| `beta0` \| `sigma0` \| `gamma0` |
| `PixelScale` | `POWER` \| `AMPLITUDE` \| `DECIBEL` |
| `PixelDataType` | `UINT8` \| `UINT16` \| `INT16` \| `FLOAT32` \| `CINT16` \| `CFLOAT32` |
| `SpeckleFilter` | `NONE` \| `LEE` \| `REFINED_LEE` \| `GAMMA_MAP` \| `OTHER` |
| `ArdFamily` | `NRB` \| `POL` \| `ORB` \| `GSLC` \| `INSAR` \| `CB` \| `NONE` |
| `ArdConformanceLevel` | `THRESHOLD` \| `GOAL` \| `NONE` |
| `ConformanceStandard` | `SICD` \| `SIDD` \| `CPHD` \| `STAC` \| `OGC_EO` \| `CEOS_ARD` |
| `GeolocationMetric` | `UNDEFINED` \| `CE90` \| `CE95` \| `LE90` \| `RMSE` \| `SIGMA_1` \| `SIGMA_3` |
| `GeolocationBasis` | `PREDICTED_BUDGET` \| `MEASURED_CALVAL` \| `NOT_PUBLISHED` |
| `EphemerisClass` | `PREDICTED` \| `RESTITUTED` \| `PRECISE` |
| `GcpFileFormat` | `CSV` \| `SHAPEFILE` \| `GEOJSON` \| `VENDOR_TEMPLATE` |
| `AssetPresence` | `ALWAYS` \| `ON_REQUEST` |
| `FileFormat` | `COG` \| `GEOTIFF` \| `NITF` \| `HDF5` \| `NETCDF` \| `PNG` |
| `MetadataStandard` | `STAC` \| `OGC_EO` \| `VENDOR_PROPRIETARY` \| `NONE` |
| `DeliveryChannel` | `S3` \| `HTTPS` \| `SFTP` \| `API` \| `OGC_SERVICE` \| `PHYSICAL_MEDIA` |
| `OgcService` | `WMS` \| `WMTS` \| `WCS` \| `WFS` |
| `MultiSceneDelivery` | `SEPARATE` \| `MOSAIC` |
| `TemporalAggregation` | `DAILY` \| `WEEKLY` \| `TEN_DAY` \| `MONTHLY` \| `SEASONAL` \| `ANNUAL` \| `CUSTOM` |
| `GridReferenceSystem` | `MGRS` \| `UTM` \| `GEOGRAPHIC` \| `EASE_GRID_2` \| `OTHER` |
| `GeophysicalVariable` | `BACKSCATTER` \| `COHERENCE` \| `SOIL_MOISTURE` \| `WIND_SPEED` \| `WAVE_HEIGHT` \| `OTHER` |
| `GapFillingMethod` | `NONE` \| `TEMPORAL_INTERPOLATION` \| `SPATIAL_INTERPOLATION` \| `NEAREST_VALID` \| `OTHER` |
| `CommitmentLevel` | `BEST_EFFORT` \| `TARGET` \| `GUARANTEED` |
| `TimeUnit` | `SECOND` \| `MINUTE` \| `HOUR` \| `DAY` \| `WEEK` \| `MONTH` \| `YEAR` |
| `ResolutionClass` | `F` \| `H` \| `M` |
| `CopernicusCMClass` | `VHR1` \| `VHR2` |
| `LicenceModel` | `OPEN_DATA` \| `SINGLE_USER` \| `MULTI_USER` \| `ENTERPRISE` \| `RESELL_PERMITTED` |
| `EulaTemplate` | `STANDARD` \| `GOVERNMENT` \| `MULTI` \| `CUSTOM` |
| `PermittedPurpose` | `COMMERCIAL` \| `NON_COMMERCIAL` \| `RESEARCH` \| `HUMANITARIAN` |
| `EligibleBuyerClass` | `ANY` \| `GOVERNMENT_ONLY` \| `ACCREDITED_ONLY` |
| `ExportControl` | `NONE` \| `ITAR` \| `EAR` \| `EU_DUAL_USE` |
| `SecurityClassification` | `UNCLASSIFIED` \| `CONTROLLED` \| `CLASSIFIED` |

Table: Remaining enumerations. {#tbl:spec-remaining-enumerations}

## Mapping tables

### `productType` → dependent fields {#spec-producttype-dependent-fields}

The seller sets `dataType` and `processingLevel` **according to this table**.
The “phase preserved” column is informational - it is **not a model field**,
because it would follow entirely from `dataType`. The model does not compute
`dataType` or `processingLevel` either; the validation layer checks that what
the seller set is consistent with the table.

The table is in [ProductType](#spec-producttype).

**Attributes inapplicable by processing level:**

| Level | Inapplicable | Why |
|---|---|---|
| `L0` | `terrainHandling`, `elevationModel`, `mapProjectionFamily`, `epsgCode`, `looks*`, `enl`, `weightingFunction*`, `Resolution` | the image does not exist yet |
| `L1A` | — | full set |
| `L1B` | — | full set |
| `L1C` | — | full set |
| `L2` | `looks*`, `enl`, `weightingFunction*`, `backscatterConvention` | a geophysical variable, not a backscatter image |
| `L3` | `looks*`, `enl`, `weightingFunction*`, `illuminationTime`, single acquisition date | a composite of many acquisitions; requires `CompositeProfile` |

Table: Attributes inapplicable by processing level. {#tbl:spec-inapplicable-by-level}

### CEOS ARD → required roles {#spec-ceos-ard-roles}

This table determines which `assetLayer` roles must carry `presence = ALWAYS`
for the declared `ardFamily` and `ardConformanceLevel` (rule W-36).
**It comes from the CEOS specification, not from the vendor.**

| `ardFamily` | At `THRESHOLD` | Additionally at `GOAL` |
|---|---|---|
| `NRB` | `gamma0`, `local-incidence-angle`, `mask` | `scattering-area`, `gamma-to-sigma-ratio`, `ellipsoid-incidence-angle` |
| `POL` | `covmat` or `prd`, `local-incidence-angle`, `mask` | `ellipsoid-incidence-angle` |
| `ORB` | `sigma0`, `mask` | `local-incidence-angle` |
| `GSLC` | `magnitude`, `local-incidence-angle` | `ellipsoid-incidence-angle` |
| `INSAR` | *specification in preparation* | — |
| `CB` | *specification in preparation* | — |

Table: Asset roles required by CEOS ARD. {#tbl:spec-ceos-ard-roles}

**Source:** `PV` CEOS-ARD SAR PFS v1.3.1 for `NRB`; the remaining families
**require verification at source before implementation**.

### Subtype selectors → applicability

| Selector | Value | Class that appears |
|---|---|---|
| `accessMode` | `ARCHIVE` | `ArchiveAccess` |
| `accessMode` | `TASKING` | `TaskingAccess` + `TaskingTier [1..*]` + `AcquisitionPattern [1..*]` |
| `patternType` | `SINGLE` | `SingleAcquisition` |
| `patternType` | `RECURRING` | `RecurringAcquisition` |
| `patternType` | `CONTINUOUS` | `ContinuousAcquisition` |
| `collectionStrategy` | `SPOTLIGHT`, `SLIDING_SPOTLIGHT` | `SceneCoverage` |
| `collectionStrategy` | `STRIPMAP`, `PARALLEL_STRIPMAP`, `SCANSAR`, `TOPSAR` | `SwathCoverage` |
| `processingLevel` | `L3` | `CompositeProfile` |

Table: Applicability by subtype selector. {#tbl:spec-selector-applicability}

=begin

---

**Version:** 0.11 · **author:** DO

=end
