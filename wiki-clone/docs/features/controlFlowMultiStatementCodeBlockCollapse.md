# Control Flow Multi Statement Code Block Collapse

## Overview

Folds multi-statement control-flow braces in read-only files.


## Configuration

- **Toggle ID:** `controlFlowMultiStatementCodeBlockCollapse`
- **Default state:** Off
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: ControlFlowMultiStatementTestData

examples/data/ControlFlowMultiStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowMultiStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowMultiStatementTestData with control flow multi statement code block collapse.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### ControlFlowMultiStatementTestData

##### Scenario 1

**Before**
```java
        if (args.length > 0) {
```

**After**
```java
        if (args.length > 0) 
```


##### Scenario 2

**Before**
```java
        }
        if (args.length == 0) {
```

**After**
```java
        if (args.length == 0) 
```


##### Scenario 3

**Before**
```java
        } else {
```

**After**
```java
        else {
```


##### Scenario 4

**Before**
```java
        } else {
```

**After**
```java
        } else 
```


##### Scenario 5

**Before**
```java
        }
```


##### Scenario 6

**Before**
```java
        for (String arg : args) {
```

**After**
```java
        for (String arg : args) 
```


##### Scenario 7

**Before**
```java
        }
        while (true) {
```

**After**
```java
        while (true) 
```


##### Scenario 8

**Before**
```java
        try {
```

**After**
```java
        try 
```


##### Scenario 9

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        catch (Exception e) 
```


##### Scenario 10

**Before**
```java
        }
        do {
```

**After**
```java
        do 
```


##### Scenario 11

**Before**
```java
        } while (true);
```

**After**
```java
        while (true);
```
