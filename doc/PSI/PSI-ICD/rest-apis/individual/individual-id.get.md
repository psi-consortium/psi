<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /individual/{id}

**Absolute Path:** /partyManagement/v1/individual/{id}

**Summary:** Retrieves a Individual by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Individual |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET individual/{id}. {#tbl:individual-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Individual |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET individual/{id}. {#tbl:individual-id.get.md:responses}

## TOD Reference

TOD-01-01-04-View_Party_Profile
