# Release Notes

[[_TOC_]]

---

# PSI Release Notes

## Introduction

Welcome to the latest release of the Pooling and Sharing Interface (PSI) API!  
This document outlines the new features, improvements, and important updates included in this version.

## Key Highlights

The central focus of this release is the implementation of the **Mission Management ODA Blueprint**.  
This component complements the mission-related APIs by providing a *reference implementation of graphical user interfaces* that help users specify their product and service requirements.

Designed with users in mind, this component uses templates to simplify mission creation and introduces a governance layer to facilitate and control the requirements collection process.  
It's built as a standalone micro-frontend and can be easily integrated into existing OSS/BSS/PSS systems.

The interface includes multiple visualization modes:

* **Time-Based View** (e.g., mission timeline, Gantt charts — for understanding *when* something is needed)
* **Geographical View** (e.g., mission areas or network nodes on a map — for showing *where* resources are needed)
* **Logical View** (e.g., communication dependency graphs — for depicting *how* requirements relate to each other)

Another major update in this release is the migration to **TM Forum APIs Version 5 (TMF5)**.  
All APIs have been ported to the current TMF baseline.  
However, TMF5 introduced some gaps in the Component Test Kit (CTK), resulting in partial test coverage for certain APIs. This limitation will be addressed once TM Forum updates the CTK.

Additionally, this release introduces **MEF-compatible APIs**, marking the beginning of convergence between MEF and TMF frameworks within PSI.  
Our goal moving forward is to support both API standards in their respective areas.

## What's New

* **[PSI-GID]**: Describes the ODA component for mission management  
* **`\mef`**: Directory with initial MEF-compliant APIs  
* **`\psid-mockup`**: Contains a prototype of the ODA component  

### Newly Added APIs

* **[MEFW143]**: Performance Monitoring

### Updated APIs

* All APIs have been updated to **TM Forum Version 5**.

### Added Requirements

* None

## Known Limitations

* The **Service Quality Management** functionality is still in its early stages, though improvements have been made.  
  We are currently aligning these APIs with the outcomes of an ongoing TM Forum Catalyst project, expected to conclude in June.

## Feedback and Contributions

We appreciate your input!  
If you experience any issues or have suggestions, please don’t hesitate to contact us.  
We also encourage community contributions to help enhance PSI further.
