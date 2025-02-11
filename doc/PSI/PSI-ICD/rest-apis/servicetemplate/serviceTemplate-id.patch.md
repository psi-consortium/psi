<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /serviceTemplate/{id}

**Absolute Path:** /serviceCatalog/v1/serviceTemplate/{id}

**TOD Reference:** TOD-04-02-02-Update_Service_Template

**Summary:** Updates partially a ServiceTemplate

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | The identifier of the ServiceTemplate. |

Table: Parameters of PATCH serviceTemplate/{id}. {#tbl:serviceTemplate-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ServiceSpecification_Update |

Table: Request Body of PATCH serviceTemplate/{id}. {#tbl:serviceTemplate-id.patch.md:request_body}

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

Table: Responses of PATCH serviceTemplate/{id}. {#tbl:serviceTemplate-id.patch.md:responses}

{#page:break}
