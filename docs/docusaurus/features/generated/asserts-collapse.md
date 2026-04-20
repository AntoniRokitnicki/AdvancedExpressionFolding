---
title: Asserts Collapse
slug: /features/asserts-collapse
sidebar_label: Asserts Collapse
description: Folds assert statements into terse checks.
---

:::info Toggle summary
- **State key:** `assertsCollapse`
- **Default:** On
- **Controlled by:** `assertsCollapse`
:::

## Asserts Collapse
Folds assert statements into terse checks.

### Example: AssertTestData

examples/data/AssertTestData.java:
```java
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
// ...
        }
        if (args.length == 2)
            throw new IllegalArgumentException("...");
```

folded/AssertTestData-folded.java:
```java
        assert args.length != 0;
        assert args.length != 1 : "...";
        assert args.length != 2 : "...";
```

Highlights AssertTestData with asserts collapse.
Removes boilerplate while preserving behavior.
