---
title: ifNullSafe
option: ifNullSafe
source: wiki-clone/docs/features/ifNullSafe.md
---
# ifNullSafe

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafeExt.kt:24` – `if (!ifNullSafe) {`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt:24` – `if (!ifNullSafe) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:68` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:19` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:104` – `registerCheckbox(state::ifNullSafe, "Extended null-safe ifs") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:40` – `::ifNullSafeData to 6`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:14` – `fun ifNullSafeData() = testCase.runFoldingTest(`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:17` – `foldingState()::ifNullSafe,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

[video](https://www.youtube.com/watch?v=zvpvhn7ISAw)


![Null-safe if folding showcased on chained access](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/56aa2dbb-0aa1-4143-a296-801ffb0668cd)


## example Structural Searches

### If Null Safe
Extends null-safe folding to if statements and guard clauses.

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

Highlights IfNullSafeData with if null safe.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `ifNullSafe`
Related features: (none)

---
### Folding catalogue

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


##### Scenario 20

**Before**
```java
        if (user != null
                && user.getProfile() != null
                && user.getProfile().getName() != null) {
            System.out.println(user.getProfile().getName());
        }
```

**After**
```java
        System.out.println(user?.profile?.name);
```

##### Scenario 21

**Before**
```java
        if (user != null
                && user.getProfile() != null
                && user.getProfile().getName() != null) {
            System.out.println("Name: " + user.getProfile().getName());
        }
```

**After**
```java
        if (user?.profile?.name != null) {
            System.out.println("Name: " + user.profile.name);
        }
```

##### Scenario 22

**Before**
```java
        if (user != null
                && user.getProfile() != null
                && user.getProfile().getName() != null) {
            System.out.println(user.getProfile().getName().trim());
        }
```

**After**
```java
        if (user?.profile?.name != null) {
            System.out.println(user.profile.name.trim());
        }
```
