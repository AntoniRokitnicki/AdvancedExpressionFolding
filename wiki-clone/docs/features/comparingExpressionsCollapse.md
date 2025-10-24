# Comparing Expressions Collapse (State field: comparingExpressionsCollapse)

### Comparing Expressions Collapse
Folds equals and compareTo calls into direct comparison expressions.

#### Example: EqualsCompareTestData

examples/data/EqualsCompareTestData.java:
```java
        System.out.println(a.equals(b));
        System.out.println(!a.equals(b));
        System.out.println(a.compareTo(b) == 0);
        System.out.println(a.compareTo(b) != 0);
// ...
        System.out.println(a.compareTo(b) > 0);
        System.out.println(a.compareTo(b) == 1);
        System.out.println(a.compareTo(b) > -1);
        System.out.println(a.compareTo(b) >= 0); // Should be a >= b
```

folded/EqualsCompareTestData-folded.java:
```java
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
// ...
        System.out.println(a > b);
        System.out.println(a > b);
        System.out.println(a ≥ b);
        System.out.println(a ≥ b); // Should be a >= b
```

Highlights EqualsCompareTestData with comparing expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `comparingExpressionsCollapse`
Related features: (none)

---
### Folding catalogue

#### EqualsCompareTestData

##### Scenario 1

**Before**
```java
        System.out.println(a.equals(b));
        System.out.println(!a.equals(b));
        System.out.println(a.compareTo(b) == 0);
        System.out.println(a.compareTo(b) != 0);
```

**After**
```java
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
```


##### Scenario 2

**Before**
```java
        System.out.println(a.compareTo(b) > 0);
        System.out.println(a.compareTo(b) == 1);
        System.out.println(a.compareTo(b) > -1);
        System.out.println(a.compareTo(b) >= 0); // Should be a >= b
```

**After**
```java
        System.out.println(a > b);
        System.out.println(a > b);
        System.out.println(a ≥ b);
        System.out.println(a ≥ b); // Should be a >= b
```


##### Scenario 3

**Before**
```java
        System.out.println(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b) == -1);
        System.out.println(a.compareTo(b) < 1);
        System.out.println(a.compareTo(b) <= 0); // Should be a <= b
```

**After**
```java
        System.out.println(a < b);
        System.out.println(a < b);
        System.out.println(a ≤ b);
        System.out.println(a ≤ b); // Should be a <= b
```

