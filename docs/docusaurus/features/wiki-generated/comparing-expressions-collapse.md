---
title: Comparing Expressions Collapse
slug: /features/comparing-expressions-collapse
sidebar_label: Comparing Expressions Collapse
description: Folds equals and compareTo calls into direct comparison expressions.
---

:::info Toggle summary
- **State key:** `comparingExpressionsCollapse`
- **Default:** On
- **Controlled by:** `comparingExpressionsCollapse`
:::

## Comparing Expressions Collapse
Folds equals and compareTo calls into direct comparison expressions.

### Example: EqualsCompareTestData

examples/data/EqualsCompareTestData.java:
```java
        System.out.println(a.equals(b));
        System.out.println(!a.equals(b));
        System.out.println(a.compareTo(b) == 0);
        System.out.println(a.compareTo(b) != 0);
// ...
        System.out.println(a.compareTo(b) > 0);
        System.out.println(a.compareTo(b) == 1);
        System.out.println(a.compareTo(b) > -1);
        System.out.println(a.compareTo(b) >= 0); // Should be a >= b
```

folded/EqualsCompareTestData-folded.java:
```java
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
// ...
        System.out.println(a > b);
        System.out.println(a > b);
        System.out.println(a ≥ b);
        System.out.println(a ≥ b); // Should be a >= b
```

Highlights EqualsCompareTestData with comparing expressions collapse.
Removes boilerplate while preserving behavior.
