---
title: Final Emoji
slug: /features/final-emoji
sidebar_label: Final Emoji
description: Replaces final modifiers with lock emoji markers.
---

:::info Toggle summary
- **State key:** `finalEmoji`
- **Default:** Off
- **Controlled by:** `finalEmoji`
:::

## Final Emoji
Replaces final modifiers with lock emoji markers.

### Example: FinalEmojiTestData

examples/data/FinalEmojiTestData.java:
```java
    public final String m() {
        final String s = "1";
        final var s2 = "2";
// ...
        void main(final String arg, final int i, final Object o, Data data);
```

folded/FinalEmojiTestData-folded.java:
```java
    public 🔒 String m() {
        🔒 String s = "1";
        🔒 var s2 = "2";
// ...
        void main(🔒 String arg, 🔒 int i, 🔒 Object o, Data data);
```

Highlights FinalEmojiTestData with final emoji.
Removes boilerplate while preserving behavior.
