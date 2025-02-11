=begin

# TOD-04-03-04-View_Product_Template

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
Governance << 3 >> 

rectangle "PSI" {
  interface "PSS-PVD I/F" as I1
  interface "PSS-GOV I/F" as I2
}

package "PSS" {
  rectangle "View Product Template" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation

@enduml

```

![TOD-04-03-04: View Product Template](../../common/pixel.png){#fig:TOD-04-03-04-View_Product_Template}

**Prerequisites**

The product template exists in the PSS datastore.

**Main operation**

Gets a product template with a specific identifier via a standard interface specification.

**REST Endpoints**

@include [TOD-04-03-04 View Product Template Endpoints](endpoints/TOD-04-03-04-View_Product_Template-endpoints.md)

**Post Conditions**

The product template is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-04-03-04 View Product Template Requirements](requirements/TOD-04-03-04-View_Product_Template-requirements.md)

**eTOM Reference**

None
