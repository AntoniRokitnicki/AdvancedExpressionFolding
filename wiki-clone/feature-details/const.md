---
date: 2025-11-01T11:26:02.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "const feature"
tags: [research, codebase, feature, const]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: const feature

**Date**: 2025-11-01T11:26:02.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `const` feature behave within Advanced Expression Folding?

## Summary
Folds static final fields into Kotlin-style const declarations. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `const` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt` defines or persists the `const` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `const` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/SettingsConfigurable.kt` defines or persists the `const` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/EditorExtensions.kt` references `const` within `EditorExtensions`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/diff/DiffFoldingTemporaryEditor.kt` references `const` within `DiffFoldingTemporaryEditor`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/Expression.kt` references `const` within `Expression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForStatement.kt` references `const` within `ForStatement`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/literal/LocalDateLiteral.kt` references `const` within `LocalDateLiteral`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/collection/Range.kt` references `const` within `Range`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/semantic/kotlin/FieldConstExpression.kt` references `const` within `FieldConstExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/cache/Keys.kt` references `const` within `Keys`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt` references `const` within `PsiFieldExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/language/FieldShiftExt.kt` references `const` within `FieldShiftExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/ConstExt.kt` references `const` within `ConstExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokConstructorExt.kt` references `const` within `LombokConstructorExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokExt.kt` references `const` within `LombokExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokPostConstructorExt.kt` references `const` within `LombokPostConstructorExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt` references `const` within `MethodCallFactory`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/reference/ReferenceExpressionExt.kt` references `const` within `ReferenceExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/AbstractLoggingAnnotationCompletionContributor.kt` references `const` within `AbstractLoggingAnnotationCompletionContributor`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributor.kt` references `const` within `LoggableAnnotationCompletionContributor`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt` references `const` within `MainAnnotationCompletionContributor`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributor.kt` references `const` within `TracingLoggableAnnotationCompletionContributor`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/BaseTest.kt` exercises folding behavior tied to `const`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt` exercises folding behavior tied to `const`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `const`.
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt` exercises folding behavior tied to `const`.
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt` exercises folding behavior tied to `const`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `const`.
- `examples/data/EmojifyTestData.java` provides example input demonstrating `const`.
- `folded/ConstTestData-folded.java` shows folded output illustrating `const`.
- `folded/ConstructorReferenceNotationWithConstTestData-folded.java` shows folded output illustrating `const`.
- `folded/EmojifyTestData-folded.java` shows folded output illustrating `const`.

## Code References
- `examples/data/EmojifyTestData.java`
- `folded/ConstTestData-folded.java`
- `folded/ConstructorReferenceNotationWithConstTestData-folded.java`
- `folded/EmojifyTestData-folded.java`
- `src/com/intellij/advancedExpressionFolding/EditorExtensions.kt`
- `src/com/intellij/advancedExpressionFolding/diff/DiffFoldingTemporaryEditor.kt`
- `src/com/intellij/advancedExpressionFolding/expression/Expression.kt`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForStatement.kt`
- `src/com/intellij/advancedExpressionFolding/expression/literal/LocalDateLiteral.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/collection/Range.kt`
- `src/com/intellij/advancedExpressionFolding/expression/semantic/kotlin/FieldConstExpression.kt`
- `src/com/intellij/advancedExpressionFolding/processor/cache/Keys.kt`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/language/FieldShiftExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/ConstExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokConstructorExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokPostConstructorExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt`
- `src/com/intellij/advancedExpressionFolding/processor/reference/ReferenceExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/AbstractLoggingAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/SettingsConfigurable.kt`
- `test/com/intellij/advancedExpressionFolding/folding/BaseTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
