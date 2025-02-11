<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# BundledGroupProductOffering

Defines a group between a parent product offering and multiple child offerings, allowing (for example) choosing a minimum of 2 and a maximum of 6 offerings from within the list of child offerings.
And optionally specifying offerings that would be selected by default.
For example facilitate the choice of between 2 and 7 channel packs from a list, and cause certain items to be selected by default.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| lifecycleStatus | string | N/A | No |
| bundledGroupProductOfferingOption | BundledGroupProductOfferingOption | N/A | No |
| bundledGroupProductOffering | array | BundledGroupProductOffering | No |
| bundledProductOffering | array | BundledProductOffering | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of BundledGroupProductOffering. {#tbl:BundledGroupProductOffering.md:BundledGroupProductOffering}

{#page:break}
