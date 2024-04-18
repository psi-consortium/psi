<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /productTemplate

**Absolute Path:** /productCatalog/v1/productTemplate

**Summary:** List or find ProductTemplate objects

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| fields | string | No | Comma-separated properties to be provided in response. |
| offset | integer | No | Requested index for start of resources to be provided in response. |
| limit | integer | No | Requested number of resources to be provided in response. |

Table: Parameters of GET productTemplate. {#tbl:productTemplate.get.md:parameters}

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

Table: Responses of GET productTemplate. {#tbl:productTemplate.get.md:responses}

## TOD Reference

TOD-04-03-05-View_All_Product_Templates
