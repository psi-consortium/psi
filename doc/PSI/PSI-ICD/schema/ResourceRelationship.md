<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ResourceRelationship

Linked resources to the one instantiate, such as [bundled] if the resource is a bundle and you want to describe the bundled resources inside this bundle; [reliesOn] if the resource needs another already owned resource to rely on (e.g.
an option on an already owned mobile access resource) [targets] or [isTargeted] (depending on the way of expressing the link) for any other kind of links that may be useful

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | uri | No |
| relationshipType | string | N/A | Yes |
| resource | ResourceRefOrValue | N/A | Yes |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of ResourceRelationship. {#tbl:ResourceRelationship.md:ResourceRelationship}
