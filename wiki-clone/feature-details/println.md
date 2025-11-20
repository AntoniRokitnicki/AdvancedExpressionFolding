---
date: 2025-11-01T11:26:34.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "println feature"
tags: [research, codebase, feature, println]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: println feature

**Date**: 2025-11-01T11:26:34.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `println` feature behave within Advanced Expression Folding?

## Summary
![System.out.println call folded to println](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/75a5224f-7b52-4b71-9774-2814e8a867ba) Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `println` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt` defines or persists the `println` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `println` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt` references `println` within `IfNullSafePrintlnExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/PrintlnExt.kt` references `println` within `PrintlnExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/PrintlnMethodCall.kt` references `println` within `PrintlnMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/util/CodeGenerationUtil.kt` references `println` within `CodeGenerationUtil`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributor.kt` references `println` within `LoggableAnnotationCompletionContributor`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt` references `println` within `MainAnnotationCompletionContributor`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/folding/crazy/CrazyFoldingTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/folding/util/FoldingDataStorage.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/folding/util/GitUtils.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/performance/PerformanceTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt` exercises folding behavior tied to `println`.
- `test/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributorTest.kt` exercises folding behavior tied to `println`.
- `examples/data/AppendSetterInterpolatedStringTestData.java` provides example input demonstrating `println`.
- `examples/data/CompactControlFlowTestData.java` provides example input demonstrating `println`.
- `examples/data/ConcatenationTestData.java` provides example input demonstrating `println`.
- `examples/data/ControlFlowMultiStatementTestData.java` provides example input demonstrating `println`.
- `examples/data/ControlFlowSingleStatementTestData.java` provides example input demonstrating `println`.
- `examples/data/DynamicTestData.java` provides example input demonstrating `println`.
- `examples/data/ElvisTestData.java` provides example input demonstrating `println`.
- `examples/data/EmojifyTestData.java` provides example input demonstrating `println`.
- `examples/data/EqualsCompareTestData.java` provides example input demonstrating `println`.
- `examples/data/ExperimentalTestData.java` provides example input demonstrating `println`.
- `examples/data/ForRangeTestData.java` provides example input demonstrating `println`.
- `examples/data/GetSetPutTestData.java` provides example input demonstrating `println`.
- `examples/data/GetterSetterTestData.java` provides example input demonstrating `println`.
- `examples/data/IfNullSafeData.java` provides example input demonstrating `println`.
- `examples/data/InterpolatedStringTestData.java` provides example input demonstrating `println`.
- `examples/data/LetReturnIt.java` provides example input demonstrating `println`.
- `examples/data/LogBrackets.java` provides example input demonstrating `println`.
- `examples/data/LogFoldingTextBlocksTestData.java` provides example input demonstrating `println`.
- `examples/data/NullableAnnotationCheckNotNullTestData.java` provides example input demonstrating `println`.
- `examples/data/OverrideHideTestData.java` provides example input demonstrating `println`.
- `examples/data/PatternMatchingInstanceofTestData.java` provides example input demonstrating `println`.
- `examples/data/PatternMatchingRecordPatternEdgeCasesTestData.java` provides example input demonstrating `println`.
- `examples/data/PatternMatchingRecordPatternTestData.java` provides example input demonstrating `println`.
- `examples/data/PrintlnTestData.java` provides example input demonstrating `println`.
- `examples/data/PseudoAnnotationsLoggableTestData.java` provides example input demonstrating `println`.
- `examples/data/SemicolonTestData.java` provides example input demonstrating `println`.
- `examples/data/SliceTestData.java` provides example input demonstrating `println`.
- `examples/data/StringBuilderTestData.java` provides example input demonstrating `println`.
- `examples/data/SummaryParentOverrideTestData.java` provides example input demonstrating `println`.
- `examples/data/SuppressWarningsHideTestData.java` provides example input demonstrating `println`.
- `examples/data/TypeCastTestData.java` provides example input demonstrating `println`.
- `examples/data/VarTestData.java` provides example input demonstrating `println`.
- `folded/AppendSetterInterpolatedStringTestData-folded.java` shows folded output illustrating `println`.
- `folded/CompactControlFlowTestData-folded.java` shows folded output illustrating `println`.
- `folded/ConcatenationTestData-folded.java` shows folded output illustrating `println`.
- `folded/ControlFlowMultiStatementTestData-folded.java` shows folded output illustrating `println`.
- `folded/ControlFlowSingleStatementTestData-folded.java` shows folded output illustrating `println`.
- `folded/DynamicTestData-folded.java` shows folded output illustrating `println`.
- `folded/ElvisTestData-folded.java` shows folded output illustrating `println`.
- `folded/EmojifyTestData-folded.java` shows folded output illustrating `println`.
- `folded/EqualsCompareTestData-folded.java` shows folded output illustrating `println`.
- `folded/ExperimentalTestData-folded.java` shows folded output illustrating `println`.
- `folded/ForRangeTestData-folded.java` shows folded output illustrating `println`.
- `folded/GetSetPutTestData-folded.java` shows folded output illustrating `println`.
- `folded/GetterSetterTestData-folded.java` shows folded output illustrating `println`.
- `folded/IfNullSafeData-folded.java` shows folded output illustrating `println`.
- `folded/InterpolatedStringTestData-folded.java` shows folded output illustrating `println`.
- `folded/LetReturnIt-folded.java` shows folded output illustrating `println`.
- `folded/LogBrackets-folded.java` shows folded output illustrating `println`.
- `folded/LogFoldingTextBlocksTestData-folded.java` shows folded output illustrating `println`.
- `folded/NullableAnnotationCheckNotNullTestData-folded.java` shows folded output illustrating `println`.
- `folded/OverrideHideTestData-folded.java` shows folded output illustrating `println`.
- `folded/PatternMatchingInstanceofTestData-folded.java` shows folded output illustrating `println`.
- `folded/PatternMatchingRecordPatternEdgeCasesTestData-folded.java` shows folded output illustrating `println`.
- `folded/PatternMatchingRecordPatternTestData-folded.java` shows folded output illustrating `println`.
- `folded/PrintlnTestData-folded.java` shows folded output illustrating `println`.
- `folded/SemicolonTestData-folded.java` shows folded output illustrating `println`.
- `folded/SliceTestData-folded.java` shows folded output illustrating `println`.
- `folded/StringBuilderTestData-folded.java` shows folded output illustrating `println`.
- `folded/SummaryParentOverrideTestData-folded.java` shows folded output illustrating `println`.
- `folded/SuppressWarningsHideTestData-folded.java` shows folded output illustrating `println`.
- `folded/TypeCastTestData-folded.java` shows folded output illustrating `println`.
- `folded/VarTestData-folded.java` shows folded output illustrating `println`.

## Code References
- `examples/data/AppendSetterInterpolatedStringTestData.java`
- `examples/data/CompactControlFlowTestData.java`
- `examples/data/ConcatenationTestData.java`
- `examples/data/ControlFlowMultiStatementTestData.java`
- `examples/data/ControlFlowSingleStatementTestData.java`
- `examples/data/DynamicTestData.java`
- `examples/data/ElvisTestData.java`
- `examples/data/EmojifyTestData.java`
- `examples/data/EqualsCompareTestData.java`
- `examples/data/ExperimentalTestData.java`
- `examples/data/ForRangeTestData.java`
- `examples/data/GetSetPutTestData.java`
- `examples/data/GetterSetterTestData.java`
- `examples/data/IfNullSafeData.java`
- `examples/data/InterpolatedStringTestData.java`
- `examples/data/LetReturnIt.java`
- `examples/data/LogBrackets.java`
- `examples/data/LogFoldingTextBlocksTestData.java`
- `examples/data/NullableAnnotationCheckNotNullTestData.java`
- `examples/data/OverrideHideTestData.java`
- `examples/data/PatternMatchingInstanceofTestData.java`
- `examples/data/PatternMatchingRecordPatternEdgeCasesTestData.java`
- `examples/data/PatternMatchingRecordPatternTestData.java`
- `examples/data/PrintlnTestData.java`
- `examples/data/PseudoAnnotationsLoggableTestData.java`
- `examples/data/SemicolonTestData.java`
- `examples/data/SliceTestData.java`
- `examples/data/StringBuilderTestData.java`
- `examples/data/SummaryParentOverrideTestData.java`
- `examples/data/SuppressWarningsHideTestData.java`
- `examples/data/TypeCastTestData.java`
- `examples/data/VarTestData.java`
- `folded/AppendSetterInterpolatedStringTestData-folded.java`
- `folded/CompactControlFlowTestData-folded.java`
- `folded/ConcatenationTestData-folded.java`
- `folded/ControlFlowMultiStatementTestData-folded.java`
- `folded/ControlFlowSingleStatementTestData-folded.java`
- `folded/DynamicTestData-folded.java`
- `folded/ElvisTestData-folded.java`
- `folded/EmojifyTestData-folded.java`
- `folded/EqualsCompareTestData-folded.java`
- `folded/ExperimentalTestData-folded.java`
- `folded/ForRangeTestData-folded.java`
- `folded/GetSetPutTestData-folded.java`
- `folded/GetterSetterTestData-folded.java`
- `folded/IfNullSafeData-folded.java`
- `folded/InterpolatedStringTestData-folded.java`
- `folded/LetReturnIt-folded.java`
- `folded/LogBrackets-folded.java`
- `folded/LogFoldingTextBlocksTestData-folded.java`
- `folded/NullableAnnotationCheckNotNullTestData-folded.java`
- `folded/OverrideHideTestData-folded.java`
- `folded/PatternMatchingInstanceofTestData-folded.java`
- `folded/PatternMatchingRecordPatternEdgeCasesTestData-folded.java`
- `folded/PatternMatchingRecordPatternTestData-folded.java`
- `folded/PrintlnTestData-folded.java`
- `folded/SemicolonTestData-folded.java`
- `folded/SliceTestData-folded.java`
- `folded/StringBuilderTestData-folded.java`
- `folded/SummaryParentOverrideTestData-folded.java`
- `folded/SuppressWarningsHideTestData-folded.java`
- `folded/TypeCastTestData-folded.java`
- `folded/VarTestData-folded.java`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/PrintlnExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/PrintlnMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/util/CodeGenerationUtil.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/crazy/CrazyFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/util/FoldingDataStorage.kt`
- `test/com/intellij/advancedExpressionFolding/folding/util/GitUtils.kt`
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/PerformanceTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt`
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt`
- `test/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributorTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
