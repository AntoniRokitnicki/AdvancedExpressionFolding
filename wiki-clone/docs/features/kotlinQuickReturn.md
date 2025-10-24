# Kotlin Quick Return

## Overview

Folds Kotlin let/return idioms into quick-return expressions.


## Configuration

- **Toggle ID:** `kotlinQuickReturn`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

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
