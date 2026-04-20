---
title: Local Date Literal Postfix Collapse
slug: /features/local-date-literal-postfix-collapse
sidebar_label: Local Date Literal Postfix Collapse
description: Folds postfix LocalDate literals such as 2018Y-02M-12D.
---

:::info Toggle summary
- **State key:** `localDateLiteralPostfixCollapse`
- **Default:** Off
- **Controlled by:** `localDateLiteralPostfixCollapse`
:::

## Local Date Literal Postfix Collapse
Folds postfix LocalDate literals such as 2018Y-02M-12D.

### Example: LocalDateLiteralPostfixTestData

examples/data/LocalDateLiteralPostfixTestData.java:
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

folded/LocalDateLiteralPostfixTestData-folded.java:
```java
        LocalDate d1 = 2018Y-01M-10D;
        LocalDate d4 = 2018Y-01M-10D;
        LocalDate d2 = 2018Y-12M-10D;
        LocalDate d3 = 2018Y-04M-04D;
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013Y-01M-10D);
```

Highlights LocalDateLiteralPostfixTestData with local date literal postfix collapse.
Removes boilerplate while preserving behavior.
