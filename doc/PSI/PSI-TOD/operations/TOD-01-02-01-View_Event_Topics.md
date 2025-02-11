=begin

# TOD-01-02-01-View_Event_Topics

> The heading has to be included in the document including this document.

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
  rectangle "View Event Topics" as Operation1
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
  rectangle "View Event Topics" as Operation2
}

PSS2 --         PSI2_I1
PSI2_I1 --      Operation2


@enduml

```

![TOD-01-02-01: View Event Topics](../../common/pixel.png){#fig:TOD-01-02-01-View_Event_Topics}

**Prerequisites**

The event topics are created on the other system.

**Main operation**

This operation allows one system to query the available event topics of another system.
Since the relevant names for PSID are predefined, doing so is considered optional, though it may reveal additional topics.
The topics can then be subscribed to using [TOD-01-02-02](#tod-01-02-02-registereventcallback).

**REST Endpoints**

@include [TOD-01-02-01 View Event Topics Endpoints](endpoints/TOD-01-02-01-View_Event_Topics-endpoints.md)

**Post Conditions**

All available event topics of the queried system (PSS or provider) are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-01-02-01 View Event Topics Requirements](requirements/TOD-01-02-01-View_Event_Topics-requirements.md)

**eTOM Reference**

None
