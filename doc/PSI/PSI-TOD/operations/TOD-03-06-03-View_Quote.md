=begin

# TOD-03-06-03-View_Quote

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
actor "Provider" << 1 >> as Prov
entity PSS << 2 >> as PSS1

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PSS I/F" as I3
  interface "PSS-PVD I/F" as I2
  interface "PSS-PVD I/F" as I4
}

package "PSS" {
  rectangle "View Quote" as Operation1
}

package "Provider" {
  rectangle "View Quote" as Operation2
}

Customer --	    I1
I1 --           Operation1
PSS1 --         I3
I3 --           Operation1
Operation1 --   I2
I2 --           Operation2
Prov --         I4
I4 --           Operation1

@enduml

```

![TOD-03-06-03: View Quote](../../common/pixel.png){#fig:TOD-03-06-03-View_Quote}

**Prerequisites**

The quote exists in the PSS datastore.

**Main operation**

Gets a quote with a specific identifier via a standard interface specification.
The customer can request to view the quote from the PSS, and the PSS can view the quote on the provider's system.
A provider can also view a quote in the PSS if the quote is its own.

Viewing is one way to learn the state a quote has reached and the provider's prices; the other are the notifications of the Event Management API (see [TOD-01-02](#tod-01-02-eventmanagement)).

**REST Endpoints**

@include [TOD-03-06-03 View Quote Endpoints](endpoints/TOD-03-06-03-View_Quote-endpoints.md)

**Post Conditions**

The quote that the client can read is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-03-06-03 View Quote Requirements](requirements/TOD-03-06-03-View_Quote-requirements.md)

**eTOM Reference**

None
