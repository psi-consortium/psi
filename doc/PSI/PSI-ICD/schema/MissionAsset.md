<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# MissionAsset

Planning of a required asset in a mission.
May contain any combination of user-defined characteristics for an inquiry, an ordered product or a service/resource that is already in the customer inventory.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | No |
| targetProductSchema | TargetProductSchema | N/A | No |
| servicePeriod | RelativeTimePeriod | N/A | No |
| place | array | RelatedPlaceRefOrValue | No |
| inquiredProductCharacteristic | array | InquiredProductCharacteristic | No |
| realizingProduct | array | ProductRef | No |
| realizingService | array | ServiceRef | No |
| realizingResource | array | ResourceRef | No |
| assetRelationship | array | MissionAssetRelationship | No |

Table: Fields of MissionAsset. {#tbl:MissionAsset.md:MissionAsset}

{#page:break}
