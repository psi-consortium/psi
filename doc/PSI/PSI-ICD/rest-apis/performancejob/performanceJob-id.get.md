<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /performanceJob/{id}

**Absolute Path:** /performanceMonitoring/v1/performanceJob/{id}

**TOD Reference:** TOD-06-04-04-View_Performance_Monitoring_Job

**Summary:** Retrieves a Performance Job by ID.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Performance Job |

Table: Parameters of GET performanceJob/{id}. {#tbl:performanceJob-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | 'OK. (https://tools.ietf.org/html/rfc7231#section-6.3.1)' | PerformanceJob |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 404 | Not Found | Error404 |
| 500 | Internal Server Error | Error500 |

Table: Responses of GET performanceJob/{id}. {#tbl:performanceJob-id.get.md:responses}

{#page:break}
