---
title: Compact Control Flow Syntax Collapse
slug: /features/compact-control-flow-syntax-collapse
sidebar_label: Compact Control Flow Syntax Collapse
description: Folds compact if/else syntax inspired by Go.
---

:::info Toggle summary
- **State key:** `compactControlFlowSyntaxCollapse`
- **Default:** Off
- **Controlled by:** `compactControlFlowSyntaxCollapse`
:::

## Compact Control Flow Syntax Collapse
Folds compact if/else syntax inspired by Go.

### Example: CompactControlFlowTestData

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
