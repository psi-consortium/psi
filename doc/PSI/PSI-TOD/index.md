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
   from: '2022-04-01'
   to: '2024-04-09'
   version: '1.0.0'
   author: 'Hendrik Oppenberg'
   message: 'Release of Version 1.0.0'
- dcr:
   from: '2024-04-09'
   to: '2024-07-29'
   version: '1.1.0'
   author: 'Hendrik Oppenberg'
   message: 'Improve description of InquiryResults and ProductOrders.'
=end

# Document Meta Information

## Document Change Record

Changes to this document are tracked electronically.
No signature is required by the authors.
The following information can prove the integrity of the document and reveal any change.

@dcr(git_qr_code){#fig:dcr_qr_code}

## Documents

### Reference Documents

| Acronym | Reference | Title                                | Version |
|---------|-----------|--------------------------------------|---------|
| PSI-DL  | PSI-DL    | PSI Document List                    | 1.1.0   |
| PSI-ICD | PSI-ICD   | Interface Control Document           | 1.1.0   |
| PSI-REQ | PSI-REQ   | Interface Requirements Document      | 1.1.0   |
| PSI-TAD | PSI-TAD   | Terms, Abbreviations and Definitions | 1.1.0   |
| PSI-TOD | PSI-TOD   | Tasks and Operations Dictionary      | 1.1.0   |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document contains all explanations of tasks and operations supported by the Pooling And Sharing Interfaces Definition Project (PSID) and how a PSS/Provider can integrate these using the interfaces described in the [PSI-ICD].

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

> Note: The TOD does not contain workflows as a guideline for concluding certain business processes.
> It is rather a technical description of all business tasks and operations that are covered by the PSI project, and how they are realised through the standardized interfaces.
> There will be another document that will accommodate case studies as compilations describing business cases inspired by business processes collected from the business partners.

@include [generated document warning](../common/generated_document.md)

@include [Release Notes](../common/release_notes.md)

# Preamble

@include [preamble](preamble.md)

# How to Read this Document

@include [how_to_read_this_document](how_to_read_this_document.md)

# Tasks and Operations

@include [tasks_and_operations](tasks_and_operations.md)
