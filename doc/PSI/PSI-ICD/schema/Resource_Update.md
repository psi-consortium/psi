<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Resource_Update

Resource is an abstract entity that describes the common set of attributes shared by all concrete resources (e.g.
TPE, EQUIPMENT) in the inventory.
Skipped properties: id,href

| Field | Type | Format | Required |
|-------|---|--------|---|
| name | string | N/A | No |
| description | string | N/A | No |
| category | string | N/A | No |
| endOperatingDate | string | date-time | No |
| resourceVersion | string | N/A | No |
| startOperatingDate | string | date-time | No |
| activationFeature | array | Feature | No |
| administrativeState | ResourceAdministrativeStateType | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| note | array | Note | No |
| operationalState | ResourceOperationalStateType | N/A | No |
| place | RelatedPlaceRefOrValue | N/A | No |
| relatedParty | array | RelatedParty | No |
| resourceCharacteristic | array | Characteristic | No |
| resourceRelationship | array | ResourceRelationship | No |
| resourceSpecification | ResourceSpecificationRef | N/A | No |
| resourceStatus | ResourceStatusType | N/A | No |
| usageState | ResourceUsageStateType | N/A | No |
| value | string | N/A | No |
| manufactureDate | string | date-time | No |
| powerState | string | N/A | No |
| serialNumber | string | N/A | No |
| versionNumber | string | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of Resource_Update. {#tbl:Resource_Update.md:Resource_Update}
