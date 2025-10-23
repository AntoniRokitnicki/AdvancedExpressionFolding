# Override Hide (State field: overrideHide)

### Override Hide
Hides @Override annotations from view.

#### Example: OverrideHideTestData

examples/data/OverrideHideTestData.java:
```java
            @Override
// ...
            @Override
```

folded/OverrideHideTestData-folded.java:
```java
package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
```

Highlights OverrideHideTestData with override hide.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `overrideHide`
Related features: (none)
---

#### Folding catalogue

##### OverrideHideTestData mappings
| Before | After |
| --- | --- |
| `@Override public void run() {` | `public void run() {` |
| `@Override public int compare(Circle c1, Circle c2) {` | `public int compare(Circle c1, Circle c2) {` |
| `@Override public void makeSound() {` | `public void makeSound() {` |
| `@Override public void eat() {` | `public void eat() {` |
| `@Override public double calculateArea() {` | `public double calculateArea() {` |
| `@Override public double calculatePerimeter() {` | `public double calculatePerimeter() {` |
| `@Override public String toString() {` | `public String toString() {` |
| `@Override public boolean equals(Object obj) {` | `public boolean equals(Object obj) {` |
| `@Override public int hashCode() {` | `public int hashCode() {` |
