<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ProductPrice

An amount, usually of money, that represents the actual price paid by a Customer for a purchase, a rent or a lease of a Product.
The price is valid for a defined period of time.

| Field | Type | Format | Required |
|-------|---|--------|---|
| name | string | N/A | No |
| description | string | N/A | No |
| priceType | string | N/A | Yes |
| recurringChargePeriod | string | N/A | No |
| unitOfMeasure | string | N/A | No |
| billingAccount | BillingAccountRef | N/A | No |
| price | Price | N/A | Yes |
| productOfferingPrice | ProductOfferingPriceRef | N/A | No |
| productPriceAlteration | array | PriceAlteration | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of ProductPrice. {#tbl:ProductPrice.md:ProductPrice}
