# ODA Implementation - Language and Framework for the Backend

* ID: ADR039
* Status: :accepted:
* Deciders: @cgr @hop @ncz
* Date: 2024-11-26
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

The implementation of a TM Forum ODA component is planned.
The programming language with the appropriate frameworks must be selected for the backend.

## Decision Drivers

* Low effort for initial implementation and ongoing maintenance
* High-level programming language with excellent IDE support - ideally Visual Studio Code
* Team skills in the programming languages
* Small footprint of the ODA component for a quick download, installation and effortless startup
* Easy containerization with small footprint
* REST API support
* JWT support for authorization
* MongoDB for the persistence layer
* Community support

## Considered Options

* [Java](https://openjdk.java.net/)
* [Node.js](https://nodejs.org/en)
* [Python](https://www.python.org/)
* [Rust](https://www.rust-lang.org/)
* [Go](https://go.dev/)

## Decision Outcome

Chosen option: "Go".
All options would do a good job in terms of REST API, JWT and data persistence.
But solutions with Python, Node.js and Java would result in ODA containers that are too large, hindering fast downloading and testing for interested parties.
That leaves Rust and Go.
The argument of the simpler language remains in favour of Go.

## Compliance

All necessary settings for PSI ODA creation are part of the repository.
The selected programming language is reflected in the settings and preset plugins for Visual Studio Code

## Pros and Cons of the Options

### Java

Java was started by Sun Microsystems in 1995 as a multiplatform, object-oriented programming language.
Despite its age, it is still one of the most popular and powerful programming languages in the world.

Pros:

* Java is well known in the team
* Excellent integration with the Spring Framework for quick REST API and JWT support
* Proper MongoDB support, as shown in the psi-mockup
* Large ecosystem with strong enterprise support.
* Big community.

Cons:

* Not as fast as compiled languages
* Results in big artefacts that include the compiled Java Byte Code and the Java Virtual Machine (JVM) as the runtime environment
* Big memory footprint
* Compilation and restart are quite expensive

### Node.js

Node.js is a server-side runtime environment for JavaScript, known for its non-blocking, event-driven architecture, making it ideal for scalable network applications.
It's built on Chrome's V8 engine and uses npm for managing libraries and modules.

Pros:

* Lightweight and fast REST APIs with Express.js
* Big community
* Huge number of freely available libraries
* Multiple libraries for JWT support (jsonwebtoken, express-jwt and more)
* Proper MongoDB support
* Existing TM Forum example implementations are done in Node.js.
* No lengthy compilation cycles due to the interpreter architecture

Cons:

* Low amount of standard libraries, which increases the risk of faulty external (npm) libraries
* Due to the big amount of small npm libraries using each other, there is a risk of the "npm dependency hell" and version conflicts.
* Big containers, because containerization includes the runtime environment and all dependencies (potentially libraries in different version)
* Requires extensive licence examinations
* Asynchronous programming relies heavily on callbacks, leading to the infamous "callback hell".
  Although concepts such as Promises and async/await mitigate this problem, it can still lead to complex and hard to read code structures, making maintenance and debugging a challenge.

### Python

Python is a popular interpreter language for web development due to its simplicity and readability.
Its extensive libraries and tools support various web technologies, making it a versatile choice for both backend and full-stack development.

Pros:

* Simple REST APIs implementation (e.g. Django or Flask) and JWT support (e.g. Simple JWT)
* MongoDB support is ensured with the native driver PyMongo.
* Big community
* The interpreter design avoids long compilation cycles during the development phases.

Cons:

* Performance is poor (unless using performance boosting techniques)
* High memory footprint
* Big containers, because containerization includes the runtime environment and all dependencies
* Requires extensive licence examinations
* Limited knowledge within the team.

### Rust

Rust was launched in 2006 as a private project and is now being further developed as open source by the Rust Foundation.
It emphasizes performance and type safety, without a runtime or a garbage collector.
In December 2022, it became the first language other than C and assembly to be supported in the development of the Linux kernel.

Pros:

* Very efficient and modern open source programming language
* Fast REST APIs frameworks (Rocket, etc.) and JWT support (alcoholic_jwt)
* MongoDB supports an official driver available in version 3.1
* Low memory and executable/artefact footprint
* Statically linked and very performant executables
* Minimal container with statically linked executable
* Manual memory management (with most work done by the compiler) - faster than garbage collections
* Fast compiler reduces delays during the development phases
* Growing ecosystem / community

Cons:

* Limited knowledge within the team
* Steep learning curve for beginners

### Go

Go (also called Golang or Go language) was developed by Google engineers to create dependable and efficient software.
Most similarly modelled after C, Go is statically typed and explicit.
It was designed by taking inspiration from the productivity and relative simplicity of Python, with the ability of C.

Pros:

* Designed as a simple language with a focus on readability and simplicity.
* Supports high-performance REST APIs, e.g. the Gin Framework and JWT support (e.g. golang-jwt)
* MongoDB supports an official driver (mongo-go-driver)
* Low memory and executable/artefact footprint.
* Fast compilation
* Statically linked and very performant executables
* Minimal container with statically linked executable
* Garbage collection is optimized for low-latency (but not as fast as Rust's manual memory management)
* Strong and growing ecosystem (for backend systems)

Cons:

* Limited knowledge within the team.

In comparison with Rust, the executables are slightly slower and slightly bigger, whereas the compilation is slightly faster.
