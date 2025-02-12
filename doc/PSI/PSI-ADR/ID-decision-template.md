# {Short Title of Solved Problem and Solution}

=begin

How-To-Use (Remove when done!)
Copy this template to a file name (see rules for the date below)
Add a reference to the decision in the index.md.
File a merge request.

=end

* ID: ADR000
* Status: {:proposed: | :rejected: | :accepted: | :deprecated: | ... | :superseded: by [ADR-0005](0005-example.md)}
* Deciders: {list everyone involved in the decision}
* Consulted: {list everyone whose opinions are sought} <!-- optional -->
* Date: {YYYY-MM-DD when the decision was last updated}
* Version: {1.0, update accordingly}
* Category: {Architecture | Design | Security | ...}

Technical Story: {description | ticket/issue URL} <!-- optional -->

## Context and Problem Statement

{Describe the context and problem statement, e.g., in free form using two to three sentences. You may want to articulate the problem in form of a question.}

## Decision Drivers <!-- optional -->

* {driver 1, e.g., a force, facing concern, ...}
* {driver 2, e.g., a force, facing concern, ...}
* ... <!-- numbers of drivers can vary -->

## Considered Options

* {option 1}
* {option 2}
* {option 3}
* ... <!-- numbers of options can vary -->

## Decision Outcome

Chosen option: "{option 1}", because {justification. e.g., only option, which meets k.o. criterion decision driver | which resolves force {force} | ... | comes out best (see below)}.

## Compliance

{Describe how to measure and govern the application of the decision.}

### Positive Consequences <!-- optional -->

* {e.g., improvement of quality attribute satisfaction, follow-up decisions required, ...}
* ...

### Negative Consequences <!-- optional -->

* {e.g., compromising quality attribute, follow-up decisions required, ...}
* ...

## Pros and Cons of the Options <!-- optional -->

### {Option 1}

{example | description | pointer to more information | ...} <!-- optional -->

* Good, because {argument a}
* Good, because {argument b}
* Bad, because {argument c}
* ... <!-- numbers of pros and cons can vary -->

### {Option 2}

{example | description | pointer to more information | ...} <!-- optional -->

* Good, because {argument a}
* Good, because {argument b}
* Bad, because {argument c}
* ... <!-- numbers of pros and cons can vary -->

### {Option 3}

{example | description | pointer to more information | ...} <!-- optional -->

* Good, because {argument a}
* Good, because {argument b}
* Bad, because {argument c}
* ... <!-- numbers of pros and cons can vary -->

## Security Considerations <!-- optional -->

### {Option 1}

<!-- Write on which metric, C, I, and A, the option shows vulnerability. Elaborate how strong the vulnerability is (e.g. strong, weak etc.). -->
* Low vulnerability considering C aspect, because {argument a}
* Middle vulnerability considering I aspect, because {argument b}
* High vulnerability considering A, because {argument c}

### {Option 2}

<!-- Write on which metric, C, I, and A, the option shows vulnerability. Elaborate how strong the vulnerability is (e.g. strong, weak etc.). -->
* High vulnerability considering C aspect, because {argument a}
* Low vulnerability considering I aspect, because {argument b}
* Middle vulnerability considering A, because {argument c}

### {Option 3}

<!-- Write on which metric, C, I, and A, the option shows vulnerability. Elaborate how strong the vulnerability is (e.g. strong, weak etc.). -->
* Middle vulnerability considering C aspect, because {argument a}
* High vulnerability considering I aspect, because {argument b}
* Low vulnerability considering A, because {argument c}

## CIA Comparison for All Given Options

<!-- example here! -->
| Option # | Confidentiality | Integrity | Availability |
| ------ | ------ | ------ | ------ |
| 1 | Low | High | High |
...
| n | Low | Medium | Medium |

Table: {CIA table for different options}{#tbl:cia_table_options}

