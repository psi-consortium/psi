<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrder_MVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| description | string | N/A | No |
| agreement | array | AgreementRef_MVO | No |
| billingAccount | BillingAccountRef_MVO | N/A | No |
| state | ProductOrderStateType | N/A | No |
| cancellationDate | string | date-time | No |
| cancellationReason | string | N/A | No |
| category | string | N/A | No |
| channel | array | RelatedChannel_MVO | No |
| expectedCompletionDate | string | date-time | No |
| externalId | array | ExternalIdentifier_MVO | No |
| note | array | Note_MVO | No |
| notificationContact | string | N/A | No |
| orderTotalPrice | array | OrderPrice_MVO | No |
| payment | array | PaymentRef_MVO | No |
| orderRelationship | array | OrderRelationship_MVO | No |
| priority | string | N/A | No |
| productOfferingQualification | array | ProductOfferingQualificationRef_MVO | No |
| quote | array | QuoteRef_MVO | No |
| productOrderErrorMessage | array | ProductOrderErrorMessage_MVO | No |
| productOrderJeopardyAlert | array | ProductOrderJeopardyAlert_MVO | No |
| productOrderMilestone | array | ProductOrderMilestone_MVO | No |
| productOrderItem | array | ProductOrderItem_MVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_MVO | No |
| requestedCompletionDate | string | date-time | No |
| requestedStartDate | string | date-time | No |
| completionDate | string | date-time | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOrder" | Yes |

Table: Fields of ProductOrder_MVO. {#tbl:ProductOrder_MVO.md:ProductOrder_MVO}

{#page:break}
