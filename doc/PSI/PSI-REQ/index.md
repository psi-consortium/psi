=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Requirements"
subtitle: "PSI-REQ"
reference: "PSI-REQ"
---
dcr_overrides:
 - dcr:
   from: '2022-01-01'
   to: '2022-05-10'
   version: 'MS1'
   author: 'Hendrik Oppenberg'
   message: 'Initial version'
 - dcr:
   from: '2022-05-11'
   to: '2022-09-30'
   version: 'MS2'
   author: 'Hendrik Oppenberg'
   message: 'Additional requirements derived in first implementation'
 - dcr:
   from: '2022-10-01'
   to: '2022-12-31'
   version: 'MS3'
   author: 'Christine Glaesser'
   message: 'Additional requirements derived in MS3 phase'
 - dcr:
   from: '2023-01-01'
   to: '2023-04-19'
   version: 'MS4'
   author: 'Hendrik Oppenberg'
   message: 'Added links to candidate requirements and justifications MADR. These links are shown in the PSI-RVM'
 - dcr:
   from: '2023-04-20'
   to: '2023-07-27'
   version: 'MS5'
   author: 'Hendrik Oppenberg'
   message: 'Cleaned up doubly analysed candidate requirements, introduced MONITOR as new requirement category'
 - dcr:
   from: '2023-07-28'
   to: '2023-10-06'
   version: 'MS6'
   author: 'Christine Glaesser'
   message: 'Added descoping comments and assumptions from the joint requirement workshop, added classifications'
 - dcr:
   from: '2023-10-07'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Hendrik Oppenberg'
   message: 'Improved tracebility of candidate requirements and flow-down. Improved understandings and assumptions.'
 - dcr:
   from: '2024-01-26'
   to: '2024-09-11'
   version: 'MS8 [1.2.0]'
   author: 'Hendrik Oppenberg'
   message: 'Public release adjustments.'
 - dcr:
   from: '2024-09-12'
   to: '2024-12-09'
   version: 'MS9 [1.2.1]'
   author: 'Christian Grubert'
   message: 'Added mission API requirements.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Wolfgang Robben'
   message: 'No update, just version bump.'
 - dcr:
   from: '2025-02-04'
   to: '2025-04-23'
   version: 'MS11 [1.3.0]'
   author: 'Christian Grubert'
   message: 'PS28 Updated due to TMF5 update and advanced OAS patching.'
=end

# Document Meta Information

## Document Signature Table

|           | Name               | Function                       | Company         |
| --------- | ------------------ | ------------------------------ | --------------- |
| Author    | Hendrik Oppenberg  | Technical Officer              | CGI             |
| Author    | Christine Glaesser | Liaison Manager                | CGI             |
| Approval  | Rui Goncalves      | Project Manager                | SES             |
| Approval  | Wolfgang Robben    | Project Manager                | CGI             |
| Checked   | Pepijn Witte       | Quality Assurance Manager      | CGI             |

Table: Signature Table. {#tbl:signature_table}

@include [Document Change Record](../common/document-change-record.md)

## Documents

### Reference Documents

| Acronym | Reference | Title                                    | Version                  |
|---------|-----------|------------------------------------------|--------------------------|
| PSI-DL  | PSI-DL    | PSI CGI Document List                    | current MS (doc version) |
| PSI-REQ | PSI-REQ   | PSI Requirements                         | see before               |
| PSI-SDP | PSI-SDP   | PSI Strategy And Development Plan        | see before               |
| PSI-TAD | PSI-TAD   | PSI Terms, Abbreviations and Definitions | see before               |
| PSI-TOD | PSI-TOD   | PSI Tasks and Operations Dictionary      | see before               |
| PSI-VVP | PSI-VVP   | PSI Verification And Validation Plan     | see before               |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document contains requirements defined to the Pooling And Sharing Interfaces Standardisation Project (PSI).

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

# Requirement Categories

The PSI Requirements are written along the project specific terminology and definitions.
Please refer to the PSI Terms, Abbreviations & Definitions [PSI-TAD] for more information.

@include [Requirement categories](requirement_categories.md)

# PSI Requirements

The PSI requirements are derived requirements and have been engineered in the course of project execution.

@include [PSID Requirements](psid_requirements.md)
