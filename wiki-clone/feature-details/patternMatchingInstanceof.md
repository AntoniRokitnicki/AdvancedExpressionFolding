---
date: 2025-11-01T11:26:33.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "patternMatchingInstanceof feature"
tags: [research, codebase, feature, patternMatchingInstanceof]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: patternMatchingInstanceof feature

**Date**: 2025-11-01T11:26:33.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `patternMatchingInstanceof` feature behave within Advanced Expression Folding?

## Summary
Applies pattern matching to `instanceof` checks for more concise and readable code. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `patternMatchingInstanceof` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt` defines or persists the `patternMatchingInstanceof` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `patternMatchingInstanceof` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt` references `patternMatchingInstanceof` within `IfExpression`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `patternMatchingInstanceof`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `patternMatchingInstanceof`.

## Code References
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt`
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
