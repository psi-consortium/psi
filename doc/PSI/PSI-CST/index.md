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
   to: '2024-04-09'
   version: '1.0.0'
   author: 'Hendrik Oppenberg'
   message: 'Release of Version 1.0.0'
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
| PSI-DL  | PSI-DL    | PSI Document List                    | 1.0.0   |
| PSI-CST | PSI-CST   | PSI Case Study                       | 1.0.0   |
| PSI-REQ | PSI-REQ   | Interface Requirements Document      | 1.0.0   |
| PSI-TAD | PSI-TAD   | Terms, Abbreviations and Definitions | 1.0.0   |
| PSI-TOD | PSI-TOD   | Tasks and Operations Dictionary      | 1.0.0   |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document describes a case study for the Pooling And Sharing Interfaces Standardisation Project (PSI).
In this case study, we examine an organization (referred to as the "governance") that faces a critical problem within the context of GOVSATCOM (Government Satellite Communications) products. The issue at hand revolves around consumers of GOVSATCOM products not receiving timely access, experiencing a lack of assurance regarding desired product attributes (e.g., security), and incurring suboptimal costs.
The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [Release Notes](../common/release_notes.md)

# Case Study

@include [casestudy](casestudy.md)
