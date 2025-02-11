=begin

# TOD-03-04-05-View_All_Missions

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

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
}

package "PSS" {
  rectangle "View All Missions" as Operation
}

Customer --	    I1
I1 --           Operation

@enduml
```

![TOD-03-04-05: View All Missions](../../common/pixel.png){#fig:TOD-03-04-05-View_All_Missions}

**Prerequisites**

Missions of the customer exist in the PSS datastore.

**Main operation**

Gets all missions of the customer via a standard interface specification.

**REST Endpoints**

@include [TOD-03-04-05 View All Missions Endpoints](endpoints/TOD-03-04-05-View_All_Missions-endpoints.md)

**Post Conditions**

All missions of the customer are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-03-04-05 View All Missions Requirements](requirements/TOD-03-04-05-View_All_Missions-requirements.md)

**eTOM Reference**

The operation is not based on the eTOM.
