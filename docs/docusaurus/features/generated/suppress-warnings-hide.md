---
title: Suppress Warnings Hide
slug: /features/suppress-warnings-hide
sidebar_label: Suppress Warnings Hide
description: Hides @SuppressWarnings annotations from view.
---

:::info Toggle summary
- **State key:** `suppressWarningsHide`
- **Default:** On
- **Controlled by:** `suppressWarningsHide`
:::

## Suppress Warnings Hide
Hides @SuppressWarnings annotations from view.

### Example: SuppressWarningsHideTestData

examples/data/SuppressWarningsHideTestData.java:
```java
    @SuppressWarnings("deprecation")
// ...
    @SuppressWarnings({"rawtypes", "unchecked"})
```

folded/SuppressWarningsHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
```

Highlights SuppressWarningsHideTestData with suppress warnings hide.
Removes boilerplate while preserving behavior.
