=begin

# TOD-03-06-04-View_All_Quotes

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
  rectangle "View All Quotes" as Operation1
}

package "Provider" {
  rectangle "View All Quotes" as Operation2
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

![TOD-03-06-04: View All Quotes](../../common/pixel.png){#fig:TOD-03-06-04-View_All_Quotes}

**Prerequisites**

Quotes exist in the PSS datastore.

**Main operation**

Gets all quotes of the customer from the PSS via a standard interface specification, in pages (*offset*, *limit*), so that many concurrent quote requests can be monitored.
The PSS can likewise view all of its quotes on the provider's system, and a provider can view all of its own quotes in the PSS.

**REST Endpoints**

@include [TOD-03-06-04 View All Quotes Endpoints](endpoints/TOD-03-06-04-View_All_Quotes-endpoints.md)

**Post Conditions**

All quotes which the client can read are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-03-06-04 View All Quotes Requirements](requirements/TOD-03-06-04-View_All_Quotes-requirements.md)

**eTOM Reference**

None
