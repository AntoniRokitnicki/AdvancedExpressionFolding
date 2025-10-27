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
### Folding catalogue

#### SemicolonTestData

##### Scenario 1

**Before**
```java
package data;
```

**After**
```java
package data
```


##### Scenario 2

**Before**
```java
import java.util.Arrays;
```

**After**
```java
import java.util.Arrays
```


##### Scenario 3

**Before**
```java
                System.out.println(arg);
```

**After**
```java
                System.out.println(arg)
```


##### Scenario 4

**Before**
```java
                Arrays.stream(args).forEach(System.out::println);
```

**After**
```java
                Arrays.stream(args).forEach(System.out::println)
```

