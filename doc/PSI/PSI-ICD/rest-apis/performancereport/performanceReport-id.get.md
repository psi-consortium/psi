<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /performanceReport/{id}

**Absolute Path:** /performanceMonitoring/v1/performanceReport/{id}

**TOD Reference:** TOD-06-05-02-View_Performance_Monitoring_Report

**Summary:** Retrieves a Performance Report by ID.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Performance Report to be retrieved. |

Table: Parameters of GET performanceReport/{id}. {#tbl:performanceReport-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | 'OK. (https://tools.ietf.org/html/rfc7231#section-6.3.1)' | PerformanceReport |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 404 | Not Found | Error404 |
| 500 | Internal Server Error | Error500 |

Table: Responses of GET performanceReport/{id}. {#tbl:performanceReport-id.get.md:responses}

{#page:break}
