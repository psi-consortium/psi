# Overbooking (V1.0)

* ID: ADR024
* Status: :superseded: by [Overbooking (V2.0)](../Accepted/ADR024-overbooking_V2.0.md) in [PSI-ADR]
* Deciders: @wr @hop @cg @cgr @ncz @rsperb
* Date: 2023-05-31
* Version: 1.0
* Category: Design

## Context and Problem Statement

The term *overbooking* is used ambiguously depending on where the user is coming from, i.e., it has a different meaning in the candidate requirements than in the context of (Gov)SatCom service provision.
This ambiguity leads to misunderstandings when discussing the topics and defining the scenarios in which it may occur.

This decision record shall collect the different scenarios where any party might use the term *overbooking* and define a common wording for this project.

## Decision Outcome

### Overbooking

Overbooking refers a provider offering more instances of a resource, service, or product (collectively, any *item* in a catalog or inventory) than there are available in stock.
This is not a problem until the items actually need to be provided, i.e., a system can allow overbooking as a process, but cannot work properly once it reaches the state of being overbooked.

For example, an airline can sell more tickets for a plane than there are seats available.
This is not a problem until more passengers want to board onto the plane than there are actual seats.
As long as not all passengers show up to the flight, overbooking as a process is allowed and leads to no problems.
However, as soon as the plane is *overbooked*, i.e., more passengers showed up than there are seats on the plane, the system fails and mitigation for the overbooking needs to be initiated, e.g., some passengers need to get alternative flights offered.

Overbooking is a business decision to take a calculated risk, i.e., not being able to provide all items offered.
The main reason to allow overbooking is to maximize the return on investment by maximizing the usage of the available capacity of an item.
There are different strategies to implement overbooking, which heavily depend on whether an item is *dedicated* or *non-dedicated*, i.e., a dedicated item is assigned to one and only one customer while non-dedicated items can be shared among multiple users.

Dedicated items, like the seats in the plane in the example above, can be subject to overselling, while non-dedicated items can be subject to oversubscription.

Note that *overbooking* can refer as a general term to both cases.
The terms overselling and overbooking are sometimes used synonymously, but will be used with different meanings in this project to prevent ambiguities and misunderstandings in discussions.
That is, overbooking is used to describe the general process, while overselling and oversubscription are specific use cases, subsequently elaborated.

Additionally, the candidate requirements have two cases which are referred to as *overbooking*.
First the case of regional pools, where it is assumed that resources need to be duplicated to be included in multiple pools.
TM Forum and the model developed based on it during this project do not need this duplication, it is even prohibited.
That is, an item can be assigned to multiple pools at the same time without the need to be duplicated.
Therefore, the item cannot be overbooked due to multiple pools pointing at the same instance.
Secondly, the term overbooking is used synonymously with *resource contention situation*, which collects the two cases discussed below.
Additionally, the candidate requirements distinguish between responsibility on the governance level for overbooking across multiple users & user organizations, and responsibility on the community level, which is quite restricted and managed by the community itself, e.g., by a community administrative board.

#### Overselling

Overselling refers to the case when a provider allows more reservations and/or sells more instances of dedicated items than are available in stock.
This can be done for multiple reasons:

1. **There is a significant number of reservations cancelled.** Thus, the provider wants to compensate for this rate to maximize the usage of the available capacity and makes a business decision to allow overbooking. A common scenario are hotel reservations of which a significant part is cancelled regularly, e.g., due to sickness, shifted travel plans, etc. If the number of cancelled reservations is high enough, there might be a business decision to compensate this by allowing more reservations than rooms are available.
2. **There is a significant number of booked items not used.** Then, the provider can decide to maximize the usage anyway and makes a business decision to allow overbooking, i.e., to sell more capacity than there is available. An example are airlines that sell more tickets for a flight than there are seats and assume that some passengers do not show up.
3. **There is an outage or system failure leading to a reduced capacity.** If the system was near or at capacity, the system is de-facto overbooked. This can either lead to a degraded quality of services or the stop of the service. An example is a train where a carriage is missing. The passengers can find a seat somewhere else on the train (the train is not overbooked), need to stand (degraded quality of service) or can't get on the train (no service provision).
4. When selling physical items that are constantly delivered to a warehouse are having a peak in demand or problems in the supply chain (fail of "just-in-time"-processes), there is a (temporary) shortage of items. De-facto, the items are overbooked.

There are at least four strategies to mitigate the risk of low usage of available capacity and therefore reduced revenue:

1. The provider can add a *reservation fee* to their items. This is a fee that applies when making a reservation. It can be a significant percentage of the total cost of a product, e.g., 50% of the fixed costs or the first monthly rate. Then, it can be taken into account when the service is booked definitely. If the reservation is cancelled, the fee is not paid back.
2. The provider can add a *cancellation fee* to their reservations or bookings. This is similar to the reservation fee, but will be charged if the reservation or booking is cancelled.
3. The provider can allow for overselling as discussed above.
4. The provider can decide to not allow reservations. Then, the contractual notice periods apply.

If a dedicated item can be subject to a reservation, i.e., there is an additional step implemented between selecting a solution and booking that solution, and if there is a significant number of reservations cancelled, overbooking might be a reasonable solution to maximize the return on investment.
A reservation of an item can be envisioned to be applicable to dedicated connectivity services and for special hardware.
If leasing or renting hardware is part of a product, reservations might be applicable to the respective hardware as well.
In general, the most probable scenario is a product with a dedicated connectivity, e.g., due to security restrictions, that has an application with high variance of the actual need.

Of course a combination of the above-mentioned mitigation strategies is possible, although they target different aspects.
The first two options aim to reduce the *probability* of customers cancelling a reservation or booking while compensating for the monetary loss of actual cancellations.
Overbooking tries to mitigate the monetary losses of actual cancelled reservations only while allowing the customers to be quite flexible with their cancellations.
All of these strategies can be omitted by simply not allowing reservations.

Thus, overselling is a strategy that aims to mitigate the problem of cancelled reservations and bookings without a deterrent effect on customers due to fees for cancellations.

#### Oversubscription

Oversubscription is the basis for all non-dedicated telecommunications services and can be used to maximize the usage of a shared common resource.
It is defined by the ratio of the Peak Information Rate (PIR) and the Committed Information Rate (CIR).

Note that oversubscribed items can be oversold, too.
Although it makes the most sense for dedicated items, i.e., items that can be offered to one and only one customer per instance.
If a product includes a non-dedicated data bandwidth, that is the PIR and the CIR are **not** the same, reservations make only little sense (although in principle possible).
Overselling only occurs when the sum of the CIR of all shared services exceeds the total available capacity.
However, when a shared service is oversold, this leads in general only to a degraded quality of service, e.g., it reduces the mean PIR and might reduce the actual available capacity below the CIR.

### Mitigation Strategies for an Overbooked System

Depending on the case, there are different strategies that can be implemented when a system overbooked becomes contented.

For dedicated items, there are basically two solutions:

1. The order for the item is cancelled.
2. A replacement is offered.

Note that it does not matter if the item was sold or reserved and how the state was achieved.

Cancellation of an order might result in penalties, bad reputation, and other negative consequences.
An example is a cancelled train without a replacement, e.g., if it was the last train that day.
In this scenario, the travellers not only get their tickets refunded but the provider is required by law to pay additional penalties/compensations.

For non-dedicated items, there is an additional option to offer a degraded service, e.g., with lower bandwidth to some or all customers.
Note that the degradation of service can be combined with the cancellation of services, e.g., when a transponder of a satellite fails.

Depending on the product that is involved, the provider can implement priority for the customer, i.e., customers with higher priority have a higher probability to keep their service active.
Priority can be implemented on different levels:

1. Based on the type of customer, e.g., higher priority for emergency forces etc.
2. Based on a hierarchy of users.
3. Based on the type of mission, e.g., rescue missions get higher priority than common daily usage.
4. As part of the products themselves, i.e., allowing commercial customers to pay for a higher priority.

For example the first setup for priority is implemented in terrestrial networks in cases of emergency, in the US with the *Nationwide Wireless Priority Service* and in the UK with the *Mobile Telecommunication Privilege Access Scheme*.
Communication priority is also implemented on planes with the SATVOICE protocol, that grants the different categories of communication different priorities, i.e., in descending order emergency, operational (high), operational (low), and non-operational.
The last setup for priority can be implemented e.g. as Value-Added Services.

However, all these mitigation strategies can be implemented in a PSS and do not require any additional options in the API.

### Overbooking of Committed and Non-Committed Resources

Overbooking can only be managed by the governance of a PSS if the items that are subject to overbooking are committed to the system.
Non-committed items are assumed to be handled almost exclusively with the PSS acting as service broker.
However, to manage overbooking, the items need to have corresponding entries in their respective inventory.
If a committed resource is configured in such a way that the relevant parameters can be monitored in the inventory, the governance can implement processes to allow overbooking from within the PSS for those items as well as add corresponding mitigation strategies.

The possibility of overbooking an item needs to be confirmed during the accreditation process.
To do so, the governance shall create a list of KPIs that can be monitored within the PSS, e.g., machine-readable parameters of the SLS/SLA, the cancellation rate of reservations, etc.
The governance can enforce this by marking those parameters as *mandatory for overbooking* in the respective templates, available via the APIs PSID633 and PSID634.

Note that the governance has full control over the templates and thus the parameters they want to define to allow overbooking.
The flexibility of the APIs enables the governance to do so, but does not enforce any process here.
However, it is recommended to include this in the accreditation process of the items if overbooking is considered.

### Overbooking and Distributed Matchmaking

Similar to non-committed items, overbooking cannot be managed by the governance if the items are collected via a distributed matchmaking.
However, the governance can implement an accreditation process that allows parties that are able to do distributed matchmaking to offer overbooked items.
The processes and contracts necessary to do so are not subject of the interface, but require additional business processes.

When allowing to offer overbooked items via distributed matchmaking, the governance needs to monitor the impact on overbooking on their customers, e.g., failure or degradation of services.
Therefore, this process has to be based on the ability to measure certain KPIs.
Again, this can be configured by the governance and enforced by an accreditation process.
Thus, the interface is not involved besides offering the option to implement such a set of parameters.

Additionally, the transmission of KPIs in the scope of distributed matchmaking needs to be taken into account when investigating the APIs for quality of service, i.e., TMF635 on usage management, TMF657 on service quality management, and maybe some others that need to be investigated in a later stage of this project.

## Compliance

The defined terms will be added to the [PSI-TAD].

## CIA Comparison for All Given Options

Not applicable.

## Implications for the Scope

This candidate requirement assumes that a product can only be offered in multiple regions by making a duplicate in the corresponding regional pool.
The currently envisioned design of pools does not require this, as a product offer or specification and its underlying services and resources can be assigned to multiple pools.
Pool are assumed to work as follows for this project: A product specification can be included in one or more pools.
Every service specification and resource specification that is required to implement the product specification has to be in the same pool(s) to guarantee that they are visible.
The same is true for service specifications that are based on a resource specification.
The other way round is not required, i.e., a resource specification can be in more pools than the service specification and the product specification that rely on it.
Product offers can only be in a pool where all product specifications they combine are available.
If a product offer is committed to the system and available in multiple pools, the instances of the resources and/or services can be restricted to fewer pools than their respective specifications.
For a committed product offer this means that all specifications and instances have to be in that particular pool.
Thus, overbooking caused by booking the same product from multiple pools at the same time cannot occur.

From a PSS's perspective, a product offering or product specification can never be overbooked due to the fact that it is assigned to multiple pools.

The calculation of the overbooking ratio/contention ratio is a PSS internal process that does not require any interface.
The interface allows to transmit the data required to do this calculation, i.e., the *committed information rate* (CIR) and the *peak information rate* (PIR) of *shared services* in case of *oversubscription* and for every product in case of *overselling*.
However, to do this calculation, the PSS needs to store all the information on a product where oversubscription (and overselling) are possible, as the state of being overbooked is accumulating over time and not tracked by the stateless interface.
The interface does not store any information and therefore is not capable of such a calculation.

The calculation of the overbooking ratio/contention ratio of an oversubscribed product is a PSS internal process that does not require any interface.
The interface does not store any information and therefore is not capable of such a calculation.

The calculation of the community overbooking ratio/contention ratio is a PSS internal process that does not require any interface.
The interface does not store any information and therefore is not capable of such a calculation.

The PSS shall flag all cases of overbooking, i.e., both on a service level and on a community level.
Flagging has to happen based on the state of the system, i.e., internally, and without the need for any communication.
Therefore, the interface is not involved.

Internal matchmaking with respect to a priority system:
How resources are assigned cannot be specified on the interface level.

This candidate requirement defines two scenarios: First, "overbooking" of the peak information rate (PIR), which can be defined as oversubscription.
Oversubscription is a common model in both terrestrial telecommunication (contended services) and in (Gov)SatCom (shared services).
Secondly, "overbooking" of the committed information rate (CIR), which means that the PSS promised more bandwidth than it has available.
This can be defined as Overselling, which needs mitigation.
Oversubscription can lead to overselling, if the sum of the CIR exceeds the total available capacity.
In principle, both scenarios need to be handled in the PSS internally.
However, the interfaces are capable to support these processes, i.e., the interfaces can handle the transmission of the required data.

The interface allows including CIR and PIR in the respective service specifications, and thus define the *contention ratio* for each of them.
This specification needs to flow down into the corresponding SLS and SLA.
However, this is out of scope for the interface definition, as it can only allow to include the required parameters.
Enforcing the contractually agreed contention ratio means that any given product the contract defines if overbooking (either as *overselling* for *dedicated services* or as *oversubscription* for *shared services*) is allowed or not.
The PSS can only enforce this in two ways: First, if the underlying items are committed or owned by the PSS, the governance has control over them and can manage overbooking itself.
Secondly, if the underlying items are non-committed, the governance can only manage this by the accreditation process of the corresponding products, i.e., allow a provider to overbook a given product line or not.
In summary, this candidate requirement is implicitly implemented via these options.
Nevertheless, enforcing some PSS internal process is out of scope for this project.

The *contention ratio* for *shared services* can be over one, as this is the usual business case utilizing the concept of *oversubscription*.
For any other product, the PSS is responsible to calculate this based on the number of committed resources and booked products.
The interface can only allow the communication and transfer of the data required to implement this kind of monitoring.
The contention ratio is a derived value that is not required for any process involving external communication.
This requirement is related to the governance interface only.
However, the [PSID688 Event Management] can implement a notification event that informs the governance when an instance of a resource, service, or product is booked multiple times.

This candidate requirement basically states that a PSS shall be able to offer *shared services* that utilize *oversubscription*.
This is enabled by the interface, but not enforced, since this is a PSS internal process.

Assuming that "subscribed contractual option" is referring to *shared services*, this candidate requirement states that a capacity of such shared services shall be manageable by its respective community, i.e., the shared ratios, CIR, PIR, and how the capacity is chunked shall be in the responsibility of the community itself, e.g, with an administrative board.
In that case, *oversubscription* can indeed be managed by this administrative board, and even if *overselling* happens, this is a problem of the community, not of the PSS or the provider, as this is only managed internally.
This means, that a PSS shall be able to automatically forward configuration files to a hub that is providing shared serviced, e.g., to configure an iDirect Hub.
Such a file can be exchanged via the [PSID667 Document Management].

The interface [PSID688 Event Management] can implement a notification event that informs the governance and/or the community, e.g., via its administrative board.

In case of overbooking and/or degraded services, the governance or, in case of a community, a community administrative board should be able to give some users priority.
This can be handled in multiple ways, e.g., a community-internal pre-emption system, an ordered list of all members that automatically resolves those contention situations, groups with the same priority, etc.
There are already a lot of protocols to handle priority, but none of them is generally accepted or can be applied to every situation.
They are an PSS internal process, the generation of such priority systems and the corresponding assignment are subject to the PSS governance and might be implemented on demand from a user group.
However, the data to communicate priority not only internally but with multiple systems involved can be added to multiple interfaces, e.g., if pre-emption **and** distributed matchmaking are implemented to function together.
Therefore, at least the [PSID001 Customer Inquiry Management], the [PSID632 Party Management], and the [PSID639 Resource Inventory Management] can add corresponding features that allow for a reasonable process.
Note that this requires a standardized priority system across multiple systems to work properly, which this project cannot provide.
Again, the interface can only enable this process, but the details are up to the implementation.

Pre-emption is a PSS-internal process, as it handles the states of orders.
However, the interface manages the "incoming higher priority service request" just like any other request.
To implement the priority system, the interface needs to communicate the corresponding party profile for a request.
The parties need to define a default profile, which can be generated by the governance during the accreditation process.
Additionally, the resources that are booked, i.e., a subset of resources in the inventory, need to know who is allowed to request them in case of pre-emption.
Therefore, this candidate requirement is partially implemented, while pre-emption itself is out of scope for this project, but it is enabled by the interface.

Governance and user interface.
When the system monitors the corresponding event, it should be able to generate another event via the [PSID688 Event Management] that requires an approval to implement a specific solution for a contention situation.
