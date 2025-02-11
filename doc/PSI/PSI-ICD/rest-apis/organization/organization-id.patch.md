<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /organization/{id}

**Absolute Path:** /partyManagement/v1/organization/{id}

**TOD Reference:** TOD-01-01-02-Update_Party_Profile

**Summary:** Updates partially a Organization

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Organization |

Table: Parameters of PATCH organization/{id}. {#tbl:organization-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Organization_Update |

Table: Request Body of PATCH organization/{id}. {#tbl:organization-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Organization |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH organization/{id}. {#tbl:organization-id.patch.md:responses}

{#page:break}
