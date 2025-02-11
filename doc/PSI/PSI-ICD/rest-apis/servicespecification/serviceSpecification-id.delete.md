<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# DELETE /serviceSpecification/{id}

**Absolute Path:** /serviceCatalog/v1/serviceSpecification/{id}

**TOD Reference:** TOD-02-02-03-Remove_Service_Specification

**Summary:** Deletes a ServiceSpecification

## Parameters

| Name | Type | Required | Description |
| ------ | ------ | --- | ------------ |
| id | string | Yes | Identifier of the ServiceSpecification |

Table: Parameters of DELETE serviceSpecification/{id}. {#tbl:serviceSpecification-id.delete.md:parameters}

## Responses

| Code | Description | Content |
|------|-------------|---------|
| 204 | Deleted | N/A |
| 400 | Bad Request | Error |
| 401 | Unauthorized | Error |
| 403 | Forbidden | Error |
| 404 | Not Found | Error |
| 405 | Method Not allowed | Error |
| 409 | Conflict | Error |
| 500 | Internal Server Error | Error |

Table: Responses of DELETE serviceSpecification/{id}. {#tbl:serviceSpecification-id.delete.md:responses}

{#page:break}
