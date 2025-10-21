---
title: Destructuring Assignments
slug: /features/destructuring
sidebar_label: Destructuring
description: Present array and list destructuring patterns with Kotlin-style tuple syntax.
---

Destructuring folds paired assignment statements into tuple-like bindings so you can reason about value unpacking at a glance.

![Array destructuring folded into tuple view](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/18c812ce-9d12-4003-b4f4-79071421e29c)

## Arrays

Assignments that manually unpack array indices collapse into a single destructuring expression. The plugin also understands variants that omit `var`/`val` declarations or operate on existing variables.

![Array destructuring without var/val folded inline](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/3391702f-8632-4539-9e81-60f52f7ee006)

![Array destructuring applied to existing variables](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/07cee93a-2acd-4719-b244-10ab1bcb89fc)

## Lists

List destructuring follows the same pattern and keeps the ordinal positions readable.

![List destructuring presented as folded binding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/bf6cae98-f1f3-4a90-b545-89c1ebd8d09b)
