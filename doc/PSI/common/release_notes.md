# PSI Release Notes

## Release 2.1.0 (MS13)

PSI 2.1.0 extends the customer journey between product discovery and ordering with standardized product offering qualification and quote management. The release also improves API consistency, observability, event handling, documentation, test data, and the build and validation toolchain.

## Key Highlights

### Product Offering Qualification

The new **PSID679 Product Offering Qualification API**, derived from TMF679 v5.0.0, allows a customer or PSS to verify whether a product offering is available and can be delivered with the requested configuration.

The release adds the corresponding task and operation descriptions in **TOD-03-05**, requirements in **REQ-03-05**, REST endpoints, data-transfer objects, OpenAPI specifications, and mock-up support. Qualification may be processed synchronously or asynchronously, while state changes can be followed by retrieval or event notification.

### Quote Management

The new **PSID648 Quote Management API**, derived from TMF648 v5.0.0, introduces the quote as an optional step between qualification and product ordering. It supports the creation, update, retrieval, and listing of quotes and defines the handling of quote items, prices, states, and provider responses.

The release adds the corresponding task and operation descriptions in **TOD-03-06**, requirements in **REQ-03-06**, REST endpoints, data-transfer objects, OpenAPI specifications, and mock-up support.

### End-to-End Customer Journey

The PSI case study now describes extended user journeys for end-to-end satellite service delivery. It covers the participating actors, service categories, discovery alternatives, product qualification, quote negotiation, ordering, activation, mission evolution, inventory, change orders, usage, billing, and termination.

### API Observability and Consistency

The new observability guidance defines trace context, correlation identifiers, event propagation, and logging across PSI APIs. Event Management now lists the event topics used by the APIs and aligns callback registration with the TMF630 REST API Design Guidelines 5.0.0.

Collection endpoints use TMF630 offset pagination conventions. The API documentation and generated models were also aligned more consistently across request, response, create, and update schemas.

## What's New

### Newly Added APIs

* **PSID648 Quote Management**, derived from TMF648 v5.0.0
* **PSID679 Product Offering Qualification**, derived from TMF679 v5.0.0

### Added Tasks and Requirements

* **TOD-03-05** and **REQ-03-05** for Product Offering Qualification Management
* **TOD-03-06** and **REQ-03-06** for Quote Management
* Event topic, callback, and dispatch guidance in **TOD-01-02**

### Architecture Decisions

* **ADR044** records the deviations of PSID648 from TMF648.
* **ADR045** defines the scope of a product modification.
* **ADR046** adopts TMF679 and TMF648 for PSI.
* **ADR047** records the deviations of PSID679 from TMF679.
* **ADR048** defines common rules for deviations from TM Forum APIs.
* **ADR049** addresses AI-assisted capabilities.
* **ADR031** is deprecated by ADR046, and ADR021 now refers to the revised qualification approach.

### Tooling and Quality Improvements

* Extended OAS transformation support for imported examples, request bodies, responses, required properties, API descriptions, and operation examples
* Automated checks for document generation, model generation, and API schema consistency
* Updated CI workflows for main, pull-request, and release builds
* Task-based commands for building, checking, and testing the repository
* Expanded synthetic data for parties, offerings, specifications, orders, services, and resources
* Updated mock-up controllers and integration tests for the new APIs

## Compatibility Notes

PSID648 and PSID679 specialize the TM Forum v5 APIs for PSI workflows. Their intentional deviations, including response behavior, update restrictions, required data, and state handling, are documented in ADR044, ADR047, and ADR048.

## Known Limitations

No known limitations specific to this release.

## Feedback and Contributions

We welcome feedback, issue reports, and contributions from the PSI community. Please use the repository's contribution process to propose corrections and improvements.
