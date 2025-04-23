<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /performanceReport

**Absolute Path:** /performanceMonitoring/v2/performanceReport

**TOD Reference:** TOD-06-05-01-Create_Performance_Monitoring_Report

**Summary:** Creates a Performance Report.

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | PerformanceReport_Create |

Table: Request Body of POST performanceReport. {#tbl:performanceReport.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | 'Created (https://tools.ietf.org/html/rfc7231#section-6.3.2)' | PerformanceReport |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | N/A |
| 500 | Internal Server Error | Error500 |

Table: Responses of POST performanceReport. {#tbl:performanceReport.post.md:responses}

{#page:break}
