=begin

# TOD-06-03-03-Remove_Key_Indicator

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

Governance << 3 >>

rectangle "PSI" {
  interface "PSS-GOV I/F" as I4
}

package "PSS" {
  rectangle "Remove Key Indicator" as Operation
}

Governance --	    I4
I4 --           Operation

@enduml

```

![**TOD-06-03-03**: Remove Key Indicator](../../common/pixel.png){#fig:TOD-06-03-03-Remove_Key_Indicator}

**Prerequisites**

The Key Indicator exists in the PSS datastore and is not used by available Service Level Objective.

**Main operation**

Removes a Key Indicator instance via a standard interface.

**REST Endpoints**

@include [TOD-06-03-03 Remove Key Indicator](endpoints/TOD-06-03-03-Remove_Key_Indicator-endpoints.md)

**Post Conditions**

The Key Indicator is successfully deleted or indicated it is no longer valid in the PSS datastore.

**Applicable Requirements**

@include [TOD-06-03-03 Remove Key Indicator](requirements/TOD-06-03-03-Remove_Key_Indicator-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
