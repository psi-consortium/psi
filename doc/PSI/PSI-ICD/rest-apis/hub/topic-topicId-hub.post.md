<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /topic/{topicId}/hub

**Absolute Path:** /event/v2/topic/{topicId}/hub

**Summary:** Register listener to a Hub

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| topicId | string | Yes | Identifier of the parent Topic entity |

Table: Parameters of POST topic/{topicId}/hub. {#tbl:topic-topicId-hub.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | HubSubscription_Create |

Table: Request Body of POST topic/{topicId}/hub. {#tbl:topic-topicId-hub.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | HubSubscription |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST topic/{topicId}/hub. {#tbl:topic-topicId-hub.post.md:responses}

{#page:break}
