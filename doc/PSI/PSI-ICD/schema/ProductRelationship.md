<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductRelationship

Linked products to the one instantiate, such as [bundled] if the product is a bundle and you want to describe the bundled products inside this bundle; [reliesOn] if the product needs another already owned product to rely on (e.g.
an option on an already owned mobile access product) [targets] or [isTargeted] (depending on the way of expressing the link) for any other kind of links that may be useful

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| relationshipType | string | N/A | Yes |
| product | ProductRefOrValue | N/A | Yes |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of ProductRelationship. {#tbl:ProductRelationship.md:ProductRelationship}

{#page:break}
