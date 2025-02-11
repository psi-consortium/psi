<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /performanceReport

**Absolute Path:** /performanceMonitoring/v1/performanceReport

**TOD Reference:** TOD-06-05-03-View_All_Performance_Monitoring_Report

**Summary:** List or find Performance Report objects.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| performanceJobId | string | No | Identifier of Performance Job that generated Performance Report. |
| state | string | No | State of the Performance Report. See `PerformanceReportStateType` definition for details. |
| creationDate.gt | string | No | Date when the report was created - greater than. |
| creationDate.lt | string | No | Date when the report was created - lower than. |
| reportingTimeframe.startDate.gt | string | No | Start date of reporting timeframe - greater than. |
| reportingTimeframe.startDate.lt | string | No | Start date of reporting timeframe - lower than. |
| reportingTimeframe.endDate.gt | string | No | End date of reporting timeframe - greater than. |
| reportingTimeframe.endDate.lt | string | No | End date of reporting timeframe - lower than. |
| granularity | null | No | Sampling rate of the collection of measurements |
| outputFormat | null | No | Format of report output |
| resultFormat | null | No | Type of providing report results |
| consumingApplicationId | string | No | Identifier of consuming application |
| producingApplicationId | string | No | Identifier of producing application |
| offset | integer | No | Requested index for start of item to be provided in response requested by the client. Note that the index starts with "0". |
| limit | integer | No | Requested number of resources to be provided in response. |

Table: Parameters of GET performanceReport. {#tbl:performanceReport.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | 'OK. (https://tools.ietf.org/html/rfc7231#section-6.3.1)' | PerformanceReport_Find |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | Error422 |
| 500 | Internal Server Error | Error500 |

Table: Responses of GET performanceReport. {#tbl:performanceReport.get.md:responses}

{#page:break}
