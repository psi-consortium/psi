<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# EntitySpecification_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | Yes |
| description | string | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| isBundle | boolean | N/A | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| targetEntitySchema | TargetEntitySchema_FVO | N/A | No |
| specCharacteristic | array | CharacteristicSpecification_FVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| constraint | array | ConstraintRef_FVO | No |
| entitySpecRelationship | array | EntitySpecificationRelationship_FVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["EntitySpecification", "[ServiceSpecification](#servicespecification_fvo)"] | Yes |

Table: Fields of EntitySpecification_FVO. {#tbl:EntitySpecification_FVO.md:EntitySpecification_FVO}

{#page:break}
