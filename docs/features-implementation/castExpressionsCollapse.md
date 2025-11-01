---
title: Cast Expressions Collapse (State field: castExpressionsCollapse)
option: castExpressionsCollapse
source: wiki-clone/docs/features/castExpressionsCollapse.md
---
# Cast Expressions Collapse (State field: castExpressionsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/core/ExpressionBuilders.kt:85` – `override fun checkConditions(element: PsiParenthesizedExpression) = castExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/core/ExpressionBuilders.kt:111` – `override fun checkConditions(element: PsiTypeCastExpression) = castExpressionsCollapse`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:55` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt:17` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:89` – `registerCheckbox(state::castExpressionsCollapse, "Type cast expressions") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt:35` – `fun typeCastTestData() = testCase.runFoldingTest(foldingState()::castExpressionsCollapse)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Cast Expressions Collapse (State field: castExpressionsCollapse)

### Cast Expressions Collapse
Folds explicit type cast calls into concise Kotlin-style expressions.

#### Example: TypeCastTestData

examples/data/TypeCastTestData.java:
```java
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
```

folded/TypeCastTestData-folded.java:
```java
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
```

Highlights TypeCastTestData with cast expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `castExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### TypeCastTestData

##### Scenario 1

**Before**
```java
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
```

**After**
```java
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
```
