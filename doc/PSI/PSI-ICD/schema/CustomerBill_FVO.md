<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# CustomerBill_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| amountDue | Money | N/A | No |
| appliedPayment | array | AppliedPayment | No |
| billDate | string | date-time | No |
| billDocument | array | AttachmentOrDocumentRef | No |
| billNo | string | N/A | No |
| billingAccount | BillingAccountRef | N/A | No |
| billingPeriod | TimePeriod | N/A | No |
| billCycle | BillCycleRef | N/A | No |
| category | string | N/A | No |
| financialAccount | FinancialAccountRef | N/A | No |
| lastUpdate | string | date-time | No |
| nextBillDate | string | date-time | No |
| paymentDueDate | string | date-time | No |
| paymentMethod | PaymentMethodRef | N/A | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| remainingAmount | Money | N/A | No |
| runType | CustomerBillRunType | N/A | No |
| taxExcludedAmount | Money | N/A | No |
| taxIncludedAmount | Money | N/A | No |
| taxItem | array | TaxItem | No |
| state | CustomerBillStateType | N/A | No |
| productOrder | ProductOrderRef | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "CustomerBill" | Yes |

Table: Fields of CustomerBill_FVO. {#tbl:CustomerBill_FVO.md:CustomerBill_FVO}

{#page:break}
