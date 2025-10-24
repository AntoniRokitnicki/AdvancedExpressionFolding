# Summary Parent Override (State field: summaryParentOverride)

### Summary Parent Override
Folds overridden methods into parent summary stubs.

#### Example: SummaryParentOverrideTestData

examples/data/SummaryParentOverrideTestData.java:
```java
    class ParentClass extends GrandparentClass {
// ...
        public void grandparentMethod() {
```

folded/SummaryParentOverrideTestData-folded.java:
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
// ...
        public void grandparentMethod() { // overrides from GrandparentClass
```

Highlights SummaryParentOverrideTestData with summary parent override.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `summaryParentOverride`
Related features: (none)
---

#### Folding catalogue

##### SummaryParentOverrideTestData mappings
| Before | After |
| --- | --- |
| `class ParentClass extends GrandparentClass {` | `class ParentClass extends GrandparentClass(1-grandparentMethod) {` |
| `public void grandparentMethod() {` | `public void grandparentMethod() { // overrides from GrandparentClass` |
| `public class TestDataClass extends ParentClass implements FirstInterface, SecondInterface, WithoutMethodInterface {` | `public class TestDataClass extends ParentClass(1-grandparentMethod) implements FirstInterface(2-interfaceMethodOne, sharedMethod), SecondInterface(1-interfaceMethodTwo), WithoutMethodInterface(nothing overridden) {` |
| `public void interfaceMethodOne() {` | `public void interfaceMethodOne() { // overrides from FirstInterface` |
| `public void interfaceMethodTwo() {` | `public void interfaceMethodTwo() { // overrides from SecondInterface` |
| `public void sharedMethod() {` | `public void sharedMethod() { // overrides from FirstInterface` |
| `public void grandparentMethod() {` (inside `TestDataClass`) | `public void grandparentMethod() { // overrides from ParentClass` |
