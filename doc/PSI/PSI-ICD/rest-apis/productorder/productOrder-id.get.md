<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /productOrder/{id}

**Absolute Path:** /productOrdering/v1/productOrder/{id}

**TOD Reference:** TOD-03-02-03-View_Product_Order

**Summary:** Retrieves a ProductOrder by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ProductOrder |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET productOrder/{id}. {#tbl:productOrder-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ProductOrder |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET productOrder/{id}. {#tbl:productOrder-id.get.md:responses}

{#page:break}
