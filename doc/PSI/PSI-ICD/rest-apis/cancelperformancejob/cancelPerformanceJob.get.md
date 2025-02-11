<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /cancelPerformanceJob

**Absolute Path:** /performanceMonitoring/v1/cancelPerformanceJob

**Summary:** Lists or finds CancelPerformanceJob objects.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| performanceJobId | string | No | Identifer of Performance Job that is a subject of CancelPerformanceJob. |
| state | string | No | State of CancelPerformanceJob. See `PerformanceJobProcessStateType` definition for details. |
| creationDate.gt | string | No | Date when the CancelPerformanceJob was created - greater than. |
| creationDate.lt | string | No | Date when the CancelPerformanceJob was created - lower than. |
| offset | integer | No | Requested index for start of item to be provided in response requested by client. Note that the index starts with "0". |
| limit | integer | No | Requested number of items to be provided in response requested by client. |

Table: Parameters of GET cancelPerformanceJob. {#tbl:cancelPerformanceJob.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | CancelPerformanceJob_Find |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | Error422 |
| 500 | Internal Server Error | Error500 |
| 501 | Method not implemented. | Error501 |

Table: Responses of GET cancelPerformanceJob. {#tbl:cancelPerformanceJob.get.md:responses}

{#page:break}
