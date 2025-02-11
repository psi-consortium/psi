# UI Prototyping Tool

* ID ADR011
* Status: :rejected:
* Deciders: @cgr @daf @hop @wr
* Date: 2022-08-09
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

The API is defined using an OpenAPI definition and tested using ruby scripts.
This makes it hard to understand it, especially because of the high nesting of the JSON schema.
We want to create a basic user interface to visualize the concepts and let interested parties discover it autonomously.
When possible, it should also allow for import/export of XLSX files.

## Decision Drivers

* Low implementation time
* Complexity for the user
* Easy deployment

## Considered Options

* Appsmith[^appsmith]
* Budibase[^budibase]
* Angular[^angular] Material[^ng-material]

[^appsmith]: <https://www.appsmith.com/>
[^budibase]: <https://budibase.com/>
[^angular]: <https://angular.io/>
[^ng-material]: <https://material.angular.io/>

## Decision Outcome

It was decided not to provide a dedicated prototype UI for the interface, because the maintenance does not justify the little value added by it.
The interface will be showcased in actual software by SES ("REACH") and CGI ("UCSM") at a later stage.
Appsmith and Angular Material may be considered for other objectives in the future.

## Compliance

N/A

## Pros and Cons of the Options

### Appsmith

> Appsmith is the open-source framework that lets your team build custom internal applications like dashboards, admin panels, CRUD apps.

Appsmith is not meant to be deployed with a predefined set of applications.
It is possible to do so by including the data directory and our backend in a custom docker image, which has a total size of about 2 GB.

* Good, because forms can be defined directly based on a JSON structure
* Good, because can be installed on a central server to work collaboratively
* Bad, because hard to deploy "as a whole"
* Bad, because flexibility limited to what the tool offers
* Bad, because breaks on 3rd nesting level (can be configured in JSON)

### Budibase

> Budibase is an all-in-one low-code platform for building, designing, and automating business apps, such as; admin panels, forms, internal tools, client portals, and more.

* Good, because easier than Appsmith
* Bad, because only a feature-subset of Appsmith
* Bad, because no custom code at all

### Angular Material

> Angular is a development platform, built on TypeScript. As a platform, Angular includes:
>
> * A component-based framework for building scalable web applications
> * A collection of well-integrated libraries that cover a wide variety of features, including routing, forms management, client-server communication, and more
> * A suite of developer tools to help you develop, build, test, and update your code

Deploying the application is possible via usual tools, e.g. docker.
We can decide to do this in separate containers or a single one (about 700 MB).

* Good, because very flexible
* Good, because it is known in the dev team and consortium
* Bad, because some code has to be written
