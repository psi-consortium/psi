<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# KeyIndicator

KeyIndicators are defined in terms of parameters and metrics, thresholds, and tolerances 
associated with the parameters.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| category | string | N/A | No |
| isBundled | boolean | N/A | No |
| transformationAlgorithmOfKQI | string | N/A | No |
| indicatorType | string | oneOf[KQI, KPI] | No |
| validFor | TimePeriod | N/A | No |
| keyIndicatorRelationship | array | KeyIndicatorRelationship | No |
| relatedEntity | array | RelatedEntity | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | N/A | No |

Table: Fields of KeyIndicator. {#tbl:KeyIndicator.md:KeyIndicator}

{#page:break}
