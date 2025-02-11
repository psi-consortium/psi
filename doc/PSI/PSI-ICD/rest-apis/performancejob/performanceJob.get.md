<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /performanceJob

**Absolute Path:** /performanceMonitoring/v1/performanceJob

**TOD Reference:** TOD-06-04-05-View_All_Performance_Monitoring_Job

**Summary:** List or find Performance Job objects.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| buyerJobId | string | No | Identifier assigned and understandable by Buyer/Client to  facilitate search requests. |
| state | string | No | State of the Performance Job. See `PerformanceJobStateType`  definition for details. |
| creationDate.gt | string | No | Date when the job was created - greater than. |
| creationDate.lt | string | No | Date when the job was created - lower than. |
| jobType | null | No | Type of the Performance Job |
| granularity | null | No | Sampling rate of the collection of measurements. |
| reportingPeriod | null | No | Definition of time period during which report will be active  and collect measurements. |
| consumingApplicationId | string | No | Identifier of consuming application. |
| producingApplicationId | string | No | Identifier of producing application. |
| jobPriority | string | No | The priority of the Performance Job. |
| offset | integer | No | Requested index for start of item to be provided in response requested by the client. Note that the index starts with "0". |
| limit | integer | No | Requested number of resources to be provided in response. |

Table: Parameters of GET performanceJob. {#tbl:performanceJob.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | 'OK. (https://tools.ietf.org/html/rfc7231#section-6.3.1)' | PerformanceJob_Find |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | Error422 |
| 500 | Internal Server Error | Error500 |

Table: Responses of GET performanceJob. {#tbl:performanceJob.get.md:responses}

{#page:break}
