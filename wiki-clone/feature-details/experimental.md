---
date: 2025-11-01T11:26:09.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "experimental feature"
tags: [research, codebase, feature, experimental]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: experimental feature

**Date**: 2025-11-01T11:26:09.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `experimental` feature behave within Advanced Expression Folding?

## Summary
Enables experimental folding prototypes. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `experimental` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt` defines or persists the `experimental` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `experimental` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/PsiTryStatementExt.kt` references `experimental` within `PsiTryStatementExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt` references `experimental` within `MethodCallExpressionExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt` exercises folding behavior tied to `experimental`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `experimental`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/PsiTryStatementExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
