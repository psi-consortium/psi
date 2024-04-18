=begin

# TOD-02-02-05-View_All_Service_Specifications

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
  interface "PSS-USR I/F" as I0
  interface "PSS-PVD I/F" as I1
  interface "PSS-PSS I/F" as I2
}

package "PSS" {
  rectangle "View All Service Specifications" as Operation
}

Customer --     I0
I0 --           Operation
Provider --	    I1
I1 --           Operation
PSS1 --         I2
I2 --           Operation

@enduml

```

![**TOD-02-02-05**: View All Service Specifications](../../common/pixel.png){#fig:TOD-02-02-05-View_All_Service_Specifications}

**Prerequisites**

Service specifications of the provider exist in the PSS datastore.

**Main operation**

Gets all service specifications of the provider via a standard interface specification.
These can be filtered at least by service type.

**REST Endpoints**

@include [TOD-02-02-05 View All Service Specifications Endpoints](endpoints/TOD-02-02-05-View_All_Service_Specifications-endpoints.md)

**Post Conditions**

All service specifications of the provider are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-02-02-05 View All Service Specifications Requirements](requirements/TOD-02-02-05-View_All_Service_Specifications-requirements.md)

**eTOM Reference**

The operation is based on 1.4.15.1 and 1.4.19.2 process identifiers from the eTOM.
