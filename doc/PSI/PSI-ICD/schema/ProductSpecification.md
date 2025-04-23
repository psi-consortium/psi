<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductSpecification

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| brand | string | N/A | No |
| isBundle | boolean | N/A | No |
| productNumber | string | N/A | No |
| category | array | CategoryRef | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| productSpecCharacteristic | array | CharacteristicSpecification | No |
| serviceSpecification | array | ServiceSpecificationRef | No |
| bundledProductSpecification | array | BundledProductSpecification | No |
| productSpecificationRelationship | array | ProductSpecificationRelationship | No |
| resourceSpecification | array | ResourceSpecificationRef | No |
| attachment | array | AttachmentOrDocumentRef | No |
| policy | array | PolicyRef | No |
| targetProductSchema | TargetProductSchema | N/A | No |
| intentSpecification | IntentSpecificationRef | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| externalIdentifier | array | ExternalIdentifier | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductSpecification" | Yes |

Table: Fields of ProductSpecification. {#tbl:ProductSpecification.md:ProductSpecification}

{#page:break}
