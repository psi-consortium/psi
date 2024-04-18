=begin

# :book: wiki-Internal section, not compiled to documents

[[_TOC_]]

# How To Read This Document

=end

This document is structured on three hierarchical levels:

* Categories,
* Tasks, and
* Operations.

Categories follow the different business processes a PSS can implement to group the tasks by their general topic.
For example, the category [TOD-02-Product-Publishing](#TOD-02-Product-Publishing) collects all tasks that are related to registering and publishing resources, services, and products.
The first category [TOD-01-Miscellaneous](#TOD-01-Miscellaneous) is an exception.
Here, some general topics are collectively discussed, e.g., the Event API that is used both for inquiries and ordering.

A task is more specific and handles only a couple of activities that are tightly connected.
For example, the task [TOD-02-03-Product-Catalog-Management](#TOD-02-03-Product-Catalog-Management) collects all the operations that are necessary to manage the product catalog, e.g., creating, updating, viewing or deleting product specifications from it.

Operations are the low-level activities required to perform a certain task.
An operation is connected to a REST endpoint of the API and therefore involves two entities:

1. The server (e.g. a PSS) that *implements* the endpoint of the operation.
2. The client (e.g. a provider) that *uses* the endpoint.

For example, the operation [TOD-02-03-02-Update_Product_Specification](#TOD-02-03-02-Update_Product_Specification) outlines how the PATCH endpoint `/psi-api/productCatalog/v1/productSpecification/{id}` described in the [PSI-ICD] is to be used to update a product specification in a PSS's product catalog.
Additionally, each operation references the corresponding PSI requirement(s).
The description of the listed PSI requirements can be found in the [PSI-REQ] document by searching for the requirement ID.
They define the functionality that should be covered by the implementation of the operation endpoint(s).
Thus, it is easy to make a connection between the PSI requirements and the endpoints.

The naming of an operation follows this hierarchy:
The first two digits enumerate the category, the second two the task, and finally the third two digits the operation.

Tasks and operations each follow a standardized structure.

## Template for a Task

@include [TOD-XX-XX-Task_Template](tasks/TOD-XX-XX-Task_Template.md)

## Template for an Operation

@include [TOD-XX-XX-Operation_Template](operations/TOD-XX-XX-XX-Operation_Template.md)
