<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductSpecification_MVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| name | string | N/A | No |
| description | string | N/A | No |
| brand | string | N/A | No |
| isBundle | boolean | N/A | No |
| productNumber | string | N/A | No |
| category | array | CategoryRef_MVO | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_MVO | No |
| productSpecCharacteristic | array | CharacteristicSpecification_MVO | No |
| serviceSpecification | array | ServiceSpecificationRef_MVO | No |
| bundledProductSpecification | array | BundledProductSpecification_MVO | No |
| productSpecificationRelationship | array | ProductSpecificationRelationship_MVO | No |
| resourceSpecification | array | ResourceSpecificationRef_MVO | No |
| attachment | array | AttachmentOrDocumentRef | No |
| policy | array | PolicyRef_MVO | No |
| targetProductSchema | TargetProductSchema_MVO | N/A | No |
| intentSpecification | IntentSpecificationRef_MVO | N/A | No |
| lifecycleStatus | string | N/A | No |
| externalIdentifier | array | ExternalIdentifier_MVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductSpecification" | Yes |

Table: Fields of ProductSpecification_MVO. {#tbl:ProductSpecification_MVO.md:ProductSpecification_MVO}

{#page:break}
