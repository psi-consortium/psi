<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /productTemplate/{id}

**Absolute Path:** /productCatalog/v1/productTemplate/{id}

**TOD Reference:** TOD-04-03-02-Update_Product_Template

**Summary:** Updates partially a ProductTemplate

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | The identifier of the ProductTemplate |

Table: Parameters of PATCH productTemplate/{id}. {#tbl:productTemplate-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ProductSpecification_Update |

Table: Request Body of PATCH productTemplate/{id}. {#tbl:productTemplate-id.patch.md:request_body}

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

Table: Responses of PATCH productTemplate/{id}. {#tbl:productTemplate-id.patch.md:responses}

{#page:break}
