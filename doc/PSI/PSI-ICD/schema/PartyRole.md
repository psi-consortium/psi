<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PartyRole

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| role | string | N/A | No |
| engagedParty | PartyRef | N/A | No |
| partyRoleSpecification | PartyRoleSpecificationRef | N/A | No |
| characteristic | array | Characteristic | No |
| account | array | AccountRef | No |
| agreement | array | AgreementRef | No |
| contactMedium | array | ContactMedium | No |
| paymentMethod | array | PaymentMethodRef | No |
| creditProfile | array | CreditProfile | No |
| relatedParty | array | RelatedPartyOrPartyRole | No |
| status | string | N/A | No |
| statusReason | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["PartyRole", "[Supplier](#supplier)", "[Producer](#producer)", "[Consumer](#consumer)", "[BusinessPartner](#businesspartner)"] | Yes |

Table: Fields of PartyRole. {#tbl:PartyRole.md:PartyRole}

{#page:break}
