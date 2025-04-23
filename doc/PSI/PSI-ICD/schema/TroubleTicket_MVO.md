<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# TroubleTicket_MVO

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| name | string | N/A | No |
| description | string | N/A | No |
| severity | string | N/A | No |
| ticketType | string | N/A | No |
| attachment | array | AttachmentOrDocumentRef | No |
| channel | ChannelRef_MVO | N/A | No |
| requestedResolutionDate | string | date-time | No |
| expectedResolutionDate | string | date-time | No |
| resolutionDate | string | date-time | No |
| externalIdentifier | array | ExternalIdentifier_MVO | No |
| note | array | Note_MVO | No |
| priority | string | N/A | No |
| relatedEntity | array | RelatedEntity_MVO | No |
| relatedParty | array | RelatedPartyRefOrPartyRoleRef_MVO | No |
| status | TroubleTicketStatusType | N/A | No |
| statusChangeReason | string | N/A | No |
| statusChangeHistory | array | StatusChange_MVO | No |
| troubleTicketRelationship | array | TroubleTicketRelationship_MVO | No |
| troubleTicketSpecification | TroubleTicketSpecificationRef_MVO | N/A | No |
| troubleTicketCharacteristic | array | Characteristic_MVO | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | N/A | No |
| @type | string | "TroubleTicket" | Yes |

Table: Fields of TroubleTicket_MVO. {#tbl:TroubleTicket_MVO.md:TroubleTicket_MVO}

{#page:break}
