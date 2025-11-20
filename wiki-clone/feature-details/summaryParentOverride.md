---
date: 2025-11-01T11:26:40.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "summaryParentOverride feature"
tags: [research, codebase, feature, summaryParentOverride]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: summaryParentOverride feature

**Date**: 2025-11-01T11:26:40.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `summaryParentOverride` feature behave within Advanced Expression Folding?

## Summary
Folds overridden methods into parent summary stubs. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `summaryParentOverride` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/HidingSuppressionState.kt` defines or persists the `summaryParentOverride` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `summaryParentOverride` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiClassExt.kt` references `summaryParentOverride` within `PsiClassExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt` references `summaryParentOverride` within `PsiMethodExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/HidingSuppressionFoldingTest.kt` exercises folding behavior tied to `summaryParentOverride`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `summaryParentOverride`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiClassExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/HidingSuppressionState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/HidingSuppressionFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
