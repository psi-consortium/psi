<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ResourceAdministrativeStateType

ResourceAdministrativeStateType enumerations; values defined by ITU X.731: 'locked': The resource is administratively prohibited from performing services for its users; 'shutdown': Use of the resource is administratively permitted to existing instances of use only.
While the system remains in the shutting down state the manager may at any time cause the managed object to revert to the unlocked state; 'unlocked': The resource is administratively permitted to perform services for its users.
This is independent of its inherent operability.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| ResourceAdministrativeStateType | string | oneOf[locked, unlocked, shutdown] | No |

Table: Fields of ResourceAdministrativeStateType. {#tbl:ResourceAdministrativeStateType.md:ResourceAdministrativeStateType}

{#page:break}
