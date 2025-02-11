<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PerformanceJobStateType

The state of the Performance Monitoring Job.

| state                  | MEF 133 name         | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| ---------------------- | -------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `acknowledged`         | Acknowledged         | A Create Performance Monitoring Job request has been received by the Seller/Server and has passed basic validation.
Performance Monitoring Job Identifier is assigned in the Acknowledged state.
The request remains Acknowledged until all validations as applicable are completed.
If the attributes are validated the request determines if the start time is immediate or scheduled.
If immediate, the Performance Monitoring Job moves to the In-progress state.
If scheduled, the Performance Monitoring Job moves to the Scheduled state.
If not all attributes are validated, the request moves to the Rejected state.
                                     |
| `cancelled`            | Cancelled            | A Performance Monitoring Job that is In-Progress, Suspended, or Sceduled is cancelled.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| `completed`            | Completed            | A non-recurring Performance Monitoring Job finished execution.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| `inProgress`           | In-Progress          | A Performance Monitoring Job is running.
Upon completion of the Job, a determination if the Performance Monitoring Job is a one-time Job or is recurring is performed.
If the Performance Monitoring Job is a one-time Job, the state of the Performance Monitoring Job moves to the Completed state.
If the Performance Monitoring Job is recurring, the Performance Monitoring Job circles back to determine if it has an immediate start time or a scheduled start time.
If a Suspend Performance Monitoring Job request is accepted, the Job moves to the Suspended state.
If a Cancel Performance Monitoring Job request is accepted, the Job moves to the Cancelled state.
|
| `pending`              | Pending              | A Modify Performance Monitoring Job request has been accepted by the Seller/Server.
The Performance Monitoring Job remains Pending while updates to the Job are completed.
Once updates are complete, the Job returns to the Scheduled or In-Progress status depending on the schedule definition.
                                                                                                                                                                                                                                                                                                                                                                 |
| `rejected`             | Rejected             | A create Performance Monitoring Job request fails validation and is rejected with error indications by the Seller/Server.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| `resourceUnavailable`  | Resource Unavailable | A Performance Monitoring Job cannot be allocated necessary resources when moving to execution (In-Progress state).
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `scheduled`            | Scheduled            | A Performance Monitoring Job is created that does not have an immediate start time.
The Performance Monitoring Job stays Scheduled until the start time is reached.
The Performance Monitoring Job then moves to In-Progress.
If the Cancel Performance Monitoring Job request is accepted, the Job moves to the Cancelled state.
If the Modify Performance Monitoring Job request is accepted, the Job moves to the Pending state.
                                                                                                                                                                                                                                             |
| `suspended`            | Suspended            | A Suspend Performance Monitoring Job request is accepted by the Seller/Server.
The Job remains Suspended until a Resume Performance Monitoring Job request is accepted by the Seller/Server at which time the Job returns to the In-Progress state.
If the Cancel Performance Monitoring Job request is accepted, the Job moves to the Cancelled state.
If the Modify Performance Monitoring Job request is accepted, the Job moves to the Pending state.
                                                                                                                                                                                                                       |

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| PerformanceJobStateType | string | oneOf[acknowledged, cancelled, completed, inProgress, pending, rejected, resourceUnavailable, scheduled, suspended] | No |

Table: Fields of PerformanceJobStateType. {#tbl:PerformanceJobStateType.md:PerformanceJobStateType}

{#page:break}
