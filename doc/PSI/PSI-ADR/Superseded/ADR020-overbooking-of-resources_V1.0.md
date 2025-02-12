# Overbooking of Resources (V1.0)

* ID: ADR020
* Status: :superseded: by [Overbooking of Resources V2.0](ADR020-overbooking-of-resources_V2.0.md) in [PSI-ADR-Annex-I]
* Deciders: @dvs @rgs @vmr @wr @cg @daf @cgr @ncz @rsperb
* Date: 2023-05-31
* Version: 1.0
* Category: Design

## Context and Problem Statement

Various candidate requirements point out the possibility of overbooking.
Although most of them are concerning PSS-internal processes, we have to evaluate which of these have to be enabled by the interface.

## Decision Outcome

First we have to distinguish resources and services:

* For physical and logical resources (raw bandwidth, transponders, terminals) there will be **no** overbooking.
  We found no example of providers implementing this case.
  An edge-case is leasing of raw bandwidth for very short timeframes in the range of minutes.
  The API already supports this precision (even up to milliseconds) in all timestamp properties.
* For managed service, the characteristics already distinguish "committed" and "peak" information rates.
  Here, only the CIR is actually booked and cannot be overbooked.
  The PIR on the other hand can be shared.
  For example, an available bandwidth of 100 Mbit/s can be leased to two customers with 100 Mbit/s PIR each and a **total** of 100 Mbit/s CIR (different ratios possible).

Another use case which could be considered here is the possibility to **reserve** resources.
This may be the case when a customer issued an RFQ but did not yet order it.
To avoid disappointment, the provider could decide to reserve the resource for a defined time.
The interface shall support this workflow.
Alternatively, there would be the possibility of reservation-fees, so the customer could initiate this for his own safety.

This brings up the related question of state handling:
Does the PSS know the booking state (e.g. by frequent updates by the provider) or is this state stored in the provider system and has therefore been fetched.
Another look into the requirements makes the following differentiation necessary:

* **Committed** resources shall be managed by the **PSS**.
  They will be acquired by the governance via a contract, which guarantees access.
  Therefore, the provider shall not sell it outside the PSS, meaning the PSS is always aware of the booking state.
  The interface should foresee the possibility to view and update the state information.
* **On-demand** resources are still managed by the **provider** and have to be requested.
  Since they can still be sold on the free market, the availability is basically unknown to the PSS.
  The actual state has to be requested via the interface.

The current Catalog APIs are not the right place to store this information.
They store only specifications, which describe resources that may not even exist physically.
Instead, it is to be investigated if the Inventory APIs can be used for this purpose.
They are responsible to manage actually existing resources and may be able to store the state information on them.

The possibility of pre-emption does not introduce new interfaces.
It is defined by the SLA between customer and provider, and potentially includes priority management as an internal process of the PSS.
From an interface perspective, it triggers either a downgrade or cancellation of an active service and subsequently orders the free capacity as part of a new one.
Considering a high grade of automation in the PSS and provider system, this can be done within few minutes and does not necessarily include any human interaction.

Lastly, it is agreed that specifications and offerings are **not** created and deleted as part of these processes.
They describe timeless information and should only be revoked if the resource is not available for a long time (e.g. because it is decommissioned or sold).

Another **excluded** aspect is the PSS-internal process of assigning one resource to multiple pools.
This kind of overbooking is not visible to the provider or other external parties.
Effectively the resource can still only be booked once as described above.

## Compliance

Improve descriptions in [PSI-TAD] and [PSI-TOD].

## Implications for the Scope

This kind of overbooking is not visible to the provider or other external parties. Effectively the resource can still only be booked once as described above. This is a PSS-internal process, which might have an influence on the governance interface in the future, once the corresponding process is defined.

With the difference between the committed and the peak information rate, a pseudo-overbooking is possible for the peak information rate of a service. This is commonly done already but has no consequence on the interface, though.

Additionally, the interface definition allows overbooking as defined, thus intrinsically fulfilling the requirement.
