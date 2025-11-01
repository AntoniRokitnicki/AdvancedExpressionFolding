---
date: 2025-11-01T11:26:00.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "comparingLocalDatesCollapse feature"
tags: [research, codebase, feature, comparingLocalDatesCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: comparingLocalDatesCollapse feature

**Date**: 2025-11-01T11:26:00.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `comparingLocalDatesCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds java.time isBefore/isAfter checks into readable date comparisons. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `comparingLocalDatesCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/DateOperationsState.kt` defines or persists the `comparingLocalDatesCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `comparingLocalDatesCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/expression/PrefixExpressionExt.kt` references `comparingLocalDatesCollapse` within `PrefixExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/AfterDateMethodCall.kt` references `comparingLocalDatesCollapse` within `AfterDateMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/BeforeDateMethodCall.kt` references `comparingLocalDatesCollapse` within `BeforeDateMethodCall`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/DateOperationsFoldingTest.kt` exercises folding behavior tied to `comparingLocalDatesCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/expression/PrefixExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/AfterDateMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/BeforeDateMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/DateOperationsState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/DateOperationsFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
