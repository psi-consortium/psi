=begin

# EO Attribute & Taxonomy Baseline

**Version:** 0.2 · **Date:** 2026-11-25
**Activity:** A102010 — EO Domain Modeling & Matchmaking Criteria
**Status:** delivered for review

---

=end

## Scope and extensibility

### What is covered

Synthetic Aperture Radar imaging products at processing levels **L0 through L3**, described
**from the buyer's point of view** — that is, the namespace of characteristics a buyer needs
in order to decide what to purchase and how to configure it.

### What is deliberately excluded

| Excluded | Reason |
|---|---|
| Mission, satellite and instrument physics | Antenna dimensions, PRF, transmit power and orbital elements describe the vendor's asset, not the purchased product |
| Product instance layer | Footprint, acquisition timestamps, orbit number and processing status belong to the delivered scene, not to its specification |
| Pricing and billing | Unit of measure, price, billing recurrence and cancellation terms belong to the offering |
| Optical and multispectral products | Out of scope for this phase; the extension point exists, the content does not |

Table: Attributes deliberately excluded from the baseline. {#tbl:tax-excluded}

### How the set extends to optical and multispectral

Extensibility is **structural, not aspirational**. The attribute `sensorType` carries a single
permitted value `SAR` and exists precisely as the extension point. An audit of the eleven
attribute groups against a hypothetical optical model gives:

| Extends unchanged | Requires splitting |
|---|---|
| `AccessProfile` and its subtypes · `RevisitProfile` · `TargetProfile` · `DeliveryProfile` · `LicensingProfile` · `CompositeProfile` | `SensorProfile` (wholly disjoint) · `Resolution` (different qualifiers) · `AcquisitionProfile` · `ProcessingProfile` · `QualityProfile` (partly) |

Table: Extension of the attribute groups to optical and multispectral products. {#tbl:tax-optical-extension}

Six of eleven groups are already sensor-agnostic. That is the substance of the extensibility
claim — the remaining five would need an abstract class with two subtypes, in the same pattern
already used for `AccessProfile`, `AcquisitionPattern` and `CoverageGeometry`.

## Product type taxonomy

### Product types {#tax-product-types}

The primary classifier. It encodes three things at once: whether phase is preserved, which
geometry the pixels sit in, and whether terrain relief was accounted for.

| Code | Expansion | Data class | Phase preserved | Level |
|---|---|---|---|---|
| `RAW` | Raw / unfocused | `RAW` | — | L0 |
| `SSC` | Single-look Slant-range Complex | `COMPLEX` | yes | L1A |
| `SGC` | Single-look Ground projected Complex | `COMPLEX` | yes | L1A |
| `SLC` | Single Look Complex | `COMPLEX` | yes | L1A |
| `CPHD` | Compensated Phase History Data | `PHASE_HISTORY` | yes | L1A |
| `MGD` | Multilooked Ground-range Detected | `AMPLITUDE` | no | L1B |
| `GRD` | Ground Range Detected | `AMPLITUDE` | no | L1B |
| `GEC` | Geocoded Ellipsoid Corrected | `AMPLITUDE` | no | L1C |
| `GTC` | Geocoded Terrain Corrected | `AMPLITUDE` | no | L1C |
| `RTC` | Radiometrically Terrain Corrected | `AMPLITUDE` | no | L1C |
| `OCN` | Ocean — geophysical variables | `GEOPHYSICAL` | no | L2 |
| `COMPOSITE` | Gridded temporal composite | `GEOPHYSICAL` | no | L3 |

Table: SAR product types. {#tbl:tax-product-types}

**Phase is preserved exactly when the data class is `COMPLEX` or `PHASE_HISTORY`.** This is a
physical fact, not a model field — amplitude detection irreversibly discards phase. Without
phase there is no interferometry and no polarimetry.

### Processing levels {#tax-processing-levels}

How far the vendor has taken the data before handing it over. Each step is work the buyer
no longer has to do — and, from L1A onwards, information the buyer can no longer recover.

| Level | What arrives | What can be done with it | Who buys it |
|---|---|---|---|
| `L0` | The radar echoes as downlinked. Not an image — there is nothing to look at | Nothing, until you focus it yourself with a SAR processor | Research groups and integrators running their own processing chain |
| `L1A` | A focused image in the radar's own geometry, one look, **phase preserved** | Interferometry, polarimetry, coherent change detection — everything that needs phase | Anyone measuring ground movement, subsidence or structural change |
| `L1B` | Focused, detected and multi-looked, still in the sensor's ground-range geometry | Amplitude analysis and visual interpretation. **No phase, so no interferometry** | Buyers who will geocode themselves, or who only need backscatter |
| `L1C` | The same image resampled onto a map projection | Opens directly in a GIS and overlays with other layers | Most commercial buyers |
| `L2` | Not an image but a physical quantity derived from backscatter — wind field, wave spectrum, oil extent | Read the numbers. No radar knowledge needed | Maritime, meteorological and environmental users |
| `L3` | Many acquisitions combined onto one fixed grid over a stated period | Area time series, with no per-scene handling at all | Monitoring programmes, land-cover and change services |

Table: SAR processing levels. {#tbl:tax-processing-levels}

**The ladder runs one way.** Detection (L1A → L1B) discards phase irreversibly; geocoding
(L1B → L1C) resamples onto a new grid and the original one is gone. So a higher level buys
convenience and a lower level buys capability — and the lower level is always the larger
file. A buyer who may ever need interferometry must order L1A, whatever they do with it today.

**Why L1 is split three ways here.** ESA publishes a single L1; this baseline splits it,
because the three carry **disjoint attribute sets**, and a single level would leave most of
the namespace meaningless at any given moment. An L1A product has no map projection and no
resampling method; an L1C product has no meaningful look count and no slant-plane
resolution. Keeping them apart is what lets a validator say that a declared combination is
impossible rather than merely unusual.

### Collection strategies

How the beam sweeps. This is a **subtype selector**: the first four produce a swath, the last
two produce a scene, and the notion of swath width does not exist in scene modes.

| Value | Coverage geometry |
|---|---|
| `STRIPMAP`, `PARALLEL_STRIPMAP`, `SCANSAR`, `TOPSAR` | `SwathCoverage` |
| `SPOTLIGHT`, `SLIDING_SPOTLIGHT` | `SceneCoverage` |

Table: Collection strategies and their coverage geometry. {#tbl:tax-collection-strategies}

### Resolution class

**How much resolution was traded away for smoothness.** After focusing, a detected product
can be multi-looked: neighbouring samples are averaged to suppress speckle, the grainy
interference pattern every radar image carries ([Resolution](#tax-resolution)). Averaging makes the image easier to
read and the measurements less noisy, and it costs spatial detail. The resolution class
records how far that was taken.

It matters because `productType` does not record it. Two products can both read
`productType = GRD`, come from the same satellite in the same acquisition mode, and differ by
a factor of four in pixel size. ESA's `IW_GRDH` and `IW_GRDM` are exactly that pair.

| Value | Expansion | Sentinel-1 example | Resolution | Pixel spacing |
|---|---|---|---|---|
| `F` | Full | Stripmap `SM_GRDF` | 9 × 9 m | 3.5 m |
| `H` | High | Interferometric Wide `IW_GRDH` | 20 × 22 m | 10 m |
| `M` | Medium | Interferometric Wide `IW_GRDM` | 88 × 87 m | 40 m |

Table: Resolution classes. {#tbl:tax-resolution-classes}

Two warnings about reading it:

* **The letter is not comparable across acquisition modes.** `IW_GRDH` is 20 m and
  `EW_GRDH` is 50 m — both are "high". The class ranks products within a mode, not between
  modes, and `F` exists only for Stripmap.
* **It is not derivable from pixel spacing.** Two vendors can quote the same 10 m grid having
  averaged different numbers of looks, which means different noise in the result. The class,
  `looksRange` / `looksAzimuthMin` / `looksAzimuthMax` and `enl` are three different statements and the baseline
  carries all three.

### Cross-vendor resolution class

`copernicusCMClass` — `VHR1` or `VHR2`. The only **cross-vendor** resolution vocabulary found
in this work: ICEYE reports the ESA Copernicus Contributing Mission class in its own
performance tables, which maps commercial acquisition modes directly onto Copernicus
categories.

> [!NOTE]
> **Source status: unverified.** ICEYE publishes the assignments; the normative threshold
> definitions were not located in ESA/Copernicus documentation. The field carries a label
> quoted from the vendor, not a verifiable claim.

## Core metadata set

The five fields named in the activity, with their semantics.

### Resolution {#tax-resolution}

**The smallest separation at which two targets can still be told apart.** Not the pixel size —
that is sampling, and the two differ by design.

A single number is not interpretable. The baseline carries resolution as a **list of qualified
entries, at least one per axis**. Where the figure differs by beam, the product carries one entry
per beam, each at that beam's incidence angle — the angle identifies the beam, so no beam field is
needed:

| Field | Type · unit | Domain | Meaning |
|--|-|--|---|
| `axis` | enum | `RANGE` \| `AZIMUTH` | Range resolution follows from bandwidth, azimuth resolution from illumination time — two independent physical mechanisms |
| `valueMin` / `valueMax` | Decimal · m | 0.05 – 100 | Best and worst attainable on this axis |
| `plane` | enum | `SLANT` \| `GROUND` | A slant value is always numerically better than the corresponding ground value by 1/sin(incidence angle) — **exactly a factor of two at 30°** |
| `referenceAngle` | `AngleRange` · ° | 0 – 90 | The incidence angle, or angular range, at which the figure holds; `min` = `max` for a single angle. Where it is a range, `valueMin` and `valueMax` are the figures at its two ends. **Mandatory for a `GROUND` figure**, which cannot be compared without it — ground range resolution varies as 1/sin θ across the swath, a factor approaching three between 20° and 45°. A `SLANT` figure does not depend on the angle |
| `averagedOverOrbits` | Boolean | — | Whether the figure is an average over the minimum and the maximum orbit, as ESA states it for Sentinel-1, rather than a figure for one geometry. Where the figure holds and how it was averaged are two independent statements, hence two fields |

Table: Resolution fields. {#tbl:tax-resolution-fields}

**Companion fields**, in `ProcessingProfile`:

| Field | Type · unit | Domain | Meaning |
|--|-|-|---|
| `pixelSpacingRange` / `Azimuth` | Decimal · m | 0.1 – 100 | Grid sampling interval. Usually finer than the resolution — the image is oversampled so that later interpolation does not lose information |
| `looksRange` | Integer | 1 – 30 | How many independent samples were averaged in range. Smoothness is bought with resolution |
| `looksAzimuthMin` / `Max` | Integer | 1 – 30 | The same in azimuth, as a range: equal where the number is fixed, apart where the seller selects it per image — Capella chooses azimuth looks adaptively and publishes ranges such as 9 to 10 |
| `enl` | Decimal | 1 – 500 | **Equivalent number of looks** — how much smoothing was actually achieved, as against how much was attempted. Explained below |

Table: Resolution companion fields in ProcessingProfile. {#tbl:tax-resolution-companion-fields}

**What `enl` is, and why it differs from the look counts.** A radar image is grainy even over
a perfectly uniform field. The grain is **speckle** — not sensor noise but interference
between the many scatterers inside one resolution cell, and it is the reason a single-look
SAR image is hard to read. Averaging several *independent* looks at the same ground reduces
it: with `N` independent looks the graininess falls by a factor of about √`N`.

`looksRange` × the azimuth looks is what the processor **attempted** — how many looks it cut and
averaged. `enl` is what it **achieved**, measured back off the image over an area that should
be uniform, and read as "this image is as smooth as a perfect average of `enl` independent
looks would be".

The two never match, because the looks are cut from **overlapping** parts of the signal
spectrum (the attribute `lookOverlap` says how much). Overlapping looks see partly the same
interference, so each one after the first contributes less than a whole look's worth of
smoothing. A product cut as 5 × 1 looks typically measures around 4. The gap is not an error
in either number: one is a processing setting, the other a property of the delivered image.

This is also why `enl` has to be declared: it cannot simply be computed from the look
counts.

### Spectral bands — the radar equivalent

SAR is an active, single-frequency instrument. It has no spectral bands in the optical sense;
the corresponding characteristic is the transmitted frequency and its bandwidth.

| Field | Type · unit | Domain | Meaning |
|---|---|---|---|
| `frequencyBand` | enum | `X` \| `C` \| `S` \| `L` \| `P` | Sets penetration depth: X reaches the surface, L penetrates tree canopy |
| `centerFrequency` | Decimal · GHz | 0.4 – 12.0 | Centre frequency of the transmitted signal |
| `bandwidth` | Decimal · GHz | 0.01 – 1.5 | **Determines range resolution** — resolution equals c divided by twice the bandwidth |
| `polarization` | enum list `[1..4]` | `HH` \| `VV` \| `HV` \| `VH` | Transmit and receive polarisation pairs. In a specification or an offering the list is the **domain** the product supports; in an order it is the **set of channels acquired together** on every image, not a choice among alternatives, and it is fixed for a whole series |
| `polarizationMode` | enum | `SINGLE` \| `DUAL` \| `TWIN` \| `QUAD` | `DUAL` records two channels simultaneously, `TWIN` alternately, pulse by pulse |

Table: Radar frequency and bandwidth fields. {#tbl:tax-frequency-fields}

> [!NOTE]
> **Permitted polarisation combinations are constrained.** The OGC schema `omeo/1.0/sar.xsd`
> enumerates singles (`HH`, `VV`, `HV`, `VH`) and specific pairs (`HH,HV` · `HH,VH` · `HH,VV` ·
> `HV,VH` · `VH,HV` · `VH,VV` · `VV,HV` · `VV,VH`). **There are no triples** — full polarimetry
> is expressed through `polarizationMode = QUAD`. Order within a pair is significant.

### Geolocation accuracy

**How closely a pixel corresponds to its true position on the ground.** A bare number is
meaningless; the baseline carries four fields together, and a fifth statement for the whole
geometric group.

| Field | Type · unit | Domain | Meaning |
|---|---|---|---|
| `value` | Decimal · m | 0.01 – 30 | The figure |
| `metric` | enum | `UNDEFINED` \| `CE90` \| `CE95` \| `LE90` \| `RMSE` \| `SIGMA_1` \| `SIGMA_3` | Which statistic. `UNDEFINED` means the vendor published a number without stating the statistic — distinct from publishing nothing |
| `terrainCorrected` | Boolean | — | Whether stated after terrain correction |
| `ephemerisClass` | enum | `PREDICTED` \| `RESTITUTED` \| `PRECISE` | **For which orbital ephemeris the figure holds** |

Table: Geolocation accuracy fields. {#tbl:tax-geolocation-fields}

> [!NOTE]
> **Where the figure came from** — a design budget, a measurement or a guarantee — is not a field
> of the figure. It is stated once for the geometric group as a whole, in the `GEOMETRIC` entry
> of `qualityQualifier` ([Where the quality figures come from — one declaration per group](#tax-where-the-quality-figures-come-from-one-declaration-per-group)), and so covers PSLR, ISLR and co-registration as well. That basis
> and `ephemerisClass` are independent axes: a **measured** value for a **predicted** orbit is
> the commonest case in performance reports.

Buyer-supplied ground control points can improve this. The baseline declares what the buyer may
supply (`gcpFormatAccepted`, `gcpTypeAccepted`, `gcpMinCount`, `gcpMaxCount`,
`gcpAccuracyRequired`) and the accuracy that then applies (`geolocationAccuracyWithGcp`). The
improved figure holds **only if** the supplied points meet the declared accuracy — points
measured with a phone would give a worse result than none at all.

**In radar geometry the kind of point matters as much as its accuracy.** `gcpTypeAccepted`
takes `CORNER_REFLECTOR`, `TRANSPONDER` and `SURVEYED_FEATURE`: a corner reflector or a
transponder is a bright point with a known phase centre, while a surveyed natural feature may be
faint in the image or displaced by layover. The submitted set states its own kinds, which must be
among those accepted. No vendor publishes this today; the field exists because the distinction
decides whether the improved figure can hold.

### Acquisition time and acquisition window {#tax-acquisition-time}

Three independent time axes.

| Axis | Field | Meaning |
|---|---|---|
| **When the satellite images** | `fulfilmentTime` in `TaskingTier` | From window opening to acquisition |
| **When the file arrives** | `deliveryLatency` | From order placement (archive) or from acquisition (tasking) |
| **How soon the same place can be imaged again** | `revisitTimeMin` / `Max` in `RevisitProfile` | Interval between successive observations of one point on the ground |

Table: Time axes of an acquisition. {#tbl:tax-time-axes}

Supporting fields. **A duration carries no enforceable range** — the Observed column
below is what real products show, not a rule the catalogue applies; [Where a stated range is not a constraint](#tax-range-not-constraint) says why.

| Field | Type | Observed | Meaning |
|---|-|-|---|
| `illuminationTime` | `Duration` | 0.8 – 25 s | How long the radar keeps one point inside its beam — **the source of azimuth resolution**. Explained below |
| `minAcquisitionWindowWidth` | `Duration` | hours to days | The narrowest acquisition window the operator will accept. Explained below |
| `minLeadTime` | `Duration` | hours to days | How much notice an order requires before the window may open. Stated per service level, because it differs between them |
| `timeOfInterest` | `TimePeriod` | — | The stretch of time the request is about. Explained below |
| `maxSchedulingHorizon` | `Duration` | days to a year | How far ahead a **commitment** can be placed |
| `feasibilityHorizon` | `Duration` | up to three months | How far ahead candidate passes can be **searched for**. A different question from the one above, and the one a campaign planner asks |
| `revisitReferenceLatitude` | Decimal · ° | 0 – 90 | **The latitude at which the revisit figures hold.** Without it "daily revisit" is a marketing claim, because revisit grows towards the equator |
| `repeatCycle` | `Duration` | 1 – 60 d | When the satellite returns to *exactly* the same track — a precondition of interferometry, **not the same as revisit** |
| `orbitTubeDiameter` | Decimal · m | 10 – 1000 | Diameter of the tube the orbital track is held within |

Table: Acquisition time supporting fields. {#tbl:tax-time-fields}

**`illuminationTime` — dwell, and where fine azimuth resolution comes from.** A radar
achieves along-track resolution by combining echoes collected while the target stays inside
the beam: the longer the dwell, the longer the synthetic aperture, the finer the result. That
is the whole difference between stripmap and spotlight — spotlight steers the beam to keep
staring at one place instead of sweeping past it.

There is **no fixed ceiling**, and the schema declares none. Observed values run from about
0.8 s for one Sentinel-1 interferometric-wide burst, through 2–3 s in stripmap, to 10–25 s in
spotlight and dwell modes. The figures in this document are observations from the reference
instances and the cited mission documentation, not limits a vendor has declared. Because
`Duration` carries an integer amount, sub-second dwell is written in `MILLISECOND`.

**`minAcquisitionWindowWidth` — why you cannot order "tomorrow at 10:00".** When a new
acquisition is tasked, the buyer does not name a time; they name a **window within which the
satellite may take it**. The satellite can only be over a given point at times its orbit
fixes, so a ten-minute window almost certainly contains no pass at all, and the operator
would have to refuse the order or accept one it cannot fill. This field is the floor the
operator sets — typically hours to days — and it is a hard constraint on feasibility, not a
preference. It belongs in the catalogue precisely so that the buyer finds out before
committing. The narrower the window, the fewer candidate passes, so the lower the collection
success rate and, usually, the higher the price.

**Two horizons, not one.** A buyer planning a campaign asks how far ahead the operator
can tell them whether a pass exists; a buyer placing an order asks how far ahead the
operator will commit to one. Open Cosmos searches opportunities three months out while
committing only fourteen days ahead — so a single "horizon" field would have to be one or
the other, and either answer misleads half the buyers. Checking must reach at least as far
as committing, which is the one rule the pair carries.

**`timeOfInterest` — the stretch of time the request is about.** Under tasking it is the
window the acquisition has to fall inside; under archive ordering `BY_CONSTRAINT` it is the
period searched for scenes that already exist. Under `BY_SCENE` it is inapplicable, because
naming a scene already fixes its time.

In a specification it declares only that the product accepts such an interval and under what
conditions; the concrete dates travel on the order. It is **not** the contract period: a
twelve-month agreement carries its own dates on the order item, and may ask for imagery in
three specific weeks inside it. Those are two different statements and the model keeps them
apart.

Acquisition can follow three patterns, which carry disjoint attribute sets:

| Pattern | Adds |
|---|---|
| `SINGLE` | `retaskOnFailure` |
| `RECURRING` | `cadenceMin` / `cadenceMax`, `repetitionCountMin` / `Max`, `iterationFailurePolicy`, optionally `memberConstraint` |
| `CONTINUOUS` | `coveragePeriodMin` / `Max` |

Table: Acquisition patterns. {#tbl:tax-acquisition-patterns}

**`memberConstraint` — what must hold between the acquisitions of a set.** A recurring order is
how a buyer orders an interferometric pair, a longer interferometric stack or a monitoring
series; the difference lies in what is constrained between the members.

| Field | Type · unit | Meaning |
|---|---|---|
| `sameTrack`, `samePassDirection`, `sameLookSide` | Boolean | Equality conditions. They hold across the **whole set**, whatever the pairing |
| `pairingScheme` | `CONSECUTIVE` \| `COMMON_REFERENCE` \| `ALL_PAIRS` | **Which pairs the pair-wise bounds below hold between**: each member and the next; each member and one reference acquisition, which the seller selects, normally to minimise baseline spread; or every pair. Mandatory as soon as any pair-wise bound is present. For a set of two all three name the same pair |
| `temporalBaselineMax` | `Duration` | Largest time between paired acquisitions. Adds information only under `COMMON_REFERENCE` (distance from the reference) and `ALL_PAIRS` (span of the stack); under `CONSECUTIVE` it would restate `cadenceMax` and is not allowed. No minimum — the smallest gap is always a consecutive one, which `cadenceMin` already bounds |
| `perpendicularBaselineMin` / `Max` | Decimal · m | Bounds on the perpendicular baseline. The maximum keeps a pair from decorrelating geometrically; the minimum exists for a pair meant for height retrieval, where the height of ambiguity is inversely proportional to the baseline, and is allowed only for a set of two |
| `burstSyncTolerance` | Decimal · ms | Largest burst synchronisation error — the interferometry condition of the burst modes `TOPSAR` and `SCANSAR` |
| `dopplerCentroidDifferenceMax` | Decimal · Hz | Largest difference in Doppler centroid between paired acquisitions — the stripmap and spotlight counterpart: once the azimuth spectra stop overlapping the pair does not interfere, however good the baselines are |
| `commonCoverageFractionMin` | Decimal · 0 – 1 | Smallest fraction of the requested area covered by **every** member at once — the set counterpart of `minCoverageFraction`. Independent of the pairing scheme |

Table: Conditions between the acquisitions of a set (memberConstraint). {#tbl:tax-member-constraint}

An acquisition outside a bound counts as a failed iteration under `iterationFailurePolicy`, and
under `CONSECUTIVE` that policy cannot be `SKIP`, because a skipped member would create a new
consecutive pair with a doubled gap. A tandem acquisition — two satellites imaging at the same
moment — is not described: the pattern assumes acquisitions at different times.

### Coverage and footprint

Two things that must not be confused: **what the product can cover** and **what the buyer asks
for**.

Product capability, in `CoverageGeometry`:

| Subtype | Fields |
|---|---|
| `SwathCoverage` | `swathWidth` (5 – 500 km), `subSwathCount` (1 – 6), `stripLengthMin` / `Max` (10 – 600 km), `footprintAreaNominal` |
| `SceneCoverage` | `sceneWidth` (2 – 30 km), `sceneLengthMin` / `Max` (2 – 50 km), `footprintAreaNominal` |

Table: Coverage geometry subtypes. {#tbl:tax-coverage-subtypes}

> [!NOTE]
> `footprintAreaNominal` describes the **acquisition**, not the delivered extent.

The limits the seller declares on what may be asked for, in `TargetProfile`:

| Field | Type | Domain | Meaning |
|---|---|---|---|
| `serviceArea` | `ServiceArea` `[0..*]` | — | Where the product may be ordered **at all**, with the reason the limit is drawn (`ORBITAL` \| `COMMERCIAL` \| `REGULATORY`). Written as inclusions and exclusions, so a worldwide product with two embargoed countries is two exclusions rather than a hand-cut polygon |
| `targetGeometryType` | enum list | `POINT` \| `POLYGON` \| `CORRIDOR` | Which geometries the product accepts. Corridor mode matters for linear infrastructure |
| `targetAreaMin` / `Max` | Decimal · km² | 1 – 25 000 / 1 – 250 000 | Smallest and largest orderable area |
| `timeOfInterest` | `TimePeriod` | — | That the product accepts an interval, and under which ordering modes — [Acquisition time and acquisition window](#tax-acquisition-time) |
| `clippingSupported` | Boolean | — | Whether the delivered extent can be narrowed to the requested area, or the buyer always receives the full acquisition footprint |
| `minCoverageFraction` | Decimal | 0 – 1 | The smallest fraction of the requested area the seller will offer a pass for. A long thin target is refused by this, so the buyer can tell before asking. It bounds each pass on its own; for a set, the share all members cover together is `commonCoverageFractionMin` ([Acquisition time and acquisition window](#tax-acquisition-time)) |

Table: Target limits declared in TargetProfile. {#tbl:tax-target-limits}

> [!IMPORTANT]
> **The requested area itself is not in this table, and not a characteristic at all.** It
> travels on the order as `Product.place` with `role = requestedAoi`. TM Forum gives a
> product a first-class place slot with a role; carrying the same polygon as a characteristic
> as well would be a second copy of one truth, and the two copies would eventually disagree.
>
> `serviceArea` is the exception, and it needs one: a `ProductSpecification` has **no** place
> slot, so a seller's declaration about geography has nowhere else to live. `timeOfInterest`
> stays a characteristic for the mirror-image reason — TM Forum has a place slot and no
> equivalent slot for an interval.
>
> The requested area means two different things depending on the ordering mode: under
> `BY_SCENE` it is a **cut-out** from a named scene and must lie inside it; under
> `BY_CONSTRAINT` and `BY_OPPORTUNITY` it is a **target** to cover. In every mode it must lie
> inside the declared `serviceArea`.

> [!NOTE]
> **Four kinds of area, and conflating any two loses something.**
>
> | Role | Means | Lives in |
> |---|---|---|
> | `serviceArea` | where the product may be ordered at all | the specification |
> | `requestedAoi` | what the buyer asked for | inquiry, qualification, order |
> | `acquisitionFootprint` | what the satellite actually imaged | the product inventory |
> | `deliveredExtent` | what the buyer can actually download | the product inventory |
>
> Table: Kinds of area in the ordering chain. {#tbl:tax-area-kinds}
>
> The first two merged lose the refusal; the last two merged make **clipping invisible**. The
> last three travel as `RelatedPlaceRefOrValue.role`.

## Full attribute inventory

Eleven groups, 151 leaf attributes. Full definitions in the technical specification; this
table is the index.

| Group | Attributes | What it answers |
|---|---|---|
| Root classification | `sensorType`, `productType`, `resolutionClass`, `copernicusCMClass`, `systematicProduction`, `dataType`, `processingLevel`, `geophysicalVariable` | What kind of product this is. `geophysicalVariable` is present exactly when `dataType = GEOPHYSICAL` — an L2 wind field as much as an L3 composite |
| `ArchiveAccess` | `orderingMode`, `standingAccess`, `catalogueInterface`, `archiveDepthFrom`, `includesHistoricalArchive`, `archiveUpdateLatency`, `deliveryLatency`, `sourceSceneRef` | How an already existing scene is obtained |
| `TaskingAccess` | `orderingMode`, `maxSchedulingHorizon`, `feasibilityHorizon`, `exclusivity`, `holdbackPeriod`, `taskingTier` `[1..*]`, `acquisitionPattern` `[1..3]` | How a new acquisition is ordered |
| `TaskingTier` | `tierName`, `priority`, `minLeadTime`, `fulfilmentTime`, `deliveryLatency`, `collectionSuccessRate`, `passLimit` | What service levels are sold |
| `AcquisitionPattern` | `patternType`, `minAcquisitionWindowWidth` + subtype fields | One acquisition, a rhythm, or standing access |
| `MemberConstraint` | `pairingScheme`, `sameTrack`, `samePassDirection`, `sameLookSide`, `temporalBaselineMax`, `perpendicularBaselineMin` / `Max`, `burstSyncTolerance`, `dopplerCentroidDifferenceMax`, `commonCoverageFractionMin` | What must hold between the acquisitions of a recurring set — a pair or a stack ([Acquisition time and acquisition window](#tax-acquisition-time)) |
| `RevisitProfile` | `revisitTimeMin` / `Max`, `revisitReferenceLatitude`, `repeatCycle`, `orbitTubeDiameter` | How soon the same place can be imaged again, and whether the track repeats closely enough for interferometry |
| `TargetProfile` | `serviceArea`, `timeOfInterest`, `targetGeometryType`, `targetAreaMin` / `Max`, `clippingSupported`, `minCoverageFraction` | The limits on what may be asked for: which geometries, how large, when, how completely covered, and where at all |
| `SensorProfile` | `frequencyBand`, `centerFrequency`, `bandwidth`, `polarization`, `polarizationMode` | What the instrument transmits and receives |
| `AcquisitionProfile` | `collectionStrategy`, `incidenceAngle`, `incidenceAngleVariation`, `lookAngle`, `orbitDirection`, `observationDirection`, `squintAngleMax`, `illuminationTime`, `coverageGeometry` | The geometry the image was taken in. `observationDirection` is the side of the ground track the antenna points to; with `orbitDirection` it fixes whether the scene is lit from the east or the west. `incidenceAngleVariation` is the far-range minus near-range angle within one image — for a steerable sensor, the maximum over the orderable range |
| `Resolution` | `axis`, `valueMin` / `valueMax`, `plane`, `referenceAngle`, `averagedOverOrbits` | One resolution figure and the conditions it holds under ([Resolution](#tax-resolution)) |
| `ProcessingProfile` | 28 attributes — looks (`looksRange`, `looksAzimuthMin` / `Max`, `enl`), weighting, pixel grid, projection, terrain, calibration, ARD conformance | What was done to the signal before delivery. `geometryProjection` follows completely from `productType`; `terrainHandling` does not — one product type permits several handlings |
| `QualityProfile` | `qualityQualifier`, `qualityValidIncidence` + radiometric (9), geometric (14, including `gcpTypeAccepted`), interpretability (2) | How good the product is, where the figures come from — per group — and under which conditions they hold |
| `DeliveryProfile` | `fileFormat` *(format + metadata + `metadataExtensions` + deprecated)*, `assetLayer` `[1..*]`, `volumeMinGB` / `Max`, `maxBandFileSize`, `deliveryChannel`, `ogcService`, `multiSceneDelivery` | In what form and by what route the data arrives |
| `CompositeProfile` | `temporalAggregation`, `gridReferenceSystem`, `gridSpacing`, `normalizedIncidenceAngle`, `gapFillingMethod`, `minSourceAcquisitions` | Only at L3. `normalizedIncidenceAngle` is optional and applies only to backscatter — terrain-flattened `gamma^0` is usually composited without it |
| `LicensingProfile` | `licenceModel`, `eulaTemplate`, `permittedPurpose`, `eligibleBuyerClass`, `exportControl`, `securityClassification` | What the buyer may do with the data, and whether they may buy it at all |

Table: Attribute groups. {#tbl:tax-attribute-groups}

### Quality attributes

Three attributes carry a qualifier **inside the value**, because the number alone is
incomparable:

| Attribute | Qualifier | Why |
|---|---|---|
| `radiometricAccuracyAbs` / `Rel` | `sigmaMultiplier` (1, 2 or 3) | ESA states 1 dB at 3σ; the performance report states 0.317 dB at 1σ. Converted, these **agree**; compared directly they look like a threefold difference |
| `deliveryLatency`, `fulfilmentTime`, `archiveUpdateLatency` | `commitmentLevel` + `achievementRate` | Eight hours guaranteed and eight hours best-effort are two different products behind the same number |
| Resolution | `plane`, `referenceAngle` (an angle or an angular range), `averagedOverOrbits` | See [Resolution](#tax-resolution) |

Table: Qualifiers required by quality attributes. {#tbl:tax-quality-qualifiers}

> [!NOTE]
> **`archiveUpdateLatency` is measured from the end of sensing** to the moment the scene is
> discoverable and orderable in the archive. Downlink and processing lie inside the interval;
> the buyer's own download does not — that is `deliveryLatency`, measured from order placement.

### Where the quality figures come from — one declaration per group {#tax-where-the-quality-figures-come-from-one-declaration-per-group}

A quality number on its own does not say what kind of statement it is. `qualityQualifier` on
`QualityProfile` says it **per group of figures** — radiometric, geometric, interpretability —
rather than once for the whole block or separately for each of twenty-six values. A vendor may
publish a measured geolocation accuracy and a theoretical noise floor for the same product, so
one posture for the block is too coarse; one per attribute would be twenty-six. The angular
range within which all of them hold stays a single declaration, `qualityValidIncidence`.

Each entry of `qualityQualifier` carries three fields:

| Field | Values | What it settles |
|---|---|---|
| `scope` | `ALL` · `RADIOMETRIC` · `GEOMETRIC` · `INTERPRETABILITY` | Which group the entry describes. `ALL` states one posture for every group and excludes other entries; otherwise each group appears at most once |
| `basis` | `THEORETICAL` · `MEASURED` · `GUARANTEED` · `NOT_STATED` | Whether the figures are a design budget, a measurement, or a commitment the seller will answer for |
| `aggregation` | `WORST_CASE` · `TYPICAL` · `RANGE` · `MINIMUM_ACROSS_FLEET` · `NOT_STATED` | How one number was arrived at where performance varies — across sub-swaths, across incidence angles, or across the satellites of a constellation |

Table: Fields of a quality qualifier entry. {#tbl:tax-quality-qualifier-fields}

Why this matters commercially: ESA states outright that its published quality values are
to be understood **in the theoretical sense**, and the measured Range PSLR in the 2025
performance report is **partly worse** than the declared threshold. A buyer who reads a
declared figure as a guarantee may receive a product worse than declared with no basis for
complaint. One declaration per group removes that ambiguity.

`NOT_STATED` is a real value, not a placeholder. It records that the vendor published
figures without saying what kind they are — the same distinction `GeolocationMetric`
draws with `UNDEFINED`. **Across the five reference products it is the commonest value, and
that is the finding**: Sentinel-1 declares all its figures theoretical, three products state a
basis for geolocation alone (measured), and one states none at all. The industry mostly does
not declare this.

And one attribute whose absence **fails silently**:

> [!IMPORTANT]
> **`pixelScale`** — `POWER` \| `AMPLITUDE` \| `DECIBEL`. The same backscatter value can arrive
> as linear power, as amplitude (its square root) or in decibels (its logarithm). A recipient
> treating amplitude as power gets a result that looks correct and is wrong by an order of
> magnitude. Every other missing field shows up as missing data; this one does not.

## Controlled vocabularies

Fifty named vocabularies, grouped into the rows below. Values and full definitions are
in the technical specification and in the machine-readable schema; this is the index with
provenance. The schema carries further single-valued enumerations which are **discriminators**
rather than vocabularies — they select a subtype (`accessMode`, `patternType`, `sensorType`)
and offer the buyer no choice.

| Vocabulary | Values | Source |
|---|-|---|
| `ProductType` | 12 | OGC `eo:productType`, vendor documentation |
| `ProcessingLevel` | 6 | NASA EOSDIS data processing levels, refined for SAR |
| `DataType` | 5 | derived from product type |
| `ResolutionClass` | 3 | ESA Sentinel-1 Level-1 Product Formatting |
| `CopernicusCMClass` | 2 | ICEYE performance tables *(thresholds unverified)* |
| `CollectionStrategy` | 6 | vendor documentation, all three missions |
| `FrequencyBand` | 5 | IEEE radar band designations |
| `Polarization` | 4 | OGC `sar:polarisationChannels` |
| `PolarizationMode` | 4 | **OGC `omeo/1.0/sar.xsd`** — `S`, `D`, `T`, `Q` |
| `TerrainHandling` | 6 | ESA, Capella, ASF |
| `WeightingFunction` | 6 | ESA, ICEYE |
| `ResolutionAxis`, `ResolutionPlane` | 2 / 2 | ESA Sentinel-1 Product Definition |
| `TaskingPriority` | 6 | union of ICEYE, Capella and Open Cosmos values |
| `AcquisitionPatternType`, `IterationFailurePolicy` | 3 / 3 | Open Cosmos recurrent and systematic tasking |
| `PairingScheme` | 3 | model construct, for `memberConstraint` |
| `OrderingMode`, `CatalogueInterface` | 3 / 5 | CEOS OpenSearch Best Practice, vendor APIs. `OrderingMode`'s domain is narrowed per access subtype: an archive sells `BY_SCENE` and `BY_CONSTRAINT`, tasking sells `BY_CONSTRAINT` and `BY_OPPORTUNITY` |
| `CommitmentLevel` | 3 | model construct |
| `QualityScope`, `QualityBasis`, `QualityAggregation` | 4 / 4 / 5 | model construct, the three fields of `qualityQualifier`; `MINIMUM_ACROSS_FLEET` exists because a constellation's figures may hold for its best satellite or for all of them |
| `CoverageType`, `RestrictionBasis` | 2 / 3 | model construct, for `serviceArea` |
| `GeolocationMetric`, `EphemerisClass` | 7 / 3 | ESA, ICEYE |
| `GcpType` | 3 | model construct — no vendor publishes it |
| `PixelScale`, `PixelDataType`, `SpeckleFilter` | 3 / 6 / 5 | ASF HyP3, ICEYE |
| `AssetRole` | 14 | **STAC SAR Extension v1.3.2**, asset roles |
| `ArdFamily`, `ArdConformanceLevel` | 7 / 3 | **CEOS-ARD SAR PFS v1.3.1** |
| `ConformanceStandard` | 6 | NGA SICD/SIDD/CPHD, STAC, OGC, CEOS |
| `FileFormat`, `MetadataStandard`, `DeliveryChannel`, `OgcService`, `MultiSceneDelivery` | 6 / 4 / 6 / 4 / 2 | OGC service standards, vendor documentation |
| `LicenceModel`, `EulaTemplate`, `PermittedPurpose`, `EligibleBuyerClass`, `ExportControl`, `SecurityClassification` | 5 / 4 / 4 / 3 / 4 / 3 | commercial practice, export-control regimes |
| `TemporalAggregation`, `GridReferenceSystem`, `GeophysicalVariable`, `GapFillingMethod` | 7 / 5 / 6 / 5 | NASA L3A, CEOS-ARD Composite Backscatter |

Table: Controlled vocabularies. {#tbl:tax-vocabularies}

## Units

| Quantity | Unit | Attributes |
|---|---|---|
| Length, ground | metre | resolution, pixel spacing, geolocation accuracy, orbit tube diameter, perpendicular baseline |
| Length, large | kilometre | swath width, strip length, scene dimensions |
| Area | square kilometre | target area, nominal footprint |
| Angle | degree | incidence, look, squint, reference and normalised angles, latitude |
| Frequency | **gigahertz** | centre frequency, bandwidth |
| Frequency, narrow | hertz | look bandwidth |
| Power ratio | decibel | NESZ, radiometric accuracy and resolution, ambiguity ratios, PSLR, ISLR |
| Data volume | gigabyte | delivery volume |
| Duration | amount + unit | **every length of time**, as `{amount, unit}` with unit from `MILLISECOND` to `YEAR` |
| Proportion | percent | achievement rate, collection success rate |
| Count | integer | looks, sub-swaths, repetitions, control points, pass limit |
| Pixel offset | pixel | channel co-registration accuracy |

Table: Units. {#tbl:tax-units}

> [!IMPORTANT]
> **`Duration` and `TimePeriod` are different things and are never interchanged.**
> `Duration` is a *length* of time with no position on the calendar — a lead time, a latency,
> a repeat cycle. `TimePeriod` is an *absolute* interval with a start and an end, and the only
> attribute carrying one is `timeOfInterest`. Both ends of a `TimePeriod` are optional, so an
> open-ended interval needs no separate encoding.
>
> **Durations are never enumerated as named tiers.** `{amount, unit}` lets an offering narrow
> by range, which an enumeration of "8 hours / 24 hours / 72 hours" does not. `amount` is an
> integer, which is why the unit list reaches down to `MILLISECOND`: the shortest real value
> in this domain, radar dwell, is sub-second.
>
> STAC requires **GHz** for `center_frequency` and `bandwidth`, so 600 MHz is written `0.6`.

## Value ranges and their provenance

Each numeric attribute carries a domain in the machine-readable schema as `minimum` and
`maximum`. Their provenance differs and is stated honestly.

| Provenance | Example attributes | Status |
|---|---|---|
| **Read from a source document** | the incidence angle each resolution figure is stated at, polarisation combinations, weighting coefficients 0.52–0.75, orbit tube diameter 120 m, NESZ, PSLR thresholds | verified |
| **Physically bounded** | angles 0–90°, percent 0–100, sigma multiplier 1–3, `enl` ≥ 1 | inherent |
| **Estimated from observed products** | swath width 5–500 km, strip length 10–600 km, scene dimensions, target area, delivery volume, tasking lead times and horizons | **own estimate — not a vendor declaration** |

Table: Provenance of value ranges. {#tbl:tax-range-provenance}

> [!NOTE]
> The third row is stated explicitly so that no reader mistakes an estimate for a citation.

### Where a stated range is not a constraint {#tax-range-not-constraint}

Seventeen attributes are of type `Duration` — lead times, latencies, windows, repeat
cycles. For most of them this document quotes a range: "6 h – 7 d", "7 – 365 d". **None of
those ranges is enforced anywhere.**

The reason is structural rather than an oversight. A `Duration` is an amount plus a unit, so
a range from six hours to seven days has its two bounds **on two different fields**, while
`valueFrom` / `valueTo` constrain one field at a time. In the catalogue the attribute becomes
two characteristics — an unbounded integer and a unit from an eight-value list — and nothing
stops a scheduling horizon of three milliseconds.

**How this was settled.** The duration ranges have been taken out of the Domain column
and the column renamed, because a figure printed under "Domain" reads as a constraint and
these are not. What remains there is an Observed column: useful as orientation, not a rule.

The relations between durations that genuinely matter were already rules and stay rules —
a tier's lead time cannot exceed the scheduling horizon, a recurring cadence cannot promise
more often than revisit allows, and a commitment horizon cannot reach beyond the
feasibility horizon.

One real problem survives, and it belongs elsewhere. "Show me products delivering in under
24 hours" is a question a catalogue cannot answer while `amount` and `unit` are two separate
characteristics. That is a **matchmaking** concern rather than a domain one, and the answer
is the same shape as the one used for time encoding: a normalised value derived at the
catalogue adapter, where conversions to another party's format already live.

This was all found by asking whether `illuminationTime` really has a 120-second ceiling. It
had no ceiling at all; the figure existed only in prose.

### One deliberate deviation, and it is not only TM Forum's

A numeric range in a catalogue is carried by `valueFrom` and `valueTo` on
`CharacteristicValueSpecification`. Both are typed **integer**.

That cannot express `bandwidth` 0.01–1.5 GHz, `lookOverlap` 0–0.9, `enl` 4.4 or a resolution
of 0.25 m — all of them ordinary in this domain. Any range whose bounds are not whole numbers
is simply unstateable, and the obvious escape does not work: the typed subclass
`NumberCharacteristicValueSpecification` overrides `value` to number but **leaves
`valueFrom` and `valueTo` as the abstract parent declared them**. So even the explicitly
numeric subclass can only carry integer bounds.

**This was checked in both places.** It holds in the published TM Forum TMF620 v5.0 OAS, and
it holds unchanged in **PSID620 Product Catalog Management v5.0.0**, the file the PSI
consortium derives from it — the derivation does not correct it.

This baseline emits the two fields as **number**. The consequence is that catalogue files
generated from it will not validate against either schema as published. Because the
PSI copy carries it too, it is a question for consortium governance rather
than a note about somebody else's standard.

## Machine-readable form

The machine-readable form of this baseline is a JSON Schema draft-07 document,
in the convention used by MEF LSO Sonata product schemas and by the PSI consortium — URN `$id`,
a `definitions` section, polymorphism as `oneOf` over subtypes each carrying a single-valued enum
discriminator, and every geometry bound to the public GeoJSON schema.

From it are generated:

| Artefact | Shapes |
|---|---|
| TMF620 `ProductSpecification` | envelope (1 characteristic) · grouped (19) · flat (167, plus 148 aggregation relationships) |
| TMF620 `ProductOffering` | envelope (1) · grouped (17) · flat (122, of which 17 leave the buyer a choice) |
| TMF622 `ProductOrder` | envelope (1) · grouped (17) · flat-indexed (110, of which 11 fall on characteristics the offering left open) |

Table: Artefacts generated from the machine-readable form. {#tbl:tax-generated-artefacts}

The three shapes are the same content at three granularities, and they exist to be compared:
the envelope carries the whole configured product as one object value, the flat form declares
one entry per leaf. Array members carry an index only in the offering and order forms — an
index names a concrete element, and at specification time none has been chosen.

Five reference instances are filled exclusively from cited vendor documentation: Sentinel-1
IW GRDH, ICEYE Spot SLC, Capella Spot GEO, ASF HyP3 RTC, and one invented product sold both
from the archive and through tasking, which exists to exercise the combinations no single
real product shows.

## Source register

Three levels: `FV` verified at source · `PV` partly verified · `NV` not read at source.

### Standards

| | Document | Taken from it |
|:---:|---|---|
| `FV` | **TMF620 Product Catalog Management, OAS v5.0.0** | Characteristic structure, `charSpecRelationship` with aggregation/dependency/exclusivity, `targetProductSchema`, `@valueSchemaLocation`. **Negative finding:** no `minimumQuantity`, no `Constraint` schema |
| `FV` | **OGC 10-157r4**, schema `omeo/1.0/sar.xsd` | The seven SAR elements; `polarisationMode` values including `T`; the enumerated set of permitted polarisation channel combinations |
| `FV` | **STAC Specification 1.1** + `sar` 1.3.2, `product`, `processing`, `raster`, `sat`, `view`, `proj` | Asset roles, field mapping, deprecated fields |
| `FV` | **OGC Community Standard 25-004 / 25-005** *(STAC, adopted 28 Oct 2025)* | STAC's status as an OGC standard |
| `FV` | **CEOS-ARD SAR PFS v1.3.1** | Six sub-product families, Threshold and Goal levels, required asset roles |
| `FV` | **CEOS OpenSearch Best Practice** | Catalogue interface conventions |
| `FV` | **MEF LSO Sonata product schemas** (Access E-Line) | Schema convention: draft-07, URN `$id`, `oneOf` with commented discriminator hints |
| `FV` | **PSI consortium product schemas** (satelliteCapacity) | Directory and reference convention, GeoJSON binding |
| `PV` | **OGC 17-084r1**, **OGC 13-026r9** | Document character and scope |
| `NV` | **NGA SICD / SIDD / CPHD** | Present as conformance values; the metadata field sets were not reviewed |
| `NV` | **NASA EOSDIS Data Processing Levels** | Basis of the level mapping |

Table: Standards used as sources. {#tbl:tax-standards}

### Mission and product documentation

| | Source | Taken from it |
|:---:|---|---|
| `PV` | **Sentinel-1 Product Definition, S1-RS-MDA-52-7440**, issue 2/7 (2016) | Product Performance Parameters table; the caveat that all values are theoretical; the incidence angle at which each resolution figure is stated, averaged over orbits; PSLR, NESZ, radiometric accuracy and ALE declarations |
| `NV` | **Sentinel-1 Product Definition, issue 2/11** (Nov 2025) | **Deliberately unverified.** Behind a Confluence REST API returning 403. Numeric values in examples come from issue 2/7 and may be out of date; the **field structure**, which is what this baseline takes, is stable between issues |
| `FV` | **Sentinel-1 Annual Performance Report 2025** | Measured versus declared figures; the reporting format `−20.56 ± 0.49 (311)`; per-sub-swath and per-satellite breakdown |
| `FV` | **SentiWiki — S1 Products / S1 Mission** | Current product breakdown |
| `FV` | **Capella Space SAR Imagery Products Guide** | Product formats; the three-way confusion between trade name, strategy and parameter set; adaptive azimuth looks. |
| `FV` | **ICEYE Data Product Specification** (sar.iceye.com) | Imaging modes, tasking priorities and lead times, delivery service levels |
| `NV` | **ASF HyP3 Products (RTC)** | Classification of RTC as L2 by ASF, from the service description |
| `NV` | **Sentinel-1 SAR Technical Guide** | **Unavailable at source** — `sentinel.esa.int` no longer resolves; treated as superseded by SentiWiki |

Table: Mission and product documentation used as sources. {#tbl:tax-mission-docs}

## Status and next steps

**Delivered for review.** What can be said about its completeness, stated as narrowly as the
evidence allows: the attribute set was exercised by generating catalogue, offering and order
documents from it in three granularities each, by filling five reference products from cited
vendor documentation, and by writing one complete purchase from enquiry to delivered scene.
All of that validates against the schema, 53 business rules hold across every product, and
each of the rules guarding a case no vendor publishes is checked by a test that breaks the
product on purpose.

That is evidence the set is **internally consistent and sufficient for the documents built
here**. It is not yet evidence that a PSI catalogue will accept it: no template has been
loaded into one, and until that happens the claim stops at this sentence.

What this baseline does **not** yet support:

* **Matchmaking axes.** The attribute-to-axis mapping and the scoring guidance are not part of
  this document; they are in the companion `EO-Matchmaking-Axes.md`.
* **Optical and multispectral products.** The extension point is structural and real; the
  content is absent.
* **Theoretical versus measured quality, in practice.** The model can now say it per group of
  figures ([Where the quality figures come from — one declaration per group](#tax-where-the-quality-figures-come-from-one-declaration-per-group)); most vendors still do not, and `NOT_STATED` remains the commonest value. A
  buyer treating a declared value as a guarantee may receive a product worse than declared, with
  no basis for complaint.
* **The [open points](#tax-open-points)**, and tandem (bistatic) acquisition,
  which the model does not describe.
