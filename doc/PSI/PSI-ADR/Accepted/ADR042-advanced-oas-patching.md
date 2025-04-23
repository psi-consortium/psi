# Advanced OpenAPI Specification Patching

* ID: ADR042
* Status: :accepted:
* Deciders: @cgr @ncz
* Consulted: @hop
* Date: 2025-02-06
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

While updating the baseline for our APIs to TMF OpenAPIs v5, we encountered some issues with PATTY:

* Some transformations were overly specific and thus not applicable any more
* Some transformations were done just to compensate for others (e.g., removing unused schemas) and made interpreting the file hard
* Some transformations were just used to enforce a common schema on data structures that are shared between APIs, but done manually
* Some transformations were defined in multiple (if not all) transformations separately, making them hard to maintain

These problems multiplied with the complexity introduced in TMF v5 and even more when retrofitting the APIs that did not receive an update by TMF yet.

## Decision Drivers

* Resolve the problems listed above
* No regression on currently used capabilities
* No hardcoding

## Considered Options

* Introduce common transformation file
* Find a different tool matching our requirements
* Write a new transformation tool for OpenAPI specifications

## Decision Outcome

Chosen option: "Write a new transformation tool for OpenAPI specifications", because the common transformation file only solved parts of the problem and no other tool was found to implement a similar workflow.

The new transformation tool was completely written for Gradle.
It supports a set of transformations that are tailored to OpenAPI specifications, where PATTY was for all types of JSON data.
This also means that we can now directly transform OpenAPI specifications in YAML format instead of having to convert them to JSON first.
In addition to the previously available `add`, `remove` and `replace` rules, the awareness of OAS made the following additions possible:

* **Using regular expressions**\
  While patty had the option for "wildcards" to express "apply to all sub-elements", the new tool uses the full power of regular expressions to find the right elements to change.
* **Definition of common rules**\
  The Gradle syntax allows defining common rules without any additional implementation effort.
* **Importing of schemas from another file**\
  This allows us to use another API as the "master" for specific schemas to solve discrepancies between APIs.
  For example, the `ProductSpecification` data type is always imported from the "Product Catalog API" instead of fixing it where it diverges.
* **Importing of paths from another file**\
  With this method, we can create a separate file to define our custom endpoints instead of making them part of the transformation.
* **Following oneOf and allOf schemas**\
  Schemas can be composed of multiple parts, which are marked either with "oneOf" or "allOf".
  Previously, the modification or removal of a property had to target those parts specifically.
  The new transformation logic automatically finds the property in any path.
* **Automatic pruning of unused components**\
  This method automatically scans the "components" section of the specification to find unused elements and removes them.
  With this, it is sufficient to remove an endpoint without manually deleting schemas etc. that become obsolete because of it.

All these transformation rules are written in a generic way in `buildSrc`.
They are then tailored and applied to each API in the root `build.gradle` with one task (instead of one transformation file) per API.
It is possible to move these tasks into their own build-script as well, but not done yet to preserve the general workflow.

The tool has already proven useful in other areas.
It helped to improve the generation of the mock-up code (API and model) and reduced the need for manual intervention.
In the future, it may be further developed and maintained decoupled from PSI itself to allow others to similarly tailor the PSI APIs to their system.

## Compliance

The new tool completely replaces the PATTY solution with immediate effect.
All invocations of `patty` in the project are replaced by calls to the new tool.

## Analysed Candidate Requirements

### Not Considered

The following candidate requirements were analysed but not considered for the PSI:

* None

### Implemented

The following candidate requirements were analysed and implemented (for reference see [PSI-RTM]):

* P&S_208: Patching the existing OAS files allows PSI to grow with the TM Forum Open API. Updates to the Open API can be traced and handled. In a maintenance phase of PSI, this will allow for an easy adjustment to be up-to-date with TM Forum's future developments.
