<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /resourceSpecification/{id}

**Absolute Path:** /resourceCatalog/v1/resourceSpecification/{id}

**Summary:** Retrieves a ResourceSpecification by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the ResourceSpecification |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET resourceSpecification/{id}. {#tbl:resourceSpecification-id.get.md:parameters}

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

Table: Responses of GET resourceSpecification/{id}. {#tbl:resourceSpecification-id.get.md:responses}

## TOD Reference

TOD-02-01-04-View_Resource_Specification
