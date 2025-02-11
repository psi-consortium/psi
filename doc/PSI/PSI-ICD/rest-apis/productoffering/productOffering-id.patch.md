<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /productOffering/{id}

**Absolute Path:** /productCatalog/v1/productOffering/{id}

**TOD Reference:** TOD-02-04-02-Update_Product_Offering

**Summary:** Updates partially a ProductOffering

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ProductOffering |

Table: Parameters of PATCH productOffering/{id}. {#tbl:productOffering-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ProductOffering_Update |

Table: Request Body of PATCH productOffering/{id}. {#tbl:productOffering-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ProductOffering |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH productOffering/{id}. {#tbl:productOffering-id.patch.md:responses}

{#page:break}
