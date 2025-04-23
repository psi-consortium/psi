<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /troubleTicket/{id}

**Absolute Path:** /troubleTicket/v2/troubleTicket/{id}

**TOD Reference:** TOD-01-04-04-View_Trouble_Ticket

**Summary:** Retrieves a TroubleTicket by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of GET troubleTicket/{id}. {#tbl:troubleTicket-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | TroubleTicket |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of GET troubleTicket/{id}. {#tbl:troubleTicket-id.get.md:responses}

{#page:break}
