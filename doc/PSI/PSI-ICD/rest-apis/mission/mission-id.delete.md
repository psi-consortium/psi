<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /mission/{id}

**Absolute Path:** /missionManagement/v2/mission/{id}

**TOD Reference:** TOD-03-04-03-Remove_Mission

**Summary:** Deletes a Mission

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Mission |

Table: Parameters of DELETE mission/{id}. {#tbl:mission-id.delete.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of DELETE mission/{id}. {#tbl:mission-id.delete.md:responses}

{#page:break}
