<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /document/{documentId}/attachment/{attachmentId}

**Absolute Path:** /document/v2/document/{documentId}/attachment/{attachmentId}

**Summary:** Deletes an Attachment

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| documentId | string | Yes | Identifier of the document. |
| attachmentId | string | Yes | Identifier of the attachment. |

Table: Parameters of DELETE document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.delete.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 202 | Accepted | N/A |
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of DELETE document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.delete.md:responses}

{#page:break}
