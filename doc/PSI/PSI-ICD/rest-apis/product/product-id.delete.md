<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /product/{id}

**Absolute Path:** /productInventory/v1/product/{id}

**Summary:** Deletes a Product

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Product |

Table: Parameters of DELETE product/{id}. {#tbl:product-id.delete.md:parameters}

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

Table: Responses of DELETE product/{id}. {#tbl:product-id.delete.md:responses}

## TOD Reference

TOD-05-03-03-Remove_Product
