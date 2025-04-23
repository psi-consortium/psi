<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# LogicalResourceSpecification

This is a derived class of ResourceSpecification, and is used to define the invariant characteristics and behavior (attributes, methods, constraints, and relationships) of a LogicalResource.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| version | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| isBundle | boolean | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| category | string | N/A | No |
| targetResourceSchema | TargetResourceSchema | N/A | No |
| featureSpecification | array | FeatureSpecification | No |
| attachment | array | AttachmentOrDocumentRef | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| resourceSpecCharacteristic | array | CharacteristicSpecification | No |
| resourceSpecRelationship | array | ResourceSpecificationRelationship | No |
| intentSpecification | IntentSpecificationRef | N/A | No |
| externalIdentifier | array | ExternalIdentifier | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["LogicalResourceSpecification", "[ResourceFunctionSpecification](#resourcefunctionspecification)"] | Yes |

Table: Fields of LogicalResourceSpecification. {#tbl:LogicalResourceSpecification.md:LogicalResourceSpecification}

{#page:break}
