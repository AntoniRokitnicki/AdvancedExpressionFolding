---
title: Local Date Literal Collapse (State field: localDateLiteralCollapse)
option: localDateLiteralCollapse
source: wiki-clone/docs/features/localDateLiteralCollapse.md
---
# Local Date Literal Collapse (State field: localDateLiteralCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/CreateDateFactoryMethodCall.kt:13` – `override fun canExecute() = localDateLiteralCollapse || localDateLiteralPostfixCollapse`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:50` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/DateOperationsState.kt:11` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:79` – `registerCheckbox(state::localDateLiteralCollapse, "LocalDate.of literals (e.g. 2018-02-12)") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/DateOperationsFoldingTest.kt:11` – `fun localDateLiteralTestData() = testCase.runReadOnlyFoldingTest(foldingState()::localDateLiteralCollapse)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/DateOperationsFoldingTest.kt:15` – `foldingState()::localDateLiteralCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Local Date Literal Collapse (State field: localDateLiteralCollapse)

### Local Date Literal Collapse
Folds LocalDate.of(...) literals into inline YYYY-MM-DD forms.

#### Example: LocalDateLiteralTestData

examples/data/LocalDateLiteralTestData.java:
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

folded/LocalDateLiteralTestData-folded.java:
```java
        LocalDate d1 = 2018-01-10;
        LocalDate d4 = 2018-01-10;
        LocalDate d2 = 2018-12-10;
        LocalDate d3 = 2018-04-04;
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013-01-10);
```

Highlights LocalDateLiteralTestData with local date literal collapse.
Removes boilerplate while preserving behavior.

#### Example: LocalDateLiteralPostfixTestData

examples/data/LocalDateLiteralPostfixTestData.java:
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

folded/LocalDateLiteralPostfixTestData-folded.java:
```java
        LocalDate d1 = 2018Y-01M-10D;
        LocalDate d4 = 2018Y-01M-10D;
        LocalDate d2 = 2018Y-12M-10D;
        LocalDate d3 = 2018Y-04M-04D;
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013Y-01M-10D);
```

Highlights LocalDateLiteralPostfixTestData with local date literal collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `localDateLiteralCollapse`
Related features: (none)

---
### Folding catalogue

#### LocalDateLiteralTestData

##### Scenario 1

**Before**
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
```

**After**
```java
        LocalDate d1 = 2018-01-10;
        LocalDate d4 = 2018-01-10;
        LocalDate d2 = 2018-12-10;
        LocalDate d3 = 2018-04-04;
```


##### Scenario 2

**Before**
```java
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

**After**
```java
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013-01-10);
```


#### LocalDateLiteralPostfixTestData

##### Scenario 1

**Before**
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
```

**After**
```java
        LocalDate d1 = 2018Y-01M-10D;
        LocalDate d4 = 2018Y-01M-10D;
        LocalDate d2 = 2018Y-12M-10D;
        LocalDate d3 = 2018Y-04M-04D;
```


##### Scenario 2

**Before**
```java
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

**After**
```java
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013Y-01M-10D);
```
