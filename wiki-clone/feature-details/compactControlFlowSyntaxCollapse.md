---
date: 2025-11-01T11:25:58.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "compactControlFlowSyntaxCollapse feature"
tags: [research, codebase, feature, compactControlFlowSyntaxCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: compactControlFlowSyntaxCollapse feature

**Date**: 2025-11-01T11:25:58.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `compactControlFlowSyntaxCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds compact if/else syntax inspired by Go. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `compactControlFlowSyntaxCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/ControlFlowState.kt` defines or persists the `compactControlFlowSyntaxCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `compactControlFlowSyntaxCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachIndexedStatement.kt` references `compactControlFlowSyntaxCollapse` within `ForEachIndexedStatement`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachStatement.kt` references `compactControlFlowSyntaxCollapse` within `ForEachStatement`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForStatement.kt` references `compactControlFlowSyntaxCollapse` within `ForStatement`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt` references `compactControlFlowSyntaxCollapse` within `IfExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/ForStatementExpressionExt.kt` references `compactControlFlowSyntaxCollapse` within `ForStatementExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt` references `compactControlFlowSyntaxCollapse` within `IfExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/LoopExt.kt` references `compactControlFlowSyntaxCollapse` within `LoopExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/core/ControlFlowExpressionBuilders.kt` references `compactControlFlowSyntaxCollapse` within `ControlFlowExpressionBuilders`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ControlFlowFoldingTest.kt` exercises folding behavior tied to `compactControlFlowSyntaxCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachIndexedStatement.kt`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachStatement.kt`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForStatement.kt`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/ForStatementExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/LoopExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/core/ControlFlowExpressionBuilders.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/ControlFlowState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ControlFlowFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
