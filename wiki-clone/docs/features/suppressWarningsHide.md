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
---

#### Folding catalogue

##### SuppressWarningsHideTestData mappings
| Before | After |
| --- | --- |
| `@SuppressWarnings("deprecation") public SuppressWarningsHideTestData() {` | `public SuppressWarningsHideTestData() {` |
| `@SuppressWarnings({"rawtypes", "unchecked"}) public void methodWithWarnings() {` | `public void methodWithWarnings() {` |
| `@SuppressWarnings("unused") int unusedLocalVar = 42;` | `@SuppressWarnings("unused") int unusedLocalVar = 42;` (unchanged local example) |
| `@SuppressWarnings("deprecation") Date oldDate = new Date();` | `@SuppressWarnings("deprecation") Date oldDate = new Date();` (locals remain visible) |
