---
date: 2025-11-01T11:26:25.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "lombok feature"
tags: [research, codebase, feature, lombok]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: lombok feature

**Date**: 2025-11-01T11:26:25.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `lombok` feature behave within Advanced Expression Folding?

## Summary
![Animated overview of Lombok folding support](https://github.com/user-attachments/assets/7d2bdcf7-15ad-45a2-9aad-1f596443c4d7) Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `lombok` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt` defines or persists the `lombok` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/NewState.kt` defines or persists the `lombok` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `lombok` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/ClassAnnotationExpression.kt` references `lombok` within `ClassAnnotationExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/FieldAnnotationExpression.kt` references `lombok` within `FieldAnnotationExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/InterfacePropertiesExpression.kt` references `lombok` within `InterfacePropertiesExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/MethodAnnotationExpression.kt` references `lombok` within `MethodAnnotationExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/NullAnnotationExpression.kt` references `lombok` within `NullAnnotationExpression`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/PsiTryStatementExt.kt` references `lombok` within `PsiTryStatementExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiClassExt.kt` references `lombok` within `PsiClassExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt` references `lombok` within `PsiFieldExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt` references `lombok` within `PsiMethodExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/NullableExt.kt` references `lombok` within `NullableExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/AnnotationExt.kt` references `lombok` within `AnnotationExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/FieldLevelAnnotation.kt` references `lombok` within `FieldLevelAnnotation`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/InterfacePropertiesExt.kt` references `lombok` within `InterfacePropertiesExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokConstructorExt.kt` references `lombok` within `LombokConstructorExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokExt.kt` references `lombok` within `LombokExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokFieldExt.kt` references `lombok` within `LombokFieldExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokFoldingAnnotation.kt` references `lombok` within `LombokFoldingAnnotation`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokMethodExt.kt` references `lombok` within `LombokMethodExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokPostConstructorExt.kt` references `lombok` within `LombokPostConstructorExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/MethodBodyInspector.kt` references `lombok` within `MethodBodyInspector`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/MethodType.kt` references `lombok` within `MethodType`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/lombok/SummaryParentOverrideExt.kt` references `lombok` within `SummaryParentOverrideExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt` exercises folding behavior tied to `lombok`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `lombok`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt` exercises folding behavior tied to `lombok`.
- `test/com/intellij/advancedExpressionFolding/folding/base/full/FullFoldingTest.kt` exercises folding behavior tied to `lombok`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `lombok`.
- `examples/build.gradle.kts` provides example input demonstrating `lombok`.
- `examples/data/LombokPatternOffNegativeTestData.java` provides example input demonstrating `lombok`.
- `examples/data/LombokPatternOffTestData.java` provides example input demonstrating `lombok`.
- `examples/data/LombokTestData.java` provides example input demonstrating `lombok`.
- `examples/gradle/libs.versions.toml` provides example input demonstrating `lombok`.
- `folded/LombokPatternOffNegativeTestData-folded.java` shows folded output illustrating `lombok`.
- `folded/LombokPatternOffTestData-folded.java` shows folded output illustrating `lombok`.
- `folded/LombokTestData-folded.java` shows folded output illustrating `lombok`.

## Code References
- `examples/build.gradle.kts`
- `examples/data/LombokPatternOffNegativeTestData.java`
- `examples/data/LombokPatternOffTestData.java`
- `examples/data/LombokTestData.java`
- `examples/gradle/libs.versions.toml`
- `folded/LombokPatternOffNegativeTestData-folded.java`
- `folded/LombokPatternOffTestData-folded.java`
- `folded/LombokTestData-folded.java`
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/ClassAnnotationExpression.kt`
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/FieldAnnotationExpression.kt`
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/InterfacePropertiesExpression.kt`
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/MethodAnnotationExpression.kt`
- `src/com/intellij/advancedExpressionFolding/expression/semantic/lombok/NullAnnotationExpression.kt`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/PsiTryStatementExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiClassExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/NullableExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/AnnotationExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/FieldLevelAnnotation.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/InterfacePropertiesExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokConstructorExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokFieldExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokFoldingAnnotation.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokMethodExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/LombokPostConstructorExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/MethodBodyInspector.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/MethodType.kt`
- `src/com/intellij/advancedExpressionFolding/processor/lombok/SummaryParentOverrideExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/NewState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt`
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
