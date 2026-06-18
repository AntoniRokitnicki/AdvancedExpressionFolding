---
date: 2025-11-01T11:26:31.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "optional feature"
tags: [research, codebase, feature, optional]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: optional feature

**Date**: 2025-11-01T11:26:31.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `optional` feature behave within Advanced Expression Folding?

## Summary
Displays java.util.Optional flows as Kotlin-style null-safe chains. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `optional` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/CollectionsStreamsState.kt` defines or persists the `optional` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `optional` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalMapCall.kt` references `optional` within `OptionalMapCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalMapSafeCall.kt` references `optional` within `OptionalMapSafeCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalMapSafeCallParam.kt` references `optional` within `OptionalMapSafeCallParam`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalNotNullAssertionGet.kt` references `optional` within `OptionalNotNullAssertionGet`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalOf.kt` references `optional` within `OptionalOf`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalOfNullable.kt` references `optional` within `OptionalOfNullable`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalOrElseElvis.kt` references `optional` within `OptionalOrElseElvis`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/AbstractOptionalMethodCall.kt` references `optional` within `AbstractOptionalMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalGetMethodCall.kt` references `optional` within `OptionalGetMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalMapMethodCall.kt` references `optional` within `OptionalMapMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalOfMethodCall.kt` references `optional` within `OptionalOfMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalOfNullableMethodCall.kt` references `optional` within `OptionalOfNullableMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalOrElseMethodCall.kt` references `optional` within `OptionalOrElseMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/reference/MethodReferenceExt.kt` references `optional` within `MethodReferenceExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/CollectionsStreamsFoldingTest.kt` exercises folding behavior tied to `optional`.
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt` exercises folding behavior tied to `optional`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `optional`.
- `test/com/intellij/advancedExpressionFolding/unit/PlaceholderFoldingBuilderTest.kt` exercises folding behavior tied to `optional`.
- `examples/data/EmojifyTestData.java` provides example input demonstrating `optional`.
- `folded/EmojifyTestData-folded.java` shows folded output illustrating `optional`.

## Code References
- `examples/data/EmojifyTestData.java`
- `folded/EmojifyTestData-folded.java`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalMapCall.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalMapSafeCall.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalMapSafeCallParam.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalNotNullAssertionGet.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalOf.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalOfNullable.kt`
- `src/com/intellij/advancedExpressionFolding/expression/operation/optional/OptionalOrElseElvis.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/AbstractOptionalMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalGetMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalMapMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalOfMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalOfNullableMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/optional/OptionalOrElseMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/reference/MethodReferenceExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/CollectionsStreamsState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/CollectionsStreamsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`
- `test/com/intellij/advancedExpressionFolding/unit/PlaceholderFoldingBuilderTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
