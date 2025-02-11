<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# CustomerInquiry

A request for information (resources, services, products and offerings) from a Customer.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| href | string | uri | No |
| state | InquiryStateType | N/A | Yes |
| responseTime | ResponseTime | N/A | Yes |
| customerProfile | array | CustomerCharacteristic | No |
| bundlesOnly | boolean | N/A | No |
| customerResource | array | ResourceSpecification | No |
| place | array | PlaceRefOrValue | No |
| inquiredProduct | array | InquiredProduct | No |
| inquiredProvider | array | InquiredProvider | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |

Table: Fields of CustomerInquiry. {#tbl:CustomerInquiry.md:CustomerInquiry}

{#page:break}
