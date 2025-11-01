---
date: 2025-11-01T11:26:30.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "nullable feature"
tags: [research, codebase, feature, nullable]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: nullable feature

**Date**: 2025-11-01T11:26:30.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `nullable` feature behave within Advanced Expression Folding?

## Summary
Folds nullability annotations into ? and !! markers. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `nullable` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt` defines or persists the `nullable` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `nullable` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt` references `nullable` within `PsiFieldExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/NullableExt.kt` references `nullable` within `NullableExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/InterfacePropertiesExt.kt` references `nullable` within `InterfacePropertiesExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/nullable/CheckNotNullMethodCall.kt` references `nullable` within `CheckNotNullMethodCall`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt` exercises folding behavior tied to `nullable`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `nullable`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt` exercises folding behavior tied to `nullable`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `nullable`.
- `examples/data/InterfaceExtensionPropertiesTestData.java` provides example input demonstrating `nullable`.
- `folded/InterfaceExtensionPropertiesTestData-folded.java` shows folded output illustrating `nullable`.

## Code References
- `examples/data/InterfaceExtensionPropertiesTestData.java`
- `folded/InterfaceExtensionPropertiesTestData-folded.java`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/NullableExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/InterfacePropertiesExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/nullable/CheckNotNullMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
