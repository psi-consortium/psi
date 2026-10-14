# Placement of Governance and Algorithm Content in the PSI Document Set

* ID: ADR043
* Status: :proposed:
* Deciders: @hop
* Consulted: PSI governance (WS3), Amartus as independent reviewer of the first appendix
* Date: 2026-09-28
* Version: 0.1
* Category: Documentation

Technical Story: spike on where governance and algorithm content live in the PSI document set, raised by the two-speed governance story

## Context and Problem Statement

Three kinds of content are waiting for a home in the PSI document set, and none of them fits an existing document cleanly:

1. **The governance framework**: the two-speed model (PSI as innovation space, SODA as maturation channel into TM Forum) and the graduation criteria for patterns that leave the innovation space.
   Both currently target "the PSI governance document", which does not exist.
2. **Algorithm descriptions** from the interoperability and matchmaking work: the touchpoint mappings between catalog, matchmaking, qualification/POQ, CPQ, quote and ordering, and the mission-masked matching engine with its interfaces and data mappings.
3. **The whitepaper "Mission-Centric Matchmaking for Composed Services"** the reference for the mission-masked matching engine and the first concrete artefact: a self-contained method description of roughly fifteen sections with diagrams, written outside the PSI document set, whose masking, filtering and ranking mechanics the matching engine implements and whose other parts (reverse matchmaking, AI augmentation, catalog governance) are background.

The spike question is: which location in the PSI document set holds such content?
The candidates named by the story are a section of the [PSI-TAD], a section of the [PSI-TOD], or a new document.
The candidates are compared against the document list of `CONTRIBUTING.md`, §1, which is the list of documents whose sources this repository maintains.
Product data sheets are decided here as well, because they will arrive with the same question and the same shape as algorithm descriptions: human-readable reference material that supports a task without being a task.

Two words are used with a fixed meaning below.
An **annex** is an external file published next to a document in the release, such as `PSI-ICD-Annex-I.zip`; it is not rendered into the document.
An **appendix** is a chapter inside a document, rendered into its PDF and listed in its table of contents; it is additionally rendered as a standalone PDF from its own start document, so that it can be reviewed and distributed on its own like an annex.

## Decision Drivers

* Implementers must find the content where they already read: PSI-READFIRST routes the interface-implementation perspective through TAD, TOD and ICD.
* The TOD body is, by its own scope note, "a technical description of all business tasks and operations ... and how they are realized through the standardized interfaces"; a method description or a data sheet is neither a task nor an operation, but it explains how a PSS may realise one.
* The TAD "determines the language" of all documents; a text that introduces its own vocabulary must not become canonical terminology by placement alone.
  The whitepaper uses *mission* for an intent class where the TAD's mission is an instance, *plan* where the TAD answers per inquiry item, and *relaxation* where the TAD has a static allowed deviation.
* The document set is deliberately small and every document carries a Document Signature Table, an Approver, a Document Change Record, a PSI-READFIRST entry, a PSI-DL entry and a build; a new document is the most expensive option and must earn that cost.
* Normative and informative content must stay distinguishable: the body of a document is what an implementer is held to, and supplementary text must say which of its parts bind.
* PSI-MADR already covers decisions on "processes, workflows and management frameworks" (its Document Scope) and, per `CONTRIBUTING.md`, "govern[s] repository structure, documentation tooling, and mock-up implementation choices".
* The ICD external annexes are the existing precedent for material that belongs to a document without being part of its body.
* Texts written outside the document set may carry a third-party licence and confidential detail; `CONTRIBUTING.md`, §8, requires a PSI-SLF entry for third-party material and forbids confidential material in the public repository.
* The rule must be executable for the work that waits on it: the governance framework, the touchpoint mappings, the matching engine documentation and the placement of the whitepaper.

## Considered Options

For algorithm descriptions, the whitepaper and product data sheets:

* A. A section in the TAD body
* B. A section in the TOD body (a new task or category)
* C. Appendices of the TOD, analogous to the ICD annexes
* D. A new document, for example PSI-ALG

For the governance framework:

* E. A section in the TAD body
* F. A decision record in the PSI-MADR, category Governance
* G. A new document, for example PSI-GOV
* H. Repository-level documentation (`CONTRIBUTING.md`, `README.md`)

## Decision Outcome

Chosen options: **C for algorithm descriptions, method papers and product data sheets** and **F for the governance framework**.

C is chosen because the TOD is where implementers already look for how a task is realised, because an appendix keeps such text out of the normative body while rendering it into the same PDF and table of contents, and because it reuses the stitching and review mechanics the TOD already has (the ICD annexes show the same separation for machine-readable material).
Options A and B would turn method text into terminology or into operations, and option D pays for a document that would hold two appendices for the foreseeable future.

F is chosen because the two artefacts of the governance framework have exactly the shape of a decision record: the two-speed model is a decision with drivers and consequences, and the graduation criteria are its Compliance section, "how to measure and govern the application of the decision".
The PSI-MADR is public, approved at consortium level (its Approvers are the SES and CGI project managers), versioned through supersession, and declares management frameworks in scope.
"The PSI governance document" is therefore the PSI-MADR, and both artefacts land in one decision record of category Governance (the two-speed model as its Decision Outcome, the graduation criteria as its Compliance section).
Option G is deferred, not rejected: when governance content stops being expressible as decisions with compliance sections, for example because it needs continuously maintained registers of roles or channels, a further decision record extracts it into a dedicated document.

### Placement Rule for Future Content

| Content type                                                                                                     | Target                                              |
|------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------|
| Terms, abbreviations, definitions valid across documents                                                         | PSI-TAD body                                        |
| Requirements                                                                                                     | PSI-REQ                                             |
| Interface definitions: endpoints, request and response fields, schemas                                           | PSI-ICD body                                        |
| Machine-readable artefacts: OpenAPI files, JSON schemas, examples                                                 | PSI-ICD external annex (zip)                        |
| How a task or operation uses the interfaces, including data mappings and worked examples                         | PSI-TOD body (task or operation)                    |
| Algorithm and method descriptions, reference papers, product data sheets, other human-readable supporting material | PSI-TOD appendix                                    |
| Governance and process rules: models, graduation criteria, review and contribution rules of the standard         | PSI-MADR decision record, category Governance       |
| Frontend guidance                                                                                                | PSI-GID                                             |
| Business cases and case studies                                                                                  | PSI-CST                                             |
| Documentation toolchain                                                                                          | PSI-DAC                                             |

Table: Placement of content types in the PSI document set. {#tbl:adr043-placement-rule}

Applied to the content waiting for a home:

* Matching engine interfaces and mappings: the interface definitions go into the ICD body; the mission-intent-to-filter and POQ-to-ranked-view mappings go into the TOD body under `TOD-03-Product_Inquiry_And_Ordering`; both reference the appendix for the algorithm.
* The whitepaper becomes the first TOD appendix, `TOD-A01`.
* Touchpoint mappings: they are field-level mappings between interfaces and go into the TOD body of the tasks they connect; a description of the matchmaking flow that spans them becomes an appendix only if it exceeds what the tasks can carry.
* Governance framework: one PSI-MADR record of category Governance.

### Form of a TOD Appendix

* **Location and naming.**
  One Markdown file per appendix under `doc/PSI/PSI-TOD/appendices/`, named `TOD-A<nn>-<Title_With_Underscores>.md`, numbered in order of admission; numbers are never reused.
  A new chapter `# Appendices` at the end of `doc/PSI/PSI-TOD/index.md` includes `appendices.md`, which lists every appendix as a level-2 heading `## TOD-A<nn>-<Title>` followed by its `@include`, mirroring `tasks_and_operations.md`.
  Headings inside an appendix start at level 3.
* **Standalone rendering.**
  Each appendix also has a start document `doc/PSI/PSI-TOD/appendix-a<nn>.md` in the form RHOD requires of a document (common metadata, metadata block with reference `PSI-TOD-A<nn>`, Document Signature Table, Document Change Record, reference documents, introduction), which includes the same appendix file under `# Appendix` / `## TOD-A<nn>-<Title>`.
  The RHOD playbook `aiv/rhod/rhod-playbook.sh` renders it as `PSI-TOD-A<nn>` with `--no-compare`, and the TOD lists it in an External Annexes table, as the ICD and the PSI-MADR list their annexes.
  The appendix text therefore contains no anchor links into the TOD body; it names tasks and operations by their identifier.
* **Source form.**
  Markdown with one sentence per line; diagrams as PlantUML source rendered by RHOD, never as binary documents.
  A binary or machine-readable file is an annex of the ICD, not an appendix of the TOD.
* **Mandatory preamble.**
  Every appendix opens with three short paragraphs before its first level-3 heading:
  1. *Scope against the TOD body*: which tasks or operations reference it, which of its parts are normative for them, and that everything else is informative.
     An appendix is informative unless this paragraph says otherwise, and a normative part is restated or referenced by the task or operation that relies on it, so the body remains the single normative entry point.
  2. *Origin and licence*: where the text comes from, who contributed it and under which licence; third-party material additionally gets its PSI-SLF entry.
  3. *Terminology*: the text uses TAD terms; a term it introduces is defined at first use and, where it collides with a TAD term, the collision is stated in a local terms table.
     A local term becomes a TAD term only by its own decision record, never by placement in an appendix.
* **No orphans.**
  An appendix that no task or operation references does not enter the document set.

### Review before a Text Enters the Document Set

1. An issue of type `doc-change` names the target (appendix number, or the decision record) and is the review record; branch and commits follow `CONTRIBUTING.md`, §3.
2. The pull request is reviewed by the Approver of the target document per its Document Signature Table (existing rule).
3. Content that originates outside the PSI document set, such as a whitepaper or a vendor data sheet, is additionally reviewed by a person from a consortium member other than the contributing organisation, reading it as someone who has to work from the document alone; for the first appendix, contributed and implemented by CGI, this is Amartus.
4. The reviewers check the preamble: scope and normative marking, licence and confidentiality (nothing confidential enters the public repository), and terminology against the TAD.
5. Every finding is resolved in the text or declined with its reason in the issue.
6. The Document Change Record of the target document gets its entry, and the Document Build workflow renders the document with the new chapter in the table of contents and without warnings before the pull request merges.
7. A governance record follows the ordinary PSI-MADR lifecycle from `Proposed` to `Accepted`.

## Compliance

* Every change that places such content references this record and names its target from the placement rule.
* The first appendix, the matchmaking whitepaper, is placed in the form above together with the `Appendices` chapter, `appendices.md`, the start document `appendix-a01.md` and the playbook entry `PSI-TOD-A01`.
* `CONTRIBUTING.md` gains the appendix path in §4, the placement rule and the additional reviewer in §6, and a checklist line for the appendix preamble; PSI-READFIRST's description of the TOD gains one sentence on appendices.
  Both changes are made in the pull request that places the first appendix.
* The Technical Officer checks, at each release, that no method or data-sheet text has entered the TAD, TOD or ICD body outside an appendix, and that every appendix has its preamble and at least one referencing task or operation.
* This record moves to `Accepted` when the Approvers of the PSI-MADR accept it; until then the tasks above may prepare their texts against it but do not merge them.

### Positive Consequences

* No new document, signature table, PSI-DL entry or build configuration is needed.
* Implementers find algorithm descriptions in the document they already read for tasks, in the same PDF and table of contents.
* The TOD body and the TAD stay normative and terminology-clean; supplementary vocabulary is contained in the appendix that introduces it.
* The governance framework gets consortium-level approval through the existing PSI-MADR process and a versioning mechanism through supersession.
* Third-party and externally written texts get a defined licence, confidentiality and independent review before they become part of the standard.

### Negative Consequences

* The TOD grows; a long appendix such as the whitepaper adds noticeably to its page count.
* Readers looking for "the governance document" must learn that it is the PSI-MADR; PSI-READFIRST has to say so.
* A governance framework spread over several decision records needs cross-references; this is the trigger for the deferred option G.
* Every appendix is rendered twice, inside the TOD and standalone, which adds to the build time of the Document Build workflow.

## Pros and Cons of the Options

### A. Section in the TAD Body

* Good, because the TAD is read first by everyone.
* Bad, because the TAD defines language, and a method text would make its own vocabulary canonical without decision.
* Bad, because the TAD has no notion of normative-versus-informative parts; everything in it is a definition.

### B. Section in the TOD Body

* Good, because the content sits next to the inquiry tasks it supports.
* Bad, because the TOD body is structured as categories, tasks and operations bound to endpoints and requirements; a method description fits none of the three templates.
* Bad, because body text is normative by convention, so the informative majority of the whitepaper would bind implementers.

### C. Appendices of the TOD

* Good, because implementers read the TOD, and an appendix is in the same PDF and table of contents.
* Good, because the appendix preamble makes normative parts explicit and keeps the rest informative.
* Good, because RHOD already stitches Markdown by `@include`; the chapter costs one file and one include.
* Good, because it mirrors the ICD annexes: material that belongs to a document without being its body.
* Bad, because the TOD grows in size.

### D. New Document PSI-ALG

* Good, because algorithm content would be found under its own name.
* Bad, because a new document needs a signature table, Approver, Document Change Record, PSI-READFIRST and PSI-DL entries and a build, for two appendices in the foreseeable future.
* Bad, because it separates the algorithm from the tasks that use it, so both documents must cross-reference.

### E. Governance Section in the TAD Body

* Bad, for the same reasons as A; a governance model is not terminology.

### F. Decision Record in the PSI-MADR

* Good, because the PSI-MADR declares management frameworks and processes in scope and is approved by both project managers.
* Good, because the template already separates the model (Decision Outcome) from its checks (Compliance), which is the split between the two-speed model and the graduation criteria.
* Good, because supersession versions the framework without a new document.
* Bad, because governance records interleave with technical ones in the generated index; the Category field is the only grouping.
* Bad, because a framework that grows beyond decisions needs extraction later (option G).

### G. New Document PSI-GOV

* Good, because it names the governance document literally and can be read standalone by SODA contributors.
* Bad, because of the same document cost as D, for two sections today.
* Bad, because it overlaps with PSI-SDP, the project-management document that is not maintained in this repository.

### H. Repository-Level Documentation

* Good, because `CONTRIBUTING.md` already describes how contributions are governed.
* Bad, because it is not part of the released document set, has no Approver, and its scope is the repository, not the standard's relation to TM Forum.

## Security Considerations

The options differ in one aspect only, confidentiality.
Every option places the text in a public repository under Apache 2.0, so the contributing organisation releases the text on contribution; the review step above checks that nothing confidential enters.
For the whitepaper this matters twice: it was shared internally before it was cited, and its subject is itself the non-disclosure of partner inventory, so the Amartus review confirms that the published text contains method, not partner data.
Integrity and availability are unaffected by placement; every option is under version control and built by the same CI.
