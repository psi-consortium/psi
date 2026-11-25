# Rules for Deviations from TM Forum APIs

* ID: ADR048
* Status: :proposed:
* Deciders: Dominik Ogrodnik, @hop, @cgr
* Date: 2026-11-25
* Version: 0.1
* Category: Architecture

## Context and Problem Statement

PSI builds most of its APIs on the TM Forum Open APIs (ADR006) and derives each of them from its TM Forum parent with transformation rules in the build (ADR042); PSID143 Performance Monitoring is derived in the same way from the Mplify (formerly MEF) API W143, and PSID001 Customer Inquiry and PSID002 Mission Management are PSI's own.
A PSID API derived from TM Forum deviates from its parent in some way: it removes operations and attributes, restricts open strings by enumerations, imports the PSI definition of shared schemas, adds response codes or changes descriptions.

PSI follows such rules in practice, but has not written them down, so each API applies them anew, and the PSID APIs come to differ where they need not.
For example, PSID622 keeps `404` on `GET` of a collection and on `POST`, where it cannot occur, while PSID679 and PSID648 remove it. It is also not settled where the deviations are written down.

Today they are recorded:

* as transformation rules and their comments in `build.gradle`;
* as overlays under `doc/PSI/PSI-ICD/open-apis/extensions/`;
* and for the APIs prepared under CCN #4, in a decision record (ADR044 for PSID648, ADR047 for PSID679).

TMF630 v5.0.0 Part 8 "TM Forum API Specialization for a context" states what a specialized API may and may not change.
It lists the operations, endpoints, attributes, descriptions and examples a specialized API may remove or add. It does not cover response codes, whereas every PSID API removes or adds some.
Some rules can also be read from the conformance profile of the TM Forum API.

In the review of the records of the meetings of 2026-09-30 and 2026-10-05, CGI proposed a decision record on the rules for deviations from the TM Forum Open APIs.

## Decision Drivers

* The same case is handled the same way in every PSID API, e.g. a reference to an unknown entity is answered with the same code
* As few deviations as possible
* Every deviation carries its reason and whether it keeps conformance with the parent
* A deviation is written down once, next to the decision that drives it
* A reviewer can check that the recorded deviations and the build agree
* No deviation that changes the meaning of a TM Forum attribute

## Considered Options

* Option 1: No written PSI rules; each PSID API decides its deviations
* Option 2: TMF630 Part 8, and PSI rules for what Part 8 does not cover

## Decision Outcome

Chosen option: "Option 2: TMF630 Part 8, and PSI rules for what Part 8 does not cover", because it gives all PSID APIs the same rules.
A new PSID API is built by these rules, and the deviations of every PSID API are recorded in a decision record of that API.

### What a PSID API may change

A PSID API follows TMF630 v5.0.0 Part 8:

* It may reduce its parent (Rule 5), e.g. omit an operation, an endpoint or an optional attribute, or restrict an open string by an enumeration.
* It may extend its parent in a backward compatible way (Rule 6), e.g. add an attribute, make an optional attribute required, overwrite a description or replace examples.
* It keeps the required attributes (Rule 3) and the names of the resources and endpoints (Rule 7).
* It does not give an existing attribute a different functional meaning (Rule 8) and does not change a type incompatibly (Rule 9).

### PSI rules for what Part 8 does not cover

An API that is added to PSI follows these rules, whereas whether and when an existing PSID API is aligned with them is decided for each API separately.
Since the rules change response codes and schemas on which the clients of an existing API may rely, the API is aligned with all of them at once, in all its operations and schemas, so that its clients need one change on their end.

1. **Creation is answered with `201`.**
   A `POST` that creates a resource, also a task resource such as a qualification, answers `201 Created` with the created resource; `200` and `202` are removed.
   The resource is kept with its identifier, and the server returns it in the state it has reached, final or not (TMF630 Part 1 §9.2, §9.6).
   A flag such as `instantSyncQualification` or `instantSyncQuote` keeps its TM Forum meaning, which is the client's expectation of an immediate result.
2. **The identifier is required.**
   `id` is required in the resource, since every answer returns it (TMF630 Part 1), and `href` stays optional. The client does not set the identifier of a resource it creates.
3. **Response codes.**
   A response code that cannot occur is removed:
   * `404` is answered only for an unknown identifier in the path, never on `GET` of a collection (an empty list is answered with `200`) and never on `POST`; an entity that a request refers to and that is unknown, e.g. an offering, is answered with `422`;
   * `409` is answered only for a conflict with the current state of the resource, never on a resource creation;
   * `405` is answered for an operation that a server does not offer for a specified resource, e.g. `PATCH` on an existing order where the server does not offer it;
   * `501` is answered as TMF630 defines it: for JSONPath that the server does not support, in an attribute selection, a filter or a JSON Patch Query (TMF630 Part 5 and Part 6: "a 501 Not Implemented … MUST be returned"), and for a method that the server does not recognise at all (TMF630 Part 1 §3.3.4).

   A request that does not conform to the specification, or to the product schema of an item, is answered with `400`; a well-formed request that a business rule prevents is answered with `422` (TMF630 Part 1 §3.3.3), on `POST` and on `PATCH`.
   TMF630 Part 8 does not cover response codes; removing or adding a code keeps conformance unless the conformance profile of the parent requires it, e.g. `201` on `POST` in TMF679.
4. **The create and update schemas carry only what a client sets.**
   `_FVO` and `_MVO` leave out the attributes that only the server sets, e.g. results, reasons, errors, prices and the dates of the server.
   The update schema also leaves out what cannot change after the creation, e.g. `instantSyncQualification`.
   Of the states, the update schema admits only those that a client may set, as the TOD of the API names them, e.g. `cancelled`.
5. **Paging.**
   Lists are paged by offset, as the PSI-ICD describes; a PSID API does not declare the cursor parameters `before` and `after`.
6. **The same attribute, the same definition.**
   An attribute that another PSID API of the customer journey carries for the same purpose is added with the same name, type and meaning, e.g. `externalId`, `channel` or the `quantity` of an item.
   Where TM Forum types an attribute as a free string but defines an enumeration for it, e.g. `ItemActionType` for the `action` of an item, the PSID API uses the enumeration.
7. **Shared schemas.**
   A schema has one definition across the PSI APIs; a PSID API imports the PSI definition of a schema it shares with another PSID API, and the build fails when two definitions of one name differ.
   Where two TM Forum parents define one schema name differently, the PSID API whose definition differs gives the schema a name of its own, e.g. `CheckProductOfferingQualificationStateType` instead of `TaskStateType`.
8. **Examples.**
   The generic TM Forum examples should be replaced with PSI-specific ones.
9. **Deviations of every PSID API.**
   The server URL `/psi-api/<api>/v2/`, the removal of the TM Forum hub and listener endpoints in favour of PSID688 Event Management (which keeps its own), and the import of the shared schemas are recorded once, here, and not repeated per API.

### Deviations that need a decision

* A deviation that breaks conformance: one that TMF630 Part 8 does not allow, that deviates from a statement of TMF630 with keyword "MUST", or from what the conformance profile of the parent makes mandatory (e.g. relaxing an attribute that the profile marks mandatory).
* A deviation that applies to several PSID APIs or changes the flow between them.

Such a deviation is decided in a decision record of its own or in a section of the record of the API's deviations.
Every other deviation is listed only.

### Where the deviations are recorded

The deviations of a PSID API are recorded in a decision record of their own, as ADR044 does for PSID648 and ADR047 for PSID679, each with its reason, grouped by kind:

* the operations and endpoints removed;
* the states and the meaning PSI gives them;
* the response codes per operation: removed, added, and the meaning PSI fixes;
* the values restricted by enumerations;
* the attributes removed, added and made mandatory.

Each group says how TMF630 Part 8 classifies it: a reduction (Rule 5), an extension (Rule 6), not covered by Part 8, or breaking conformance (see above).

A later change of the API that adds, changes or removes a deviation updates the record in a new version, in the same pull request as the transformation rule or the overlay.
A row may be marked as a candidate for the TM Forum parent API (TMF630 Part 8 Rule 2); how and by whom candidates are raised with TM Forum is not decided here.

A chapter of the PSI-ICD was considered as the place for the deviations, but was not chosen, because it would separate a deviation from its decision and its reason.

### What this record does not decide

* Whether and when an existing PSID API is aligned with these rules.
* The validation rules that a schema cannot express, and the error codes; they are described in the PSI-ICD, not in the OpenAPI definitions.
* A common definition of the states of the PSID APIs, if necessary; the record of each API gives the meaning of its states.
* The deviations of PSID143, the only PSID API derived from Mplify: TMF630 Part 8 does not apply to it, and whether PSI rules are defined for APIs derived from Mplify is decided when PSI adopts a further one.

## Compliance

* The deviations of each PSID API are recorded in a decision record: PSID648 in ADR044, PSID679 in ADR047.
* A pull request that adds or changes a transformation rule or an overlay of a PSID API updates the decision record of that API; the reviewer checks that the two agree.
* A record of the deviations of one API applies rules from ADR048 (this ADR) by reference and decides only what they leave open.
* An existing PSID API receives its record when it is aligned with these rules.

### Positive Consequences

* The PSID APIs deviate from TM Forum by the same rules.
* Each deviation stands next to its decision and its reason.
* Candidates for TM Forum are collected along the way.
* The new PSID APIs are specified more precisely, e.g. in their response codes and in what a client sets, and the rules give the existing APIs a target to be aligned with. This makes interoperability easier.

### Negative Consequences

* The decision record of an API becomes a living document: it is updated, in a new version, with every change of the deviations of the API.
* Implementers find the deviations in PSI-ADR, not next to the interface in the PSI-ICD.
* The record and the build can drift apart; only review keeps them aligned.
* The existing PSID APIs keep their current form until they are aligned.
* TMF630 Part 8 v5.0.0 is team approved, not yet released; a later version may renumber its rules.
