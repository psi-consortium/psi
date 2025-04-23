<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /customerInquiry/{id}

**Absolute Path:** /customerInquiry/v2/customerInquiry/{id}

**TOD Reference:** TOD-03-01-05-Cancel_Customer_Inquiry

**Summary:** Cancels a CustomerInquiry

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CustomerInquiry |

Table: Parameters of DELETE customerInquiry/{id}. {#tbl:customerInquiry-id.delete.md:parameters}

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

Table: Responses of DELETE customerInquiry/{id}. {#tbl:customerInquiry-id.delete.md:responses}

{#page:break}
