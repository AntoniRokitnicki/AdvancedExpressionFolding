# Semicolons Collapse (State field: semicolonsCollapse)

### Semicolons Collapse
Folds redundant semicolons inside read-only files.

#### Example: SemicolonTestData

examples/data/SemicolonTestData.java:
```java
package data;
// ...
import java.util.Arrays;
```

folded/SemicolonTestData-folded.java:
```java
package data
// ...
import java.util.Arrays
```

Highlights SemicolonTestData with semicolons collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `semicolonsCollapse`
Related features: (none)
