<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Alarm

This resource represents an alarm supporting the information model defined in ITU-T X.733.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| ackState | string | N/A | No |
| ackSystemId | string | N/A | No |
| ackUserId | string | N/A | No |
| alarmChangedTime | string | date-time | No |
| alarmClearedTime | string | date-time | No |
| alarmDetails | string | N/A | No |
| alarmEscalation | boolean | N/A | No |
| alarmRaisedTime | string | date-time | No |
| alarmReportingTime | string | date-time | No |
| alarmedObjectType | string | N/A | No |
| clearSystemId | string | N/A | No |
| clearUserId | string | N/A | No |
| externalAlarmId | string | N/A | No |
| isRootCause | boolean | N/A | No |
| plannedOutageIndicator | string | N/A | No |
| probableCause | string | N/A | No |
| proposedRepairedActions | string | N/A | No |
| reportingSystemId | string | N/A | No |
| serviceAffecting | boolean | N/A | No |
| sourceSystemId | string | N/A | No |
| specificProblem | string | N/A | No |
| state | string | oneOf[raised, updated, cleared] | No |
| affectedService | array | AffectedService | No |
| alarmType | AlarmType | N/A | No |
| alarmedObject | AlarmedObject | N/A | No |
| comment | array | Comment | No |
| correlatedAlarm | array | AlarmRef | No |
| crossedThresholdInformation | CrossedThresholdInformation | N/A | No |
| parentAlarm | array | AlarmRef | No |
| perceivedSeverity | PerceivedSeverity | N/A | No |
| place | array | RelatedPlaceRefOrValue | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | N/A | No |

Table: Fields of Alarm. {#tbl:Alarm.md:Alarm}

{#page:break}
