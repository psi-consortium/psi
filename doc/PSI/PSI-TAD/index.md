=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Terms, Abbreviations and Definitions"
subtitle: "PSI-TAD"
reference: "PSI-TAD"
---
dcr_overrides:
 - dcr:
   from: '2022-01-01'
   to: '2022-07-07'
   version: 'MS1'
   author: 'Wolfgang Robben'
   message: 'Initial version'
 - dcr:
   from: '2022-07-08'
   to: '2022-07-14'
   version: 'MS1.1'
   author: 'Wolfgang Robben'
   message: 'Updated to resolve RID comments'
 - dcr:
   from: '2022-07-15'
   to: '2022-09-30'
   version: 'MS2'
   author: 'Christian Grubert'
   message: 'various updates.'
 - dcr:
   from: '2022-10-01'
   to: '2022-12-31'
   version: 'MS3'
   author: 'Dafinka Srezoska'
   message: 'Minor updates due to MS2 action items. Various additional updates'
 - dcr:
   from: '2023-01-01'
   to: '2023-04-19'
   version: 'MS4'
   author: 'Christian Grubert'
   message: 'Added change_orders, beam characteristics, user missions, overbooking and best-effort-options'
 - dcr:
   from: '2023-04-20'
   to: '2023-07-27'
   version: 'MS5'
   author: 'Norbert Czeranka'
   message: 'Added SLI, SLA, SLO, SLS, KPI and KPQ, OTM and UI and some rephrasing to overbooking.'
 - dcr:
   from: '2023-07-28'
   to: '2023-10-06'
   version: 'MS6'
   author: 'Hendrik Oppenberg'
   message: 'Clarified on overbooking, overselling and overprovisioning, adjusted TAD definitions, resolved MS5 action items'
 - dcr:
   from: '2023-10-07'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Bela Lars Mueller'
   message: 'Elaborated on provider journey. Add examples of bundled offerings. Finalized overbooking concepts'
 - dcr:
   from: '2024-01-26'
   to: '2024-09-11'
   version: 'MS8 [1.2.0]'
   author: 'Thomas Schulz'
   message: 'Public release adjustments.'
 - dcr:
   from: '2024-09-12'
   to: '2024-12-09'
   version: 'MS9 [1.2.1]'
   author: 'Dominik Ogrodnik'
   message: 'SLA redefinition from UnifiedAPIs.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Hendrik Oppenberg'
   message: 'Mission API: mission assets, products and mission management stubs.'
 - dcr:
   from: '2025-02-04'
   to: '2025-04-23'
   version: 'MS11 [1.3.0]'
   author: 'Wolfgang Robben'
   message: 'SatCom Ontology, Geospatial, EU CID 2023/1054.'
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
| Author    | Dafinka Srezoska  | PSI Project Team               | CGI             |
| Author    | Christian Grubert | PSI Project Team               | CGI             |
| Author    | Norbert Czeranka  | Project Team                   | CGI             |
| Author    | Hendrik Oppenberg | Technical Officer              | CGI             |
| Author    | Christine Gläßer  | Liasion Manager                | CGI             |
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

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document contains all explanations of terminology, abbreviations and definitions used within the Pooling And Sharing Interfaces Standardisation Project (PSI).

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

# Preamble

@include [preamble](preamble.md)

# Terminology & Concepts

@include [terminology & concepts chapter](terms.md)

# Abbreviations

@include [abbreviations chapter](abbreviations.md)

# Definitions

@include [definitions chapter](definitions.md)
