<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductSpecificationCharacteristicValueUse

A use of the ProductSpecificationCharacteristicValue by a ProductOffering to which additional properties (attributes) apply or override the properties of similar properties contained in ProductSpecificationCharacteristicValue.
It should be noted that characteristics which their value(s) addressed by this object must exist in corresponding product specification.
The available characteristic values for a ProductSpecificationCharacteristic in a Product specification can be modified at the ProductOffering level.
For example, a characteristic 'Color' might have values White, Blue, Green, and Red.
But, the list of values can be restricted to e.g.
White and Blue in an associated product offering.
It should be noted that the list of values in 'ProductSpecificationCharacteristicValueUse' is a strict subset of the list of values as defined in the corresponding product specification characteristics.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | No |
| maxCardinality | integer | N/A | No |
| minCardinality | integer | N/A | No |
| valueType | string | N/A | No |
| productSpecCharacteristicValue | array | CharacteristicValueSpecification | No |
| productSpecification | ProductSpecificationRef | N/A | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ProductSpecificationCharacteristicValueUse. {#tbl:ProductSpecificationCharacteristicValueUse.md:ProductSpecificationCharacteristicValueUse}

{#page:break}
