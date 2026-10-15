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

### Actors and System Boundaries

The scenarios involve several actors and systems that collaborate to discover, offer, procure, and manage satellite services. Their responsibilities and interactions depend on the business processes and technical capabilities of the participating Pooling and Sharing Systems (PSS).

The following actors and systems are considered throughout the user journeys.

#### Customers

Customers are individuals or organizations seeking satellite services to fulfil their operational needs.
These services may include satellite communications, Earth Observation, intelligence products, or combinations thereof.

Customers interact with a **PSS** to discover suitable products, submit requirements, select product offerings, and place orders.
Depending on the capabilities of the PSS, customers may also manage missions, request qualifications or quotes, and manage their existing products.

Customer interactions typically take place through a graphical user interface, although API-based integration with external customer systems may also be supported.

#### Governance

Governance is responsible for supervising the PSS and controlling the products and services made available to customers.

The specific responsibilities of Governance depend on the implementation and operating model of the PSS. They may include:

- **Product Catalog Management:** Maintaining an internal product catalog, approving published product specifications and offerings, or configuring trusted external catalogs from which product information can be obtained.
- **Use Case Management:** In use-case-oriented PSS implementations, defining reusable use case templates and mapping product specifications to the requirements represented by those use cases.
- **Mission Template Management:** In mission-oriented PSS implementations, defining mission templates that customers can use as starting points for their missions.
- **Order Approval:** In implementations requiring oversight, reviewing and approving customer orders before they are forwarded to the respective providers.

Governance activities may be performed manually or supported by automated workflows within the PSS.

Not every PSS implementation requires all of these activities.

#### Providers

Providers are organizations offering satellite services through a PSS.
They are responsible for defining the products they offer and for fulfilling orders placed by customers.

Providers publish product specifications and, where applicable, product offerings that describe the services available to customers.

Providers typically operate their own IT systems, which may support business processes such as:

- Product Catalog Management, including the publication of Product Specifications and Product Offerings.
- Product Inquiry processing to assess customer requirements and identify suitable services.
- Product Offering Qualification (POQ) to determine whether a requested product configuration can be provided.
- Quote Management to prepare customer-specific commercial offers.
- Product Order Management to receive, process, and fulfil orders.

The degree of integration between a provider and a PSS can vary considerably.

Some providers may implement all relevant PSI APIs, enabling automated interactions throughout the product lifecycle.
Others may support only a subset of the APIs, while some may have no PSI API integration at all.

In the latter cases, interactions may be handled through manual procedures, data imports, or other integration mechanisms provided by the PSS.

The scenarios therefore distinguish between business capabilities and their technical implementation.
A business process may be supported even when the corresponding PSI API is not implemented by the provider.

#### Pooling and Sharing Systems (PSS)

A PSS is a software system that supports the discovery, procurement, and management of satellite services.

The role of a PSS is determined by its position in a particular interaction rather than by its implementation.
This enables federated architectures in which multiple PSS implementations exchange product information and business requests through standardized PSI APIs.
A PSS may therefore act as a customer towards one system while simultaneously acting as a broker or provider towards another.

In the scenarios, the primary PSS acts as a **Broker PSS**, providing customers with access to products from multiple providers.
It may aggregate product catalogs, perform matchmaking, coordinate qualification and quotation processes, and manage product orders and inventories.
Refer to [PSI-TAD] for more information on other PSS modes.

#### External Systems

In addition to the participating PSS implementations, customers and providers may operate external systems that support parts of the overall business process.

Examples include external mission management systems used by customers and provider-operated systems for catalog management, qualification, quotation, order processing, and fulfilment.

These systems may integrate directly with a PSS through supported APIs or interact through other technical or manual mechanisms.

The scenarios will explicitly identify external systems where their involvement is relevant to the demonstrated business process.

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
