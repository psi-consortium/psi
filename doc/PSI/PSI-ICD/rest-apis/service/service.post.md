<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /service

**Absolute Path:** /serviceInventory/v1/service

**TOD Reference:** TOD-05-02-01-Create_Service

**Summary:** Creates a Service

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Service_Create |

Table: Request Body of POST service. {#tbl:service.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | Service |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST service. {#tbl:service.post.md:responses}

{#page:break}
