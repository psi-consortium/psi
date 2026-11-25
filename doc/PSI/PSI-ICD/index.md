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
 - dcr:
   from: '2026-07-27'
   to: '2026-07-31'
   version: 'PSI-DOC-VERSION'
   author: 'Christian Grubert'
   message: 'No update, PSI Phase 2 version bump.'
 - dcr:
   from: '2026-10-06'
   to: '2026-11-23'
   version: 'MS12 [2.0.0-alpha]'
   author: 'Kinga Kocierz'
   message: 'Added PSID648 Quote Management API derived from TMF648 v5.0.0, aligned with PSID679 and TOD-03-06: synchronous or asynchronous quote answered with 201 only, no 404 on create and list, no 409 on create, no 501 on delete, quote id required, quote states with refused, quoteErrorMessage with the reason of a status change, product required in a quote item, 422 on create and update as a component, channel, list without cursor, sort and filter parameters, no TMF648 request and response examples.'
 - dcr:
   from: '2026-10-27'
   to: '2026-11-23'
   version: 'PSI-DOC-VERSION'
   author: 'Dominik Ogrodnik'
   message: 'Added PSID679 Product Offering Qualification API derived from TMF679 v5.0.0: CheckProductOfferingQualification only, creation always answered with 201'
 - dcr:
   from: '2026-11-19'
   to: '2026-11-19'
   version: 'PSI-DOC-VERSION'
   author: 'Dominik Ogrodnik'
   message: 'TMF630 REST API Design Guidelines 5.0.0 as the reference; the paging of lists by offset.'
 - dcr:
   from: '2026-11-09'
   to: '2026-11-19'
   version: 'PSI-DOC-VERSION'
   author: 'Dominik Ogrodnik'
   message: 'Added Observability: the trace context and the correlation identifier in REST headers, events and logs.'
 - dcr:
   from: '2026-11-24'
   to: '2026-11-24'
   version: 'PSI-DOC-VERSION'
   author: 'Kinga Kocierz'
   message: 'PSID648 Quote Management: the server-set attributes, instantSyncQuote and productOfferingQualification removed from Quote_MVO; its state limited to QuoteUpdateStateType (accepted, refused, cancelled); new QuoteItem_MVO without productOffering, productOfferingQualificationItem and state.'
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
For recommended guidelines and design patterns when implementing TM Forum's REST APIs, use the TMF630 REST API Design Guidelines 5.0.0[^2] as a reference.
Filtering, for instance, is an optional function that is not required to be implemented for the GET endpoints returning lists of objects.
Therefore, it is not part of the parameters listed in the respective endpoint sections of the ICD.
If an implementor of the server side decides to develop it, *Part 1* of the REST API Design Guidelines describes the querying of resources with attribute filtering, while *Part 6* describes advanced collection filtering using JSONPath.
Client implementors are advised to clarify which filters are available on the server side.

The GET endpoints returning lists of objects are paged by offset, as *Part 1* describes it (Offset Pagination – Query Based Style):

* The client gives the index of the first object in the parameter *offset* and the number of objects it requests in *limit*.
  Without *offset*, the list starts with the first object; without *limit*, the server applies its own page size or returns the whole list.
  A server may limit the page size to a maximum of its own.
* The server answers with `200`, also when it returns only a part of the list, and states the number of objects in the response in the header `X-Result-Count` and the number of all matching objects in `X-Total-Count`.
* A list that no object matches, or an *offset* beyond the end of the list, is answered with `200` and an empty list; a negative *offset* is answered with `400`.
* The navigation links in the header `Link`, which *Part 1* requires, are not declared by the PSI APIs; a server may return them.
* The other pagination methods of *Part 1*, by range header and by cursor, are not used.

[^2]: https://www.tmforum.org/resources/specifications/tmf630-rest-api-design-guidelines-5-0-0/
[^3]: https://swagger.io/resources/open-api/
[^4]: https://swagger.io/tools/swagger-codegen/

## Security Considerations

@include [security](security.md)

## Observability

@include [observability](observability.md)

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
