# Quote Management: Deviations from TMF648

* ID: ADR044
* Status: :proposed:
* Deciders: Kinga Kocierz
* Consulted: Dominik Ogrodnik, @hop, @cgr
* Date: 2026-11-10
* Version: 0.1
* Category: Design

Technical Story: PSID648 Quote Management API (psi issue #66, PR #75) and its task description TOD-03-06 (psi issue #62)

## Context and Problem Statement

PSID648 Quote Management is derived from TM Forum TMF648 Quote Management v5.0.0, as all PSI APIs are derived from the TM Forum Open APIs ([ADR006](../Accepted/ADR006-use-tmforum-openapis-and-sid_V1.0.md)).
Used as is, TMF648 does not fit the PSI quote process described in TOD-03-06 in four respects:

1. **State semantics.**
   In TMF648, `rejected` is the state of a quote the customer has rejected, and `declined` is also defined.
   In PSI, a quote that fails the business validation of the PSS or the provider has to be told apart from a quote that the customer turns down, as for the product order (PSID622), where `rejected` means a failed validation.
2. **Synchronous and asynchronous quotes.**
   The description of `instantSyncQuote` in TMF648 says that a synchronous quote is answered with `200` and an asynchronous one with `201`, which contradicts the `201` and `202` responses TMF648 defines for `POST /quote`.
   In PSI a quote can be processed synchronously or asynchronously (TOD-03-06), and the server may answer asynchronously even when the client asked for a synchronous quote, so the client needs a single, predictable response code in both cases.
3. **Reasons for a rejection.**
   TMF648 has no attribute in which the provider can say why a quote was rejected, nor which quote items failed.
   A decomposed bundle quote is rejected as a whole when one of its parts fails, so the failed items have to be named.
4. **Alignment with the other PSI ordering APIs.**
   The product order (PSID622) and the product offering qualification (PSID679) already carry attributes that TMF648 lacks (for example the sales channel) and make attributes required that TMF648 leaves optional.
   A customer who qualifies, quotes and orders the same product should find the same structures in all three APIs.

How should PSID648 deviate from TMF648 so that it supports the PSI quote process and stays consistent with PSID622 and PSID679?

## Decision Drivers

* An unambiguous quote life cycle for the customer, the PSS and the provider.
* Consistency with PSID622 Product Ordering and PSID679 Product Offering Qualification: same state names for the same meaning, same error structure, same required attributes.
* As few changes to TM Forum structures as possible.

## Considered Options

* Option 1: Use TMF648 v5.0.0 as is
* Option 2: Align PSID648 with PSID622 and PSID679 (state model, response codes, added and required attributes)
* Option 3: Keep the TMF648 states and carry the PSI meaning in additional attributes (for example a sub-state or a reason code)

## Decision Outcome

Chosen option: "Option 2: Align PSID648 with PSID622 and PSID679", because it is the only option in which a state name means the same in quote, order and qualification, and it needs no new structures beyond those PSID622 already uses.

### 1. Quote states

`QuoteStateType` keeps the TMF648 values except `declined`, which is replaced by `refused`.

| State | Set by | Meaning in PSI | Final |
|---|---|---|---|
| `acknowledged` | PSS / provider | The quote request has been received and passed the first checks. | no |
| `inProgress` | PSS / provider | The quote is being priced. | no |
| `pending` | PSS / provider | As in TMF648: the quote waits for an action, for example further information. | no |
| `approved` | PSS / provider | The quote is priced and can be accepted or refused by the customer. | no |
| `accepted` | customer | The customer accepts an approved quote. An expired quote cannot be accepted. | yes |
| `refused` | customer | The customer refuses an approved quote (`rejected` in TMF648). | yes |
| `rejected` | PSS / provider | The quote failed the business validation, also when only one part of a decomposed bundle quote failed. The reason is given in `quoteErrorMessage`. | yes |
| `cancelled` | customer | The customer withdraws the quote, also an approved one, as long as it is neither accepted nor refused. | yes |

Table: Quote states in PSID648 {#tbl:adr044-quote-states}

`declined` is not used.
The provider never cancels a quote; it rejects it.

### 2. `instantSyncQuote` and the response to `POST /quote`

* `instantSyncQuote` stays a free boolean. It states what the client *expects*: with `true`, the priced quote in the response (`approved`, or `rejected` if it fails the validation); with `false`, a quote in a state that is not final (`acknowledged` or `inProgress`) that is priced afterwards.
* The server may still answer asynchronously when it cannot price the quote at once.
* `POST /quote` answers with **`201 Created` in both cases**. The client learns from the state of the returned quote which kind of answer it got. `202` is removed.

### 3. Response codes

| Operation | Removed from TMF648 | Added | Meaning fixed by PSI |
|---|---|---|---|
| `POST /quote` | `202`, `404`, `409` | `422` | `422` = the quote failed the business validation at once. |
| `GET /quote` | `404` | – | An empty list is answered with `200`. |
| `PATCH /quote/{id}` | – | `422` | `409` = the update is not allowed in the current state of the quote (including the final states and accepting an expired quote); `422` = the update failed the business validation at once. |
| `DELETE /quote/{id}` | `501` | – | A server that does not offer the deletion answers `405`, as in PSID679. |

Table: Response codes of PSID648 compared with TMF648 {#tbl:adr044-response-codes}

`GET /quote` supports only the `fields`, `offset` and `limit` parameters, as `GET /productOfferingQualification` in PSID679; the cursor parameters (`before`, `after`), `sort` and `filter` are removed.
`DELETE /quote/{id}` stays in the interface but is not mapped to a TOD operation, as `DELETE /productOrder/{id}` in PSID622.

### 4. Added attributes

| Attribute | Added to | Type | Content | Aligned with |
|---|---|---|---|---|
| `quoteErrorMessage` | `Quote`, `Quote_FVO`, `Quote_MVO` | array of `QuoteErrorMessage` (`_FVO`, `_MVO` respectively) | The errors that caused a state change of the quote, in particular the reason why it was `rejected`. | `productOrderErrorMessage` in PSID622 |
| `channel` | `Quote`, `Quote_FVO` | `ChannelRef` | The sales channel through which the quote was requested. | `channel` in PSID622 and PSID679 |
| `channel` | `Quote_MVO` | `ChannelRef_MVO` | As above, in the update view. | PSID622 |

Table: Attributes added to PSID648 {#tbl:adr044-added-attributes}

**`QuoteErrorMessage`** is a new schema based on `Extensible`, modelled on `ProductOrderErrorMessage` of TMF622.
It contains:

* `code` – the error code
* `reason` – an explanation of the reason for the error
* `message` – more details and the corrective actions
* `status` – an error code extension, such as `sys-ABC-2001`
* `referenceError` – the URI of documentation describing the error
* `timestamp` – when the error happened (`date-time`)
* `quoteItem` – an array of `QuoteItemRef` (taken from TMF622: `quoteId`, `quoteHref`, `quoteItemId`) naming the quote items that failed

It is a schema of its own rather than a subtype of the shared `ErrorMessage`, because the `ErrorMessage` discriminator is shared with PSID622 and lists only `ProductOrderErrorMessage`.

**`ChannelRef`** is taken unchanged from TMF622 and is an `EntityRef`: `id` (required), `href`, `name` and `@referredType`.

### 5. Attributes made mandatory

| Schema | Attribute | TMF648 | PSID648 | Reason |
|---|---|---|---|---|
| `Quote` | `id` | optional (from `Entity`) | **required** | Every accepted `POST /quote` creates a quote instance, see below. |
| `QuoteItem_FVO` | `product` | optional | **required** | A quote item always describes the product to be quoted (its characteristics, place and related parties), for `add` as well as for `modify` and `delete`, as in TOD-03-06-01. |

Table: Attributes made mandatory in PSID648 {#tbl:adr044-required-attributes}

**Why `Quote.id` is mandatory.**
In PSI, a `POST /quote` that passes the syntactic checks always creates a quote instance on the server, whatever its outcome: it is answered with `201 Created` whether the quote is priced at once, will be priced later, or has already been `rejected` by the business validation.
The quote therefore always exists as a resource, and the client needs its identifier to read it (`GET /quote/{id}`), to accept, refuse or cancel it (`PATCH /quote/{id}`), and to relate the notifications it receives to it.
The PSS also needs the identifier to relate the quotes it requested from the providers, for example the parts of a decomposed bundle quote, to the customer's quote.
A quote without an `id` could not be followed up, so the provider always returns the quote with its identifier, as PSID679 does for a product offering qualification.

## Compliance

* The deviations are implemented by the `transformQuote` rules in `build.gradle` and by the extension `doc/PSI/PSI-ICD/open-apis/extensions/PSID648-Quote-v5.0.0.oas.json`, which holds the quote paths, `QuoteStateType` and `QuoteErrorMessage`.
* The generated `PSID648-Quote-v5.0.0.oas.json` and its PSI-ICD pages show the states, the response codes and the added and required attributes as described above.
* TOD-03-06 and REQ-03-06 use the state names and response codes of this record.
* Reviewers of a later PSID648 change check that a new TMF648 version taken over by the transformation keeps sections 1 to 5.

### Positive Consequences

* A state name means the same in quote, product order and product offering qualification.
* A rejected quote always says why, and which items failed, in the same structure as a rejected product order.
* A client always gets `201` and an identified quote, and can follow it up.

### Negative Consequences

* A TMF648 client that uses `declined`, expects `202`, or omits `product` in a quote item is not compatible with PSID648 without changes.
* `rejected` has a different meaning than in TMF648, which has to be pointed out to implementers coming from TM Forum.
