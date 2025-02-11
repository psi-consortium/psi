<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /cancelPerformanceJob

**Absolute Path:** /performanceMonitoring/v1/cancelPerformanceJob

**TOD Reference:** TOD-06-04-03-Cancel_Performance_Monitoring_Job


## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | CancelPerformanceJob_Create |

Table: Request Body of POST cancelPerformanceJob. {#tbl:cancelPerformanceJob.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | CancelPerformanceJob |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 422 | Unprocessable entity due to the business validation problems | Error422 |
| 500 | Internal Server Error | Error500 |
| 501 | Method not implemented. | Error501 |

Table: Responses of POST cancelPerformanceJob. {#tbl:cancelPerformanceJob.post.md:responses}

{#page:break}
