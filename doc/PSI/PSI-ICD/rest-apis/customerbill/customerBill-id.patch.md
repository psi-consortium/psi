<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PATCH /customerBill/{id}

**Absolute Path:** /customerBillManagement/v1/customerBill/{id}

**Summary:** Updates partially a CustomerBill

## Parameters

| Name | Type | Required | Description |
|------|------|----------|-------------|
| id | string | Yes | Identifier of the CustomerBill |

Table: Parameters of PATCH customerBill/{id}. {#tbl:customerBill-id.patch.md:parameters}

## Request Body

| Content Type | Reference |
|--------------|-----------|
| application/json;charset=utf-8 | CustomerBill_Update |

Table: Request Body of PATCH customerBill/{id}. {#tbl:customerBill-id.patch.md:request_body}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Updated | CustomerBill |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of PATCH customerBill/{id}. {#tbl:customerBill-id.patch.md:responses}

## TOD Reference

TOD-03-03-02-Update_Customer_Bill
