<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /checkProductOfferingQualification/{id}

**Absolute Path:** /productOfferingQualification/v2/checkProductOfferingQualification/{id}

**Summary:** Updates partially a CheckProductOfferingQualification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of PATCH checkProductOfferingQualification/{id}. {#tbl:checkProductOfferingQualification-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | CheckProductOfferingQualification_MVO |
| application/merge-patch+json | CheckProductOfferingQualification_MVO |
| application/json-patch+json | JsonPatchOperations |
| application/json-patch-query+json | JsonPatchOperations |

Table: Request Body of PATCH checkProductOfferingQualification/{id}. {#tbl:checkProductOfferingQualification-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | CheckProductOfferingQualification |
| 202 | Accepted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 422 | Unprocessable Content | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of PATCH checkProductOfferingQualification/{id}. {#tbl:checkProductOfferingQualification-id.patch.md:responses}

{#page:break}
