---
title: Kotlin Quick Return (State field: kotlinQuickReturn)
option: kotlinQuickReturn
source: wiki-clone/docs/features/kotlinQuickReturn.md
---
# Kotlin Quick Return (State field: kotlinQuickReturn)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/LetReturnExt.kt:16` – `if (!kotlinQuickReturn) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:67` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:20` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:109` – `registerCheckbox(state::kotlinQuickReturn, "Kotlin quick return") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:10` – `foldingState()::kotlinQuickReturn,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Kotlin Quick Return (State field: kotlinQuickReturn)

### Kotlin Quick Return
Folds Kotlin let/return idioms into quick-return expressions.

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

Highlights LetReturnIt with kotlin quick return.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `kotlinQuickReturn`
Related features: (none)

---
### Folding catalogue

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
