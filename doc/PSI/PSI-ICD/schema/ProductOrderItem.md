<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrderItem

An identified part of the order.
A product order is decomposed into one or more order items.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| quantity | integer | N/A | No |
| action | OrderItemActionType | N/A | Yes |
| appointment | AppointmentRef | N/A | No |
| billingAccount | BillingAccountRef | N/A | No |
| itemPrice | array | OrderPrice | No |
| itemTerm | array | OrderTerm | No |
| itemTotalPrice | array | OrderPrice | No |
| payment | array | PaymentRef | No |
| product | ProductRefOrValue | N/A | No |
| productOffering | ProductOfferingRef | N/A | No |
| productOfferingQualificationItem | ProductOfferingQualificationItemRef | N/A | No |
| productOrderItem | array | ProductOrderItem | No |
| productOrderItemRelationship | array | OrderItemRelationship | No |
| qualification | array | ProductOfferingQualificationRef | No |
| quoteItem | QuoteItemRef | N/A | No |
| state | ProductOrderItemStateType | N/A | No |
| requestedStartDate | string | date-time | No |
| requestedEndDate | string | date-time | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ProductOrderItem. {#tbl:ProductOrderItem.md:ProductOrderItem}

{#page:break}
