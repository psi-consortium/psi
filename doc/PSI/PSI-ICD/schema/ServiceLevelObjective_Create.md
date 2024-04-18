<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceLevelObjective_Create

Service level objectives are defined in terms of parameters and metrics, thresholds, and tolerances associated with the parameters.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | N/A | No |
| name | string | N/A | No |
| conformancePeriod | TimePeriod | N/A | No |
| conformanceTarget | string | N/A | Yes |
| graceTimes | string | N/A | No |
| thresholdTarget | string | N/A | No |
| tolerancePeriod | TimeInterval | N/A | No |
| toleranceTarget | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| keyIndicator | KeyIndicator | N/A | Yes |
| consequence | array | ServiceLevelSpecConsequence | No |
| threshold | array | ServiceLevelObjectiveThreshold | No |
| applicability | array | ApplicableTimePeriod | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | N/A | No |
| \@type | string | N/A | No |

Table: Fields of ServiceLevelObjective_Create. {#tbl:ServiceLevelObjective_Create.md:ServiceLevelObjective_Create}
