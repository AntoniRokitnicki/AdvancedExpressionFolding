---
title: Override Hide
slug: /features/override-hide
sidebar_label: Override Hide
description: Hides @Override annotations from view.
---

:::info Toggle summary
- **State key:** `overrideHide`
- **Default:** On
- **Controlled by:** `overrideHide`
:::

## Override Hide
Hides @Override annotations from view.

### Example: OverrideHideTestData

examples/data/OverrideHideTestData.java:
```java
            @Override
// ...
            @Override
```

folded/OverrideHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
```

Highlights OverrideHideTestData with override hide.
Removes boilerplate while preserving behavior.
