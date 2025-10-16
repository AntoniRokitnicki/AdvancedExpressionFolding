---
title: Pseudo-Annotations
slug: /features/pseudo-annotations
sidebar_label: Pseudo-Annotations
description: Generate throwaway entry points with `@Main` and other helper annotations.
---

`@Main` expands into a runnable `public static void main(String[] args)` method so you can try out API ideas without scaffolding boilerplate.

![Quickly generate a main method with @Main](https://github.com/user-attachments/assets/53ad15ef-2c32-4fe4-a857-d36114d020aa)

## `@Main`

### How it works

1. Trigger code completion above any method and accept the `@Main` suggestion.
2. The plugin inserts a `main` method at the top of the class.
3. It synthesises parameters for the constructor or instance receiver when needed.
4. When the target method returns a value, the call is wrapped in `System.out.println` so you see the result immediately.

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

becomes

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

### Parameter defaults

- Numeric primitives → `0`
- `boolean` → `false`
- `char` → `'\0'`
- Floating-point primitives → `0.0` / `0.0f`
- Reference types → `null`
- `java.util.Date` → `new java.util.Date()`
- `java.time.LocalDate` → `LocalDate.now()`
- `java.time.LocalDateTime` → `LocalDateTime.now()`
- `java.time.ZonedDateTime` → `ZonedDateTime.now()`
- Varargs → empty arrays (`new Type[]{}`)

### Smart behaviour

- Generates constructor arguments automatically when invoking instance methods.
- Removes any pre-existing `main` method to avoid duplicates.
- Cleans up the pseudo-annotation after expansion.
- Formats the inserted code so spacing stays consistent with the project style.

## `@Loggable`

`@Loggable` instruments methods with `System.out.println` calls that trace execution.

- Apply it above a method to insert entry and exit statements around the existing body.
- Apply it above a class to update every method and constructor in the file.
- Parameters are included in the entry log so you can see the values passed to the call.
- Exit statements are placed before `return` / `throw` statements to ensure they always run.
- The contributor removes the annotation after expansion and skips methods that are already logged.

```java
public class Test {
    @Loggable
    public String greet(String name) {
        return "Hello " + name;
    }
}
```

becomes

```java
public class Test {
    public String greet(String name) {
        System.out.println("Entering Test.greet" + " with args: " + "name=" + name);
        System.out.println("Exiting Test.greet");
        return "Hello " + name;
    }
}
```

## Example files

- [@Main source sample](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/PseudoAnnotationsMainTestData.java)
- [@Main completion tests](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/test/com/intellij/advancedExpressionFolding/pseudo/MainAnnotationCompletionContributorTest.kt)
- [@Loggable completion tests](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/test/com/intellij/advancedExpressionFolding/pseudo/LoggableAnnotationCompletionContributorTest.kt)
