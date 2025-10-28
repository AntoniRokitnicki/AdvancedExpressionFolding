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
### Folding catalogue

#### PatternMatchingInstanceofTestData

##### Scenario 1

**Before**
```java
        if (o instanceof String) {
            String s = (String) o;
```

**After**
```java
        if (o instanceof String s) {
```


##### Scenario 2

**Before**
```java
        if (o instanceof Integer) {
            Integer num = (Integer) o;
```

**After**
```java
        if (o instanceof Integer num) {
```


##### Scenario 3

**Before**
```java
        if (o instanceof Data) {
            Data d = (Data) o;
```

**After**
```java
        if (o instanceof Data d) {
```


##### Scenario 4

**Before**
```java
        if (o instanceof int[]) {
            int[] arr = (int[]) o;
```

**After**
```java
        if (o instanceof int[] arr) {
```


##### Scenario 5

**Before**
```java
        if (o instanceof DayOfWeek) {
            DayOfWeek day = (DayOfWeek) o;
```

**After**
```java
        if (o instanceof DayOfWeek day) {
```


##### Scenario 6

**Before**
```java
            if (o instanceof String) {
                String s = (String) o;
```

**After**
```java
            if (o instanceof String s) {
```

