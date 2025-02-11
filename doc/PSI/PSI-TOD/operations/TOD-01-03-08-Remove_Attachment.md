=begin

# TOD-01-03-08-Remove_Attachment

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
  rectangle "Remove Attachment" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![TOD-01-03-08: Remove Attachment](../../common/pixel.png){#fig:TOD-01-03-08-Remove_Attachment}

**Prerequisites**

The attachment to be removed exists in the PSS.

**Main operation**

Removes an attachment either by deleting it or indicating it is no longer valid, via a standard interface specification.

The system implementing the interface should ensure that the attachment is removed also from the physical location or the file storage system.

**REST Endpoints**

@include [TOD-01-03-08 Remove Attachment Endpoints](endpoints/TOD-01-03-08-Remove_Attachment-endpoints.md)

**Post Conditions**

The attachment has been deleted or indicated it is no longer valid in the PSS.

**Applicable Requirements**

@include [TOD-01-03-08 Remove Attachment Requirements](requirements/TOD-01-03-08-Remove_Attachment-requirements.md)

**eTOM Reference**

None
