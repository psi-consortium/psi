<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductStock

ProductStock is a base class for defining a product (or configured product with values characteristic) stock level.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| creationDate | string | date-time | No |
| lastInventoryDate | string | date-time | No |
| lastUpdate | string | date-time | No |
| replenishmentDate | string | date-time | No |
| stockLevelCategory | string | N/A | No |
| channel | array | ChannelRef | No |
| marketSegment | array | MarketSegmentRef | No |
| maxStockLevel | Quantity | N/A | No |
| minStockLevel | Quantity | N/A | No |
| place | RelatedPlaceRefOrValue | N/A | No |
| productStockLevel | Quantity | N/A | No |
| productStockRelationship | array | ProductStockRelationship | No |
| productStockStatusType | ProductStockStatusType | N/A | No |
| productStockUsageType | ProductStockUsageType | N/A | No |
| relatedParty | array | RelatedParty | No |
| reorderQuantity | Quantity | N/A | No |
| resource | array | ResourceRef | No |
| stockLevelAlert | Quantity | N/A | No |
| stockedProduct | ProductRefOrValue | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ProductStock. {#tbl:ProductStock.md:ProductStock}

{#page:break}
