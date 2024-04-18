<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /product/{id}

**Absolute Path:** /productInventory/v1/product/{id}

**Summary:** Updates partially a Product

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Product |

Table: Parameters of PATCH product/{id}. {#tbl:product-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Product_Update |

Table: Request Body of PATCH product/{id}. {#tbl:product-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Product |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH product/{id}. {#tbl:product-id.patch.md:responses}

## TOD Reference

TOD-05-03-02-Update_Product
