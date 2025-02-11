=begin

# TOD-02-03-04-View_Product_Specification

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
entity PSS << 2 >> as PSS1
Governance << 3 >>

rectangle "PSI" {
  interface "PSS-USR I/F" as I0
  interface "PSS-PVD I/F" as I1
  interface "PSS-PSS I/F" as I2
  interface "PSS-GOV I/F" as I4
}

package "PSS" {
  rectangle "View Product Specification" as Operation
}

Customer --	    I0
I0 --           Operation
Provider --	    I1
I1 --           Operation
PSS1 --         I2
I2 --           Operation
Governance --   I4
I4 --           Operation

@enduml

```

![TOD-02-03-04: View Product Specification](../../common/pixel.png){#fig:TOD-02-03-04-View_Product_Specification}

**Prerequisites**

The product specification exists in the PSS datastore.

**Main operation**

Gets a product specification of the provider with a specific identifier via a standard interface specification.

**REST Endpoints**

@include [TOD-02-03-04 View Product Specification Endpoints](endpoints/TOD-02-03-04-View_Product_Specification-endpoints.md)

**Post Conditions**

The product specification is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-02-03-04 View Product Specification Requirements](requirements/TOD-02-03-04-View_Product_Specification-requirements.md)

**eTOM Reference**

The operation is based on 1.2.22.1 and 1.2.23.2 process identifiers from the eTOM.
