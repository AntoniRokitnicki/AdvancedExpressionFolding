---
title: Field Shift Folding
slug: /features/field-shift
sidebar_label: Field Shift
description: Collapse verbose getter/setter pairs and defensive copies into property-style assignments.
---

Field Shift recognises repetitive field access patterns and folds them into a compact property syntax. It makes bean-style classes far easier to scan, especially when assignments and validation logic interleave.

[Video walkthrough](https://youtu.be/qANBuozPpvM)

![Animated preview of field shift folding](https://github.com/user-attachments/assets/1e314518-72a3-445f-a966-e13608a3c678)

## Assignments

Direct assignments collapse into a property view so setters and getters line up neatly.

![Field assignment folded into property-style access](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/9b44c642-0edf-408f-98f2-5eae06b1216b)

## Defensive copies

The plugin spots defensive copy patterns and folds them while keeping the intent visible.

![Defensive copy highlighting before applying folding](https://github.com/user-attachments/assets/25ef4342-a83d-44f1-9af5-ef2fb83de0fb)

![Defensive copy collapsed by field shift folding](https://github.com/user-attachments/assets/52b5d9b8-afed-4310-a309-8f4016433540)

## Preconditions integration

`Preconditions.checkNotNull` blocks are folded to highlight the contract directly next to the field.

<img alt="Preconditions.checkNotNull folding preview" src="https://github.com/user-attachments/assets/345f1c8d-3487-49b7-a384-f5875dd69458" />
