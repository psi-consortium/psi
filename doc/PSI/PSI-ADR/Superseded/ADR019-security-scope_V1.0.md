# Security Scope (V1.0)

* ID: ADR019
* Status: :superseded: by [Security Scope V1.1](../Accepted/ADR019-security-scope_V1.1.md) in [PSI-ADR]
* Deciders: @dvs @rgs @vmr @wr @cg @daf @cgr @ncz @rsperb
* Date: 2023-03-15
* Version: 1.0
* Category: Security

## Context and Problem Statement

The scope of security aspects that shall be covered by the API definition is to be decided.

## Decision Outcome

As for other aspects we strongly divide between "the API definition" (the main output of this project) and "the implementation" of the API (a real PSS or provider system).
Thus, this decision is mainly considering **where** security is handled, not **if**.

For PSI this means practically that the interface must be interpreted as *one* building block of an overall solution.
The solution must be enabled to allow strong encryption and meaningful authentication.
Thus, the interface must not block such techniques.
However, the interface cannot and should not enforce the usage of such techniques.
They fall into to the domain solution and not the interface.
The interface will (only) strongly suggest in the documentation the application of such techniques.

This is in line with TM-Forum's approach[^tmf_rest].

[^tmf_rest]: <https://www.tmforum.org/resources/specification/tmf630-rest-api-design-guidelines-4-2-0/>

### Authentication

Authentication is the process of validating the identity of the source of a request.
In HTTP-based communication it is usually implemented by credentials (username, password, second-factor) stored in a server-side session or a JWT[^jwt], API-Keys or a PKI[^pki].
It is performed via one or more requests before the actual API access, and transported in a separate HTTP-Header field and therefore has no effect on the transported data.
It was agreed to exclude it from the scope of the API definition, but add it as a prerequisite into the documentation with a reference to the TM Forum REST API Guidelines, which suggest the use of OpenAuth 2.0.

As per requirements, additional accreditation of participants will be performed, which leads to even higher trust in the legitimacy of authenticated requests.
The actual process is considered to be outside the project scope, as it is performed before the API can be used.

Authentication of the server towards the client on the other hand is integral part of the mandatory SSL protocol.
It will not be defined how the PKI is implemented, as this depends on the concrete security level of the participants.
Therefore, no additional information will be provided.

[^jwt]: JSON Web Token (see <http://jwt.io/>)
[^pki]: Public Key Infrastructure

### Authorization and Data Visibility

Authorization is closely related to the above discussed authentication:
It is the process of determining whether the authenticated entity has access to a source.
Because of the close relation, it is not possible to define this without implying an authorization method.
The documentation will be extended with guidelines to implement this, for example using role based access control (RBAC).

Especially for a multi-provider PSS, but also for a provider handling data from multiple customers, it is very important to prevent data leaking between parties.
This again is the responsibility of the implementation and already integrated in the phrasing of requirements.
The interface definition describes the structure of data that can be exchanged, while the implementation must decide which entities (and attributes) are actually exposed to which authenticated party.

### Transport Encryption

All data that is exchanged between systems has to be encrypted while it is transported.
As already stated in the [PSI-ICD], this is accomplished by using SSL with TLS v1.2 or higher.
Additional layers like VPNs, custom PKI and certificate-pinning are possible, but out of scope for this project.

Another possibility is to use the JWE[^jwe] technology to encrypt data end-to-end.
This allows to obfuscate the data even for reverse-proxies or other middle boxes.
There is currently no known data-exchange where it seems reasonable to enforce this.
Since the used data format can easily be adapted for this approach, some information on the subject will be added to the [PSI-ICD].

[^jwe]: JSON Web Encryption (see <http://jwt.io/>)

### Data at Rest

Securing data at rest (on hard drives, databases etc.) from unauthorized access and data loss is out of scope of the interface.

### Other Attack Vectors

The API could be misused by attackers, e.g. via denial-of-service (including brute-force on authentication) or replay attacks.
This can not be prevented by the API definition, but by the security design of the implementation.
It is therefore out-of-scope for this project.

## Compliance

As a first step, the above-mentioned possibilities will be added to the [PSI-ICD] with useful links to external sources.
Depending on the extent, it may be decided to extract it to a new document later.

Additionally, [PSI-TOD] and [PSI-REQ] are to be checked and augmented with references to these new chapters of the [PSI-ICD].

## Implications for the Scope

The accreditation and vetting processes can be performed on data provided via the interface.
The interface itself can only define its formatting, but not its content.
The business processes necessary to implement accreditation and/or vetting are - in principle - enabled by the PSI, but cannot be enforced.

As discussed above, the interface must be interpreted as *one* building block of an overall solution.
The solution must be enabled to allow strong encryption, meaningful authentication, and other security measures.
Thus, the interface must not block such techniques.
However, the interface cannot and should not enforce the usage of such techniques.
They fall into to the domain solution and not the interface.

As discussed above, the API could be misused by attackers.
This can not be prevented by the API definition, but by the security design of the implementation.
It is therefore out-of-scope for this project.

The interface is developed with state-of-the-art designs and based on a common industry standard.

Whenever a specific technology is considered for usage together with the PSI, the corresponding decision record lists a summary of the probability scores of each option regarding the CIA triad.

There are only parts of this candidate requirement to be applied to an interface definition, because most of them can only be applied to a PSS or in some cases to the implementation of the interface.
As discussed above, this is out of scope for this project.
Nevertheless, whenever applicable, the security aspects are analysed based on common security standards.

As described above, the interface enforces transport layer encryption using SSL with TLS v1.3 or higher.
However, data at rest and its security are out of scope of this project.
