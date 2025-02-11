<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# CustomerBillStateType

Recommended Enumeration Type (not formal forced in standard): Valid values for the lifecycle state of the bill: new = 'bill is ready to validate or to sent', validated = 'bill is checked (manual / automatic)', sent = 'bill is sent with the channel defined in the billingaccount', settled = 'bill is payed', partiallySettled = 'bill is partially payed', onHold = 'bill will not be in further processing until open issues connected to the bill are solved'

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| CustomerBillStateType | string | oneOf[new, onHold, validated, sent, settled, partiallyPaid, withdrawn] | No |

Table: Fields of CustomerBillStateType. {#tbl:CustomerBillStateType.md:CustomerBillStateType}

{#page:break}
