=begin

# :book: Information for Contributors - Not Included into Final Document

This is the start document for the standalone rendering of appendix TOD-A01 (RHOD result PSI-TOD-A01).
The content lives in appendices/TOD-A01-Mission_Centric_Matchmaking.md and is included in the PSI-TOD as well (index.md, chapter Appendices).
Edit the content there, not here.

=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Tasks and Operations Dictionary - Appendix A01: Mission-Centric Matchmaking"
subtitle: "PSI-TOD-A01"
reference: "PSI-TOD-A01"
---
dcr_overrides:
 - dcr:
   from: '2026-09-28'
   to: '2026-09-28'
   version: 'MS12 [2.0.0-alpha]'
   author: 'Hendrik Oppenberg'
   message: 'Initial version: the whitepaper "Mission-Centric Matchmaking for Composed Services" as TOD appendix A01.'
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
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
| PSI-MADR | PSI-MADR  | PSI Markdown Administrative Decision Records | see before               |
| PSI-REQ  | PSI-REQ   | PSI Requirements                             | see before               |
| PSI-SLF  | PSI-SLF   | PSI Software License File                    | see before               |
| PSI-TAD  | PSI-TAD   | PSI Terms, Abbreviations and Definitions     | see before               |
| PSI-TOD  | PSI-TOD   | PSI Tasks and Operations Dictionary          | see before               |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document is the standalone rendering of appendix A01 of the [PSI-TOD].
It is rendered separately so that the appendix can be reviewed and distributed on its own, analogous to the annexes of the [PSI-ICD]; its content is identical to the chapter "Appendices" of the [PSI-TOD], which remains the normative entry point.
The appendix opens with its scope against the [PSI-TOD] body, its origin and licence, and its terminology against the [PSI-TAD].
Placement and form follow [PSI-MADR] ADR043.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

# Appendix

## TOD-A01-Mission_Centric_Matchmaking

@include [TOD-A01-Mission_Centric_Matchmaking](appendices/TOD-A01-Mission_Centric_Matchmaking.md)
