<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceLevelObjectiveThreshold

A threshold base entity containing threshold rules.This entity is mandatory.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | No |
| thresholdType | string | oneOf[LogicDefinition, PreDefined] | No |
| consequence | array | ServiceLevelSpecConsequence | No |
| thresholdCondition | string | oneOf[Raise, Clear] | No |
| applicability | array | ApplicableTimePeriod | No |
| upperBound | Quantity | N/A | No |
| lowerBound | Quantity | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | N/A | No |
| \@type | string | N/A | No |

Table: Fields of ServiceLevelObjectiveThreshold. {#tbl:ServiceLevelObjectiveThreshold.md:ServiceLevelObjectiveThreshold}
