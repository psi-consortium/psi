<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceSpecification_Create

ServiceSpecification is a class that offers characteristics to describe a type of service.
Functionally, it acts as a template by which Services may be instantiated.
By sharing the same specification, these services would therefore share the same set of characteristics.
Skipped properties: id,href

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| name | string | N/A | Yes |
| description | string | N/A | No |
| isBundle | boolean | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| version | string | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| constraint | array | ConstraintRef | No |
| entitySpecRelationship | array | EntitySpecificationRelationship | No |
| featureSpecification | array | ServiceFeatureSpecification | No |
| relatedParty | array | RelatedParty | No |
| resourceSpecification | array | ResourceSpecificationRef | No |
| serviceLevelSpecification | array | ServiceLevelSpecificationRef | No |
| serviceSpecRelationship | array | ServiceSpecRelationship | No |
| specCharacteristic | array | CharacteristicSpecification | No |
| targetEntitySchema | TargetEntitySchema | N/A | No |
| validFor | TimePeriod | N/A | No |
| category | string | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ServiceSpecification_Create. {#tbl:ServiceSpecification_Create.md:ServiceSpecification_Create}

{#page:break}
