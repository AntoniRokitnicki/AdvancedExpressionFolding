---
date: 2025-11-01T11:26:37.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "semicolonsCollapse feature"
tags: [research, codebase, feature, semicolonsCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: semicolonsCollapse feature

**Date**: 2025-11-01T11:26:37.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `semicolonsCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds redundant semicolons inside read-only files. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `semicolonsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/UnclassifiedFeatureState.kt` defines or persists the `semicolonsCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `semicolonsCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt` references `semicolonsCollapse` within `IfExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/core/LexicalExpressionBuilders.kt` references `semicolonsCollapse` within `LexicalExpressionBuilders`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/UnclassifiedFeatureFoldingTest.kt` exercises folding behavior tied to `semicolonsCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt`
- `src/com/intellij/advancedExpressionFolding/processor/core/LexicalExpressionBuilders.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/UnclassifiedFeatureState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/UnclassifiedFeatureFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
