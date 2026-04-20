---
title: constructorReferenceNotation
option: constructorReferenceNotation
source: wiki-clone/docs/features/constructorReferenceNotation.md
---
# constructorReferenceNotation

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/declaration/PsiFieldExt.kt:58` – `list.addIfEnabled(constructorReferenceNotation) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:90` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:27` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:260` – `state::constructorReferenceNotation,`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:264` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#constructorReferenceNotation")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:18` – `::constructorReferenceNotationTestData to 6`
- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:19` – `::constructorReferenceNotationWithConstTestData to 6`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:85` – `fun constructorReferenceNotationTestData() = testCase.runFoldingTest(foldingState()::constructorReferenceNotation)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:88` – `fun constructorReferenceNotationWithConstTestData() = testCase.runFoldingTest(`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/KotlinLanguageFoldingTest.kt:89` – `foldingState()::constructorReferenceNotation,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

## constructorReferenceNotation

### Constructor reference notation `::new` and compact field initialization
Simplifies constructor references and inline field initialization.

#### Example: ConstructorReferenceNotationTestData

examples/data/ConstructorReferenceNotationTestData.java:
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
// ...
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
```

folded/ConstructorReferenceNotationTestData-folded.java:
```java
        public static final ConstClass SELF = ::new;
        public static final ConstClass SELF_ANN = ::new{};
// ...
        private static final HashMap<String, String> MAP = ::new;
        private static final HashMap<String, String> MAP2 = ::new;
```

Highlights ConstructorReferenceNotationTestData with constructor reference notation `::new` and compact field initialization.
Removes boilerplate while preserving behavior.

#### Example: ConstructorReferenceNotationWithConstTestData

examples/data/ConstructorReferenceNotationWithConstTestData.java:
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
// ...
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
        private static final Map<String, String> MAP3 = new HashMap<>();
        private static final Map<String, String> MAP_TREE = new TreeMap<>();
        private static final Map<String, String> MAP4 = Map.of();
```

folded/ConstructorReferenceNotationWithConstTestData-folded.java:
```java
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
// ...
        private const HashMap<String, String> MAP = ::new;
        private const HashMap<String, String> MAP2 = ::new;
        private const Map<String, String> MAP3 = new HashMap<>();
        private const Map<String, String> MAP_TREE = new TreeMap<>();
        private const Map<String, String> MAP4 = Map.of();
```

Highlights ConstructorReferenceNotationWithConstTestData with constructor reference notation `::new` and compact field initialization.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `constructorReferenceNotation`
Related features: (none)

---
### Folding catalogue

#### ConstructorReferenceNotationTestData

##### Scenario 1

**Before**
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
```

**After**
```java
        public static final ConstClass SELF = ::new;
        public static final ConstClass SELF_ANN = ::new{};
```


##### Scenario 2

**Before**
```java
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
```

**After**
```java
        private static final HashMap<String, String> MAP = ::new;
        private static final HashMap<String, String> MAP2 = ::new;
```


##### Scenario 3

**Before**
```java
        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST_SINGLE.get(0));
```

**After**
```java
        public static final ConstClass SELF_PARAM_1 = ::new(true);
        public static final ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.get(0));
```


##### Scenario 4

**Before**
```java
        final ConstClass SELF = new ConstClass();
        ConstClass SELF_ANN = new ConstClass() {
        };
```

**After**
```java
        final ConstClass SELF = ::new;
        ConstClass SELF_ANN = ::new{};
```


##### Scenario 5

**Before**
```java
        private final HashMap<String, String> MAP = new HashMap<>();
        private final HashMap<String, String> MAP2 = new HashMap<String, String>();
```

**After**
```java
        private final HashMap<String, String> MAP = ::new;
        private final HashMap<String, String> MAP2 = ::new;
```


##### Scenario 6

**Before**
```java
        public final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));
```

**After**
```java
        public final ConstClass SELF_PARAM_1 = ::new(true);
        public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));
```


#### ConstructorReferenceNotationWithConstTestData

##### Scenario 1

**Before**
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
```

**After**
```java
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
```


##### Scenario 2

**Before**
```java
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
        private static final Map<String, String> MAP3 = new HashMap<>();
        private static final Map<String, String> MAP_TREE = new TreeMap<>();
        private static final Map<String, String> MAP4 = Map.of();
```

**After**
```java
        private const HashMap<String, String> MAP = ::new;
        private const HashMap<String, String> MAP2 = ::new;
        private const Map<String, String> MAP3 = new HashMap<>();
        private const Map<String, String> MAP_TREE = new TreeMap<>();
        private const Map<String, String> MAP4 = Map.of();
```


##### Scenario 3

**Before**
```java
        private static final List<String> LIST = new ArrayList<>();
        private static final List<String> LIST2 = List.of();
        private static final List<String> LIST_SINGLE = List.of("1");
        private static final List<String> LIST_LINKED = new LinkedList<>();
```

**After**
```java
        private const List<String> LIST = new ArrayList<>();
        private const List<String> LIST2 = List.of();
        private const List<String> LIST_SINGLE = List.of("1");
        private const List<String> LIST_LINKED = new LinkedList<>();
```


##### Scenario 4

**Before**
```java
        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST_SINGLE.get(0));
```

**After**
```java
        const ConstClass SELF_PARAM_1 = ::new(true);
        const ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.get(0));
```


##### Scenario 5

**Before**
```java
        public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
```

**After**
```java
        const ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
```


##### Scenario 6

**Before**
```java
        public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
```

**After**
```java
        const ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
```


##### Scenario 7

**Before**
```java
        final ConstClass SELF = new ConstClass();
        ConstClass SELF_ANN = new ConstClass() {
        };
```

**After**
```java
        final ConstClass SELF = ::new;
        ConstClass SELF_ANN = ::new{};
```


##### Scenario 8

**Before**
```java
        private final HashMap<String, String> MAP = new HashMap<>();
        private final HashMap<String, String> MAP2 = new HashMap<String, String>();
```

**After**
```java
        private final HashMap<String, String> MAP = ::new;
        private final HashMap<String, String> MAP2 = ::new;
```


##### Scenario 9

**Before**
```java
        public final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));
```

**After**
```java
        public final ConstClass SELF_PARAM_1 = ::new(true);
        public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));
```
