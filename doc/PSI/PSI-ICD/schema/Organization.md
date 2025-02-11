<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Organization

Organization represents a group of people identified by shared interests or purpose.
Examples include business, department and enterprise.
Because of the complex nature of many businesses, both organizations and organization units are represented by the same data.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| isHeadOffice | boolean | N/A | No |
| isLegalEntity | boolean | N/A | No |
| nameType | string | N/A | No |
| organizationType | string | N/A | No |
| tradingName | string | N/A | No |
| contactMedium | array | ContactMedium | No |
| creditRating | array | PartyCreditProfile | No |
| existsDuring | TimePeriod | N/A | No |
| externalReference | array | ExternalReference | No |
| organizationChildRelationship | array | OrganizationChildRelationship | No |
| organizationIdentification | array | OrganizationIdentification | No |
| organizationParentRelationship | OrganizationParentRelationship | N/A | No |
| otherName | array | OtherNameOrganization | No |
| partyCharacteristic | array | Characteristic | No |
| relatedParty | array | RelatedParty | No |
| status | OrganizationStateType | N/A | No |
| taxExemptionCertificate | array | TaxExemptionCertificate | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of Organization. {#tbl:Organization.md:Organization}

{#page:break}
