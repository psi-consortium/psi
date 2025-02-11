<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /checkProductStock/{id}

**Absolute Path:** /stock/v1/checkProductStock/{id}

**TOD Reference:** TOD-05-04-01-Check_Product_Stock

**Summary:** Retrieves a CheckProductStock by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CheckProductStock |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET checkProductStock/{id}. {#tbl:checkProductStock-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | CheckProductStock |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET checkProductStock/{id}. {#tbl:checkProductStock-id.get.md:responses}

{#page:break}
