## patternMatchingInstanceof

### Pattern Matching for `instanceof` (JEP 394)
Applies pattern matching to `instanceof` checks for more concise and readable code.

#### Example: PatternMatchingInstanceofTestData

examples/data/PatternMatchingInstanceofTestData.java:
```java
        if (o instanceof String) {
            String s = (String) o;
// ...
        if (o instanceof Integer) {
            Integer num = (Integer) o;
```

folded/PatternMatchingInstanceofTestData-folded.java:
```java
        if (o instanceof String s) {
// ...
        if (o instanceof Integer num) {
```

Highlights PatternMatchingInstanceofTestData with pattern matching for `instanceof` (jep 394).
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `patternMatchingInstanceof`
Related features: (none)
