# for array

## Overview

![Array destructuring without var/val folded inline](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/3391702f-8632-4539-9e81-60f52f7ee006)

## Configuration

- **Toggle ID:** `destructuring`
- **Default state:** Off
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

### for array without _Variable declarations (var/val)_

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


---
### Folding catalogue

#### DestructuringAssignmentArrayTestData

##### Scenario 1

**Before**
```java
        Data ignored1 = array[0];
```

**After**
```java
        val ignored1 = array[0];
```


##### Scenario 2

**Before**
```java
        Data first = array[0];
```

**After**
```java
        val first, second, third, fourth) = array;
```


##### Scenario 3

**Before**
```java
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

**After**
```java
        val ignored21 = data.array[4];
        val ignored22 = data.array[5];
```


##### Scenario 4

**Before**
```java
        Data ignored21 = data.getArray()[4];
        Data ignored22 = data.getArray()[5];

        Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
        Data getter3 = data.getArray()[2];
```

**After**
```java
        var getter1, getter2, getter3) = data.array;
```


##### Scenario 5

**Before**
```java
        Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];
```

**After**
```java
        val deepGetter1, deepGetter2) = data.data.array;
```


##### Scenario 6

**Before**
```java
        Data wrongParent1 = data.getArray()[0];
        Data wrongParent2 = data.getData().getArray()[1];
```

**After**
```java
        val wrongParent1 = data.array[0];
        val wrongParent2 = data.data.array[1];
```


#### DestructuringAssignmentArrayWithoutValTestData

##### Scenario 1

**Before**
```java
        Data first = array[0];
```

**After**
```java
        Data (first, second, third, fourth) = array;
```


##### Scenario 2

**Before**
```java
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

**After**
```java
        Data ignored21 = data.array[4];
        Data ignored22 = data.array[5];
```


##### Scenario 3

**Before**
```java
        Data ignored21 = data.getArray()[4];
        Data ignored22 = data.getArray()[5];

        Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
```

**After**
```java
        Data (getter1, getter2) = data.array;
```


##### Scenario 4

**Before**
```java
        Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];
```

**After**
```java
        Data (deepGetter1, deepGetter2) = data.data.array;
```


##### Scenario 5

**Before**
```java
        Data wrongParent1 = data.getArray()[0];
        Data wrongParent2 = data.getData().getArray()[1];
```

**After**
```java
        Data wrongParent1 = data.array[0];
        Data wrongParent2 = data.data.array[1];
```


#### DestructuringAssignmentListTestData

##### Scenario 1

**Before**
```java
        Data ignored1 = list.get(0);
```

**After**
```java
        val ignored1 = list.get(0);
```


##### Scenario 2

**Before**
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

**After**
```java
        val first, second, third, fourth) = list;
```


##### Scenario 3

**Before**
```java
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

**After**
```java
        val ignored21 = data.list.get(4);
        val ignored22 = data.list.get(5);
```


##### Scenario 4

**Before**
```java
        Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);
```

**After**
```java
        var getter1, getter2, getter3) = data.list;
```


##### Scenario 5

**Before**
```java
        Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);
```

**After**
```java
        val deepGetter1, deepGetter2) = data.data.list;
```


##### Scenario 6

**Before**
```java
        Data wrongParent1 = data.getList().get(0);
        Data wrongParent2 = data.getData().getList().get(1);
```

**After**
```java
        val wrongParent1 = data.list.get(0);
        val wrongParent2 = data.data.list.get(1);
```


#### DestructuringAssignmentListWithoutValTestData

##### Scenario 1

**Before**
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

**After**
```java
        Data (first, second, third, fourth) = list;
```


##### Scenario 2

**Before**
```java
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

**After**
```java
        Data ignored21 = data.list.get(4);
        Data ignored22 = data.list.get(5);
```


##### Scenario 3

**Before**
```java
        Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);
```

**After**
```java
        Data (getter1, getter2, getter3) = data.list;
```


##### Scenario 4

**Before**
```java
        Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);
```

**After**
```java
        Data (deepGetter1, deepGetter2) = data.data.list;
```


##### Scenario 5

**Before**
```java
        Data wrongParent1 = data.getList().get(0);
        Data wrongParent2 = data.getData().getList().get(1);
```

**After**
```java
        Data wrongParent1 = data.list.get(0);
        Data wrongParent2 = data.data.list.get(1);
```
