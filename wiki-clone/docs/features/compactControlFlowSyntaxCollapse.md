# Compact Control Flow Syntax Collapse (State field: compactControlFlowSyntaxCollapse)

### Compact Control Flow Syntax Collapse
Folds compact if/else syntax inspired by Go.

#### Example: CompactControlFlowTestData

examples/data/CompactControlFlowTestData.java:
```java
        if (args.length > 0) {
// ...
        for (String arg : args) {
```

folded/CompactControlFlowTestData-folded.java:
```java
        if args.length > 0 {
// ...
        for String arg : args {
```

Highlights CompactControlFlowTestData with compact control flow syntax collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `compactControlFlowSyntaxCollapse`
Related features: (none)

---
### Folding catalogue

#### CompactControlFlowTestData

##### Scenario 1

**Before**
```java
        if (args.length > 0) {
```

**After**
```java
        if args.length > 0 {
```


##### Scenario 2

**Before**
```java
        for (String arg : args) {
```

**After**
```java
        for String arg : args {
```


##### Scenario 3

**Before**
```java
        for (int i = 0; i < args.length; i++) {
```

**After**
```java
        for int i = 0; i < args.length; i++ {
```


##### Scenario 4

**Before**
```java
        while (true) {
```

**After**
```java
        while true {
```


##### Scenario 5

**Before**
```java
        } while (true);
        switch (args.length) {
```

**After**
```java
        } while true;
        switch args.length {
```


##### Scenario 6

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        } catch Exception e {
```

