<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Service_Create

Service is a base class for defining the Service hierarchy.
All Services are characterized as either being possibly visible and usable by a Customer or not.
This gives rise to the two subclasses of Service: CustomerFacingService and ResourceFacingService.
Skipped properties: id,href

| Field | Type | Format | Required |
|-------|---|--------|---|
| name | string | N/A | No |
| description | string | N/A | No |
| category | string | N/A | No |
| endDate | string | date-time | No |
| hasStarted | boolean | N/A | No |
| isBundle | boolean | N/A | No |
| isServiceEnabled | boolean | N/A | No |
| isStateful | boolean | N/A | No |
| serviceDate | string | N/A | No |
| serviceType | string | N/A | No |
| startDate | string | date-time | No |
| startMode | string | N/A | No |
| feature | array | Feature | No |
| note | array | Note | No |
| place | array | RelatedPlaceRefOrValue | No |
| relatedEntity | array | RelatedEntityRefOrValue | No |
| relatedParty | array | RelatedParty | No |
| serviceCharacteristic | array | Characteristic | No |
| serviceOrderItem | array | RelatedServiceOrderItem | No |
| serviceRelationship | array | ServiceRelationship | No |
| serviceSpecification | ServiceSpecificationRef | N/A | Yes |
| state | ServiceStateType | N/A | Yes |
| supportingResource | array | ResourceRef | No |
| supportingService | array | ServiceRefOrValue | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of Service_Create. {#tbl:Service_Create.md:Service_Create}
