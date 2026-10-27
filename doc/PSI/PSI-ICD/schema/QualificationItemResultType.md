<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# QualificationItemResultType

PSI closed vocabulary for the result of a qualification item, fixed for interoperability between PSSs and providers.
green = the provider can deliver the requested product offering or product specification as requested (configuration, place and date); no alternatives are proposed.
yellow = the provider cannot fully confirm that it can deliver the item as requested; alternatives may be proposed.
red = the provider cannot deliver the item as requested; alternatives may be proposed.
Alternatives are carried in alternateProductOfferingProposal.
For an item that contains other items (a bundle) the result is derived from the contained items: red as soon as any completed contained item is red; once every contained item is completed, yellow if any of them is yellow, otherwise green; absent until then.
Replaces the free-form string of TMF679 v5; the values follow the example TMF679 v5 gives for the overall result and the serviceabilityConfidence of MEF LSO Sonata.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| QualificationItemResultType | string | oneOf[green, yellow, red] | No |

Table: Fields of QualificationItemResultType. {#tbl:QualificationItemResultType.md:QualificationItemResultType}

{#page:break}
