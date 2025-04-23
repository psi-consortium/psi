<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /customerInquiry/{id}

**Absolute Path:** /customerInquiry/v2/customerInquiry/{id}

**TOD Reference:** TOD-03-01-04-Update_Customer_Inquiry

**Summary:** Updates partially a CustomerInquiry

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CustomerInquiry |

Table: Parameters of PATCH customerInquiry/{id}. {#tbl:customerInquiry-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json | CustomerInquiry_MVO |

Table: Request Body of PATCH customerInquiry/{id}. {#tbl:customerInquiry-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 202 | Update Accepted | CustomerInquiry |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH customerInquiry/{id}. {#tbl:customerInquiry-id.patch.md:responses}

{#page:break}
