=begin

# TOD-01-02-02-Register_Event_Callback

> The heading has to be included in the document including this document.

|                |                         |
|----------------|-------------------------|
| Name           | Register Event Callback |
| Operation ID   | TOD-01-02-02            |
| Implementor/s  | PSS, Provider           |
| Consumer/s     | PSS, Provider           |

Table: Overview Operation **TOD-01-02-02-Register_Event_Callback**. {#tbl:table-TOD-01-02-02-Register_Event_Callback}

=end

```plantuml
@startuml
skinparam actor {
  BackgroundColor<< 0 >> 	SteelBlue
  BorderColor<< 0 >> 		SteelBlue
  BackgroundColor<< 1 >> 	Green
  BorderColor<< 1 >> 		Green
  BackgroundColor<< 3 >> 	Red
  BorderColor<< 3 >> 		Red
}

skinparam entity{
  BackgroundColor<< 2 >> 	Orange
  BorderColor<< 2 >> 		Orange
}

Provider << 1 >>
entity PSS << 2 >> as PSS1

rectangle "PSI" as PSI1 {
  interface "PSS-PVD I/F" as PSI1_I1
  interface "PSS-PSS I/F" as PSI1_I2
}

package "PSS" as PSS_PCK {
  rectangle "Register Event Callback" as Operation1
}

Provider --	    PSI1_I1
PSI1_I1 --      Operation1
PSS1 --         PSI1_I2
PSI1_I2 --      Operation1

entity PSS << 2 >> as PSS2

rectangle "PSI" as PSI2 {
  interface "PSS-PVD I/F" as PSI2_I1
}

package "Provider" as PVD_PCK {
  rectangle "Register Event Callback" as Operation2
}

PSS2 --         PSI2_I1
PSI2_I1 --      Operation2

@enduml

```

![**TOD-01-02-02**: Register Event Callback](../../common/pixel.png){#fig:TOD-01-02-02-Register_Event_Callback}

**Prerequisites**

At least one event topic must exist.

**Main operation**

Each system has to register callbacks for the events they want to receive from one another.
Usually, both systems will register for the following topics named after the corresponding entities:

* inquiry
* order
* invoice

The registration must contain the URL of the callback endpoint, which is expected to be the one described in [TOD-01-02-03](#tod-01-02-03-dispatch_event).
Note that the implementation may reject callbacks if the host is not whitelisted beforehand (see PSI-ICD).

Events are always filtered by the sending system on a need-to-know basis, e.g. the PSS will send order events only to the parties participating in the interaction.
Additionally, the registration may contain a filter query to tailor the scope of the received events.
Those can be defined as a conjunction of attributes and expected values.
The available attributes depend on the event type and can be nested using dot-notation.
When a list of items is queried, only one of them has to match the given value.
Implementations may offer additional capabilities as described in the TMF630 REST API Design Guidelines 4.2.0[^tmf_api_guidelines].
For example, an order event could be filtered using:

* `event.priority=1` (receive only orders with high priority)
* `event.productOrderItem.productOffering.id=abcd` (receive only orders of a specific product offering)
* `event.category=InternetAccess&state=pending` (receive only orders with category 'InternetAccess' **and** state 'pending')

**REST Endpoints**

@include [TOD-01-02-02 Register Event Callback Endpoints](endpoints/TOD-01-02-02-Register_Event_Callback-endpoints.md)

**Post Conditions**

The callback is registered in the other system.

**Applicable Requirements**

@include [TOD-01-02-02 Register Event Callback Requirements](requirements/TOD-01-02-02-Register_Event_Callback-requirements.md)

**eTOM Reference**

None

[^tmf_api_guidelines]: https://www.tmforum.org/resources/specification/tmf630-rest-api-design-guidelines-4-2-0/
