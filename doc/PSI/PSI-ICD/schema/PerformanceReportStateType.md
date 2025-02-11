<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PerformanceReportStateType

Possible values for the state of a Performance Report.

| State        | Description                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| acknowledged | A Performance Report request has been received by Seller/Server and has passed basic validations.
Performance Report Identifier is assigned in the Acknowledged state.
The report remains Acknowledged until all validations as applicable are completed.
If the attributes are validated, the Performance Report moves to the In-Progress state.
If not all attributes are validated, the report moves to the Rejected state.
|
| completed    | A Performance Report is completed and results are available.
                                                                                                                                                                                                                                                                                                                                                                               |
| failed       | A Performance Report processing has failed.
                                                                                                                                                                                                                                                                                                                                                                                                |
| inProgress   | A Performance Report has successfully passed the validations checks and the report processing has started.
                                                                                                                                                                                                                                                                                                                                 |
| rejected     | This state indicates that: <br>- Invalid information is provided through the `PerformanceReport` request <br>- The request fails to meet validation rules for `PerformanceReport` delivery (processing).
                                                                                                                                                                                                                                   |

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| PerformanceReportStateType | string | oneOf[acknowledged, completed, failed, inProgress, rejected] | No |

Table: Fields of PerformanceReportStateType. {#tbl:PerformanceReportStateType.md:PerformanceReportStateType}

{#page:break}
