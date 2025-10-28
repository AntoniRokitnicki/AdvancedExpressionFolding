---
title: Null-Safe If Folding
slug: /features/null-safe-if
sidebar_label: Null-Safe If
description: Collapse explicit null-guard conditionals into Kotlin-inspired safe calls.
---

`ifNullSafe` recognises the boilerplate pattern where code checks an object for null before dereferencing it. When enabled, the nested checks shrink into a compact Kotlin-style safe call, dramatically improving readability in legacy codebases.

[Watch the feature in action](https://www.youtube.com/watch?v=zvpvhn7ISAw).

![Null-safe if folding showcased on chained access](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/56aa2dbb-0aa1-4143-a296-801ffb0668cd)

## Structural search recipes

Use the IDE's structural search dialog to experiment with your own guard conditions. Two templates illustrate the kind of branches the plugin recognises:

```
$Instance$ != null && $Instance$.$MethodCall$() != null
```

![Structural search template for null-safe guard with method call](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/ce93188e-72bf-4a5b-8bff-cc6fb8fb3c76)

```
$Instance$ != null && $Instance$.$MethodCall$()
```

![Structural search template for null-check followed by usage](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/3eb6192c-b69e-4841-b28f-8edae3a95636)

## Guarded println examples

When the guarded branch only prints the dereferenced value, the plugin can drop the whole `if` statement and emit a single null-safe call:

```java title="Before"
if (user != null
        && user.getProfile() != null
        && user.getProfile().getName() != null) {
    System.out.println(user.getProfile().getName());
}
```

```java title="After"
System.out.println(user?.profile?.name);
```

If the println body performs extra work (for example, string concatenation), the guard remains while dereferences inside the block adopt safe-call syntax:

```java title="Before"
if (user != null
        && user.getProfile() != null
        && user.getProfile().getName() != null) {
    System.out.println("Name: " + user.getProfile().getName());
}
```

```java title="After"
if (user?.profile?.name != null) {
    System.out.println("Name: " + user.profile.name);
}
```

## Example files

- [Source sample](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/examples/data/IfNullSafeData.java)
- [Folded view](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/folded/IfNullSafeData-folded.java)
