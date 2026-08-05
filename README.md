# AndroidForge

> A production-grade Android framework built from scratch to explore modern Android architecture, reusable libraries, and engineering best practices.

---

## The philosophy for AndroidForge

Every piece of code added to this repository should satisfy the following principles.
- Reusable
- Scalable
- Production Ready
- Learn Before Building
- Simplicity Over Cleverness
- Modular by Default

---

## Guiding Principles

- Kotlin First
- MVVM Architecture
- Coroutines
- StateFlow & SharedFlow
- Dependency Injection
- Multi-module Architecture
- Material Design 3
- SOLID Principles
- Clean Code
- Composition over Inheritance
- Feature-first Design
- Testability
- CI/CD from Day One

---

# Roadmap

The project will be developed incrementally.

Each milestone focuses on understanding the underlying concepts before implementation.

---

## Milestone 1 — Foundation

- Project cleanup
- Gradle structure
- Version Catalog
- Package conventions
- Build configuration
- Coding standards
- Hilt setup

---

## Milestone 2 — Architecture

- BaseActivity
- BaseFragment
- BaseViewModel
- BaseDialog
- UI state management
- Error handling
- Resource wrapper

---

## Milestone 3 — Design System

- Colors
- Typography
- Spacing
- Shapes
- Theme
- Animations
- Icons

---

## Milestone 4 — UI Kit

Reusable UI components including

- Buttons
- Toolbars
- Dialogs
- Snackbars
- Bottom Sheets
- Loading Views
- Error Views
- RecyclerView utilities

---

## Milestone 5 — Network Kit

- Retrofit
- OkHttp
- Authentication
- Interceptors
- Logging
- API wrappers
- Error parser
- Connectivity observer
- Retry mechanism

---

## Milestone 6 — Recycler Framework

A generic RecyclerView framework supporting

- DiffUtil
- Multiple View Types
- Headers & Footers
- Pagination
- Empty States
- Payload Updates
- Click Handling
- Swipe & Drag
- Selection

---

## Milestone 7 — Navigation

Explore and compare

- Navigation Component
- Custom Navigator
- Deep Links
- Feature Navigation
- Back Stack Management

---

## Milestone 8 — Storage

- Room
- DataStore
- Encrypted Storage
- Preferences
- Caching

---

## Milestone 9 — Utilities

- Logger
- Extensions
- Permission Manager
- Keyboard Utilities
- Clipboard
- Date & Time
- Image Loading

---

## Milestone 10 — DevOps

- GitHub Actions
- Static Analysis
- Lint
- Detekt
- Unit Tests
- Fastlane
- Automated Releases

---

## Milestone 11 — Documentation

Every module should contain

- README
- Architecture
- Public API
- Usage Examples
- Diagrams
- Screenshots

---

# Future Vision

The long-term objective is to split AndroidForge into independent Gradle modules that can be consumed by any Android project.

For example,

```
implementation(project(":core-ui"))
implementation(project(":core-network"))
implementation(project(":core-navigation"))
implementation(project(":core-storage"))
```

Eventually, these modules may also be published as standalone libraries.

---

# Engineering Rules

Before merging any feature, ask:

- Is it reusable?
- Is it scalable?
- Is it production ready?
- Is it documented?
- Is it testable?

If the answer is "No" to any question, revisit the implementation.

---

# Learning Goals

AndroidForge exists to deepen understanding of Android engineering, not just Android development.

Topics include

- Android Architecture
- Dependency Injection
- Build Systems
- Gradle
- Modularization
- Reactive Programming
- Performance
- Memory Management
- Design Systems
- CI/CD
- Software Engineering Principles

---

# License

This repository is currently intended as a personal engineering project and learning resource.
