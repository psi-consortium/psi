<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /performanceJob

**Absolute Path:** /performanceMonitoring/v2/performanceJob

**TOD Reference:** TOD-06-04-01-Create_Performance_Monitoring_Job

**Summary:** Creates a Performance Job.

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | PerformanceJob_Create |

Table: Request Body of POST performanceJob. {#tbl:performanceJob.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | 'Created (https://tools.ietf.org/html/rfc7231#section-6.3.2)' | PerformanceJob |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | N/A |
| 500 | Internal Server Error | Error500 |

Table: Responses of POST performanceJob. {#tbl:performanceJob.post.md:responses}

{#page:break}
