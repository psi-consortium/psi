<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /resourceTemplate/{id}

**Absolute Path:** /resourceCatalog/v1/resourceTemplate/{id}

**Summary:** Deletes a ResourceTemplate

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | The identifier of the ResourceTemplate. |

Table: Parameters of DELETE resourceTemplate/{id}. {#tbl:resourceTemplate-id.delete.md:parameters}

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

Table: Responses of DELETE resourceTemplate/{id}. {#tbl:resourceTemplate-id.delete.md:responses}

## TOD Reference

TOD-04-01-03-Remove_Resource_Template
