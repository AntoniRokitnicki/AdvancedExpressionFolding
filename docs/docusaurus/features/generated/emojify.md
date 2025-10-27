---
title: Emojify
slug: /features/emojify
sidebar_label: Emojify
description: Replaces select syntax elements with emoji equivalents.
---

:::info Toggle summary
- **State key:** `emojify`
- **Default:** Off
- **Controlled by:** `emojify`
:::

## Emojify
Replaces select syntax elements with emoji equivalents.

### Example: EmojifyTestData

examples/data/EmojifyTestData.java:
```java
package data;

import java.time.DayOfWeek;
// ...
public class EmojifyTestData {

    public final class FinalData {
// ...

        public void anotherMethod() {
            final int anotherFinalVariable;
```

folded/EmojifyTestData-folded.java:
```java
📦 data;

🚢 java.time.DayOfWeek;
// ...
public 🏛️ EmojifyTestData {

    public 🔒 🏛️ FinalData {
// ...

        public 💀 anotherMethod() {
            🔒 🔢 anotherFinalVariable;
```

Highlights EmojifyTestData with emojify.
Removes boilerplate while preserving behavior.
