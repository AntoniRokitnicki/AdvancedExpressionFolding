# Advanced Expression Folding settings overview

## Overview

![New Advanced Expression Folding settings panel](https://github.com/user-attachments/assets/45e0d314-d19f-4c06-96a8-3d6555d8ca4a)

## Configuration

- **Toggle ID:** `feature-settings-overview`
- **Default state:** Unknown
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

## Navigate to the panel
1. Open **Settings / Preferences**.
2. Select **Editor → General → Code Folding**.
3. Choose **Advanced Expression Folding** in the tree to reveal the plugin-specific options.

## What you will see
* **Global On** sits at the top and acts as a master switch. Disable it to temporarily restore IntelliJ’s default folding without losing individual preferences.
* Feature groups such as **Null safety**, **Collections & builders**, **Control flow**, and **Lombok & annotations** organise the dozens of checkboxes shown on the Home page.
* Each checkbox opens inline samples. Click a sample name (for example, “builders” under *Field shift*) to preview the before/after code directly inside the dialog.
* Links on the right-hand side jump straight to the corresponding wiki article for deeper explanations and caveats.

## Tips for curating your setup
* Start with the groups that match your pain points—teams adopting Kotlin-style idioms can enable the whole **Null safety** block first.
* Keep the dialog open while browsing project files; the IDE updates folds as soon as you tick or untick a checkbox.
* Use the search box in the settings window to quickly locate a toggle by name if you remember it from the Home page tables.
