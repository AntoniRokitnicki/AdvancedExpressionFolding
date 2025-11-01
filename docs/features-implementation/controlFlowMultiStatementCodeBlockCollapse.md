---
title: Control Flow Multi Statement Code Block Collapse (State field: controlFlowMultiStatementCodeBlockCollapse)
option: controlFlowMultiStatementCodeBlockCollapse
source: wiki-clone/docs/features/controlFlowMultiStatementCodeBlockCollapse.md
---
# Control Flow Multi Statement Code Block Collapse (State field: controlFlowMultiStatementCodeBlockCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiCodeBlockExt.kt:34` – `if (controlFlowMultiStatementCodeBlockCollapse && !element.isWritable) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:60` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ControlFlowState.kt:12` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:167` – `state::controlFlowMultiStatementCodeBlockCollapse,`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ControlFlowFoldingTest.kt:14` – `foldingState()::controlFlowMultiStatementCodeBlockCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Control Flow Multi Statement Code Block Collapse (State field: controlFlowMultiStatementCodeBlockCollapse)

### Control Flow Multi Statement Code Block Collapse
Folds multi-statement control-flow braces in read-only files.

#### Example: ControlFlowMultiStatementTestData

examples/data/ControlFlowMultiStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowMultiStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowMultiStatementTestData with control flow multi statement code block collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `controlFlowMultiStatementCodeBlockCollapse`
Related features: (none)

---
### Folding catalogue

#### ControlFlowMultiStatementTestData

##### Scenario 1

**Before**
```java
        if (args.length > 0) {
```

**After**
```java
        if (args.length > 0) 
```


##### Scenario 2

**Before**
```java
        }
        if (args.length == 0) {
```

**After**
```java
        if (args.length == 0) 
```


##### Scenario 3

**Before**
```java
        } else {
```

**After**
```java
        else {
```


##### Scenario 4

**Before**
```java
        } else {
```

**After**
```java
        } else 
```


##### Scenario 5

**Before**
```java
        }
```


##### Scenario 6

**Before**
```java
        for (String arg : args) {
```

**After**
```java
        for (String arg : args) 
```


##### Scenario 7

**Before**
```java
        }
        while (true) {
```

**After**
```java
        while (true) 
```


##### Scenario 8

**Before**
```java
        try {
```

**After**
```java
        try 
```


##### Scenario 9

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        catch (Exception e) 
```


##### Scenario 10

**Before**
```java
        }
        do {
```

**After**
```java
        do 
```


##### Scenario 11

**Before**
```java
        } while (true);
```

**After**
```java
        while (true);
```
