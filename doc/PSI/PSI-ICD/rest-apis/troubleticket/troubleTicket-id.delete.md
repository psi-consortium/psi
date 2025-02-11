<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /troubleTicket/{id}

**Absolute Path:** /troubleTicket/v1/troubleTicket/{id}

**TOD Reference:** TOD-01-04-03-Remove_Trouble_Ticket

**Summary:** Deletes a TroubleTicket

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the TroubleTicket |

Table: Parameters of DELETE troubleTicket/{id}. {#tbl:troubleTicket-id.delete.md:parameters}

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

Table: Responses of DELETE troubleTicket/{id}. {#tbl:troubleTicket-id.delete.md:responses}

{#page:break}
