## for array

### for array without _Variable declarations (var/val)_
![Array destructuring without var/val folded inline](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/3391702f-8632-4539-9e81-60f52f7ee006)

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

Highlights DestructuringAssignmentArrayTestData with for array without _variable declarations (var/val)_.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentArrayWithoutValTestData

examples/data/DestructuringAssignmentArrayWithoutValTestData.java:
```java
        Data first = array[0];
// ...
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

folded/DestructuringAssignmentArrayWithoutValTestData-folded.java:
```java
        Data (first, second, third, fourth) = array;
// ...
        Data ignored21 = data.array[4];
        Data ignored22 = data.array[5];
```

Highlights DestructuringAssignmentArrayWithoutValTestData with for array without _variable declarations (var/val)_.
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

Highlights DestructuringAssignmentListTestData with for array without _variable declarations (var/val)_.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentListWithoutValTestData

examples/data/DestructuringAssignmentListWithoutValTestData.java:
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
// ...
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

folded/DestructuringAssignmentListWithoutValTestData-folded.java:
```java
        Data (first, second, third, fourth) = list;
// ...
        Data ignored21 = data.list.get(4);
        Data ignored22 = data.list.get(5);
```

Highlights DestructuringAssignmentListWithoutValTestData with for array without _variable declarations (var/val)_.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `destructuring`
Related features: (none)
---

#### Folding catalogue

##### DestructuringAssignmentArrayTestData mappings
| Before | After |
| --- | --- |
| `Data ignored1 = array[0];` | `val ignored1 = array[0];` |
| `<pre>Data first = array[0];

        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];</pre>` | `val first, second, third, fourth) = array;` |
| `Data ignored21 = data.getArray()[4];` | `val ignored21 = data.array[4];` |
| `Data ignored22 = data.getArray()[5];` | `val ignored22 = data.array[5];` |
| `<pre>Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
        Data getter3 = data.getArray()[2];</pre>` | `var getter1, getter2, getter3) = data.array;` |
| `<pre>Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];</pre>` | `val deepGetter1, deepGetter2) = data.data.array;` |
| `Data wrongParent1 = data.getArray()[0];` | `val wrongParent1 = data.array[0];` |
| `Data wrongParent2 = data.getData().getArray()[1];` | `val wrongParent2 = data.data.array[1];` |

##### DestructuringAssignmentArrayWithoutValTestData mappings
| Before | After |
| --- | --- |
| `<pre>Data first = array[0];

        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];</pre>` | `Data (first, second, third, fourth) = array;` |
| `Data ignored21 = data.getArray()[4];` | `Data ignored21 = data.array[4];` |
| `Data ignored22 = data.getArray()[5];` | `Data ignored22 = data.array[5];` |
| `<pre>Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];</pre>` | `Data (getter1, getter2) = data.array;` |
| `<pre>Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];</pre>` | `Data (deepGetter1, deepGetter2) = data.data.array;` |
| `Data wrongParent1 = data.getArray()[0];` | `Data wrongParent1 = data.array[0];` |
| `Data wrongParent2 = data.getData().getArray()[1];` | `Data wrongParent2 = data.data.array[1];` |

##### DestructuringAssignmentListTestData mappings
| Before | After |
| --- | --- |
| `Data ignored1 = list.get(0);` | `val ignored1 = list.get(0);` |
| `<pre>Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);</pre>` | `val first, second, third, fourth) = list;` |
| `Data ignored21 = data.getList().get(4);` | `val ignored21 = data.list.get(4);` |
| `Data ignored22 = data.getList().get(5);` | `val ignored22 = data.list.get(5);` |
| `<pre>Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);</pre>` | `var getter1, getter2, getter3) = data.list;` |
| `<pre>Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);</pre>` | `val deepGetter1, deepGetter2) = data.data.list;` |
| `Data wrongParent1 = data.getList().get(0);` | `val wrongParent1 = data.list.get(0);` |
| `Data wrongParent2 = data.getData().getList().get(1);` | `val wrongParent2 = data.data.list.get(1);` |

##### DestructuringAssignmentListWithoutValTestData mappings
| Before | After |
| --- | --- |
| `<pre>Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);</pre>` | `Data (first, second, third, fourth) = list;` |
| `Data ignored21 = data.getList().get(4);` | `Data ignored21 = data.list.get(4);` |
| `Data ignored22 = data.getList().get(5);` | `Data ignored22 = data.list.get(5);` |
| `<pre>Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);</pre>` | `Data (getter1, getter2, getter3) = data.list;` |
| `<pre>Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);</pre>` | `Data (deepGetter1, deepGetter2) = data.data.list;` |
| `Data wrongParent1 = data.getList().get(0);` | `Data wrongParent1 = data.list.get(0);` |
| `Data wrongParent2 = data.getData().getList().get(1);` | `Data wrongParent2 = data.data.list.get(1);` |
