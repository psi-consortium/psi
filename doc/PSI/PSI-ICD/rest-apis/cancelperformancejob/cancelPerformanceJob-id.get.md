<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /cancelPerformanceJob/{id}

**Absolute Path:** /performanceMonitoring/v2/cancelPerformanceJob/{id}

**Summary:** Retrieves a CancelPerformanceJob by ID.

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CancelPerformanceJob |

Table: Parameters of GET cancelPerformanceJob/{id}. {#tbl:cancelPerformanceJob-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | CancelPerformanceJob |
| 400 | Bad Request | Error400 |
| 401 | Unauthorized | Error401 |
| 403 | Forbidden | Error403 |
| 404 | Not Found | Error404 |
| 500 | Internal Server Error | Error500 |
| 501 | Method not implemented. | Error501 |

Table: Responses of GET cancelPerformanceJob/{id}. {#tbl:cancelPerformanceJob-id.get.md:responses}

{#page:break}
