=begin

# Requirement Categories

[[_TOC_]]

=end

For clarity and ease of use, all PSI requirements are grouped by the corresponding endpoint of the interface and then enumerated corresponding to the [PSI-TOD].
Additionally, they are tagged according to their core semantic with one or more category.
The categories are listed and described in the table below.

| Category | Description                                                   |
| -------- | ------------------------------------------------------------- |
| MISSION  | Requirements on missions                                      |
| INQUIRY  | Requirements on customer inquiries                            |
| PARTY    | Requirements on party management (individuals, organizations) |
| POOLING  | Requirements on pooling of resources, services, and products  |
| SEC      | Requirements on security                                      |
| ORDER    | Requirements on product ordering                              |
| DOC      | Requirements on managing documents as attachments             |
| TICKET   | Requirements on managing trouble tickets                      |
| MONITOR  | Requirements on managing service monitoring                   |

Table: PSID Requirement Categories {#tbl:psid-req-cat}

=begin

> @hop: These are not yet implemented. Include as soon as they become relevant.

| PSID-MATCH   | Requirements related to matchmaking                           |
| PSID-MISSION | Requirements on user missions                                 |

=end
 
# Interfaces

A PSS has at least four different actors they need to interact with:

* Providers (satellite operators, (Gov)SatCom service providers)
* Other Pooling & Sharing Systems
* Users
* Governance (the operator of the PSS and their staff)

Towards deducing the interfaces that implement a given PSI requirement, the diagram in the respective operation in the [PSI-TOD] should be consulted.
The diagram demonstrates graphically who takes the role of a server for the REST endpoints and who are the actors that consume them, taking over the role of a client.

For example, if the server is the PSS and the clients are the customers, providers, and other PSS, the requirement relates to the PSS-user I/F, the PSS-provider I/F, and the PSS-PSS I/F respectively.

# RFC 2119 Phrasing

Several words are used to signify the requirements in the specification.
The key words "MUST", "MUST NOT", "REQUIRED", "SHALL", "SHALL NOT", "SHOULD", "SHOULD NOT", "RECOMMENDED", "MAY", and "OPTIONAL" in this document are to be interpreted as described in [RFC 2119](https://www.rfc-editor.org/rfc/rfc2119).

1. **MUST**
   This word, or the terms "REQUIRED" or "SHALL", mean that the definition is an absolute requirement of the specification.
2. **MUST NOT**
   This phrase, or the phrase "SHALL NOT", mean that the definition is an absolute prohibition of the specification.
3. **SHOULD**
   This word, or the adjective "RECOMMENDED", mean that there may exist valid reasons in particular circumstances to ignore a particular item, but the full implications must be understood and    carefully weighed before choosing a different course.
4. **SHOULD NOT**
   This phrase, or the phrase "NOT RECOMMENDED" mean that there may exist valid reasons in particular circumstances when the particular behaviour is acceptable or even useful, but the full    implications should be understood and the case carefully weighed before implementing any behaviour described with this label.
5. **MAY**
   This word, or the adjective "OPTIONAL", mean that an item is truly optional.
   One vendor may choose to include the item because a particular marketplace requires it or because the vendor feels that it enhances the product while another vendor may omit the same item.
   An implementation which does not include a particular option MUST be prepared to interoperate with another implementation which does include the option, though perhaps with reduced functionality.
   In the same vein an implementation which does include a particular option MUST be prepared to interoperate with another implementation which does not include the option (except, of course, for the feature the option provides.)
