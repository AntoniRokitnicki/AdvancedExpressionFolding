---
title: Compact Control Flow Syntax Collapse (State field: compactControlFlowSyntaxCollapse)
option: compactControlFlowSyntaxCollapse
source: wiki-clone/docs/features/compactControlFlowSyntaxCollapse.md
---
# Compact Control Flow Syntax Collapse (State field: compactControlFlowSyntaxCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachIndexedStatement.kt:35` – `if (compactControlFlowSyntaxCollapse && prefix == "(") {`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachIndexedStatement.kt:60` – `val middlePlaceholder = if (compactControlFlowSyntaxCollapse) " : " else ") : "`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachStatement.kt:30` – `if (compactControlFlowSyntaxCollapse &&`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForEachStatement.kt:53` – `val placeholder = if (compactControlFlowSyntaxCollapse) {`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/ForStatement.kt:35` – `if (compactControlFlowSyntaxCollapse && statement.lParenth != null && statement.rParenth != null) {`
- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:251` – `return compactControlFlowSyntaxCollapse &&`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/ForStatementExpressionExt.kt:125` – `compactControlFlowSyntaxCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/controlflow/IfExt.kt:47` – `return if (element.expression != null && compactControlFlowSyntaxCollapse) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:59` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ControlFlowState.kt:11` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:38` – `state::compactControlFlowSyntaxCollapse,`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ControlFlowFoldingTest.kt:18` – `fun compactControlFlowTestData() = testCase.runFoldingTest(foldingState()::compactControlFlowSyntaxCollapse)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Compact Control Flow Syntax Collapse (State field: compactControlFlowSyntaxCollapse)

### Compact Control Flow Syntax Collapse
Folds compact if/else syntax inspired by Go.

#### Example: CompactControlFlowTestData

examples/data/CompactControlFlowTestData.java:
```java
        if (args.length > 0) {
// ...
        for (String arg : args) {
```

folded/CompactControlFlowTestData-folded.java:
```java
        if args.length > 0 {
// ...
        for String arg : args {
```

Highlights CompactControlFlowTestData with compact control flow syntax collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `compactControlFlowSyntaxCollapse`
Related features: (none)

---
### Folding catalogue

#### CompactControlFlowTestData

##### Scenario 1

**Before**
```java
        if (args.length > 0) {
```

**After**
```java
        if args.length > 0 {
```


##### Scenario 2

**Before**
```java
        for (String arg : args) {
```

**After**
```java
        for String arg : args {
```


##### Scenario 3

**Before**
```java
        for (int i = 0; i < args.length; i++) {
```

**After**
```java
        for int i = 0; i < args.length; i++ {
```


##### Scenario 4

**Before**
```java
        while (true) {
```

**After**
```java
        while true {
```


##### Scenario 5

**Before**
```java
        } while (true);
        switch (args.length) {
```

**After**
```java
        } while true;
        switch args.length {
```


##### Scenario 6

**Before**
```java
        } catch (Exception e) {
```

**After**
```java
        } catch Exception e {
```
