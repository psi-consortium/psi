=begin

# TOD-01-03-03-Remove_Document

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
  rectangle "Remove Document" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![**TOD-01-03-03**: Remove Document](../../common/pixel.png){#fig:TOD-01-03-03-Remove_Document}

**Prerequisites**

The document exists in the PSS datastore.

**Main operation**

Removes a document either by deleting it or indicating it is no longer valid, via a standard interface specification.

Additionally, all associated attachments of the document are deleted or marked as not valid.
The system implementing the interface should ensure that the attachments are removed from the physical location or file storage system.

**REST Endpoints**

@include [TOD-01-03-03 Remove Document Endpoints](endpoints/TOD-01-03-03-Remove_Document-endpoints.md)

**Post Conditions**

The document is successfully deleted or indicated it is no longer valid in the PSS datastore.

**Applicable Requirements**

@include [TOD-01-03-03 Remove Document Requirements](requirements/TOD-01-03-03-Remove_Document-requirements.md)

**eTOM Reference**

None
