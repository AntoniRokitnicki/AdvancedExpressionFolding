---
title: If Null Safe
slug: /features/if-null-safe
sidebar_label: If Null Safe
description: Extends null-safe folding to if statements and guard clauses.
---

:::info Toggle summary
- **State key:** `ifNullSafe`
- **Default:** On
- **Controlled by:** `ifNullSafe`
:::

[video](https://www.youtube.com/watch?v=zvpvhn7ISAw)


![Null-safe if folding showcased on chained access](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/56aa2dbb-0aa1-4143-a296-801ffb0668cd)



## If Null Safe
Extends null-safe folding to if statements and guard clauses.

### Example: IfNullSafeData

examples/data/IfNullSafeData.java:
```java
        var threeChains = data != null
                && data.getData1() != null
// ...
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

folded/IfNullSafeData-folded.java:
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
// ...
                && data?.data1?.active == true;
```

Highlights IfNullSafeData with if null safe.
Removes boilerplate while preserving behavior.
