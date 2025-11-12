---
title: Get Expressions Collapse (State field: getExpressionsCollapse)
option: getExpressionsCollapse
source: wiki-clone/docs/features/getExpressionsCollapse.md
---
# Get Expressions Collapse (State field: getExpressionsCollapse)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/core/ExpressionBuilders.kt:31` – `override fun checkConditions(element: PsiArrayAccessExpression) = getExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/expression/PsiArrayAccessExpressionExt.kt:23` – `if (!isLeftSideAssignment && index != null && getExpressionsCollapse) {`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/ArraysListMethodCall.kt:13` – `override fun canExecute() = getExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionGetMethodCall.kt:17` – `override fun canExecute() = getExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionsUnmodifiableListMethodCall.kt:12` – `override fun canExecute() = getExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/CollectionsUnmodifiableSetMethodCall.kt:12` – `override fun canExecute() = getExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/collection/MapPutMethodCall.kt:15` – `override fun canExecute() = getExpressionsCollapse`
- `src/com/intellij/advancedExpressionFolding/processor/reference/NewExpressionExt.kt:46` – `if (type != null && arrayInitializer != null && getExpressionsCollapse) {`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:52` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/ExpressionCollapseState.kt:21` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:46` – `state::getExpressionsCollapse,`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/folding/base/folding/ExpressionCollapseFoldingTest.kt:20` – `fun getSetPutTestData() = testCase.runFoldingTest(foldingState()::getExpressionsCollapse)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt:37` – `foldingState()::getExpressionsCollapse,`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Get Expressions Collapse (State field: getExpressionsCollapse)

### Get Expressions Collapse
Folds collection access and literal builders into indexed or map-style expressions.

#### Example: GetSetPutTestData

examples/data/GetSetPutTestData.java:
```java
        List<String> list = Arrays.asList("one", "two");
        list.set(1,"three" );
        System.out.println(list.get(list.size() - 1));
        System.out.println(args[args.length - 1]);
// ...
        map.put("one", 1);
        System.out.println(map.get("two"));
        List<String> singleton = java.util.Collections.singletonList("one");
```

folded/GetSetPutTestData-folded.java:
```java
        List<String> list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.getLast());
        System.out.println(args.last());
// ...
        map["one"] = 1;
        System.out.println(map["two"]);
        List<String> singleton = ["one"];
```

Highlights GetSetPutTestData with get expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: ExperimentalTestData

examples/data/ExperimentalTestData.java:
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
// ...
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```

folded/ExperimentalTestData-folded.java:
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].getBytes();
// ...
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```

Highlights ExperimentalTestData with get expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `getExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### GetSetPutTestData

##### Scenario 1

**Before**
```java
        List<String> list = Arrays.asList("one", "two");
        list.set(1,"three" );
        System.out.println(list.get(list.size() - 1));
        System.out.println(args[args.length - 1]);
```

**After**
```java
        List<String> list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.getLast());
        System.out.println(args.last());
```


##### Scenario 2

**Before**
```java
        map.put("one", 1);
        System.out.println(map.get("two"));
        List<String> singleton = java.util.Collections.singletonList("one");
```

**After**
```java
        map["one"] = 1;
        System.out.println(map["two"]);
        List<String> singleton = ["one"];
```


##### Scenario 3

**Before**
```java
        List<String> asList = Arrays.asList("one", "two");
```

**After**
```java
        List<String> asList = ["one", "two"];
```


##### Scenario 4

**Before**
```java
        List<String> copy = new ArrayList<>(Arrays.asList("one", "two"));
```

**After**
```java
        List<String> copy = ["one", "two"];
```


##### Scenario 5

**Before**
```java
        List<String> unmodifiable = Collections.unmodifiableList(Arrays.asList("one", "two"));
```

**After**
```java
        List<String> unmodifiable = ["one", "two"];
```


##### Scenario 6

**Before**
```java
        Set<String> set = new HashSet<String>() {
            {
                add("one");
                add("two");
            }
        };
```

**After**
```java
        Set<String> set = ["one", "two"];
```


##### Scenario 7

**Before**
```java
        Set<String> copyOfSet = Collections.unmodifiableSet(new HashSet<String>() {
            {
                add("one");
                add("two");
            }
        });
```

**After**
```java
        Set<String> copyOfSet = ["one", "two"];
```


##### Scenario 8

**Before**
```java
        String[] strings = new String[] {"one", "two"};
```

**After**
```java
        String[] strings = ["one", "two"];
```


##### Scenario 9

**Before**
```java
        System.out.println(System.getProperty("user.dir"));
```

**After**
```java
        System.out.println(System["user.dir"]);
```


##### Scenario 10

**Before**
```java
        System.out.println(System.getenv().get("user.dir"));
```

**After**
```java
        System.out.println(System.getenv()["user.dir"]);
```


#### ExperimentalTestData

##### Scenario 1

**Before**
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
```

**After**
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
```


##### Scenario 2

**Before**
```java
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```


##### Scenario 3

**Before**
```java
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
```

**After**
```java
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```


##### Scenario 4

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows(IllegalArgumentException) {
```


##### Scenario 5

**Before**
```java
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
```


##### Scenario 6

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows {
```


##### Scenario 7

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
```


##### Scenario 8

**Before**
```java
            try {
                return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
```

**After**
```java
            @SneakyThrows
            return new String(System["sort-desc"].bytes, "UTF-8");
```


##### Scenario 9

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows
```


##### Scenario 10

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
```


##### Scenario 11

**Before**
```java
            example.set("ok");
            String current = example.get();
            System.out.println(example.get().isEmpty());
            example.set(example.get());
            String duplicate = example.get() + example.get();
```

**After**
```java
            example.!! = "ok";
            String current = example.!!;
            System.out.println(example.!!.empty);
            example.!! = example.!!;
            String duplicate = example.!! + example.!!;
```
