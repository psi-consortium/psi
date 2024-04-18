<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /serviceLevelSpecification/{id}

**Absolute Path:** /serviceQuality/v1/serviceLevelSpecification/{id}

**Summary:** Updates partially a ServiceLevelSpecification

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the ServiceLevelSpecification |

Table: Parameters of PATCH serviceLevelSpecification/{id}. {#tbl:serviceLevelSpecification-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ServiceLevelSpecification_Update |

Table: Request Body of PATCH serviceLevelSpecification/{id}. {#tbl:serviceLevelSpecification-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ServiceLevelSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH serviceLevelSpecification/{id}. {#tbl:serviceLevelSpecification-id.patch.md:responses}

## TOD Reference

TOD-06-02-02-Update_Service_Level_Specification
