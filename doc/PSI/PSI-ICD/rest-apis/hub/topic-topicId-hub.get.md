<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /topic/{topicId}/hub

**Absolute Path:** /event/v2/topic/{topicId}/hub

**Summary:** List or find Hub subscription objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| topicId | string | Yes | Identifier of the parent Topic entity |
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |

Table: Parameters of GET topic/{topicId}/hub. {#tbl:topic-topicId-hub.get.md:parameters}

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

Table: Responses of GET topic/{topicId}/hub. {#tbl:topic-topicId-hub.get.md:responses}

{#page:break}
