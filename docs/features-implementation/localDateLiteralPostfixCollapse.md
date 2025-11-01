---
title: Local Date Literal Postfix Collapse (State field: localDateLiteralPostfixCollapse)
option: localDateLiteralPostfixCollapse
source: wiki-clone/docs/features/localDateLiteralPostfixCollapse.md
---
# Local Date Literal Postfix Collapse (State field: localDateLiteralPostfixCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/expression/literal/LocalDateLiteral.kt:38` – `val usePostfix = localDateLiteralPostfixCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/date/CreateDateFactoryMethodCall.kt:13` – `override fun canExecute() = localDateLiteralCollapse || localDateLiteralPostfixCollapse`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:51` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/DateOperationsState.kt:12` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:84` – `registerCheckbox(state::localDateLiteralPostfixCollapse, "Postfix LocalDate literals (e.g. 2018Y-02M-12D)") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/DateOperationsFoldingTest.kt:16` – `foldingState()::localDateLiteralPostfixCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Local Date Literal Postfix Collapse (State field: localDateLiteralPostfixCollapse)

### Local Date Literal Postfix Collapse
Folds postfix LocalDate literals such as 2018Y-02M-12D.

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

Highlights LocalDateLiteralPostfixTestData with local date literal postfix collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `localDateLiteralPostfixCollapse`
Related features: (none)

---
### Folding catalogue

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
