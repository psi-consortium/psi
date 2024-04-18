<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /serviceTemplate/{id}

**Absolute Path:** /serviceCatalog/v1/serviceTemplate/{id}

**Summary:** Deletes a ServiceTemplate

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | The identifier of the ServiceTemplate. |

Table: Parameters of DELETE serviceTemplate/{id}. {#tbl:serviceTemplate-id.delete.md:parameters}

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

Table: Responses of DELETE serviceTemplate/{id}. {#tbl:serviceTemplate-id.delete.md:responses}

## TOD Reference

TOD-04-02-03-Remove_Service_Template
