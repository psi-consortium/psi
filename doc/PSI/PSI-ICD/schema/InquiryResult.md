<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# InquiryResult

A ranked result of an inquiry.
Must contain at least one ProductSpecification, optionally complemented by an offering.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | Yes |
| priority | number | N/A | No |
| note | array | Note | No |
| totalPrice | array | TotalPrice | No |
| inquiredProductRelationship | array | InquiredProductRelationship | No |
| productSpecification | array | ProductSpecification | Yes |
| product | array | Product | No |
| productOffering | array | ProductOffering | No |

Table: Fields of InquiryResult. {#tbl:InquiryResult.md:InquiryResult}
