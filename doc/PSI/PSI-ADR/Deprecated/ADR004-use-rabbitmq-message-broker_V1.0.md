
# Use RabbitMQ Message Broker

* ID: ADR004
* Status: :deprecated: on 2022-10-21 with [Asynchronous Workflow Approach](../Accepted/ADR014-asynchronous-workflow-approach_V1.0.md) in [PSI-ADR]
* Deciders: @cgr @daf
* Date: 2022-05-10
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

The communication between PSS and PSS, or PSS and provider can be asynchronous.
Some requests might require longer time to process, hence the response will not be immediate.

Therefore, the PSI requires a message broker as an intermediate system between two entities for an async message exchange.
Message brokers enable applications, systems, and services to communicate with each other and exchange information by translating messages between the formal message protocols of the sender and the receiver.
As the time of writing, it is assumed that every PSS serves its own message broker and may also use it for internal processing.

## Decision Drivers

* Messages should be transmitted immediately, without polling
* Messages have to be available (at least) until they are processed by the receiver
* Authentication and authorization of different clients has to be possible
* Clustered message brokers are necessary for a distributed PSS to transparently distribute the messages to all nodes.
* Integrated Request-Response-Flows are favourable
* Integrated Broker-to-Broker transfer reduces the implementation effort

## Considered Options

* [RabbitMQ](https://www.rabbitmq.com/)
* [Apache Kafka](https://kafka.apache.org/)
* [Apache ActiveMQ Artemis](https://activemq.apache.org/components/artemis/)

## Decision Outcome

Chosen option: "RabbitMQ", because it fulfils all decision drivers and supports complex message routing.
Apache Kafka on the other hand will cause unnecessary storage load and complexity in handling its security aspects.
Apache ActiveMQ Artemis, although meets the decision drivers, it has a small community and is poorly maintained.

## Compliance

The PSI Mock-up will be set up with RabbitMQ message broker and related Spring Boot dependencies as soon as asynchronous messaging should be implemented.

## Pros and Cons of the Options

### RabbitMQ

RabbitMQ is a message broker, written in Erlang, designed as a message queue, with the ability to handle complex message routing.
It originally implemented the AMQP protocol, but has been extended to support STOMP, MQTT and other protocols.

It supports both producer/consumer (queue) and publisher/subscriber (topic) messaging.
A message is sent by the producer to an "exchange", but never directly to a "queue".
There can be different types of "exchange": "fanout", "direct", "topic" or "headers", depending on the approach of broadcasting the messages to the queues.
For example, "fanout" exchange broadcasts to all queues.

For "direct" and "topic" exchanges, a "queue" is bound to the "exchange" with a "binding key".
If the binding key of the "queue" matches the "routing key" of the message, the "exchange" dispatches it to the "queue", ready to be consumed.
"Header" exchanges use header attribute to handle the routing instead of a "routing key".
Queues can be marked as "durable" to ensure messages survive a message broker restart.

RabbitMQ supports several authentication and authorisation mechanisms.

* Good, because of high availability.
* Good, because it has a web UI for monitoring the message broker.
* Good, because it has excellent documentation coverage and big community.
* Good, because of cross-language clients.
* Good, because it supports multiple messaging protocols.
* Good, because it supports clustering by default, and federation to transmit messages between message brokers without requiring clustering.
* Good, because it supports "streams" for persistence and replay/time-travelling (log-based messaging such as Apache Kafka).
* Good, because it can integrate Request-Response-Flows via callback queues and correlation IDs.
* Good, because it supports message acknowledgments.
* Bad, because it does not have as high throughput as Apache Kafka, for example.

### Apache Kafka

Apache Kafka is a message broker designed for high throughput of data.
Its unique concept is the use of distributed transaction logs, which get appended by producers and read by consumers.
The permanent storage (limited only by size and/or age of entries) allows clients to retroactively analyse the data stream.

Messages (usually called "events") are organized in "topics".
They contain a key (e.g. the ID of a changed entity), a value (e.g. what happened to the entity and optionally the new value) and the timestamp.
When a producer sends it to the message broker, it gets appended to one "partition", assuring that events with the same key are always on the same partition.
The clients then read from all partitions of the topic, while the order is guaranteed to be how they were written to the partition.
"Exactly-once-processing" is also possible, as well as replication of one topic on a cluster of a separate PSS.

Authentication of clients can be done in multiple ways, which will not be listed here.
Authorization can be applied using ACLs on Topic, Groups and Clusters.

* Good, because high throughput
* Good, because high availability
* Good, because permanent storage
* Good, because clients in 10+ languages
* Good, because vast community and documentation
* Neutral: simple routing
* Bad, because potentially high storage cost
* Bad, because additional storage to be secured
* Bad, because no explicit response handling (workaround: a "response topic")

### Apache ActiveMQ Artemis

ActiveMQ Artemis is high-performance, non-blocking, multiprotocol, Java-based message broker.
ActiveMQ currently has the "classic" and Artemis version which should be the "next generation" message broker once it reaches maturity.
It implements the Java Messaging Service (JMS) API.

It supports both producer/consumer (queue) and publish/subscribe (topic) messaging concepts.
A message is sent to an "address" with a unique name, a routing type (anycast/multicast), and zero or more queues.
When a producer sends a message to an "address" of a routing type "anycast", it is routed to a queue and dispatched to a consumer.
Once the message is consumed, it is deleted from the queue. The "multicast" addresses act as topics.
The messages are routed to each queue for each subscriber, and therefore each subscriber will get every message.

The configuration is done in a broker.xml file. ActiveMQ Artemis supports authentication and authorization.
It has role based security for addresses and fine-grained security for queues.

* Good, because supports multiple protocols: AMQP, MQTT, STOMP, OpenWire, Core/HornetQ.
* Good, because supports clustering, and address and queue federation over connected message brokers.  
* Good, because of high availability using shared storage or network replication.
* Good, because of journal implementations for low-latency persistence as well as JDBC.
* Good, because of cross-language clients.
* Bad, because it is not mature, and community support is limited. Bigger support is available for the older ActiveMQ Classic.
* Bad, because it is poorly maintained and is very difficult to find code examples with Spring Boot that are up-to-date.
