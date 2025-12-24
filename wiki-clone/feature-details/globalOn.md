---
date: 2025-11-01T11:26:17.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "globalOn feature"
tags: [research, codebase, feature, globalOn]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: globalOn feature

**Date**: 2025-11-01T11:26:17.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `globalOn` feature behave within Advanced Expression Folding?

## Summary
![Keymap actions configured for folding and unfolding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/35863f50-d441-4402-8172-db6e75962350) Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `globalOn` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt` defines or persists the `globalOn` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/IConfig.kt` defines or persists the `globalOn` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt` references `globalOn` within `AdvancedExpressionFoldingBuilder`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/action/FoldingOnAction.kt` references `globalOn` within `FoldingOnAction`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/action/GlobalToggleFoldingAction.kt` references `globalOn` within `GlobalToggleFoldingAction`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt` exercises folding behavior tied to `globalOn`.
- `test/com/intellij/advancedExpressionFolding/unit/FoldingActionsTest.kt` exercises folding behavior tied to `globalOn`.
- `test/com/intellij/advancedExpressionFolding/unit/PlaceholderFoldingBuilderTest.kt` exercises folding behavior tied to `globalOn`.
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt` exercises folding behavior tied to `globalOn`.

## Code References
- `src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt`
- `src/com/intellij/advancedExpressionFolding/action/FoldingOnAction.kt`
- `src/com/intellij/advancedExpressionFolding/action/GlobalToggleFoldingAction.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/IConfig.kt`
- `test/com/intellij/advancedExpressionFolding/integration/IntegrationTest.kt`
- `test/com/intellij/advancedExpressionFolding/unit/FoldingActionsTest.kt`
- `test/com/intellij/advancedExpressionFolding/unit/PlaceholderFoldingBuilderTest.kt`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
