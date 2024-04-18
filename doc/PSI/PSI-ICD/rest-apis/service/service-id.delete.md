<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /service/{id}

**Absolute Path:** /serviceInventory/v1/service/{id}

**Summary:** Deletes a Service

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Service |

Table: Parameters of DELETE service/{id}. {#tbl:service-id.delete.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of DELETE service/{id}. {#tbl:service-id.delete.md:responses}

## TOD Reference

TOD-05-02-03-Remove_Service
