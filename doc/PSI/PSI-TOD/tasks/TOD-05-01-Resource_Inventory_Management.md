=begin

# TOD-05-01-Resource_Inventory_Management

> The heading has to be included in the document including this document.

=end

The Resource Inventory Management task takes care of the maintenance of resources in the PSS, brought in by providers or customers themselves.

Resources are created based on their specifications and reflect the actual characteristics of an existing instance.
There are two different scenarios to do this:

* For *on-demand* resources, the entity may or may not exist in the provider system.
  They are unknown to the PSS and are only queried via the Stock Management API (cf. [TOD-05-04](#tod-05-04-stockmanagement)).
  Only upon order acceptance (or even afterwards) it is created in the PSS and "lives" for the requested period of time.
* *Committed* resources are created in the PSS beforehand.
  This allows the PSS to internally check the availability in any given timespan.
  Every order (or reservation, if supported) will create a sub-entity that is related to the master-entity and handled like above.

```plantuml
@startuml
package Catalog as C_PSS {
    object "Specification" as SpecA_PSS {
    }
}
package Inventory as I_PSS {
    object "Instance 1" as I1_PSS {
        validFor = [period of commitment]
        relatedParty = [prov]
    }
    object "Instance 1a" as I1a_PSS {
        validFor = [period of mission a]
        status = "booked"
        relatedParty = [prov, cusA]
    }
    object "Instance 1b" as I1b_PSS {
        validFor = [period of mission b]
        status = "reserved"
        relatedParty = [prov, cusB]
    }

    object "Instance 2" as I2_PSS {
        validFor = [period of commitment]
        relatedParty = [prov]
    }
}

SpecA_PSS --> I1_PSS
I1_PSS --> I1a_PSS : \trelatedEntity
I1_PSS --> I1b_PSS
I1a_PSS -[hidden]> I1b_PSS
I1_PSS -[hidden]> I2_PSS

SpecA_PSS --> I2_PSS
@enduml
```

![TOD-05-01: Tree of Resources.](../../common/pixel.png){#fig:resource_tree}

Depending on the implementation, the resource can be further subdivided, e.g. if it is shared with another user or resold by a service provider.
If the resource instance is offered to customers, it has to be wrapped in a product instance resembling their corresponding specifications.

```plantuml
@startuml

usecase "Resource Inventory Management"          as Task
rectangle "Create Resource"      as O1
rectangle "Update Resource"      as O2
rectangle "Remove Resource"      as O3
rectangle "View Resource"	     as O4
rectangle "View All Resources"    as O5

Task  ..> O1    : <<includes>>
Task  ..> O2    : <<includes>>
Task  ..> O3    : <<includes>>
Task  ..> O4    : <<includes>>
Task  ..> O5    : <<includes>>
@enduml
```

![TOD-05-01: Resource Inventory Management](../../common/pixel.png){#fig:TOD-05-01-Resource_Inventory_Management}

|                       |  Customer  |  Provider  | Other PSS | Governance |
|-----------------------|:----------:|:----------:|:---------:|:----------:|
| **Create Resource**   | \checkmark | \checkmark |           |            |
| **Update Resource**   | \checkmark | \checkmark |           |            |
| **Remove Resource**   | \checkmark | \checkmark |           |            |
| **View Resource**     | \checkmark | \checkmark |           | \checkmark |
| **View All Resource** | \checkmark | \checkmark |           | \checkmark |

Table: Resource Inventory Management Matrix. {#tbl:resource-inventory-management-matrix}

**Applicable Requirements**

@include [TOD-05-01 Resource Inventory Management Requirements](requirements/TOD-05-01-Resource_Inventory_Management-requirements.md)

**eTOM Reference**

The task is based on the 1.5.4.5 process identifier from the eTOM.
