<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /resourceTemplate/{id}

**Absolute Path:** /resourceCatalog/v1/resourceTemplate/{id}

**Summary:** Updates partially a ResourceTemplate

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | The identifier of the ResourceTemplate. |

Table: Parameters of PATCH resourceTemplate/{id}. {#tbl:resourceTemplate-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ResourceSpecification_Update |

Table: Request Body of PATCH resourceTemplate/{id}. {#tbl:resourceTemplate-id.patch.md:request_body}

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

Table: Responses of PATCH resourceTemplate/{id}. {#tbl:resourceTemplate-id.patch.md:responses}

## TOD Reference

TOD-04-01-02-Update_Resource_Template
