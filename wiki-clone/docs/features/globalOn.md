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
