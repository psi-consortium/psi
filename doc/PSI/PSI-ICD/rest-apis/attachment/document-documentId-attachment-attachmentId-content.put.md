<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PUT /document/{documentId}/attachment/{attachmentId}/content

**Absolute Path:** /document/v2/document/{documentId}/attachment/{attachmentId}/content

**Summary:** Updates the content of an Attachment

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| documentId | string | Yes | Identifier of the Document |
| attachmentId | string | Yes | Identifier of the Attachment |

Table: Parameters of PUT document/{documentId}/attachment/{attachmentId}/content. {#tbl:document-documentId-attachment-attachmentId-content.put.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| */* | binary |

Table: Request Body of PUT document/{documentId}/attachment/{attachmentId}/content. {#tbl:document-documentId-attachment-attachmentId-content.put.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | Attachment |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of PUT document/{documentId}/attachment/{attachmentId}/content. {#tbl:document-documentId-attachment-attachmentId-content.put.md:responses}

{#page:break}
