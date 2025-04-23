=begin
[[_TOC_]]
=end

@include [common meta information like version docdate etc..](../common/common_metadata.md)

=begin metadata
title: "PSI Documentation as Code"
subtitle: "PSI-DAC"
reference: "PSI-DAC"
---
dcr_overrides:
 - dcr:
   from: '2022-01-01'
   to: '2025-04-30'
   version: 'MS11 [1.3.0]'
   author: 'Christine Glaesser'
   message: 'Initial version'
=end

# Document Meta Information

## Document Signature Table

|           | Name               | Function                       | Company         |
| --------- | ------------------ | ------------------------------ | --------------- |
| Author    | Wolfgang Robben    | Project Manager                | CGI             |
| Author    | Hendrik Oppenberg  | Technical Officer              | CGI             |
| Author    | Christine Glaesser | Liaison Manager                | CGI             |
| Approval  | Victoria McCarthy  | Project Manager                | SES             |
| Checked   | Pepijn Witte       | Quality Assurance Manager      | CGI             |

Table: Signature Table. {#tbl:signature_table}

## Document Change Record

### Changes

@dcr(dcr_table)

Table: Document Change Record. {#tbl:dcr}

### Source Control

Changes to this document are tracked electronically.
No signature is required by the authors.
The following information can prove the integrity of the document and reveal any change.

@dcr(git_table)

Table: Git log. {#tbl:git_log}

@dcr(git_qr_code){#fig:dcr_qr_code}

## Documents

### Reference Documents

| Acronym       | Reference  | Title                                    | Version                    |
|---------------|------------|------------------------------------------|----------------------------|
| PSI-TAD       | PSI-TAD    | Terms, Abbreviations and Definitions     | see before                 |

Table: Reference Documents {#tbl:reference-documents}

# Introduction

@include [common introduction](../common/intro_description.md)

## Document Scope

This document describes the workflows and technologies around *Documentation-as-Code* pipeline and Requirements Traceability Matrix (RTM) applied within the PSI project.
The document will outline the benefits of handling documentation in such a way, as well as the general outline and outlook for potential adaptation to future projects.

@include [generated document warning](../common/generated_document.md)

@include [development_state](../common/development_state.md)

# Software Documentation

Each software product is developed following a design phase, which captures the customer's intent of using the finalised product.
The documentation of the actual implementation shall explain how the software works, how it should be used and how it shall be maintained.
This can include user documentation, e.g. guides and manuals for end-users, as well as technical documentation for developers.
The technical documents may include the software's architecture, API documentation and details how to interact with the system in an automated manner.
Also, third-party services included in the produced software and maintenance documentation like troubleshooting guides may be described in a technical documentation. Well-written documentation offers a vast amount of benefits, to name some examples:

1. Clarification on Software usage: promoting the understanding of installation, configuration and usage of software
2. Ensuring compliance and legal requirements: especially for health-care, documentation is legally required to meet regulatory standards (e.g. GAMP5)
3. Enhancing collaboration and knowledge sharing: well-written documentation ensures that all team members and newly onboarded members have a common understanding
   of the software's architecture and intention
4. Improvement of maintenance: up-to-date documentation facilitates assessment of needed bug fixes or new features, and facilitates handovers
5. Consistency in development: a standardized approach to documentation ensures that developers follow conventions and best practices

Thus, well-written documentation helps end-users and software developers to use the software effectively and maintain it for long-term success.

Software documentation in the Space domain is especially critical as the systems and missions are complex and high-risk, also from cost perspective.
Therefore, strict standards for both software development and documentation exist and are followed such as ISO 9001 or ECSS (European Cooperation for Space Standardization).
These standards ensure the reliability, maintainability and security of the space software being developed, with a strong emphasis on clear traceability from requirements
to final implementation and verification.

Different toolings have been established for space software related documentation.
Among those are Configuration Management Systems like Git or Subversion, taking care of version control of both code and documentation, or Document Management Systems like Confluence.
Model-Based Development Tools like SysML are often applied to document software behaviour, overall architecture or system interactions.
For requirements tracing and verification, IBM's DOORS is a well-known application.
DOORS also allows to customize settings, e.g. uploading Independent Verification and Validation (IVV) Test documents, to facilitate requirements tracing and verification.

## Known issues for Software Documentation

While sound, well-written software documentation offers a vast amount of benefits as outlined above, it can be a challenge to keep the developed software and accompanying documentation on the same page.
Especially in agile projects, feature development and its focus might change over the course of the project.
Software documentation should be done while the feature is developed, however, as deadlines come closer, documentation is often shifted to the last minutes.
Thus, software documentation is often error-prone as the actual development diverges from the initial intended feature implementation.
As outlined above, different toolings exist for different goals of documentation.
This represents an obstacle for developers, as they have to switch context and environment for documentation, which is emphasized once the deadline for feature development approaches.
As result, documentation on software is often perceived as (annoying) add-on to existing software solutions, a nice-to-have to fulfill requirements.
While updates done on software are done in an automated way via CI pipelines, are not flown down to documentation in same manner.
As a result, documentation is often not in best state and very quickly outdated as new features are being developed.

To mitigate those issues, the idea to treat documentation in same manner as code was born.

# The concept of DaC

The concept of Documentation-as-Code (DaC) was introduced in the mid-2010s.
It aimed at treating documentation as integral part of the codebase, applying same principles (e.g. code review to documentation review) and tools to documentation as is done for code.
Although the idea was recognised earlier, the approach has become more common as software development practices have shifted towards more agile methodologies and the increasing adoption of version control systems like Git, growing popularity of plain text such as Markdown and the adoption of Continuous Integration and Continuous Delivery (CI/CD).
Since 2022, DaC is an established practice in the software development community [^1], particularly in DevOps, agile and open-source communities.

## Benefits

As outlined, software documentation that is not included in the codebase suffers from specific defects.

DaC can mitigate those by streamlining the workflows for both code creation and documentation generation.
The environments usually used for coding, e.g. Eclipse on Linux or Visual Studio Code on Windows, allow to write files in Markdown native.
Specific libraries, easily plugged in, allow the developer to stay in their well-known coding environment, while writing documentation with 
IntelliSense support in Markdown.
This treatment renders documentation as artifact, just like implemented code.

Additionally, it is beneficial to inspect the documentation of a feature alongside the existing code, allowing to spot differences and adopt small, targeted changes to documentation.
Including the resulting documentation, such as Markdown files, in code commits can address the aforementioned discrepancy between the codebase and documentation. 
Additionally, implementing regulations, such as checking the peer review before merging, ensures that the documentation is updated for specific features. 
Thus, DaC helps to mitigate the issue of a mismatch between the codebase and documentation.

Once the documentation has been transferred to a format that allows for automation, the automated formatting of the documentation to the customer's desired specifications is just one click away.
This can speed up delivery time; while it is usually a significant amount of (manual) time to correctly convert documentation to another format, automating such via CI/CD pipelines like offered by GitHub and GitLab can reduce such delivery time.
Thus, the delivery can be done with codebase and documentation in any format side by side.

Treating documentation as artifacts just like code allows for easy version control as well.
Just like code, documents can then be tracked, reviewed, and rolled back without losing important information.
If implemented in, for example, a Markdown or subsequent rendering flag, the version the documentation has been generated on can also be included in the document, e.g. in form of a Git hash code.
This allows for a very convenient way of reviewing that the documentation at hand is matching the codebase, comparing e.g. the Git hash.

Traditionally, documentation is written and maintained by technical writers.
This demands close collaboration between developmental team and the writing board.
If not given, documentation might not match the actual feature implementation or, worse, might contain wrong information which will impact future development and maintenance activities.
If however documentation is part of development and treated like any other software artifact, developers are encouraged to contribute to documentation and maintain such.
The technical writers can then focus on the overall quality rather than content.

In addition, if documentation is part of the repository, each team member easily has access to the up-to-date documentation.
This facilitates onboarding new team members and knowledge sharing.

Overall, DaC emphasizes collaboration, transparency and automation.

# DaC application in PSID
@include [RHOD](./rhod.md)

# Automation of requirements traceability
@include [AITF](./aitf.md)

# Automated Generation of OpenAPI Specification files
@include [Automated Generation of OpenAPI Specification files](./oas-transformation.md)

# Large Language Models for document generation
@include [LLM](./llm.md)

# Automation of document generation - Conclusion
@include [AutomationOutlook](./outlook.md)

[^1]: See https://daniel-woste.de/posts/2024/Documentation_as_Code_in_Automotive_System_Software_Engineering.html
