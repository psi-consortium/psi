=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Tasks and Operations Dictionary"
subtitle: "PSI-TOD"
reference: "PSI-TOD"
---
dcr_overrides:
 - dcr:
   from: '2022-01-01'
   to: '2022-08-24'
   version: 'MS1'
   author: 'Christian Grubert'
   message: 'Initial version. Process definition. Building blocks for the party management'
 - dcr:
   from: '2022-08-25'
   to: '2022-09-30'
   version: 'MS2'
   author: 'Dafinka Srezoska'
   message: 'First API drafts and implementations: Resource, service and product catalogue management, Product offering management. Finalization of the party management. Customer inquiry initial version'
 - dcr:
   from: '2022-10-01'
   to: '2022-12-31'
   version: 'MS3'
   author: 'Dafinka Srezoska'
   message: 'Added inquiry, template, event, attachment and billing tasks. Various additional updates'
 - dcr:
   from: '2023-01-01'
   to: '2023-04-19'
   version: 'MS4'
   author: 'Norbert Czeranka'
   message: 'Added interaction diagrams, user mission tasks and geometries for beams. Clarified on ressource templates'
 - dcr:
   from: '2023-04-20'
   to: '2023-07-27'
   version: 'MS5'
   author: 'Christian Grubert'
   message: 'Added trouble ticket management, feature examples, priority, quality management figures and rephrased inquiry based on ESA comments'
 - dcr:
   from: '2023-07-28'
   to: '2023-10-06'
   version: 'MS6'
   author: 'Christian Grubert'
   message: 'Added access probability, defragmentation and updates required to encompass the JSON Schema approach'
 - dcr:
   from: '2023-10-07'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Christian Grubert'
   message: 'Added product templates, improved product characteristics, advanced billing.'
 - dcr:
   from: '2024-01-26'
   to: '2024-09-11'
   version: 'MS8 [1.2.0]'
   author: 'Thomas Schulz'
   message: 'Public release adjustments.'
 - dcr:
   from: '2024-09-11'
   to: '2024-12-09'
   version: 'MS9 [1.2.1]'
   author: 'Christian Grubert'
   message: 'Definition of mission API.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Christian Grubert'
   message: 'Relative time model in mission API, mission asset management.'
 - dcr:
   from: '2025-02-04'
   to: '2025-04-30'
   version: 'MS11 [1.3.0]'
   author: 'Christian Grubert'
   message: 'Alarm Management, Monitoring API, Performance Management.'
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
| Author    | Dafinka Srezoska  | PSI Project Team               | CGI             |
| Author    | Christian Grubert | PSI Project Team               | CGI             |
| Author    | Norbert Czeranka  | PSI Project Team               | CGI             |
| Author    | Hendrik Oppenberg | Technical Officer              | CGI             |
| Approval  | Victoria McCarthy | Project Manager                | SES             |
| Approval  | Wolfgang Robben   | Project Manager                | CGI             |
| Checked   | Pepijn Witte      | Quality Assurance Manager      | CGI             |

Table: Signature Table. {#tbl:signature_table}

@include [Document Change Record](../common/document-change-record.md)

## Documents

### Reference Documents

| Acronym  | Reference | Title                                        | Version                  |
|----------|-----------|----------------------------------------------|--------------------------|
| PSI-DL   | PSI-DL    | PSI CGI Document List                        | current MS (doc version) |
| PSI-CST  | PSI-CST   | PSI Case Study                               | see before               |
| PSI-ICD  | PSI-ICD   | PSI Interface Control Document               | see before               |
| PSI-MADR | PSI-MADR  | PSI Markdown Administrative Decision Records | see before               |
| PSI-REQ  | PSI-REQ   | PSI Requirements                             | see before               |
| PSI-TAD  | PSI-TAD   | PSI Terms, Abbreviations and Definitions     | see before               |
| PSI-TOD  | PSI-TOD   | PSI Tasks and Operations Dictionary          | see before               |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document contains all explanations of tasks and operations supported by the Pooling And Sharing Interfaces Definition Project (PSID) and how a PSS/Provider can integrate these using the interfaces described in the [PSI-ICD].

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

> Note: The [PSI-TOD] does not contain workflows as a guideline for concluding certain business processes.
> It is rather a technical description of all business tasks and operations that are covered by the PSI project, and how they are realized through the standardized interfaces.
> For the accommodation of case studies as compilations of business cases inspired by business processes collected from the business partners, cf. [PSI-CST].

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

# Preamble

@include [preamble](preamble.md)

# How to Read this Document

@include [how_to_read_this_document](how_to_read_this_document.md)

# Tasks and Operations

@include [tasks_and_operations](tasks_and_operations.md)
