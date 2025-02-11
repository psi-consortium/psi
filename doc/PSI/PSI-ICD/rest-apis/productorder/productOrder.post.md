<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /productOrder

**Absolute Path:** /productOrdering/v1/productOrder

**TOD Reference:** TOD-03-02-01-Create_Product_Order

**Summary:** Creates a ProductOrder

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ProductOrder_Create |

Table: Request Body of POST productOrder. {#tbl:productOrder.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | ProductOrder |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST productOrder. {#tbl:productOrder.post.md:responses}

{#page:break}
