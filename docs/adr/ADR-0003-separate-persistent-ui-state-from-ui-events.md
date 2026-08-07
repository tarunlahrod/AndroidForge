# ADR-0003

Title: Separate UI state from UI events

Status:
Accepted

Context:
The presentation layer must communicate two fundamentally different kinds of information to the UI:
1. Persistent State — information that describes the current UI and should survive configuration changes and UI recreation.
2. One-Time Events — occurrences that should be consumed exactly once and should not be replayed when the UI is recreated.

Alternatives:
- LiveData
- SingleLiveEvent

Decision: AndroidForge models UI as a pure function of persistent state. One-time occurrences (such as navigation, snackbars, and toasts) are modeled separately as events and are never encoded as persistent UI state.

