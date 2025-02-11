=begin

# TOD-06-06-01-View_Alarm

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

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PSS I/F" as I2
}

package "PSS" {
  rectangle "View Alarm" as Operation
}

Customer --  I1
I1 --  Operation
PSS1 -- I2
I2 -- Operation

@enduml

```

![**TOD-06-06-01**: View Alarm](../../common/pixel.png){#fig:TOD-06-06-01-View_Alarm}

**Prerequisites**

The Alarm exists in the PSS datastore.

**Main operation**

Gets an Alarm instance via a standard interface.

**REST Endpoints**

@include [TOD-06-06-01 View Alarm](endpoints/TOD-06-06-01-View_Alarm-endpoints.md)

**Post Conditions**

The Alarm is successfully returned for viewing.

**Applicable Requirements**

@include [TOD-06-06-01 View Alarm](requirements/TOD-06-06-01-View_Alarm-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.6 and 1.5.8 process identifier from the eTOM.
