---
date: 2025-11-01T11:25:59.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "comparingExpressionsCollapse feature"
tags: [research, codebase, feature, comparingExpressionsCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: comparingExpressionsCollapse feature

**Date**: 2025-11-01T11:25:59.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `comparingExpressionsCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds equals and compareTo calls into direct comparison expressions. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `comparingExpressionsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt` defines or persists the `comparingExpressionsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `comparingExpressionsCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/expression/BinaryExpressionExt.kt` references `comparingExpressionsCollapse` within `BinaryExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/expression/PrefixExpressionExt.kt` references `comparingExpressionsCollapse` within `PrefixExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/EqualsMethodCall.kt` references `comparingExpressionsCollapse` within `EqualsMethodCall`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt` exercises folding behavior tied to `comparingExpressionsCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/expression/BinaryExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/expression/PrefixExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/EqualsMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
