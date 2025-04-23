<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /troubleTicket/{id}

**Absolute Path:** /troubleTicket/v2/troubleTicket/{id}

**TOD Reference:** TOD-01-04-02-Update_Trouble_Ticket

**Summary:** Updates partially a TroubleTicket

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the Resource |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of PATCH troubleTicket/{id}. {#tbl:troubleTicket-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | TroubleTicket_MVO |
| application/merge-patch+json | TroubleTicket_MVO |
| application/json-patch+json | JsonPatchOperations |
| application/json-patch-query+json | JsonPatchOperations |

Table: Request Body of PATCH troubleTicket/{id}. {#tbl:troubleTicket-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | TroubleTicket |
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

Table: Responses of PATCH troubleTicket/{id}. {#tbl:troubleTicket-id.patch.md:responses}

{#page:break}
