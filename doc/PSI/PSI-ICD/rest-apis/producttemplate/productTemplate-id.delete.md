<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /productTemplate/{id}

**Absolute Path:** /productCatalog/v1/productTemplate/{id}

**TOD Reference:** TOD-04-03-03-Remove_Product_Template

**Summary:** Deletes a ProductTemplate

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | The identifier of the ProductTemplate |

Table: Parameters of DELETE productTemplate/{id}. {#tbl:productTemplate-id.delete.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of DELETE productTemplate/{id}. {#tbl:productTemplate-id.delete.md:responses}

{#page:break}
