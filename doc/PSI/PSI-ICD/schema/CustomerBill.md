<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# CustomerBill

The customer bill.
Can be a regular recurring bill or an extra bill on demand by the customer or the csp.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | N/A | No |
| billDate | string | date-time | No |
| billNo | string | N/A | No |
| category | string | N/A | No |
| lastUpdate | string | date-time | No |
| nextBillDate | string | date-time | No |
| paymentDueDate | string | date-time | No |
| amountDue | Money | N/A | No |
| appliedPayment | array | AppliedPayment | No |
| billCycle | BillCycleRef | N/A | No |
| billDocument | array | AttachmentOrDocumentRef | No |
| billingAccount | BillingAccountRef | N/A | No |
| billingPeriod | TimePeriod | N/A | No |
| financialAccount | FinancialAccountRef | N/A | No |
| paymentMethod | PaymentMethodRef | N/A | No |
| relatedParty | array | RelatedParty | No |
| remainingAmount | Money | N/A | No |
| runType | CustomerBillRunType | N/A | No |
| state | CustomerBillStateType | N/A | No |
| taxExcludedAmount | Money | N/A | No |
| taxIncludedAmount | Money | N/A | No |
| taxItem | array | TaxItem | No |
| productOrder | ProductOrderRef | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of CustomerBill. {#tbl:CustomerBill.md:CustomerBill}
