# Settings search filter QA

## Preconditions
- IntelliJ IDEA with the Advanced Expression Folding plugin installed.
- A project opened so the settings page can show example checkout actions.

## Steps
1. Open **Settings/Preferences → Editor → Advanced Expression Folding 2**.
2. Type `lombok` in the filter field.
   - Only Lombok-related options remain visible and their example/doc rows are hidden when the checkbox row is hidden.
3. Clear the filter by removing the query.
   - All folding options return and the example/doc rows reappear for the affected entries.
4. Enable **Getters and setters as properties** while the list is unfiltered and do not apply yet.
5. Type `stream` into the filter to hide the previously toggled checkbox.
6. Toggle **Display stream operations as Groovy's spread operator** while the filter is active and press **Apply**.
7. Clear the filter and verify that **Getters and setters as properties** remains enabled even though it was hidden during the apply.
8. Press **OK**, reopen the settings page, and confirm that both toggles persist.

## Expected results
- The filter immediately hides checkboxes (and their associated example/doc rows) that do not match the query text.
- Clearing the filter restores the full list without losing any selections.
- Applying settings while some options are hidden preserves the hidden selections.
