<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /document/{documentId}/attachment

**Absolute Path:** /documentManagement/v1/document/{documentId}/attachment

**Summary:** Creates an Attachment

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| documentId | string | Yes | The identifier of the Document. |

Table: Parameters of POST document/{documentId}/attachment. {#tbl:document-documentId-attachment.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| multipart/form-data | Attachment_Create |
| application/json;charset=utf-8 | Attachment_Create |

Table: Request Body of POST document/{documentId}/attachment. {#tbl:document-documentId-attachment.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | Attachment |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of POST document/{documentId}/attachment. {#tbl:document-documentId-attachment.post.md:responses}

## TOD Reference

TOD-01-03-06-Create_Attachment
