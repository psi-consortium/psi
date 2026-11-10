# Scope of a Product Modification

* ID: ADR045
* Status: :proposed:
* Deciders: Kinga Kocierz
* Consulted: Dominik Ogrodnik, @hop, @cgr
* Date: 2026-11-10
* Version: 0.1
* Category: Design

Technical Story: Changes to an existing product in Quote Management (TOD-03-06, psi issue #62) and Product Offering Qualification (PSID679)

## Context and Problem Statement

An item with action `modify` changes an existing product; it can appear in a product offering qualification (PSID679) and in a quote (PSID648).
TM Forum does not restrict what such an item may change.

What may a `modify` item change: only the configuration of the product, or also its product offering, and may the new offering belong to another provider?

## Decision Drivers

* One rule for qualification and quote.
* Only the provider that holds the product instance can change it.
* Upgrades and downgrades should not need a termination and a new product.

## Considered Options

* Option 1: Configuration only
* Option 2: Configuration or product offering of the same provider and the same product specification
* Option 3: Also another product specification of the same provider
* Option 4: Any change, including another provider

## Decision Outcome

Chosen option: "Option 2", because it covers upgrades and downgrades while keeping the product instance, its type and its provider.

A `modify` item may change:

* the configuration of the product, within the bounds of its product offering;
* its product offering, to another one of the **same provider** based on the **same product specification**.

A change of the product specification or of the provider is a termination of the existing product (`delete`) and a new product (`add`).
An item that breaks this rule fails the business validation: `422` when detected at once, otherwise the state `rejected`.

## Compliance

* TOD-03-06 describes this rule.
* PSID679 applies the same rule to `modify` items.
* The OpenAPI definitions are not changed.

### Positive Consequences

* Upgrades and downgrades keep the product and its identifier.
* Qualification and quote apply the same rule.

### Negative Consequences

* A change of product type or provider needs a termination and a new product and, for another provider, usually a new customer inquiry.
