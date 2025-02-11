# Beam Handling (V1.0)

* ID: ADR017
* Status: :superseded: by [Beam Handling V1.1](../Accepted/2023-02-01-beam-handling_V1.1.md) in [PSI-MADR]
* Deciders: @cgr @daf @cg @wr @rsperb @KK
* Date: 2023-02-01
* Version: 1.0
* Category: Design

## Context and Problem Statement

The PSS needs to know where a service can be delivered, which implies knowledge of the involved beams, especially their respective footprints and EIRP values.
But beams are not always fixed, so we need to find a way to handle this.

## Decision Drivers

* Provide enough information
  * to the PSS and/or provider for matchmaking
  * to the customer for selecting the best option
* Minimize complexity
* Protect confidential data of the provider

## Considered Options

* Time-dependent beam geometries
* Live updates by the provider
* Allow definition of beam and service boundaries

## Decision Outcome

Chosen option: "Allow definition of beam and service boundaries", because this information is just enough for coarse matchmaking and simple compared to other options.

Accordingly, the PSID will *not* include time-dependent beam geometries, which would highly increase the complexity of matchmaking and doesn't solve the problem of steered beams and confidential information.
Also, *no* new workflow to periodically update beams is specified, which would require some unnecessary effort on the provider side and doesn't cover the case of confidential information.
In other words, the beam prediction and allocation will only be handled by the provider and not by the PSS.
PSS governance and provider can agree to update dynamic information through existing Catalog and Inventory APIs if required, for example when the PSS is actively managing committed resources.

This leaves the following two extremes for defining beam resources:

* **Minimum:** The provider defines a single (more or less precise) area where they can potentially offer a beam or service.
  This geometry does not contain a value for EIRP nor any other metadata.
  It is just enough to find the provider in the matchmaking process and offer the user to start a RFQ, where the provider can check the actual availability in the user's mission zone.
* **Maximum:** The provider defines multiple geometries for a beam, one for each isotropic area.
  They also add product offerings for pre-configured services, which are bound to a required EIRP.
  This allows the matchmaking to exclude offerings that are not available when the mission zone is in an area with lower EIRP.

As an intermediate use case the API also allows providing exact beam information without having pre-configured services, which were included in the above sample for better understanding.
Note that the already introduced GeoJSON data format is able to describe "holes" for those areas, which can be used to exclude certain areas from a bigger polygon.

## Compliance

[PSI-ICD] and [PSI-TAD] will be updated to explicitly show this behaviour.

## Implications for the Scope

The interface enables the exchange of footprints in any desired precision agreed between PSS governance and provider.
This information can be used by the governance to derive the total set of regions where services are offered and display it to the user.
However, this is not part of the API, but a PSS-internal process.

The coverage of beams is one way to allocate a geolocation to a resource, service, or product.
An easy way to make a first approach for mapping, is to compare a service request's localization with the available satellite footprints.
However, this is not part of the API, but a PSS-internal process.

Pooling based on the geographical region requires resources, services, and product to have a location or an area where they can be deployed. The footprint is an easy and reasonable way to do this.
However, this is not part of the API, but a PSS-internal process.

The GeoJSON data format is able to describe "holes" within areas, which can be used to exclude certain areas from a bigger polygon.

During the lifecycle of a satellite, its footprint(s) may or may not change, even continuously.
Pushing updates when required is the easiest way to manage these changes.
