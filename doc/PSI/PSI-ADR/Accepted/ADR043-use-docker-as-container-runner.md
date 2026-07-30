# Use Docker as Container Runner

- ID: ADR043
- Status: :accepted:
- Deciders: @wr @hop @sst
- Date: 2026-07-22
- Version 1.0
- Category: Architecture

## Context and Problem Statement

The PSI project uses self-hosted GitHub Action Runners to execute Continuous Integration (CI) workflows that require certain internal tooling (RHOD).

To improve security and isolation, execution inside a container will be enforced (`ACTIONS_RUNNER_REQUIRE_JOB_CONTAINER=true`).

Docker and Podman were considered as possible OCI-compliant container runtimes. While both can execute containers, GitHub Actions and the GitHub Actions Runner have been designed primarily around Docker.

Many GitHub Actions invoke the Docker CLI directly or rely on Docker-specific behavior. Although Podman provides a Docker-compatible interface, compatibility is not guaranteed for all actions and requires additional validation and maintenance.

## Decision Drivers

- Security
- Complexity of integrating with the CGI internal container registry
- GitHub action runner support

## Considered Options

- Docker
- Podman

## Decision Outcome

Podman is, in most cases, a drop-in replacement for Docker. However, some small details differ during execution. The GitHub action runner depends on some of these differences.
Use Docker as the container runtime for our self-hosted GitHub action runner.

Revisit this once Podman is supported by GitHub Action runners, see [GitHub actions runner issue](https://github.com/actions/runner/issues/505).

## Compliance

Docker will be installed on the internal CGI GitHub action runner.
