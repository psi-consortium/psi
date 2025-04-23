<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Service_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | No |
| isServiceEnabled | boolean | N/A | No |
| hasStarted | boolean | N/A | No |
| startMode | string | N/A | No |
| isStateful | boolean | N/A | No |
| startDate | string | date-time | No |
| endDate | string | date-time | No |
| serviceOrderItem | array | RelatedServiceOrderItem_FVO | No |
| note | array | Note_FVO | No |
| serviceType | string | N/A | No |
| isBundle | boolean | N/A | No |
| category | string | N/A | No |
| feature | array | Feature_FVO | No |
| relatedEntity | array | RelatedEntity_FVO | No |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| serviceCharacteristic | array | Characteristic_FVO | No |
| serviceRelationship | array | ServiceRelationship_FVO | No |
| supportingService | array | ServiceRefOrValue_FVO | No |
| supportingResource | array | ResourceRef_FVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| place | array | RelatedPlaceRefOrValue_FVO | No |
| state | ServiceStateType | N/A | Yes |
| serviceSpecification | ServiceSpecificationRef_FVO | N/A | Yes |
| intent | IntentRefOrValue_FVO | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "Service" | Yes |

Table: Fields of Service_FVO. {#tbl:Service_FVO.md:Service_FVO}

{#page:break}
