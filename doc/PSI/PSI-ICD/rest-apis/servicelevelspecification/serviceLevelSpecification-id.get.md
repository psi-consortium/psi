<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /serviceLevelSpecification/{id}

**Absolute Path:** /serviceQuality/v1/serviceLevelSpecification/{id}

**Summary:** Retrieves a ServiceLevelSpecification by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the ServiceLevelSpecification |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET serviceLevelSpecification/{id}. {#tbl:serviceLevelSpecification-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ServiceLevelSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET serviceLevelSpecification/{id}. {#tbl:serviceLevelSpecification-id.get.md:responses}

## TOD Reference

TOD-06-02-04-View_Service_Level_Specification
