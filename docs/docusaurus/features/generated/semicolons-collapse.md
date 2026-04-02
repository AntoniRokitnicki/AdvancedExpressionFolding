---
title: Semicolons Collapse
slug: /features/semicolons-collapse
sidebar_label: Semicolons Collapse
description: Folds redundant semicolons inside read-only files.
---

:::info Toggle summary
- **State key:** `semicolonsCollapse`
- **Default:** Off
- **Controlled by:** `semicolonsCollapse`
:::

## Semicolons Collapse
Folds redundant semicolons inside read-only files.

### Example: SemicolonTestData

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
