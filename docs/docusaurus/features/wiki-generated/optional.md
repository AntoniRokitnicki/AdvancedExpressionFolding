---
title: Optional
slug: /features/optional
sidebar_label: Optional
description: Displays java.util.Optional flows as Kotlin-style null-safe chains.
---

:::info Toggle summary
- **State key:** `optional`
- **Default:** On
- **Controlled by:** `optional`
:::

## Optional
Displays java.util.Optional flows as Kotlin-style null-safe chains.

### Example: ConcatenationTestData

examples/data/ConcatenationTestData.java:
```java
        list.add("one");
        list.remove("one");
// ...
        list.addAll(singleton);
        list.removeAll(singleton);
        Collections.addAll(list, args);
```

folded/ConcatenationTestData-folded.java:
```java
        list += "one";
        list -= "one";
// ...
        list += singleton;
        list -= singleton;
        list += args;
```

Highlights ConcatenationTestData with optional.
Removes boilerplate while preserving behavior.

### Example: OptionalTestData

examples/data/OptionalTestData.java:
```java
            o = opt.get();
// ...
        o = opt.orElseThrow();
```

folded/OptionalTestData-folded.java:
```java
            o = opt!!;
// ...
        o = opt!!;
```

Highlights OptionalTestData with optional.
Removes boilerplate while preserving behavior.

### Example: SpreadTestData

examples/data/SpreadTestData.java:
```java
        String empNames = list.stream()
                .map(Data::getString)
// ...
        var p1 = data.getDataList().stream().map(Data::getData).toList();
```

folded/SpreadTestData-folded.java:
```java
        String empNames = list*.string()
// ...
        var p1 = data.getDataList().stream()*.data().toList();
```

Highlights SpreadTestData with optional.
Removes boilerplate while preserving behavior.
