=begin

# TOD-01-01-05-View_All_Party_Profiles

> The heading has to be included in the document including this document.

=end


```plantuml
@startuml
skinparam actor {
  BackgroundColor<< 0 >>    SteelBlue
  BorderColor<< 0 >>        SteelBlue
  BackgroundColor<< 1 >>    Green
  BorderColor<< 1 >>        Green
  BackgroundColor<< 3 >>    Red
  BorderColor<< 3 >>        Red
}

skinparam entity{
  BackgroundColor<< 2 >>   Orange
  BorderColor<< 2 >>       Orange
}

Customer << 0 >>
Provider << 1 >>
entity PSS << 2 >> as PSS1
Governance << 3 >>

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PVD I/F" as I2
  interface "PSS-PSS I/F" as I3
  interface "PSS-GOV I/F" as I4
}

package "PSS" {
  rectangle "View All Party Profiles" as Operation
}

Customer --     I1
I1 --           Operation
Provider --     I2
I2 --           Operation
PSS1 --         I3
I3 --           Operation
Governance --   I4
I4 --           Operation

@enduml

```


![TOD-01-01-05: View All Party Profiles](../../common/pixel.png){#fig:TOD-01-01-05-View_All_Party_Profiles}

**Prerequisites**

Party profiles exist in the PSS datastore.

**Main operation**

Retrieves associated party profiles.
Access and visibility restrictions are the responsibility of the implementing PSS.

The Governance, Customer, Provider, and PSS can retrieve all party profiles via this interface.

**REST Endpoints**

@include [TOD-01-01-05 View All Party Profiles Endpoints](endpoints/TOD-01-01-05-View_All_Party_Profiles-endpoints.md)

**Post Conditions**

All visible party profiles are successfully returned to be viewed.

**Applicable Requirements**

@include [TOD-01-01-05 View All Party Profiles Requirements](requirements/TOD-01-01-05-View_All_Party_Profiles-requirements.md)

**eTOM Reference**

The operation is based on 1.3.6.5 and 1.6.21.2 process identifiers from the eTOM.

