<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# QualificationResultType

PSI closed vocabulary for the result of the whole qualification request; same values and meaning as QualificationItemResultType, fixed for interoperability.
The result is red as soon as any completed item is red; once every item is completed, yellow if any item is yellow, otherwise green; absent until then.
Replaces the free-form string of TMF679 v5.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| QualificationResultType | string | oneOf[green, yellow, red] | No |

Table: Fields of QualificationResultType. {#tbl:QualificationResultType.md:QualificationResultType}

{#page:break}
