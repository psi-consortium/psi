<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Alarm

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| href | string | uri | No |
| externalAlarmId | string | N/A | No |
| state | string | oneOf[raised, updated, cleared] | No |
| alarmType | AlarmType | N/A | No |
| perceivedSeverity | PerceivedSeverity | N/A | No |
| probableCause | string | N/A | No |
| specificProblem | string | N/A | No |
| alarmedObjectType | string | N/A | No |
| alarmedObject | AlarmedObjectRef | N/A | No |
| reportingSystemId | string | N/A | No |
| sourceSystemId | string | N/A | No |
| alarmDetails | string | N/A | No |
| alarmRaisedTime | string | date-time | No |
| alarmChangedTime | string | date-time | No |
| alarmClearedTime | string | date-time | No |
| alarmReportingTime | string | date-time | No |
| ackState | string | oneOf[unacknowledged, acknowledged] | No |
| ackSystemId | string | N/A | No |
| ackUserId | string | N/A | No |
| affectedService | array | ServiceRef | No |
| alarmEscalation | boolean | N/A | No |
| clearSystemId | string | N/A | No |
| clearUserId | string | N/A | No |
| comment | array | Comment | No |
| correlatedAlarm | array | AlarmRef | No |
| crossedThresholdInformation | CrossedThresholdInformation | N/A | No |
| isRootCause | boolean | N/A | No |
| parentAlarm | array | AlarmRef | No |
| plannedOutageIndicator | string | oneOf[InService, OutOfService] | No |
| proposedRepairedActions | string | N/A | No |
| serviceAffecting | boolean | N/A | No |
| place | array | RelatedPlaceRef | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "Alarm" | Yes |

Table: Fields of Alarm. {#tbl:Alarm.md:Alarm}

{#page:break}
