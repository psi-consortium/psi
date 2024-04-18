<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Product

A product offering procured by a customer or other interested party playing a party role.
A product is realized as one or more service(s) and / or resource(s).

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| href | string | uri | No |
| name | string | N/A | No |
| description | string | N/A | No |
| isBundle | boolean | N/A | No |
| isCustomerVisible | boolean | N/A | No |
| orderDate | string | date-time | No |
| productSerialNumber | string | N/A | No |
| startDate | string | date-time | No |
| terminationDate | string | date-time | No |
| agreement | array | AgreementItemRef | No |
| billingAccount | BillingAccountRef | N/A | No |
| place | array | RelatedPlaceRefOrValue | No |
| product | array | ProductRefOrValue | No |
| productCharacteristic | array | Characteristic | No |
| productOffering | ProductOfferingRef | N/A | No |
| productOrderItem | array | RelatedProductOrderItem | No |
| productPrice | array | ProductPrice | No |
| productRelationship | array | ProductRelationship | No |
| productSpecification | ProductSpecificationRef | N/A | No |
| productTerm | array | ProductTerm | No |
| realizingResource | array | ResourceRef | No |
| realizingService | array | ServiceRef | No |
| relatedParty | array | RelatedParty | No |
| status | ProductStatusType | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of Product. {#tbl:Product.md:Product}
