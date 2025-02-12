
# Use Spring Framework

* ID: ADR003
* Status: :accepted:
* Deciders: @cgr @daf
* Date: 2022-04-22
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

The PSI prototype application will be programmed in Java.
A platform is required that provides comprehensive infrastructure support for developing Java applications.

## Decision Drivers

* Maturity and stability
* Flexibility
* Number of extensions
* Configuration complexity
* Community support
* Knowledge in the team

## Considered Options

* [Spring](https://spring.io/)
* [Dropwizard](https://www.dropwizard.io/)
* [Microprofile](https://microprofile.io/)

## Decision Outcome

Chosen option: "Spring", because is the oldest and most popular framework for Java.
It utilises runtime Dependency Injection (DI) and Aspect-Oriented Programming (AOP) to enable highly flexible applications.
There are a lot of existing extensions for web, cloud, security and other aspects.
One of the extensions is Spring Boot, which features "convention over configuration" to remove the XML configurations needed before.
Spring has a big community, it is a framework that is well known by the team which limits the learning time.

The other compared tools have fewer extensions, small community support and slow evolving standard.

## Compliance

Add Spring dependencies in Gradle and necessary configuration when setting up the PSI prototype application.

## Pros and Cons of the Options

### Spring

Spring (cf. <https://spring.io/>) is the oldest and most popular of the compared frameworks.
It utilises runtime Dependency Injection (DI) and Aspect Oriented Programming (AOP) to enable highly flexible applications.
There are a lot of existing extensions for web, cloud, security and other aspects.
One of the extensions is Spring Boot, which features "convention over configuration" to remove the XML configurations needed before.
The extensive documentation and the "starters" enable an easy beginning, though low-level customisations can get complex.

#### Upsides

* Mature and stable
* High flexibility
* Lots of extensions
* Easy configuration with Spring Boot
* Big community

#### Downsides

* Long startup time
* Complex inner-workings

### Microprofile

Microprofile is a standard API for Java microservices.
It is implemented by multiple products, where "Quarkus" (cf. <https://quarkus.io/>) by JBoss, Red Hat and IBM is the most popular one.
Similar to Spring it offers Dependency Injection (DI), but does this at compile time for faster startup and lower container sizes.
This also results in the possibility to create native images, which are even faster at startup.
The extension ecosystem and community is smaller, but growing.

#### Upsides

* Stability by using a standard
* High flexibility
* Fast startup time
* Extensions for all common use cases
* Easy configuration

#### Downsides

* The standard evolves slowly
* Fewer extensions (e.g. no Service Discovery)

### Dropwizard

Dropwizard (cf. <https://www.dropwizard.io/>) is a set of fixed libraries, which are curated and tested to be compatible.
This results in a well-defined and compact environment for web development, but has drawbacks when other features are needed.

#### Upsides

* Well-defined environment
* Good startup time

#### Downsides

* Less extensible
* Less community support
