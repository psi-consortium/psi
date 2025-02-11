=begin

# TOD-01-03-07-Update_Attachment

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
  rectangle "Update Attachment" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation

@enduml

```

![TOD-01-03-07: Update Attachment](../../common/pixel.png){#fig:TOD-01-03-07-Update_Attachment}

**Prerequisites**

The attachment to be updated exists in the PSS.

**Main operation**

This operation updates the properties of an existing attachment in a PSS via a standard interface specification.

When updating the *content*, the system implementing the interface should ensure that the file is also updated in the physical location or file storage system.

**REST Endpoints**

@include [TOD-01-03-07 Update Attachment Endpoints](endpoints/TOD-01-03-07-Update_Attachment-endpoints.md)

**Post Conditions**

The attachment has been successfully updated in the PSS.

**Applicable Requirements**

@include [TOD-01-03-07 Update Attachment Requirements](requirements/TOD-01-03-07-Update_Attachment-requirements.md)

**eTOM Reference**

None
