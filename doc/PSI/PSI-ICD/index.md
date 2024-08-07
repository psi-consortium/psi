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
   message: 'Improvement of CustomerInquriy entity relationships. Include relatedParty in ProductCatalog.'
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
| PSI-TAD | PSI-TAD   | Terms, Abbreviations and Definitions | 1.1.0   |

Table: Reference Documents. {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Scope of Document

This document is the Interface Control Document of the Pooling & Sharing Interface (PSI).
It contains the description of the interfaces between a PSS and a provider or two PSS, which are based on TM Forum OpenAPIs.

The following sections heavily refer to terms, abbreviations and definitions defined in the [PSI-TAD].

@include [generated document warning](../common/generated_document.md)

## Release Notes

@include [Release Notes](../common/release_notes.md)

## High-Level Connection

This document describes the interfaces any PSS and any provider system must implement to enable communication between them.
Unless specified otherwise, all interfaces are implemented RESTful, which includes the following properties:

* **Connection Type:** ad-hoc
* **Protocol:** HTTP(S)
* **Content Type:** JSON

Each of the following sections describe the available endpoints and operations, including the parameters, and request bodies.
Wherever a named class is referenced see the [Data-Transfer-Objects](#data-transfer-objects) section for details.
All information is also available as OpenAPI Specifications[^3] in annex I of this document.
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
