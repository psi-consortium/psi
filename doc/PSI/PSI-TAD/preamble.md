=begin

# :book: wiki-Internal section, not compiled to documents

[[_TOC_]]

# Preamble

=end

The Pooling and Sharing Interface Definition (PSID) project develops the specifications for the interfaces of a Pooling & Sharing system based on the Open Digital Framework of TM Forum.
The Open Digital Framework defines processes and business entities that are commonly used by telecommunication providers to achieve the best possible compatibility between them.
Although most of the work is built around terrestrial communication services, satellite providers are interested to adapt it, too.
PSID follows the same domain structure, including but not necessarily restricted to:

* Common
* Customer
* Product
* Service
* Resource

The terms in these documents refer to the Aggregate Business Entities (ABEs) of the Information Framework (Shared Information/Data Model, SID), including some addendums.
They are described again to show the actual scope (excluding what is not required for this project) and to add some more information on how they are applied to the satellite communication context.

The actual API specification is based on the Open APIs of TM Forum, which is certified to comply with the SID.
PSID applies a set of patches to create tailored OpenApi files, which are then used to generate the code stubs of the prototypes.

Note that we distinguish between the definition (PSID) and its implementation, the Pooling & Sharing Interface (PSI).

In addition, we make use of MEF's standardization approach for products via schemas.
MEF (formerly Metro Ethernet Forum) is a nonprofit, global industry consortium of network, cloud, and technology providers that develops and promotes technical standards, certification programs, and best practices to drive interoperable and agile Carrier Ethernet and digital service delivery.
