<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /productSpecification/{id}

**Absolute Path:** /productCatalog/v1/productSpecification/{id}

**TOD Reference:** TOD-02-03-04-View_Product_Specification

**Summary:** Retrieves a ProductSpecification by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ProductSpecification |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET productSpecification/{id}. {#tbl:productSpecification-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ProductSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET productSpecification/{id}. {#tbl:productSpecification-id.get.md:responses}

{#page:break}
