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
