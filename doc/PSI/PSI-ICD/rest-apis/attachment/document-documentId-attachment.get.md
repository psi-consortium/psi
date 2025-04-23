<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /document/{documentId}/attachment

**Absolute Path:** /document/v2/document/{documentId}/attachment

**Summary:** List or find Attachment objects

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| documentId | string | Yes | The identifier of the Document. |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of GET document/{documentId}/attachment. {#tbl:document-documentId-attachment.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of GET document/{documentId}/attachment. {#tbl:document-documentId-attachment.get.md:responses}

{#page:break}
