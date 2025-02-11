=begin

# TOD-02-03-05-View_All_Product_Specifications

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
  rectangle "View All Product Specifications" as Operation
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

![TOD-02-03-05: View All Product Specifications](../../common/pixel.png){#fig:TOD-02-03-05-View_All_Product_Specifications}

**Prerequisites**

Product specifications of the provider exist in the PSS datastore.

**Main operation**

Gets all product specifications of the provider via a standard interface specification.

**REST Endpoints**

@include [TOD-02-03-05 View All Product Specifications Endpoints](endpoints/TOD-02-03-05-View_All_Product_Specifications-endpoints.md)

**Post Conditions**

All product specifications of the provider are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-02-03-05 View All Product Specifications Requirements](requirements/TOD-02-03-05-View_All_Product_Specifications-requirements.md)

**eTOM Reference**

The operation is based on 1.2.22.1 and 1.2.23.2 process identifiers from the eTOM.
