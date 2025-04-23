<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /topic/{topicId}/event

**Absolute Path:** /event/v2/topic/{topicId}/event

**Summary:** List or find Event objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| topicId | string | Yes | Identifier of the parent Topic entity |
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |

Table: Parameters of GET topic/{topicId}/event. {#tbl:topic-topicId-event.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET topic/{topicId}/event. {#tbl:topic-topicId-event.get.md:responses}

{#page:break}
