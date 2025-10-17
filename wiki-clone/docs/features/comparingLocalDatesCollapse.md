## comparingLocalDatesCollapse

### Comparing Local Dates Collapse
Folds java.time isBefore/isAfter checks into readable date comparisons.

#### Example: LocalDateTestData

examples/data/LocalDateTestData.java:
```java
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);;
        boolean d1SmallerOrEqualD2 = !d1.isAfter(d2);
// ...
        if (date1.isBefore(date2) | date1.isAfter(date2) | !date1.isBefore(date2) | !date1.isAfter(date2)) {
```

folded/LocalDateTestData-folded.java:
```java
        boolean isBefore = d1 < d2;
        boolean isAfter = d1 > d2;
        boolean d2SmallerOrEqualD1 = d1 ≥ d2;;
        boolean d1SmallerOrEqualD2 = d1 ≤ d2;
// ...
        if (date1 < date2 | date1 > date2 | date1 ≥ date2 | date1 ≤ date2) {
```

Highlights LocalDateTestData with comparing local dates collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `comparingLocalDatesCollapse`
Related features: (none)
