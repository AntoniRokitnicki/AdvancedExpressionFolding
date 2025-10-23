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
---

#### Folding catalogue

##### PatternMatchingInstanceofTestData mappings
| Before | After |
| --- | --- |
| `<pre>if (o instanceof String) {
            String s = (String) o;</pre>` | `if (o instanceof String s) {` |
| `<pre>if (o instanceof Integer) {
            Integer num = (Integer) o;</pre>` | `if (o instanceof Integer num) {` |
| `<pre>if (o instanceof Data) {
            Data d = (Data) o;</pre>` | `if (o instanceof Data d) {` |
| `<pre>if (o instanceof int[]) {
            int[] arr = (int[]) o;</pre>` | `if (o instanceof int[] arr) {` |
| `<pre>if (o instanceof DayOfWeek) {
            DayOfWeek day = (DayOfWeek) o;</pre>` | `if (o instanceof DayOfWeek day) {` |
| `<pre>if (o instanceof String) {
                String s = (String) o;</pre>` (TypeMatching positive) | `if (o instanceof String s) {` |
| `<pre>if (o instanceof String) {
                String s = (String) o;</pre>` (VariableUsage positive) | `if (o instanceof String s) {` |
| `<pre>if (o instanceof String) {
                String s = (String) o;</pre>` (SimpleAssignment positive) | `if (o instanceof String s) {` |
| `<pre>if (o instanceof String) {
                String s = (String) o;</pre>` (CastAssignment positive) | `if (o instanceof String s) {` |
