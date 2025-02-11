<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOfferingPriceRefOrValue

A new product offering price being created by value or a reference to an existing product offering price that alreasy created.
The polymorphic attributes @type, @schemaLocation & @referredType are related to the product offering price and not to this ReforValue structure

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| priceType | string | N/A | No |
| recurringChargePeriod | string | N/A | No |
| recurringChargePeriodLength | integer | N/A | No |
| version | string | N/A | No |
| constraint | array | ConstraintRef | No |
| price | ProductPriceValue | N/A | No |
| priceAlteration | array | POPAlteration | No |
| unitOfMeasure | Quantity | N/A | No |
| validFor | TimePeriod | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |
| @referredType | string | N/A | No |

Table: Fields of ProductOfferingPriceRefOrValue. {#tbl:ProductOfferingPriceRefOrValue.md:ProductOfferingPriceRefOrValue}

{#page:break}
