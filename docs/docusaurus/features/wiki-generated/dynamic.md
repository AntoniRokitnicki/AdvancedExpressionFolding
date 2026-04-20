---
title: Dynamic
slug: /features/dynamic
sidebar_label: Dynamic
description: Applies dynamic naming to methods based on a configuration file.
---

:::info Toggle summary
- **State key:** `dynamic`
- **Default:** On
- **Controlled by:** `dynamic`
:::

## Dynamic names for methods based on $user.home/dynamic-ajf2.toml
Applies dynamic naming to methods based on a configuration file.

### Example: DynamicTestData

examples/data/DynamicTestData.java:
```java
    public static void staticMethod(Data data) {
// ...
                .normalMethod(
                        staticMethod(
```

folded/DynamicTestData-folded.java:
```java
    public static void changedStaticMethod(Data data) {
// ...
                .changedNormalMethod(
                        changedStaticMethod(
```

Highlights DynamicTestData with dynamic names for methods based on $user.home/dynamic-ajf2.toml.
Removes boilerplate while preserving behavior.
