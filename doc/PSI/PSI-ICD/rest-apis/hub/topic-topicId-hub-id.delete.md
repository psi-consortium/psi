<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /topic/{topicId}/hub/{id}

**Absolute Path:** /event/v2/topic/{topicId}/hub/{id}

**Summary:** Unregister listener from a Hub

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| topicId | string | Yes | Identifier of the parent Topic entity |
| id | string | Yes | Identifier of the Hub |

Table: Parameters of DELETE topic/{topicId}/hub/{id}. {#tbl:topic-topicId-hub-id.delete.md:parameters}

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

Table: Responses of DELETE topic/{topicId}/hub/{id}. {#tbl:topic-topicId-hub-id.delete.md:responses}

{#page:break}
