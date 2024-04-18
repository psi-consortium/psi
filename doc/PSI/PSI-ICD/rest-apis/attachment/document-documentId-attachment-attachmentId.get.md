<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /document/{documentId}/attachment/{attachmentId}

**Absolute Path:** /documentManagement/v1/document/{documentId}/attachment/{attachmentId}

**Summary:** Retrieves an Attachment by ID

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| documentId | string | Yes | The identifier of the Document. |
| attachmentId | string | Yes | The identifier of the Attachment. |
| fields | string | No | Comma-separated properties to provide in response. |

Table: Parameters of GET document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Attachment |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.get.md:responses}

## TOD Reference

TOD-01-03-09-View_Attachment
