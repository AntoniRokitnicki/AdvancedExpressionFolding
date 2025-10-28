# Override Hide (State field: overrideHide)

### Override Hide
Hides @Override annotations from view.

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

Default: On
Controlled by: `overrideHide`
Related features: (none)

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

