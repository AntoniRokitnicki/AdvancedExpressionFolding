---
title: Pattern Matching for instanceof
slug: /features/pattern-matching-instanceof
sidebar_label: `instanceof` Pattern Matching
description: Display Java 16 style pattern matching in earlier codebases without changing source.
---

The folding rewrites verbose `instanceof` checks and casts into a single pattern matching expression, mirroring the syntax introduced in JEP 394.

- [Source sample](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/PatternMatchingInstanceofTestData.java)
- [Folded view](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/PatternMatchingInstanceofTestData-folded.java)

![Pattern matching instanceof code before folding](https://github.com/user-attachments/assets/5a53d3ac-f7c7-4df0-aae0-14eaf2322542)

➡️

![Pattern matching instanceof folded result](https://github.com/user-attachments/assets/652604c5-cd29-4be4-adac-8ee44e9a031d)
