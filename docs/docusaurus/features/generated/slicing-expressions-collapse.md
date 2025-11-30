---
title: Slicing Expressions Collapse
slug: /features/slicing-expressions-collapse
sidebar_label: Slicing Expressions Collapse
description: Folds List.subList and String.substring calls into concise slice notation.
---

:::info Toggle summary
- **State key:** `slicingExpressionsCollapse`
- **Default:** On
- **Controlled by:** `slicingExpressionsCollapse`
:::

## Slicing Expressions Collapse
Folds List.subList and String.substring calls into concise slice notation.

### Example: SliceTestData

examples/data/SliceTestData.java:
```java
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(1, 2));
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, list.size() - 2));
        System.out.println(list.subList(0, list.size() - 2));
// ...
        System.out.println(f.substring(1));
        System.out.println(f.substring(1, 2));
        System.out.println(f.substring(1, f.length()));
        System.out.println(f.substring(0, 2));
        System.out.println(f.substring(1, f.length() - 2));
        System.out.println(f.substring(0, f.length() - 2));
```

folded/SliceTestData-folded.java:
```java
        System.out.println(list[1:]);
        System.out.println(list[1:2]);
        System.out.println(list[1:]);
        System.out.println(list[:2]);
        System.out.println(list[1:-2]);
        System.out.println(list[:-2]);
// ...
        System.out.println(f[1:]);
        System.out.println(f[1:2]);
        System.out.println(f[1:]);
        System.out.println(f[:2]);
        System.out.println(f[1:-2]);
        System.out.println(f[:-2]);
```

Highlights SliceTestData with slicing expressions collapse.
Removes boilerplate while preserving behavior.
