=begin

# TOD-01-02-04-Deregister_Event_Callback

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
  rectangle "Deregister Event Callback" as Operation1
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
  rectangle "Deregister Event Callback" as Operation2
}

PSS2 --         PSI2_I1
PSI2_I1 --      Operation2

@enduml

```

![**TOD-01-02-04**: Deregister Event Callback](../../common/pixel.png){#fig:TOD-01-02-04-Deregister_Event_Callback}

**Prerequisites**

The callback is registered in another system.

**Main operation**

When a system does not need to listen for an event topic any more, it can deregister its callback from other systems.
The other system will consequently not dispatch events to the callback afterwards.

**REST Endpoints**

@include [TOD-01-02-04 Deregister Event Callback Endpoints](endpoints/TOD-01-02-04-Deregister_Event_Callback-endpoints.md)

**Post Conditions**

The callback is deregistered in the other system.

**Applicable Requirements**

@include [TOD-01-02-04 Deregister Event Callback Requirements](requirements/TOD-01-02-04-Deregister_Event_Callback-requirements.md)

**eTOM Reference**

None
