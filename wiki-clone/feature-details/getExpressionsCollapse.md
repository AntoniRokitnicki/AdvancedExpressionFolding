---
date: 2025-11-01T11:26:15.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "getExpressionsCollapse feature"
tags: [research, codebase, feature, getExpressionsCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: getExpressionsCollapse feature

**Date**: 2025-11-01T11:26:15.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `getExpressionsCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds collection access and literal builders into indexed or map-style expressions. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `getExpressionsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt` defines or persists the `getExpressionsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `getExpressionsCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/core/ExpressionBuilders.kt` references `getExpressionsCollapse` within `ExpressionBuilders`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/expression/PsiArrayAccessExpressionExt.kt` references `getExpressionsCollapse` within `PsiArrayAccessExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/ArraysListMethodCall.kt` references `getExpressionsCollapse` within `ArraysListMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionGetMethodCall.kt` references `getExpressionsCollapse` within `CollectionGetMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionsUnmodifiableListMethodCall.kt` references `getExpressionsCollapse` within `CollectionsUnmodifiableListMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionsUnmodifiableSetMethodCall.kt` references `getExpressionsCollapse` within `CollectionsUnmodifiableSetMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/MapPutMethodCall.kt` references `getExpressionsCollapse` within `MapPutMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/reference/NewExpressionExt.kt` references `getExpressionsCollapse` within `NewExpressionExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt` exercises folding behavior tied to `getExpressionsCollapse`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt` exercises folding behavior tied to `getExpressionsCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/core/ExpressionBuilders.kt`
- `src/com/intellij/advancedExpressionFolding/processor/expression/PsiArrayAccessExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/ArraysListMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionGetMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionsUnmodifiableListMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionsUnmodifiableSetMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/MapPutMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/reference/NewExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
