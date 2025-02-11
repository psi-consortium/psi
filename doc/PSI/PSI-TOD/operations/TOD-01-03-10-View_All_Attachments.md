=begin

# TOD-01-03-10-View_All_Attachments

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
  rectangle "View All Attachments" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation
PSS1 --         I3
I3 --           Operation

@enduml
```

![TOD-01-03-10: View All Attachments](../../common/pixel.png){#fig:TOD-01-03-10-View_All_Attachments}

**Prerequisites**

Attachments exist in the PSS.

**Main operation**

Gets a list of all attachments that are applicable to the user requesting them.

**REST Endpoints**

@include [TOD-01-03-10 View All Attachments Endpoints](endpoints/TOD-01-03-10-View_All_Attachments-endpoints.md)

**Post Conditions**

The list of all available attachments is successfully returned.

**Applicable Requirements**

@include [TOD-01-03-10 View All Attachments Requirements](requirements/TOD-01-03-10-View_All_Attachments-requirements.md)

**eTOM Reference**

None
