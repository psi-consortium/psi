=begin

# Release Notes

[[_TOC_]]

=end

# PSI Release Notes

## Introduction

Welcome to the third release of the Pooling and Sharing Interface API!
Below, you'll find details about the features, enhancements, and other important aspects of this release.

## Key Highlights

The focus of this release lies on **mission management**, to facilitate a common understanding of user requirement towards communication.
It aims to complement the Inquiry API by providing workflows and *understanding* to service requirements.
This is mainly a user-oriented API, but it also enables exchange of mission data between PSS systems and therefore cross-platform-market places.
This could become a future focal point.
Such data exchange would include actual user requirements (expressed as missions), as well as templates for such missions.
By the use of templates, user mission creation is streamlined and allows a governance to safeguard, streamline or ease the process of user requirement gathering.

Together with the APIs, we are working on a Plug&Play component for P&S systems (Hubs, Brokers, Marketplaces…), based on ODA.
This will be a standalone Micro-Frontend open to be integrated into existing OSS/BSS/PSS systems.
A first draft is included in this release.

It will come with different views:

* Time based (e.g. mission timeline, Gantt-Chart, to express that is needed *when*)
* Geography based (e.g. mission zones or network nodes on a map to express what is needed *where*)
* Logical View (e.g. communication interdependency graph to express *how* the requirements will look like)

Another area of improvement is the **performance management API**.

A new API has been added that allows to request performance reports to an ongoing mission from the provider.
That is: the report itself is generated on provider's systems.
The API handles the request and exchange of the report.
The report has to be in line with the product's SLA and allows monitoring of compliance.
It allows also to define alarm thresholds and receive a push of threshold violations by the provider, avoiding a constant pull.

We also added the technical considerations and resulting decisions to the document set.
This allows easier future evolution and maintenance of the standard.

## What's New

* [PSI-GID] now contains descriptions about the ODA component for mission management
* [PSI-ICD] now contains new and updated APIs - see below!
* [PSI-ADR] first release of our decision records
* [PSI-TAD] now contains descriptions of concepts for user missions, as well as performance and alarm management
* [PSI-TOD] now contains new tasks and operations for user missions, performance and alarm management

### Newly added APIs

* PSID002 Mission Management
  * This customer-facing API allows them to manage missions and assign products, services and resources to them.
  * It can also serve as an entry point for the Customer Inquiry API to find matching products for their requirements.
* PSID143 Performance Monitoring
  * Based on MEF143 - Performance Monitoring API (Version 2.0.0-RC).
  * The performance monitoring allows a PSS or customer to request performance reports from a provider.
* PSID642 Alarm
  * Based on TMF642 - Alarm API (Version 4.1.0).
  * Allows the provider to notify a PSS or customer about detected problems with their products.

### Updates APIs

* PSID001 Customer Inquiry
  * Improved handling of places by adapting TMF Geography types.
* PSID620 Product Catalog
  * Based on TMF620 - Product Catalog Management API (Version 4.1.0).
  * Changed `SLARef` to `ServiceLevelSpecificationRef`
  * Streamlined GeoJSON types
* PSID633 Service Catalog
  * Based on TMF633 - Service Catalog Management API (Version 4.1.0).
  * Changed `SLARef` to `ServiceLevelSpecificationRef`
  * Streamlined GeoJSON types
* PSID634 Resource Catalog
  * Based on TMF634 - Resource Catalog Management API (Version 4.1.0).
  * Changed `SLARef` to `ServiceLevelSpecificationRef`
  * Streamlined GeoJSON types
* PSID657 Service Quality Management
  * Based on TMF657 - Service Quality Management API (Version 4.1.0).
  * Add endpoints to manage KPIs that are supported by the PSS.

### Added Requirements

* MISSION requirement category
* REQ-06-03 Key Indicator Management
* REQ-06-04 Performance Monitoring Job Management
* REQ-06-05 Performance Monitoring Report Management
* REQ-06-06 Alarm Management

## Known Limitations

1. The Service Quality Management is rather basic.
   There is an ongoing effort to align this set of APIs with the results of a TM Forum Catalyst project.
   More information will follow in one of the next releases.
1. The Mission Management Service is at an early state.
   However, the available API implements basic mission management services, import and export.
   A full set of APIs to implement such a service are subject to an upcoming release.
   Refer also the [PSI-GID] to learn about the available API use cases.

# Outlook

Currently, we are working on the next release with the following focal points:

* Finalize the mission management component
* Update the API baseline to TM Forum 5
* Converge with MEF schema for some selected APIs

# Feedback and Contributions

We value your feedback!
If you encounter any issues or have suggestions, please reach out.
Additionally, we welcome contributions from the community.
