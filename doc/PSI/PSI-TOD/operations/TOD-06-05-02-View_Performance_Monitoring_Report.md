=begin

# TOD-06-05-02-View_Performance_Monitoring_Report

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
entity PSS << 2 >> as PSS1
Governance << 3 >>

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PSS I/F" as I2
  interface "PSS-GOV I/F" as I3
}

package "PSS" {
  rectangle "View Performance Monitoring Report" as Operation
}

Customer --	I1
I1 -- Operation
PSS1 -- I2
I2 -- Operation
Governance -- I3
I3 -- Operation

@enduml

```

![**TOD-06-05-02**: View Performance Monitoring Report](../../common/pixel.png){#fig:TOD-06-05-02-View_Performance_Monitoring_Report}

**Prerequisites**

The Performance Monitoring Report exists in the PSS datastore.

**Main operation**

Gets a Performance Monitoring Report instance via a standard interface.

**REST Endpoints**

@include [TOD-06-05-02 View Performance Monitoring Report](endpoints/TOD-06-05-02-View_Performance_Monitoring_Report-endpoints.md)

**Post Conditions**

The Performance Monitoring Report is successfully returned for viewing.

**Applicable Requirements**

@include [TOD-06-05-02 View Performance Monitoring Report](requirements/TOD-06-05-02-View_Performance_Monitoring_Report-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
