---
title: Suppress Warnings Hide (State field: suppressWarningsHide)
option: suppressWarningsHide
source: wiki-clone/docs/features/suppressWarningsHide.md
---
# Suppress Warnings Hide (State field: suppressWarningsHide)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:48` – `list.forwardIfEnabled(suppressWarningsHide) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:94` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/HidingSuppressionState.kt:11` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:275` – `registerCheckbox(state::suppressWarningsHide, "Hide @SuppressWarnings annotation") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:277` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#suppressWarningsHide")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:66` – `::suppressWarningsHideTestData to 4`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/HidingSuppressionFoldingTest.kt:11` – `fun suppressWarningsHideTestData() = testCase.runFoldingTest(foldingState()::suppressWarningsHide)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Suppress Warnings Hide (State field: suppressWarningsHide)

### Suppress Warnings Hide
Hides @SuppressWarnings annotations from view.

#### Example: SuppressWarningsHideTestData

examples/data/SuppressWarningsHideTestData.java:
```java
    @SuppressWarnings("deprecation")
// ...
    @SuppressWarnings({"rawtypes", "unchecked"})
```

folded/SuppressWarningsHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
```

Highlights SuppressWarningsHideTestData with suppress warnings hide.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `suppressWarningsHide`
Related features: (none)

---
### Folding catalogue

#### SuppressWarningsHideTestData

##### Scenario 1

**Before**
```java
    @SuppressWarnings("deprecation")
```


##### Scenario 2

**Before**
```java
    @SuppressWarnings({"rawtypes", "unchecked"})
```
