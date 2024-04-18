<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ServiceLevelSpecification_Create

A Service Level Specification represents a pre-defined or negotiated set of Service Level 
Objectives.
In addition, certain consequences are associated with not meeting the Service Level 
Objectives.
Service Level Agreements are expressed in terms of Service Level Specifications.
Skipped properties: id,href

| Field | Type | Format | Required |
|-------|---|--------|---|
| name | string | N/A | Yes |
| description | string | N/A | No |
| relatedServiceLevelObjective | array | ServiceLevelObjectiveRef | Yes |
| validFor | TimePeriod | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of ServiceLevelSpecification_Create. {#tbl:ServiceLevelSpecification_Create.md:ServiceLevelSpecification_Create}
