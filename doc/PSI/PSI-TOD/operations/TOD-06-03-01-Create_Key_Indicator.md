=begin

# TOD-06-03-01-Create_Key_Indicator

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
  rectangle "Create Key Indicator" as Operation
}

Governance --	    I4
I4 --           Operation

@enduml

```

![**TOD-06-03-01**: Create Key Indicator](../../common/pixel.png){#fig:TOD-06-03-01-Create_Key_Indicator}

**Prerequisites**

The key indicator does not exist in the PSS datastore.

**Main operation**

Creates a new key indicator instance via a standard interface.

Some properties of a key indicator are:

* *name* - Short name of the key indicator
* *indicatorType* - KPI or KQI
* *isBundled* - A boolean that specifies whether the key indicator represents a single key indicator (false) or a key indicator that represents an aggregation (true)
* *validFor* - The validity of the key indicator
* *keyIndicatorRelationship* - A list of key indicator relationships related to this object

**REST Endpoints**

@include [TOD-06-03-01 Create Key Indicator](endpoints/TOD-06-03-01-Create_Key_Indicator-endpoints.md)

**Post Conditions**

The key indicator is successfully created in the PSS datastore.

**Applicable Requirements**

@include [TOD-06-03-01 Create Key Indicator](requirements/TOD-06-03-01-Create_Key_Indicator-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
