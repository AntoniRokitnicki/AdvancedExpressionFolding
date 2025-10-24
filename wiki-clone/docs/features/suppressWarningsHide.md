# Suppress Warnings Hide

## Overview

Hides @SuppressWarnings annotations from view.


## Configuration

- **Toggle ID:** `suppressWarningsHide`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: SuppressWarningsHideTestData

examples/data/SuppressWarningsHideTestData.java:
```java
    @SuppressWarnings("deprecation")
// ...
    @SuppressWarnings({"rawtypes", "unchecked"})
```

folded/SuppressWarningsHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
```

Highlights SuppressWarningsHideTestData with suppress warnings hide.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### SuppressWarningsHideTestData

##### Scenario 1

**Before**
```java
    @SuppressWarnings("deprecation")
```


##### Scenario 2

**Before**
```java
    @SuppressWarnings({"rawtypes", "unchecked"})
```
