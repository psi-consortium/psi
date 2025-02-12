# Customer Inquiry Message Sequence

* ID: ADR015
* Status: :accepted:
* Deciders: @cgr @daf @cg
* Date: 2022-11-25
* Version: 1.0
* Category: Design

## Context and Problem Statement

In the previous decision [2022-10-21 Asynchronous Workflow Approach](2022-10-21-asynchronous-workflow-approach_V1.0.md) we decided how to handle events with focus on the order management.
The second API which requires asynchronous messages is the customer inquiry management, which was defined from scratch because of missing patterns in TM Forum.
This leads to the need of defining the message flow between PSS and provider.
Since this communication is not 1-to-1, the conditions are slightly but essentially different and require a new decision on the data exchange.

## Decision Drivers

* Use of the Event API
* Consistent state under all conditions
* API and model changes are *possible*

## Considered Options

* Distinct inquiry per system
* Common inquiry with synchronisation

## Decision Outcome

Chosen option: Common inquiry with synchronisation, because it is the more consistent option.
The additional filtering of event data is considered a minor effort.

## Compliance

Requirements are documented in PSI-REQ and PSI-TOD.
An example implementation (without filtering) will be added to the mock-up.

## Pros and Cons of the Options

### Distinct Inquiry per System

In this variant, every system creates a distinct inquiry.
The user creates an inquiry in the PSS, and the PSS creates a new inquiry (with different IDs and potentially different data) in every single provider system.
Figure {@fig:distinct-inquiry} shows the inquiries with ID 1 for the PSS and 2/3 for the providers.
The initial one is created by the user, while the others are created by the PSS, which has to keep track of this association.
When the inquiry is processed by the provider, the system sends an "updated"-event to the PSS.
After the last provider has set the state to "completed", the PSS can update its own state and inform the user.
Depending on the implementation, it can also directly fetch and aggregate the results of the provider or wait until the user wants to view it.

Note the option of storing the association as part of the actual inquiry data structure was discussed after doing that in option 2 (Common inquiry with synchronisation).
We found that this does not add much additional value to this particular setup and deliberately decided not to change this description.

```plantuml
@startuml
title Inquiry/ITT Process (Option 1: Distinct Inquiry per System)

actor Customer
entity PSS
entity "Provider 1" as Prov1
entity "Provider 2" as Prov2
actor "Staff" as Prov_Staff

Customer -> PSS ++ : POST /inquiry
PSS -> Prov1 ++ : POST /inquiry
return {id: 2, state: PENDING}
PSS -> Prov2 ++ : POST /inquiry
return {id: 3, state: PENDING}
note over PSS : Store id-association\n1 -> [2,3]
return {id: 1, state: PENDING}

Prov_Staff -> Prov1 ++ : Enter Result
Prov1 -> PSS ++ : POST /topic/inquiry/updated\n{id: 2, state: COMPLETED}
note over PSS : Store that inquiry 2\nis completed
return Ack
return Ack

Prov_Staff -> Prov2 ++ : Enter Result
Prov2 -> PSS ++ : POST /topic/inquiry/updated\n{id: 3, state: COMPLETED}
note over PSS : Store that inquiry 3\nis completed\nand update inquiry 1
PSS --> Customer : Mail (Inquiry 1 completed)
return Ack
return Ack

... some time later (PSS might already pre-fetch results) ...

Customer -> PSS ++ : GET /inquiry/1/offerings
PSS -> Prov1 ++ : GET /inquiry/2/offerings
return [{...}, {...}]
PSS -> Prov2 ++ : GET /inquiry/3/offerings
return [{...}]
return [{...},{...},{...}]
@enduml
```

![Distinct Inquiry per System](../../common/pixel.png){#fig:distinct-inquiry}

* Good, because explicit requests per provider
* Good, because unambiguous state handling
* Bad, because inconsistent use of events
* Bad, because additional storage of associated IDs

### Common Inquiry with Synchronisation

In the second option, all systems share a common ID and synchronize the state (see figure {@fig:common-inquiry}).
The inquiry is created by the user, possibly containing the list of providers they want to include (can be constructed by the PSS if not present).
The PSS stores this information directly in the data structure and introduces a state for each provider.
This is then propagated to the named providers using a "created"-event.
It may be necessary to filter the submitted data to not disclose the other participants.
The providers work on the inquiry and send an update of their own state upon completion, which is stored by the PSS (other changes are dismissed).
When all providers completed their part, the overall state is set to "completed" as well and the user is informed as above.
The PSS might also decide to show the intermediate results to the user and abort pending providers (which are then informed via an "update"-event as well).
To prevent long delays and limiting the response time of the providers, the governance of a PSS should be able to set a *responseTime* characteristic for inquiries in the provider's party profile.
If the provider does not respond within the agreed response time, their state will be updated to "cancelled" and their product offerings will not be part of the customer inquiry results.

```plantuml
@startuml
title Inquiry/ITT Process (Option 2: Common Inquiry with Synchronisation)

actor Customer
entity PSS
entity "Provider 1" as Prov1
entity "Provider 2" as Prov2
actor "Staff" as Prov_Staff

Customer -> PSS ++ : POST /inquiry\n{providers: [\n\t{name: Prov1},\n\t{name: Prov2}\n]}
note over PSS 
""{id: 1,state: PENDING, providers: [""
""    {name: Prov1, state: PENDING},""
""    {name: Prov2, state: PENDING}""
""]}""
end note
PSS -> Prov1 ++ : POST /topic/inquiry/created\n{id: 1, state: PENDING, providers: [\n\t{name: Prov1, state: PENDING}\n]}
return Ack
PSS -> Prov2 ++ : POST /topic/inquiry/created\n{id: 1, state: PENDING, providers: [\n\t{name: Prov2, state: PENDING}\n]}
return  Ack
return {id: 1, state: PENDING}

Prov_Staff -> Prov1 ++ : Enter Result
Prov1 -> PSS ++ : POST /topic/inquiry/updated\n{id: 1, state: PENDING, providers: [\n\t{name: Prov1, state: COMPLETED}\n]}
note over PSS 
""{id: 1, state: IN_PROGRESS, providers: [""
""    {name: Prov1, state: COMPLETED},""
""    {name: Prov2, state: PENDING}""
""]}""
end note
return Ack
return Ack

Prov_Staff -> Prov2 ++ : Enter Result
Prov2 -> PSS ++ : POST /topic/inquiry/updated\n{id: 1, state: PENDING, providers: [\n\t{name: Prov2, state: COMPLETED}\n]}
PSS --> Customer : Mail (Inquiry 1 completed)
note over PSS 
""{id: 1, state: COMPLETED, providers: [""
""    {name: Prov1, state: COMPLETED},""
""    {name: Prov2, state: COMPLETED}""
""]}""
end note
return Ack
return Ack

... some time later (PSS might already pre-fetch results) ...

Customer -> PSS ++ : GET /inquiry/1/offerings
PSS -> Prov1 ++ : GET /inquiry/1/offerings
return [{...}, {...}]
PSS -> Prov2 ++ : GET /inquiry/1/offerings
return [{...}]
return [{...},{...},{...}]
@enduml
```

![Common Inquiry with Synchronisation](../../common/pixel.png){#fig:common-inquiry}

* Good, because consistent use of events
* Good, because explicit state handling as part of the data model
* Neutral, because event data has to be filtered

## Implications for the Scope

The maximum time that is allowed to answer an inquiry is defined as a parameter in this API.
