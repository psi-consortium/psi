<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /troubleTicket

**Absolute Path:** /troubleTicket/v1/troubleTicket

**TOD Reference:** TOD-01-04-01-Create_Trouble_Ticket

**Summary:** Creates a TroubleTicket

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | TroubleTicket_Create |

Table: Request Body of POST troubleTicket. {#tbl:troubleTicket.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | TroubleTicket |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST troubleTicket. {#tbl:troubleTicket.post.md:responses}

{#page:break}
