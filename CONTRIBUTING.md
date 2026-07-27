# Contributing to PSI

Thank you for your interest in contributing to the **Pooling & Sharing Interface Definition Project (PSI)**.
PSI is an ESA-funded consortium initiative defining a common standard for the interfaces between Pooling & Sharing Systems (PSS) in the SatCom domain, built on TM Forum's Open Digital Framework.
This document describes how to get access, how contributions are governed, and where things live in the repository.

## 1. Before You Start

Read **[PSI-READFIRST](doc/PSI/PSI-READFIRST/index.md)** first.
It lists the full PSI document set and the suggested reading order depending on your angle of contribution — interface implementation, project management, or general understanding.
Everything below assumes you have at least skimmed it.

The standard documents maintained in this repository (under `doc/PSI/`) are:

| Reference | Title | Source directory |
|---|---|---|
| PSI-READFIRST | Read-Me-First (document list, reading order) | `doc/PSI/PSI-READFIRST/` |
| PSI-TAD | Terms, Abbreviations and Definitions | `doc/PSI/PSI-TAD/` |
| PSI-REQ | Requirements | `doc/PSI/PSI-REQ/` |
| PSI-TOD | Tasks and Operations Dictionary | `doc/PSI/PSI-TOD/` |
| PSI-MADR | Markdown Administrative Decision Records | `doc/PSI/PSI-ADR/` |
| PSI-ICD | Interface Control Document (incl. OpenAPI definitions) | `doc/PSI/PSI-ICD/` |
| PSI-SLF | Software License File | `doc/PSI/PSI-SLF/` |
| PSI-CST | Case Study | `doc/PSI/PSI-CST/` |
| PSI-DAC | Documentation as Code (the documentation toolchain itself) | `doc/PSI/PSI-DAC/` |
| PSI-GID | Graphical Interface Definitions | `doc/PSI/PSI-GID/` |

Some documents referenced in PSI-READFIRST (PSI-SDP, PSI-RR, PSI-VVP) are project-management documents whose sources are not maintained in this repository.

Two documents are particularly relevant before you write anything:

- **PSI-TAD** — defines the terminology used across the project.
  Read this before opening an issue or PR so we share vocabulary.
- **PSI-MADR** — the decision records.
  They govern repository structure, documentation tooling, and mock-up implementation choices; if you're unsure why something is organised the way it is, check `doc/PSI/PSI-ADR/Accepted/` before proposing a restructure.

## 2. Requesting Development Environment Access

Access to the PSI development environment is granted on request, not self-service.

To request access, email **Hendrik Oppenberg** (Technical Officer) or **Wolfgang Robben** (Project Manager):

- Hendrik Oppenberg — [hendrik.oppenberg@cgi.com](mailto:hendrik.oppenberg@cgi.com)
- Wolfgang Robben — [wolfgang.robben@cgi.com](mailto:wolfgang.robben@cgi.com)

Please include in your email:

1. Your name and affiliation (company/organisation).
2. The area you intend to work on (e.g. a specific interface, the mock-up implementation, a specific PSI-* document).
3. A link to an existing issue, if one already exists for the work.

You will receive confirmation once access has been set up.

## 3. Contribution Workflow

1. **Check open issues** first to avoid duplicating or conflicting with planned work.
2. **Open an issue** describing what you plan to address before starting substantial work.
   This applies especially to anything touching architecture (PSI-MADR-relevant) or the standard documents themselves — smaller fixes (typos, minor clarifications) can go straight to a PR referencing a new or existing issue.
3. **Wait for maintainer acknowledgement** on non-trivial issues before investing significant effort, so we can flag overlaps or conflicts with the roadmap early.
4. **Branch and implement.**
   Reference the issue number in your branch name and commits.
5. **Open a pull request** against `main`, referencing the issue it resolves.
   The Main CI builds draft PDFs of the documents, so reviewers can inspect the rendered result.
6. **Review and sign-off.**
   - Code changes: reviewed by a maintainer for the affected module.
   - Changes to a PSI-* standard document: reviewed by that document's Approver as listed in its **Document Signature Table** (see the table at the top of each document, e.g. PSI-READFIRST).

### Branch and commit naming

The issue number is the traceability anchor for both:

- **Branches:** `<type>/<issue-number>-<short-slug>`, e.g. `feat/142-icd-order-endpoint`, `doc/156-tad-glossary-update`, `fix/160-adr-broken-link`.
- **Types:** `doc` (documentation — PSI-* standard documents under `doc/PSI/` *or* repository-level docs such as this file), `feat` (new functionality), `fix` (bug fix), `chore` (build/tooling), `refactor` (restructuring without functional or content change).
  - Review routing for `doc` depends on the path: changes under `doc/PSI/` go to that document's Approver (per its Document Signature Table); repository-level docs go to a general maintainer.
- **Commits:** [Conventional Commits](https://www.conventionalcommits.org/) style (`type: summary`), with `Refs #<issue-number>` in the footer.

The type prefix isn't just labelling — it mirrors the review routing above, so anyone scanning open branches or PRs can tell at a glance whether a document Approver or a module maintainer is the right reviewer.
Not enforced by tooling yet; treat it as convention until contributor volume justifies a commit-lint check.

### Pull request checklist

- [ ] References the issue it addresses.
- [ ] For changes to a PSI-* document: added a new entry to the document's Document Change Record (the `dcr_overrides` list in the metadata block of its `index.md`) with your name and a summary of the change.
- [ ] For changes introducing third-party material: updated **PSI-SLF** with the corresponding licence information.
- [ ] Edits target hand-written sources, not generated files (see §6).

## 4. Repository Structure

| Path | Contents |
|---|---|
| `doc/PSI/` | Source of the PSI-* standard documents (see §1), including shared fragments under `doc/PSI/common/`. |
| `doc/PSI/PSI-ICD/open-apis/` | The PSI OpenAPI definitions — the machine-readable core of the standard. |
| `tmforum/` | TM Forum Open Digital Framework source APIs and schemas, from which the PSI APIs are derived (see `LICENSE` / PSI-SLF for attribution). |
| `mef/` | MEF source APIs, likewise inputs to the PSI API transformation (see `LICENSE` / PSI-SLF for attribution). |
| `source/` | Implementations validating the defined interfaces: the Java mock-up (`common/`, `psid-mockup/`, both Gradle subprojects), the Go `mission-management-backend/`, the Svelte/TypeScript `mission-management-frontend/`, plus `docker/`, `mongodb/` and a Helm chart for running them. See `source/README.md`. |
| `buildSrc/` | Custom Gradle tasks (Groovy) for API transformation and documentation generation. |
| `aiv/` | The RHOD render pipeline (`aiv/rhod/`) that stitches the Markdown sources and renders them to PDF; used by CI. |
| `.github/workflows/` | CI: document builds on PRs and `main` (draft PDFs), release builds on tags publishing the PDFs as release assets. |

There is no checked-in build output.
The rendered standard documents are published as assets of each [GitHub release](https://github.com/psi-consortium/psi/releases).

## 5. Building the Project

The root project is Gradle-based and drives API transformation, the Java mock-up, and documentation generation:

```
./gradlew build       # build and test the Java mock-up (source:common, source:psid-mockup)
./gradlew generate    # regenerate derived documentation content (see §6)
```

The mission-management backend and frontend are built separately — the Go backend via its `Makefile` / `go run`, the frontend via `pnpm` — see the `README.md` in each module under `source/`.

PDF rendering of the standard documents is done by the RHOD pipeline (`aiv/rhod/rhod-playbook.sh`).
CI runs it in a dedicated container image on a self-hosted runner; you normally don't run it locally — open a PR and let the Document Build workflow produce the draft PDFs.
**PSI-DAC** documents the documentation-as-code toolchain in detail.

## 6. Contributing to Standard Documents (PSI-*)

Each PSI-* document is composed from Markdown sources under `doc/PSI/<DOCUMENT>/` via `@include` references, with shared fragments in `doc/PSI/common/` (metadata, introduction, development state, release notes, document change record).
When editing:

- Edit hand-written sources only.
  Some files under `doc/` are **generated** by `./gradlew generate` and will be overwritten: the REST API schema documentation under `doc/PSI/PSI-ICD/schema/`, the ADR indexes (`list-of-decisions.md` in each `doc/PSI/PSI-ADR/<Status>/` folder), and the TOD/REQ reference files.
  If you change OpenAPI definitions or requirements, re-run `./gradlew generate` and commit the regenerated files along with your change.
- Add an entry to the `dcr_overrides` list in the metadata block of the document's `index.md`, with your name, date range, version, and a one-line summary of the change.
- New decision records start from `doc/PSI/PSI-ADR/ID-decision-template.md` and go into `doc/PSI/PSI-ADR/Proposed/`; they move to `Accepted/` (or `Rejected/` etc.) as the consortium decides.
- Standard documents are written in **British English**.
  This file follows the same convention.
- Substantive changes require sign-off from the document's Approver(s) per its Document Signature Table.

## 7. Coding Conventions

Not yet formalised across all modules.
Follow the existing style within the file or module you're touching, and check the module's `README.md` (e.g. `source/mission-management-backend/README.md`) for module-specific tooling.
For the Java mock-up, note that large parts are generated from the OpenAPI definitions (`source/generated/` is not checked in) — change the OAS sources or the transformation tasks in `buildSrc/`, not the generated code.

## 8. Licensing

PSI is licensed under **Apache License 2.0** (© THE PSI CONSORTIUM).
Per §5 of the licence, any contribution you submit is licensed under the same terms by default.
PSI was commissioned by ESA as an open source initiative — there is no separate IP or contributor agreement outside this licence.

Portions of PSI are adapted from the TM Forum and MEF projects — see `PSI-SLF` for details.
If your contribution incorporates material from elsewhere, check licence compatibility and update `PSI-SLF` accordingly.

**Proprietary implementations.**
Some consortium members maintain proprietary implementations of PSI interfaces for testing and demonstration purposes.
These are not part of this repository; at most, this repository documents their existence or interface points, never their source code.
Do not submit proprietary or confidential material as part of a contribution.

## 9. Conduct

Be respectful and constructive in issues, reviews, and discussions.
Disagreements about architecture or interface design are expected and welcome — keep them technical.

## 10. Contact

For anything not covered here, or for topics not suited to a public issue (e.g. IP-sensitive matters), contact Hendrik Oppenberg or Wolfgang Robben (see §2).
