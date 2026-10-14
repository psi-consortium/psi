<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /quote/{id}

**Absolute Path:** /quoteManagement/v2/quote/{id}

**Summary:** Updates partially a Quote

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of PATCH quote/{id}. {#tbl:quote-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | Quote_MVO |
| application/merge-patch+json | Quote_MVO |
| application/json-patch+json | JsonPatchOperations |
| application/json-patch-query+json | JsonPatchOperations |

Table: Request Body of PATCH quote/{id}. {#tbl:quote-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Quote_RES |
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

Table: Responses of PATCH quote/{id}. {#tbl:quote-id.patch.md:responses}

{#page:break}
