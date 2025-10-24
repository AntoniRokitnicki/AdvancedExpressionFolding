# Log Folding

## Overview

![Log statement before folding its bracketed payload](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/948c1f47-9185-4b7c-a8d0-d72f3d064fa5)
*Figure 1. Verbose SLF4J call before the plugin rewrites the argument list.*

## Configuration

- **Toggle ID:** `logFolding`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

![Log bracket content folded into a compact placeholder](https://github.com/user-attachments/assets/b1bc0d45-d06d-4f25-a16c-82b9c9fdc31b)
*Figure 2. The same statement with placeholders collapsed to `$name`, `$age`, etc.*

![Log bracket content expanded after unfolding](https://github.com/user-attachments/assets/5dd3e36f-7c4a-45cf-bcb0-3e838e79e3f7)
*Figure 3. Expanded view after pressing `Expand recursively` to reveal original arguments.*


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


---
### Folding catalogue

#### LogBrackets

##### Scenario 1

**Before**
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
```

**After**
```java
        log.debug("Debug message with 1 parameter - Name: $name");
```


##### Scenario 2

**Before**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
        log.info(MY_MARKER, "Marker missing parameters {} {}");
```


##### Scenario 3

**Before**
```java
        log.info("Info message with 2 parameters - Name: %s, Age: %d    ", name, age);
        log.info("Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info("Info message with 2 parameters - Name: $name, Age: $age    ");
        log.info("Info message with 2 parameters - Name: $name, Age: $age");
```


##### Scenario 4

**Before**
```java
        log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));
        log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);
```

**After**
```java
        log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");
        log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");
```


##### Scenario 5

**Before**
```java
        log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);
        log.error("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s", name, age);
        log.error("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s", name);
```

**After**
```java
        log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");
        log.error("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s");
        log.error("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s");
```


##### Scenario 6

**Before**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    name,
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: $name, 2: ${data.data.name}, 3: $city");
```


##### Scenario 7

**Before**
```java
                    data.getData().getName(),

                    city
            );

            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    data.getData().getName(),
                    name,
                    data.getData().getName()
            );
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: ${data.data.name}, 2: $name, 3: ${data.data.name}");
```


##### Scenario 8

**Before**
```java
            log.error("error1 %s", e, e.getMessage(), e);
            log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());
```

**After**
```java
            log.error("error1 $e", e.message, e);
            log.error("error2 ${data.data.name}", data.data.name, data.data.name);
```


##### Scenario 9

**Before**
```java
        String formatted = String.format("Hello, %s! Your age is %d", name, age);
        log.info("String.format example: {}", formatted);
```

**After**
```java
        String formatted = String.format("Hello, $name! Your age is $age");
        log.info("String.format example: $formatted");
```


##### Scenario 10

**Before**
```java
        System.out.printf("User: %s, Age: %d, City: %s%n", name, age, city);
```

**After**
```java
        System.out.printf("User: $name, Age: $age, City: $city%n");
```


##### Scenario 11

**Before**
```java
        System.err.printf("Error scenario: User %s not found in %s and ignore new-line break%n", name, city, "ignored");
```

**After**
```java
        System.err.printf("Error scenario: User $name not found in $city and ignore new-line break%n", "ignored");
```


##### Scenario 12

**Before**
```java
        formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city);
        log.info("Formatter example: {}", formatter.toString());
```

**After**
```java
        formatter.format("User details - Name: $name, Age: $age, City: $city");
        log.info("Formatter example: ${formatter.toString()}");
```


##### Scenario 13

**Before**
```java
            writer.printf("Log entry: User %s, Age %d, accessed from %s", name, age, city);
```

**After**
```java
            writer.printf("Log entry: User $name, Age $age, accessed from $city");
```


##### Scenario 14

**Before**
```java
            log.error("Failed to write to log file: %s", e.getMessage());
```

**After**
```java
            log.error("Failed to write to log file: ${e.message}");
```


##### Scenario 15

**Before**
```java
        System.out.println("Debug message with 1 parameter - Name: %s".formatted(name));
        System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));
        System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));
```

**After**
```java
        System.out.println("Debug message with 1 parameter - Name: $name".formatted());
        System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());
        System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());
```


##### Scenario 16

**Before**
```java
        System.out.println("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s".formatted(name, age, city));
        System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));
        System.out.println("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s".formatted(name));
```

**After**
```java
        System.out.println("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s".formatted());
        System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());
        System.out.println("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s".formatted());
```


##### Scenario 17

**Before**
```java
        System.out.println("Additional 1 parameter - Name: %s".formatted(name, data));
        System.out.println("Additional 2 parameters - Name: %s".formatted(name, data, logPrintfStyle(data)));
```

**After**
```java
        System.out.println("Additional 1 parameter - Name: $name".formatted( data));
        System.out.println("Additional 2 parameters - Name: $name".formatted( data, logPrintfStyle(data)));
```


#### LogFoldingTextBlocksTestData

##### Scenario 1

**Before**
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
```

**After**
```java
        log.debug("Debug message with 1 parameter - Name: $name");
```


##### Scenario 2

**Before**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```


##### Scenario 3

**Before**
```java
        log.info("Info message with 2 parameters - Name: %s, Age: %d    ", name, age);
        log.info("Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info("Info message with 2 parameters - Name: $name, Age: $age    ");
        log.info("Info message with 2 parameters - Name: $name, Age: $age");
```


##### Scenario 4

**Before**
```java
        log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));
        log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);
```

**After**
```java
        log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");
        log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");
```


##### Scenario 5

**Before**
```java
        log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);
        log.error("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s", name, age);
        log.error("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s", name);
```

**After**
```java
        log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");
        log.error("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s");
        log.error("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s");
```


##### Scenario 6

**Before**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    name,
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: $name, 2: ${data.data.name}, 3: $city");
```


##### Scenario 7

**Before**
```java
                    data.getData().getName(),

                    city
            );

            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    data.getData().getName(),
                    name,
                    data.getData().getName()
            );
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: ${data.data.name}, 2: $name, 3: ${data.data.name}");
```


##### Scenario 8

**Before**
```java
            log.error("error1 %s", e, e.getMessage(), e);
            log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());
```

**After**
```java
            log.error("error1 $e", e.message, e);
            log.error("error2 ${data.data.name}", data.data.name, data.data.name);
```


##### Scenario 9

**Before**
```java
        String formatted = String.format("Hello, %s! Your age is %d", name, age);
        log.info("String.format example: {}", formatted);
```

**After**
```java
        String formatted = String.format("Hello, $name! Your age is $age");
        log.info("String.format example: $formatted");
```


##### Scenario 10

**Before**
```java
        System.out.printf("User: %s, Age: %d, City: %s%n", name, age, city);
```

**After**
```java
        System.out.printf("User: $name, Age: $age, City: $city%n");
```


##### Scenario 11

**Before**
```java
        System.err.printf("Error scenario: User %s not found in %s and ignore new-line break%n", name, city, "ignored");
```

**After**
```java
        System.err.printf("Error scenario: User $name not found in $city and ignore new-line break%n", "ignored");
```


##### Scenario 12

**Before**
```java
        formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city);
        log.info("Formatter example: {}", formatter.toString());
```

**After**
```java
        formatter.format("User details - Name: $name, Age: $age, City: $city");
        log.info("Formatter example: ${formatter.toString()}");
```


##### Scenario 13

**Before**
```java
            writer.printf("Log entry: User %s, Age %d, accessed from %s", name, age, city);
```

**After**
```java
            writer.printf("Log entry: User $name, Age $age, accessed from $city");
```


##### Scenario 14

**Before**
```java
            log.error("Failed to write to log file: %s", e.getMessage());
```

**After**
```java
            log.error("Failed to write to log file: ${e.message}");
```


##### Scenario 15

**Before**
```java
        System.out.println("Debug message with 1 parameter - Name: %s".formatted(name));
        System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));
        System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));
```

**After**
```java
        System.out.println("Debug message with 1 parameter - Name: $name".formatted());
        System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());
        System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());
```


##### Scenario 16

**Before**
```java
        System.out.println("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s".formatted(name, age, city));
        System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));
        System.out.println("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s".formatted(name));
```

**After**
```java
        System.out.println("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s".formatted());
        System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());
        System.out.println("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s".formatted());
```


##### Scenario 17

**Before**
```java
        System.out.println("Additional 1 parameter - Name: %s".formatted(name, data));
        System.out.println("Additional 2 parameters - Name: %s".formatted(name, data, logPrintfStyle(data)));
```

**After**
```java
        System.out.println("Additional 1 parameter - Name: $name".formatted( data));
        System.out.println("Additional 2 parameters - Name: $name".formatted( data, logPrintfStyle(data)));
```


##### Scenario 18

**Before**
```java
                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s
                """, name, age, city);
```

**After**
```java
                Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s
                """);
```


##### Scenario 19

**Before**
```java
                Root: {}
                Child: {}
                """, data.getName(), data.getData().getName());
```

**After**
```java
                Root: ${data.name}
                Child: ${data.data.name}
                """);
```


##### Scenario 20

**Before**
```java
                Name: {}
                Age: {}
                City: {}
                """, name, age, city);
```

**After**
```java
                Name: $name
                Age: $age
                City: $city
                """);
```


##### Scenario 21

**Before**
```java
                Parent: {}
                Child: {}
                """, data.getName(), data.getData().getName());
```

**After**
```java
                Parent: ${data.name}
                Child: ${data.data.name}
                """);
```


##### Scenario 22

**Before**
```java
                {}
                """, formatter);
```

**After**
```java
                $formatter
                """);
```
