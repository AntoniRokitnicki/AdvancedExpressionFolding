---
title: pseudoAnnotations
option: pseudoAnnotations
source: wiki-clone/docs/features/pseudoAnnotations.md
---
# pseudoAnnotations

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/pseudo/AbstractLoggingAnnotationCompletionContributor.kt:141` – `if (!pseudoAnnotations) return`
- `src/com/intellij/advancedExpressionFolding/pseudo/AbstractLoggingAnnotationCompletionContributor.kt:157` – `if (!pseudoAnnotations) return`
- `src/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributor.kt:33` – `if (!pseudoAnnotations) return`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:95` with default `true`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/LombokState.kt:16` with default `true`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:279` – `registerCheckbox(state::pseudoAnnotations, "Pseudo-annotations: @Main, @Loggable") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:281` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#pseudoAnnotations")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:287` – `originalPseudoAnnotationsValue = settings.state.pseudoAnnotations`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:288` – `settings.state.pseudoAnnotations = true`
- `test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt:294` – `settings.state.pseudoAnnotations = originalPseudoAnnotationsValue`
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt:373` – `originalPseudoAnnotationsValue = settings.state.pseudoAnnotations`
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt:374` – `settings.state.pseudoAnnotations = true`
- `test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt:380` – `settings.state.pseudoAnnotations = originalPseudoAnnotationsValue`
- `test/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributorTest.kt:30` – `originalPseudoAnnotationsValue = settings.state.pseudoAnnotations`
- `test/com/intellij/advancedExpressionFolding/pseudo/TracingLoggableAnnotationCompletionContributorTest.kt:31` – `settings.state.pseudoAnnotations = true`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

https://github.com/user-attachments/assets/53ad15ef-2c32-4fe4-a857-d36114d020aa

# Pseudo Annotations (State field: pseudoAnnotations)

### Pseudo Annotations
Folds entry points into an @Main pseudo-annotation.

#### Example: PseudoAnnotationsMainTestData

examples/data/PseudoAnnotationsMainTestData.java:
```java
package data;


// ...
    }

}
```

folded/PseudoAnnotationsMainTestData-folded.java:
```java
// No folded sample provided.
```

Highlights PseudoAnnotationsMainTestData with pseudo annotations.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `pseudoAnnotations`
Related features: (none)

#### Generated entry points

`@Main` is applied through code completion and immediately expands into a helper `main` method that invokes the annotated member. The tests covering the feature show the exact transformation:

```java
// Before
public class Test {
    public static void staticMethod(int x) {
    }
}
// After
public class Test {
    public static void main(String[] args) {
        int x = 0;
        staticMethod(x);
    }

    public static void staticMethod(int x) {
    }
}
```

```java
// Before
public class Test {
    public void instanceMethod(String s) {
    }
}
// After
public class Test {
    public static void main(String[] args) {
        String s = "";
        new Test().instanceMethod(s);
    }

    public void instanceMethod(String s) {
    }
}
```

The generated `main` method populates primitive and reference parameters with defaults (`0` for integers, empty strings for `String` parameters) and prints return values when the target method produces a result.【F:test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt†L24-L85】 The pseudo-annotation is removed once the scaffold is inserted, leaving a ready-to-run entry point alongside the original API for quick experimentation.

### Folding catalogue

#### PseudoAnnotationsMainTestData

##### Scenario 1

**Before**
```java
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void greet(String greeting) {
        System.out.println(greeting + " " + name);
    }
}
```

**After**
```java
public class Person {
    public static void main(String[] args) {
        String name = null;

        String greeting = null;
        new Person(name).greet(greeting);
    }

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void greet(String greeting) {
        System.out.println(greeting + " " + name);
    }
}
```

The generated main method initialises constructor parameters and invokes the annotated method, mirroring the behaviour described in the pseudo-annotation docs.【F:docs/docusaurus/features/pseudo-annotations.md†L21-L55】
