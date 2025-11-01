---
title: Check Expressions Collapse (State field: checkExpressionsCollapse)
option: checkExpressionsCollapse
source: wiki-clone/docs/features/checkExpressionsCollapse.md
---
# Check Expressions Collapse (State field: checkExpressionsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt:63` – `if (checkExpressionsCollapse) {`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt:231` – `if (checkExpressionsCollapse && condition is PsiBinaryExpression) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:54` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt:18` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:99` – `registerCheckbox(state::checkExpressionsCollapse, "Null-safe calls") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt:8` – `fun elvisTestData() = testCase.runFoldingTest(foldingState()::checkExpressionsCollapse)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:15` – `foldingState()::checkExpressionsCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Check Expressions Collapse (State field: checkExpressionsCollapse)

### Check Expressions Collapse
Folds null checks and Elvis patterns into safe-call expressions.

#### Example: ElvisTestData

examples/data/ElvisTestData.java:
```java
        System.out.println(e != null ? e : "");
        System.out.println(e != null ? e.sayHello() : "");
// ...
        if (e != null) {
                e.get().sayHello();
        }
        if (e.get() != null) {
                e.get().sayHello();
        }
```

folded/ElvisTestData-folded.java:
```java
        System.out.println(e ?: "");
        System.out.println(e?.sayHello() ?: "");
// ...
        e?.get().sayHello();
        e.get()?.sayHello();
```

Highlights ElvisTestData with check expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: IfNullSafeData

examples/data/IfNullSafeData.java:
```java
        var threeChains = data != null
                && data.getData1() != null
// ...
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

folded/IfNullSafeData-folded.java:
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
// ...
                && data?.data1?.active == true;
```

Highlights IfNullSafeData with check expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `checkExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### ElvisTestData

##### Scenario 1

**Before**
```java
        System.out.println(e != null ? e : "");
        System.out.println(e != null ? e.sayHello() : "");
        System.out.println(e == null ? "" : e); // Inverted Elvis should also fold to e ?: ""
```

**After**
```java
        System.out.println(e ?: "");
        System.out.println(e?.sayHello() ?: "");
        System.out.println(e ?: ""); // Inverted Elvis should also fold to e ?: ""
```


##### Scenario 2

**Before**
```java
        if (e != null) {
                e.get().sayHello();
        }
        if (e.get() != null) {
                e.get().sayHello();
        }
```

**After**
```java
        e?.get().sayHello();
        e.get()?.sayHello();
```


#### IfNullSafeData

##### Scenario 1

**Before**
```java
        var threeChains = data != null
                && data.getData1() != null
```

**After**
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
```


##### Scenario 2

**Before**
```java
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

**After**
```java
                && data?.data1?.active == true;
```


##### Scenario 3

**Before**
```java
        var notChain = data != null && !data.getData1().isActive();
        var chain = data != null && data.getData1() != null && data.getData1().getData4() != null;
```

**After**
```java
        var notChain = data != null && !data.data1.active;
        var chain = data?.data1?.data4 != null;
```


##### Scenario 4

**Before**
```java
        if (data != null && data.getData1() != null &&
                data.getData1().getData2() != null && data.getData1().
                getData2()
                .getData3() != null) {
```

**After**
```java
        if (data?.data1?.data2?.data3 != null) {
```


##### Scenario 5

**Before**
```java
        if (data != null && data.getData1() != null) {
```

**After**
```java
        if (data?.data1 != null) {
```


##### Scenario 6

**Before**
```java
        if (data != null && data.isActive()) {
```

**After**
```java
        if (data?.active == true) {
```


##### Scenario 7

**Before**
```java
        if (data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null
```

**After**
```java
        if (data?.data1?.data2?.data3?.data4 != null
```


##### Scenario 8

**Before**
```java
                && data != null
                && data.getData1() != null
                && !data.getData1().isActive()
```

**After**
```java
                && data?.data1?.active == false
```


##### Scenario 9

**Before**
```java
        boolean has = data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null;
        var active = data != null
                && data.getData1() != null
                && data.getData1().isActive();
        var inactive = data != null && !data.isActive();
        while (data != null && data.getData2() != null && !data.getData2().isActive()) {
            active = !data.getData1().isActive();
```

**After**
```java
        boolean has = data?.data1?.data2?.data3?.data4 != null;
        var active = data?.data1?.active == true;
        var inactive = data?.active == false;
        while (data?.data2?.active == false) {
            active = !data.data1.active;
```


##### Scenario 10

**Before**
```java
        if ((data != null && data.getData6() != null &&
                data.getData6().isActive())) {
```

**After**
```java
        if ((data?.data6?.active == true)) {
```


##### Scenario 11

**Before**
```java
        if ((data != null && data.getData6() != null &&
                !data.getData6().isActive())) {
```

**After**
```java
        if ((data?.data6?.active == false)) {
```


##### Scenario 12

**Before**
```java
                || data != null
                && data.getData1() != null
                && data.getData1().isActive())
```

**After**
```java
                || data?.data1?.active == true)
```


##### Scenario 13

**Before**
```java
                && (data != null
                && data.getData2() != null
                && data.getData2().isActive() ||
```

**After**
```java
                && (data?.data2?.active == true ||
```


##### Scenario 14

**Before**
```java
                data != null
                        && data.getData3() != null
                        && data.getData3().isActive()
                        && data.getData3().getData4() != null
                        && data.getData3().getData4().isActive()) ||
```

**After**
```java
                data?.data3?.active == true
                        && data.data3.data4?.active == true) ||
```


##### Scenario 15

**Before**
```java
                (data != null
                        && data.getData5() != null
                        && data.getData5().isActive()) &&
```

**After**
```java
                (data?.data5?.active == true) &&
```


##### Scenario 16

**Before**
```java
                                data != null &&
                                data.getData6() != null &&
                                data.getData6().isActive())) {
```

**After**
```java
                                data?.data6?.active == true)) {
```


##### Scenario 17

**Before**
```java
        if (data.getData1().getData2().getData3() != null &&
                data.getData1().getData2().getData3().isActive()) {
```

**After**
```java
        if (data.data1.data2.data3?.active == true) {
```


##### Scenario 18

**Before**
```java
        if (data2.getData1().getData2() != null &&
                data2.getData1().getData2().isActive()) {
```

**After**
```java
        if (data2.data1.data2?.active == true) {
```


##### Scenario 19

**Before**
```java
        if (data2.getData1() != null &&
                data2.getData1().isActive()) {
```

**After**
```java
        if (data2.data1?.active == true) {
```
