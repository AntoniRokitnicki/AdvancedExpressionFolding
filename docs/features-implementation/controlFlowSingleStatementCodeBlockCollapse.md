---
title: Control Flow Single Statement Code Block Collapse (State field: controlFlowSingleStatementCodeBlockCollapse)
option: controlFlowSingleStatementCodeBlockCollapse
source: wiki-clone/docs/features/controlFlowSingleStatementCodeBlockCollapse.md
---
# Control Flow Single Statement Code Block Collapse (State field: controlFlowSingleStatementCodeBlockCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiCodeBlockExt.kt:58` – `if (!controlFlowSingleStatementCodeBlockCollapse || element.isWritable) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:58` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ControlFlowState.kt:13` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:159` – `state::controlFlowSingleStatementCodeBlockCollapse,`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ControlFlowFoldingTest.kt:9` – `foldingState()::controlFlowSingleStatementCodeBlockCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Control Flow Single Statement Code Block Collapse (State field: controlFlowSingleStatementCodeBlockCollapse)

### Control Flow Single Statement Code Block Collapse
Folds single-statement control-flow braces in read-only files.

#### Example: ControlFlowSingleStatementTestData

examples/data/ControlFlowSingleStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowSingleStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowSingleStatementTestData with control flow single statement code block collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `controlFlowSingleStatementCodeBlockCollapse`
Related features: (none)

---
### Folding catalogue

#### ControlFlowSingleStatementTestData

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
        else 
```


##### Scenario 4

**Before**
```java
        }
```


##### Scenario 5

**Before**
```java
        } else {
```

**After**
```java
        } else 
```


##### Scenario 6

**Before**
```java
        }
        if (args.length > 0) {
```

**After**
```java
        if (args.length > 0) 
```


##### Scenario 7

**Before**
```java
        } else {
```

**After**
```java
        else {
```


##### Scenario 8

**Before**
```java
        for (String arg : args) {
```

**After**
```java
        for (String arg : args) 
```


##### Scenario 9

**Before**
```java
        }
        for (int i = 0; i < args.length; i++) {
```

**After**
```java
        for (int i = 0; i < args.length; i++) 
```


##### Scenario 10

**Before**
```java
        while (true) {
```

**After**
```java
        while (true) 
```


##### Scenario 11

**Before**
```java
        do {
```

**After**
```java
        do 
```


##### Scenario 12

**Before**
```java
        } while (true);
```

**After**
```java
        while (true);
```


##### Scenario 13

**Before**
```java
        try {
```

**After**
```java
        try 
```


##### Scenario 14

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        catch (Exception e) 
```


##### Scenario 15

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        } catch (Exception e) 
```


##### Scenario 16

**Before**
```java
        }
        try {
```

**After**
```java
        try 
```


##### Scenario 17

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        catch (Exception e) {
```
