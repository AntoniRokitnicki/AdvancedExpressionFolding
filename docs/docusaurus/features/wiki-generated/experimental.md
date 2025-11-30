---
title: Experimental
slug: /features/experimental
sidebar_label: Experimental
description: Enables experimental folding prototypes.
---

:::info Toggle summary
- **State key:** `experimental`
- **Default:** Off
- **Controlled by:** `experimental`
:::

## Experimental
Enables experimental folding prototypes.

### Example: ExperimentalTestData

examples/data/ExperimentalTestData.java:
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
// ...
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```

folded/ExperimentalTestData-folded.java:
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].getBytes();
// ...
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```

Highlights ExperimentalTestData with experimental.
Removes boilerplate while preserving behavior.
