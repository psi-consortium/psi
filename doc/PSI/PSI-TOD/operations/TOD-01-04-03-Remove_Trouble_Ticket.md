=begin

# TOD-01-04-03-Remove_Trouble_Ticket

=end

```plantuml
@startuml
skinparam actor {
  BackgroundColor<< 0 >> 	SteelBlue
  BorderColor<< 0 >> 		SteelBlue
  BackgroundColor<< 1 >> 	Green
  BorderColor<< 1 >> 		Green
}

skinparam entity{
  BackgroundColor<< 2 >> 	Orange
  BorderColor<< 2 >> 		Orange
}

Customer << 0 >>
Provider << 1 >> 

rectangle "PSI" {
  interface "PSS-PVD I/F" as I1
  interface "PSS-USR I/F" as I2
}

package "PSS" {
  rectangle "Remove Trouble Ticket" as Operation
}

Provider --	    I1
I1 --           Operation
Customer --     I2
I2 --           Operation

@enduml

```

![TOD-01-04-03: Remove Trouble Ticket](../../common/pixel.png){#fig:TOD-01-04-03-Remove_Trouble_Ticket}

**Prerequisites**

The trouble ticket exists in the PSS datastore.

**Main operation**

Removes a trouble ticket either by deleting it or indicating it is no longer valid, via a standard interface specification.

**REST Endpoints**

@include [TOD-01-04-03 Remove Trouble Ticket Endpoints](endpoints/TOD-01-04-03-Remove_Trouble_Ticket-endpoints.md)

**Post Conditions**

The trouble ticket is successfully deleted or indicated it is no longer valid in the PSS datastore.

**Applicable Requirements**

@include [TOD-01-04-03 Remove Trouble Ticket Requirements](requirements/TOD-01-04-03-Remove_Trouble_Ticket-requirements.md)

**eTOM Reference**

The operation is based on 1.4.6.4 process identifier from the eTOM.
