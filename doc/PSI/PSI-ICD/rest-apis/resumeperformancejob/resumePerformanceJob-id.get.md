<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /resumePerformanceJob/{id}

**Absolute Path:** /performanceMonitoring/v2/resumePerformanceJob/{id}

**Summary:** Retrieves a ResumePerformanceJob by ID.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ResumePerformanceJob |

Table: Parameters of GET resumePerformanceJob/{id}. {#tbl:resumePerformanceJob-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ResumePerformanceJob |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 404 | Not Found | Error404 |
| 500 | Internal Server Error | Error500 |
| 501 | Method not implemented. | Error501 |

Table: Responses of GET resumePerformanceJob/{id}. {#tbl:resumePerformanceJob-id.get.md:responses}

{#page:break}
