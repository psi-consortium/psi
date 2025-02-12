# Implementation of Mission Management ODA Component

* ID: ADR040
* Status: :accepted:
* Deciders: @hop, @wr, @cgr, @ncz, @bmu
* Date: 2024-11-27
* Version: 1.0
* Category: Design

## Context and Problem Statement

How can a mission management ODA component be implemented regarding GUI, UX and structure of mission planning?
The way we thought about mission planning in the past was very linear.
The user defines a name, duration, location, service needs and gets service options as a result.
When thinking of missions related to crisis response, this approach is not sufficient anymore.
The orchestration of a crisis response is probably centralized, so a mission will be planned on a very high level.
Organizations of a certain area or expertise would plan a sub-mission within the main mission.
They will send teams like e.g. firefighters, medical teams, THW etc. into the field and each team might have different service needs.

## Decision Drivers

* usage must be easy
* templates should be provided
* sub-missions can be created
* teams must be included
* component must be self-standing and capable of being integrated into other systems

## Decision Outcome

The new concept will be decoupled from UCSM to rethink the concept of missions.
The following sections describe the topics of the discussion and their outcome.

### Creating a mission from templates

The users are no experts regarding products from earth observation or SatCom domains, which creates a need for supporting tools.
Therefore, it should be possible for the user to use a template to create a mission.
This will provide an entry-point on a low level and draws a frame, in which the user can easily define service needs.

### Creating sub-missions on the basis of a need-to-know principle

Being able to create sub-missions gives actors the freedom to adapt certain aspects of the main mission by default, while being able to manage local situations more specifically.
This will follow a hierarchical structure, which also mirrors the organisational structure of crisis responders.
The head of the organization will define a main mission as a frame.
This might only include data, which are true for all participants, like event type, date, name of the mission.
Organizations of certain expertise or of a specific location are system users with a dedicated role, which allows them to plan a sub-mission inside the main mission.
The sub-mission will inherit certain data from the main mission, some of which are editable and regarded a default value, and some of them are fixed.
The sub-mission planner could then define specific needs for their sub-mission, this can also be supported via templates.
The stakeholders share information on the need-to-know principle. E.g. the sub-mission planner might send a team of firefighters to a location and shares that information with the headquarters, but specific information about the team-members won't be shared.

### Dealing with teams as resources instead of managing them separately

The problem of dealing with teams as an own entity is, that it produces a big overhead.
Would it be managed in a dedicated subsystem?
Do we need to provide a team-editor?
Do we need to deal with a team on member-level?
The solution was, to treat and manage teams as resource.
The subsystem and API are already there and need only be updated, teams can easily be integrated in existing data models.
It also enables to pool and share human resources by describing their skills.
By linking any resource or product to another one, one could also easily model requirements for something like a firetruck, that may need internet connection and firefighters alike.

### Seeing the mission from three different perspectives

Mission as a concept is highly abstract and therefore, very flexible.
This enables to provide different views on the mission.
These views are:

* Timescale view -> Gantt chart
* Geo-scale view -> map with areas and other layers
* Resource interdependencies view -> showing dependencies between actors, areas, resources and other topics. From there, new sub-mission and resources can be added.

This helps the user to gather a full overview of the mission.

## Compliance

The new mission management approach will be visualized via wireframes.
Additionally, we will create a proof-of-concept implementation, that will showcase the main storyline.
Depending on time and human resources, some parts might not be implemented in code but will be in future extension of the project.
