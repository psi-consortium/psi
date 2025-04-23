<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductSpecification_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | Yes |
| description | string | N/A | No |
| brand | string | N/A | No |
| isBundle | boolean | N/A | No |
| productNumber | string | N/A | No |
| category | array | CategoryRef_FVO | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| productSpecCharacteristic | array | CharacteristicSpecification_FVO | No |
| serviceSpecification | array | ServiceSpecificationRef_FVO | No |
| bundledProductSpecification | array | BundledProductSpecification_FVO | No |
| productSpecificationRelationship | array | ProductSpecificationRelationship_FVO | No |
| resourceSpecification | array | ResourceSpecificationRef_FVO | No |
| attachment | array | AttachmentOrDocumentRef | No |
| policy | array | PolicyRef_FVO | No |
| targetProductSchema | TargetProductSchema_FVO | N/A | No |
| intentSpecification | IntentSpecificationRef_FVO | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | Yes |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductSpecification" | Yes |

Table: Fields of ProductSpecification_FVO. {#tbl:ProductSpecification_FVO.md:ProductSpecification_FVO}

{#page:break}
