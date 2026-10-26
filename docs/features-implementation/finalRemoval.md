---
title: Final Removal (State field: finalRemoval)
option: finalRemoval
source: wiki-clone/docs/features/finalRemoval.md
---
# Final Removal (State field: finalRemoval)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/token/PsiKeywordExt.kt:24` – `finalRemoval.takeIfTrue(keyword)?.finalRemoval() ?: finalEmoji.takeIfTrue(keyword)?.finalEmoji()`
- `src/com/intellij/advancedExpressionFolding/processor/token/PsiKeywordExt.kt:28` – `private fun PsiKeyword.finalRemoval(): Expression? = foldFinalsExceptFields { exprHide() }`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:77` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/EmojiVisibilityState.kt:11` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:190` – `registerCheckbox(state::finalRemoval, "Remove the 'final' modifier from all elements except fields") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:36` – `::finalRemovalTestData to 4`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/EmojiVisibilityFoldingTest.kt:8` – `fun finalRemovalTestData() = testCase.runFoldingTest(foldingState()::finalRemoval)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Final Removal (State field: finalRemoval)

### Final Removal
Folds the final modifier away from non-field declarations.

#### Example: FinalRemovalTestData

examples/data/FinalRemovalTestData.java:
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
// ...
        void main(final String arg, final int i, final Object o, Data data);
```

folded/FinalRemovalTestData-folded.java:
```java
    public  String m() {
         String s = "1";
         var s2 = "2";
// ...
        void main( String arg,  int i,  Object o, Data data);
```

Highlights FinalRemovalTestData with final removal.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `finalRemoval`
Related features: (none)

---
### Folding catalogue

#### FinalRemovalTestData

##### Scenario 1

**Before**
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
```

**After**
```java
    public  String m() {
         String s = "1";
         var s2 = "2";
```


##### Scenario 2

**Before**
```java
        void main(final String arg, final int i, final Object o, Data data);
```

**After**
```java
        void main( String arg,  int i,  Object o, Data data);
```


##### Scenario 3

**Before**
```java
    final static class Data {
```

**After**
```java
     static class Data {
```


##### Scenario 4

**Before**
```java
    final public record UserDataRecord(String username, boolean active, String userIdentifier) {
        final void main(final String arg, final int i, final Object o, Data data) {
```

**After**
```java
     public record UserDataRecord(String username, boolean active, String userIdentifier) {
         void main( String arg,  int i,  Object o, Data data) {
```
