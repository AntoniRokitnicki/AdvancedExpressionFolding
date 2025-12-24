---
date: 2025-11-01T11:26:35.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "pseudoAnnotations feature"
tags: [research, codebase, feature, pseudoAnnotations]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: pseudoAnnotations feature

**Date**: 2025-11-01T11:26:35.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `pseudoAnnotations` feature behave within Advanced Expression Folding?

## Summary
https://github.com/user-attachments/assets/53ad15ef-2c32-4fe4-a857-d36114d020aa Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `pseudoAnnotations` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt` defines or persists the `pseudoAnnotations` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `pseudoAnnotations` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/pseudo/AbstractLoggingAnnotationCompletionContributor.kt` references `pseudoAnnotations` within `AbstractLoggingAnnotationCompletionContributor`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt` references `pseudoAnnotations` within `MainAnnotationCompletionContributor`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt` exercises folding behavior tied to `pseudoAnnotations`.
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt` exercises folding behavior tied to `pseudoAnnotations`.
- `test/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributorTest.kt` exercises folding behavior tied to `pseudoAnnotations`.

## Code References
- `src/com/intellij/advancedExpressionFolding/pseudo/AbstractLoggingAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt`
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt`
- `test/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributorTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
