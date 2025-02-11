<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductSpecificationCharacteristic

A characteristic quality or distinctive feature of a ProductSpecification.
 The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).
Certain characteristics, such as color, may be configured during the ordering or some other process.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | No |
| configurable | boolean | N/A | No |
| extensible | boolean | N/A | No |
| isUnique | boolean | N/A | No |
| maxCardinality | integer | N/A | No |
| minCardinality | integer | N/A | No |
| regex | string | N/A | No |
| valueType | string | N/A | No |
| productSpecCharRelationship | array | ProductSpecificationCharacteristicRelationship | No |
| productSpecCharacteristicValue | array | CharacteristicValueSpecification | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |
| @valueSchemaLocation | string | N/A | No |

Table: Fields of ProductSpecificationCharacteristic. {#tbl:ProductSpecificationCharacteristic.md:ProductSpecificationCharacteristic}

{#page:break}
