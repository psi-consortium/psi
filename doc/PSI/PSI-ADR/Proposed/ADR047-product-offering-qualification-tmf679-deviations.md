# Product Offering Qualification Management: Deviations from TMF679

* ID: ADR047
* Status: :proposed:
* Deciders: Dominik Ogrodnik, @hop, @cgr
* Consulted: Kinga Kocierz
* Date: 2026-11-25
* Version: 0.1
* Category: Design

Technical Story: PSID679 specialises TMF679 v5.0.0 for the PSI qualification process; this record collects its deviations from TMF679 with their reasons, as the spike T-A201020-US001-003 asks (psi issue #85). The API was added in psi PR #100 (issue #83), and TOD-03-05 describes the process (PR #110, issue #87).

## Context and Problem Statement

PSID679 Product Offering Qualification is derived from TM Forum TMF679 Product Offering Qualification Management v5.0.0, which PSI adopts in ADR046.
Used as is, TMF679 does not fit the PSI qualification process described in TOD-03-05 in five areas:

1. **Scope.**
   TMF679 has two tasks: `CheckProductOfferingQualification` checks offerings that the client has selected, `QueryProductOfferingQualification` searches for offerings that can be delivered.
   In PSI the search is done by the Customer Inquiry and the matchmaking.
2. **State semantics.**
   TMF679 defines six states (`TaskStateType`), but its user guide describes only four: `rejected` and `cancelled` have no description, and `terminatedWithError` ("Qualification is not available or partial") mixes a failed process with a negative result; the state of an item is a free string.
   PSI adds no state; it gives the missing meanings, so that a refused qualification, a failed process and a negative result can be told apart, as for the product order (PSID622) and the quote (PSID648, [ADR044](../Accepted/ADR044-quote-management-tmf648-deviations.md)).
3. **Creation of a qualification.**
   TMF679 answers `POST` with `200`, which returns the result "on the fly" and keeps nothing, with `201`, which creates and keeps the qualification, or with `202`, which means asynchronous processing, and the qualification may not exist yet (TMF630 v5.0.0 Part 1 §6.1, §9.2, §9.4).
   In PSI every qualification has to be kept with its identifier: a PSS that passes items on to providers or other PSSs links its own qualification to theirs, and a quote or a product order may refer to the qualification and its items.
   At the same time, a provider with its own qualification engine can often answer at once.
4. **Open values.**
   TMF679 types the result of an item and of the whole qualification, and the action of an item, as free strings.
   In the meeting of 2026-10-05 the traffic light was called an example and the choice was left to the implementer.
   A PSS, however, combines the answers of several providers, for a bundle or along a chain of PSSs, which works only when all of them use the same values; in the review of the meeting records, closing the value set was agreed.
5. **Alignment with the other PSI APIs.**
   The product order (PSID622) and the quote (PSID648) carry attributes that TMF679 lacks (the external identifier, the quantity of an item), and ADR048 sets the rules by which a PSID API removes, restricts, adds and requires attributes and response codes.
   A customer who qualifies, quotes and orders the same product should find the same structures in all three APIs.

How should PSID679 deviate from TMF679 so that it supports the PSI qualification process, lets customers, PSSs and providers interoperate, and stays consistent with PSID622 and PSID648?

## Decision Drivers

* An unambiguous qualification life cycle for the customer, the PSS and the provider.
* Every qualification can be referred to by its identifier, and a provider that can qualify at once can return the result in the response.
* A PSS can read and combine the results of any provider.
* An approach aligned with PSID622 Product Ordering and PSID648 Quote Management, especially for state names and their meaning, response codes, error structures and required attributes.
* As few changes to TM Forum structures as possible, each a reduction or an extension that TMF630 v5.0.0 Part 8 allows for a specialised API, and no new meaning for an existing attribute.

## Considered Options

* Option 1: Use TMF679 v5.0.0 as is
* Option 2: Specialise TMF679 by the rules of ADR048: align it with PSID622 and PSID648 and fix what TMF679 leaves open

## Decision Outcome

Chosen option: "Option 2: Specialise TMF679 by the rules of ADR048", because it improves interoperability: closed values let a PSS combine the answers of several providers, every qualification can be referred to by its identifier, and an item has the same structure as in the quote and the order; every deviation stays within what ADR048 allows.

### Operations and endpoints

| Removed from TMF679 | Reason |
|---|---|
| `QueryProductOfferingQualification`, all its operations | It searches for offerings that can be delivered, which the Customer Inquiry and the matchmaking do. |

Table: Operations and endpoints removed from TMF679 {#tbl:adr047-removed-operations}

`CheckProductOfferingQualification` keeps `POST`, `GET` of the list and by identifier, `PATCH` and `DELETE`.
`DELETE /checkProductOfferingQualification/{id}` stays in the interface but is not mapped to a TOD operation, as `DELETE /productOrder/{id}` in PSID622 and `DELETE /quote/{id}` in PSID648.

Classification: reduction (TMF630 Part 8, Rule 5). The hub and listener endpoints are removed, and the server URL set, as for every PSID API (ADR048, rule 9).

### Qualification states

The state enumeration keeps the six values of TMF679's `TaskStateType` and is named `CheckProductOfferingQualificationStateType`, because PSID687 already defines a `TaskStateType` with other values and the PSI build keeps one definition per schema name (ADR048, rule 7).
The `state` of an item uses the same enumeration (see "Other restricted values").
PSI adds no state; it gives a meaning to the two states that the TMF679 user guide does not describe, and narrows a third:

| State | TMF679 user guide | PSI |
|---|---|---|
| `rejected` | no description | The qualification failed a business validation, of the server itself or of a provider or other PSS to which it passed an item, at once in the `201` answer or later. The reason is given in `terminationError` of the items. |
| `cancelled` | no description | The client no longer needs the qualification: the customer cancels the qualification at the PSS, the PSS at the provider or at the other PSS. Only the client sets it, by `PATCH`. |
| `terminatedWithError` | "Qualification is not available or partial" | Processing cannot be completed, e.g. a provider does not answer. The whole qualification ends so as soon as one of its items does; the reason is given in `terminationError` of that item. |

Table: States whose meaning PSID679 fixes {#tbl:adr047-qualification-states}

TOD-03-05 gives the meaning of every state, who sets it and the transitions.

Classification: the renaming is not covered by TMF630 Part 8 (ADR048, rule 7); the meanings extend the descriptions of TMF679 without changing what the values stand for (TMF630 Part 8, Rules 6 and 8).

### Creation of a qualification: always `201`

* `POST /checkProductOfferingQualification` always creates and keeps the qualification and answers with **`201 Created`**, including its `id`; `200` and `202` are removed (ADR048, rule 1).
* The server decides at runtime whether the `201` already carries the result (TMF630 Part 1 §9.6) and sets: `done` with the results when it could qualify at once, `rejected` when a business validation refused it at once, `terminatedWithError` when it could not process it at all, otherwise a state that is not final, which the client follows by `GET` or through the notifications of PSID688.
  Instead of creating the qualification `rejected`, the server may refuse such a request with `422` and create nothing.
* `instantSyncQualification` keeps its TMF679 meaning: the client's expectation of an immediate result, which also applies when the flag is absent.
  The server meets it where it can, but always answers `201`, as `instantSyncCheck` in PSID687 (TOD-05-04-01, psi #105) and `instantSyncQuote` in PSID648 (ADR044).
* `instantSyncQualification` is not in the update schema, because how a qualification was created cannot be changed afterwards.
* PSI does not specify the `Location` header, as for the other PSI APIs.

The other answers were not chosen:

* `200` and `202` as in TMF679: a `200` leaves nothing that a PSS, a quote or a product order could refer to, and a `202` may come before the qualification exists, while PSI has no monitor resource by which a client could find it.
* `200` when the result is immediate, `201` otherwise: TMF630 Part 1 §9.2 reserves `201` for a task that is kept, and the client would get two codes for the same outcome; PSID687 removed its `200` for the same reason (psi #105).

The TMF679 conformance profile requires only `201` on `POST` and treats `200` as optional; it marks the results and `effectiveQualificationDate` as mandatory only once the qualification has been completed, so a `201` in state `acknowledged` meets it.

Classification: not covered by TMF630 Part 8; the TMF679 conformance profile is kept (ADR048, rule 1).

### Response codes

| Operation | Removed from TMF679 | Added | Meaning fixed by PSI |
|---|---|---|---|
| `POST /checkProductOfferingQualification` | `200`, `202`, `404`, `409` | `400`, `401`, `403`, `422`, `501`, `503` | `201` in every case (see "Creation of a qualification: always `201`"). `422` = the request failed a business validation at once and no qualification was created. An offering that is not in the catalog is not answered with `404` but refused (`422` or `rejected`). The client does not set the identifier, so a new qualification cannot conflict with an existing one (`409`). |
| `GET /checkProductOfferingQualification` | `404` | – | An empty list is answered with `200`. |
| `PATCH /checkProductOfferingQualification/{id}` | – | `422` | `422` = a well-formed change that a business rule prevents, e.g. an item with an offering that is not in the catalog (ADR048, rule 3). |
| `DELETE /checkProductOfferingQualification/{id}` | `501` | – | A server that does not offer the removal answers `405`. |

Table: Response codes of PSID679 compared with TMF679 {#tbl:adr047-response-codes}

`GET /checkProductOfferingQualification` keeps the `fields`, `offset` and `limit` parameters of TMF679 (paging by offset, ADR048, rule 5); filtering by attributes is optional (TMF630 Part 1).

Classification: not covered by TMF630 Part 8; the TMF679 conformance profile, which requires only `201` on `POST`, is kept (ADR048, rule 3).

### Result vocabulary

`qualificationItemResult` and `qualificationResult` use one closed vocabulary:

| Value | Meaning | Alternatives |
|---|---|---|
| `green` | The item can be delivered as requested (configuration, place, date). | none |
| `yellow` | It cannot be fully confirmed that the item can be delivered as requested. | may be proposed |
| `red` | The item cannot be delivered as requested. | may be proposed |

Table: Result vocabulary of PSID679 {#tbl:adr047-result-vocabulary}

The result of the whole qualification, and of an item that contains other items, follows from the results of its items:

* `red` as soon as one item is `red`, even while other items have no result yet;
* otherwise, once every item has its result, `yellow` if at least one item is `yellow`;
* `green` if every item is `green`.

A `yellow` item does not yet decide the result of the whole: the server waits for the other items, since an item that is still open may end `red`, which makes the whole `red`.

Alternatives are carried in `alternateProductOfferingProposal`.
How a marketplace shows the result, and whether a `red` or `yellow` result prevents a quote or an order for the item, is up to the implementation.

The other vocabularies were not chosen:

* A free string as in TMF679: a PSS could not combine the results of providers that chose different values.
* `qualified` / `alternate` / `unqualified`, the item prose of TMF679: `alternate` is not a result of its own: it says that alternatives are proposed, which `alternateProductOfferingProposal` already shows, and both a `yellow` and a `red` item may carry them.
* `qualified` / `unqualified`: cannot say that the provider is not sure yet, e.g. while a feasibility check is pending, the `yellow` of Mplify LSO Sonata (`serviceabilityConfidence`).

Classification: reduction, an open string restricted by an enumeration (TMF630 Part 8, Rule 5).

### Other restricted values

| Attribute | TMF679 | PSID679 | Reason |
|---|---|---|---|
| `action` of an item | free string ("add, modify, remove, etc.") | `ItemActionType`: `add`, `modify`, `delete`, `noChange` | The TMF679 OAS defines `ItemActionType` but uses it only in `RelatedOrderItem`, not for `action`; PSID622 and PSID648 use it for the same attribute (ADR048, rule 6). |
| `state` of an item | free string | `CheckProductOfferingQualificationStateType` | See "Qualification states". |
| `state` in the update schema | `TaskStateType`, all six states | `CheckProductOfferingQualificationUpdateStateType`: `cancelled` | `cancelled` is the only state a client may set (see "Qualification states"; ADR048, rule 4). |

Table: Other values restricted in PSID679 {#tbl:adr047-restricted-values}

Classification: reduction (TMF630 Part 8, Rule 5); ADR048, rules 4 and 6.

### Removed attributes

| Attribute | Removed from | Reason |
|---|---|---|
| `promotion` | the item, its create and update schemas, `AlternateProductOfferingProposal` | The qualification carries no price; the price belongs to the quote. `Product.productPrice` stays, as in the other PSI APIs. |
| `instantSyncQualification` | the update schema only; it stays in the create schema and the qualification resource | How a qualification was created cannot be changed afterwards (see "Creation of a qualification: always `201`"). |
| The attributes that only the server sets: the results, the reasons, the alternatives, the termination error, the dates of the provider | the create and update schemas | A client must not set them (ADR048, rule 4). |

Table: Attributes removed from TMF679 {#tbl:adr047-removed-attributes}

Classification: reduction (TMF630 Part 8, Rule 5); ADR048, rule 4.

### Added attributes

| Attribute | Added to | Type | Content | Aligned with |
|---|---|---|---|---|
| `externalId` | `CheckProductOfferingQualification`, its create and update schemas | array of `ExternalIdentifier` | The identifiers of the qualification at other parties, e.g. the identifier that a PSS gave to the qualification it passes on. | `externalId` in PSID622 and PSID648 |
| `quantity` | `CheckProductOfferingQualificationItem`, its create and update schemas | integer | The number of units of the offering to qualify. | `quantity` of the items in PSID622 and PSID648 |

Table: Attributes added to PSID679 {#tbl:adr047-added-attributes}

Classification: extension (TMF630 Part 8, Rule 6); ADR048, rule 6.

### Attributes made mandatory

| Schema | Attribute | TMF679 | PSID679 | Reason |
|---|---|---|---|---|
| `CheckProductOfferingQualification` | `id` | optional (from `Entity`) | **required** | Every qualification is kept: the client and a PSS refer to it by its identifier, and a quote or a product order may refer to it (see "Creation of a qualification: always `201`"); as `Quote.id` in PSID648 (ADR048, rule 2). |
| `CheckProductOfferingQualification_FVO` | `checkProductOfferingQualificationItem` | optional | **required**, at least one item | A qualification without an item checks nothing. |
| `CheckProductOfferingQualificationItem_FVO` | `id`, `action`, `product` | optional | **required** | An item is identified within the qualification, states what is to be done, and describes the product to be qualified: for `add` its configuration, for `modify` and `delete` the existing product. |

Table: Attributes made mandatory in PSID679 {#tbl:adr047-required-attributes}

An item names the offering to qualify in its own `productOffering`, not in `product.productOffering`.
TOD-03-05 requires it as a business rule (`422` when it is missing), not as a required attribute, as PSID622 and PSID648 leave the offering of an item optional in their schemas.
Classification: extension (TMF630 Part 8, Rule 6); ADR048, rule 2 for `id`.

### Shared schemas, descriptions and examples

* The schemas that PSID679 shares with the other PSI APIs take their PSI definition (ADR048, rules 7 and 9).
* The descriptions of the attributes stay as in TMF679, except where an attribute now refers to a PSI enumeration (see "Result vocabulary" and "Other restricted values") and where PSI states its behaviour: `instantSyncQualification` (see "Creation of a qualification: always `201`") and `provideOnlyAvailable`, which in PSI limits only the proposed alternatives to available offerings, while every requested item is returned with its result, also a `red` one.
* `info.description` stays as in TMF679 except where it describes `QueryProductOfferingQualification`, the `200` answer, a request for a product specification without an offering, and the notification of events.
* The TM Forum examples are removed as they do not apply to PSI.

Classification: reduction and extension (TMF630 Part 8, Rules 5, 6 and 10); ADR048, rules 7, 8 and 9.

## Compliance

* The deviations are implemented by the `transformProductOfferingQualification` rules in `build.gradle` and by the extension `doc/PSI/PSI-ICD/open-apis/extensions/PSID679-Product_Offering_Qualification-v5.0.0.oas.json`, which holds the qualification paths, `CheckProductOfferingQualificationStateType`, `QualificationItemResultType` and `QualificationResultType`.
* The generated `PSID679-Product_Offering_Qualification-v5.0.0.oas.json` and its PSI-ICD pages show the operations, the states, `201` on `POST`, the response codes, the result vocabulary and other modifications as described above.
* TOD-03-05 and REQ-03-05 use the state names, the result vocabulary and the response codes of this record; TOD-03-05 describes when the result and `effectiveQualificationDate` are present, and the notifications on the event topic *qualification* (TOD-01-02).
* Reviewers of a later PSID679 change check that a new TMF679 version taken over by the transformation keeps the deviations recorded here.

### Positive Consequences

* Interoperability improves: the values, the meanings of the states and the attributes that TMF679 leaves open are fixed, so customers, PSSs and providers read each other's qualifications the same way.
* Results are comparable across providers and PSSs and can be combined for bundles; validation rejects unknown values.
* Every qualification can be referred to by its identifier, and a provider that can qualify at once returns the result in the first response; the client always gets `201`.
* A customer finds the same item structure in qualification, quote and order.
* The same result values as Mplify LSO Sonata, which eases the convergence decided in ADR041.

### Negative Consequences

* A TMF679 client that uses `QueryProductOfferingQualification`, relies on `200` or `202`, sends values outside the enumerations, or omits `id`, `action` or `product` in an item is not compatible with PSID679 without changes; after a `201` it reads the state in the response: the result is either already there or comes later, in a notification (PSID688) or by `GET`.
* A provider that follows the item prose of TMF679 (`qualified`, `alternate`, `unqualified`) or the examples of its user guide (`orange`) fails validation.
* `terminatedWithError` has a narrower meaning than in TMF679, and `rejected` and `cancelled` a meaning that TMF679 does not give, which has to be pointed out to implementers.
* The TMF679 conformance test kit also runs its tests of `QueryProductOfferingQualification`, so PSID679 does not pass it officially, although the conformance profile requires only one of the two tasks and TMF630 Part 8 allows the removal (Rule 5).

## Pros and Cons of the Options

### Option 1: Use TMF679 v5.0.0 as is

* Good, because a provider that implements TMF679 needs no change, and PSID679 would pass the TMF679 conformance test kit.
* Bad, because a PSS cannot combine the results of providers that chose different values.
* Bad, because a `200` keeps no qualification that a PSS, a quote or a product order could refer to, and a `202` may come before the qualification exists.
* Bad, because `QueryProductOfferingQualification` duplicates the Customer Inquiry, and the states do not tell a refusal, a failed process and a negative result apart.
* Bad, because it differs from what PSI decided for its other APIs, e.g. notifications through PSID688 Event Management instead of a `/hub` per API.

### Option 2: Specialise TMF679 by the rules of ADR048

* Good, because it improves interoperability: closed values, required identifiers, and the same item structure as in the quote and the order.
* Good, because the results are comparable across providers and PSSs and can be combined for bundles.
* Bad, because a client written for TMF679 needs changes (see the negative consequences).
* Bad, because the deviations have to be kept when a new TMF679 version is taken over.
