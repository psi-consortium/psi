<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# ResourceSpecificationRef

Resources are physical or non-physical components (or some combination of these) within an enterprise's infrastructure or inventory.
They are typically consumed or used by services (for example a physical port assigned to a service) or contribute to the realization of a Product (for example, a SIM card).
They can be drawn from the Application, Computing and Network domains, and include, for example, Network Elements, software, IT systems, content and information, and technology components.
A ResourceSpecification is an abstract base class for representing a generic means for implementing a particular type of Resource.
In essence, a ResourceSpecification defines the common attributes and relationships of a set of related Resources, while Resource defines a specific instance that is based on a particular ResourceSpecification.

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| id | string | N/A | Yes |
| href | string | uri | No |
| name | string | N/A | No |
| version | string | N/A | No |
| @baseType | string | N/A | No |
| @schemaLocation | string | uri | No |
| @type | string | N/A | No |
| @referredType | string | N/A | No |

Table: Fields of ResourceSpecificationRef. {#tbl:ResourceSpecificationRef.md:ResourceSpecificationRef}

{#page:break}
