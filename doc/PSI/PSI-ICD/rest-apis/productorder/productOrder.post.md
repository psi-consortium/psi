<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /productOrder

**Absolute Path:** /productOrdering/v2/productOrder

**TOD Reference:** TOD-03-02-01-Create_Product_Order

**Summary:** Creates a ProductOrder

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of POST productOrder. {#tbl:productOrder.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | ProductOrder_FVO |

Table: Request Body of POST productOrder. {#tbl:productOrder.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | OK/Created | ProductOrder |
| 202 | Accepted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of POST productOrder. {#tbl:productOrder.post.md:responses}

{#page:break}
