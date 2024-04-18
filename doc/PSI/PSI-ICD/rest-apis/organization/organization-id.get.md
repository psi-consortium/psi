<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /organization/{id}

**Absolute Path:** /partyManagement/v1/organization/{id}

**Summary:** Retrieves a Organization by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Organization |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET organization/{id}. {#tbl:organization-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Organization |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET organization/{id}. {#tbl:organization-id.get.md:responses}

## TOD Reference

TOD-01-01-04-View_Party_Profile
