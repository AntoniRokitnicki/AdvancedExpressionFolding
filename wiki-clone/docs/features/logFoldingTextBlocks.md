# Log Folding Text Blocks (State field: logFoldingTextBlocks)

### Log Folding Text Blocks
Folds log Text Blocks into compact placeholders.

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

Highlights LogFoldingTextBlocksTestData with log folding text blocks.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `logFoldingTextBlocks`
Related features: (none)
---

#### Folding catalogue

##### LogFoldingTextBlocksTestData mappings
| Before | After |
| --- | --- |
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
