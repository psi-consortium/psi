<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /organization

**Absolute Path:** /partyManagement/v2/organization

**TOD Reference:** TOD-01-01-05-View_All_Party_Profiles

**Summary:** List or find Organization objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |

Table: Parameters of GET organization. {#tbl:organization.get.md:parameters}

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

Table: Responses of GET organization. {#tbl:organization.get.md:responses}

{#page:break}
