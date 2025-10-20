# Suppress Warnings Hide (State field: suppressWarningsHide)

### Suppress Warnings Hide
Hides @SuppressWarnings annotations from view.

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

Default: On
Controlled by: `suppressWarningsHide`
Related features: (none)
