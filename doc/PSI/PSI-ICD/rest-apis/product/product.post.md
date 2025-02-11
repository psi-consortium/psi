<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /product

**Absolute Path:** /productInventory/v1/product

**TOD Reference:** TOD-05-03-01-Create_Product

**Summary:** Creates a Product

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Product_Create |

Table: Request Body of POST product. {#tbl:product.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | Product |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST product. {#tbl:product.post.md:responses}

{#page:break}
