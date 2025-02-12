=begin

# :book: ​Information for contributors - not included into final document

[[_TOC_]]

Documents referenced in this page:

* PSI-ADR

## internal, general comments

* Add general comments here.

=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Administrative Decision Records - Annex I"
subtitle: "PSI ADR-Annex-I"
reference: "PSI-ADR-Annex-I"
---
dcr_overrides:
 - dcr:
   from: '2022-01-01'
   to: '2023-10-05'
   version: 'MS6'
   author: 'Christian Grubert'
   message: 'Initial version created from PSI-MADR'
 - dcr:
   from: '2023-10-06'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Wolfgang Robben'
   message: 'Minor corrections from MS6 delivery'
 - dcr:
   from: '2024-01-25'
   to: '2024-09-11'
   version: 'MS8'
   author: 'Wolfgang Robben'
   message: 'Updates for MS8 delivery'
 - dcr:
   from: '2024-09-11'
   to: '2024-12-09'
   version: 'MS9 [1.2.1]'
   author: 'Wolfgang Robben'
   message: 'Updates for MS9 delivery'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Wolfgang Robben'
   message: 'Updates for MS10 delivery'
=end

# Document Meta Information

## Document Signature Table

|           | Name               | Function                       | Company         |
| --------- | ------------------ | ------------------------------ | --------------- |
| Author    | Christian Grubert  | PSI Project Team               | CGI             |
| Author    | Dafinka Srezoska   | PSI Project Team               | CGI             |
| Author    | Norbert Czeranka   | Project Team                   | CGI             |
| Author    | Christine Glaesser | Liaison Manager                | CGI             |
| Author    | Hendrik Oppenberg  | Technical Officer              | CGI             |
| Approval  | Rui Goncalves      | Project Manager                | SES             |
| Approval  | Wolfgang Robben    | Project Manager                | CGI             |
| Checked   | Pepijn Witte       | Quality Assurance Manager      | CGI             |

Table: Signature Table. {#tbl:signature_table}

## Document Change Record

### Changes

@dcr(dcr_table)

Table: Document Change Record. {#tbl:dcr}

### Source Control

Changes to this document are tracked electronically.
No signature is required by the authors.
The following information can prove the integrity of the document and reveal any change.

@dcr(git_table)

Table: Git log. {#tbl:git_log}

@dcr(git_qr_code){#fig:dcr_qr_code}

## Documents

### Reference Documents

| Acronym  | Reference | Title                                        | Version                  |
|----------|-----------|----------------------------------------------|--------------------------|
| PSI-DL   | PSI-DL    | PSI CGI Document List                        | current MS (doc version) |
| PSI-ICD  | PSI-ICD   | PSI Interface Control Document               | see before               |
| PSI-REQ  | PSI-REQ   | PSI Requirements                             | see before               |
| PSI-RTM  | PSI-RTM   | PSI RTM - Requirements Traceability Matrix   | see before               |
| PSI-TAD  | PSI-TAD   | PSI Terms, Abbreviations and Definitions     | see before               |
| PSI-TOD  | PSI-TOD   | PSI Tasks and Operations Dictionary          | see before               |

Table: Reference Documents {#tbl:reference-documents}

# Introduction

This annex provides a list of deprecated and superseded architectural decision records (MADR).
They have been moved here from the main document [PSI-ADR] to improve its readability.

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

# List of Deprecated Decisions

The following sections compile the list of management decisions previously taken by the PSI team, that are now deprecated.

@include [List of Decisions](Deprecated/list-of-decisions.md)

# List of Superseded Decisions

The following sections compile the list of management decisions previously taken by the PSI team, that are superseded by other decisions.

@include [List of Decisions](Superseded/list-of-decisions.md)
