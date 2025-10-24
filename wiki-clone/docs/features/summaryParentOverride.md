# Summary Parent Override

## Overview

Folds overridden methods into parent summary stubs.


## Configuration

- **Toggle ID:** `summaryParentOverride`
- **Default state:** Off
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: SummaryParentOverrideTestData

examples/data/SummaryParentOverrideTestData.java:
```java
    class ParentClass extends GrandparentClass {
// ...
        public void grandparentMethod() {
```

folded/SummaryParentOverrideTestData-folded.java:
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
// ...
        public void grandparentMethod() { // overrides from GrandparentClass
```

Highlights SummaryParentOverrideTestData with summary parent override.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### SummaryParentOverrideTestData

##### Scenario 1

**Before**
```java
    class ParentClass extends GrandparentClass {
```

**After**
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
```


##### Scenario 2

**Before**
```java
        public void grandparentMethod() {
```

**After**
```java
        public void grandparentMethod() { // overrides from GrandparentClass
```


##### Scenario 3

**Before**
```java
    public class TestDataClass extends ParentClass implements FirstInterface, SecondInterface, WithoutMethodInterface {
```

**After**
```java
    public class TestDataClass extends ParentClass(1-grandparentMethod) implements FirstInterface(2-interfaceMethodOne, sharedMethod), SecondInterface(1-interfaceMethodTwo), WithoutMethodInterface(nothing overridden) {
```


##### Scenario 4

**Before**
```java
        }
```

**After**
```java
        } // overrides from FirstInterface
```


##### Scenario 5

**Before**
```java
        }
```

**After**
```java
        } // overrides from SecondInterface
```


##### Scenario 6

**Before**
```java
        }
```

**After**
```java
        } // overrides from ParentClass
```
