---
title: Expression Func
slug: /features/expression-func
sidebar_label: Expression Func
description: Folds single-expression methods into expression-bodied functions.
---

:::info Toggle summary
- **State key:** `expressionFunc`
- **Default:** On
- **Controlled by:** `expressionFunc`
:::

## Expression Func
Folds single-expression methods into expression-bodied functions.

### Example: ExpressionFuncTestData

examples/data/ExpressionFuncTestData.java:
```java
    public long findNinjaId() {
        return 1L;
    }
// ...
    private void printStatus() {
        new HashMap<String, String>().put("a", "b");
    }
```

folded/ExpressionFuncTestData-folded.java:
```java
    findNinjaId { 1L }
// ...
    private void printStatus() { new HashMap<String, String>().put("a", "b") }
```

Highlights ExpressionFuncTestData with expression func.
Removes boilerplate while preserving behavior.
