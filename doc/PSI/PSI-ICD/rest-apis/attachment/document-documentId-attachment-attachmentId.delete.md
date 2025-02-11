<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /document/{documentId}/attachment/{attachmentId}

**Absolute Path:** /documentManagement/v1/document/{documentId}/attachment/{attachmentId}

**TOD Reference:** TOD-01-03-08-Remove_Attachment

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
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of DELETE document/{documentId}/attachment/{attachmentId}. {#tbl:document-documentId-attachment-attachmentId.delete.md:responses}

{#page:break}
