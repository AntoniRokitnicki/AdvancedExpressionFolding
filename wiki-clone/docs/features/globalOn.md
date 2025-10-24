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
---

#### Behaviour

- Turning `globalOn` off short-circuits every folding builder before it touches PSI, so even if individual toggles remain enabled the editor renders the raw source with no synthetic placeholders.
- Re-enabling `globalOn` immediately restores whatever combination of feature switches you had before, making it a quick panic button when debugging or presenting code.
- The flag does not change per-feature checkboxes in settings; it only gates whether their fold regions are registered. This keeps customised setups intact while still offering a single master toggle.
