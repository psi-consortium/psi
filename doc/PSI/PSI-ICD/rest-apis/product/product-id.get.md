<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /product/{id}

**Absolute Path:** /productInventory/v1/product/{id}

**Summary:** Retrieves a Product by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Product |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET product/{id}. {#tbl:product-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Product |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET product/{id}. {#tbl:product-id.get.md:responses}

## TOD Reference

TOD-05-03-04-View_Product
