=begin

# TOD-06-02-05-View_All_Service_Level_Specification

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
  rectangle "View All Service Level Specification" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation
Customer --     I3
I3 --           Operation


@enduml

```

![**TOD-06-02-05**: View All Service Level Specification](../../common/pixel.png){#fig:TOD-06-02-05-View_All_Service_Level_Specification}

**Prerequisites**

The service level specification exists in the PSS datastore.

**Main operation**

Gets all service level specification instance via a standard interface.
Customers can only view the service level specifications granted by providers.

**REST Endpoints**

@include [TOD-06-02-05 View All Service Level Specifications](endpoints/TOD-06-02-05-View_All_Service_Level_Specification-endpoints.md)

**Post Conditions**

All visible service level specifications are successfully returned for viewing.

**Applicable Requirements**

@include [TOD-06-02-05 View_All Service Level Specifications](requirements/TOD-06-02-05-View_All_Service_Level_Specification-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
