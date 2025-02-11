<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PerformanceJobValue

Direct assignment of values defined by PerformanceJob type to  PerformanceReport object.
Necessary when PerformanceReport is not created by PerformanceJob and without relation to PerformanceJob.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| consumingApplicationId | string | N/A | No |
| fileTransferData | FileTransferData | N/A | No |
| granularity | Interval | N/A | No |
| outputFormat | OutputFormat | N/A | Yes |
| producingApplicationId | string | N/A | No |
| resultFormat | ResultFormat | N/A | Yes |
| servicePayloadSpecificAttributes | ServicePayloadSpecificAttributes | N/A | Yes |
| @type | string | "PerformanceJobValue" | Yes |

Table: Fields of PerformanceJobValue. {#tbl:PerformanceJobValue.md:PerformanceJobValue}

{#page:break}
