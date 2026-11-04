<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# POST /quote

**Absolute Path:** /quoteManagement/v2/quote

**Summary:** Creates a Quote

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| fields | string | No | Comma-separated properties to be provided in response |

Table: Parameters of POST quote. {#tbl:quote.post.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | Quote_FVO |

Table: Request Body of POST quote. {#tbl:quote.post.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 201 | Created | Quote_RES |
| 202 | Accepted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 422 | Unprocessable Content | Error |
| 500 | Internal Server Error | Error |
| 501 | Not Implemented | Error |
| 503 | Service Unavailable | Error |

Table: Responses of POST quote. {#tbl:quote.post.md:responses}

{#page:break}
