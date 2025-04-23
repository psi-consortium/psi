<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /document/{documentId}/attachment

**Absolute Path:** /document/v2/document/{documentId}/attachment

**Summary:** Creates an Attachment

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| documentId | string | Yes | The identifier of the Document. |

Table: Parameters of POST document/{documentId}/attachment. {#tbl:document-documentId-attachment.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| multipart/form-data | Attachment_FVO |
| application/json;charset=utf-8 | Attachment_FVO |

Table: Request Body of POST document/{documentId}/attachment. {#tbl:document-documentId-attachment.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | OK/Created | Attachment |
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

Table: Responses of POST document/{documentId}/attachment. {#tbl:document-documentId-attachment.post.md:responses}

{#page:break}
