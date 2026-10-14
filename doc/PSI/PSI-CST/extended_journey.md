=begin

# Extended User Journeys: End-to-End Satellite Service Delivery

> The heading has to be included in the document that includes this document.

=end

The preceding Case Study and User Story demonstrate the fundamental interactions between Customers, Pooling and Sharing Systems (PSS), and Resource Providers.
They illustrate how satellite communication services can be published, discovered, matched against customer requirements, ordered, and provisioned through interoperable PSI APIs.

This chapter extends these scenarios to illustrate a broader range of satellite services, customer journeys with alternative workflows, and business processes supported by the PSI APIs.
These services may be provided independently or combined to address more complex customer needs.

A customer may, for example, initially request intelligence about a geographical area, subsequently obtain additional observation data, and later procure satellite communication services to support operational activities.
The PSS therefore supports not only the procurement of individual products but also the progressive composition and management of services throughout an evolving mission.

The scenarios are organized into five parts:

1. **Service Ecosystem, Actors and Common Concepts** — The satellite service categories, participating systems, and their responsibilities.
2. **Alternative Discovery Journeys** — Different approaches to identifying suitable products, including externally managed missions.
3. **Product Qualification and Ordering** — The common processes connecting product selection with order placement.
4. **Iterative Mission Evolution** — The progressive acquisition and modification of services as mission requirements evolve.
5. **Post-Order Lifecycle Management** — Product inventory, product changes, billing, and service termination.

Each scenario will identify the participating actors, the main interactions, the expected outcomes, and the relevant PSI APIs.

```plantuml
repeat
fork
    :Catalog Selection;
fork again
    :Use-Case Templates;
fork again
    :Mission Management;
    :Product Inquiry;
end fork
note left: Discovery\nJourney\nAlternatives
:Product Offering Qualification;
split
split again
    :Quote Negotiation;
end split
:Product Order & Fulfilment;
:Post-Order Lifecycle; <<continuous>>
```

![Extended User Journey Overview.](../common/pixel.png){#fig:ext-user-journey-overview}

The objective is to illustrate how standardized interfaces enable interoperable satellite service delivery across different business processes, service categories, and system architectures.

## Service Ecosystem, Actors and Common Concepts

TODO: Establishes the shared context for all subsequent journeys.

### Actors and System Boundaries

TODO: Customer, Broker PSS, Provider PSS, Resource Provider, and external mission management systems.

### Satellite Service Categories

TODO: SatCom, Earth Observation, AI-based intelligence, and composite services.

### Common Service Lifecycle

TODO: Overview of the stages from discovery to termination.

### Baseline Scenario

TODO: A representative mission that progressively requires intelligence, observation, and communications. Could be skipped and only laid out in the "Iterative Mission Evolution" chapter.

## Alternative Discovery Journeys

TODO: Defines how customers identify products before entering the common ordering process.

### Direct Product Catalog Selection

TODO: The customer browses available offerings and selects a known product without matchmaking.

### Use-case Template Selection

TODO: The customer is selecting and tailoring a use-case template that is provided by the governance.

### Mission-Based Product Inquiry

TODO: The customer defines a mission, specifies requirements, and receives matching product offerings.


### Product Inquiry With External Mission

TODO: A variation in which an external system manages the mission and integrates with the PSS through APIs.

## Product Qualification and Ordering

TODO: A common process following any of the three discovery journeys.

### Product Offering Qualification

TODO: Qualification of a selected offering against specific customer requirements and provider capabilities.

### Quote Negotiation

TODO: Requesting, preparing, presenting, and accepting a customer-specific commercial offer.

### Product Order Creation

TODO: Translating a selected and, where necessary, qualified offering into a product order.

### Order Processing and Confirmation

TODO: Provider-side validation, processing, and communication of order status.

## Iterative Mission Evolution

TODO: Demonstrates how a mission develops over time rather than ending with the first order. For example:

1. Initial Intelligence Request — Ordering an AI-based intelligence product to obtain information relevant to a mission.
2. Mission Refinement — Updating mission requirements based on intelligence results or changing operational needs.
3. Additional Earth Observation Services — Acquiring complementary observation data or analytical services.
4. Subsequent SatCom Procurement — Ordering communication services required during a later mission phase.
5. Coordinating Multiple Products — Managing several independently ordered products within the same mission.

## Post-Order Lifecycle Management

TODO: Extends the customer journey beyond successful order confirmation.

### Product Inventory

TODO: Retrieving and tracking the products assigned to a customer, including their lifecycle states.

### Product Change Orders

TODO: Requesting and processing modifications to existing products.

### Usage and Billing

TODO: Accessing usage information, charges, and billing-related records.

### Product Termination and De-allocation

TODO: Ending product usage and releasing associated resources.
