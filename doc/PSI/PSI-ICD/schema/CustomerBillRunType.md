<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# CustomerBillRunType

Recommended Enumeration Type (not formal forced in standard): Valid values for the runType of a bill.
The bill could be produced in a regular bill cycle 'onCycle'.
Otherwise the bill is produced on a request (e.g.
customer request).
This could be indicated by 'offCycle'

| Field | Type | Format | Required |
|-------|---|--------|---|
| CustomerBillRunType | string | oneOf[onCycle, offCycle] | No |

Table: Fields of CustomerBillRunType. {#tbl:CustomerBillRunType.md:CustomerBillRunType}
