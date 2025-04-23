<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOffering_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | Yes |
| description | string | N/A | No |
| isBundle | boolean | N/A | No |
| isSellable | boolean | N/A | No |
| statusReason | string | N/A | No |
| validFor | TimePeriod | N/A | No |
| version | string | N/A | No |
| place | array | PlaceRef_FVO | No |
| serviceLevelSpecification | ServiceLevelSpecificationRef | N/A | No |
| channel | array | ChannelRef_FVO | No |
| serviceCandidate | ServiceCandidateRef_FVO | N/A | No |
| category | array | CategoryRef_FVO | No |
| resourceCandidate | ResourceCandidateRef_FVO | N/A | No |
| productOfferingTerm | array | ProductOfferingTerm_FVO | No |
| productOfferingPrice | array | ProductOfferingPrice | No |
| agreement | array | AgreementRef_FVO | No |
| bundledProductOffering | array | BundledProductOffering_FVO | No |
| bundledGroupProductOffering | array | BundledGroupProductOffering | No |
| attachment | array | AttachmentOrDocumentRef | No |
| marketSegment | array | MarketSegmentRef_FVO | No |
| productOfferingRelationship | array | ProductOfferingRelationship_FVO | No |
| productOfferingCharacteristic | array | CharacteristicSpecification_FVO | No |
| prodSpecCharValueUse | array | ProductSpecificationCharacteristicValueUse_FVO | No |
| policy | array | PolicyRef_FVO | No |
| allowedAction | array | AllowedProductAction_FVO | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | Yes |
| productSpecification | ProductSpecificationRef_FVO | N/A | No |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| accessProbability | integer | N/A | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "ProductOffering" | Yes |

Table: Fields of ProductOffering_FVO. {#tbl:ProductOffering_FVO.md:ProductOffering_FVO}

{#page:break}
