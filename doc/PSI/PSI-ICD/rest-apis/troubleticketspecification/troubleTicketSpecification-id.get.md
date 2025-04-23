<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /troubleTicketSpecification/{id}

**Absolute Path:** /troubleTicket/v2/troubleTicketSpecification/{id}

**Summary:** Retrieves a TroubleTicketSpecification by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of GET troubleTicketSpecification/{id}. {#tbl:troubleTicketSpecification-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | TroubleTicketSpecification |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of GET troubleTicketSpecification/{id}. {#tbl:troubleTicketSpecification-id.get.md:responses}

{#page:break}
