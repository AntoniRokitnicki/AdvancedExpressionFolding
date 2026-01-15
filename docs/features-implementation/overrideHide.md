---
title: Override Hide (State field: overrideHide)
option: overrideHide
source: wiki-clone/docs/features/overrideHide.md
---
# Override Hide (State field: overrideHide)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:43` – `list.forwardIfEnabled(overrideHide) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:93` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/HidingSuppressionState.kt:10` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:271` – `registerCheckbox(state::overrideHide, "Hide @Override annotation") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:273` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#overrideHide")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:58` – `::overrideHideTestData to 6`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/HidingSuppressionFoldingTest.kt:8` – `fun overrideHideTestData() = testCase.runFoldingTest(foldingState()::overrideHide)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Override Hide (State field: overrideHide)

### Override Hide
Hides @Override annotations from view.

#### Example: OverrideHideTestData

examples/data/OverrideHideTestData.java:
```java
            @Override
// ...
            @Override
```

folded/OverrideHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
```

Highlights OverrideHideTestData with override hide.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `overrideHide`
Related features: (none)

---
### Folding catalogue

#### OverrideHideTestData

##### Scenario 1

**Before**
```java
            @Override
```


##### Scenario 2

**Before**
```java
        @Override
```
