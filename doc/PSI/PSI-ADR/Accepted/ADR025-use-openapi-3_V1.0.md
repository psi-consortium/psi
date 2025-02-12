# Use OpenAPI 3

* ID: ADR025
* Status: :accepted:
* Deciders: @cgr @hop @wr
* Date: 2023-06-14
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

We currently use TMF interface definitions written in OpenAPI 2.
The newly created Inquiry API uses OpenAPI 3 and the Document API is converted to use new features.
It may be sensible to convert the others, too.

## Decision Drivers

* No breaking changes to the API
* Required features
* Migration workload

## Considered Options

* Stay at OpenAPI 2
* Upgrade to OpenAPI 3

## Decision Outcome

Chosen option: "Upgrade to OpenAPI 3", because we need some of the new features (i.e. improved support for binary data and discriminators).
It is foreseeable that other APIs may need those as well.
Having all files written in the same format improves the overall readability.
Since we already have the means to convert the definition files and all tools accept both formats, the additional workload to update the patch files is acceptable.

## Compliance

All interface definition files are upgraded as part of the merge request containing this decision.
New APIs will be automatically upgraded by the transformation script.
