=begin

# TOD-03-03-03-View_Customer_Bill

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
  rectangle "View Customer Bill" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![**TOD-03-03-03**: View Customer Bill](../../common/pixel.png){#fig:TOD-03-03-03-View_Customer_Bill}

**Prerequisites**

The customer bill exists in the PSS datastore.

**Main operation**

Gets a customer bill with a specific identifier via a standard interface specification.

**REST Endpoints**

@include [TOD-03-03-03 View Customer Bill Endpoints](endpoints/TOD-03-03-03-View_Customer_Bill-endpoints.md)

**Post Conditions**

The customer bill that the customer or the provider can read, is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-03-03-03 View Customer Bill Requirements](requirements/TOD-03-03-03-View_Customer_Bill-requirements.md)

**eTOM Reference**

The operation is based on 1.3.9.2.2 process identifier from the eTOM.


