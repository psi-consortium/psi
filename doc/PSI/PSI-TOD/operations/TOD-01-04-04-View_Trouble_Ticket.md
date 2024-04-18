=begin

# TOD-01-04-04-View_Trouble_Ticket

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
  rectangle "View Trouble Ticket" as Operation
}

Provider --	    I1
I1 --           Operation
Customer --     I2
I2 --           Operation
Governance --     I3
I3 --           Operation

@enduml

```

![**TOD-01-04-04**: View Trouble Ticket](../../common/pixel.png){#fig:TOD-01-04-04-View_Trouble_Ticket}

**Prerequisites**

The trouble ticket exists in the PSS datastore.

**Main operation**

Gets a trouble ticket with a specific identifier via a standard interface specification.

**REST Endpoints**

@include [TOD-01-04-04 View Trouble Ticket Endpoints](endpoints/TOD-01-04-04-View_Trouble_Ticket-endpoints.md)

**Post Conditions**

The trouble ticket is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-01-04-04 View Trouble Ticket Requirements](requirements/TOD-01-04-04-View_Trouble_Ticket-requirements.md)

**eTOM Reference**

The operation is based on 1.4.6.4 process identifier from the eTOM.
