=begin

# TOD-01-02-Event_Management

> The heading has to be included in the document including this document.

=end

Some processes between a PSS and a provider (or PSS and PSS), such as customer inquiries and orders, can take longer time to complete.
For example, when a customer inquiry is created, the provider may require significant time to process and respond with an adequate product offering.
Or, when a product order is placed by a customer, it can take hours to days for its state to change, e.g. from 'inProgress' to 'completed'.

Inside a PSS (or a sophisticated provider system) the anticipated approach to propagate such state changes are message queues.
A direct connection between these, although possible, would result in a strong coupling of the systems and major implications by the interface definition on the internal implementations.
In order to avoid this, the Event Management defines how to exchange the information using REST.

> Note that this does not **enforce** the use of message queues.
All named operations and endpoints can also be implemented in a monolithic application.

The Event Management task is based on the exchange of events between two systems.
*Topics* are target containers for events which exist to store different events separated into domains.
A PSS must have at least three topics: *order*, *inquiry* and *alarm*.
Hence, all events related to orders are collected in the *order* topic, while the events of the inquiries are stored in the *inquiry* topic.
Alarms related to breaching service level specification are stored in the *alarm* topic.
However, PSS and provider systems are allowed to define additional topics, if needed, to organise the events in their interface implementation.

The following diagram illustrates a usual execution sequence, using the "order" topic as an example:

```plantuml
@startuml
participant PSS
participant Provider as PVD

opt check available topics
    PSS -> PVD ++ : GET http://pvd.com/psi/event/v2/topic
    return [{"name": "order"}, {"name": "inquiry"}, ...]
end

autonumber

PSS -> PVD ++ : POST http://pvd.com/psi/event/v2/topic/order/hub\n{"callback": "http://pss.com/psi/event/v2/topic/order/event"}
return {"id": "1a0f2e44-5490-4fe9-b58a-b2e9006c033f"}

...

loop everytime an event occurs
    PVD -> PSS ++ : POST http://pss.com/psi/event/v2/topic/order/event\n{"eventType": "updated", "value": {"@type": "Order", "id": "...."}}
    return Ack
end

PSS -> PVD ++ : DELETE http://pvd.com/psi/event/v2/topic/order/hub/{id}
return Ack
@enduml
```

![TOD-01-02: Event Management Sequence](../../common/pixel.png){#fig:TOD-01-02-Event_Management-sequence}

The shown steps are further described in the following operations:

```plantuml
@startuml

usecase "Event Management"            as Task
rectangle "View Event Topics"         as O1
rectangle "Register Event Callback"   as O2
rectangle "Dispatch Event"            as O3
rectangle "Deregister Event Callback" as O4

Task  ..> O1    : <<includes>>
Task  ..> O2    : <<includes>>
Task  ..> O3    : <<includes>>
Task  ..> O4    : <<includes>>
@enduml

```

![TOD-01-02: Event Management](../../common/pixel.png){#fig:TOD-01-02-Event_Management}

|                               | Customer |  Provider  | Other PSS  | Governance |
|-------------------------------|:--------:|:----------:|:----------:|:----------:|
| **View Event Topics**         |          | \checkmark | \checkmark |            |
| **Register Event Callback**   |          | \checkmark | \checkmark |            |
| **Dispatch Event**            |          | \checkmark | \checkmark |            |
| **Deregister Event Callback** |          | \checkmark | \checkmark |            |

Table: Event Management Matrix. {#tbl:event-management-matrix}

Please note that the Governance does not get direct access to the endpoints.
Nevertheless, the Governance usually has read access to the event data via the monitoring service.

**eTOM Reference**

None
