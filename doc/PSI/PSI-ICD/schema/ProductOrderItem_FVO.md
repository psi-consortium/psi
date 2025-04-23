<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrderItem_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| quantity | integer | N/A | No |
| action | ItemActionType | N/A | Yes |
| appointment | AppointmentRef_FVO | N/A | No |
| billingAccount | BillingAccountRef_FVO | N/A | No |
| itemPrice | array | OrderPrice_FVO | No |
| itemTerm | array | OrderTerm_FVO | No |
| itemTotalPrice | array | OrderPrice_FVO | No |
| note | array | Note_FVO | No |
| payment | array | PaymentRef_FVO | No |
| product | ProductRefOrValue_FVO | N/A | No |
| productOffering | ProductOfferingRef_FVO | N/A | No |
| productOfferingQualificationItem | ProductOfferingQualificationItemRef_FVO | N/A | No |
| quoteItem | QuoteItemRef_FVO | N/A | No |
| productOrderItem | array | ProductOrderItem_FVO | No |
| productOrderItemRelationship | array | OrderItemRelationship_FVO | No |
| state | ProductOrderItemStateType | N/A | No |
| qualification | array | ProductOfferingQualificationRef_FVO | No |
| requestedStartDate | string | date-time | No |
| requestedEndDate | string | date-time | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOrderItem" | Yes |

Table: Fields of ProductOrderItem_FVO. {#tbl:ProductOrderItem_FVO.md:ProductOrderItem_FVO}

{#page:break}
