---
title: Final Emoji (State field: finalEmoji)
option: finalEmoji
source: wiki-clone/docs/features/finalEmoji.md
---
# Final Emoji (State field: finalEmoji)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/token/PsiKeywordExt.kt:24` – `finalRemoval.takeIfTrue(keyword)?.finalRemoval() ?: finalEmoji.takeIfTrue(keyword)?.finalEmoji()`
- `src/com/intellij/advancedExpressionFolding/processor/token/PsiKeywordExt.kt:26` – `private fun PsiKeyword.finalEmoji(): Expression? = foldFinalsExceptFields { expr(Emoji.FINAL_LOCK.toString()) }`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:79` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/EmojiVisibilityState.kt:10` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:195` – `registerCheckbox(state::finalEmoji, "Replace the 'final' modifier with ${Emoji.FINAL_LOCK}") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:35` – `::finalEmojiTestData to 4`
- `test/com/intellij/advancedExpressionFolding/folding/base/full/FullFoldingTest.kt:14` – `.enableAll(state::emojify, state::finalEmoji, state::arithmeticExpressions)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/EmojiVisibilityFoldingTest.kt:12` – `fun finalEmojiTestData() = testCase.runFoldingTest(foldingState()::finalEmoji)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Final Emoji (State field: finalEmoji)

### Final Emoji
Replaces final modifiers with lock emoji markers.

#### Example: FinalEmojiTestData

examples/data/FinalEmojiTestData.java:
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
// ...
        void main(final String arg, final int i, final Object o, Data data);
```

folded/FinalEmojiTestData-folded.java:
```java
    public 🔒 String m() {
        🔒 String s = "1";
        🔒 var s2 = "2";
// ...
        void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data);
```

Highlights FinalEmojiTestData with final emoji.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `finalEmoji`
Related features: (none)

---
### Folding catalogue

#### FinalEmojiTestData

##### Scenario 1

**Before**
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
```

**After**
```java
    public 🔒 String m() {
        🔒 String s = "1";
        🔒 var s2 = "2";
```


##### Scenario 2

**Before**
```java
        void main(final String arg, final int i, final Object o, Data data);
```

**After**
```java
        void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data);
```


##### Scenario 3

**Before**
```java
    final static class Data {
```

**After**
```java
    🔒 static class Data {
```


##### Scenario 4

**Before**
```java
    final public record UserDataRecord(String username, boolean active, String userIdentifier) {
        final void main(final String arg, final int i, final Object o, Data data) {
```

**After**
```java
    🔒 public record UserDataRecord(String username, boolean active, String userIdentifier) {
        🔒 void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data) {
```
