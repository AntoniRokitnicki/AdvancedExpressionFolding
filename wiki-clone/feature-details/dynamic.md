---
date: 2025-11-01T11:26:07.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "dynamic feature"
tags: [research, codebase, feature, dynamic]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: dynamic feature

**Date**: 2025-11-01T11:26:07.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `dynamic` feature behave within Advanced Expression Folding?

## Summary
Applies dynamic naming to methods based on a configuration file. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `dynamic` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt` defines or persists the `dynamic` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `dynamic` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt` references `dynamic` within `PsiMethodExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt` references `dynamic` within `MethodCallFactory`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/AddDynamicMethodFoldingIntention.kt` references `dynamic` within `AddDynamicMethodFoldingIntention`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParser.kt` references `dynamic` within `ConfigurationParser`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicDialog.kt` references `dynamic` within `DynamicDialog`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicExt.kt` references `dynamic` within `DynamicExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicMethodCall.kt` references `dynamic` within `DynamicMethodCall`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicMethodCallData.kt` references `dynamic` within `DynamicMethodCallData`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/IDynamicDataProvider.kt` references `dynamic` within `IDynamicDataProvider`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/BaseTest.kt` exercises folding behavior tied to `dynamic`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/FoldingTestState.kt` exercises folding behavior tied to `dynamic`.
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt` exercises folding behavior tied to `dynamic`.
- `test/com/intellij/advancedExpressionFolding/folding/util/TestDynamicDataProvider.kt` exercises folding behavior tied to `dynamic`.
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt` exercises folding behavior tied to `dynamic`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `dynamic`.
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt` exercises folding behavior tied to `dynamic`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/AddDynamicMethodFoldingIntention.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParser.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicDialog.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicMethodCall.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/DynamicMethodCallData.kt`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/IDynamicDataProvider.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/BaseTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/FoldingTestState.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/folding/util/TestDynamicDataProvider.kt`
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
