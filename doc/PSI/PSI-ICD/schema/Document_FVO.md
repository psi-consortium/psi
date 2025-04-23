<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Document_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | Yes |
| description | string | N/A | No |
| documentType | string | N/A | No |
| version | string | N/A | No |
| category | array | CategoryRef_FVO | No |
| creationDate | string | date-time | No |
| expiryDate | string | date-time | No |
| documentRelationship | array | DocumentRefOrValue_FVO | No |
| lastUpdate | string | date-time | No |
| lifecycleState | string | N/A | No |
| characteristic | array | Characteristic_FVO | No |
| relatedEntity | RelatedEntity_FVO | N/A | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "Document" | Yes |

Table: Fields of Document_FVO. {#tbl:Document_FVO.md:Document_FVO}

{#page:break}
