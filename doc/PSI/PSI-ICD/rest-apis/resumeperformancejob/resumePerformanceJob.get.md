<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /resumePerformanceJob

**Absolute Path:** /performanceMonitoring/v2/resumePerformanceJob

**Summary:** Lists or finds ResumePerformanceJob objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| performanceJobId | string | No | Identifer of Performance Job that is a subject of ResumePerformanceJob. |
| state | string | No | State of ResumePerformanceJob. See `PerformanceJobProcessStateType` definition for details. |
| creationDate.gt | string | No | Date when the ResumePerformanceJob was created - greater than. |
| creationDate.lt | string | No | Date when the ResumePerformanceJob was created - lower than. |
| offset | integer | No | Requested index for start of item to be provided in response requested by client. Note that the index starts with "0". |
| limit | integer | No | Requested number of items to be provided in response requested by client. |

Table: Parameters of GET resumePerformanceJob. {#tbl:resumePerformanceJob.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | N/A |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | N/A |
| 500 | Internal Server Error | Error500 |
| 501 | Method not implemented. | Error501 |

Table: Responses of GET resumePerformanceJob. {#tbl:resumePerformanceJob.get.md:responses}

{#page:break}
