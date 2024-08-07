<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductOffering

Represents entities that are orderable from the provider of the catalog, this resource includes pricing information.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| isBundle | boolean | N/A | No |
| isSellable | boolean | N/A | No |
| lastUpdate | string | date-time | No |
| lifecycleStatus | string | N/A | No |
| statusReason | string | N/A | No |
| version | string | N/A | No |
| agreement | array | AgreementRef | No |
| attachment | array | AttachmentOrDocumentRef | No |
| bundledProductOffering | array | BundledProductOffering | No |
| category | array | CategoryRef | No |
| channel | array | ChannelRef | No |
| marketSegment | array | MarketSegmentRef | No |
| place | array | PlaceRef | No |
| prodSpecCharValueUse | array | ProductSpecificationCharacteristicValueUse | No |
| productOfferingPrice | array | ProductOfferingPriceRefOrValue | No |
| productOfferingRelationship | array | ProductOfferingRelationship | No |
| productOfferingTerm | array | ProductOfferingTerm | No |
| productSpecification | ProductSpecificationRef | N/A | No |
| resourceCandidate | ResourceCandidateRef | N/A | No |
| serviceCandidate | ServiceCandidateRef | N/A | No |
| serviceLevelAgreement | SLARef | N/A | No |
| validFor | TimePeriod | N/A | No |
| accessProbability | integer | N/A | No |
| relatedParty | array | RelatedParty | No |
| bundledGroupProductOffering | array | BundledGroupProductOffering | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of ProductOffering. {#tbl:ProductOffering.md:ProductOffering}
