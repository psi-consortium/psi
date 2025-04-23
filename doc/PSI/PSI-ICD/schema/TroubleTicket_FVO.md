<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# TroubleTicket_FVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | No |
| name | string | N/A | No |
| description | string | N/A | Yes |
| severity | string | N/A | Yes |
| ticketType | string | N/A | Yes |
| attachment | array | AttachmentOrDocumentRef | No |
| channel | ChannelRef_FVO | N/A | No |
| requestedResolutionDate | string | date-time | No |
| expectedResolutionDate | string | date-time | No |
| resolutionDate | string | date-time | No |
| externalIdentifier | array | ExternalIdentifier_FVO | No |
| note | array | Note_FVO | No |
| priority | string | N/A | No |
| relatedEntity | array | RelatedEntity_FVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_FVO | No |
| status | TroubleTicketStatusType | N/A | No |
| statusChangeDate | string | date-time | No |
| statusChangeReason | string | N/A | No |
| troubleTicketRelationship | array | TroubleTicketRelationship_FVO | No |
| troubleTicketSpecification | TroubleTicketSpecificationRef_FVO | N/A | No |
| troubleTicketCharacteristic | array | Characteristic_FVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "TroubleTicket" | Yes |

Table: Fields of TroubleTicket_FVO. {#tbl:TroubleTicket_FVO.md:TroubleTicket_FVO}

{#page:break}
