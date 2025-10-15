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
