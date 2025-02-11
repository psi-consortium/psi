<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# RelatedEntityRefOrValue

A reference to an entity, where the type of the entity is not known in advance.
A related entity defines a entity described by reference or by value linked to a specific entity.
The polymorphic attributes @type, @schemaLocation & @referredType are related to the Entity and not the RelatedEntityRefOrValue class itself

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| href | string | N/A | No |
| name | string | N/A | No |
| role | string | N/A | Yes |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |
| @referredType | string | N/A | Yes |

Table: Fields of RelatedEntityRefOrValue. {#tbl:RelatedEntityRefOrValue.md:RelatedEntityRefOrValue}

{#page:break}
