<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /document/{id}

**Absolute Path:** /documentManagement/v1/document/{id}

**Summary:** Retrieves a Document by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Document |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET document/{id}. {#tbl:document-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Document |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET document/{id}. {#tbl:document-id.get.md:responses}

## TOD Reference

TOD-01-03-04-View_Document
