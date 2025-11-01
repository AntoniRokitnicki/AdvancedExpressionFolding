---
date: 2025-11-01T11:26:28.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "memoryImprovement feature"
tags: [research, codebase, feature, memoryImprovement]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: memoryImprovement feature

**Date**: 2025-11-01T11:26:28.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `memoryImprovement` feature behave within Advanced Expression Folding?

## Summary
* only for files in **testData** folder Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `memoryImprovement` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt` defines or persists the `memoryImprovement` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/IConfig.kt` defines or persists the `memoryImprovement` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `memoryImprovement` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt` references `memoryImprovement` within `AdvancedExpressionFoldingBuilder`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt` exercises folding behavior tied to `memoryImprovement`.

## Code References
- `src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/IConfig.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
