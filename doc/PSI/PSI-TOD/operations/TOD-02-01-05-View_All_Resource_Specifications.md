=begin

# TOD-02-01-05-View_All_Resource_Specifications

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

rectangle "PSI" {
  interface "PSS-PVD I/F" as I1
  interface "PSS-PSS I/F" as I2
  interface "PSS-USR I/F" as I3
}

package "PSS" {
  rectangle "View All Resource Specifications" as Operation
}

Provider --	    I1
I1 --           Operation
PSS1 --         I2
I2 --           Operation
Customer --     I3
I3 --           Operation

@enduml

```

![**TOD-02-01-05**: View All Resource Specifications](../../common/pixel.png){#fig:TOD-02-01-05-View_All_Resource_Specifications}

**Prerequisites**

Resource specifications of the provider exist in the PSS datastore.

**Main operation**

Gets all resource specifications of the provider via a standard interface specification.
These can be filtered at least by resource type.
Customers can view their own declared resource specifications.

**REST Endpoints**

@include [TOD-02-01-05 View All Resource Specifications Endpoints](endpoints/TOD-02-01-05-View_All_Resource_Specifications-endpoints.md)

**Post Conditions**

All resource specifications of the provider are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-02-01-05 View All Resource Specifications Requirements](requirements/TOD-02-01-05-View_All_Resource_Specifications-requirements.md)

**eTOM Reference**

The operation is based on 1.5.17.1 and 1.5.19.2 process identifiers from the eTOM.
