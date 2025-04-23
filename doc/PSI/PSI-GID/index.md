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
   from: '2022-01-01'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Bela Lars Müller'
   message: 'Initial version'
 - dcr:
   from: '2024-01-26'
   to: '2024-09-11'
   version: 'MS8 [1.2.0]'
   author: 'Hendrik Oppenberg'
   message: 'Public release adjustments.'
 - dcr:
   from: '2024-09-12'
   to: '2024-12-12'
   version: 'MS9 [1.2.1]'
   author: 'Bela Mueller'
   message: 'Updates to mission definition and mission templating.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Bela Mueller'
   message: 'Added sub-level mission planning.'
 - dcr:
   from: '2025-02-04'
   to: '2025-04-23'
   version: 'MS11 [1.3.0]'
   author: 'Bela Mueller'
   message: 'ODA mission planning component added.'     
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
| Author    | Bela Lars Müller  | PSI Project Team               | CGI             |
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
| PSI-TAD | PSI-TAD   | PSI Terms, Abbreviations and Definitions | see before               |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document contains the description the Graphical Interface of the Pooling & Sharing (PSI), how data **could** be presented and interaction between user and system **could** be implemented in a web frontend.
The document is not aiming to be a strict set of requirements for the frontend, but a guide to get a better understanding of the processes that can be implemented with the APIs.
Thus, the GID is to be understood as a guideline or inspiration for a frontend developer and as a supplement to understand the processes for a backend designer.
Many more roles can take benefit from this kind of presentation, thus, this list of people who may benefit from this visual display of the processes goes on.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

# Provider Journey

@include [Provider GUI](./provider-GUI.md)

# User Journey

@include [User GUI](./user-GUI.md)

# CGA

@include [CGA GUI](./cga.md)

# Mission Management ODA Component

@include [Mission ODA GUI](./mission-oda.md)
