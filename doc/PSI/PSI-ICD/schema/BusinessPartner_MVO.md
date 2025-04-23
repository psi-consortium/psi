<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# BusinessPartner_MVO

When business partner is the BusinessPartner 

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| name | string | N/A | Yes |
| description | string | N/A | No |
| role | string | N/A | No |
| engagedParty | PartyRef_MVO | N/A | Yes |
| partyRoleSpecification | PartyRoleSpecificationRef_MVO | N/A | No |
| characteristic | array | Characteristic_MVO | No |
| account | array | AccountRef_MVO | No |
| agreement | array | AgreementRef_MVO | No |
| contactMedium | array | ContactMedium_MVO | No |
| paymentMethod | array | PaymentMethodRef_MVO | No |
| creditProfile | array | CreditProfile_MVO | No |
| relatedParty | array | RelatedPartyOrPartyRole_MVO | No |
| status | string | N/A | No |
| statusReason | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "BusinessPartner" | Yes |

Table: Fields of BusinessPartner_MVO. {#tbl:BusinessPartner_MVO.md:BusinessPartner_MVO}

{#page:break}
