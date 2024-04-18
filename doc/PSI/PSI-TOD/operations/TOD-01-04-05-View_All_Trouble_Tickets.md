=begin

# TOD-01-04-05-View_All_Trouble_Tickets

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
  interface "PSS-USR I/F" as I2
  interface "PSS-GOV I/F" as I3
}

package "PSS" {
  rectangle "View All Trouble Tickets" as Operation
}

Provider --	    I1
I1 --           Operation
Customer --     I2
I2 --           Operation
Governance --     I3
I3 --           Operation

@enduml

```

![**TOD-01-04-05**: View All Trouble Tickets](../../common/pixel.png){#fig:TOD-01-04-05-View_All_Trouble_Tickets}

**Prerequisites**

Trouble Tickets exist in the PSS datastore.

**Main operation**

Gets all trouble tickets visible to the caller via a standard interface specification.
These can be filtered at least by type and status.

**REST Endpoints**

@include [TOD-01-04-05 View All Trouble Tickets Endpoints](endpoints/TOD-01-04-05-View_All_Trouble_Tickets-endpoints.md)

**Post Conditions**

All visible trouble tickets are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-01-04-05 View All Trouble Tickets Requirements](requirements/TOD-01-04-05-View_All_Trouble_Tickets-requirements.md)

**eTOM Reference**

The operation is based on 1.4.6.4 process identifier from the eTOM.
