=begin

# TOD-06-01-Service_Level_Objective_Management

> The heading has to be included in the document including this document.

=end

The Service Level Objective Management task takes care of the maintenance of service level objectives (SLOs) in the PSS.
These SLOs direct and control the performance of services to meet the expectations of the customers.
They are combined in SLSs (see [TOD-06-02](#tod-06-02-servicelevelspecificationmanagement)), which are part of service specifications and product offerings.

Expected performance of the service is defined by thresholds attached to key indicators.
The service provider creates an SLO based on the key indicators (KPIs/KQIs) which are pre-defined by the governance (see [TOD-06-03](#tod-06-03-keyindicatormanagement)).

```plantuml
@startuml

usecase "SLO Management" as Task
rectangle "Create SLO"       as O1
rectangle "Update SLO"       as O2
rectangle "Remove SLO"       as O3
rectangle "View SLO"	     as O4
rectangle "View All SLOs"    as O5

Task  ..> O1    : <<includes>>
Task  ..> O2    : <<includes>>
Task  ..> O3    : <<includes>>
Task  ..> O4    : <<includes>>
Task  ..> O5    : <<includes>>
@enduml
```

![TOD-06-01: Service Level Objective (SLO) Management](../../common/pixel.png){#fig:TOD-06-01-Service_Level_Objective_Management}

|                   |  Customer  |  Provider  | Other PSS | Governance |
|-------------------|:----------:|:----------:|:---------:|:----------:|
| **Create SLO**    |            | \checkmark |           |            |
| **Update SLO**    |            | \checkmark |           |            |
| **Remove SLO**    |            | \checkmark |           |            |
| **View SLO**      | \checkmark | \checkmark |           | \checkmark |
| **View All SLOs** | \checkmark | \checkmark |           | \checkmark |

Table: Service Level Objective Management Matrix. {#tbl:service-level-objective-management-matrix}

**eTOM Reference**

The task is based on the 1.4.7 process identifier from the eTOM.
