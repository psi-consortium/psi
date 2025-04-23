=begin

# TOD-06-04-Performance_Monitoring_Job_Management

> The heading has to be included in the document including this document.

=end

The Performance Monitoring Job Management task takes care of the maintenance of PM jobs in the PSS.

The performance monitoring job is responsible for provisioning appropriate measurement points, and performance objectives, together with measurement intervals and schedules, to measure applicable KPIs/KQIs specific to the service and defined by service level objectives.

Performance monitoring jobs are typically associated with an SLS but can be used for an on-demand performance measurement that is initiated for a limited time, typically a single run or non-continuous run, to carry out the performance measurement tests and support troubleshooting during service assurance.
Management of a performance monitoring job is depicted in the following diagram.

```plantuml
@startuml
participant PSS
participant Provider as PVD

autonumber

...
PSS -> PVD  : POST http://pss.com/psi-api/performanceMonitoring/v2/performanceJob\n{"jobType":"on-demand",<job_configuration>}
activate PVD
PVD --> PSS: {"id": "730ac707-6d2b-47ac-a903-2ed7808e5bec"}

PVD -> PVD: generate and collect data
...
note over PVD: reporting period elapses
PVD -> PVD: create performance report
deactivate PVD
...

note over PVD: reporting continues\nbased on job definition

PSS -> PVD ++ : GET http://pss.com/psi-api/performanceMonitoring/v2/performanceReport
return: {["id":"b2a7ef8d-b218-4841-8584-bec2b6d1af4d",\n "id":"05cd940c-6524-483b-9043-f8a4589311c4"]}

PSS -> PVD ++ : GET http://pss.com/psi-api/performanceMonitoring/v2/performanceReport/b2a...
return {"id": "b2a7ef8d-b218-4841-8584-bec2b6d1af4d",\n"reportContent": "..."}

@enduml
```

![**TOD-06-04**: Performance Monitoring Job Sequence](../../common/pixel.png){#fig:TOD-06-04-Performance_Monitoring_Job-sequence}

Operations applicable to the performance monitoring job are listed below.

```plantuml
@startuml

usecase "PM Job Management" as Task
rectangle "Create PM Job"       as O1
rectangle "Update PM Job"       as O2
rectangle "Cancel PM Job"       as O3
rectangle "View PM Job"	        as O4
rectangle "View All PM Jobs"    as O5

Task  ..> O1    : <<includes>>
Task  ..> O2    : <<includes>>
Task  ..> O3    : <<includes>>
Task  ..> O4    : <<includes>>
Task  ..> O5    : <<includes>>
@enduml
```

![**TOD-06-04**: Performance Monitoring Job Management](../../common/pixel.png){#fig:TOD-06-04-Performance_Monitoring_Job_Management}

|                      |  Customer  |  Provider  | Other PSS  | Governance |
|----------------------|:----------:|:----------:|:----------:|:----------:|
| **Create PM Job**    | \checkmark |            | \checkmark |            |
| **Update PM Job**    | \checkmark |            | \checkmark |            |
| **Cancel PM Job**    | \checkmark |            | \checkmark |            |
| **View PM Job**      | \checkmark |            | \checkmark | \checkmark |
| **View All PM Jobs** | \checkmark |            | \checkmark | \checkmark |

Table: Performance Monitoring Job Management Matrix. {#tbl:performance-monitoring-job-management-matrix}

**eTOM Reference**

The task is based on the 1.4.7 process identifier from the eTOM.
