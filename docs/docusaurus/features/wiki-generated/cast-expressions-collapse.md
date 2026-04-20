---
title: Cast Expressions Collapse
slug: /features/cast-expressions-collapse
sidebar_label: Cast Expressions Collapse
description: Folds explicit type cast calls into concise Kotlin-style expressions.
---

:::info Toggle summary
- **State key:** `castExpressionsCollapse`
- **Default:** On
- **Controlled by:** `castExpressionsCollapse`
:::

## Cast Expressions Collapse
Folds explicit type cast calls into concise Kotlin-style expressions.

### Example: TypeCastTestData

examples/data/TypeCastTestData.java:
```java
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
```

folded/TypeCastTestData-folded.java:
```java
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
```

Highlights TypeCastTestData with cast expressions collapse.
Removes boilerplate while preserving behavior.
