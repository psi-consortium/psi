<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /serviceLevelObjective/{id}

**Absolute Path:** /serviceQuality/v1/serviceLevelObjective/{id}

**TOD Reference:** TOD-06-01-02-Update_Service_Level_Objective

**Summary:** Updates partially a ServiceLevelObjective

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ServiceLevelObjective |

Table: Parameters of PATCH serviceLevelObjective/{id}. {#tbl:serviceLevelObjective-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | ServiceLevelObjective_Update |

Table: Request Body of PATCH serviceLevelObjective/{id}. {#tbl:serviceLevelObjective-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | ServiceLevelObjective |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH serviceLevelObjective/{id}. {#tbl:serviceLevelObjective-id.patch.md:responses}

{#page:break}
