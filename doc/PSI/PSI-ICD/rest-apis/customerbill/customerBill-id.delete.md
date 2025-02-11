<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /customerBill/{id}

**Absolute Path:** /customerBillManagement/v1/customerBill/{id}

**TOD Reference:** TOD-03-03-05-Withdraw_Customer_Bill

**Summary:** Withdraw a CustomerBill

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | The identifier of the CustomerBill. |

Table: Parameters of DELETE customerBill/{id}. {#tbl:customerBill-id.delete.md:parameters}

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

Table: Responses of DELETE customerBill/{id}. {#tbl:customerBill-id.delete.md:responses}

{#page:break}
