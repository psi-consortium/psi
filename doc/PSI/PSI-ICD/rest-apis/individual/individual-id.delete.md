<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /individual/{id}

**Absolute Path:** /partyManagement/v1/individual/{id}

**Summary:** Deletes a Individual

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Individual |

Table: Parameters of DELETE individual/{id}. {#tbl:individual-id.delete.md:parameters}

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

Table: Responses of DELETE individual/{id}. {#tbl:individual-id.delete.md:responses}

## TOD Reference

TOD-01-01-03-Remove_Party_Profile
