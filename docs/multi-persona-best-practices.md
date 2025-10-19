# Multi-persona best practices

The Advanced Expression Folding persona system lets teams share folding preferences without overwriting one another. Each persona captures a snapshot of the folding state, audit metadata, and an optional folded-text color.

## Switching and auditing

* Use the toolbar **Folding Personas** menu or the keyboard shortcuts listed below to jump between personas without opening Settings.
* The Settings panel now records audit entries every time a persona is activated or updated. Review the "Recent persona activity" panel before applying changes to avoid surprises.
* Conflict warnings appear when you stage a change that would overwrite a previously saved persona value. Review the diff preview to confirm the conflict is intentional before applying.

## Default shortcuts

| Persona | Shortcut |
| --- | --- |
| Core | `Alt+Shift+0` |
| Analyst | `Alt+Shift+1` |
| Logger | `Alt+Shift+2` |
| Reviewer | `Alt+Shift+3` |

## Collaboration tips

* Capture persona-specific examples with the download link in Settings so each developer can try the folds locally.
* Use the persona audit log as a lightweight changelog during code reviews; it records who changed a persona and when.
* Keep personas focused. Instead of toggling dozens of settings, create a new persona when a workflow needs a radically different configuration.

## Troubleshooting

* If the folded text color looks wrong after switching personas, press the "Apply folded color" button in Settings to reset to theme-aware defaults.
* Persona shortcuts rely on the default keymap. If they conflict with your custom keymap, edit them under **Settings | Keymap | Advanced Expression Folding**.
