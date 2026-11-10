<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# QuoteStateType

Possible values for the state of the quote.
In PSI, the state in which the customer rejects an approved quote is named refused (rejected in TMF648); rejected is the state of a quote that failed the business validation; declined is not used.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| QuoteStateType | string | oneOf[acknowledged, rejected, pending, inProgress, cancelled, approved, accepted, refused] | No |

Table: Fields of QuoteStateType. {#tbl:QuoteStateType.md:QuoteStateType}

{#page:break}
