<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# TroubleTicket_Create

A trouble ticket is a record of an issue that is created, tracked, and managed by a trouble ticket management system
Skipped properties: id,href

| Field | Type | Format | Required |
|-------|---|--------|---|
| name | string | N/A | No |
| description | string | N/A | Yes |
| creationDate | string | date-time | No |
| expectedResolutionDate | string | date-time | No |
| externalId | string | N/A | No |
| lastUpdate | string | date-time | No |
| priority | string | N/A | No |
| requestedResolutionDate | string | date-time | No |
| resolutionDate | string | date-time | No |
| severity | string | N/A | Yes |
| statusChangeDate | string | date-time | No |
| statusChangeReason | string | N/A | No |
| ticketType | string | N/A | Yes |
| attachment | array | AttachmentOrDocumentRef | No |
| channel | ChannelRef | N/A | No |
| note | array | Note | No |
| relatedEntity | array | RelatedEntity | No |
| relatedParty | array | RelatedParty | No |
| status | TroubleTicketStatusType | N/A | No |
| statusChange | array | StatusChange | No |
| troubleTicketRelationship | array | TroubleTicketRelationship | No |
| \@baseType | string | N/A | No |
| \@schemaLocation | string | uri | No |
| \@type | string | N/A | No |

Table: Fields of TroubleTicket_Create. {#tbl:TroubleTicket_Create.md:TroubleTicket_Create}
