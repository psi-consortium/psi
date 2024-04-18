<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /topic/{topicId}/event

**Absolute Path:** /eventManagement/v1/topic/{topicId}/event

**Summary:** Creates a Event

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| topicId | string | Yes | Identifier of the parent Topic entity |

Table: Parameters of POST topic/{topicId}/event. {#tbl:topic-topicId-event.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Event_Create |

Table: Request Body of POST topic/{topicId}/event. {#tbl:topic-topicId-event.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | Event |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST topic/{topicId}/event. {#tbl:topic-topicId-event.post.md:responses}

## TOD Reference

TOD-01-02-03-Dispatch_Event
