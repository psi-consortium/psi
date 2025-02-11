<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# PerformanceJobProcessStateType

The state of the process related to the Performance Job

| state          | MEF 133 name | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| -------------- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accepted`     | Accepted     | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has been validated and accepted by the Seller/Server.
                                                                                                                                                                                                                                                                                                                                                                |
| `acknowledged` | Acknowledged | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has been received by the Seller/Server and has passed basic validation.
Performance Monitoring Job Process Identifier is assigned in the Acknowledged state.
The request remains Acknowledged until all validations as applicable are completed.
If the attributes are validated, the request moves to the Accepted state.
If not all attributes are validated, the request moves to the Declined state.
|
| `completed`    | Completed    | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has been completed by the Seller/Server.
                                                                                                                                                                                                                                                                                                                                                                             |
| `declined`     | Declined     | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has failed validation and has been declined by the Seller/Server.
                                                                                                                                                                                                                                                                                                                                                        |

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| PerformanceJobProcessStateType | string | oneOf[accepted, acknowledged, completed, declined] | No |

Table: Fields of PerformanceJobProcessStateType. {#tbl:PerformanceJobProcessStateType.md:PerformanceJobProcessStateType}

{#page:break}
