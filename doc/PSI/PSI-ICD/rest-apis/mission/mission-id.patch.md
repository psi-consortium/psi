<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /mission/{id}

**Absolute Path:** /missionManagement/v1/mission/{id}

**TOD Reference:** TOD-03-04-02-Update_Mission

**Summary:** Updates partially a Mission

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Mission |

Table: Parameters of PATCH mission/{id}. {#tbl:mission-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | Mission_Update |

Table: Request Body of PATCH mission/{id}. {#tbl:mission-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 202 | Update Accepted | Mission |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH mission/{id}. {#tbl:mission-id.patch.md:responses}

{#page:break}
