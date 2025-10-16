---
title: Lombok Dirty Off
slug: /features/lombok-dirty-off
sidebar_label: Lombok Dirty Off
description: Skips folding Lombok accessors marked as dirty.
---

:::info Toggle summary
- **State key:** `lombokDirtyOff`
- **Default:** Off
- **Controlled by:** `lombokDirtyOff`
:::

## Lombok Dirty Off
Skips folding Lombok accessors marked as dirty.

### Example: LombokDirtyOffTestData

examples/data/LombokDirtyOffTestData.java:
```java
        public class DirtyData {
// ...
            private boolean ok;
```

folded/LombokDirtyOffTestData-folded.java:
```java
        @EqualsAndHashCode public class DirtyData {
// ...
            @Getter private boolean ok;
```

Highlights LombokDirtyOffTestData with lombok dirty off.
Removes boilerplate while preserving behavior.
