=begin

# TOD-05-03-03-Remove_Product

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
  rectangle "Remove Product" as Operation
}

Provider --	    I1
I1 --           Operation

@enduml

```

![TOD-05-03-03: Remove Product](../../common/pixel.png){#fig:TOD-05-03-03-Remove_Product}

**Prerequisites**

The product exists in the PSS datastore.

**Main operation**

Removes a product either by deleting it or indicating it is no longer valid, via a standard interface.

**REST Endpoints**

@include [TOD-05-03-03 Remove Product Endpoints](endpoints/TOD-05-03-03-Remove_Product-endpoints.md)

**Post Conditions**

The product is successfully deleted or indicated it is no longer valid in the PSS datastore.

**Applicable Requirements**

@include [TOD-05-03-03 Remove Product Requirements](requirements/TOD-05-03-03-Remove_Product-requirements.md)

**eTOM Reference**

The operation is based on the 1.2.11 process identifier from the eTOM.
