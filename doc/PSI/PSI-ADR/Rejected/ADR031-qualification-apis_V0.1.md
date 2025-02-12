# Adaptation of TMF645 and TMF679 (V0.1)

* ID: ADR031
* Status: :rejected:
* Deciders: @hop, @cgr, @wr
* Consulted: @cg, @bmu, @ncz
* Date: 2023-11-21
* Version: 0.1
* Category: Architecture

## Context and Problem Statement

Should the TM Forum APIs "TMF645 Service Qualification Management" (SQM) and "TMF679 Product Offering Qualification Management" (POQM) be adapted for (Gov)SatCom use cases as part of PSI?

The APIs in question help in checking the technical and commercial feasibility of product offerings, i.e., of services and bundled product offerings.
The decision revolves around whether these APIs should be integrated as part of the PSID ICDs and if they would need adaptions.

### Configuration, Standardization, and Matchmaking

A PSS should support both experts with precise requirements and inexperienced customers with general requirements, suggesting the need for a robust product configurator within the system.
Since product, service, and resource specifications will be and need to be standardized through JSON schemas, the space of combinations is already reduced and the comparability between results is enhanced.
However, the diversity in configuring bundles can still complicate comparisons with similar offerings, indicating the utility of a CPQ (Configure, Price, Quote) engine.

A configurator for a PSS can consist of three steps:

1. **Inquiry**:
   The customer specifies a mission based on the standardized schemas.
2. **Matchmaking**:
   The matchmaking compares the inquiry against available product offerings in the catalog and configures them according to the customer's specifications.
3. **Configuration and Eligibility**:
   The results are presented to the customer, open parameters to be configured need to be adjusted now.

The more details are specified for the inquiry, the more restrictions can already be applied by the matchmaking.
The less detailed the inquiry is, the more generic are the results by the matchmaking that require additional configurations.
Additionally, for complex products or missions consisting of multiple products, the variety of possible configurations can grow exponentially.
However, only a fraction of these might be technically or commercially eligible, restricting the space of potential combinations.

TMF645 and TMF679 provide checks for eligibility, service compatibility, and availability.
In the standard TM Forum processes, this check happens only after the configuration, however, it could be applied at any time in the process described above.
Thus, the two qualification APIs can complement the Inquiry API and the matchmaking process of a PSS.

Additionally, this is potentially useful for order modification processes, i.e., to verify that a change request is eligible before the order is touched at all.

The core data models of the qualification APIs may not need significant adjustments as they mainly consist of a qualification object, referring to the offer, that provides either a positive verification result of the eligibility or an alternative option.

### Existing Solutions

UCSM has a straightforward relationship between product specifications and offerings, without extensive configuration options.
The adaptation already requires the addition of configuration functionalities to accurately represent data from providers like SES and Inmarsat.
Implementing the eligibility check as part of the ordering process would be required additionally and might result in a lot of unforeseeable effort as this is work-in-progress.

In UCSM, there is a one-to-one (1:1) relationship model, which means:

* Each product is directly linked to a single service or resource. This linkage doesn't differentiate between services or resources through different catalogs but instead uses a type system to categorize them.
* Similarly, each product offering is associated with one product, creating a straightforward, uncomplicated mapping that simplifies the understanding of which offerings are available for each product.
* The only exception to this 1:1 mapping occurs with bundled product offerings, where one bundled product offering can contain multiple individual product offerings.
  Furthermore, a bundled product offering can include several other bundled product offerings, which is where the hierarchical structure comes into play.

This flexibility can accommodate complex product configurations and varied customer needs, which is essential in the dynamic (Gov)SatCom market.

Configuration options like optional add-ons or alternatives (e.g. different terminals for the same internet access service) add complexity but also flexibility, allowing for more tailored solutions to meet specific customer requirements.
However, they also depart from the existing simple mapping structures, necessitating a system capable of handling these intricate relationships and requiring the corresponding logic for SQM and POQM.

REACH on the other hand has a different business model implemented.
If the system was able to answer a request for qualification needs to be clarified.

### Information Gaps

Certain critical information, like satellite capacity or peak transmission quota required for the effective functioning of the TMF645 and TMF679 APIs may not be readily available or shared by CSPs.
This is because some data, such as the exact capacity of a satellite or the reserves allocated for peak transmission times, may be considered proprietary or confidential business information.
Without access to this detailed information, the PSS's ability to accurately determine the technical feasibility and commercial suitability of product offerings could be compromised.

In satellite communications, where service eligibility and product offering feasibility are contingent on a myriad of technical parameters like bandwidth availability, satellite coverage, and ground station compatibility, the absence of complete information can significantly impact the qualification process.
The APIs rely on accurate data to assess if a service can be added to an existing bundled product offering or if an upgrade, like increasing bandwidth, is viable both technically and commercially.

For the APIs to function as intended, there needs to be a clear and reliable source of information.
This source must provide up-to-date and comprehensive data regarding service eligibility and product offering configurations.
If implemented by the PSS, it might require CSPs to disclose more information than they typically would, or it could involve developing a secure and mutually agreeable method of sharing sensitive data to ensure that the qualification APIs can operate effectively.
On the other hand, if the APIs are implemented by the provider, they don't have to disclose any information apart from the above mentioned yes/no/alternative response.

Adding this into the considerations highlights the dependency of the APIs' functionality on external information sources.
It underscores the necessity for collaboration between the providers of satellite services and the entities managing the qualification APIs to ensure that decision-making regarding service qualification and product offering adaptation is well-informed and accurate.
This collaboration might involve contractual agreements, the establishment of standardized data-sharing protocols, or the implementation of secure, real-time data exchange mechanisms to minimize the information gaps and facilitate the smooth operation of the APIs within the (Gov)SatCom domain.
Thus, enabling these APIs might result in a need to involve the CSPs' systems for *every* request, which would result in a lot of over-head traffic.

### Potential for Zero-Touch Automation

The matchmaking process, that based on customer inquiries, can potentially create tailored bundles, reducing manual work for CSPs.
This advanced level of automation within the (Gov)SatCom service provisioning and management aims to minimize or entirely eliminate manual intervention from CSPs.
The concept is particularly relevant in the context of the TMF645 and TMF679 APIs, which could be used to automate the process of service qualification and product offering qualification, i.e., testing the eligibility of the configured bundles automatically.

In the (Gov)SatCom industry, the provisioning of services can be complex due to the intricate technical parameters involved and the need to align with the varying capabilities and availability of satellites and satellite networks.
Zero-Touch Automation seeks to simplify this process by allowing systems to automatically handle SQM and POQM based on predefined rules and dynamic data inputs.
The concept of Zero-Touch Automation in the context of the TMF APIs would ensure that the service qualification and product offering processes are not only more efficient, but also scalable.
As the number of satellite services and potential configurations grows, the ability of the PSS to handle these without manual input becomes increasingly valuable.
It allows for rapid scaling of services, quicker response to market demands, and a more seamless customer experience.
Subsequently, the PSS enables Zero-Touch Partnering, i.e., to create product bundles with products of other companies/organizations seamlessly.

In essence, Zero-Touch Automation represents a significant leap forward in service management automation, promising efficiency and scalability but requiring careful planning, sophisticated technology, and close cooperation between all stakeholders involved.

## Decision Drivers

* Effort versus benefit for the time remaining.
* The potential for these APIs to improve the designed processes.
* The relevance of the APIs to the (Gov)SatCom use case.

## Considered Options

* Adapting TMF645 and TMF679 for (Gov)SatCom use cases.
* Not adapting TMF645 and TMF679 but considering them for future development.

## Decision Outcome

Chosen option: "Not adapting TMF645 and TMF679", because the major implementation effort required does not justify the marginal benefits at the current stage.

## Compliance

N/A

### Positive Consequences

* Avoids unnecessary complexity and effort for the time remaining.
* Keeps focus on essential features required for the time remaining.

### Negative Consequences

* Misses the opportunity to showcase extended capability with TMF645 and TMF679.
* May require additional work if these APIs become relevant in the future.

## Security Considerations

### Adapting TMF645 and TMF679

* Low vulnerability considering the confidentiality aspect, as no major changes are proposed.
  However, letting the provider implement the API might actually increase confidentiality.
* Medium vulnerability considering the integrity aspect, due to the complexity of the adaptation.
* Medium vulnerability considering availability, because additional API calls are required.

### Not Adapting TMF645 and TMF679

* Low vulnerability across confidentiality, integrity, and availability, as the current solution's security posture is maintained.

### CIA Comparison for All Given Options

| Option # | Confidentiality | Integrity | Availability |
| ------ | ------ | ------ | ------ |
| 1 | Low  | Medium | Medium |
| 2 | Low | Low | Low |

Table: CIA table for different options{#tbl:cia_table_options}

## Links

* [TMF645 Service Qualification API User Guide v4.0.1](https://www.tmforum.org/resources/specification/tmf645-service-qualification-api-user-guide-v4-0/)
* [TMF679 Product Offering Qualification API REST Specification R19.0.1](https://www.tmforum.org/resources/standard/tmf679-product-offering-qualification-api-rest-specification-r19-0-0/)
* [Use Case UC002: Browse B2C catalogue and check Fiber technical eligibility v10.0.0](https://projects.tmforum.org/wiki/display/PUB/Use+Case+UC002%3A+Browse+B2C+catalogue+and+check+Fiber+technical+eligibility+v10.0.0)
* [TMF Use Case UC003: Order Capture – Fiber contract v10.0.0](https://projects.tmforum.org/wiki/pages/viewpage.action?pageId=204508817)
* [Use Case UC004: Order Delivery – Fiber contract v10.0.0](https://projects.tmforum.org/wiki/pages/viewpage.action?pageId=204508837)
* [Zero-Touch Partnering](https://www.tmforum.org/resources/reference/gb1027c-zero-touch-partnering-use-cases-v4-0-0/)
