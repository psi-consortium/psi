<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /customerBill/{id}

**Absolute Path:** /customerBillManagement/v1/customerBill/{id}

**TOD Reference:** TOD-03-03-03-View_Customer_Bill

**Summary:** Retrieves a CustomerBill by ID

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CustomerBill |
| fields | string | No | Comma-separated properties to provide in response |

Table: Parameters of GET customerBill/{id}. {#tbl:customerBill-id.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | CustomerBill |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET customerBill/{id}. {#tbl:customerBill-id.get.md:responses}

{#page:break}
