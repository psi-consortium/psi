<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Resource

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| category | string | N/A | No |
| endOperatingDate | string | date-time | No |
| administrativeState | ResourceAdministrativeStateType | N/A | No |
| operationalState | ResourceOperationalStateType | N/A | No |
| resourceStatus | ResourceStatusType | N/A | No |
| usageState | ResourceUsageStateType | N/A | No |
| validFor | TimePeriod | N/A | No |
| note | array | Note | No |
| resourceOrderItem | array | RelatedResourceOrderItem | No |
| place | array | RelatedPlaceRef | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| supportingResource | array | ResourceRefOrValue | No |
| resourceRelationship | array | ResourceRelationship | No |
| resourceCharacteristic | array | Characteristic | No |
| attachment | array | AttachmentOrDocumentRef | No |
| resourceSpecification | ResourceSpecificationRef | N/A | No |
| startOperatingDate | string | date-time | No |
| resourceVersion | string | N/A | No |
| activationFeature | array | Feature | No |
| intent | IntentRef | N/A | No |
| externalIdentifier | array | ExternalIdentifier | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["LogicalResource", "PhysicalResource", "Resource", "[ResourceCollection](#resourcecollection)"] | Yes |

Table: Fields of Resource. {#tbl:Resource.md:Resource}

{#page:break}
