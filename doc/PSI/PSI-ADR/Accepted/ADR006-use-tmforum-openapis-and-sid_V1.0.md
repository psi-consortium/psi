# Use TM Forum Open APIs and SID

* ID: ADR006
* Status: :accepted:
* Deciders: @cgr @daf
* Date: 2022-06-10
* Version 1.0
* Category: Architecture

## Context and Problem Statement

The Pooling and Sharing Interfaces project is based on the frameworks of TM Forum.
It should be decided whether the Information Framework (SID) or the Open APIs will be used as a guideline for the implementation of the PSI data model.

Also, the implications on the licensing due to the usage of TM Forum should be considered.

## Decision Drivers

* Easiness of code generation from the model
* Complexity of mapping the satellite communication domain model to the TM Forum's data models
* Documentation coverage
* Licensing implications

## Considered Options

* [TM Forum SID](https://www.tmforum.org/oda/information-systems/information-framework-sid/)
* [TM Forum Open APIs](https://www.tmforum.org/oda/about-open-apis/)

## Decision Outcome

Both TM Forum frameworks will be used for the PSI project, but with a different focus.
For the development of the standardized interfaces, Open APIs will be used.
Nevertheless, the Information Framework (SID) will be used to supplement the documentation, especially regarding the TAD.

## Compliance

The standardized Pooling & Sharing Interfaces will follow a tailored version of the TM Forum Open APIs and data models to match the satellite communication context.
The TAD refers to the ABEs of the Information Framework (SID), including some addendums to show the actual scope (excluding what is not required for this project) and to add some domain-specific information.

### TM Forum SID

The Information Framework (SID) provides a common data model and data dictionary for implementing business processes in the telecommunications sector.
To categorize business entities it contains the concept of domains e.g. Customer, Product, Service, Resource, Common, etc.
Within each domain, further partitioning is achieved by identifying Aggregate Business Entities (ABEs).
To understand the business perspective, these entities contain textual descriptions, properties and relationships depicted via diagrams.

Based on the available documentation, [Eclipse Papyrus](https://www.eclipse.org/papyrus/) was used in attempts to generate entity and DTO classes from the data model.
These were to be used to define standard-compatible request and response bodies of the data exchange between different PSS and providers.
However, due to the vague description of how to generate the classes and the deep hierarchical levels of the model, the code generation was not successful.

* Good, because it contains detailed descriptions of the ABEs, as well as diagrams that show relationships between entities.
* Good, because the satellite communication domain model can be mapped to the SID's model.
* Bad, because of vague documentation on how to generate code classes from the data model.
* Bad, because of too much abstraction and complex inheritance structure of the entities.  

### TM Forum Open APIs

The TM Forum Open APIs were developed to meet the challenges of increasingly complex multipartner digital services and as a response to the rise of Microservices.
Their data model is strongly based on the SID.
The differences that exist between the two occur mostly because the SID has a deep inheritance structure which is difficult to apply in a REST API model.
Therefore, the Open APIs flatten SID's data model which enables the usage of single concrete classes.
This additionally relaxes the use of derived fields from the parent levels in the inheritance hierarchy and enables descoping of unused entities.

Unlike the SID, the data model of TM Forum Open APIs is available on [GitHub](https://github.com/tmforum-apis/Open_Api_And_Data_Model/tree/master/apis) and can be easily generated with the help of different tools such as:

* [Swagger Editor](https://editor.swagger.io/)
* [Open API Generator](https://openapi-generator.tech/) with available plugins for Maven and Gradle.

The TM Forum Open APIs are licensed under Apache 2.0 License terms and conditions.
According to the Apache Licensing FAQ page, if an Apache 2.0 licensed code is used and modified, the result can be used commercially, and can be distributed under a different license, but one needs to acknowledge the use of the Foundation's software e.g. by preserving the license text and any NOTICE files.  

* Good, because it is easy to generate the data model.
* Good, because the satellite communication domain model can be mapped to the TM Forum Open APIs models.
* Good, because contains detailed documentation on how to implement the Open APIs including example code implementations and request/response samples for the REST endpoints.
* Bad, because of complex relationships between entities, although it simplifies and flattens the deep inheritance hierarchy of the SID.

## Scope

The influence of this document on the project's scope is significant. It outlines the decision to adopt TM Forum's frameworks, specifically the Open APIs and the Information Framework (SID), for the PSI project. The document highlights that while both frameworks will be utilized, they will serve different purposes. The Open APIs will be the primary focus for developing standardized interfaces, ensuring ease of integration and alignment with industry standards. The SID will supplement the documentation, particularly for the Technical Architecture Document (TAD). The document also emphasizes the benefits of using TM Forum's standardized interfaces, such as minimal adjustments needed for common business processes and the facilitation of integration between PSSs and service providers. Additionally, it underscores the importance of adhering to widely accepted industry standards to simplify the integration of terrestrial telecommunication services. The holistic approach of TM Forum's framework, with its comprehensive Open API component, is deemed highly applicable to the project's needs. This strategic decision is expected to streamline the development process, enhance interoperability, and ensure compliance with industry best practices.

## Links

[TM Forum Open API Licensing](https://www.tmforum.org/press-and-news/tm-forum-linux-foundation-partner-drive-open-source-architectures-apis-ecosystem-innovation-telecoms-industry/)  
[Apache Licensing FAQ](https://www.apache.org/foundation/license-faq.html)
