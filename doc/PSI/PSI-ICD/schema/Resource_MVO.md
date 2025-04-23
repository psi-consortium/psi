<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Resource_MVO

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
| note | array | Note_MVO | No |
| resourceOrderItem | array | RelatedResourceOrderItem_MVO | No |
| place | array | RelatedPlaceRef_MVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_MVO | No |
| supportingResource | array | ResourceRefOrValue_MVO | No |
| resourceRelationship | array | ResourceRelationship_MVO | No |
| resourceCharacteristic | array | Characteristic_MVO | No |
| attachment | array | AttachmentOrDocumentRef | No |
| resourceSpecification | ResourceSpecificationRef_MVO | N/A | No |
| startOperatingDate | string | date-time | No |
| resourceVersion | string | N/A | No |
| activationFeature | array | Feature_MVO | No |
| intent | IntentRef_MVO | N/A | No |
| externalIdentifier | array | ExternalIdentifier_MVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["LogicalResource", "PhysicalResource", "Resource", "[ResourceCollection](#resourcecollection_mvo)"] | Yes |

Table: Fields of Resource_MVO. {#tbl:Resource_MVO.md:Resource_MVO}

{#page:break}
