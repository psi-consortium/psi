=begin

# TOD-05-01-05-View_All_Resources

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
  rectangle "View All Resources" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation
Customer --     I3
I3 --           Operation

@enduml

```

![TOD-05-01-05: View All Resources](../../common/pixel.png){#fig:TOD-05-01-05-View_All_Resources}

**Prerequisites**

Resources exist in the PSS datastore.

**Main operation**

Gets all visible resources previously registered in the PSS via a standard interface.
These can be filtered at least by resource type.
Customers can view their own declared resources and those they booked.

**REST Endpoints**

@include [TOD-05-01-05 View All Resources Endpoints](endpoints/TOD-05-01-05-View_All_Resources-endpoints.md)

**Post Conditions**

All visible resources are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-05-01-05 View All Resources Requirements](requirements/TOD-05-01-05-View_All_Resources-requirements.md)

**eTOM Reference**

The operation is based on the 1.5.4.5 process identifier from the eTOM.
