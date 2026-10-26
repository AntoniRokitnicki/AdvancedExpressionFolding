---
title: Experimental (State field: experimental)
option: experimental
source: wiki-clone/docs/features/experimental.md
---
# Experimental (State field: experimental)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/controlflow/PsiTryStatementExt.kt:17` – `if (!experimental || !isSimpleTryCatch()) return null`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt:104` – `onGetterSetter(element, document, identifier, qualifier, experimental)?.let { return it }`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt:142` – `experimental: Boolean`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt:147` – `experimental && isNamelessGetter(identifier.text, element, resolvedMethod)`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt:162` – `if (isSimpleSetter(text, element, qualifier, experimental, resolvedMethod)) {`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt:184` – `experimental: Boolean,`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/MethodCallExpressionExt.kt:187` – `val isNamelessSetter = experimental && isNamelessSetter(text, element, method)`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:99` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/GlobalSettingsState.kt:14` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:285` – `registerCheckbox(state::experimental, "Experimental features") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:287` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#experimental")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:30` – `::experimentalTestData to 5`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt:32` – `fun experimentalTestData() = testCase.runFoldingTest(`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt:33` – `foldingState()::experimental,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Experimental (State field: experimental)

### Experimental
Enables experimental folding prototypes.

#### Example: ExperimentalTestData

examples/data/ExperimentalTestData.java:
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
// ...
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```

folded/ExperimentalTestData-folded.java:
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].getBytes();
// ...
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```

Highlights ExperimentalTestData with experimental.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `experimental`
Related features: (none)

---
### Folding catalogue

#### ExperimentalTestData

##### Scenario 1

**Before**
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
```

**After**
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
```


##### Scenario 2

**Before**
```java
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```


##### Scenario 3

**Before**
```java
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
```

**After**
```java
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```


##### Scenario 4

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows(IllegalArgumentException) {
```


##### Scenario 5

**Before**
```java
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
```


##### Scenario 6

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows {
```


##### Scenario 7

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
```


##### Scenario 8

**Before**
```java
            try {
                return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
```

**After**
```java
            @SneakyThrows
            return new String(System["sort-desc"].bytes, "UTF-8");
```


##### Scenario 9

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows
```


##### Scenario 10

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
```


##### Scenario 11

**Before**
```java
            example.set("ok");
            String current = example.get();
            System.out.println(example.get().isEmpty());
            example.set(example.get());
            String duplicate = example.get() + example.get();
```

**After**
```java
            example.!! = "ok";
            String current = example.!!;
            System.out.println(example.!!.empty);
            example.!! = example.!!;
            String duplicate = example.!! + example.!!;
```
