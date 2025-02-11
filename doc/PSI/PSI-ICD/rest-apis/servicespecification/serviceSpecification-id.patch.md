<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /serviceSpecification/{id}

**Absolute Path:** /serviceCatalog/v1/serviceSpecification/{id}

**TOD Reference:** TOD-02-02-02-Update_Service_Specification

**Summary:** Updates partially a ServiceSpecification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ServiceSpecification |

Table: Parameters of PATCH serviceSpecification/{id}. {#tbl:serviceSpecification-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ServiceSpecification_Update |

Table: Request Body of PATCH serviceSpecification/{id}. {#tbl:serviceSpecification-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ServiceSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH serviceSpecification/{id}. {#tbl:serviceSpecification-id.patch.md:responses}

{#page:break}
