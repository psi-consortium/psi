<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PhysicalResourceSpecification

This is an example of a derived class of ResourceSpecification, and is used to define the invariant characteristics and behavior (attributes, methods, constraints, and relationships) of a PhysicalResource.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| model | string | N/A | No |
| part | string | N/A | No |
| sku | string | N/A | No |
| vendor | string | N/A | No |
| category | string | N/A | No |
| isBundle | boolean | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| version | string | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| featureSpecification | array | FeatureSpecification | No |
| relatedParty | array | RelatedParty | No |
| resourceSpecCharacteristic | array | ResourceSpecificationCharacteristic | No |
| resourceSpecRelationship | array | ResourceSpecificationRelationship | No |
| targetResourceSchema | TargetResourceSchema | N/A | No |
| validFor | TimePeriod | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | "PhysicalResourceSpecification" | Yes |

Table: Fields of PhysicalResourceSpecification. {#tbl:PhysicalResourceSpecification.md:PhysicalResourceSpecification}
