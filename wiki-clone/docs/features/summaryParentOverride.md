# Summary Parent Override (State field: summaryParentOverride)

### Summary Parent Override
Folds overridden methods into parent summary stubs.

The summary after each parent name, e.g. `(1-grandparentMethod)`, and the `// overrides from X` note at the body brace of
each `@Override` method are inlay hints (Settings | Editor | Inlay Hints | Other), so the parent name and the brace keep
their own text and highlighting, other foldings of the method (e.g. `expressionFunc`) stay intact, and `folded/` files no
longer contain them. The examples below show what the editor displays.

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
### Folding catalogue

#### SummaryParentOverrideTestData

##### Scenario 1

**Before**
```java
    class ParentClass extends GrandparentClass {
```

**After**
```java
    class ParentClass extends GrandparentClass(1-grandparentMethod) {
```


##### Scenario 2

**Before**
```java
        public void grandparentMethod() {
```

**After**
```java
        public void grandparentMethod() { // overrides from GrandparentClass
```


##### Scenario 3

**Before**
```java
    public class TestDataClass extends ParentClass implements FirstInterface, SecondInterface, WithoutMethodInterface {
```

**After**
```java
    public class TestDataClass extends ParentClass(1-grandparentMethod) implements FirstInterface(2-interfaceMethodOne, sharedMethod), SecondInterface(1-interfaceMethodTwo), WithoutMethodInterface(nothing overridden) {
```


##### Scenario 4

**Before**
```java
        }
```

**After**
```java
        } // overrides from FirstInterface
```


##### Scenario 5

**Before**
```java
        }
```

**After**
```java
        } // overrides from SecondInterface
```


##### Scenario 6

**Before**
```java
        }
```

**After**
```java
        } // overrides from ParentClass
```


##### Scenario 7

**Before**
```java
    class NameRepository implements Repository<String> {
```

**After**
```java
    class NameRepository implements Repository(1-size)<String> {
```


##### Scenario 8

**Before**
```java
        }
```

**After**
```java
        } // overrides from Repository
```

