---
date: 2025-11-01T11:26:22.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "localDateLiteralPostfixCollapse feature"
tags: [research, codebase, feature, localDateLiteralPostfixCollapse]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: localDateLiteralPostfixCollapse feature

**Date**: 2025-11-01T11:26:22.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `localDateLiteralPostfixCollapse` feature behave within Advanced Expression Folding?

## Summary
Folds postfix LocalDate literals such as 2018Y-02M-12D. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `localDateLiteralPostfixCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/DateOperationsState.kt` defines or persists the `localDateLiteralPostfixCollapse` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `localDateLiteralPostfixCollapse` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/expression/literal/LocalDateLiteral.kt` references `localDateLiteralPostfixCollapse` within `LocalDateLiteral`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/CreateDateFactoryMethodCall.kt` references `localDateLiteralPostfixCollapse` within `CreateDateFactoryMethodCall`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/DateOperationsFoldingTest.kt` exercises folding behavior tied to `localDateLiteralPostfixCollapse`.

## Code References
- `src/com/intellij/advancedExpressionFolding/expression/literal/LocalDateLiteral.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/CreateDateFactoryMethodCall.kt`
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
