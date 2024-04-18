<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceLevelSpecification

A Service Level Specification represents a pre-defined or negotiated set of Service Level 
Objectives.
In addition, certain consequences are associated with not meeting the Service Level 
Objectives.
Service Level Agreements are expressed in terms of Service Level Specifications.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | No |
| relatedServiceLevelObjective | array | ServiceLevelObjectiveRef | No |
| validFor | TimePeriod | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of ServiceLevelSpecification. {#tbl:ServiceLevelSpecification.md:ServiceLevelSpecification}
