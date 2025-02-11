=begin

# TOD-01-03-05-View_All_Documents

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
  interface "PSS-USR I/F" as I1
  interface "PSS-PVD I/F" as I2
  interface "PSS-PSS I/F" as I3
}

package "PSS" {
  rectangle "View All Documents" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation
PSS1 --         I3
I3 --           Operation

@enduml

```

![TOD-01-03-05: View All Documents](../../common/pixel.png){#fig:TOD-01-03-05-View_All_Documents}

**Prerequisites**

Documents exist in the PSS datastore.

**Main operation**

Gets all documents that are applicable to the user requesting them.

**REST Endpoints**

@include [TOD-01-03-05 View All Documents Endpoints](endpoints/TOD-01-03-05-View_All_Documents-endpoints.md)

**Post Conditions**

All documents of the provider are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-01-03-05 View All Documents Requirements](requirements/TOD-01-03-05-View_All_Documents-requirements.md)

**eTOM Reference**

None
