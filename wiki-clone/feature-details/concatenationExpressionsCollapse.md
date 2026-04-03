---
date: 2025-11-01T11:26:01.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "concatenationExpressionsCollapse feature"
tags: [research, codebase, feature, concatenationExpressionsCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: concatenationExpressionsCollapse feature

**Date**: 2025-11-01T11:26:01.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `concatenationExpressionsCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds StringBuilder append chains, collection add/remove calls, interpolated strings, and stream collectors into compact expressions. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `concatenationExpressionsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt` defines or persists the `concatenationExpressionsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `concatenationExpressionsCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt` references `concatenationExpressionsCollapse` within `IfExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/AppendMethodCall.kt` references `concatenationExpressionsCollapse` within `AppendMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/CharAtMethodCall.kt` references `concatenationExpressionsCollapse` within `CharAtMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionAddAllMethodCall.kt` references `concatenationExpressionsCollapse` within `CollectionAddAllMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionAddMethodCall.kt` references `concatenationExpressionsCollapse` within `CollectionAddMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionRemoveAllMethodCall.kt` references `concatenationExpressionsCollapse` within `CollectionRemoveAllMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionRemoveMethodCall.kt` references `concatenationExpressionsCollapse` within `CollectionRemoveMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionStreamMethodCall.kt` references `concatenationExpressionsCollapse` within `CollectionStreamMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/stream/StreamCollectMethodCall.kt` references `concatenationExpressionsCollapse` within `StreamCollectMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/stream/StreamMethodCall.kt` references `concatenationExpressionsCollapse` within `StreamMethodCall`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/CollectionsStreamsFoldingTest.kt` exercises folding behavior tied to `concatenationExpressionsCollapse`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt` exercises folding behavior tied to `concatenationExpressionsCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/AppendMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/CharAtMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionAddAllMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionAddMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionRemoveAllMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionRemoveMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionStreamMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/stream/StreamCollectMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/stream/StreamMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/CollectionsStreamsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
