<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /productOffering/{id}

**Absolute Path:** /productCatalog/v1/productOffering/{id}

**TOD Reference:** TOD-02-04-04-View_Product_Offering

**Summary:** Retrieves a ProductOffering by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ProductOffering |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET productOffering/{id}. {#tbl:productOffering-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ProductOffering |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET productOffering/{id}. {#tbl:productOffering-id.get.md:responses}

{#page:break}
