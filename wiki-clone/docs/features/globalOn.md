# Global On

## Overview
The **Global On** switch acts as the master control for Advanced Expression Folding. When enabled, feature-specific processors
register their folds; when disabled, the plugin leaves files untouched so you can temporarily return to IntelliJ's default folding
without changing individual options.

## Configuration
- **Toggle ID:** `globalOn`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** All feature checkboxes inherit this master switch

## Enable or disable the plugin
1. Open **Settings / Preferences → Editor → General → Code Folding**.
2. Select **Advanced Expression Folding**.
3. Toggle **Global On** at the top of the panel.
4. Click **Apply** (or **OK**) to refresh the open editors.

## Behaviour
- When **Global On** is off, the plugin skips registering every feature-specific folding descriptor, effectively restoring the
  stock IntelliJ presentation for all files.
- When the switch is on, each per-feature checkbox determines whether its folding processor contributes regions; disabling a
  single option while the master switch remains true removes only that behaviour.
- Use Global On as a panic button during debugging or code reviews when you want to temporarily suppress folds without losing your
  curated configuration.

With **Global On** disabled the builder short-circuits `buildFoldRegions`, returning an empty descriptor array regardless of per-
feature checkboxes; flipping the switch back on immediately reapplies the cached per-feature settings because the builder resumes
collecting descriptors normally.【F:src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt†L23-L39】
