---
title: Get Expressions Collapse
slug: /features/get-expressions-collapse
sidebar_label: Get Expressions Collapse
description: Folds collection access and literal builders into indexed or map-style expressions.
---

:::info Toggle summary
- **State key:** `getExpressionsCollapse`
- **Default:** On
- **Controlled by:** `getExpressionsCollapse`
:::

## Get Expressions Collapse
Folds collection access and literal builders into indexed or map-style expressions.

### Example: GetSetPutTestData

examples/data/GetSetPutTestData.java:
```java
        List<String> list = Arrays.asList("one", "two");
        list.set(1,"three" );
        System.out.println(list.get(list.size() - 1));
        System.out.println(args[args.length - 1]);
// ...
        map.put("one", 1);
        System.out.println(map.get("two"));
        List<String> singleton = java.util.Collections.singletonList("one");
```

folded/GetSetPutTestData-folded.java:
```java
        List<String> list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.getLast());
        System.out.println(args.last());
// ...
        map["one"] = 1;
        System.out.println(map["two"]);
        List<String> singleton = ["one"];
```

Highlights GetSetPutTestData with get expressions collapse.
Removes boilerplate while preserving behavior.

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

Highlights ExperimentalTestData with get expressions collapse.
Removes boilerplate while preserving behavior.
