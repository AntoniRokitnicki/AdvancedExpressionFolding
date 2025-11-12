---
title: Comparing Expressions Collapse (State field: comparingExpressionsCollapse)
option: comparingExpressionsCollapse
source: wiki-clone/docs/features/comparingExpressionsCollapse.md
---
# Comparing Expressions Collapse (State field: comparingExpressionsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/expression/BinaryExpressionExt.kt:64` – `if (!comparingExpressionsCollapse) {`
- `src/com/intellij/advancedExpressionFolding/processor/expression/PrefixExpressionExt.kt:62` – `if (comparingExpressionsCollapse) {`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/EqualsMethodCall.kt:12` – `override fun canExecute() = comparingExpressionsCollapse`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:48` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt:19` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:69` – `registerCheckbox(state::comparingExpressionsCollapse, "Object.equals and Comparable.compareTo expressions") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt:32` – `fun equalsCompareTestData() = testCase.runFoldingTest(foldingState()::comparingExpressionsCollapse)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Comparing Expressions Collapse (State field: comparingExpressionsCollapse)

### Comparing Expressions Collapse
Folds equals and compareTo calls into direct comparison expressions.

#### Example: EqualsCompareTestData

examples/data/EqualsCompareTestData.java:
```java
        System.out.println(a.equals(b));
        System.out.println(!a.equals(b));
        System.out.println(a.compareTo(b) == 0);
        System.out.println(a.compareTo(b) != 0);
// ...
        System.out.println(a.compareTo(b) > 0);
        System.out.println(a.compareTo(b) == 1);
        System.out.println(a.compareTo(b) > -1);
        System.out.println(a.compareTo(b) >= 0); // Should be a >= b
```

folded/EqualsCompareTestData-folded.java:
```java
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
// ...
        System.out.println(a > b);
        System.out.println(a > b);
        System.out.println(a ≥ b);
        System.out.println(a ≥ b); // Should be a >= b
```

Highlights EqualsCompareTestData with comparing expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `comparingExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### EqualsCompareTestData

##### Scenario 1

**Before**
```java
        System.out.println(a.equals(b));
        System.out.println(!a.equals(b));
        System.out.println(a.compareTo(b) == 0);
        System.out.println(a.compareTo(b) != 0);
```

**After**
```java
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
```


##### Scenario 2

**Before**
```java
        System.out.println(a.compareTo(b) > 0);
        System.out.println(a.compareTo(b) == 1);
        System.out.println(a.compareTo(b) > -1);
        System.out.println(a.compareTo(b) >= 0); // Should be a >= b
```

**After**
```java
        System.out.println(a > b);
        System.out.println(a > b);
        System.out.println(a ≥ b);
        System.out.println(a ≥ b); // Should be a >= b
```


##### Scenario 3

**Before**
```java
        System.out.println(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b) == -1);
        System.out.println(a.compareTo(b) < 1);
        System.out.println(a.compareTo(b) <= 0); // Should be a <= b
```

**After**
```java
        System.out.println(a < b);
        System.out.println(a < b);
        System.out.println(a ≤ b);
        System.out.println(a ≤ b); // Should be a <= b
```
