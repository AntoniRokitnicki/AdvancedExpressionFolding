---
title: patternMatchingInstanceof
option: patternMatchingInstanceof
source: wiki-clone/docs/features/patternMatchingInstanceof.md
---
# patternMatchingInstanceof

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:59` – `patternMatchingInstanceof && element is PsiIfStatement -> {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:88` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:26` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:245` – `registerCheckbox(state::patternMatchingInstanceof, "Pattern Matching for instanceof (JEP 394)") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:59` – `::patternMatchingInstanceofTestData to 6`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:75` – `fun patternMatchingInstanceofTestData() = testCase.runFoldingTest(foldingState()::patternMatchingInstanceof)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:78` – `fun patternMatchingRecordPatternTestData() = testCase.runFoldingTest(foldingState()::patternMatchingInstanceof)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:82` – `testCase.runFoldingTest(foldingState()::patternMatchingInstanceof)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

## patternMatchingInstanceof

### Pattern Matching for `instanceof` (JEP 394)
Applies pattern matching to `instanceof` checks for more concise and readable code.

#### Example: PatternMatchingInstanceofTestData

examples/data/PatternMatchingInstanceofTestData.java:
```java
        if (o instanceof String) {
            String s = (String) o;
// ...
        if (o instanceof Integer) {
            Integer num = (Integer) o;
```

folded/PatternMatchingInstanceofTestData-folded.java:
```java
        if (o instanceof String s) {
// ...
        if (o instanceof Integer num) {
```

Highlights PatternMatchingInstanceofTestData with pattern matching for `instanceof` (jep 394).
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `patternMatchingInstanceof`
Related features: (none)

---
### Folding catalogue

#### PatternMatchingInstanceofTestData

##### Scenario 1

**Before**
```java
        if (o instanceof String) {
            String s = (String) o;
```

**After**
```java
        if (o instanceof String s) {
```


##### Scenario 2

**Before**
```java
        if (o instanceof Integer) {
            Integer num = (Integer) o;
```

**After**
```java
        if (o instanceof Integer num) {
```


##### Scenario 3

**Before**
```java
        if (o instanceof Data) {
            Data d = (Data) o;
```

**After**
```java
        if (o instanceof Data d) {
```


##### Scenario 4

**Before**
```java
        if (o instanceof int[]) {
            int[] arr = (int[]) o;
```

**After**
```java
        if (o instanceof int[] arr) {
```


##### Scenario 5

**Before**
```java
        if (o instanceof DayOfWeek) {
            DayOfWeek day = (DayOfWeek) o;
```

**After**
```java
        if (o instanceof DayOfWeek day) {
```


##### Scenario 6

**Before**
```java
            if (o instanceof String) {
                String s = (String) o;
```

**After**
```java
            if (o instanceof String s) {
```
