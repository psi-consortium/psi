=begin

# TOD-04-01-01-Create_Resource_Template

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

Governance << 3 >>

rectangle "PSI" {
  interface "PSS-GOV I/F" as I1
}

package "PSS" {
  rectangle "Create Resource Template" as Operation
}

Governance --   I1
I1 --           Operation

@enduml

```

![TOD-04-01-01: Create Resource Template](../../common/pixel.png){#fig:TOD-04-01-01-Create_Resource_Template}

**Prerequisites**

The resource template does not exist in the PSS datastore.

**Main operation**

The governance creates a new resource template in the PSS with predefined field values for a resource specification of a given resource type (e.g. modem, antenna).
The resource template can then be used by providers to register a resource specification by replacing the default values in the template with their resource specific values.

**REST Endpoints**

@include [TOD-04-01-01 Create Resource Template Endpoints](endpoints/TOD-04-01-01-Create_Resource_Template-endpoints.md)

**Post Conditions**

The resource template is successfully created in the PSS datastore.

**Applicable Requirements**

@include [TOD-04-01-01 Create Resource Template Requirements](requirements/TOD-04-01-01-Create_Resource_Template-requirements.md)

**eTOM Reference**

None
