<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /troubleTicketSpecification

**Absolute Path:** /troubleTicket/v2/troubleTicketSpecification

**Summary:** Creates a TroubleTicketSpecification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of POST troubleTicketSpecification. {#tbl:troubleTicketSpecification.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | TroubleTicketSpecification_FVO |

Table: Request Body of POST troubleTicketSpecification. {#tbl:troubleTicketSpecification.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | OK/Created | TroubleTicketSpecification |
| 202 | Accepted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of POST troubleTicketSpecification. {#tbl:troubleTicketSpecification.post.md:responses}

{#page:break}
