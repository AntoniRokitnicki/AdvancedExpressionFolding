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

#### Folding catalogue

##### VarTestData mappings
| Before | After |
| --- | --- |
| `String string = "Hello, world";` | `val string = "Hello, world";` |
| `int count = 0;` | `var count = 0;` |
| `for (String arg : args) {` | `for (val arg : args) {` |
| `for (int i = 0; i < args.length; i++) {` | `for (var i = 0; i < args.length; i++) {` |
| `String arg = args[i];` | `val arg = args[i];` |

##### LetReturnIt mappings
| Before | After |
| --- | --- |
| `String var1 = getData(someString); if (var1 != null) { return var1; }` | `val var1 = getData(someString)?.let { return it }` |
| `String var2 = getData(someString); if (var2 == null) { return null; }` | `val var2 = getData(someString) ?: return null` |
| `String var4 = getData(someString); if (var4 != null) { return var4; } var4.toString();` | `val var4 = getData(someString)?.let { return it }` and `var4;` |
| `String var5 = getData(someString); if (var5 == null) { return null; }` | `val var5 = getData(someString) ?: return null` |
| `String var6 = getData(someString); if (var6 == null) { return null; }` | `val var6 = getData(someString) ?: return null` |
| `String var7 = getData(someString); if (var7 != null) { return var7; }` | `val var7 = getData(someString)?.let { return it }` |

##### DestructuringAssignmentArrayTestData mappings
| Before | After |
| --- | --- |
| `Data ignored1 = array[0];` | `val ignored1 = array[0];` |
| `Data first = array[0]; Data second = array[1]; Data third = array[2]; Data fourth = array[3];` | `val first, second, third, fourth) = array;` |
| `Data ignored21 = data.getArray()[4];` | `val ignored21 = data.array[4];` |
| `Data ignored22 = data.getArray()[5];` | `val ignored22 = data.array[5];` |
| `Data getter1 = data.getArray()[0]; Data getter2 = data.getArray()[1]; Data getter3 = data.getArray()[2];` | `var getter1, getter2, getter3) = data.array;` |
| `Data deepGetter1 = data.getData().getArray()[0]; Data deepGetter2 = data.getData().getArray()[1];` | `val deepGetter1, deepGetter2) = data.data.array;` |
| `Data wrongParent1 = data.getArray()[0];` | `val wrongParent1 = data.array[0];` |
| `Data wrongParent2 = data.getData().getArray()[1];` | `val wrongParent2 = data.data.array[1];` |

##### DestructuringAssignmentListTestData mappings
| Before | After |
| --- | --- |
| `Data ignored1 = list.get(0);` | `val ignored1 = list.get(0);` |
| `Data first = list.get(0); Data second = list.get(1); Data third = list.get(2); Data fourth = list.get(3);` | `val first, second, third, fourth) = list;` |
| `Data ignored21 = data.getList().get(4);` | `val ignored21 = data.list.get(4);` |
| `Data ignored22 = data.getList().get(5);` | `val ignored22 = data.list.get(5);` |
| `Data getter1 = data.getList().get(0); Data getter2 = data.getList().get(1); Data getter3 = data.getList().get(2);` | `var getter1, getter2, getter3) = data.list;` |
| `Data deepGetter1 = data.getData().getList().get(0); Data deepGetter2 = data.getData().getList().get(1);` | `val deepGetter1, deepGetter2) = data.data.list;` |
| `Data wrongParent1 = data.getList().get(0);` | `val wrongParent1 = data.list.get(0);` |
| `Data wrongParent2 = data.getData().getList().get(1);` | `val wrongParent2 = data.data.list.get(1);` |
