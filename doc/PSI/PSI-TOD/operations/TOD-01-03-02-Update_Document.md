=begin

# TOD-01-03-02-Update_Document

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

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PVD I/F" as I2
}

package "PSS" {
  rectangle "Update Document" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![**TOD-01-03-02**: Update Document](../../common/pixel.png){#fig:TOD-01-03-02-Update_Document}

**Prerequisites**

The document exists in the PSS datastore.

**Main operation**

Updates an existing document via a standard interface specification.

**REST Endpoints**

@include [TOD-01-03-02 Update Document Endpoints](endpoints/TOD-01-03-02-Update_Document-endpoints.md)

**Post Conditions**

The document is successfully updated in the PSS datastore.

**Applicable Requirements**

@include [TOD-01-03-02 Update Document Requirements](requirements/TOD-01-03-02-Update_Document-requirements.md)

**eTOM Reference**

None
