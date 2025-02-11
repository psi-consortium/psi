<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /service/{id}

**Absolute Path:** /serviceInventory/v1/service/{id}

**TOD Reference:** TOD-05-02-04-View_Service

**Summary:** Retrieves a Service by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Service |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET service/{id}. {#tbl:service-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Service |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET service/{id}. {#tbl:service-id.get.md:responses}

{#page:break}
