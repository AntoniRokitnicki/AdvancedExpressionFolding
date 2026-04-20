---
title: globalOn
option: globalOn
source: wiki-clone/docs/features/globalOn.md
---
# globalOn

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt:24` – `if (!globalOn || isFoldingFile(element)) {`
- `src/com/intellij/advancedExpressionFolding/action/FoldingOnAction.kt:15` – `if (!globalOn) {`
- `src/com/intellij/advancedExpressionFolding/action/FoldingOnAction.kt:16` – `globalOn = true`
- `src/com/intellij/advancedExpressionFolding/action/GlobalToggleFoldingAction.kt:15` – `override fun isSelected(e: AnActionEvent): Boolean = globalOn`
- `src/com/intellij/advancedExpressionFolding/action/GlobalToggleFoldingAction.kt:18` – `globalOn = state`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:101` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt:4` with default `true`.

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:19` – `.filter { it.name != "globalOn" && it.name != "memoryImprovement" }`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:25` – `val initialGlobalOn = state.globalOn`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:35` – `assertEquals(initialGlobalOn, state.globalOn)`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:43` – `state.globalOn = false`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:53` – `assertFalse(state.globalOn)`
- `test/com/intellij/advancedExpressionFolding/unit/FoldingActionsTest.kt:27` – `originalGlobalOn = settings.state.globalOn`
- `test/com/intellij/advancedExpressionFolding/unit/FoldingActionsTest.kt:28` – `settings.state.globalOn = true`
- `test/com/intellij/advancedExpressionFolding/unit/FoldingActionsTest.kt:33` – `settings.state.globalOn = originalGlobalOn`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

## globalOn

![Keymap actions configured for folding and unfolding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/35863f50-d441-4402-8172-db6e75962350)

# Global On (State field: globalOn)

### Global On
Master switch that enables or disables all Advanced Expression Folding actions.

_No bundled example for this setting._
This option affects editor behaviour without a dedicated sample file.

Default: On
Controlled by: `globalOn`
Related features: (none)

#### Interaction with feature toggles

The `globalOn` flag is evaluated before any individual folding processor runs. When it is switched off the plugin skips registration of every feature-specific descriptor, regardless of the per-feature checkboxes, effectively restoring IntelliJ’s default presentation. When the switch is on, the per-feature settings decide which processors contribute folds; disabling one of those options while `globalOn` stays true only removes that specific behaviour. This makes `globalOn` a safe panic button for debugging sessions or code reviews where developers want to momentarily suppress all advanced folding without losing their fine-grained preferences.

With `globalOn` disabled the builder short-circuits `buildFoldRegions`, returning an empty descriptor array regardless of per-feature checkboxes; as soon as the master switch is flipped back on the cached per-feature settings apply again because the builder resumes collecting descriptors normally.【F:src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt†L23-L39】
