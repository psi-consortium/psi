<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /product/{id}

**Absolute Path:** /productInventory/v2/product/{id}

**TOD Reference:** TOD-05-03-02-Update_Product

**Summary:** Updates partially a Product

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of PATCH product/{id}. {#tbl:product-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | Product_MVO |
| application/merge-patch+json | Product_MVO |
| application/json-patch+json | JsonPatchOperations |
| application/json-patch-query+json | JsonPatchOperations |

Table: Request Body of PATCH product/{id}. {#tbl:product-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Product |
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

Table: Responses of PATCH product/{id}. {#tbl:product-id.patch.md:responses}

{#page:break}
