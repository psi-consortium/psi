=begin

# TOD-03-02-03-View_Product_Order

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

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PVD I/F" as I2
}

package "PSS" {
  rectangle "View Product Order" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![**TOD-03-02-03**: View Product Order](../../common/pixel.png){#fig:TOD-03-02-03-View_Product_Order}

**Prerequisites**

The product order exists in the PSS datastore.

**Main operation**

Gets a product order with a specific identifier via a standard interface specification.
The customer and the provider can request to view the product order from the PSS.

**REST Endpoints**

@include [TOD-03-02-03 View Product Order Endpoints](endpoints/TOD-03-02-03-View_Product_Order-endpoints.md)

**Post Conditions**

The product order that the customer or provider can read, is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-03-02-03 View Product Order Requirements](requirements/TOD-03-02-03-View_Product_Order-requirements.md)

**eTOM Reference**

None
