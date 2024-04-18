<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /individual/{id}

**Absolute Path:** /partyManagement/v1/individual/{id}

**Summary:** Updates partially a Individual

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Individual |

Table: Parameters of PATCH individual/{id}. {#tbl:individual-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Individual_Update |

Table: Request Body of PATCH individual/{id}. {#tbl:individual-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Individual |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH individual/{id}. {#tbl:individual-id.patch.md:responses}

## TOD Reference

TOD-01-01-02-Update_Party_Profile
