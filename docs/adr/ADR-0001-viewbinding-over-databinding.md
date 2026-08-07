# ADR-0001

Title: Adopt ViewBinding as the initial UI binding mechanism

Status:
Accepted

Context:
AndroidForge is intended as a project to deeply understand the Android framework while building reusable infrastructure.

Alternatives:
- findViewById()
- DataBinding
- Jetpack Compose

Decision:
Use ViewBinding for all XML-based screens.

Rationale:
- Type-safe view access
- Minimal build overhead
- Keeps UI logic in Kotlin
- Allows learning the classic Android View system
- Compose can be evaluated later if project goals evolve

Consequences:
+ Simpler generated code
+ Faster builds than DataBinding
+ Easier debugging
- XML-based UI remains
- Future migration to Compose may require UI rewrites