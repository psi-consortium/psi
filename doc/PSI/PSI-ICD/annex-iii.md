@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Interface Control Document - Annex-III"
subtitle: "PSI-ICD-Annex-III"
reference: "PSI-ICD-Annex-III"
---
dcr_overrides:
 - dcr:
   from: '2022-03-01'
   to: '2022-08-24'
   version: 'MS1'
   author: 'Christian Grubert'
   message: 'Initial version'
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
| Author    | Christian Grubert | Project Team                   | CGI             |
| Author    | Norbert Czeranka  | Project Team                   | CGI             |
| Author    | Dafinka Srezoska  | Project Team                   | CGI             |
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

This document is the Interface Control Document of the Pooling & Sharing Interface (PSI).
It contains the description of the interfaces between a PSS and a provider or two PSS, which are based on TM Forum OpenAPIs.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

# EO Attribute and Taxonomy Baseline

@include [EO-Attribute-Taxonomy-Baseline](annex-iii/EO-Attribute-Taxonomy-Baseline.md)

# EO Matchmaking Axes and Scoring Guidance

@include [EO-Matchmaking-Axes](annex-iii/EO-Matchmaking-Axes.md)

# SAR Model Notes — Design Commentary

@include [SAR-model-notes](annex-iii/SAR-model-notes.md)

# SAR Model UML

![SAR product specification UML model](annex-iii/SAR-model-UML.svg){#fig:sar-model-uml}