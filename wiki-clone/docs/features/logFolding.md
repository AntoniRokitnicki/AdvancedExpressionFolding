![Log statement before folding its bracketed payload](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/948c1f47-9185-4b7c-a8d0-d72f3d064fa5)


![Log bracket content folded into a compact placeholder](https://github.com/user-attachments/assets/b1bc0d45-d06d-4f25-a16c-82b9c9fdc31b)

![Log bracket content expanded after unfolding](https://github.com/user-attachments/assets/5dd3e36f-7c4a-45cf-bcb0-3e838e79e3f7)

# Log Folding (State field: logFolding)

### Log Folding
Folds log statements into compact placeholders with arguments.

#### Example: LogBrackets

examples/data/LogBrackets.java:
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

folded/LogBrackets-folded.java:
```java
        log.debug("Debug message with 1 parameter - Name: $name");
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```

Highlights LogBrackets with log folding.
Removes boilerplate while preserving behavior.

#### Example: LogFoldingTextBlocksTestData

examples/data/LogFoldingTextBlocksTestData.java:
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

folded/LogFoldingTextBlocksTestData-folded.java:
```java
        log.debug("Debug message with 1 parameter - Name: $name");
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```

Highlights LogFoldingTextBlocksTestData with log folding.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `logFolding`
Related features: (none)
