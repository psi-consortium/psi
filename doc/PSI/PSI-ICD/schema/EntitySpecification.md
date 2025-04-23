<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# EntitySpecification

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| isBundle | boolean | N/A | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| targetEntitySchema | TargetEntitySchema | N/A | No |
| specCharacteristic | array | CharacteristicSpecification | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| constraint | array | ConstraintRef | No |
| entitySpecRelationship | array | EntitySpecificationRelationship | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["EntitySpecification", "[ServiceSpecification](#servicespecification)"] | Yes |

Table: Fields of EntitySpecification. {#tbl:EntitySpecification.md:EntitySpecification}

{#page:break}
