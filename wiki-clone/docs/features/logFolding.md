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
---

#### Folding catalogue

##### LogBrackets formatting
| Before | After |
| --- | --- |
| `log.debug("Debug message with 1 parameter - Name: %s", name);` | `log.debug("Debug message with 1 parameter - Name: $name");` |
| `log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);` | `log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");` |
| `log.info("Info message with 2 parameters - Name: %s, Age: %d    ", name, age);` | `log.info("Info message with 2 parameters - Name: $name, Age: $age    ");` |
| `log.info("Info message with 2 parameters - Name: %s, Age: %d", name, age);` | `log.info("Info message with 2 parameters - Name: $name, Age: $age");` |
| `log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));` | `log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");` |
| `log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);` | `log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");` |
| `log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);` | `log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");` |
| `log.error("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s", name, age);` | `log.error("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s");` |
| `log.error("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s", name);` | `log.error("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s");` |
| `<pre>log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
        name,
        data.getData().getName(),
        city
);</pre>` | `log.warn("Warning message with 3 parameters and formatting - 1: $name, 2: ${data.data.name}, 3: $city");` |
| `<pre>log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
        data.getData().getName(),
        name,
        data.getData().getName()
);</pre>` | `log.warn("Warning message with 3 parameters and formatting - 1: ${data.data.name}, 2: $name, 3: ${data.data.name}");` |
| `log.error("error1 %s", e, e.getMessage(), e);` | `log.error("error1 $e", e.message, e);` |
| `log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());` | `log.error("error2 ${data.data.name}", data.data.name, data.data.name);` |
| `String formatted = String.format("Hello, %s! Your age is %d", name, age);` | `String formatted = String.format("Hello, $name! Your age is $age");` |
| `log.info("String.format example: {}", formatted);` | `log.info("String.format example: $formatted");` |
| `System.out.printf("User: %s, Age: %d, City: %s%n", name, age, city);` | `System.out.printf("User: $name, Age: $age, City: $city%n");` |
| `System.err.printf("Error scenario: User %s not found in %s and ignore new-line break%n", name, city, "ignored");` | `System.err.printf("Error scenario: User $name not found in $city and ignore new-line break%n", "ignored");` |
| `formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city);` | `formatter.format("User details - Name: $name, Age: $age, City: $city");` |
| `log.info("Formatter example: {}", formatter.toString());` | `log.info("Formatter example: ${formatter.toString()}");` |
| `writer.printf("Log entry: User %s, Age %d, accessed from %s", name, age, city);` | `writer.printf("Log entry: User $name, Age $age, accessed from $city");` |
| `log.error("Failed to write to log file: %s", e.getMessage());` | `log.error("Failed to write to log file: ${e.message}");` |
| `System.out.println("Debug message with 1 parameter - Name: %s".formatted(name));` | `System.out.println("Debug message with 1 parameter - Name: $name".formatted());` |
| `System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));` | `System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());` |
| `System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));` | `System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());` |
| `System.out.println("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s".formatted(name, age, city));` | `System.out.println("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s".formatted());` |
| `System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));` | `System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());` |
| `System.out.println("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s".formatted(name));` | `System.out.println("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s".formatted());` |
| `System.out.println("Additional 1 parameter - Name: %s".formatted(name, data));` | `System.out.println("Additional 1 parameter - Name: $name".formatted( data));` |
| `System.out.println("Additional 2 parameters - Name: %s".formatted(name, data, logPrintfStyle(data)));` | `System.out.println("Additional 2 parameters - Name: $name".formatted( data, logPrintfStyle(data)));` |

##### LogFoldingTextBlocksTestData formatting
| Before | After |
| --- | --- |
| `log.debug("Debug message with 1 parameter - Name: %s", name);` | `log.debug("Debug message with 1 parameter - Name: $name");` |
| `log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);` | `log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");` |
| `System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));` | `System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());` |
| `<pre>log.error("""
                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s
                """, name, age, city);</pre>` | `<pre>log.error("""
                Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s
                """);</pre>` |
| `<pre>log.info("""
                Data summary:
                Root: {}
                Child: {}
                """, data.getName(), data.getData().getName());</pre>` | `<pre>log.info("""
                Data summary:
                Root: ${data.name}
                Child: ${data.data.name}
                """);</pre>` |
| `<pre>log.debug("""
                User summary:
                Name: {}
                Age: {}
                City: {}
                """, name, age, city);</pre>` | `<pre>log.debug("""
                User summary:
                Name: $name
                Age: $age
                City: $city
                """);</pre>` |
| `<pre>log.warn("""
                Nested data snapshot:
                Parent: {}
                Child: {}
                """, data.getName(), data.getData().getName());</pre>` | `<pre>log.warn("""
                Nested data snapshot:
                Parent: ${data.name}
                Child: ${data.data.name}
                """);</pre>` |
| `<pre>log.trace("""
                Formatter contents:
                {}
                """, formatter);</pre>` | `<pre>log.trace("""
                Formatter contents:
                $formatter
                """);</pre>` |
