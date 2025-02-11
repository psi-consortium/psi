=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Case Study"
subtitle: "PSI-CST"
reference: "PSI-CST"
---
dcr_overrides:
 - dcr:
   from: '2022-04-01'
   to: '2023-04-27'
   version: 'MS4'
   author: 'David Valcarcel'
   message: 'Initial version'
 - dcr:
   from: '2023-04-28'
   to: '2023-07-26'
   version: 'MS5'
   author: 'Norbert Czeranka'
   message: 'Restructurings, extracted roadmaps and portions of SDP, extracted case study'
 - dcr:
   from: '2023-07-28'
   to: '2023-10-05'
   version: 'MS6'
   author: 'Divya Chauhan'
   message: 'Added information stemming from SES internal case study, improved descriptions of broker and provider PSS'
 - dcr:
   from: '2023-10-06'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Hendrik Oppenberg'
   message: 'Elaborated on matchmaking'
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
   author: 'Hendrik Oppenberg'
   message: 'No update, just version bump.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Wolfgang Robben'
   message: 'No update, just version bump.'
=end

# Document Meta Information

## Document Signature Table

|          | Name               | Function                  | Company |
|----------|--------------------|---------------------------|---------|
| Author   | Norbert Czeranka   | PSI Project Team          | CGI     |
| Author   | Divya Chauhan      | PSI Project Team          | SES     |
| Approval | Rui Goncalves      | Project Manager           | SES     |
| Approval | Hendrik Oppenberg  | Technical Officer         | CGI     |
| Checked  | Pepijn Witte       | Quality Assurance Manager | CGI     |

Table: Signature Table. {#tbl:signature_table}

@include [Document Change Record](../common/document-change-record.md)

## Documents

### Applicable Documents

| Acronym | Reference | Title | Version |
|---------|-----------|-------|---------|
|         |           |       |         |

Table: Applicable Documents. {#tbl:applicable-documents}

{#page:break}

### Reference Documents

| Acronym  | Reference | Title                                        | Version                  |
|----------|-----------|----------------------------------------------|--------------------------|
| PSI-DL   | PSI-DL    | PSI CGI Document List                        | current MS (doc version) |
| PSI-MADR | PSI-MADR  | PSI Markdown Administrative Decision Records | see before               |
| PSI-TAD  | PSI-TAD   | PSI Terms, Abbreviations and Definitions     | see before               |
| PSI-TOD  | PSI-TOD   | PSI Tasks and Operations Dictionary          | see before               |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document describes a case study for the Pooling And Sharing Interfaces Standardisation Project (PSI).
In this case study, we examine an organization (referred to as the "governance") that faces a critical problem within the context of (Gov)SatCom (Government Satellite Communications) products. The issue at hand revolves around consumers of (Gov)SatCom products not receiving timely access, experiencing a lack of assurance regarding desired product attributes (e.g., security), and incurring suboptimal costs.
The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [Release Notes](../common/release_notes.md)

# Case Study

@include [casestudy](casestudy.md)

# A User Story: Demonstration of capabilities

@include [userstory](userstory.md)
