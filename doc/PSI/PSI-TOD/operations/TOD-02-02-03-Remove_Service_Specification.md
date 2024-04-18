=begin

# TOD-02-02-03-Remove_Service_Specification

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
  rectangle "Remove Service Specification" as Operation
}

Provider --	    I1
I1 --           Operation

@enduml

```

![**TOD-02-02-03**: Remove Service Specification](../../common/pixel.png){#fig:TOD-02-02-03-Remove_Service_Specification}

**Prerequisites**

The service specification exists in the PSS datastore.

**Main operation**

Removes a service specification either by deleting it or indicating it is no longer valid, via a standard interface specification.

**REST Endpoints**

@include [TOD-02-02-03 Remove Service Specification Endpoints](endpoints/TOD-02-02-03-Remove_Service_Specification-endpoints.md)

**Post Conditions**

The service specification is successfully deleted or indicated it is no longer valid in the PSS datastore.

**Applicable Requirements**

@include [TOD-02-02-03 Remove Service Specification Requirements](requirements/TOD-02-02-03-Remove_Service_Specification-requirements.md)

**eTOM Reference**

The operation is based on 1.4.15.1 and 1.4.19.2 process identifiers from the eTOM.
