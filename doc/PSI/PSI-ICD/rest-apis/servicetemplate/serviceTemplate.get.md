<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /serviceTemplate

**Absolute Path:** /serviceCatalog/v1/serviceTemplate

**TOD Reference:** TOD-04-02-05-View_All_Service_Templates

**Summary:** List or find ServiceTemplate objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response. |
| offset | integer | No | The requested index for start of resources to be provided in response. |
| limit | integer | No | The requested number of resources to be provided in response. |

Table: Parameters of GET serviceTemplate. {#tbl:serviceTemplate.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ServiceSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET serviceTemplate. {#tbl:serviceTemplate.get.md:responses}

{#page:break}
