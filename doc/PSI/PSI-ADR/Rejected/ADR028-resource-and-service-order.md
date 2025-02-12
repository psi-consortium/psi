# Resource and Service Order Management

* ID: ADR028
* Status: :rejected:
* Deciders: @ncz @cgr @hop
* Date: 2023-08-21
* Version: 1.0
* Category: Design

## Context and Problem Statement

PSID currently utilizes the TM Forum *Product Ordering Management API* as described in TMF622 V4.0 to process orderings.
Herewith customer orders, that were created based on available offerings (later also RFQs, etc.) are shared with corresponding providers.
In the case that the provider signed up as a recipient for the event support of orders, PSS even distributes these orderings to the provider's system.

A closer look at the offerings reveals that a product offering consists of one or, in the case of bundled offerings, several product specifications.
These product specifications are in turn build of either one or more service specifications or one or more resource specifications.
Additionally, service specifications could be built from a hierarchy of further service specifications and/or resource specifications.
To reflect this complexity and accompany the lifecycle of each of the resulting service and resource, TM Forum offers a matching subdivision of product orders into service and/or resource orders.
We have to evaluate their benefit for the PSS-to-Provider interface.

## Decision Drivers

* All details required to successfully place an order have to be passed to the provider
* All details required to successfully put a service/resource into operation have to be passed to the customer.
* Keeping the barrier for new providers as low as possible.

## Considered Options

* Proceed only with TMF622 *Product Ordering Management API*.
* Additionally introduce TMF641 *Service Ordering Management API* and TMF652 *Resource Order Management API*

## Decision Outcome

It has been decided to **not** implement TMF641 and TMF652 and proceed with TMF622 to share and route (via event management) product orderings.

Since the currently supported interfaces at the product order level already transport all the necessary information to the provider side and an enhanced lifecycle tracking of decomposed orders is currently not required (not part of the candidate requirements), we should keep the number of interfaces that have to be supported by the providers low.
As the result, any necessary refinement of the product order is performed only on the provider side.

## Compliance

N/A

## Pros and Cons of the Options

### TMF822

TMF822 *Product Ordering Management API* is already part of PSID and allows sharing orders with providers within a PSS or even with PSS clients via the event management.

Processing of the product order on the provider side results in the creation of suitable products, services and resources.
These inventory entities are linked to each other and to the original product order via the product.
In addition, resources are used by the provider to supply the configuration parameters needed to set up the hardware on the customer side.

Should it appear necessary that attachments are also needed on service and/or product level - maybe even for product orders -, then we would expand the existing schemata by an additional attachment reference.

### Introducing TMF641 and TMF652

Introducing TMF641 *Service Ordering Management API* and TMF652 *Resource Order Management API* would add two new APIs to PSID to deal with orderings.
This would mean tripling the number of APIs to be supported by the providers.

With the help of TMF641 and TMF652 the decomposition of the order could be available in the PSS and therefore for the customers, too.
So, the PSS would publish more details about the fulfilment state of the sub entities of an order.
In addition, TMF641 *Service Ordering Management API* introduces new associations for e.g.

* ServiceOrderMilestone
* ServiceOrderErrorMessage
* ServiceOrderJeopardyAlert

They are all used internally by the provider to fulfil the service order.
It would be questionable whether the provider always wants to share these details with their customers.

Taking into account that all necessary information is already shared with the provision of the product order, the introduction of TMF641 and TMF652 seems to be another level of complexity that is not needed or even desired for PSS-to-Provider interfaces.

