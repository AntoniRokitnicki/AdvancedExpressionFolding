---
date: 2025-11-01T11:26:27.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "lombokPatternOff feature"
tags: [research, codebase, feature, lombokPatternOff]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: lombokPatternOff feature

**Date**: 2025-11-01T11:26:27.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `lombokPatternOff` feature behave within Advanced Expression Folding?

## Summary
Uses a regex to disable Lombok folding for matching classes. Default state value: `unspecified`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `lombokPatternOff` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt` defines or persists the `lombokPatternOff` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `lombokPatternOff` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/lombok/AnnotationExt.kt` references `lombokPatternOff` within `AnnotationExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LombokFoldingTest.kt` exercises folding behavior tied to `lombokPatternOff`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `lombokPatternOff`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/lombok/AnnotationExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
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
