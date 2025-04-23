<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrder_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| description | string | N/A | No |
| agreement | array | AgreementRef_FVO | No |
| billingAccount | BillingAccountRef_FVO | N/A | No |
| requestedInitialState | InitialProductOrderStateType | N/A | No |
| category | string | N/A | No |
| channel | array | RelatedChannel_FVO | No |
| externalId | array | ExternalIdentifier_FVO | No |
| note | array | Note_FVO | No |
| notificationContact | string | N/A | No |
| orderTotalPrice | array | OrderPrice_FVO | No |
| payment | array | PaymentRef_FVO | No |
| orderRelationship | array | OrderRelationship_FVO | No |
| priority | string | N/A | No |
| productOfferingQualification | array | ProductOfferingQualificationRef_FVO | No |
| quote | array | QuoteRef_FVO | No |
| productOrderErrorMessage | array | ProductOrderErrorMessage_FVO | No |
| productOrderJeopardyAlert | array | ProductOrderJeopardyAlert_FVO | No |
| productOrderMilestone | array | ProductOrderMilestone_FVO | No |
| productOrderItem | array | ProductOrderItem_FVO | Yes |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| requestedCompletionDate | string | date-time | No |
| requestedStartDate | string | date-time | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOrder" | Yes |

Table: Fields of ProductOrder_FVO. {#tbl:ProductOrder_FVO.md:ProductOrder_FVO}

{#page:break}
