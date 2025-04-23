<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /resourceSpecification/{id}

**Absolute Path:** /resourceCatalog/v2/resourceSpecification/{id}

**TOD Reference:** TOD-02-01-02-Update_Resource_Specification

**Summary:** Updates partially a ResourceSpecification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of PATCH resourceSpecification/{id}. {#tbl:resourceSpecification-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | ResourceSpecification_MVO |
| application/merge-patch+json | ResourceSpecification_MVO |
| application/json-patch+json | JsonPatchOperations |
| application/json-patch-query+json | JsonPatchOperations |

Table: Request Body of PATCH resourceSpecification/{id}. {#tbl:resourceSpecification-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ResourceSpecification |
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

Table: Responses of PATCH resourceSpecification/{id}. {#tbl:resourceSpecification-id.patch.md:responses}

{#page:break}
