@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Interface Control Document - Annex-III"
subtitle: "PSI-ICD-Annex-III"
reference: "PSI-ICD-Annex-III"
---
dcr_overrides:
 - dcr:
   from: '2026-08-01'
   to: '2026-12-11'
   version: 'MS13 [2.1.0]'
   author: 'Dominik Ogrodnik'
   message: 'Initial version (activity A102010 EO Domain Modeling & Matchmaking Criteria): EO attribute and taxonomy baseline, EO matchmaking axes and scoring guidance, SAR model notes and UML model, based on draft v0.11 of 2026-11-12. Delivered for stakeholder review.'
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
| Author    | Dominik Ogrodnik  | Project Team                   | Amartus         |
| Author    | Christian Grubert | Project Team                   | CGI             |
| Author    | Hendrik Oppenberg | Technical Officer              | CGI             |
| Approval  | Victoria McCarthy | Project Manager                | SES             |
| Approval  | Wolfgang Robben   | Project Manager                | CGI             |
| Checked   | Pepijn Witte      | Quality Assurance Manager      | CGI             |

Table: Signature Table. {#tbl:signature_table}

@include [Document Change Record](../common/document-change-record.md)

## Documents

### Reference Documents

| Acronym | Reference | Title                                    | Version                  |
|---------|-----------|------------------------------------------|--------------------------|
| PSI-DL  | PSI-DL    | PSI CGI Document List                    | current MS (doc version) |
| PSI-ICD | PSI-ICD   | PSI Interface Control Document           | see before               |
| PSI-TAD | PSI-TAD   | PSI Terms, Abbreviations and Definitions | see before               |
| PSI-TOD | PSI-TOD   | PSI Tasks and Operations Dictionary      | see before               |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Scope of Document

This document answers four questions, in order:

1. **Which EO product types** are represented, and how is the set extended later?
2. **What is the core metadata set**, and what does each field mean?
3. **Which controlled vocabularies, units and value ranges** apply to each attribute?
4. **Where does each value come from**, and how well was that source verified?

It is the input to build PSI product data sheet templates and to define candidate axes.

It is not the full technical reference. This baseline states what the attribute set is, **not** why it came out that way.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)


## Abbreviations

| | | |
|---|---|---|
| **AOI** | Area of Interest | the area a buyer asks to have imaged |
| **ALE** | Absolute Location Error | a geolocation accuracy figure |
| **ARD** | Analysis Ready Data | CEOS family of pre-processed product definitions |
| **CEOS** | Committee on Earth Observation Satellites | publisher of ARD and of the OpenSearch Best Practice |
| **CE90 / LE90** | Circular / Linear Error at 90 % | geolocation statistics; see also RMSE |
| **ENL** | Equivalent Number of Looks | [Resolution](#sec:tax-resolution) |
| **EO** | Earth Observation | |
| **ESA** | European Space Agency | operator of Sentinel-1 |
| **GCP** | Ground Control Point | a surveyed point the buyer supplies to improve geolocation |
| **GeoJSON** | — | the geometry format geometries are bound to |
| **IEEE** | Institute of Electrical and Electronics Engineers | source of the radar band letters |
| **ISLR / PSLR** | Integrated / Peak Sidelobe Ratio | image quality figures: how much energy a bright target spills into its neighbours |
| **MEF** | MEF Forum | source of the LSO Sonata schema convention this baseline follows |
| **NASA EOSDIS** | NASA Earth Observing System Data and Information System | source of the processing-level scale |
| **NESZ** | Noise Equivalent Sigma Zero | the backscatter level at which signal equals instrument noise — the floor of what can be measured |
| **NGA** | National Geospatial-Intelligence Agency (United States) | publisher of the SICD / SIDD / CPHD formats |
| **OAS** | OpenAPI Specification | the machine-readable form of a TM Forum API |
| **OGC** | Open Geospatial Consortium | publisher of the EO metadata and service standards cited here |
| **PRF** | Pulse Repetition Frequency | an instrument parameter, deliberately out of scope ([What is deliberately excluded](#sec:tax-excluded)) |
| **PSI** | the consortium and standard this baseline feeds | its interface definitions are numbered PSID\*\*\* |
| **RMSE** | Root Mean Square Error | |
| **SAR** | Synthetic Aperture Radar | |
| **SICD / SIDD / CPHD** | Sensor Independent Complex Data / Derived Data / Compensated Phase History Data | NGA formats, present here as conformance values |
| **STAC** | SpatioTemporal Asset Catalog | catalogue specification, an OGC Community Standard since October 2025 |
| **TMF** | TM Forum | TMF620, TMF622 and so on are its Open API specifications |
| **URN** | Uniform Resource Name | the identifier form used for `$id` in the schema |
| **VHR** | Very High Resolution | a Copernicus Contributing Mission class, [Cross-vendor resolution class](#sec:tax-cross-vendor-resolution-class) |

Table: Abbreviations. {#tbl:tax-abbreviations}

Product type codes (`GRD`, `SLC`, `SSC` and the rest) are expanded in [Product types](#tax-product-types); processing
levels in [Processing levels](#tax-processing-levels).


# EO Attribute and Taxonomy Baseline

@include [EO-Attribute-Taxonomy-Baseline](annex-iii/EO-Attribute-Taxonomy-Baseline.md)

# EO Matchmaking Axes and Scoring Guidance

@include [EO-Matchmaking-Axes](annex-iii/EO-Matchmaking-Axes.md)

{#page:beginlandscape}

# SAR Data Model

@include [SAR product specification UML model](annex-iii/SAR-model-UML.puml){#fig:sar-product-model width=130%}

{#page:endlandscape}

@include [SAR-data-model](annex-iii/SAR-data-model.md)

# SAR Technical Specification

@include [SAR-technical-specification](annex-iii/SAR-technical-specification.md)
