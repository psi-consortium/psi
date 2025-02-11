<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /performanceJobComplexQuery

**Absolute Path:** /performanceMonitoring/v1/performanceJobComplexQuery

**TOD Reference:** TOD-06-04-05-View_All_Performance_Monitoring_Job

**Summary:** Creates a PerformanceJobComplexQuery.

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | PerformanceJobComplexQuery_Create |

Table: Request Body of POST performanceJobComplexQuery. {#tbl:performanceJobComplexQuery.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | 'OK. (https://tools.ietf.org/html/rfc7231#section-6.3.1)' | PerformanceJobComplexQuery |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | Error422 |
| 500 | Internal Server Error | Error500 |

Table: Responses of POST performanceJobComplexQuery. {#tbl:performanceJobComplexQuery.post.md:responses}

{#page:break}
