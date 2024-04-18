<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /resource/{id}

**Absolute Path:** /resourceInventory/v1/resource/{id}

**Summary:** Retrieves a Resource by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET resource/{id}. {#tbl:resource-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Resource |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET resource/{id}. {#tbl:resource-id.get.md:responses}

## TOD Reference

TOD-05-01-04-View_Resource
