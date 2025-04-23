<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /keyIndicator/{id}

**Absolute Path:** /serviceQualityManagement/v2/keyIndicator/{id}

**TOD Reference:** TOD-06-03-02-Update_Key_Indicator

**Summary:** Updates partially a KeyIndicator

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |

Table: Parameters of PATCH keyIndicator/{id}. {#tbl:keyIndicator-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | KeyIndicator_Update |

Table: Request Body of PATCH keyIndicator/{id}. {#tbl:keyIndicator-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | KeyIndicator |
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

Table: Responses of PATCH keyIndicator/{id}. {#tbl:keyIndicator-id.patch.md:responses}

{#page:break}
