---
title: Asserts Collapse (State field: assertsCollapse)
option: assertsCollapse
source: wiki-clone/docs/features/assertsCollapse.md
---
# Asserts Collapse (State field: assertsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/expression/controlflow/IfExpression.kt:259` – `if (!assertsCollapse || condition !is PsiBinaryExpression) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:62` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ControlFlowState.kt:14` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:114` – `registerCheckbox(state::assertsCollapse, "Asserts") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:63` – `settings.enableAll(state::assertsCollapse)`
- `test/com/intellij/advancedExpressionFolding/unit/SettingsTest.kt:67` – `if (property.name == "assertsCollapse") {`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ControlFlowFoldingTest.kt:21` – `fun assertTestData() = testCase.runReadOnlyFoldingTest(foldingState()::assertsCollapse)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Asserts Collapse (State field: assertsCollapse)

### Asserts Collapse
Folds assert statements into terse checks.

#### Example: AssertTestData

examples/data/AssertTestData.java:
```java
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
// ...
        }
        if (args.length == 2)
            throw new IllegalArgumentException("...");
```

folded/AssertTestData-folded.java:
```java
        assert args.length != 0;
        assert args.length != 1 : "...";
        assert args.length != 2 : "...";
```

Highlights AssertTestData with asserts collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `assertsCollapse`
Related features: (none)

---
### Folding catalogue

#### AssertTestData

##### Scenario 1

**Before**
```java
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("...");
        }
        if (args.length == 2)
            throw new IllegalArgumentException("...");
```

**After**
```java
        assert args.length != 0;
        assert args.length != 1 : "...";
        assert args.length != 2 : "...";
```
