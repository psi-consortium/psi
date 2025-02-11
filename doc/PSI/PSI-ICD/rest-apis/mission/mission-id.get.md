<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /mission/{id}

**Absolute Path:** /missionManagement/v1/mission/{id}

**TOD Reference:** TOD-03-04-04-View_Mission

**Summary:** Retrieves a Mission

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Mission |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET mission/{id}. {#tbl:mission-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | OK | Mission |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET mission/{id}. {#tbl:mission-id.get.md:responses}

{#page:break}
