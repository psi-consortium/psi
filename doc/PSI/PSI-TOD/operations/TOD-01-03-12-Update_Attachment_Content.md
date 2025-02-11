=begin

# TOD-01-03-12-Update_Attachment_Content

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
  rectangle "Update Attachment Content" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![TOD-01-03-12: Update Attachment Content](../../common/pixel.png){#fig:TOD-01-03-12-Update_Attachment_Content}

**Prerequisites**

The attachment whose binary content is to be updated exists in the PSS.

**Main operation**

This operation updates the actual binary content of an existing attachment in the PSS via a standard interface specification.
It means that the system implementing the interface updates the file in the physical location or file storage system.

**REST Endpoints**

@include [TOD-01-03-12 Update Attachment Content Endpoints](endpoints/TOD-01-03-12-Update_Attachment_Content-endpoints.md)

**Post Conditions**

The binary content of the attachment has been updated.

**Applicable Requirements**

@include [TOD-01-03-12 Update Attachment Content Requirements](requirements/TOD-01-03-12-Update_Attachment_Content-requirements.md)

**eTOM Reference**

None
