<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# EntitySpecification_MVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| name | string | N/A | No |
| description | string | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| isBundle | boolean | N/A | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| targetEntitySchema | TargetEntitySchema_MVO | N/A | No |
| specCharacteristic | array | CharacteristicSpecification_MVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_MVO | No |
| constraint | array | ConstraintRef_MVO | No |
| entitySpecRelationship | array | EntitySpecificationRelationship_MVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["EntitySpecification", "[ServiceSpecification](#servicespecification_mvo)"] | Yes |

Table: Fields of EntitySpecification_MVO. {#tbl:EntitySpecification_MVO.md:EntitySpecification_MVO}

{#page:break}
