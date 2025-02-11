=begin

# TOD-06-04-02-Update_Performance_Monitoring_Job

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
  rectangle "Update Performance Monitoring Job" as Operation
}

Customer --	    I1
I1 --           Operation
PSS1 --   I2
I2 --           Operation

@enduml

```

![**TOD-06-04-02**: Update Performance Monitoring Job](../../common/pixel.png){#fig:TOD-06-02-02-Update_Performance_Monitoring_Job}

**Prerequisites**

The Performance Monitoring Job exists in the PSS datastore.

**Main operation**

Updates a Performance Monitoring Job instance via a standard interface. 
Suspends/Resumes a Performance Monitoring Job instance via a standard interface. 

**REST Endpoints**

@include [TOD-06-04-02 Update Performance Monitoring Job](endpoints/TOD-06-04-02-Update_Performance_Monitoring_Job-endpoints.md)

**Post Conditions**

The Performance Monitoring Job is successfully updated in the PSS datastore.

**Applicable Requirements**

@include [TOD-06-04-02 Update Performance Monitoring Job](requirements/TOD-06-04-02-Update_Performance_Monitoring_Job-requirements.md)

**eTOM Reference**

The operation is based on the 1.4.7 process identifier from the eTOM.
