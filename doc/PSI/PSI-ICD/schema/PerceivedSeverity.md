<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PerceivedSeverity

Lists the possible severities that can be allocated to an Alarm.
The values are consistent with ITU-T Recommendation X.733.
Once an alarm has been cleared, its perceived severity is set to 'cleared' and can no longer be set.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| PerceivedSeverity | string | oneOf[critical, major, minor, warning, indeterminate, cleared] | No |

Table: Fields of PerceivedSeverity. {#tbl:PerceivedSeverity.md:PerceivedSeverity}

{#page:break}
