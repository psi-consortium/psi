# Use GitLab Flow as Branching Concept

* ID: ADR008
* Status: :accepted:
* Deciders: @daf @cgr @wr @hop
* Date: 2022-07-05
* Version: 1.0
* Category: Architecture

## Context and Problem Statement

We have to choose an appropriate branching concept for our repository.

### What Is a Branching Concept

Branches are primarily used as a means for teams to develop features giving them a separate workspace for their code.
These branches are usually merged back to a master branch upon completion of work.
This way, features (and bug fixes) are kept separate from each other, making it easier to fix mistakes.
This means that branches protect the main body of code and changes made to any given branch don’t affect other developers.

A branching strategy, therefore, is the strategy that software development teams adopt when writing, merging and deploying code when using a version control system.
It is essentially a set of rules that developers follow to define how they interact with a shared codebase.

## Decision Drivers

* Ease of application
* Support of agile QA workflow (allow QA to work on dedicated release branch)
* Knowledge in the team

## Considered Options

* [GitFlow](https://www.atlassian.com/de/git/tutorials/comparing-workflows/gitflow-workflow)
* [GitHub Flow](https://docs.github.com/en/get-started/quickstart/github-flow)
* [GitLab Flow](https://docs.gitlab.com/ee/topics/gitlab_flow.html)
* [TrunkBased Flow](https://trunkbaseddevelopment.com/)

## Decision Outcome

Chosen *slightly modified* [GitLab Flow](https://docs.gitlab.com/ee/topics/gitlab_flow.html).

While being relatively simple, it still allows tracking releases and work on them while progressing development.
This work can be picked back to development branches.
The main branch is utilized to contain deployment-ready code, so code is merged into the production branch when it’s ready to be released.

The modification lies in the nature of PSI not having real releases. We are not delivering a solution.
Therefore, we usually treat release-branches to be **delivery branches**.
In PSI, we have fixed release cycles. When approaching a release cycle, feature-branches should be merged only when they are not affecting the deliverability of the main branch.

The release branches are created in front of a delivery and are used to track a release and the RIDs raised on them.  

With this modification, the strategy is a chimera between [TrunkBased](https://trunkbaseddevelopment.com/) and GitLab workflow.

### Maintainer Permissions

Together with this decision, both key developers are granted maintainer access, so they can merge on their own and enforce the strategy among other teammates.

Peer-review should be performed before merge (assured by the reviewed-by feature of GitLab).
In rare, exceptional cases, peer-review can be omitted. This just be justified by a comment in the merge request.

Direct push to the main branch is strictly prohibited and only allowed for AIV activities like e.g. fixing a stuck or broken pipeline.

## Compliance

Apply the new workflow from **SPRINT S-06 22CW28-30** on.

## Pros and Cons of the Options

### GitFlow

The main idea behind the Git flow branching strategy is to isolate your work into different types of branches. There are five different branch types in total:

* Main
* Dev
* Feature
* Release
* Hotfix

The two primary branches in Git flow are main and develop. There are three types of supporting branches with different intended purposes: feature, release, and hotfix.

#### Pro

* The various types of branches make it easy and intuitive to organize your work.
* The systematic development process allows for efficient testing.
* The use of release branches allows you to easily and continuously support multiple versions of production code.

#### Con

* Depending on the complexity, the Git flow model could overcomplicate and slow the development process and release cycle.
* In longer development cycles, Git flow is historically not able to support Continuous Delivery or Continuous Integration (CI/CD).
* Less agile

### GitHub Flow

The GitHub flow branching strategy is a relatively simple workflow that allows smaller teams, or smaller applications/products that don't require supporting multiple versions, to expedite their work.
In GitHub flow, the main branch contains your production-ready code.
The other branches, feature branches, should contain work on new features and bug fixes and will be merged back into the main branch when the work is finished and properly reviewed.

#### Pro

* Of the strategies covered, GitHub flow is the simplest.
* Because of the simplicity of the workflow, this branching strategy allows for Continuous Delivery and Continuous Integration (CI/CD).
* This branch strategy works great for small teams and smaller applications.

#### Con

* This Git branch strategy is unable to support multiple versions of code in production at the same time.
* The lack of dedicated development branches makes GitHub flow more susceptible to bugs in production.

### GitLab Flow

At its core, the GitLab flow branching strategy is a clearly defined **workflow**.
While being quite similar to the GitHub flow branch strategy, the main differentiator is the addition of environment branches, i.e. "production" or "release" branches, depending on the situation.
In PSI, these "environments" e.g. can be the AQA (agile QA) review or a delivery ("release").

Just as in the other two Git branch strategies, GitLab flow has a main branch that contains code that is ready to be deployed. However, this code is not the source of truth for releases.
In GitLab flow, the feature branch contains work for new features and bug fixes which will be merged back into the main branch when they’re finished, reviewed, and approved.
For each release the team will create a release branch to review and approve it separately, too.

#### Pro

* When compared to the Git flow branch strategy, GitLab flow is more simple.
* GitLab flow is more organized and structured than the GitHub flow branch strategy.
* After slight modification, GitLab flow can allow for Continuous Delivery, Agile Delivery and versioned releases.

#### Con

* GitLab flow is not the simplest Git branch strategy.
* GitLab flow is not the most structured Git branching strategy which can lead to messy collaboration.
