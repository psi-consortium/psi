<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrder_Create

A Product Order is a type of order which  can  be used to place an order between a customer and a service provider or between a service provider and a partner and vice versa,
Skipped properties: id,href,completionDate,orderDate,state,expectedCompletionDate,productOrderItem.state

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| description | string | N/A | No |
| cancellationDate | string | date-time | No |
| cancellationReason | string | N/A | No |
| category | string | N/A | No |
| externalId | string | N/A | No |
| notificationContact | string | N/A | No |
| priority | string | N/A | No |
| requestedCompletionDate | string | date-time | No |
| requestedStartDate | string | date-time | No |
| agreement | array | AgreementRef | No |
| billingAccount | BillingAccountRef | N/A | No |
| channel | array | RelatedChannel | No |
| note | array | Note | No |
| orderTotalPrice | array | OrderPrice | No |
| payment | array | PaymentRef | No |
| productOfferingQualification | array | ProductOfferingQualificationRef | No |
| productOrderItem | array | ProductOrderItem | Yes |
| quote | array | QuoteRef | No |
| relatedParty | array | RelatedParty | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ProductOrder_Create. {#tbl:ProductOrder_Create.md:ProductOrder_Create}

{#page:break}
