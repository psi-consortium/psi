<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /resource/{id}

**Absolute Path:** /resourceInventory/v1/resource/{id}

**Summary:** Updates partially a Resource

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Resource |

Table: Parameters of PATCH resource/{id}. {#tbl:resource-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Resource_Update |

Table: Request Body of PATCH resource/{id}. {#tbl:resource-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Resource |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH resource/{id}. {#tbl:resource-id.patch.md:responses}

## TOD Reference

TOD-05-01-02-Update_Resource
