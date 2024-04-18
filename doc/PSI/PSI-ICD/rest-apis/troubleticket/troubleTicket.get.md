<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /troubleTicket

**Absolute Path:** /troubleTicket/v1/troubleTicket

**Summary:** List or find TroubleTicket objects

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |

Table: Parameters of GET troubleTicket. {#tbl:troubleTicket.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | TroubleTicket |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET troubleTicket. {#tbl:troubleTicket.get.md:responses}

## TOD Reference

TOD-01-04-05-View_All_Trouble_Tickets
