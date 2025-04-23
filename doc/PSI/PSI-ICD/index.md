=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Interface Control Document"
subtitle: "PSI-ICD"
reference: "PSI-ICD"
---
dcr_overrides:
 - dcr:
   from: '2022-03-01'
   to: '2022-08-24'
   version: 'MS1'
   author: 'Christian Grubert'
   message: 'Initial version'
 - dcr:
   from: '2022-08-25'
   to: '2022-09-30'
   version: 'MS2'
   author: 'Christian Grubert'
   message: 'Added party management and resource registration.'
 - dcr:
   from: '2022-10-01'
   to: '2022-12-31'
   version: 'MS3'
   author: 'Christian Grubert'
   message: 'Added inquiry, template, event, attachment and billing API'
 - dcr:
   from: '2023-01-01'
   to: '2023-04-19'
   version: 'MS4'
   author: 'Christian Grubert'
   message: 'Updated endpoints descriptions for billing and attachments, added beam areas, best-effort flag and inquiry timeouts, added security considerations'
 - dcr:
   from: '2023-04-20'
   to: '2023-07-26'
   version: 'MS5'
   author: 'Norbert Czeranka'
   message: 'Priority system for offerings, characteristics, trouble ticket management and time intervals'
 - dcr:
   from: '2023-07-27'
   to: '2023-10-06'
   version: 'MS6'
   author: 'Christian Grubert'
   message: 'Introduced JSON Schema approach and GeoJSON details'
 - dcr:
   from: '2023-10-07'
   to: '2024-01-25'
   version: 'MS7'
   author: 'Christian Grubert'
   message: 'Clarified mandatory columns, additional JSON schemas, introduced polymorphism'
 - dcr:
   from: '2024-01-26'
   to: '2024-09-11'
   version: 'MS8 [1.2.0]'
   author: 'Thomas Schulz'
   message: 'Public release adjustments.'
 - dcr:
   from: '2024-09-12'
   to: '2024-12-09'
   version: 'MS9 [1.2.1]'
   author: 'Christian Grubert'
   message: 'Mission-API and location handling updates.'
 - dcr:
   from: '2024-12-10'
   to: '2025-02-03'
   version: 'MS10 [1.2.2]'
   author: 'Norbert Czeranka'
   message: 'Relative times in Mision-API, implicit subtypes, Open-API 3.0 type update.'
 - dcr:
   from: '2025-02-04'
   to: '2025-04-23'
   version: 'MS11 [1.2.3]'
   author: 'Christian Grubert'
   message: 'TMF5 Updates (migrations) to all APIs.'  
=end

# Document Meta Information

## Document Signature Table

|           | Name              | Function                       | Company         |
| --------- | ----------------- | ------------------------------ | --------------- |
| Author    | Christian Grubert | Project Team                   | CGI             |
| Author    | Norbert Czeranka  | Project Team                   | CGI             |
| Author    | Dafinka Srezoska  | Project Team                   | CGI             |
| Author    | Hendrik Oppenberg | Technical Officer              | CGI             |
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
| PSI-ICD | PSI-ICD   | PSI Interface Control Document           | see before               |
| PSI-TAD | PSI-TAD   | PSI Terms, Abbreviations and Definitions | see before               |
| PSI-TOD | PSI-TOD   | PSI Tasks and Operations Dictionary      | see before               |

Table: Reference Documents. {#tbl:reference-documents}

### External Annexes

| Reference        | Title or Filename    |
|------------------|----------------------|
| PSI-ICD-Annex-I  | PSI-ICD-Annex-I.zip  |
| PSI-ICD-Annex-II | PSI-ICD-Annex-II.zip |

Table: External Annexes {#tbl:external-annexes}

# Introduction

@include [common introduction](../common/intro_description.md)

## Scope of Document

This document is the Interface Control Document of the Pooling & Sharing Interface (PSI).
It contains the description of the interfaces between a PSS and a provider or two PSS, which are based on TM Forum OpenAPIs.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

@include [Release Notes](../common/release_notes.md)

## High-Level Connection

This document describes the interfaces any PSS and any provider system must implement to enable communication between them.
Unless specified otherwise, all interfaces are implemented RESTful, which includes the following properties:

* **Connection Type:** ad-hoc
* **Protocol:** HTTP(S)
* **Content Type:** JSON

Each of the following sections describe the available endpoints and operations, including the parameters, and request bodies.
Wherever a named class is referenced see the [Data-Transfer-Objects](#data-transfer-objects) section for details.
All information is also available as OpenAPI Specifications[^3] in [PSI-ICD-Annex-I] of this document.
These files can be used to generate server and client code[^4] in a programming language of choice.

Every interface is implemented by a server and a client.
There is no clear distinction between a PSS and a provider system in this document, since both can implement each side depending on the use-case.
Especially the broker-type PSS will often receive data from one party (e.g. the customer) as a server and forward it to another (e.g. the provider) as a client.
The PSI mock-up is a sample implementation of the endpoints described in the ICD.

It is up to the implementor to decide how the endpoints will be implemented internally on their side.
For recommended guidelines and design patterns when implementing TM Forum's REST APIs, use the TMF630 REST API Design Guidelines 4.2.0[^2] document as a reference.
Filtering, for instance, is an optional function that is not required to be implemented for the GET endpoints returning lists of objects.
Therefore, it is not part of the parameters listed in the respective endpoint sections of the ICD.
If an implementor of the server side decides to develop it, *Part One* of the REST API Design Guidelines describes the recommendations for querying resources with attribute filtering, while *Part Six* describes advanced collection filtering using JSON Path.
Client implementors are advised to clarify which filters are available on the server side.

[^2]: https://www.tmforum.org/resources/specification/tmf630-rest-api-design-guidelines-4-2-0/
[^3]: https://swagger.io/resources/open-api/
[^4]: https://swagger.io/tools/swagger-codegen/

## Security Considerations

@include [security](security.md)

## Types and Characteristics

@include [characteristics](characteristics.md)

# REST APIs

@include [rest-apis](rest-apis/index.md)

# Data-Transfer-Objects

The following sections describe all DTOs used in the APIs described above.
Most entities can make use of polymorphism using the `@type` and `@baseType` attributes.
An implementation may omit these fields if only the base type is used, i.e. no subtypes exist.
This is the case for essentially all inner types, e.g. characteristics and references.
In references, the attribute `@referredType` can be used to hint the type of the target entity.

@include [schemas](schema/index.md)
