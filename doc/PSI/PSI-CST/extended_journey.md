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

The following scenario is based on a fictional customer responsible for coordinating the emergency response to an emerging wildfire.
As the situation develops, the customer requires different satellite services to support situational awareness and field operations.

> [!NOTE]
> Four providers, **Provider A, B, C, and D**, are already connected to the PSS, and their Product Specifications and Product Offerings are known to the system.
> The providers offer different satellite services, including Earth Observation, AI-based intelligence, and satellite communication (SatCom).
> The initial onboarding of providers and publication of their catalogs are therefore not covered in this scenario.

Following reports of an emerging wildfire, the customer needs to determine the geographical extent of the affected area to support the planning and coordination of emergency response activities.

To obtain this information, the customer uses the PSS to discover and order a **bundled Product Offering** consisting of satellite imagery and an AI-based analysis service.
The satellite imagery provides observation data for the relevant geographical area, while the AI service processes this data to identify and map the areas affected by the fire.

Once the ordered products have been delivered, the customer receives the resulting intelligence and can use it to assess the situation and plan the deployment of emergency personnel.

As the emergency response progresses, the customer decides to deploy personnel to the affected region.
To ensure reliable communication with the field teams, particularly if terrestrial communication infrastructure becomes unavailable or unreliable, the customer identifies an additional need for satellite communication services.

The customer returns to the PSS to discover and order a suitable **SatCom Product Offering**, providing a backup communication channel for the deployed personnel.

This additional requirement arises after the initial intelligence products have been ordered and demonstrates how the customer can progressively procure different satellite services as operational needs evolve.

## Discovery Journey Alternatives

The way customers discover suitable satellite services depends on their requirements, their familiarity with available products, and the capabilities of the Pooling and Sharing System (PSS) they are using.

Some customers may start with a mission objective and rely on the PSS to identify suitable products through matchmaking.
Others may already have specific service requirements or know which products they want to procure, allowing them to follow a more direct discovery process.

A PSS may support one or more of the discovery alternatives described in the following sections.
These alternatives are not mutually exclusive, and customers may use different approaches depending on their needs and the functionality provided by the PSS.

Regardless of the chosen discovery approach, the objective is to identify suitable Product Offerings that can subsequently be qualified, quoted, and ordered through the corresponding business processes.

The following sections illustrate three alternative discovery journeys and a variation in which mission management is performed in an external system.

### Direct Product Catalog Selection

In this journey, the customer discovers available satellite services by browsing the Product Catalog provided by the PSS, without first defining a mission or submitting specific service requirements.

The customer opens the Product Catalog, which presents a list of all available Product Specifications.
Depending on the PSS implementation, the catalog may provide search and filtering capabilities to help customers narrow down the results based on relevant product characteristics, such as service category, geographical coverage, or technical capabilities.

Once the customer selects a Product Specification, the PSS displays the associated Product Offerings.
These represent the concrete products available for procurement, with different characteristics or commercial conditions.

The way the Product Catalog is populated depends on the architecture and capabilities of the PSS.
Two approaches are possible:

- *Internal Product Catalog*: The PSS maintains its own catalog containing the available Product Specifications and Product Offerings.
  Providers must actively register or publish the products they wish to offer through the PSS.
  This may be achieved through PSI APIs, data imports, or manual registration.
- *Virtual Product Catalog*: The PSS dynamically combines product information from trusted external catalogs operated by providers or other PSS implementations.
  Rather than requiring all products to be registered locally, the PSS retrieves and presents the available Product Specifications and Product Offerings from these external sources.

A PSS may also combine both approaches, maintaining an internal catalog while incorporating products from selected external catalogs.
From the customer's perspective, the discovery process remains largely the same, regardless of how the catalog is implemented.

After selecting a Product Offering, the customer can proceed to the subsequent qualification, quotation, or ordering processes, depending on the requirements of the selected product and the capabilities of the participating systems.

### Use-case Template Selection

In this journey, the customer discovers suitable satellite services by selecting from a set of predefined **Use Cases** configured by the Governance of the PSS.
Unlike direct catalog selection, where customers browse the available Product Specifications themselves, this approach guides customers towards suitable products based on their intended use.

The process starts with **Governance defining and configuring Use Cases** based on their domain knowledge and understanding of customer needs.
These Use Cases represent common operational requirements or scenarios for which suitable products have already been identified.

Use Cases may be organized hierarchically, allowing customers to navigate through nested Use Cases or select additional options to further specify their needs.
For each Use Case and its possible options, Governance assigns suitable Product Specifications and Offerings.

When a customer accesses the PSS, they are presented with the available Use Cases.
After selecting a Use Case and any applicable sub-options, the PSS presents a **scoped view of the Product Catalog**, containing the Product Specifications and Offerings associated with that selection by Governance.

Depending on the PSS implementation, customers may be allowed to choose between different Product Offerings associated with the selected Use Case.
For example, a customer might select a higher service tier or an alternative offering from a preferred provider while remaining within the scope of the configured Use Case.

This approach strongly suggests the use of an **internal Product Catalog**, as Governance needs to maintain the relationships between Use Cases, Product Specifications, and Product Offerings.
However, it is also possible for Governance to reference specifications or offerings maintained in trusted external catalogs operated by providers or other PSS implementations.

From the customer's perspective, the discovery process is simplified by relying on the knowledge and preconfigured product selections of Governance, rather than requiring detailed knowledge of the available satellite services.

Once the customer has selected a suitable Product Offering, they can proceed to the subsequent qualification, quotation, or ordering processes, depending on the requirements of the selected product and the capabilities of the participating systems.

### Mission-Based Product Inquiry

In this journey, the customer discovers suitable satellite services by defining a **Mission** that describes their operational objectives and associated service requirements.
Unlike the previous discovery approaches, the customer does not select products from a catalog but describes what they want to achieve and relies on the PSS to identify suitable products through matchmaking.

The journey consists of two phases: **Mission Creation and Management**, followed by **Product Inquiry and Matchmaking**.
Note that both can be done on separate systems, i.e. the mission management can be done using existing, domain-specific software, which only uses the Product Inquiry API to query the PSS.

#### Phase 1: Mission Creation and Management

The customer starts by creating a new Mission or continuing to work on an existing one.
A Mission defines the operational context in which satellite services are required, including information such as the mission's purpose, timeframe, geographical areas, and associated service requirements.

A Mission may contain requirements for multiple product types, potentially combining satellite communication, Earth Observation, and AI-based intelligence services.
These requirements may evolve throughout the mission lifecycle as the customer's operational needs change.

To assist customers in defining their missions, the PSS may provide different levels of guidance:

- **Mission Templates:** Governance may define reusable Mission Templates for common operational scenarios, similar to the Use Cases described in the previous journey. Customers can select a suitable template as a starting point and adapt its parameters and service requirements to their specific needs.
- **AI-Based Mission Companion:** The PSS may provide an AI-based assistant that continuously evaluates changes to the Mission and suggests relevant additions, refinements, or adjustments. Customers may also interact with the assistant through a conversational interface, describing their intentions in natural language and asking it to apply corresponding changes to the Mission.
- **Knowledge-Based Assistance:** In the background, the PSS may use a knowledge graph that relates operational capabilities and functions to suitable product types. This enables the system to translate high-level mission objectives into more concrete service requirements and to identify dependencies between different capabilities.

These assistance mechanisms may be combined, allowing customers to start with a predefined template and progressively refine their Mission with AI-based recommendations.

The resulting Mission contains one or more sets of service requirements that can be used to discover suitable products.

#### Phase 2: Product Inquiry and Matchmaking

Once the customer has defined the relevant service requirements, the PSS creates a **Product Inquiry** for one or multiple product types associated with the Mission.

In contrast to Direct Product Catalog Selection or Use-Case Selection, the Product Inquiry is **not bound to a specific Product Specification or Product Offering**.
Instead, it describes the required product types and their characteristics, derived from the Mission.

Consequently, the PSS does not necessarily need to maintain a local Product Catalog to support this discovery approach.

Depending on its architecture, the PSS may process the inquiry in different ways:

- **Local Matchmaking:** The PSS forwards the Product Inquiry to an internal matchmaking algorithm, which evaluates the requirements against Product Specifications and Product Offerings available through a local or accessible external catalog.
- **Distributed Matchmaking:** The PSS forwards the Product Inquiry to other PSS implementations or provider systems capable of processing it. These systems perform matchmaking against their respective catalogs and return suitable Product Specifications and Product Offerings.
- **Combined Matchmaking:** The PSS may perform an initial matchmaking step locally and distribute the inquiry to selected external systems for further evaluation.

Regardless of the approach, matchmaking evaluates the requested product types and characteristics against the available products and returns a **selection of suitable Product Specifications and Product Offerings** tailored to the inquiry.

The PSS consolidates the results and presents them to the customer, who can review the proposed products and select the offerings that best meet their mission requirements.

If the results do not sufficiently satisfy the customer's needs, they may refine the Mission and repeat the Product Inquiry, allowing the discovery process to evolve iteratively.

Once suitable Product Offerings have been selected, the customer can proceed to the subsequent qualification, quotation, or ordering processes, depending on the requirements of the selected products and the capabilities of the participating systems.

The Mission remains available as the overarching context for the selected and subsequently ordered products, enabling customers to revisit their requirements and procure additional services as their operational needs evolve.

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

### Product Activation

TODO: End of order lifecycle: product gets activated (may be some time after confirmation)

## Iterative Mission Evolution

TODO: Demonstrates how a mission develops over time rather than ending with the first order. For example:

0. Initial Intelligence Request — Ordering an AI-based intelligence product to obtain information relevant to a mission.
1. Mission Refinement — Updating mission requirements based on intelligence results or changing operational needs.
2. Additional SatCom Procurement — Ordering communication services required during a later mission phase.
3. Coordinating Multiple Products — Managing several independently ordered products within the same mission.

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
