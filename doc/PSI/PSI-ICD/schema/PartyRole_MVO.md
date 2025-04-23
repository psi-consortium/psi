<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PartyRole_MVO

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
| @type | string | oneOf["PartyRole", "[Supplier](#supplier_mvo)", "[Producer](#producer_mvo)", "[Consumer](#consumer_mvo)", "[BusinessPartner](#businesspartner_mvo)"] | Yes |

Table: Fields of PartyRole_MVO. {#tbl:PartyRole_MVO.md:PartyRole_MVO}

{#page:break}
