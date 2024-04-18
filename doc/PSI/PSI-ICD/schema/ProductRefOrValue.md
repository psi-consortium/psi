<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductRefOrValue

A product to be created defined by value or existing defined by reference.
The polymorphic attributes @type, @schemaLocation & @referredType are related to the product entity and not the RelatedProductRefOrValue class itself

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
| \@referredType | string | N/A | No |

Table: Fields of ProductRefOrValue. {#tbl:ProductRefOrValue.md:ProductRefOrValue}
