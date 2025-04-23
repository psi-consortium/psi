=begin

# TOD-06-05-Performance_Monitoring_Report_Management

> The heading has to be included in the document including this document.

=end

The Performance Monitoring Report Management task takes care of the maintenance of PM reports in the PSS.
Performance Monitoring Reports show data collected by the service provider.
This data can be generated in two ways.
First, when a service with an attached SLS is provisioned, the provider starts collecting performance measurements related to the SLS.
In order to get the performance data, the PSS can request an ad-hoc report creation which is depicted in the diagram below.

```plantuml
@startuml
participant Customer
participant PSS
participant Provider as PVD

autonumber

Customer -> PSS ++: POST https://pss.com/psi-api/productOrdering/v2/productOrder
PSS -> Customer: {"id": "620b78f9-c3ab-4869-8956-6cb88c27e434"}
note over PSS: Order with attached SLS fulfillment
Customer -> PSS: GET https://pss.com/psi-api/productOrdering/v2/productOrder/620b78f9-c3ab-4869-8956-6cb88c27e434
return {"state": "Completed"}

note over PVD: Start performance monitoring
...
PSS -> PVD ++ : POST http://pss.com/psi-api/performanceMonitoring/v2/performanceReport\n{<report_configuration>}
return {"id": "c285ce32-3cf1-4d47-9fc5-0bff88a196c6",\n"reportContent": "..."}

@enduml
```

![**TOD-06-05**: Performance Monitoring Report Sequence](../../common/pixel.png){#fig:TOD-06-05-Performance_Monitoring_Report-sequence}

A second option is to create an on-demand Performance Monitoring Job which will collect data and put it in the report (see [TOD-06-04](#tod-06-04-performance_monitoring_job_management)).

Operations available for managing a Performance Monitoring Report are listed in the following diagram.

```plantuml
@startuml

usecase "PM Report Management"   as Task
rectangle "Create PM Report"	 as O1
rectangle "View PM Report"	     as O2
rectangle "View All PM Reports"  as O3

Task  ..> O1    : <<includes>>
Task  ..> O2    : <<includes>>
Task  ..> O3    : <<includes>>
@enduml
```

![**TOD-06-05**: Performance Monitoring Report Management](../../common/pixel.png){#fig:TOD-06-05-Performance_Monitoring_Report_Management}

|                         |  Customer  |  Provider  | Other PSS  | Governance |
|-------------------------|:----------:|:----------:|:----------:|:----------:|
| **Create PM Report**    | \checkmark |            | \checkmark |            |
| **View PM Report**      | \checkmark |            | \checkmark | \checkmark |
| **View All PM Reports** | \checkmark |            | \checkmark | \checkmark |

Table: Performance Monitoring Report Management Matrix. {#tbl:performance-monitoring-report-management-matrix}

**eTOM Reference**

The task is based on the 1.4.7 process identifier from the eTOM.
