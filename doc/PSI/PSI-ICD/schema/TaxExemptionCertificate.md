<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# TaxExemptionCertificate

A tax exemption certificate represents a tax exemption granted to a party (individual or organization) by a tax jurisdiction which may be a city, state, country,...
An exemption has a certificate identifier (received from the jurisdiction that levied the tax) and a validity period.
An exemption is per tax types and determines for each type of tax what portion of the tax is exempted (partial by percentage or complete) via the tax definition.

| Field | Type | Format | Required |
|-------|---|--------|---|
| id | string | N/A | No |
| certificateNumber | string | N/A | No |
| issuingJurisdiction | string | N/A | No |
| reason | string | N/A | No |
| attachment | AttachmentOrDocumentRef | N/A | No |
| taxDefinition | array | TaxDefinition | No |
| validFor | TimePeriod | N/A | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of TaxExemptionCertificate. {#tbl:TaxExemptionCertificate.md:TaxExemptionCertificate}
