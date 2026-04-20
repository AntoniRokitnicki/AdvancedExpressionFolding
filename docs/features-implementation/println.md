---
title: println
option: println
source: wiki-clone/docs/features/println.md
---
# println

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt:57` – `val placeholder = if (println) {`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt:58` – `"println($safeCall);"`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt:60` – `"System.out.println($safeCall);"`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/IfNullSafePrintlnExt.kt:147` – `if (methodCall.methodExpression.referenceName != "println") {`
- `src/com/intellij/advancedExpressionFolding/processor/language/kotlin/PrintlnExt.kt:21` – `if (!println) {`
- `src/com/intellij/advancedExpressionFolding/processor/methodcall/basic/PrintlnMethodCall.kt:12` – `override val methodNames by lazy { listOf("println") }`
- `src/com/intellij/advancedExpressionFolding/processor/util/CodeGenerationUtil.kt:38` – `println(v.elements)`
- `src/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributor.kt:57` – `val exitStatementText = "System.out.println($exitExpression);"`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:74` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:22` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:153` – `registerCheckbox(state::println, "Simplify System.out.println to println") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:155` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#println")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:32` – `System.out.println(value + name);`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:39` – `System.out.println("Entering Test.doWork" + " with args: " + "value=" + value + ", " + "name=" + name);`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:40` – `System.out.println(value + name);`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:41` – `System.out.println("Exiting Test.doWork");`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:64` – `System.out.println("Entering Test.compute" + " with args: " + "flag=" + flag);`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:66` – `System.out.println("Exiting Test.compute");`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:69` – `System.out.println("Exiting Test.compute");`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:83` – `System.out.println("work");`

## Example Inputs

- `examples/data/InterpolatedStringTestData.java:5` – `System.out.println("Hello, " + args[0]);`
- `examples/data/InterpolatedStringTestData.java:6` – `System.out.println("Hello, " + args[0] + "!");`
- `examples/data/InterpolatedStringTestData.java:7` – `System.out.println(args[0] + ", hello!");`
- `examples/data/InterpolatedStringTestData.java:8` – `System.out.println(args[0] + ", " + args[0]);`
- `examples/data/InterpolatedStringTestData.java:10` – `System.out.println("Hello, " + name);`
- `examples/data/InterpolatedStringTestData.java:11` – `System.out.println("Hello, " + name + "!");`
- `folded/EmojifyTestData-folded.java:543` – `System.out.println(Singleton.🧍.isOk());`
- `folded/EmojifyTestData-folded.java:544` – `System.out.println(Singleton.🧍.main(Singleton.🧍.main(Singleton.getInstance())));`
- `folded/EmojifyTestData-folded.java:547` – `System.out.println(Singleton.🧍.OTHER_NAME.isOk());`
- `folded/EmojifyTestData-folded.java:548` – `System.out.println(Singleton.🧍.OTHER_NAME.main(Singleton.🧍.OTHER_NAME.main(Singleton.getInstance())));`
- `folded/LogBrackets-folded.java:75` – `System.out.println("Debug message with 1 parameter - Name: $name".formatted());`
- `folded/LogBrackets-folded.java:76` – `System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());`

## Legacy Reference

Content imported from the historical wiki entry for completeness.

![System.out.println call folded to println](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/75a5224f-7b52-4b71-9774-2814e8a867ba)

# Println (State field: println)

### Println
Folds System.out.println calls into println.

#### Example: PrintlnTestData

examples/data/PrintlnTestData.java:
```java
        System.out.println("Hello");
        System.out.println
// ...
        System.
                out.println("Spacing");
        System.out.
// ...
        System.out.println("Passed as parameter: " +
this.getClass());
        System.out.println("""
```

folded/PrintlnTestData-folded.java:
```java
        println("Hello");
        println
// ...
        println("Spacing");
        println(3.14);
        println(string);
// ...
        println("Passed as parameter: " + string);
        println("Passed as parameter: " + this.getClass());
        println("""
```

Highlights PrintlnTestData with println.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `println`
Related features: (none)

---
### Folding catalogue

#### PrintlnTestData

##### Scenario 1

**Before**
```java
        System.out.println("Hello");
        System.out.println
```

**After**
```java
        println("Hello");
        println
```


##### Scenario 2

**Before**
```java
        System.
                out.println("Spacing");
        System.out.
                println(3.14);
        System.out.println(string);
        System.out.println(true);
        System.out.println('A');
        System.out.println(CONST_VALUE);
        System.out.println("Divided: " + "" +
                "into" +
                " multiple" + " " + "strings");
        System.out.println("Passed as parameter: " + string);
        System.out.println("Passed as parameter: " +
this.getClass());
        System.out.println("""
```

**After**
```java
        println("Spacing");
        println(3.14);
        println(string);
        println(true);
        println('A');
        println(CONST_VALUE);
        println("Divided: " + "" + "into" + " multiple" + " " + "strings");
        println("Passed as parameter: " + string);
        println("Passed as parameter: " + this.getClass());
        println("""
```
