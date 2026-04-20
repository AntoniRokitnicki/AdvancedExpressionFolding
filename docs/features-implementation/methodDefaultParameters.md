---
title: example:
option: methodDefaultParameters
source: wiki-clone/docs/features/methodDefaultParameters.md
---
# example:

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiClassExt.kt:31` – `list.addIfEnabled(methodDefaultParameters) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:91` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:25` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:267` – `registerCheckbox(state::methodDefaultParameters, "Default parameter values inline for overloaded method") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:269` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#methodDefaultParameters")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:53` – `::methodDefaultParametersTestData to 7`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:94` – `fun methodDefaultParametersTestData() = testCase.runFoldingTest(foldingState()::methodDefaultParameters)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

#### example:

### Method Default Parameters
Folds overloads into default-parameter annotations.

#### Example: MethodDefaultParametersTestData

examples/data/MethodDefaultParametersTestData.java:
```java
        public String applySort1(String criterionName, boolean descending) {
// ...
        public String applySort1() {
            return applySort1("DESC", false);
        }
```

folded/MethodDefaultParametersTestData-folded.java:
```java
        public String applySort1(String criterionName = "DESC", boolean descending = false) {
// ...
        
```

Highlights MethodDefaultParametersTestData with method default parameters.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `methodDefaultParameters`
Related features: (none)

---
### Folding catalogue

#### MethodDefaultParametersTestData

##### Scenario 1

**Before**
```java
        public String applySort1(String criterionName, boolean descending) {
```

**After**
```java
        public String applySort1(String criterionName = "DESC", boolean descending = false) {
```


##### Scenario 2

**Before**
```java
        public String applySort1() {
            return applySort1("DESC", false);
        }
```

**After**
```java
        
```


##### Scenario 3

**Before**
```java
        public String applySort2(String criterionName, boolean descending) {
```

**After**
```java
        public String applySort2(String criterionName, boolean descending = System.getProperty("sort-desc") != null) {
```


##### Scenario 4

**Before**
```java
        public String applySort2(String criterionName) {
            return applySort2(criterionName, System.getProperty("sort-desc") != null);
        }
```

**After**
```java
        
```


##### Scenario 5

**Before**
```java
            return applySortWrongFirstType(String.valueOf(criterionName), false);
```

**After**
```java
            return applySortWrongFirstType(criterionName, false);
```


##### Scenario 6

**Before**
```java
        public String multipleDefaults(String name, int count, boolean flag) {
```

**After**
```java
        public String multipleDefaults(String name, int count = 10, boolean flag = false) {
```


##### Scenario 7

**Before**
```java
        public String multipleDefaults(String name) {
            return multipleDefaults(name, 10, false);
        }
```

**After**
```java
        
```


##### Scenario 8

**Before**
```java
        public String chainedDefaults(String name, int count, boolean flag) {
```

**After**
```java
        public String chainedDefaults(String name, int count, boolean flag = true) {
```


##### Scenario 9

**Before**
```java
        public String chainedDefaults(String name, int count) {
            return chainedDefaults(name, count, true);
        }
```

**After**
```java
        
```


##### Scenario 10

**Before**
```java
        public String expressionDefaults(String name, int count) {
```

**After**
```java
        public String expressionDefaults(String name, int count = "test".length() + 2) {
```


##### Scenario 11

**Before**
```java
        public String expressionDefaults(String name) {
            return expressionDefaults(name, "test".length() + 2);
        }
```

**After**
```java
        
```


##### Scenario 12

**Before**
```java
        public static String staticWithDefaults(String name, boolean flag) {
```

**After**
```java
        public static String staticWithDefaults(String name, boolean flag = true) {
```


##### Scenario 13

**Before**
```java
        public static String staticWithDefaults(String name) {
            return staticWithDefaults(name, true);
        }
```

**After**
```java
        
```


##### Scenario 14

**Before**
```java
            return String.valueOf(value);
```

**After**
```java
            return value;
```


##### Scenario 15

**Before**
```java
        public String mixedTypes(String text, int number, boolean flag, double value) {
```

**After**
```java
        public String mixedTypes(String text, int number, boolean flag, double value = 1.0) {
```


##### Scenario 16

**Before**
```java
        public String mixedTypes(String text, int number, boolean flag) {
            return mixedTypes(text, number, flag, 1.0);
        }
```

**After**
```java
        
```


##### Scenario 17

**Before**
```java
        public String varargMethod(String prefix, String... items) {
```

**After**
```java
        public String varargMethod(String prefix, String... items = "default") {
```


##### Scenario 18

**Before**
```java
        public String varargMethod(String prefix) {
            return varargMethod(prefix, "default");
        }
```

**After**
```java
        
```


##### Scenario 19

**Before**
```java
        public String differentParamNames(String name, boolean enabled) {
```

**After**
```java
        public String differentParamNames(String name, boolean enabled = true) {
```


##### Scenario 20

**Before**
```java
        public String differentParamNames(String title) {
            return differentParamNames(title, true);
        }
```

**After**
```java
        
```


##### Scenario 21

**Before**
```java
        public <T> String genericMethod(T item, boolean flag) {
            return item.toString() + flag;
```

**After**
```java
        public <T> String genericMethod(T item, boolean flag = false) {
            return item + flag;
```


##### Scenario 22

**Before**
```java
        public <T> String genericMethod(T item) {
            return genericMethod(item, false);
        }
```

**After**
```java
        
```
