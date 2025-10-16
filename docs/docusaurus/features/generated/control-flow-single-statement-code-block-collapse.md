---
title: Control Flow Single Statement Code Block Collapse
slug: /features/control-flow-single-statement-code-block-collapse
sidebar_label: Control Flow Single Statement Code Block Collapse
description: Folds single-statement control-flow braces in read-only files.
---

:::info Toggle summary
- **State key:** `controlFlowSingleStatementCodeBlockCollapse`
- **Default:** Off
- **Controlled by:** `controlFlowSingleStatementCodeBlockCollapse`
:::

## Control Flow Single Statement Code Block Collapse
Folds single-statement control-flow braces in read-only files.

### Example: ControlFlowSingleStatementTestData

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
