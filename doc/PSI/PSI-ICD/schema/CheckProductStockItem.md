<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# CheckProductStockItem

CheckProductStockItem is used to log and execute query about one product (or configured product) stock availability

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| availabilityResult | AvailabilityResultType | N/A | No |
| provideAlternative | boolean | N/A | No |
| alternate | array | AlternateProductStock | No |
| checkedProductStock | ProductStock | N/A | Yes |
| requestedQuantity | Quantity | N/A | Yes |
| state | TaskStateType | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of CheckProductStockItem. {#tbl:CheckProductStockItem.md:CheckProductStockItem}

{#page:break}
