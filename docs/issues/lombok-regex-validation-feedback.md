# Issue: Provide validation feedback for Lombok opt-out regex

## Summary
The settings field **"Regex to disable Lombok folding (matched classes won’t be folded)"** accepts malformed patterns and silently clears them. Users receive no feedback that their filter failed to compile, so Lombok folding remains active despite the attempted override.

## Steps to Reproduce
1. Open *Settings/Preferences → Editor → Code Folding → Advanced Expression Folding*.
2. Locate the Lombok section and focus the text field labelled *"Regex to disable Lombok folding (matched classes won’t be folded)"*.
3. Enter an invalid regular expression such as `[` and click **Apply** (or revisit the dialog later).

## Actual Result
- No validation error is displayed and the text box keeps the invalid pattern for the remainder of the session.
- The underlying state setter in `CheckboxesProvider#createEditor` swallows the `PatternSyntaxException` and resets the stored value to `null`, so the filter disappears on the next dialog open and Lombok folding is never disabled.

## Expected Result
- Invalid regular expressions should be rejected with inline feedback so the user knows the pattern must be fixed.
- Only syntactically correct patterns should be persisted; malformed patterns should not be silently discarded.

## Additional Context
- `CheckboxesProvider#createEditor` currently runs `text.toPattern()` inside `runCatching { … }.getOrNull()` and clears the property when compilation fails, but never surfaces the error to the UI.
- Providing live validation feedback (for example via `ComponentValidator`) would make the failure obvious and prevent state desynchronization between the UI and persisted settings.

## Acceptance Criteria
- When an invalid pattern is typed, the editor displays an error message and the settings dialog prevents applying the malformed value.
- Valid patterns continue to be saved and used to disable Lombok folding for matching classes.

## Issue Creation
Create the GitHub issue via the CLI once authentication is configured:

```bash
gh api repos/AntoniRokitnicki/AdvancedExpressionFolding/issues \
  -f title='Provide validation feedback for Lombok opt-out regex' \
  -f body=$'## Summary\nThe settings field **"Regex to disable Lombok folding (matched classes won’t be folded)"** accepts malformed patterns and silently clears them. Users receive no feedback that their filter failed to compile, so Lombok folding remains active despite the attempted override.\n\n## Steps to Reproduce\n1. Open *Settings/Preferences → Editor → Code Folding → Advanced Expression Folding*.\n2. Locate the Lombok section and focus the text field labelled *"Regex to disable Lombok folding (matched classes won’t be folded)"*.\n3. Enter an invalid regular expression such as `[` and click **Apply** (or revisit the dialog later).\n\n## Actual Result\n- No validation error is displayed and the text box keeps the invalid pattern for the remainder of the session.\n- The underlying state setter in `CheckboxesProvider#createEditor` swallows the `PatternSyntaxException` and resets the stored value to `null`, so the filter disappears on the next dialog open and Lombok folding is never disabled.\n\n## Expected Result\n- Invalid regular expressions should be rejected with inline feedback so the user knows the pattern must be fixed.\n- Only syntactically correct patterns should be persisted; malformed patterns should not be silently discarded.\n\n## Additional Context\n- `CheckboxesProvider#createEditor` currently runs `text.toPattern()` inside `runCatching { … }.getOrNull()` and clears the property when compilation fails, but never surfaces the error to the UI.\n- Providing live validation feedback (for example via `ComponentValidator`) would make the failure obvious and prevent state desynchronization between the UI and persisted settings.\n\n## Acceptance Criteria\n- When an invalid pattern is typed, the editor displays an error message and the settings dialog prevents applying the malformed value.\n- Valid patterns continue to be saved and used to disable Lombok folding for matching classes.'
```
