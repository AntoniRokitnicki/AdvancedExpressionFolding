---
title: Semicolons Collapse (State field: semicolonsCollapse)
option: semicolonsCollapse
source: wiki-clone/docs/features/semicolonsCollapse.md
---
# Semicolons Collapse (State field: semicolonsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:197` – `if (!semicolonsCollapse && throwStatement.text.endsWith(";")) {`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:217` – `if (semicolonsCollapse) "" else ";"`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:221` – `if (!semicolonsCollapse && throwStatement.text.endsWith(";")) {`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:241` – `if (semicolonsCollapse) "" else ";"`
- `src/com/intellij/advancedExpressionFolding/processor/core/LexicalExpressionBuilders.kt:14` – `semicolonsCollapse &&`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:61` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/UnclassifiedFeatureState.kt:9` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:174` – `registerCheckbox(state::semicolonsCollapse, "Semicolons (read-only files)") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/UnclassifiedFeatureFoldingTest.kt:8` – `fun semicolonTestData() = testCase.runReadOnlyFoldingTest(foldingState()::semicolonsCollapse)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Semicolons Collapse (State field: semicolonsCollapse)

### Semicolons Collapse
Folds redundant semicolons inside read-only files.

#### Example: SemicolonTestData

examples/data/SemicolonTestData.java:
```java
package data;
// ...
import java.util.Arrays;
```

folded/SemicolonTestData-folded.java:
```java
package data
// ...
import java.util.Arrays
```

Highlights SemicolonTestData with semicolons collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `semicolonsCollapse`
Related features: (none)

---
### Folding catalogue

#### SemicolonTestData

##### Scenario 1

**Before**
```java
package data;
```

**After**
```java
package data
```


##### Scenario 2

**Before**
```java
import java.util.Arrays;
```

**After**
```java
import java.util.Arrays
```


##### Scenario 3

**Before**
```java
                System.out.println(arg);
```

**After**
```java
                System.out.println(arg)
```


##### Scenario 4

**Before**
```java
                Arrays.stream(args).forEach(System.out::println);
```

**After**
```java
                Arrays.stream(args).forEach(System.out::println)
```
