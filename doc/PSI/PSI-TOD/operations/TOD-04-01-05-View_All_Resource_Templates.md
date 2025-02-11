=begin

# TOD-04-01-05-View_All_Resource_Templates

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
  rectangle "View All Resource Templates" as Operation
}

Provider --	    I1
I1 --           Operation
Governance --   I2
I2 --           Operation
Customer --     I3
I3 --           Operation

@enduml

```

![TOD-04-01-05: View All Resource Templates](../../common/pixel.png){#fig:TOD-04-01-05-View_All_Resource_Templates}

**Prerequisites**

Resource templates exist in the PSS datastore.

**Main operation**

Gets all resource templates via a standard interface specification.
These can be filtered by name and resource type.

**REST Endpoints**

@include [TOD-04-01-05 View All Resource Templates Endpoints](endpoints/TOD-04-01-05-View_All_Resource_Templates-endpoints.md)

**Post Conditions**

All resource templates matching the filter criteria are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-04-01-05 View All Resource Templates Requirements](requirements/TOD-04-01-05-View_All_Resource_Templates-requirements.md)

**eTOM Reference**

None
