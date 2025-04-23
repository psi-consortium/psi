<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceSpecification_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
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
| resourceSpecification | array | ResourceSpecificationRef_FVO | No |
| serviceLevelSpecification | array | ServiceLevelSpecificationRef_FVO | No |
| serviceSpecRelationship | array | EntityRelationship_FVO | No |
| intentSpecification | IntentSpecificationRef_FVO | N/A | No |
| featureSpecification | array | FeatureSpecification_FVO | No |
| category | string | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | Yes |
| @type | string | "ServiceSpecification" | Yes |

Table: Fields of ServiceSpecification_FVO. {#tbl:ServiceSpecification_FVO.md:ServiceSpecification_FVO}

{#page:break}
