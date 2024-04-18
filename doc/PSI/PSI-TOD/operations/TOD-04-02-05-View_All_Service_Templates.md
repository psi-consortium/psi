=begin

# TOD-04-02-05-View_All_Service_Templates

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
  rectangle "View All Service Templates" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation

@enduml

```

![**TOD-04-02-05**: View All Service Templates](../../common/pixel.png){#fig:TOD-04-02-05-View_All_Service_Templates}

**Prerequisites**

Service templates exist in the PSS datastore.

**Main operation**

Gets all service template via a standard interface specification.
These can be filtered by name and service type.

**REST Endpoints**

@include [TOD-04-02-05 View All Service Templates Endpoints](endpoints/TOD-04-02-05-View_All_Service_Templates-endpoints.md)

**Post Conditions**

All service templates matching the filter criteria are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-04-02-05 View All Service Templates Requirements](requirements/TOD-04-02-05-View_All_Service_Templates-requirements.md)

**eTOM Reference**

None
