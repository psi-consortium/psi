=begin

# TOD-04-02-04-View_Service_Template

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
  rectangle "View Service Template" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation

@enduml

```

![**TOD-04-02-04**: View Service Template](../../common/pixel.png){#fig:TOD-04-02-04-View_Service_Template}

**Prerequisites**

The service template exists in the PSS datastore.

**Main operation**

Gets a service template with a specific identifier via a standard interface specification.

**REST Endpoints**

@include [TOD-04-02-04 View Service Template Endpoints](endpoints/TOD-04-02-04-View_Service_Template-endpoints.md)

**Post Conditions**

The service template is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-04-02-04 View Service Template Requirements](requirements/TOD-04-02-04-View_Service_Template-requirements.md)

**eTOM Reference**

None
