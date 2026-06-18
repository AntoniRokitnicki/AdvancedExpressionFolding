---
date: 2025-11-01T11:26:06.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "destructuring feature"
tags: [research, codebase, feature, destructuring]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: destructuring feature

**Date**: 2025-11-01T11:26:06.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `destructuring` feature behave within Advanced Expression Folding?

## Summary
![Array destructuring without var/val folded inline](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/3391702f-8632-4539-9e81-60f52f7ee006) Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `destructuring` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt` defines or persists the `destructuring` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `destructuring` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiDeclarationStatementExt.kt` references `destructuring` within `PsiDeclarationStatementExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `destructuring`.
- `test/com/intellij/advancedExpressionFolding/folding/base/full/FullFoldingTest.kt` exercises folding behavior tied to `destructuring`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `destructuring`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiDeclarationStatementExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/full/FullFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
