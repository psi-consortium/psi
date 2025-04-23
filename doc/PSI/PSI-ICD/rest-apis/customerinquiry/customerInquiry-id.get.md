<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /customerInquiry/{id}

**Absolute Path:** /customerInquiry/v2/customerInquiry/{id}

**TOD Reference:** TOD-03-01-02-View_Customer_Inquiry

**Summary:** Retrieves a CustomerInquiry

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CustomerInquiry |

Table: Parameters of GET customerInquiry/{id}. {#tbl:customerInquiry-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | OK | CustomerInquiry |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET customerInquiry/{id}. {#tbl:customerInquiry-id.get.md:responses}

{#page:break}
