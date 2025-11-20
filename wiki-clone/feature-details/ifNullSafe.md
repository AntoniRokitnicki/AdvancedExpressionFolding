---
date: 2025-11-01T11:26:18.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "ifNullSafe feature"
tags: [research, codebase, feature, ifNullSafe]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: ifNullSafe feature

**Date**: 2025-11-01T11:26:18.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `ifNullSafe` feature behave within Advanced Expression Folding?

## Summary
[video](https://www.youtube.com/watch?v=zvpvhn7ISAw) Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `ifNullSafe` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt` defines or persists the `ifNullSafe` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `ifNullSafe` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafeExt.kt` references `ifNullSafe` within `IfNullSafeExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt` references `ifNullSafe` within `IfNullSafePrintlnExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `ifNullSafe`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `ifNullSafe`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafeExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
