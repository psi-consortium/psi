<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ResourceSpecification_Create

Resources are physical or non-physical components (or some combination of these) within an enterprise's infrastructure or inventory.
They are typically consumed or used by services (for example a physical port assigned to a service) or contribute to the realization of a Product (for example, a SIM card).
They can be drawn from the Application, Computing and Network domains, and include, for example, Network Elements, software, IT systems, content and information, and technology components.
A ResourceSpecification is a base class that represents a generic means for implementing a particular type of Resource.
In essence, a ResourceSpecification defines the common attributes and relationships of a set of related Resources, while Resource defines a specific instance that is based on a particular ResourceSpecification.
Skipped properties: id,href

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| name | string | N/A | Yes |
| description | string | N/A | No |
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
| model | string | N/A | No |
| part | string | N/A | No |
| sku | string | N/A | No |
| vendor | string | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ResourceSpecification_Create. {#tbl:ResourceSpecification_Create.md:ResourceSpecification_Create}

{#page:break}
