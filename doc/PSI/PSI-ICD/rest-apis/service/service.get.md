<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /service

**Absolute Path:** /serviceInventory/v1/service

**TOD Reference:** TOD-05-02-05-View_All_Services

**Summary:** List or find Service objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |

Table: Parameters of GET service. {#tbl:service.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Service |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET service. {#tbl:service.get.md:responses}

{#page:break}
