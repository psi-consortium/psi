=begin

# TOD-03-03-02-Update_Customer_Bill

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

Provider << 1 >>

rectangle "PSI" {
  interface "PSS-PVD I/F" as I1
}

package "PSS" {
  rectangle "Update Customer Bill" as Operation
}

Provider --	    I1
I1 --           Operation

@enduml

```

![**TOD-03-03-02**: Update Customer Bill](../../common/pixel.png){#fig:TOD-03-03-02-Update_Customer_Bill}

**Prerequisites**

The customer bill exists in the PSS datastore.

**Main operation**

The provider updates the state of an existing customer bill in the PSS via a standard interface specification.
It should not be possible to update the customer bill's properties that affect its traceability for tax reasons.

**REST Endpoints**

@include [TOD-03-03-02 Update Customer Bill Endpoints](endpoints/TOD-03-03-02-Update_Customer_Bill-endpoints.md)

**Post Conditions**

The customer bill is successfully updated in the PSS.

**Applicable Requirements**

@include [TOD-03-03-02 Update Customer Bill Requirements](requirements/TOD-03-03-02-Update_Customer_Bill-requirements.md)

**eTOM Reference**

The operation is based on 1.3.9.4.3 process identifier from the eTOM.

