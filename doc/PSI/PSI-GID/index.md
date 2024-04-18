=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Graphical Interface Definitions"
subtitle: "PSI-GID"
reference: "PSI-GID"
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
| PSI-GID | PSI-GID   | Graphical Interface Description      | 1.0.0   |
| PSI-TAD | PSI-TAD   | Terms, Abbreviations and Definitions | 1.0.0   |
| PSI-TOD | PSI-TOD   | Tasks and Operations Dictionary      | 1.0.0   |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document contains the description the Graphical Interface of the Pooling & Sharing (PSI), how data will be presented and interaction between user and system will be implemented in a web frontend.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [Release Notes](../common/release_notes.md)

## Provider Journey

@include [Provider GUI](./provider-GUI.md)

## User Journey

@include [User GUI](./user-GUI.md)

## CGA

@include [CGA GUI](./cga.md)
