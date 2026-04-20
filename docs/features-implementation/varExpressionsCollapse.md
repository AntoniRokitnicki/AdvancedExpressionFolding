---
title: Var Expressions Collapse (State field: varExpressionsCollapse)
option: varExpressionsCollapse
source: wiki-clone/docs/features/varExpressionsCollapse.md
---
# Var Expressions Collapse (State field: varExpressionsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/controlflow/ForStatementExpressionExt.kt:183` – `varExpressionsCollapse,`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/ForStatementExpressionExt.kt:227` – `varExpressionsCollapse,`
- `src/com/intellij/advancedExpressionFolding/processor/core/DeclarationBuilders.kt:26` – `override fun checkConditions(element: PsiVariable) = varExpressionsCollapse &&`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiDeclarationStatementExt.kt:85` – `return if (varExpressionsCollapse) {`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiVariableExt.kt:30` – `return varExpressionsCollapse &&`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:56` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt:25` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:32` – `registerCheckbox(state::varExpressionsCollapse, "Variable declarations (var/val)") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt:38` – `fun varTestData() = testCase.runFoldingTest(foldingState()::varExpressionsCollapse)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:9` – `foldingState()::varExpressionsCollapse,`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:24` – `foldingState()::varExpressionsCollapse,`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:37` – `foldingState()::varExpressionsCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Var Expressions Collapse (State field: varExpressionsCollapse)

### Var Expressions Collapse
Folds variable declarations into val/var style declarations.

#### Example: VarTestData

examples/data/VarTestData.java:
```java
        String string = "Hello, world";
// ...
        int count = 0;
        for (String arg : args) {
```

folded/VarTestData-folded.java:
```java
        val string = "Hello, world";
// ...
        var count = 0;
        for (val arg : args) {
```

Highlights VarTestData with var expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: LetReturnIt

examples/data/LetReturnIt.java:
```java
        String var1 = getData(someString);
        if (var1 != null) {
            return var1;
// ...
        if (var5 == null) {
            return null;
        }
// ...
        String var6 = getData(someString);
        if (var6 == null) {
            return null;
        }
```

folded/LetReturnIt-folded.java:
```java
        val var1 = getData(someString)?.let { return it }
        val var2 = getData(someString) ?: return null
        val var4 = getData(someString)?.let { return it }
        var4;
        val var5 = getData(someString) ?: return null
// ...
        val var6 = getData(someString) ?: return null
```

Highlights LetReturnIt with var expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentArrayTestData

examples/data/DestructuringAssignmentArrayTestData.java:
```java
        Data ignored1 = array[0];
// ...
        Data first = array[0];
```

folded/DestructuringAssignmentArrayTestData-folded.java:
```java
        val ignored1 = array[0];
// ...
        val first, second, third, fourth) = array;
```

Highlights DestructuringAssignmentArrayTestData with var expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentListTestData

examples/data/DestructuringAssignmentListTestData.java:
```java
        Data ignored1 = list.get(0);
// ...
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

folded/DestructuringAssignmentListTestData-folded.java:
```java
        val ignored1 = list.get(0);
// ...
        val first, second, third, fourth) = list;
```

Highlights DestructuringAssignmentListTestData with var expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `varExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### VarTestData

##### Scenario 1

**Before**
```java
        String string = "Hello, world";
```

**After**
```java
        val string = "Hello, world";
```


##### Scenario 2

**Before**
```java
        int count = 0;
        for (String arg : args) {
```

**After**
```java
        var count = 0;
        for (val arg : args) {
```


##### Scenario 3

**Before**
```java
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
```

**After**
```java
        for (var i = 0; i < args.length; i++) {
                val arg = args[i];
```


#### LetReturnIt

##### Scenario 1

**Before**
```java
        String var1 = getData(someString);
        if (var1 != null) {
            return var1;
        }
        String var2 = getData(someString);
        if (var2 == null) {
            return null;
        }
        String var4 = getData(someString);
        if (var4 != null) {
            return var4;
        }
        var4.toString();
        String var5 = getData(someString);
        if (var5 == null) {
            return null;
        }
```

**After**
```java
        val var1 = getData(someString)?.let { return it }
        val var2 = getData(someString) ?: return null
        val var4 = getData(someString)?.let { return it }
        var4;
        val var5 = getData(someString) ?: return null
```


##### Scenario 2

**Before**
```java
        String var6 = getData(someString);
        if (var6 == null) {
            return null;
        }
```

**After**
```java
        val var6 = getData(someString) ?: return null
```


##### Scenario 3

**Before**
```java
        String var7 = getData(someString);
        if (var7 != null) {
            return var7;
        }
```

**After**
```java
        val var7 = getData(someString)?.let { return it }
```


#### DestructuringAssignmentArrayTestData

##### Scenario 1

**Before**
```java
        Data ignored1 = array[0];
```

**After**
```java
        val ignored1 = array[0];
```


##### Scenario 2

**Before**
```java
        Data first = array[0];
```

**After**
```java
        val first, second, third, fourth) = array;
```


##### Scenario 3

**Before**
```java
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

**After**
```java
        val ignored21 = data.array[4];
        val ignored22 = data.array[5];
```


##### Scenario 4

**Before**
```java
        Data ignored21 = data.getArray()[4];
        Data ignored22 = data.getArray()[5];

        Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
        Data getter3 = data.getArray()[2];
```

**After**
```java
        var getter1, getter2, getter3) = data.array;
```


##### Scenario 5

**Before**
```java
        Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];
```

**After**
```java
        val deepGetter1, deepGetter2) = data.data.array;
```


##### Scenario 6

**Before**
```java
        Data wrongParent1 = data.getArray()[0];
        Data wrongParent2 = data.getData().getArray()[1];
```

**After**
```java
        val wrongParent1 = data.array[0];
        val wrongParent2 = data.data.array[1];
```


#### DestructuringAssignmentListTestData

##### Scenario 1

**Before**
```java
        Data ignored1 = list.get(0);
```

**After**
```java
        val ignored1 = list.get(0);
```


##### Scenario 2

**Before**
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

**After**
```java
        val first, second, third, fourth) = list;
```


##### Scenario 3

**Before**
```java
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

**After**
```java
        val ignored21 = data.list.get(4);
        val ignored22 = data.list.get(5);
```


##### Scenario 4

**Before**
```java
        Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);
```

**After**
```java
        var getter1, getter2, getter3) = data.list;
```


##### Scenario 5

**Before**
```java
        Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);
```

**After**
```java
        val deepGetter1, deepGetter2) = data.data.list;
```


##### Scenario 6

**Before**
```java
        Data wrongParent1 = data.getList().get(0);
        Data wrongParent2 = data.getData().getList().get(1);
```

**After**
```java
        val wrongParent1 = data.list.get(0);
        val wrongParent2 = data.data.list.get(1);
```
