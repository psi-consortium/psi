<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ResourceSpecification_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | Yes |
| description | string | N/A | No |
| version | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| isBundle | boolean | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| category | string | N/A | No |
| targetResourceSchema | TargetResourceSchema_FVO | N/A | No |
| featureSpecification | array | FeatureSpecification_FVO | No |
| attachment | array | AttachmentOrDocumentRef | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| resourceSpecCharacteristic | array | CharacteristicSpecification_FVO | No |
| resourceSpecRelationship | array | ResourceSpecificationRelationship_FVO | No |
| intentSpecification | IntentSpecificationRef_FVO | N/A | No |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ResourceSpecification" | Yes |

Table: Fields of ResourceSpecification_FVO. {#tbl:ResourceSpecification_FVO.md:ResourceSpecification_FVO}

{#page:break}
