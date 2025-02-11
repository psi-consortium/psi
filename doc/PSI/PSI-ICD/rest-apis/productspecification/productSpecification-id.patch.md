<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /productSpecification/{id}

**Absolute Path:** /productCatalog/v1/productSpecification/{id}

**TOD Reference:** TOD-02-03-02-Update_Product_Specification

**Summary:** Updates partially a ProductSpecification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ProductSpecification |

Table: Parameters of PATCH productSpecification/{id}. {#tbl:productSpecification-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ProductSpecification_Update |

Table: Request Body of PATCH productSpecification/{id}. {#tbl:productSpecification-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ProductSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH productSpecification/{id}. {#tbl:productSpecification-id.patch.md:responses}

{#page:break}
