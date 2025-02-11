<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# GET /customerInquiry/{id}/results

**Absolute Path:** /customerInquiry/v1/customerInquiry/{id}/results

**TOD Reference:** TOD-03-01-03-View_Inquiry_Results

**Summary:** Retrieves the InquiryResults matching the CustomerInquiry

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the CustomerInquiry |
| fields | string | No | Comma-separated properties to be provided in response |
| offset | integer | No | Requested index for start of resources to be provided in response |
| limit | integer | No | Requested number of resources to be provided in response |

Table: Parameters of GET customerInquiry/{id}/results. {#tbl:customerInquiry-id-results.get.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 200 | Success | InquiryResult |
| 204 | No content can be provided, because the inquiry is not yet processed. | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of GET customerInquiry/{id}/results. {#tbl:customerInquiry-id-results.get.md:responses}

{#page:break}
