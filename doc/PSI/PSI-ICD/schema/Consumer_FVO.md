<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Consumer_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | Yes |
| description | string | N/A | No |
| role | string | N/A | No |
| engagedParty | PartyRef_FVO | N/A | Yes |
| partyRoleSpecification | PartyRoleSpecificationRef_FVO | N/A | No |
| characteristic | array | Characteristic_FVO | No |
| account | array | AccountRef_FVO | No |
| agreement | array | AgreementRef_FVO | No |
| contactMedium | array | ContactMedium_FVO | No |
| paymentMethod | array | PaymentMethodRef_FVO | No |
| creditProfile | array | CreditProfile_FVO | No |
| relatedParty | array | RelatedPartyOrPartyRole_FVO | No |
| status | string | N/A | No |
| statusReason | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "Consumer" | Yes |

Table: Fields of Consumer_FVO. {#tbl:Consumer_FVO.md:Consumer_FVO}

{#page:break}
