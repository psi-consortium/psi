=begin

# TOD-04-01-03-Remove_Resource_Template

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
  rectangle "Remove Resource Template" as Operation
}

Governance --   I1
I1 --           Operation

@enduml

```

![**TOD-04-01-03**: Remove Resource Template](../../common/pixel.png){#fig:TOD-04-01-03-Remove_Resource_Template}

**Prerequisites**

The resource template exists in the PSS datastore.

**Main operation**

Removes a resource template either by deleting it or indicating it is no longer valid, via a standard interface specification available to the governance only.

**REST Endpoints**

@include [TOD-04-01-03 Remove Resource Template Endpoints](endpoints/TOD-04-01-03-Remove_Resource_Template-endpoints.md)

**Post Conditions**

The resource template is successfully deleted or indicated it is no longer valid in the PSS datastore.

**Applicable Requirements**

@include [TOD-04-01-03 Remove Resource Template Requirements](requirements/TOD-04-01-03-Remove_Resource_Template-requirements.md)

**eTOM Reference**

None
