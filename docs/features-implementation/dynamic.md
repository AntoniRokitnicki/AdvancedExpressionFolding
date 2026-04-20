---
title: dynamic
option: dynamic
source: wiki-clone/docs/features/dynamic.md
---
# dynamic

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:14` – `import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicExt`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:35` – `list.addIfEnabled(dynamic) {`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt:3` – `import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.ConfigurationParser`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt:4` – `import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt:5` – `import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt:41` – `private var dynamicProvider: IDynamicDataProvider? = ConfigurationParser`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt:52` – `fun refreshMethodCallMappings(dynamicProvider: IDynamicDataProvider? = null) {`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallFactory.kt:54` – `if (dynamicProvider != null) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:82` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt:13` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:222` – `registerCheckbox(state::dynamic, "Dynamic names for methods based on \$user.home/dynamic-ajf2.toml") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:224` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#dynamic")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt:1` – `package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic`
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt:17` – `val configPath = temporaryHome.resolve("dynamic-ajf2.toml")`
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt:39` – `assertTrue(parsed.isEmpty(), "Expected empty list when dynamic configuration file is absent")`
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt:50` – `val configPath = temporaryHome.resolve("dynamic-ajf2.toml")`
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt:71` – `val temporaryHome = Files.createTempDirectory("dynamic-ajf2-test")`
- `test/com/intellij/advancedExpressionFolding/processor/methodcall/dynamic/ConfigurationParserTest.kt:77` – `Files.deleteIfExists(temporaryHome.resolve("dynamic-ajf2.toml"))`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:26` – `::dynamicTestData to 4`
- `test/com/intellij/advancedExpressionFolding/performance/MethodCallFactoryPerformanceTest.kt:5` – `import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

## dynamic

### Dynamic names for methods based on $user.home/dynamic-ajf2.toml
Applies dynamic naming to methods based on a configuration file.

#### Example: DynamicTestData

examples/data/DynamicTestData.java:
```java
    public static void staticMethod(Data data) {
// ...
                .normalMethod(
                        staticMethod(
```

folded/DynamicTestData-folded.java:
```java
    public static void changedStaticMethod(Data data) {
// ...
                .changedNormalMethod(
                        changedStaticMethod(
```

Highlights DynamicTestData with dynamic names for methods based on $user.home/dynamic-ajf2.toml.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `dynamic`
Related features: (none)

---
### Folding catalogue

#### DynamicTestData

##### Scenario 1

**Before**
```java
    public static void staticMethod(Data data) {
```

**After**
```java
    public static void changedStaticMethod(Data data) {
```


##### Scenario 2

**Before**
```java
                .normalMethod(
                        staticMethod(
```

**After**
```java
                .changedNormalMethod(
                        changedStaticMethod(
```


##### Scenario 3

**Before**
```java
                                        .normalMethod(
                                                new DynamicTestData().staticMethod(
                                                        data.getData()
```

**After**
```java
                                        .changedNormalMethod(
                                                new DynamicTestData().changedStaticMethod(
                                                        data.data
```


##### Scenario 4

**Before**
```java
        staticMethod(data.getData());
```

**After**
```java
        changedStaticMethod(data.data);
```


##### Scenario 5

**Before**
```java
    private String normalMethod(String args) {
        return normalMethod(args.substring(1));
```

**After**
```java
    private String changedNormalMethod(String args) {
        return changedNormalMethod(args.substring(1));
```


##### Scenario 6

**Before**
```java
    private static String staticMethod(String args) {
```

**After**
```java
    private static String changedStaticMethod(String args) {
```
