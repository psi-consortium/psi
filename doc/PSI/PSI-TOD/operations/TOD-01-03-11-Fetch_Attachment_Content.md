=begin

# TOD-01-03-11-Fetch_Attachment_Content

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
  rectangle "Fetch Attachment Content" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation
PSS1 --         I3
I3 --           Operation

@enduml

```

![TOD-01-03-11: Fetch Attachment Content](../../common/pixel.png){#fig:TOD-01-03-11-Fetch_Attachment_Content}

**Prerequisites**

The attachment whose binary content is to be fetched exists in the PSS.

**Main operation**

The operation fetches the actual binary content of the attachment from the PSS for direct preview.

**REST Endpoints**

@include [TOD-01-03-11 Fetch Attachment Content Endpoints](endpoints/TOD-01-03-11-Fetch_Attachment_Content-endpoints.md)

**Post Conditions**

The binary content of the attachment is successfully fetched for preview.

**Applicable Requirements**

@include [TOD-01-03-11 Fetch Attachment Content Requirements](requirements/TOD-01-03-11-Fetch_Attachment_Content-requirements.md)

**eTOM Reference**

None
