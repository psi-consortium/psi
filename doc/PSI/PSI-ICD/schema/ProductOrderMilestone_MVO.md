<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOrderMilestone_MVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | No |
| status | string | oneOf[Yet-To-Reach, Completed, Violated] | No |
| milestoneDate | string | date-time | No |
| message | string | N/A | No |
| productOrderItem | array | ProductOrderItemRef_MVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOrderMilestone" | Yes |

Table: Fields of ProductOrderMilestone_MVO. {#tbl:ProductOrderMilestone_MVO.md:ProductOrderMilestone_MVO}

{#page:break}
