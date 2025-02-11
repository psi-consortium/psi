=begin

# TOD-01-01-01-Create_Party_Profile

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
Governance << 3 >>

rectangle "PSI" {
  interface "PSS-USR I/F" as I1
  interface "PSS-PVD I/F" as I2
  interface "PSS-PSS I/F" as I3
  interface "PSS-GOV I/F" as I4
}

package "PSS" {
  rectangle "Create Party Profile" as Operation
}

Customer --	    I1
I1 --           Operation
Provider --	    I2
I2 --           Operation
PSS1 --         I3
I3 --           Operation
Governance --   I4
I4 --           Operation

@enduml

```

![TOD-01-01-01: Create Party Profile](../../common/pixel.png){#fig:TOD-01-01-01-Create_Party_Profile}

**Prerequisites**

The party has no profile.

**Main operation**

Creates a profile for a party with basic party data, identification data, contact data and additional attributes, via a standard interface specification.

The party can be an individual or an organization.

**REST Endpoints**

@include [TOD-01-01-01 Create Party Profile Endpoints](endpoints/TOD-01-01-01-Create_Party_Profile-endpoints.md)

**Post Conditions**

The profile for the party is successfully created in the PSS datastore.

**Applicable Requirements**

@include [TOD-01-01-01 Create Party Profile Requirements](requirements/TOD-01-01-01-Create_Party_Profile-requirements.md)

**eTOM Reference**

The operation is based on 1.3.6.1 and 1.6.21.2 process identifiers from the eTOM.

