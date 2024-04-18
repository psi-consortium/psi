<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# InquiredProductCharacteristic

A characteristic quality or distinctive feature of an InquiredProduct.
The characteristic can take a discrete value, such as color, can take a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | Yes |
| name | string | N/A | No |
| priority | number | N/A | No |
| valueType | string | N/A | No |
| inquiredProductCharacteristicValue | array | InquiredCharacteristicValueSpecification | Yes |

Table: Fields of InquiredProductCharacteristic. {#tbl:InquiredProductCharacteristic.md:InquiredProductCharacteristic}
