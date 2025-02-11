<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /resourceSpecification/{id}

**Absolute Path:** /resourceCatalog/v1/resourceSpecification/{id}

**TOD Reference:** TOD-02-01-02-Update_Resource_Specification

**Summary:** Updates partially a ResourceSpecification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ResourceSpecification |

Table: Parameters of PATCH resourceSpecification/{id}. {#tbl:resourceSpecification-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ResourceSpecification_Update |

Table: Request Body of PATCH resourceSpecification/{id}. {#tbl:resourceSpecification-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ResourceSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH resourceSpecification/{id}. {#tbl:resourceSpecification-id.patch.md:responses}

{#page:break}
