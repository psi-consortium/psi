=begin

# :book: ​Information for contributors - not included into final document

[[_TOC_]]

Documents referenced in this page:

* PSI-MADR

## internal, general comments

* Add general comments here.

=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Markdown Administrative Decision Records"
subtitle: "PSI MADR"
reference: "PSI-MADR"
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
   from: '2022-07-14'
   to: '2022-09-30'
   version: 'MS2'
   author: 'Hendrik Oppenberg'
   message: 'Decisions taken for code patching and testing in MS2'
 - dcr:
   from: '2022-10-01'
   to: '2022-12-31'
   version: 'MS3'
   author: 'Christine Glaesser'
   message: 'Decisions taken for async workflows and cust. inquiry in MS3'
 - dcr:
   from: '2023-01-01'
   to: '2023-04-19'
   version: 'MS4'
   author: 'Wolfgang Robben'
   message: 'Decisions taken for beam handling, demonstrations, inventory API, overbooking and security in MS4'
 - dcr:
   from: '2023-04-20'
   to: '2023-07-18'
   version: 'MS5 pre'
   author: 'Wolfgang Robben'
   message: 'Decisions taken for transition to OpenAPI 3 and bundled group offering'
 - dcr:
   from: '2023-04-19'
   to: '2023-07-27'
   version: 'MS5'
   author: 'Hendrik Oppenberg'
   message: 'Decision prepared for quality management, updated decision requirement trace, extracted roadmap'
 - dcr:
   from: '2023-07-28'
   to: '2023-10-06'
   version: 'MS6'
   author: 'Christine Glaesser'
   message: 'Updated workflow for decision management, added requirement assumptions and descoping/implementation comments'
 - dcr:
   from: '2023-10-07'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Christian Grubert'
   message: 'Descope logistics, elaborated on advanced billing, introduced overbooking, improved traceability'
 - dcr:
   from: '2024-01-26'
   to: '2024-09-11'
   version: 'MS8 [1.2.0]'
   author: 'Norbert Czeranka'
   message: 'Added chapters 3.27 and 3.78. Public domain release changes.'
 - dcr:
   from: '2024-09-12'
   to: '2024-12-09'
   version: 'MS9 [1.2.1]'
   author: 'Bela Mueller'
   message: 'Added mission API decisions and the mission templates.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Wolfgang Robben'
   message: 'Updates for MS10 delivery'
 - dcr:
   from: '2025-02-04'
   to: '2025-04-23'
   version: 'MS11pre [1.2.3]'
   author: 'Wolfgang Robben'
   message: 'Advanced OAS patching, MEF convergence.'     
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

@include [Document Change Record](../common/document-change-record.md)

## Documents

### Reference Documents

| Acronym          | Reference        | Title                                                  | Version                  |
|------------------|------------------|--------------------------------------------------------|--------------------------|
| PSI-DL           | PSI-DL           | PSI CGI Document List                                  | current MS (doc version) |
| PSI-RTM          | PSI-RTM          | PSI RTM - Requirements Traceability Matrix             | see before               |
| PSI-TAD          | PSI-TAD          | PSI Terms, Abbreviations and Definitions               | see before               |
| PSI-TOD          | PSI-TOD          | PSI Tasks and Operations Dictionary                    | see before               |

Table: Reference Documents {#tbl:reference-documents}

### External Annexes

| Reference        | Title or Filename                                      |
|------------------|--------------------------------------------------------|
| PSI-ADR-Annex-I | PSI Markdown Administrative Decision Records - Annex I |

Table: External Annexes {#tbl:external-annexes}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document provides a list of all architectural decision records (ADR).
ADR is a lean templated approach to capture any decisions in a structured way.
The template originated from capturing architectural decisions and developed to a template allowing to capture any decisions taken in project execution.

Decision may be related not only to technological or design aspects but also e.g. processes, workflows and management frameworks as well as tooling for verification and validation.
Such decision will be e.g. the verification and validation approach.

This document will be updated in lockstep with the design progress to reflect all decision taken and will be final only with the last delivery.
This document will be iterated/evolved in line with the agile project execution.
Deprecated and superseded architectural decision records have been moved to [PSI-ADR-Annex-I] to improve the readability of the document.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

### Security Considerations

A security assessment is performed, if needed, on considered options to analyse their strengths and weaknesses from a security point of view.
The assessment is focussing on the three most important concepts, *confidentiality*, *integrity* and *availability*.

*Confidentiality* is the property that information is not made available or disclosed to unauthorized entities.
Data that is or is going to be protected is usually categorized according to the damage that could be done if this data was retrieved by an unauthorized entity.
Usually, several methods can be implemented to ensure confidentiality, amongst those are:

* usage of strong passwords
* data encryption
* store and check ownership of data
* two-factor authentication (2FA)
* (role based) access control

*Integrity* refers to the maintaining of accuracy, consistency and trustworthiness of data during its lifecycle.
To ensure integrity, several mitigations can be put in place:

* a file permission scheme can be implemented
* user access control
* version control
* checksums of files, e.g. using MD5 or SHA
* backups and redundancies for file storage

*Availability* denotes the consistency and accessibility of data for authorized access.
Availability includes the proper maintenance of hardware and technical infrastructure and can be increased by dedicated setups and implementations, for example:

* regular system updates (security patches)
* hardware maintenance and timely repairs
* prevent bottlenecks and single point of failures by introducing redundancy and failover hardware
* firewalls and proxy servers, guarding against denial-of-service (DoS) attacks and network intrusions
* usage of a properly functioning operating system, free of software conflicts

The result of this assessment helps shape a decision, and is presented in a CIA (confidentiality, integrity, availability) table which compares all considered options.

# List of Accepted Decisions

The following sections compile the list of management decisions that where accepted by the PSI team.

@include [List of Decisions](Accepted/list-of-decisions.md)

# List of Proposed Decisions

The following sections compile the list of management decisions that are proposed, but not yet decided by the PSI team.

@include [List of Decisions](Proposed/list-of-decisions.md)

# List of Rejected Decisions

The following sections compile the list of management decisions where all options were rejected by the PSI team.
Though they did not introduce any change, they are important to understand why the scope has been limited.

@include [List of Decisions](Rejected/list-of-decisions.md)
