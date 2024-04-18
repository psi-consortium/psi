<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /resourceTemplate/{id}

**Absolute Path:** /resourceCatalog/v1/resourceTemplate/{id}

**Summary:** Retrieves a ResourceTemplate by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | The identifier of the ResourceTemplate. |
| fields | string | No | Comma-separated properties to provide in response. |

Table: Parameters of GET resourceTemplate/{id}. {#tbl:resourceTemplate-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ResourceSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET resourceTemplate/{id}. {#tbl:resourceTemplate-id.get.md:responses}

## TOD Reference

TOD-04-01-04-View_Resource_Template
