=begin

# TOD-05-03-04-View_Product

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
  rectangle "View Product" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation
Customer --     I3
I3 --           Operation

@enduml

```

![TOD-05-03-04: View Product](../../common/pixel.png){#fig:TOD-05-03-04-View_Product}

**Prerequisites**

The product exists in the PSS datastore.

**Main operation**

Gets a product with a specific identifier via a standard interface.
Customers can only see the products they booked and providers can only see the products they have created.

**REST Endpoints**

@include [TOD-05-03-04 View Product Endpoints](endpoints/TOD-05-03-04-View_Product-endpoints.md)

**Post Conditions**

The product is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-05-03-04 View Product Requirements](requirements/TOD-05-03-04-View_Product-requirements.md)

**eTOM Reference**

The operation is based on the 1.2.11 process identifier from the eTOM.
