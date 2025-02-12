# Implementation of (Gov)SatCom Hardware Logistics Process (V0.1)

* ID: ADR033
* Status: :rejected:
* Deciders: @hop, @cg, @cgr
* Consulted: @vmr, @rsperb, Jemma Gregory
* Date: 2023-11-29
* Version: 0.1
* Category: Design

## Context and Problem Statement

We need to decide if and how to implement a logistics process for SatCom-related hardware, considering that TM Forum lacks a proper process description for this.
The current system design does not have a process for logistics of hardware.
In addition, the ability to book 'off the shelf' services that automate hardware selection and shipment qualification is required to improve system acceptance and reduce time to market.

The logistics process for SatCom-related hardware requires a systematic approach to address the unique challenges of shipping physical products, which may range from small components to large equipment.
The process must be capable of integrating various factors, such as customer preferences, legal restrictions, shipment options, and cost calculations.
Considering the TM Forum APIs and the need for a standardized approach, the process to be implemented should include the following steps and considerations:

1. **Product Offering Selection**: Customers choose product offerings that meet their needs.
   If the offering includes hardware, the system must prompt customers to specify delivery details.
2. **Delivery Specification and Restrictions**: The system should allow customers to specify delivery locations.
   It must be capable of identifying and flagging issues like embargoes or legal restrictions (e.g., Wassenaar Arrangement) that could affect delivery feasibility.
3. **Shipping Options**: Given the variability in the size and weight of (Gov)SatCom hardware, customers should be presented with multiple shipping options.
   These options could range from standard postal services to specialized freight if the hardware requires it.
4. **Cost and Time Calculations**: The system needs to accurately estimate or calculate shipping costs and delivery times.
   For standard logistics, this would involve integrating with courier and freight services to obtain real-time quotes based on the cargo class.
5. **Logistics Partner Integration**: The system should have the capability to interface with logistics partners for instances where shipping costs cannot be calculated immediately, necessitating quotes or interventions from logistics companies.
6. **Virtual Content Delivery**: For products without physical components, the process needs to account for digital delivery.
6. **Shipment Documentation**: Adequate documentation must be generated and managed within the system to comply with international shipping laws, customs regulations, and logistics partner requirements.

A PSS should allow for booking off-the-shelf services/products, i.e., hardware offerings should allow for automatic qualification and selection in the system, promoting zero-touch automation.
For instance, if a customer selects a service that includes a satellite terminal, the system should automatically provide shipping options and costs.

## Decision Drivers

* Absence of an immediate candidate requirement for a comprehensive logistics process.
* The need for a more automated system that can handle logistics efficiently.
* The necessity for a reliable and flexible logistics process in the context of (Gov)SatCom hardware.
* The variable cost implications based on delivery locations and specifications.
* Legal and practical shipping restrictions.
* The availability and status of TM Forum APIs related to shipping and their suitability for the required logistics process.

### TM Forum API Integration

The state of the available TM Forum Open APIs varies widely, as some APIs are still under development and some are already obsolete.
Additionally, there is no data model available in the SID to account for logistics properly, although the available TM Forum documents and Open API definitions hint that this is needed and will be implemented in the future.

TM Forum Open APIs investigated are:

* **TMF700 Shipping Order Management API**: For creating and managing shipping orders.
  Currently in the pre-production stage, but seems to be fully developed.
* **TMF711 Shipment Management API**: Though still in development, it could be used for overarching management of shipment lifecycles.
  Currently in the pre-production stage, only the `swagger` file is available.
* **TMF712 Shipment Qualification API**: To qualify shipments pre-order, ensuring eligibility and compliance with restrictions.
  Teasered in the description of TMF700, but no further information available.
  This especially hints at a dedicated data model for the logistics.
* **TMF684 Shipment Tracking API**: For providing customers with the ability to track their shipments.
* **TMF663 Shopping Cart Management API**: To manage the shopping cart experience, including integration with logistics for real-time cost calculations and delivery options.

### Data Requirements

Assuming the PSS shall be able to at least make an educated guess for the shipping costs, it needs an API to get the corresponding information for a product offering containing physical goods from the CSP.
For example, the following data would allow for a minimal setup that can even be used for booking of-the-shelf products.
Note that the following tables are only an example that is not meant to be implemented as is, but a basis for further discussions and investigations.

*Cargo Classes* can be freely defined by the provider.
Each terminal is then assigned to a class, the PSS doesn't really have to know why.

|Cargo Class|Description|
|---|---|
|A|< 20kg, EU-UNCLA|
|B|< 50kg, EU-UNCLA|
|C|> 50kg or EU-CLA|

Table: Example for different cargo class options.{#tbl:cargo_class}

The CSP could generate a list similar to the one below for the PSS to account for shipment.
This might require the CSP to validate the qualification of the shipment.

|Destination Country|Cargo Class|Shipment Possible|Shipping Restrictions|Shipping Cost|Shipping Time (min)|Shipping Time (max)|
|---|---|---|---|---|---|---|
|DE|A|YES||10,00 €|3 Days|5 Days|
|DE|B|YES||100,00 €|1 Week|2 Weeks|
|DE|C|YES||1.000,00 €|1 Week|2 Weeks|
|DZ|A|LIMITED|ECCN necessary|500,00 €|2 Weeks|4 Weeks|
|DZ|B|LIMITED|ECCN necessary|2.000,00 €|3 Weeks|2 Months|
|DZ|C|NO|Shipment to EU only||||
|RU|A|NO|Sanctions||||
|RU|B|NO|Sanctions||||
|RU|C|NO|Sanctions||||

Table: Example for a logistics table including estimated prices and shipping duration.{#tbl:shipping_options}

This list will be much longer in reality.
If a country/class combination is not assigned, the PSS shall inform the user that shipment has to be checked manually.

Note that this is connected to candidate requirements stating the availability of services and products in a given region, especially P&S_016.

Additional data required for the process might include:

* Detailed product dimensions and weights. (Note that this could be simplified by the cargo class as discussed above.)
* Integration with external databases for international shipping restrictions and customs information.
* Customer delivery preferences.

Note that, if the CSP defines a list similar to the one in {@tbl:shipping_options}, they would also be responsible to keep it up-to-date.

## Considered Options

* Implementing delivery as a bookable service within the existing service/product catalog.
* Developing a new data model and corresponding APIs tailored to the logistics process.
* Postponing the logistics process implementation due to the absence of an immediate candidate requirement.

## Decision Outcome

Chosen option: "Postponing the logistics process implementation," because there is no immediate candidate requirement. The remaining time of the project needs to focus on the implemented APIs and the demonstration thereof.

Delivery of hardware can be omitted when assuming that the customer has their own, compatible hardware in place already.

## Compliance

N/A

### Positive Consequences

* Enables the system to focus on immediate needs and capabilities, such as virtual product and service delivery.
* Sets the stage for future logistics process implementation when/if the requirement becomes concrete.

### Negative Consequences

* Delays the development of a logistics process for physical products, which may become necessary in the future.

## Pros and Cons of the Options

### Postponing the Logistics Process

* Good, because TM Forum might have developed it by the time that it is needed, allowing PSI to  implement an established standard.
* Good, because it avoids investment in a process that is not yet required.
* Bad, because it might lead to a future rush in logistics process development if the need arises unexpectedly.

### Developing a New Data Model and APIs

* Good, because it would provide a tailored solution for logistics.
* Good, because available TM Forum documents hint at the need of such a model.
* Neutral, because TM Forum has not yet published a data model for the SID.
  This could be a way to take over leadership, although this is not a core competence and goal of the project.
* Bad, because it requires significant resources without an immediate requirement.
* Bad, because there is only little information available on the actual processes.

### Using the Service Catalog for Delivery

* Good, because it integrates with the existing infrastructure.
* Bad, because available TM Forum documents hint at the need of a model independent of the catalogs.
* Bad, because it may complicate the service catalog with logistics-specific options.

## Security Considerations

The security implications of postponing the physical logistics process are minimal.

## Implications for the Scope

Shipment restrictions should already be considered when making a product or product offering available.

## Links

* [TMF700](https://www.tmforum.org/resources/standard/tmf700-shipping-order-api-user-guide-v4-0-0/) Shipping Order Management API
* [TMF684](https://www.tmforum.org/resources/specification/tmf684-shipment-tracking-api-rest-specification-r18-0-0/) Shipment Tracking API
* [TMF663](https://www.tmforum.org/resources/specification/tmf663-shopping-cart-management-api-user-guide-v5-0-0/) Shopping Cart Management
