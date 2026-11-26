# Adoption of TMF679 and TMF648

* ID: ADR046
* Status: :proposed:
* Deciders: Dominik Ogrodnik, Kinga Kocierz, @hop, @cgr
* Date: 2026-11-25
* Version: 0.1
* Category: Architecture

Technical Story: PSID679 Product Offering Qualification API (psi issue #83, PR #100) with its task description TOD-03-05 (psi issue #87, PR #110); PSID648 Quote Management API (psi issue #66, PR #75) with its task description TOD-03-06 (psi issue #62, PR #102)

## Context and Problem Statement

PSI has previously decided against a qualification API:

* [ADR021](../Accepted/ADR021-inventory-stock-api_V1.0.md) de-scoped the TMF679 Product Offering Qualification API, because eligibility "should already been checked as part of the inquiry process", and named the check of change requests for eligibility as a reason to consider it again.
* [ADR031](../Deprecated/ADR031-qualification-apis_V0.1.md) then chose "Not adapting TMF645 and TMF679", because "the major implementation effort required does not justify the marginal benefits at the current stage", and named the cost of that choice: it "may require additional work if these APIs become relevant in the future".

For a quote, PSI has no decision at all: the Customer Inquiry returns non-binding prices, and a Request-for-Quote returns a tailored offering (PSI-TAD).

CCN #4 now considers the introduction of both of them:

* a TMF679-based qualification: A201000 "Offering Validation for Readiness", A201010 "TMF679 based Offering Validation Engine" and A201020 "Designing and Testing POQ and Quote API";
* a TMF648-based quote: A202000 "Quote Management", A202010 and A202020 "TMF648 Quote Mock-Up Implementation".

Next to these two APIs, CCN #4 names a Product Offering Availability & Pricing Discovery API, e.g. "Co-design PSI-aligned POQ and Availability/Pricing Discovery APIs (or equivalent)" (A201020).

In the meetings of 2026-09-30 and 2026-10-05 the consortium agreed on TMF679 v5.0.0 and TMF648 v5.0.0 (preview version), and that ADR031 is marked as deprecated (ACTION-2026-10-05-005).
It described the steps of the extended end-to-end customer journey as answering three different questions: the inquiry finds the candidates for ordering, the Product Offering Qualification (POQ) says whether one candidate can be delivered, and the Quote gives its final price and terms.
POQ and Quote were confirmed as optional from the API's point of view.

## Decision Drivers

* The scope of CCN #4 (WS2 Smart-Bundle Orchestration)
* One purpose per step of the customer journey; no two APIs give the same answer
* A provider can answer with its own capacity data, rules and prices without disclosing them to the PSS
* Interoperability between PSSs and providers, including providers that already implement TM Forum
* As few new APIs defined, and as few deviations from TM Forum, as possible

## Considered Options

* Option 1: Keep ADR021 and ADR031: no qualification and no quote API; eligibility and prices come from the Customer Inquiry and Request-for-Quote
* Option 2: Extend the Customer Inquiry with a qualification and a quote step, as the inquiry type "Change Request" of the PSI-TAD does for changes
* Option 3: Adopt TMF679 v5.0.0 and TMF648 v5.0.0 as the PSI profiles PSID679 and PSID648
* Option 4: As option 3, with a separate availability and pricing discovery API before the qualification, as CCN #4 names it

## Decision Outcome

Chosen option: "Option 3: Adopt TMF679 v5.0.0 and TMF648 v5.0.0 as the PSI profiles PSID679 and PSID648", because it gives each question of the journey one standard API, keeps the data of the provider with the provider, and is what CCN #4 asks for and the consortium agreed on; option 4 would add an API whose questions the other steps already answer (see "Availability and pricing discovery").

TMF645 Service Qualification Management stays out of PSI: the customer buys products, so the qualification is done on product offerings, and whether the services behind them can be delivered is the provider's own concern.
Mplify (formerly MEF) LSO Sonata serves as a reference where it settles a question that TM Forum leaves open ([ADR041](../Accepted/ADR041-mef-convergence.md)).

### Customer Inquiry, POQ and Quote

The three steps answer different questions, so none of them replaces another:

* The Customer Inquiry matches a request, which may be vague, against the offerings of many providers, in a distributed matchmaking if needed (ADR015), and returns ranked candidates that may match only partially (ADR022).
  Its result says which offerings could fit, not whether a provider can deliver a given configuration at a given place and date.
* The POQ asks the provider of a selected offering whether it can deliver it as requested.
  It carries the full configuration and covers the ordering of a new offering, taken from the inquiry results or from the catalog, and the change or termination of an active product alike.
  The provider answers with its own data and rules, e.g. the capacity of a satellite, which ADR031 found "may be considered proprietary or confidential business information", and discloses only the result.
* The Quote gives the binding price and terms of a selected offering: the prices in the catalog and in the inquiry results are list prices only, whereas the Quote can take into consideration the price modifiers relevant to the customer.

### Place in the customer journey

* The steps follow each other as Customer Inquiry or product catalog query, POQ, Quote and Product Order.
  POQ and Quote are optional and independent of each other: a customer may order without either, qualify without quoting, or quote without qualifying.
  Where a POQ or a Quote was done, the Product Order refers to it (`productOfferingQualification` and `quote` on the order, `productOfferingQualificationItem` and `quoteItem` on its items).
* A provider or the governance of a PSS may require a POQ, a Quote or both before it accepts an order, as a policy of its own, not as a rule of the interface.
  For example, a provider requires a POQ for an offering whose delivery depends on capacity, coverage or terminal compatibility, which ADR031 names as the conditions of eligibility in satellite communications, or a Quote where the price depends on the configuration or on the customer.
  A governance may require a POQ for certain groups of users or missions.
* An offering has one provider, so the PSS passes the POQ or the Quote of an offering on to that provider only, never to several.
  Whether a customer compares the offerings of several providers with several items in one POQ or Quote, or with several POQs or Quotes, is up to the implementation; the PSS passes each item on to the provider of its offering.
* A change or a termination of an active product does not go through the Customer Inquiry.
  The customer orders it with `action: modify` or `action: delete`, optionally after a POQ and a Quote for it, e.g. to learn whether the change can be delivered or what the termination cost is; ADR021 anticipated this use ("It may be considered again to check change requests for eligibility").
* The customer sends the POQ and the Quote, or the PSS sends them on its behalf, e.g. right after an inquiry; which of the two, and under which conditions, is up to the implementation of the PSS.
* For a bundle composed by the PSS from the offerings of several providers, the PSS sends one POQ and one Quote per provider, each with the parts of that provider, and combines the answers.
  How the PSS decomposes the bundle and combines the answers is not part of the PSID679 and PSID648 interfaces; it belongs to what CCN #4 calls the distributed CPQ (A204010, A204020).

### Interfaces and calls between the systems

* POQ and Quote are offered on three interfaces: the PSS-USR I/F, where the customer is the client of the PSS; the PSS-PVD I/F, where the PSS is the client of the provider; and the PSS-PSS I/F, where a PSS is the client of another PSS.
  They are not offered on the PSS-GOV I/F.
* The provider of the offering always acts as the server.
  The PSS implements the same APIs in the server role towards its customer, but calls the provider's APIs as the client, just as it does for the stock check (TOD-05-04).
* This differs from how a PSS passes on a product order or an inquiry: as an event through PSID688 Event Management (ADR014, ADR015), after which the provider reads and updates the order in the PSS.
* The reasons:
  * the provider answers a POQ or a Quote itself, often at once (`instantSyncQualification`, `instantSyncQuote`);
  * a POQ and a Quote of a provider are its own records, which it keeps in its own system, while the PSS keeps its own and links them;
  * one API serves the customer at the PSS, the PSS at the provider and a PSS at another PSS, so a PSS in a chain behaves the same way towards each side.
* A POQ and a Quote are one-to-one between two systems, each with its own identifier; the PSS stores the association.
  ADR015 rejected this pattern ("Distinct inquiry per system") for the Customer Inquiry, but ADR015 is about communication that "is not 1-to-1", so there is no contradiction.
* The provider informs the PSS about the changes of its POQ or Quote by notifications (PSID688), and the PSS informs its customer in the same way; the provider does not call the qualification or quote endpoints of the PSS.

### Availability and pricing discovery

PSI adopts no separate discovery API, such as the Mplify LSO Sonata Product Offering Availability and Pricing Discovery API:

* ADR041 already keeps that API apart from the Customer Inquiry, because its adaptation "does not promise any additional value for PSI".
* The questions it answers are answered by the steps above: which offerings fit a need (Customer Inquiry, product catalog), whether a selected configuration can be delivered at the requested place and date (POQ, with alternatives), what it costs as a list price (product catalog, inquiry results), and what it costs bindingly (Quote).
* The "Availability/Pricing Discovery APIs (or equivalent)" of CCN #4 are therefore realised by the Customer Inquiry, the product catalog, the POQ and the Quote.

### Earlier decisions and the earlier scope of PSI

* ADR031 is deprecated by this record; TMF645 stays out of PSI.
* The de-scoping of the qualification API in ADR021 is reversed; the rest of ADR021 stays.
* The inquiry type "Change Request" of the PSI-TAD, in which the customer checks "the feasibility and potentially new price with the provider", is replaced by a POQ, which answers the feasibility, and a Quote, which answers the new price; the change itself is then ordered with `action: modify` (see "Place in the customer journey"; ACTION-2026-10-05-002).
* Request-for-Quote (RFQ) and Invitation-to-Tender (ITT) stay inquiry types.
  Despite its name, an RFQ asks a provider for a tailored offering, which comes back as an offering (PSI-TAD), not as a quote; that offering can then be qualified and quoted like any other.
* How PSID648 and PSID679 deviate from TMF648 and TMF679 is recorded in ADR044 and ADR047.

### What this record does not decide

* How a customer learns that a provider or a governance requires a POQ or a Quote before an order.

## Compliance

* The PSI-ICD contains PSID679 and PSID648, and no separate availability or pricing discovery API.
* TOD-03-05 and TOD-03-06 describe POQ and Quote as optional steps between the inquiry and the order, offered on the PSS-USR, PSS-PVD and PSS-PSS interfaces, with the PSS as the client of the provider.
* The PSI-TAD describes the check of a change of an active product as a POQ and a Quote before the order, and no longer lists "Change Request" as an inquiry type (ACTION-2026-10-05-002).
* ADR031 is deprecated with a reference to this record.

### Positive Consequences

* Each question of the journey has one standard API: what could fit (inquiry), whether it can be delivered (POQ), and what it costs (Quote).
* A provider keeps its capacity data, rules and prices to itself and answers only the request at hand.
* The feasibility and the price of a change or a termination of an active product can be checked before it is ordered.
* PSI needs no further API for availability and pricing.

### Negative Consequences

* A PSS passes a POQ or a Quote on differently from a product order or an inquiry, so an implementer handles both patterns.
* Every POQ and Quote reaches the system of the provider; the overhead that ADR031 foresaw ("involve the CSPs' systems for *every* request") applies, limited by both steps being optional.
* Before a quote, a price for a configuration at a given place and date is available only as the non-binding list price of the catalog or the inquiry results.
* TMF648 v5.0.0 is still a preview; PSID648 has to follow its changes until it is released.

## Pros and Cons of the Options

### Option 1: Keep ADR021 and ADR031

* Good, because it needs no new API and no effort.
* Bad, because the inquiry says which offerings could fit, but not whether a provider can deliver a given configuration at a given place and date, nor at which binding price; ADR031 itself found that eligibility depends on data that the PSS does not have.
* Bad, because the feasibility and the price of a change of an active product can be checked only with the inquiry type "Change Request" of the PSI-TAD, which the Customer Inquiry of the PSI-ICD does not implement.

### Option 2: Extend the Customer Inquiry

* Good, because it adds no API.
* Bad, because the inquiry asks many providers which offerings could fit and ranks their answers (ADR015, ADR022), while a qualification and a quote ask one provider about one offering; the inquiry would gain a second purpose and further states, which every implementer of the inquiry has to handle, even one that never qualifies or quotes.
* Bad, because no TM Forum or Mplify API covers such an inquiry, so every PSS and provider implements a PSI-specific extension, while TMF679 and TMF648 exist for exactly these steps.

### Option 3: Adopt TMF679 and TMF648

* Good, because each step has one standard API and one purpose.
* Good, because providers that implement TM Forum need fewer changes; the deviations are recorded (ADR044, ADR047).
* Bad, because the consortium maintains two more API profiles; implementers bear no cost, as both APIs are optional.
* Bad, because TMF648 v5.0.0 is still a preview.

### Option 4: As option 3, with a separate discovery API

* Good, because it follows the wording of CCN #4 literally.
* Good, because it would give a non-binding price for a configuration at a given place and date before the quote.
* Bad, because it adds an API between the inquiry and the POQ whose questions the inquiry, the catalog, the POQ and the Quote already answer.
* Bad, because ADR041 found that its adaptation "does not promise any additional value for PSI": it addresses a single seller, does not allow vague requests, and does not rank its results.

## Security Considerations

### Option 1: Keep ADR021 and ADR031

* Low vulnerability considering the confidentiality aspect, as nothing changes.
* Low vulnerability considering the integrity aspect, as nothing changes.
* Low vulnerability considering availability, as nothing changes.

### Option 2: Extend the Customer Inquiry

* Medium vulnerability considering the confidentiality aspect, because the inquiry is one record that the PSS synchronises with every provider it involves, and only a filter keeps each of them from seeing the others (ADR015: "It may be necessary to filter the submitted data to not disclose the other participants"); eligibility answers and binding prices in that record would depend on the same filter.
* Medium vulnerability considering the integrity aspect, because the life cycle of the inquiry becomes more complex.
* Low vulnerability considering availability, because no further interface is added.

### Option 3: Adopt TMF679 and TMF648

* Low vulnerability considering the confidentiality aspect, because the provider computes the answer itself and discloses only the result, provided that the PSS passes each provider only its own part.
* Medium vulnerability considering the integrity aspect, because the identifiers of a POQ or a Quote are associated across systems.
* Medium vulnerability considering availability, because each POQ and Quote needs a call to the system of the provider.

### Option 4: As option 3, with a separate discovery API

* Low vulnerability considering the confidentiality aspect, as for option 3.
* Medium vulnerability considering the integrity aspect, as for option 3, with one more API.
* Medium vulnerability considering availability, because one more call to the system of the provider is needed before the POQ.

### CIA Comparison for All Given Options

| Option # | Confidentiality | Integrity | Availability |
| ------ | ------ | ------ | ------ |
| 1 | Low | Low | Low |
| 2 | Medium | Medium | Low |
| 3 | Low | Medium | Medium |
| 4 | Low | Medium | Medium |

Table: CIA table for different options {#tbl:adr046-cia-table-options}
