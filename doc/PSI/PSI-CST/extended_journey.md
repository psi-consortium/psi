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

In the scenarios, the primary PSS acts as a **PSS**, providing customers with access to products from multiple providers.
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

Once the customer has discovered and selected suitable Product Offerings, the process continues with product qualification and ordering.

The following steps are **independent of the discovery journey** chosen by the customer.
Regardless of whether the products were identified through Direct Product Catalog Selection, Use-Case Selection, or Mission-Based Product Inquiry, the subsequent processes follow the same general principles.

> [!NOTE]
> If the PSS has assembled a bundled Product Offering containing products from multiple providers, it is responsible for decomposing the bundle into provider-specific requests throughout the POQ, Quote, and Order processes.
> Each provider receives only the information relevant to its own products, without visibility into the products or commercial arrangements of other providers.
> The PSS coordinates the individual interactions and consolidates their results to present a unified process to the customer.

### Product Offering Qualification

The Product Offering Qualification (POQ) process allows the customer to verify whether a selected Product Offering can be provided under the requested conditions before placing an order.

Following the TM Forum Product Offering Qualification Management approach (TMF679), the PSS creates a POQ request containing the selected Product Offering and the relevant requirements, such as the requested configuration, geographical location, or timeframe.
Depending on the PSS implementation, the qualification may be initiated **automatically** after product discovery or **explicitly by the customer**.

The PSS forwards the POQ request to the responsible provider, which evaluates whether the requested product can be delivered under the specified conditions.
This may involve checking technical feasibility, resource availability, geographical coverage, or other product-specific constraints.

The provider may process the request **asynchronously**, particularly when qualification requires complex feasibility checks or manual intervention.
In this case, the PSS tracks the qualification status and receives or retrieves the result once processing has been completed.

The result indicates whether the requested product is eligible for ordering and may include additional information, such as qualification conditions, restrictions, or reasons why the request cannot be fulfilled.
If the requested product cannot be provided as specified, the provider may also **propose alternative Product Offerings** that partially or fully satisfy the customer's requirements.

The PSS presents the qualification result and any proposed alternatives to the customer.
If the qualification is positive, the customer can proceed with the subsequent Quote or Order process, as applicable.
If the qualification is negative, the customer may adjust their requirements, select an alternative Product Offering, or repeat the qualification process.

### Quote Negotiation

The Quote Negotiation process allows the customer to request a customized commercial offer for a selected Product Offering before placing an order.

Following the TM Forum Quote Management approach (TMF648), this process is **optional**.
Customers may order a Product Offering directly under its published terms and conditions without requesting a separate Quote.
However, a Quote may be required when the customer requests specific configurations, pricing, contractual conditions, or other deviations from the standard offering.

The customer initiates the process through the PSS, which creates a Quote request containing the selected Product Offering, the requested configuration, and any additional requirements.
The PSS forwards the request to the responsible provider.

The provider evaluates the request and prepares a customized Quote, including the applicable pricing, terms and conditions, and validity period.
Similar to the POQ process, Quote processing may be **asynchronous**, particularly when commercial negotiations, internal approvals, or additional feasibility checks are required.

The provider returns the Quote to the PSS, which presents it to the customer.
Depending on the provider's response, the customer may accept the proposed terms or request adjustments, resulting in further negotiation between the customer and provider through the PSS.

Once the customer accepts a Quote, the agreed conditions can be used to proceed with the Product Order process.
If the customer rejects the Quote or no agreement is reached, they may revise their requirements, select another Product Offering, or abandon the request.

### Product Order Creation

The Product Ordering process allows the customer to place an order for one or more selected Product Offerings.

Following the TM Forum Product Ordering Management approach (TMF622), the process may be initiated directly after product discovery, following a successful Product Offering Qualification (POQ), or after the acceptance of a negotiated Quote.
The Product Order contains the selected Product Offerings, their requested configurations, and relevant information such as the customer, requested delivery dates, and applicable commercial conditions.

The customer submits the order through the PSS, which validates the request and, depending on the PSS implementation, may require approval by Governance before forwarding it to the responsible provider.

The provider receives the Product Order and begins processing it.
This may include validating the order details, checking contractual conditions, reserving resources, and initiating the necessary fulfilment activities.

Once the provider accepts the order, it creates a corresponding Product entry in its Product Inventory using the TM Forum Product Inventory Management API (TMF637), with the initial status `created`.
This entry represents the ordered product and allows its lifecycle to be tracked independently of the Product Order.
The PSS can retrieve or receive the inventory information to maintain visibility of the customer's ordered products.

Order processing may be asynchronous, particularly when the requested products require additional preparation, resource allocation, or manual intervention.
The provider communicates changes to the order status through the TMF622 API, allowing the PSS to track its progress.
These updates may be delivered through event notifications (TMF688) or retrieved by the PSS.

The PSS presents the current order status to the customer, providing visibility into the processing progress.
Depending on the provider's workflow, the order may transition through different states, such as acknowledged, in progress, completed, or rejected.
For orders containing multiple items, the status of individual order items may also be tracked.

### Product Activation

The Product Activation process marks the transition from an ordered product to an operational product, making the underlying services available to the customer.

Depending on the product and the agreed activation date, activation may take place immediately after order acceptance or at a later point in time.
The provider is responsible for preparing and activating the product, which may involve provisioning resources, configuring services, or performing other product-specific activities.

Once the product has been successfully activated, the provider updates the corresponding Product Inventory entry through TMF637, changing its status from `created` to `active`.
The PSS retrieves or receives this update and presents the product's operational status to the customer.

At this point, the underlying services are being provided and the customer can begin using the ordered product.
The provider then marks the associated Product Order as `completed` through TMF622, indicating that the order has been successfully fulfilled.

The activated products remain visible in the customer's Product Inventory and can subsequently be managed through the post-order lifecycle processes described in the following sections.

## Iterative Mission Evolution

A customer's operational requirements may evolve throughout the lifecycle of a mission.
New information, changing circumstances, or additional operational objectives may lead to the need for further satellite services, even when previously ordered products are already active.

As illustrated in the [Baseline Scenario](#baseline-scenario), the customer initially orders Earth Observation imagery and an AI-based intelligence service to identify the areas affected by an emerging wildfire.
Based on the intelligence obtained, the customer decides to deploy emergency personnel to the affected region and requires additional satellite communication (SatCom) services as a backup communication channel.

The customer can discover these additional products using any of the previously described [Discovery Journey Alternatives](#discovery-journey-alternatives).

- **Direct Product Catalog Selection:** The customer browses the Product Catalog to identify and select a suitable SatCom Product Offering.
- **Use-Case Selection:** The customer selects a new Use Case based on the intelligence obtained.
  This may also involve navigating to another branch or selecting additional options within a previously chosen Use Case.
- **Mission-Based Product Inquiry:** The customer reopens the existing Mission and extends its requirements to include satellite communication services.
  The PSS may again assist through Mission Templates or the AI-based Mission Companion, potentially using the intelligence obtained from the previously ordered products to suggest suitable additional capabilities.
  The updated requirements lead to a new Product Inquiry and matchmaking process.

The Mission-Based approach offers the additional advantage of maintaining a common context for all products associated with the Mission.
Previously ordered products, newly identified requirements, and additional products can be managed together, providing the customer with a consolidated view of the services supporting their operational objectives.

Regardless of the chosen discovery approach, the subsequent Product Qualification and Ordering processes remain unchanged.
The newly selected Product Offerings may undergo POQ and Quote Negotiation where required, followed by Product Ordering and Activation as described in the preceding sections.

Additional products are ordered and activated independently of the existing ones, which may remain operational throughout the process.
The customer can repeat this cycle as often as necessary, progressively extending the services available to support the Mission.

## Post-Order Lifecycle Management

Once a product has been ordered and activated, the customer may continue to interact with the PSS throughout the product's lifecycle.

The PSS provides capabilities to view and manage existing products, request modifications to their configurations, and access usage and billing information.
Eventually, products may also be terminated when they are no longer required.

These processes are independent of the original discovery and ordering journey and may be performed at any point during the operational lifetime of a product.

The following sections describe the relevant lifecycle management processes and how the PSS interacts with providers through the corresponding PSI APIs to maintain a consistent view of the customer's products.

### Product Inventory

The Product Inventory provides an overview of all products that have been ordered by the customer, including their configurations, lifecycle states, and associated information.

As described previously, the provider creates a Product Inventory entry with the initial status `created` once an order has been accepted using TMF637.
The entry is subsequently updated throughout the product's lifecycle, for example changing its status to `active` when the underlying service has been activated.

The PSS retrieves or receives the Product Inventory information from the respective providers and presents a consolidated view to the customer.
This allows customers to browse their products, including products that have been ordered but are not yet active, products currently in use, and products that have already been terminated.

For each product, the customer can inspect relevant information, such as its Product Offering, configuration, provider, validity period, and current lifecycle status.
Depending on the PSS implementation, additional details about the underlying services or resources may also be available.

If a product was ordered in the context of a Mission, it is also displayed within the Mission Management interface.
This allows customers to view all products associated with a Mission in one place, including their current states, and to understand which capabilities are already available or planned.

The Product Inventory also serves as a starting point for subsequent lifecycle management processes.
Customers may select an existing product to request changes to its configuration, access related usage or billing information, or initiate its termination.

### Product Change Orders

The Product Change Order process allows customers to modify an existing product, such as changing its configuration, service characteristics, or subscription conditions.

The customer starts by selecting an existing product from their Product Inventory in the PSS.
Depending on the product and the capabilities of the PSS, the customer may select from a list of alternative Product Offerings associated with the same Product Specification, for example to upgrade to a higher service tier or adjust the product's characteristics.

If the desired modification is not covered by an existing Product Offering, the customer may initiate a [Product Offering Qualification](#product-offering-qualification) and/or [Quote Negotiation](#quote-negotiation) process, as described in the preceding sections, to determine whether the requested change is feasible and under which conditions it can be provided.

Once the customer has selected the desired modification, the PSS creates a new Product Order using TMF622.
Unlike an order for a new product, the corresponding Product Order Item has its `action` set to `modify` instead of `add` and references the existing Product Inventory entry that is to be changed.

The PSS forwards the change order to the responsible provider, which validates and processes it following the same general principles as a new Product Order.
The provider communicates order status updates to the PSS, allowing the customer to track the progress of the requested modification.

Once the provider accepts the change order, they update the corresponding Product Inventory entry through TMF637 to reflect the agreed modifications and their applicable lifecycle state.
Depending on the nature of the change, the actual modification of the underlying service may take effect immediately or at a later point in time.

The PSS retrieves or receives the updated inventory information and presents the modified product to the customer.
If the product is associated with a Mission, the updated configuration is also reflected in the Mission Management interface.

### Usage and Billing

The Usage and Billing processes allow customers to access billing information and, where applicable, monitor the consumption of their ordered products.
Following the TM Forum Customer Bill Management approach (TMF678), **billing information is created and maintained by the provider** and made available to the customer through the PSS.

The customer can access the billing section of the PSS to view bills associated with their products.
The PSS retrieves the relevant billing information from the respective providers using TMF678 and presents it in a consolidated view.
Depending on the provider and the product, customers may inspect billing periods, charges, applicable taxes, and payment-related information.

For products where consumption is measured, the provider may additionally support the TM Forum Usage Management API (TMF635).
This optional API allows the PSS to retrieve usage records associated with the customer's products, such as consumed communication capacity, transferred data volumes, or other product-specific usage metrics.

The PSS presents the available usage information to the customer, allowing them to review their consumption and better understand the charges associated with their products.
The availability and level of detail of usage information depend on the product characteristics and the capabilities of the provider.
Products with fixed pricing or without measurable consumption may not require usage reporting.

### Product Termination and De-allocation

The Product Termination and De-allocation process marks the end of a product's operational lifecycle and ensures that the resources associated with it are released.

A product may be terminated in two ways:

- **Scheduled Termination:** The product reaches the end of its agreed lifetime, as specified during the ordering process.
  The provider automatically initiates the termination and de-allocation process.
- **Customer-Initiated Termination:** The customer selects an existing product from their Product Inventory and requests its cancellation.
  Following the TM Forum Product Ordering Management approach (TMF622), the PSS creates a new Product Order with the corresponding Order Item's `action` set to `delete`, referencing the existing product.
  The provider processes the cancellation request and communicates its status through TMF622.

In both cases, the provider is responsible for performing the necessary de-allocation activities, such as deactivating the underlying services, releasing reserved resources, and terminating any associated provisioning.

The provider also updates the corresponding Product Inventory entry through TMF637 to reflect the product's termination.
The PSS retrieves or receives the updated status and presents it to the customer.

Terminated products remain accessible in the customer's Product Inventory as historical records, allowing them to review previously ordered products and their associated information.
If a product was associated with a Mission, its termination is also reflected in the Mission Management interface.
