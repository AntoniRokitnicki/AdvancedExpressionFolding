# Local Date Literal Collapse (State field: localDateLiteralCollapse)

### Local Date Literal Collapse
Folds LocalDate.of(...) literals into inline YYYY-MM-DD forms.

#### Example: LocalDateLiteralTestData

examples/data/LocalDateLiteralTestData.java:
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

folded/LocalDateLiteralTestData-folded.java:
```java
        LocalDate d1 = 2018-01-10;
        LocalDate d4 = 2018-01-10;
        LocalDate d2 = 2018-12-10;
        LocalDate d3 = 2018-04-04;
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013-01-10);
```

Highlights LocalDateLiteralTestData with local date literal collapse.
Removes boilerplate while preserving behavior.

#### Example: LocalDateLiteralPostfixTestData

examples/data/LocalDateLiteralPostfixTestData.java:
```java
        LocalDate d1 = LocalDate.of(2018, 01, 10);
        LocalDate d4 = LocalDate.of(2018, 01, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        LocalDate d3 = LocalDate.of(2018,  4 ,  4   );
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));
```

folded/LocalDateLiteralPostfixTestData-folded.java:
```java
        LocalDate d1 = 2018Y-01M-10D;
        LocalDate d4 = 2018Y-01M-10D;
        LocalDate d2 = 2018Y-12M-10D;
        LocalDate d3 = 2018Y-04M-04D;
// ...
        boolean d1SmallerOrEqualD2 = !d1.isAfter(2013Y-01M-10D);
```

Highlights LocalDateLiteralPostfixTestData with local date literal collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `localDateLiteralCollapse`
Related features: (none)
---

#### Folding catalogue

##### LocalDateLiteralTestData mappings
| Before | After |
| --- | --- |
| `LocalDate d1 = LocalDate.of(2018, 01, 10);` | `LocalDate d1 = 2018-01-10;` |
| `LocalDate d4 = LocalDate.of(2018, 01, 10);` | `LocalDate d4 = 2018-01-10;` |
| `LocalDate d2 = LocalDate.of(2018, 12, 10);` | `LocalDate d2 = 2018-12-10;` |
| `LocalDate d3 = LocalDate.of(2018,  4 ,  4   );` | `LocalDate d3 = 2018-04-04;` |
| `boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));` | `boolean d1SmallerOrEqualD2 = !d1.isAfter(2013-01-10);` |

##### LocalDateLiteralPostfixTestData mappings
| Before | After |
| --- | --- |
| `LocalDate d1 = LocalDate.of(2018, 01, 10);` | `LocalDate d1 = 2018Y-01M-10D;` |
| `LocalDate d4 = LocalDate.of(2018, 01, 10);` | `LocalDate d4 = 2018Y-01M-10D;` |
| `LocalDate d2 = LocalDate.of(2018, 12, 10);` | `LocalDate d2 = 2018Y-12M-10D;` |
| `LocalDate d3 = LocalDate.of(2018,  4 ,  4   );` | `LocalDate d3 = 2018Y-04M-04D;` |
| `boolean d1SmallerOrEqualD2 = !d1.isAfter(LocalDate.of(2013, 1, 10));` | `boolean d1SmallerOrEqualD2 = !d1.isAfter(2013Y-01M-10D);` |
