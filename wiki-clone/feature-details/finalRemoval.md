---
date: 2025-11-01T11:26:14.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "finalRemoval feature"
tags: [research, codebase, feature, finalRemoval]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: finalRemoval feature

**Date**: 2025-11-01T11:26:14.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `finalRemoval` feature behave within Advanced Expression Folding?

## Summary
Folds the final modifier away from non-field declarations. Default state value: `false`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `finalRemoval` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/EmojiVisibilityState.kt` defines or persists the `finalRemoval` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `finalRemoval` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/token/PsiKeywordExt.kt` references `finalRemoval` within `PsiKeywordExt`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/EmojiVisibilityFoldingTest.kt` exercises folding behavior tied to `finalRemoval`.
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt` exercises folding behavior tied to `finalRemoval`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/token/PsiKeywordExt.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/EmojiVisibilityState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/EmojiVisibilityFoldingTest.kt`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
