<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /mission

**Absolute Path:** /missionManagement/v2/mission

**TOD Reference:** TOD-03-04-01-Create_Mission

**Summary:** Creates a Mission

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | Mission_FVO |

Table: Request Body of POST mission. {#tbl:mission.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 202 | Accepted | Mission |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST mission. {#tbl:mission.post.md:responses}

{#page:break}
