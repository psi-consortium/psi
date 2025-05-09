<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /document/{documentId}/attachment/{attachmentId}

**Absolute Path:** /document/v2/document/{documentId}/attachment/{attachmentId}

**Summary:** Updates an Attachment

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| documentId | string | Yes | The identifier of the document. |
| attachmentId | string | Yes | The identifier of the attachment. |

Table: Parameters of PATCH document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| multipart/form-data | Attachment_MVO |
| application/json;charset=utf-8 | Attachment_MVO |

Table: Request Body of PATCH document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | Attachment |
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

Table: Responses of PATCH document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.patch.md:responses}

{#page:break}
