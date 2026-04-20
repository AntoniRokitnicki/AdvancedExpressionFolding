---
title: Range Expressions Collapse
slug: /features/range-expressions-collapse
sidebar_label: Range Expressions Collapse
description: Folds indexed loops into Kotlin-style range expressions.
---

:::info Toggle summary
- **State key:** `rangeExpressionsCollapse`
- **Default:** On
- **Controlled by:** `rangeExpressionsCollapse`
:::

## Range Expressions Collapse
Folds indexed loops into Kotlin-style range expressions.

### Example: ForRangeTestData

examples/data/ForRangeTestData.java:
```java
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
// ...
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
```

folded/ForRangeTestData-folded.java:
```java
                for ((int i, String arg) : args) {
// ...
                for (String arg : args) {
```

Highlights ForRangeTestData with range expressions collapse.
Removes boilerplate while preserving behavior.
