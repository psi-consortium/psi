
# Use Java as the Backend Programming Language for the API

* ID: ADR001
* Status: :accepted:
* Deciders: @cgr @daf @hop
* Date: 2022-04-22
* Version 1.0
* Category: Architecture

## Context and Problem Statement

To implement the standardized PSI Mock-up, we need a programming language for the backend development.

## Decision Drivers

* Team knowledge in a common programming language
* Simplicity of integrating a message broker/queue like Kafka or RabbitMQ
* Support for REST API
* Manipulation of data models
* Community support

## Considered Options

* [Java](https://openjdk.java.net/)
* [Python](https://www.python.org/)
* [Elixir](https://elixir-lang.org/)
* [Go](https://go.dev/)
* [Ruby](https://www.ruby-lang.org/en/)

## Decision Outcome

Chosen option: "Java", because compared to the other languages considered, wins in the majority of decision drivers.
The most important of all is that is well known to the team and has excellent integration with both REST API and
event-driven messaging, which is required for the development of the PSI Mock-up.

## Compliance

Java will be installed in the developers environment and integrated in the setup of the PSI Mock-up.

## Pros and Cons of the Options

### Java

Pros:

* The team knows Java, meaning learning time is low.
* Excellent integration with the Spring Framework for quick REST API and message queue implementation (support for Kafka and RabbitMQ).
* Structured data model for a stable API.
* Big community.

Cons:

* Not as powerful for anonymous data models as Python for example.

### Python

Pros:

* Simple REST APIs implementation with Django/Flask.
* Supports integration of Kafka and RabbitMQ for event-driven message handling.
* Works well with dynamic, extendable data models.
* Big community.

Cons:

* Limited knowledge within the team.

### Elixir

Elixir is a functional programming language and is an advancement of Erlang.

Pros:

* Support for REST APIs with the Phoenix Framework.
* Supports integration of Kafka and RabbitMQ for event-driven message handling.
* Works well with dynamic, extendable data models.

Cons:

* No knowledge within the team.
* Young programming language, and the ecosystem is far from perfect and complete.
* Small community.

### Go

Go (also called Golang or Go language) was developed by Google engineers to create dependable and efficient software.
Most similarly modelled after C, Go is statically typed and explicit.
It was designed by taking inspiration from the productivity and relative simplicity of Python, with the ability of C.

Pros:

* Supports high-performance REST APIs with Gin Framework.
* Supports integration of Kafka and RabbitMQ for event-driven message handling.
* Growing community.
* It is a simple language.

Cons:

* No knowledge within the team.
* Not the best language to work with dynamic, extendable data models.
* A young language, so it is still developing.

### Ruby

Pros:

* Simple REST APIs implementation with Ruby on Rails Framework.
* Supports integration of Kafka and RabbitMQ for event-driven message handling.
* Works well with dynamic, extendable data models.

Cons:

* Limited knowledge within the team.
* Smaller community compared to Java and Python.
