# Lombok regex validation

## Goal
Ensure the Lombok exclusion pattern editor keeps the previous valid pattern when a new value fails validation and immediately surfaces the regex error to the user.

## Preconditions
* Start with any project where the plugin is enabled.
* Remember or write down an existing valid pattern (the default empty value also counts).

## Steps
1. Open **Settings/Preferences | Editor | Advanced Folding**.
2. Locate the "Regex to disable Lombok folding" editor.
3. Enter the invalid pattern `*`.
4. Observe that the editor is marked with an error outline and an inline validator tooltip describing the regex problem.
5. Press <kbd>Enter</kbd> or move focus away from the editor.
6. Re-open the settings page (or re-focus the field) and confirm the previous valid pattern is still present instead of the invalid value.
7. Replace the content with a valid regular expression, e.g. `com.example.+`, and verify that the error outline disappears immediately and the value is persisted.

## Expected result
* Invalid patterns show an error message without overwriting the last valid value.
* Valid patterns remove the error highlight and are saved as usual.
