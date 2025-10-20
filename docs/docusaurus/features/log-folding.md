---
title: Log Folding
slug: /features/log-folding
sidebar_label: Log Folding
description: Replace noisy logger argument lists with concise placeholders while editing.
---

The log folding options collapse the bracketed payload inside logging statements so you can focus on the message format. The plugin supports SLF4J, Log4j, and most APIs that follow the `{}` placeholder convention.

![Log statement before folding its bracketed payload](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/948c1f47-9185-4b7c-a8d0-d72f3d064fa5)

Once folded, the method call shows only the format string until you expand it or place the caret on the call.

![Log bracket content folded into a compact placeholder](https://github.com/user-attachments/assets/b1bc0d45-d06d-4f25-a16c-82b9c9fdc31b)

Need to inspect or edit the parameters? Unfold temporarily to reveal the original arguments.

![Log bracket content expanded after unfolding](https://github.com/user-attachments/assets/5dd3e36f-7c4a-45cf-bcb0-3e838e79e3f7)

Enable the **Log folding for text blocks** option to treat Java text blocks the same way; disable it if your team prefers to keep multiline payloads fully visible.
