
# Use Gradle Build Tool

* ID: ADR002
* Status: :accepted:
* Deciders: @cgr @daf
* Date: 2022-04-22
* Version 1.0
* Category: Architecture

Technical Story: [PSI-1](https://spcbo-repos-ext.vcs.de/gitlab/psi/psi/-/issues/1)

## Context and Problem Statement

The PSI prototype implementation will be using Java with Spring.
We need a build tool to compile the source files to an executable.

## Decision Drivers

* Dependency management is required
* Compatible with Java and Spring
* Compile time should be low

## Considered Options

* Maven
* Gradle

## Decision Outcome

Chosen option: "Gradle", because it has quicker recompile time than Maven.
The easier extendability of Gradle could have a positive impact in the future, though it is not required now.
Both compared tools are equal in other decision drivers.

## Compliance

The prototype will be set up with Gradle as soon as the first component breakdown is done.
