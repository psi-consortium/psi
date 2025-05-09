=begin

# TOD-06-02-02-Update_Service_Level_Specification

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

Provider << 1 >>

rectangle "PSI" {
  interface "PSS-PVD I/F" as I1
}

package "PSS" {
  rectangle "Update Service Level Specification" as Operation
}

Provider --	    I1
I1 --           Operation

@enduml

```

![TOD-06-02-02: Update Service Level Specification](../../common/pixel.png){#fig:TOD-06-02-02-Update_Service_Level_Specification}

**Prerequisites**

The service level specification exists in the PSS datastore and referenced service level objectives are available.

**Main operation**

Updates a service level specification instance via a standard interface.

**REST Endpoints**

@include [TOD-06-02-02 Update Service Level Specification](endpoints/TOD-06-02-02-Update_Service_Level_Specification-endpoints.md)

**Post Conditions**

The service level specification is successfully updated in the PSS datastore.

**Applicable Requirements**

@include [TOD-06-02-02 Update Service Level Specification](requirements/TOD-06-02-02-Update_Service_Level_Specification-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
