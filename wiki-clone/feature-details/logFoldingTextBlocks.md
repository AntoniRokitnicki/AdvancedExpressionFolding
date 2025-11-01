---
date: 2025-11-01T11:26:24.203417+00:00
researcher: gpt-5-codex
git_commit: 089f76a81c48eb501fbb924c437c3b59cb175f27
branch: work
repository: AdvancedExpressionFolding
topic: "logFoldingTextBlocks feature"
tags: [research, codebase, feature, logFoldingTextBlocks]
status: complete
last_updated: 2025-11-01
last_updated_by: gpt-5-codex
---

# Research: logFoldingTextBlocks feature

**Date**: 2025-11-01T11:26:24.203417+00:00
**Researcher**: gpt-5-codex
**Git Commit**: 089f76a81c48eb501fbb924c437c3b59cb175f27
**Branch**: work
**Repository**: AdvancedExpressionFolding

## Research Question
How does the `logFoldingTextBlocks` feature behave within Advanced Expression Folding?

## Summary
Folds log Text Blocks into compact placeholders. Default state value: `true`.

## Detailed Findings
### Settings Integration
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt` defines or persists the `logFoldingTextBlocks` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/LogFoldingState.kt` defines or persists the `logFoldingTextBlocks` toggle, establishing defaults and UI bindings.
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt` defines or persists the `logFoldingTextBlocks` toggle, establishing defaults and UI bindings.

### Folding Processors
- `src/com/intellij/advancedExpressionFolding/processor/expression/LiteralExpressionExt.kt` references `logFoldingTextBlocks` within `LiteralExpressionExt`, controlling folding logic when enabled.
- `src/com/intellij/advancedExpressionFolding/processor/logger/LoggerBracketsExtensionBase.kt` references `logFoldingTextBlocks` within `LoggerBracketsExtensionBase`, controlling folding logic when enabled.

### Tests and Samples
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LogFoldingTest.kt` exercises folding behavior tied to `logFoldingTextBlocks`.

## Code References
- `src/com/intellij/advancedExpressionFolding/processor/expression/LiteralExpressionExt.kt`
- `src/com/intellij/advancedExpressionFolding/processor/logger/LoggerBracketsExtensionBase.kt`
- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/LogFoldingState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/LogFoldingTest.kt`

## Architecture Insights
`NewState` composes feature toggles through interface delegation, and processors guard their transformations with the feature flag to avoid unwanted folding.

## Historical Context (from thoughts/)
- None

## Related Research
- None

## Open Questions
- None
