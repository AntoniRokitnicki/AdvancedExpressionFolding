# Control Flow Multi Statement Code Block Collapse (State field: controlFlowMultiStatementCodeBlockCollapse)

### Control Flow Multi Statement Code Block Collapse
Folds multi-statement control-flow braces in read-only files.

#### Example: ControlFlowMultiStatementTestData

examples/data/ControlFlowMultiStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowMultiStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowMultiStatementTestData with control flow multi statement code block collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `controlFlowMultiStatementCodeBlockCollapse`
Related features: (none)
