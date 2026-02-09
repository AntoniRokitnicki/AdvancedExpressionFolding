---
title: Log Folding Text Blocks
slug: /features/log-folding-text-blocks
sidebar_label: Log Folding Text Blocks
description: Folds log Text Blocks into compact placeholders.
---

:::info Toggle summary
- **State key:** `logFoldingTextBlocks`
- **Default:** Off
- **Controlled by:** `logFoldingTextBlocks`
:::

## Log Folding Text Blocks
Folds log Text Blocks into compact placeholders.

### Example: LogFoldingTextBlocksTestData

examples/data/LogFoldingTextBlocksTestData.java:
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

folded/LogFoldingTextBlocksTestData-folded.java:
```java
        log.debug("Debug message with 1 parameter - Name: $name");
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```

Highlights LogFoldingTextBlocksTestData with log folding text blocks.
Removes boilerplate while preserving behavior.
