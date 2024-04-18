=begin

# TOD-01-04-02-Update_Trouble_Ticket

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
  rectangle "Update Trouble Ticket" as Operation
}

Provider --	    I1
I1 --           Operation
Customer --     I2
I2 --           Operation
Governance --     I3
I3 --           Operation

@enduml

```

![**TOD-01-04-02**: Update Trouble Ticket](../../common/pixel.png){#fig:TOD-01-04-02-Update_Trouble_Ticket}

**Prerequisites**

The trouble ticket exists in the PSS datastore. The following properties are available additional to the creation:

* *expectedResolutionDate* - The expected resolution date by the provider.
* *resolutionDate* - The actual resolution date, set when closing the ticket.
* *status* - The current status of the ticket (e.g. pending, in progress, resolved).
* *statusChangeReason* - To be set when the status is changed. Will be stored in the status history by the server.
* *note* - Additional comments by the customer or provider.

**Main operation**

Updates an existing trouble ticket via a standard interface specification.

**REST Endpoints**

@include [TOD-01-04-02 Update Trouble Ticket Endpoints](endpoints/TOD-01-04-02-Update_Trouble_Ticket-endpoints.md)

**Post Conditions**

The trouble ticket is successfully updated in the PSS datastore.

**Applicable Requirements**

@include [TOD-01-04-02 Update Trouble Ticket Requirements](requirements/TOD-01-04-02-Update_Trouble_Ticket-requirements.md)

**eTOM Reference**

The operation is based on 1.4.6.4 and 1.4.6.6 process identifiers from the eTOM.
