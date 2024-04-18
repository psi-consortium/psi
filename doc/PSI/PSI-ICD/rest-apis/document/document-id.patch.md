<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /document/{id}

**Absolute Path:** /documentManagement/v1/document/{id}

**Summary:** Updates partially a Document

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the Document |

Table: Parameters of PATCH document/{id}. {#tbl:document-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | Document_Update |

Table: Request Body of PATCH document/{id}. {#tbl:document-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Document |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH document/{id}. {#tbl:document-id.patch.md:responses}

## TOD Reference

TOD-01-03-02-Update_Document
