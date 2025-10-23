# Range Expressions Collapse (State field: rangeExpressionsCollapse)

### Range Expressions Collapse
Folds indexed loops into Kotlin-style range expressions.

#### Example: ForRangeTestData

examples/data/ForRangeTestData.java:
```java
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
// ...
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
```

folded/ForRangeTestData-folded.java:
```java
                for ((int i, String arg) : args) {
// ...
                for (String arg : args) {
```

Highlights ForRangeTestData with range expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `rangeExpressionsCollapse`
Related features: (none)
---

#### Folding catalogue

##### ForRangeTestData mappings
| Before | After |
| --- | --- |
| `for (int i = 0; i < args.length; i++) { String arg = args[i];` | `for ((int i, String arg) : args) {` |
| `for (int i = 0; i < args.length; i++) { String arg = args[i];` | `for (String arg : args) {` |
| `for (int i = 0; i < args.length; i++) { System.out.println(i);` | `for (int i : [0, args.length)) {` |
| `for (int i = 0; i <= args.length - 1; i++) {` | `for (int i : [0, args.length - 1]) {` |
| `for (int i = 0; i < list.size(); i++) { String a = list.get(i);` | `for (String a : list) {` |
| `for (int i = 0; i < list.size(); i++) { String a = list.get(i); System.out.println(i);` | `for ((int i, String a) : list) {` |
| `if (args.length > 0 && args.length < 2) {` | `if (args.length in (0, 2)) {` |
