---
title: Println
slug: /features/println
sidebar_label: Println
description: Folds System.out.println calls into println.
---

:::info Toggle summary
- **State key:** `println`
- **Default:** On
- **Controlled by:** `println`
:::

![System.out.println call folded to println](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/75a5224f-7b52-4b71-9774-2814e8a867ba)


## Println
Folds System.out.println calls into println.

### Example: PrintlnTestData

examples/data/PrintlnTestData.java:
```java
        System.out.println("Hello");
        System.out.println
// ...
        System.
                out.println("Spacing");
        System.out.
// ...
        System.out.println("Passed as parameter: " +
this.getClass());
        System.out.println("""
```

folded/PrintlnTestData-folded.java:
```java
        println("Hello");
        println
// ...
        println("Spacing");
        println(3.14);
        println(string);
// ...
        println("Passed as parameter: " + string);
        println("Passed as parameter: " + this.getClass());
        println("""
```

Highlights PrintlnTestData with println.
Removes boilerplate while preserving behavior.
