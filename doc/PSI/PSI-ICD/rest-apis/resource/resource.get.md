<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /resource

**Absolute Path:** /resourceInventory/v2/resource

**TOD Reference:** TOD-05-01-05-View_All_Resources

**Summary:** List or find Resource objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |
| before | string | No | An opaque string value representing the page results before the cursor value |
| after | string | No | An opaque string value representing the page results after the cursor value |
| sort | string | No | The default direction is Ascending order, the use of the modifier in front of the sort field name, “-“, changes the sort order direction. |
| filter | string | No | Filter a collection using JSONPath |

Table: Parameters of GET resource. {#tbl:resource.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of GET resource. {#tbl:resource.get.md:responses}

{#page:break}
