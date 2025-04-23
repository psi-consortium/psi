<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrderItem_MVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| quantity | integer | N/A | No |
| action | ItemActionType | N/A | No |
| appointment | AppointmentRef_MVO | N/A | No |
| billingAccount | BillingAccountRef_MVO | N/A | No |
| itemPrice | array | OrderPrice_MVO | No |
| itemTerm | array | OrderTerm_MVO | No |
| itemTotalPrice | array | OrderPrice_MVO | No |
| note | array | Note_MVO | No |
| payment | array | PaymentRef_MVO | No |
| product | ProductRefOrValue_MVO | N/A | No |
| productOffering | ProductOfferingRef_MVO | N/A | No |
| productOfferingQualificationItem | ProductOfferingQualificationItemRef_MVO | N/A | No |
| quoteItem | QuoteItemRef_MVO | N/A | No |
| productOrderItem | array | ProductOrderItem_MVO | No |
| productOrderItemRelationship | array | OrderItemRelationship_MVO | No |
| state | ProductOrderItemStateType | N/A | No |
| qualification | array | ProductOfferingQualificationRef_MVO | No |
| requestedStartDate | string | date-time | No |
| requestedEndDate | string | date-time | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOrderItem" | Yes |

Table: Fields of ProductOrderItem_MVO. {#tbl:ProductOrderItem_MVO.md:ProductOrderItem_MVO}

{#page:break}
