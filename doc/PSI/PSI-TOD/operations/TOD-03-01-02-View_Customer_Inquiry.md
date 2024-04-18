=begin

# TOD-03-01-02-View_Customer_Inquiry

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
  rectangle "View Customer Inquiry" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --       	Operation
PSS1 --	    I3
I3 --       	Operation

@enduml

```

![**TOD-03-01-02**: View Customer Inquiry](../../common/pixel.png){#fig:TOD-03-01-02-View_Customer_Inquiry}

**Prerequisites**

The customer inquiry exists in the PSS datastore.

**Main operation**

Gets a customer inquiry of the customer with a specific identifier via a standard interface specification.

**REST Endpoints**

@include [TOD-03-01-02 View Customer Inquiry Endpoints](endpoints/TOD-03-01-02-View_Customer_Inquiry-endpoints.md)

**Post Conditions**

The customer inquiry is successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-03-01-02 View Customer Inquiry Requirements](requirements/TOD-03-01-02-View_Customer_Inquiry-requirements.md)

**eTOM Reference**

The operation is based on 1.3.5.6 process identifiers from the eTOM.

