<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /resource/{id}

**Absolute Path:** /resourceInventory/v2/resource/{id}

**TOD Reference:** TOD-05-01-03-Remove_Resource

**Summary:** Deletes a Resource

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |

Table: Parameters of DELETE resource/{id}. {#tbl:resource-id.delete.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 202 | Accepted | N/A |
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of DELETE resource/{id}. {#tbl:resource-id.delete.md:responses}

{#page:break}
