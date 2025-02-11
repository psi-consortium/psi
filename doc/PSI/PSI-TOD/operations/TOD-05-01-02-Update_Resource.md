=begin

# TOD-05-01-02-Update_Resource

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
  interface "PSS-PVD I/F" as I1
  interface "PSS-USR I/F" as I2
}

package "PSS" {
  rectangle "Update Resource" as Operation
}

Provider --	    I1
I1 --           Operation
Customer --     I2
I2 --           Operation

@enduml

```

![TOD-05-01-02: Update Resource](../../common/pixel.png){#fig:TOD-05-01-02-Update_Resource}

**Prerequisites**

The resource exists in the PSS datastore.

**Main operation**

Updates an existing resource via a standard interface.

**REST Endpoints**

@include [TOD-05-01-02 Update Resource Endpoints](endpoints/TOD-05-01-02-Update_Resource-endpoints.md)

**Post Conditions**

The resource is successfully updated in the PSS datastore.

**Applicable Requirements**

@include [TOD-05-01-02 Update Resource Requirements](requirements/TOD-05-01-02-Update_Resource-requirements.md)

**eTOM Reference**

The operation is based on the 1.5.4.5.1 process identifier from the eTOM.
