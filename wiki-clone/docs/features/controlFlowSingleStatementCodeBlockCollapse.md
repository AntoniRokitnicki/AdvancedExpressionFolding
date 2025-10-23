# Control Flow Single Statement Code Block Collapse (State field: controlFlowSingleStatementCodeBlockCollapse)

### Control Flow Single Statement Code Block Collapse
Folds single-statement control-flow braces in read-only files.

#### Example: ControlFlowSingleStatementTestData

examples/data/ControlFlowSingleStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowSingleStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowSingleStatementTestData with control flow single statement code block collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `controlFlowSingleStatementCodeBlockCollapse`
Related features: (none)
