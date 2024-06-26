<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /troubleTicket/{id}

**Absolute Path:** /troubleTicket/v1/troubleTicket/{id}

**Summary:** Updates partially a TroubleTicket

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the TroubleTicket |

Table: Parameters of PATCH troubleTicket/{id}. {#tbl:troubleTicket-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | TroubleTicket_Update |

Table: Request Body of PATCH troubleTicket/{id}. {#tbl:troubleTicket-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | TroubleTicket |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH troubleTicket/{id}. {#tbl:troubleTicket-id.patch.md:responses}

## TOD Reference

TOD-01-04-02-Update_Trouble_Ticket
