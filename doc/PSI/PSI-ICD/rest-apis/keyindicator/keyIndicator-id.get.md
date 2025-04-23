<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /keyIndicator/{id}

**Absolute Path:** /serviceQualityManagement/v2/keyIndicator/{id}

**TOD Reference:** TOD-06-03-04-View_Key_Indicator

**Summary:** Retrieves a KeyIndicator by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of GET keyIndicator/{id}. {#tbl:keyIndicator-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | KeyIndicator |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of GET keyIndicator/{id}. {#tbl:keyIndicator-id.get.md:responses}

{#page:break}
