---
title: Summary Parent Override
slug: /features/summary-parent-override
sidebar_label: Summary Parent Override
description: Folds overridden methods into parent summary stubs.
---

:::info Toggle summary
- **State key:** `summaryParentOverride`
- **Default:** Off
- **Controlled by:** `summaryParentOverride`
:::

## Summary Parent Override
Folds overridden methods into parent summary stubs.

### Example: SummaryParentOverrideTestData

examples/data/SummaryParentOverrideTestData.java:
```java
    class ParentClass extends GrandparentClass {
// ...
        public void grandparentMethod() {
```

folded/SummaryParentOverrideTestData-folded.java:
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
// ...
        public void grandparentMethod() { // overrides from GrandparentClass
```

Highlights SummaryParentOverrideTestData with summary parent override.
Removes boilerplate while preserving behavior.
