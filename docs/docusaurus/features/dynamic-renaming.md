---
title: Dynamic Method Renaming
slug: /features/dynamic-renaming
sidebar_label: Dynamic Renaming
description: Pull friendly display names for methods from an external configuration file.
---

Enable **dynamic** to map fully-qualified method names to human-friendly labels stored in `PathManager.getConfigDir()/advanced-expression-folding/dynamic-ajf2.toml`. The plugin reads the file on the fly and displays the alias wherever the method appears, making domain-specific APIs easier to reason about.

![Dynamic renaming applied to method calls](https://github.com/user-attachments/assets/250e6884-6254-4707-85ac-7c861d3773f2)

- [Source sample](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/DynamicTestData.java)
- [Folded view](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/DynamicTestData-folded.java)
