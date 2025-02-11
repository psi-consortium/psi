<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /alarm/{id}

**Absolute Path:** /alarm/v1/alarm/{id}

**Summary:** Retrieves a Alarm by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Alarm |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET alarm/{id}. {#tbl:alarm-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Alarm |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET alarm/{id}. {#tbl:alarm-id.get.md:responses}

{#page:break}
