# Feature Idea: Checkbox Tree for Grouping Settings

## Current State
The settings panel is built dynamically using `SettingsConfigurable` and `CheckboxesProvider`.
All folding options are rendered as a long, flat list of independent checkboxes,
so related features like flow control or Lombok must be toggled one by one.

## Proposal
Replace the flat list with a tree of checkboxes where parent nodes represent option categories
(e.g. *Control Flow*, *Nullability*, *Lombok*).
Toggling a parent would enable or disable all children, while allowing fine‑grained overrides.
This structure would improve discoverability and let users manage large groups of options quickly.
