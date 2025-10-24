# Cast Expressions Collapse

## Overview

Folds explicit type cast calls into concise Kotlin-style expressions.


## Configuration

- **Toggle ID:** `castExpressionsCollapse`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: TypeCastTestData

examples/data/TypeCastTestData.java:
```java
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
```

folded/TypeCastTestData-folded.java:
```java
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
```

Highlights TypeCastTestData with cast expressions collapse.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### TypeCastTestData

##### Scenario 1

**Before**
```java
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
```

**After**
```java
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
```
