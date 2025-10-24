# Slicing Expressions Collapse

## Overview

Folds List.subList and String.substring calls into concise slice notation.


## Configuration

- **Toggle ID:** `slicingExpressionsCollapse`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: SliceTestData

examples/data/SliceTestData.java:
```java
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(1, 2));
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, list.size() - 2));
        System.out.println(list.subList(0, list.size() - 2));
// ...
        System.out.println(f.substring(1));
        System.out.println(f.substring(1, 2));
        System.out.println(f.substring(1, f.length()));
        System.out.println(f.substring(0, 2));
        System.out.println(f.substring(1, f.length() - 2));
        System.out.println(f.substring(0, f.length() - 2));
```

folded/SliceTestData-folded.java:
```java
        System.out.println(list[1:]);
        System.out.println(list[1:2]);
        System.out.println(list[1:]);
        System.out.println(list[:2]);
        System.out.println(list[1:-2]);
        System.out.println(list[:-2]);
// ...
        System.out.println(f[1:]);
        System.out.println(f[1:2]);
        System.out.println(f[1:]);
        System.out.println(f[:2]);
        System.out.println(f[1:-2]);
        System.out.println(f[:-2]);
```

Highlights SliceTestData with slicing expressions collapse.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### SliceTestData

##### Scenario 1

**Before**
```java
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(1, 2));
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, list.size() - 2));
        System.out.println(list.subList(0, list.size() - 2));
```

**After**
```java
        System.out.println(list[1:]);
        System.out.println(list[1:2]);
        System.out.println(list[1:]);
        System.out.println(list[:2]);
        System.out.println(list[1:-2]);
        System.out.println(list[:-2]);
```


##### Scenario 2

**Before**
```java
        System.out.println(f.substring(1));
        System.out.println(f.substring(1, 2));
        System.out.println(f.substring(1, f.length()));
        System.out.println(f.substring(0, 2));
        System.out.println(f.substring(1, f.length() - 2));
        System.out.println(f.substring(0, f.length() - 2));
```

**After**
```java
        System.out.println(f[1:]);
        System.out.println(f[1:2]);
        System.out.println(f[1:]);
        System.out.println(f[:2]);
        System.out.println(f[1:-2]);
        System.out.println(f[:-2]);
```
