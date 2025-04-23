<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Resource_FVO

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
| note | array | Note_FVO | No |
| resourceOrderItem | array | RelatedResourceOrderItem_FVO | No |
| place | array | RelatedPlaceRef_FVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| supportingResource | array | ResourceRefOrValue_FVO | No |
| resourceRelationship | array | ResourceRelationship_FVO | No |
| resourceCharacteristic | array | Characteristic_FVO | No |
| attachment | array | AttachmentOrDocumentRef | No |
| resourceSpecification | ResourceSpecificationRef_FVO | N/A | No |
| startOperatingDate | string | date-time | No |
| resourceVersion | string | N/A | No |
| activationFeature | array | Feature_FVO | No |
| intent | IntentRef_FVO | N/A | No |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | oneOf["LogicalResource", "PhysicalResource", "Resource", "[ResourceCollection](#resourcecollection_fvo)"] | Yes |

Table: Fields of Resource_FVO. {#tbl:Resource_FVO.md:Resource_FVO}

{#page:break}
