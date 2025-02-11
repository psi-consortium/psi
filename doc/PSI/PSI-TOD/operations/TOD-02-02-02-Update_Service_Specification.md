=begin

# TOD-02-02-02-Update_Service_Specification

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
  rectangle "Update Service Specification" as Operation
}

Provider --	    I1
I1 --           Operation

@enduml

```

![TOD-02-02-02: Update Service Specification](../../common/pixel.png){#fig:TOD-02-02-02-Update_Service_Specification}

**Prerequisites**

The service specification exists in the PSS datastore.
A PSS might reject the update of essential characteristics like the frequency band.
In that case, the client shall set an expiration date for the current specification and create a new one that is valid thereafter.

**Main operation**

Updates an existing service specification via a standard interface specification.

**REST Endpoints**

@include [TOD-02-02-02 Update Service Specification Endpoints](endpoints/TOD-02-02-02-Update_Service_Specification-endpoints.md)

**Post Conditions**

The service specification is successfully updated in the PSS datastore.

**Applicable Requirements**

@include [TOD-02-02-02 Update Service Specification Requirements](requirements/TOD-02-02-02-Update_Service_Specification-requirements.md)

**eTOM Reference**

The operation is based on 1.4.15.1 and 1.4.19.2 process identifiers from the eTOM.
