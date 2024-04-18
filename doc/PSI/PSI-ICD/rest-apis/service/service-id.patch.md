<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /service/{id}

**Absolute Path:** /serviceInventory/v1/service/{id}

**Summary:** Updates partially a Service

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Service |

Table: Parameters of PATCH service/{id}. {#tbl:service-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Service_Update |

Table: Request Body of PATCH service/{id}. {#tbl:service-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Service |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH service/{id}. {#tbl:service-id.patch.md:responses}

## TOD Reference

TOD-05-02-02-Update_Service
