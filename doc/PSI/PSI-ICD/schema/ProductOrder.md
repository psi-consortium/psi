<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrder

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| description | string | N/A | No |
| agreement | array | AgreementRef | No |
| billingAccount | BillingAccountRef | N/A | No |
| state | ProductOrderStateType | N/A | No |
| requestedInitialState | InitialProductOrderStateType | N/A | No |
| cancellationDate | string | date-time | No |
| cancellationReason | string | N/A | No |
| category | string | N/A | No |
| channel | array | RelatedChannel | No |
| expectedCompletionDate | string | date-time | No |
| externalId | array | ExternalIdentifier | No |
| note | array | Note | No |
| notificationContact | string | N/A | No |
| orderTotalPrice | array | OrderPrice | No |
| payment | array | PaymentRef | No |
| orderRelationship | array | OrderRelationship | No |
| priority | string | N/A | No |
| productOfferingQualification | array | ProductOfferingQualificationRef | No |
| quote | array | QuoteRef | No |
| productOrderErrorMessage | array | ProductOrderErrorMessage | No |
| productOrderJeopardyAlert | array | ProductOrderJeopardyAlert | No |
| productOrderMilestone | array | ProductOrderMilestone | No |
| productOrderItem | array | ProductOrderItem | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| requestedCompletionDate | string | date-time | No |
| requestedStartDate | string | date-time | No |
| creationDate | string | date-time | No |
| completionDate | string | date-time | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOrder" | Yes |

Table: Fields of ProductOrder. {#tbl:ProductOrder.md:ProductOrder}

{#page:break}
