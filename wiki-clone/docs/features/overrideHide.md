# Override Hide

## Overview

Hides @Override annotations from view.


## Configuration

- **Toggle ID:** `overrideHide`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: OverrideHideTestData

examples/data/OverrideHideTestData.java:
```java
            @Override
// ...
            @Override
```

folded/OverrideHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
```

Highlights OverrideHideTestData with override hide.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### OverrideHideTestData

##### Scenario 1

**Before**
```java
            @Override
```


##### Scenario 2

**Before**
```java
        @Override
```
