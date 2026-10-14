<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Quote_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| description | string | N/A | No |
| agreement | array | AgreementRef | No |
| salesOpportunity | SalesOpportunityRef | N/A | No |
| category | string | N/A | No |
| contactMedium | array | ContactMedium | No |
| expectedFulfillmentStartDate | string | date-time | No |
| externalId | array | ExternalIdentifier_FVO | No |
| instantSyncQuote | boolean | N/A | No |
| note | array | Note | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| productOfferingQualification | array | ProductOfferingQualificationRef | No |
| requestedQuoteCompletionDate | string | date-time | No |
| version | string | N/A | No |
| quoteItem | array | QuoteItem_FVO | Yes |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "Quote" | Yes |

Table: Fields of Quote_FVO. {#tbl:Quote_FVO.md:Quote_FVO}

{#page:break}
