<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /productOrder/{id}

**Absolute Path:** /productOrdering/v1/productOrder/{id}

**Summary:** Updates partially a ProductOrder

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the ProductOrder |

Table: Parameters of PATCH productOrder/{id}. {#tbl:productOrder-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ProductOrder_Update |

Table: Request Body of PATCH productOrder/{id}. {#tbl:productOrder-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ProductOrder |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH productOrder/{id}. {#tbl:productOrder-id.patch.md:responses}

## TOD Reference

TOD-03-02-02-Update_Product_Order
