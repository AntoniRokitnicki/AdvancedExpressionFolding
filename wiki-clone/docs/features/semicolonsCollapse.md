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
---

#### Folding catalogue

##### SemicolonTestData mappings
| Before | After |
| --- | --- |
| `package data;` | `package data` |
| `import java.util.Arrays;` | `import java.util.Arrays` |
| `System.out.println(arg);` | `System.out.println(arg)` |
| `Arrays.stream(args).forEach(System.out::println);` | `Arrays.stream(args).forEach(System.out::println)` |
