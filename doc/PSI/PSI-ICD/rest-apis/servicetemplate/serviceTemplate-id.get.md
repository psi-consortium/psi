<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /serviceTemplate/{id}

**Absolute Path:** /serviceCatalog/v1/serviceTemplate/{id}

**Summary:** Retrieves a ServiceTemplate by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | The identifier of the ServiceTemplate. |
| fields | string | No | Comma-separated properties to provide in response. |

Table: Parameters of GET serviceTemplate/{id}. {#tbl:serviceTemplate-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ServiceSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET serviceTemplate/{id}. {#tbl:serviceTemplate-id.get.md:responses}

## TOD Reference

TOD-04-02-04-View_Service_Template
