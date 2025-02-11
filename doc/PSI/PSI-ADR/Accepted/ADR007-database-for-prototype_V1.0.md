# Database for Prototype

* ID: ADR007
* Status: :accepted:
* Deciders: @cgr @daf
* Date: 2022-07-04
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

The prototype should be able to do simple data storage to provide better simulation of a real PSS.
For example, this will allow to actually create a resource specification and retrieve exactly this specification (instead of a dummy).
To implement this we want to use a simple database backend with as less overhead as possible.

## Decision Drivers

* Easy or no installation
* Easy to use (e.g. spring integration)
* Support for simple SELECT queries
* Production drivers like security, scalability and robustness were **not** applied

## Considered Options

* Postgres
* MariaDB
* H2
* MongoDB
* CouchDB

## Decision Outcome

Chosen option: "MongoDB", because

* It is document-oriented, so no separate ERM is needed.
* Installation can be done via docker as well as embed (for unit testing).
* Official `spring-data` integration exists, which allows easy construction of queries using `MongoTemplate`.

The simple structure allowed the team to adapt rather quickly, despite having nearly no previous knowledge.
First CRUD instructions were already done while researching and worked immediately.

## Compliance

The MongoDB connector for spring is included in the prototype and used for the first endpoints.
Further usage is ensured by code review.

## Pros and Cons of the Options

| Capability             | Postgres   | MariaDB    | H2         | MongoDB         | CouchDB         |
|------------------------|------------|------------|------------|-----------------|-----------------|
| **Data structure**     | Relational | Relational | Relational | Object-Oriented | Object-Oriented |
| **Docker**             | Yes        | Yes        | No         | Yes             | Yes             |
| **Embedded**           | No         | No         | Yes        | Yes             | No              |
| **Spring Integration** | Yes        | Yes        | Yes        | Yes             | 3rd-Party       |
| **Community Support**  | High       | High       | High       | High            | Mediocre        |
| **Team Knowledge**     | Yes        | Yes        | Yes        | No              | No              |

Table: Decision-matrix for the prototype database {#tbl:prototype_database}
