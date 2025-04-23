<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /serviceLevelObjective/{id}

**Absolute Path:** /serviceQualityManagement/v2/serviceLevelObjective/{id}

**TOD Reference:** TOD-06-01-04-View_Service_Level_Objective

**Summary:** Retrieves a ServiceLevelObjective by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ServiceLevelObjective |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET serviceLevelObjective/{id}. {#tbl:serviceLevelObjective-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | ServiceLevelObjective |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET serviceLevelObjective/{id}. {#tbl:serviceLevelObjective-id.get.md:responses}

{#page:break}
