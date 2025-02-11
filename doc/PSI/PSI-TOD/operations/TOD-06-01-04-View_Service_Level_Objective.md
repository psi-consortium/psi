=begin

# TOD-06-01-04-View_Service_Level_Objective

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

Customer << 0 >>
Provider << 1 >>
Governance << 3 >>

rectangle "PSI" {
  interface "PSS-PVD I/F" as I1
  interface "PSS-GOV I/F" as I2
  interface "PSS-USR I/F" as I3
}

package "PSS" {
  rectangle "View Service Level Specification" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation
Customer --     I3
I3 --           Operation

@enduml

```

![TOD-06-01-04: View Service Level Objective](../../common/pixel.png){#fig:TOD-06-01-04-View_Service_Level_Objective}

**Prerequisites**

The service level objective exists in the PSS datastore.

**Main operation**

Gets a service level objective instance via a standard interface.
Customers can only view the service level objectives granted by providers.

**REST Endpoints**

@include [TOD-06-01-04 View Service Level Objective](endpoints/TOD-06-01-04-View_Service_Level_Objective-endpoints.md)

**Post Conditions**

The service level objective is successfully returned for viewing.

**Applicable Requirements**

@include [TOD-06-01-04 View Service Level Objective](requirements/TOD-06-01-04-View_Service_Level_Objective-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
