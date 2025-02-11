<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PerformanceJob_Common

A Performance Monitoring Job specifies the performance monitoring objectives specific to each subject of monitoring which could be an  ordered pair (i.e., two UNIs) or an entity (i.e., port).

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| description | string | N/A | No |
| buyerJobId | string | N/A | No |
| consumingApplicationId | string | N/A | No |
| fileTransferData | FileTransferData | N/A | No |
| performanceProfile | PerformanceProfileValue | N/A | Yes |
| producingApplicationId | string | N/A | No |
| scheduleDefinition | ScheduleDefinition | N/A | No |
| servicePayloadSpecificAttributes | ServicePayloadSpecificAttributes | N/A | Yes |

Table: Fields of PerformanceJob_Common. {#tbl:PerformanceJob_Common.md:PerformanceJob_Common}

{#page:break}
