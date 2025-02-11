<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceRefOrValue

A Service to be created defined by value or existing defined by reference.
The polymorphic attributes @type, @schemaLocation & @referredType are related to the Service entity and not the RelatedServiceRefOrValue class itself

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| href | string | N/A | No |
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
| serviceSpecification | ServiceSpecificationRef | N/A | No |
| state | ServiceStateType | N/A | No |
| supportingResource | array | ResourceRef | No |
| supportingService | array | ServiceRefOrValue | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |
| @referredType | string | N/A | No |

Table: Fields of ServiceRefOrValue. {#tbl:ServiceRefOrValue.md:ServiceRefOrValue}

{#page:break}
