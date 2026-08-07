# ADR-0002

Title: Adopt an incremental MVVM architecture

Status:
Accepted

Decision:
- Activities/Fragments own rendering.
- ViewModels own presentation logic.
- Repositories own data access.
- UserCases are deferred until justified by workflow complexity.

Principles:
- YAGNI
- High Cohesion
- Incremental Architecture
- Packages before Modules