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
