---
title: Summary Parent Override (State field: summaryParentOverride)
option: summaryParentOverride
source: wiki-clone/docs/features/summaryParentOverride.md
---
# Summary Parent Override (State field: summaryParentOverride)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiClassExt.kt:28` – `list.addIfEnabled(summaryParentOverride) {`
- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiMethodExt.kt:27` – `if (summaryParentOverride) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:89` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/HidingSuppressionState.kt:12` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:252` – `state::summaryParentOverride,`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:65` – `::summaryParentOverrideTestData to 5`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/HidingSuppressionFoldingTest.kt:14` – `fun summaryParentOverrideTestData() = testCase.runFoldingTest(foldingState()::summaryParentOverride)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Summary Parent Override (State field: summaryParentOverride)

### Summary Parent Override
Folds overridden methods into parent summary stubs.

#### Example: SummaryParentOverrideTestData

examples/data/SummaryParentOverrideTestData.java:
```java
    class ParentClass extends GrandparentClass {
// ...
        public void grandparentMethod() {
```

folded/SummaryParentOverrideTestData-folded.java:
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
// ...
        public void grandparentMethod() { // overrides from GrandparentClass
```

Highlights SummaryParentOverrideTestData with summary parent override.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `summaryParentOverride`
Related features: (none)

---
### Folding catalogue

#### SummaryParentOverrideTestData

##### Scenario 1

**Before**
```java
    class ParentClass extends GrandparentClass {
```

**After**
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
```


##### Scenario 2

**Before**
```java
        public void grandparentMethod() {
```

**After**
```java
        public void grandparentMethod() { // overrides from GrandparentClass
```


##### Scenario 3

**Before**
```java
    public class TestDataClass extends ParentClass implements FirstInterface, SecondInterface, WithoutMethodInterface {
```

**After**
```java
    public class TestDataClass extends ParentClass(1-grandparentMethod) implements FirstInterface(2-interfaceMethodOne, sharedMethod), SecondInterface(1-interfaceMethodTwo), WithoutMethodInterface(nothing overridden) {
```


##### Scenario 4

**Before**
```java
        }
```

**After**
```java
        } // overrides from FirstInterface
```


##### Scenario 5

**Before**
```java
        }
```

**After**
```java
        } // overrides from SecondInterface
```


##### Scenario 6

**Before**
```java
        }
```

**After**
```java
        } // overrides from ParentClass
```
