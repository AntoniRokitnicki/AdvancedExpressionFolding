---
title: Slicing Expressions Collapse (State field: slicingExpressionsCollapse)
option: slicingExpressionsCollapse
source: wiki-clone/docs/features/slicingExpressionsCollapse.md
---
# Slicing Expressions Collapse (State field: slicingExpressionsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/SubstringOrSubListMethodCall.kt:17` – `override fun canExecute() = slicingExpressionsCollapse`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:47` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt:24` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:64` – `registerCheckbox(state::slicingExpressionsCollapse, "List.subList and String.substring expressions") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt:23` – `fun sliceTestData() = testCase.runFoldingTest(foldingState()::slicingExpressionsCollapse)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Slicing Expressions Collapse (State field: slicingExpressionsCollapse)

### Slicing Expressions Collapse
Folds List.subList and String.substring calls into concise slice notation.

#### Example: SliceTestData

examples/data/SliceTestData.java:
```java
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(1, 2));
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, list.size() - 2));
        System.out.println(list.subList(0, list.size() - 2));
// ...
        System.out.println(f.substring(1));
        System.out.println(f.substring(1, 2));
        System.out.println(f.substring(1, f.length()));
        System.out.println(f.substring(0, 2));
        System.out.println(f.substring(1, f.length() - 2));
        System.out.println(f.substring(0, f.length() - 2));
```

folded/SliceTestData-folded.java:
```java
        System.out.println(list[1:]);
        System.out.println(list[1:2]);
        System.out.println(list[1:]);
        System.out.println(list[:2]);
        System.out.println(list[1:-2]);
        System.out.println(list[:-2]);
// ...
        System.out.println(f[1:]);
        System.out.println(f[1:2]);
        System.out.println(f[1:]);
        System.out.println(f[:2]);
        System.out.println(f[1:-2]);
        System.out.println(f[:-2]);
```

Highlights SliceTestData with slicing expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `slicingExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### SliceTestData

##### Scenario 1

**Before**
```java
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(1, 2));
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, list.size() - 2));
        System.out.println(list.subList(0, list.size() - 2));
```

**After**
```java
        System.out.println(list[1:]);
        System.out.println(list[1:2]);
        System.out.println(list[1:]);
        System.out.println(list[:2]);
        System.out.println(list[1:-2]);
        System.out.println(list[:-2]);
```


##### Scenario 2

**Before**
```java
        System.out.println(f.substring(1));
        System.out.println(f.substring(1, 2));
        System.out.println(f.substring(1, f.length()));
        System.out.println(f.substring(0, 2));
        System.out.println(f.substring(1, f.length() - 2));
        System.out.println(f.substring(0, f.length() - 2));
```

**After**
```java
        System.out.println(f[1:]);
        System.out.println(f[1:2]);
        System.out.println(f[1:]);
        System.out.println(f[:2]);
        System.out.println(f[1:-2]);
        System.out.println(f[:-2]);
```
