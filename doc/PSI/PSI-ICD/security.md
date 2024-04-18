=begin

[[_TOC_]]

# Security Considerations

=end

The PSID is designed to be as implementation-agnostic as possible, so it can be used as a building block for any PSS or client (user, provider and governance).
Therefore, it must also be integrated in the overall security solution of the implementing system and can not establish strong requirements on those aspects.
The following subsections will provide an overview of interface-related security aspects the implementor must consider, as well as some guidance on available solutions.

## Transport Encryption

As stated in the introduction, all data is exchanged with TLS encryption using HTTPS.
It is strongly recommended using the latest version (which is 1.3 at the time of writing) and to disable older versions via the server configuration to prevent downgrade attacks.
The recommendation also applies to internal subsystems, even if they are not using PSI directly.
Each party can decide whether to use a well-known certification authority or to arrange a custom Public-Key-Infrastructure (PKI).
In the latter case, the root keys of the PKI have to be exchanged beforehand to establish the connection.

For extended security, the implementation can introduce additional encryption layers.
Most notably, classified systems will most likely require a VPN connection.
The soft- or hardware solution for the connection is to be agreed beforehand.

The above-mentioned measures are implemented on the transport layer and therefore transparent to the application.
But this implies that the data is only encrypted on the network between one system and another.
It is quite common though to use middleboxes like reverse-proxies and load-balancers, which will decrypt and re-encrypt the data for transmission to the next system.
For highest security, the parties can agree to use the JSON Web Encryption (JWE, RFC 7516) technology.
It enables the encryption of the message body (e.g. an inquiry), so it can only be decrypted by the target subsystem.

## Authentication

Authentication is the process of validating the identity of a communication partner.
This can be between two systems (PSS-to-Provider, PSS-to-PSS) or between a natural person and a system.
Setting up the authentication for the first time is part of the accreditation process.

Since all data exchange is based on HTTPS, the server is always authenticated via the SSL certificate.
As described above, the authenticity is checked either via a well-known certification authority or via a custom PKI.
Additionally, it is possible to use certificate pinning (RFC 7469) to prevent man-in-the-middle attacks.

There are many standardized authentication methods available for clients.
Selecting the right one depends on the security needs and structure of the implementation.
There may also be differences made for authentication of other systems and natural persons.

For machine-to-machine communication, each implementation is expected to have a configuration of allowed hosts (e.g. `api.example.com:8081`) and their respective authentication scheme and credentials.
Depending on the operational requirements, this may be offered as a self-service UI or restricted to the system operators (e.g. for security reasons or complexity concerns).
When calling an endpoint, the respective authentication can be selected and applied automatically.

The recommended authentication method is the OAuth 2.0 protocol, which is also favoured by the REST API Design Guidelines of TM Forum.
It separates the authentication provider from the rest of the system and allows authenticating systems and natural persons alike.
The credentials to authenticate the client can be classic combinations of username and password, a second-factor, or certificate-based like the server authentication.
Either way they are sent to the authentication provider before the first request, which then verifies the identity and creates an access token.
The token is stored in a server-side session (using a short-lived cookie in the client browser) or returned to the client for further use as an HTTP-Header (useful for machine-to-machine communication).

## Authorization and Data Visibility

Authorization is closely related to the above described authentication:
It is the process of determining whether the authenticated entity has access to a source.
Because of the close relation, it is not possible to define this without implying an authorization method.
This chapter describes two aspects, which have to be considered by the server implementation.

First, it has to check the permissions of the client to access any given endpoint.
For example, there are endpoints that are only allowed to be called by the system governance (see [PSI-TOD]).
Consequently, any request by other parties must be prohibited, and a 403 status code must be returned as described in the [PSI-ICD].
The most common concept for this is role based access control (RBAC).

If the client is allowed to access the endpoint, the server must also check if the client is allowed to perform the given operation on the requested data.
This could be implemented by using an access control list (ACL).
As long as not stated otherwise, every party is only allowed to read and update entities they previously created themselves.
The owner will extend this access as side effects to other actions:

* As part of resource accreditation, the resource is made visible to SATCOM users.
* When an order is created, the party information of the customer is made visible to the provider.

The system might also provide further internal processes to make data *writable* by other parties, e.g. for other persons in the same organization or a mission expert that supports the customer in the process of service acquisition.
These permission checks are mandatory for the overall entity, but can also be performed on single attributes if required.

The Event API needs special attention in this regard.
The implementation must assure that no published events are leaked to third parties.
Also, it shall ensure that received events are processed under the same conditions as above.

## Other Security Aspects

There are other security aspects that should be considered by all systems independently of the PSI.
The following (incomplete) list is indicating some well-known ones, that could be part of the accreditation process if deemed relevant for the governance:

* Securing data-at-rest (e.g. in databases) from unauthorized access and data loss
* Denial-of-Service attacks
* Brute-Force attacks
* Replay attacks
* Physical security
