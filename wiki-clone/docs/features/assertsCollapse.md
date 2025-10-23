# Asserts Collapse (State field: assertsCollapse)

### Asserts Collapse
Folds assert statements into terse checks.

#### Example: AssertTestData

examples/data/AssertTestData.java:
```java
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
// ...
        }
        if (args.length == 2)
            throw new IllegalArgumentException("...");
```

folded/AssertTestData-folded.java:
```java
        assert args.length != 0;
        assert args.length != 1 : "...";
        assert args.length != 2 : "...";
```

Highlights AssertTestData with asserts collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `assertsCollapse`
Related features: (none)
---

#### Folding catalogue

##### AssertTestData mappings
| Before | After |
| --- | --- |
| `if (args.length == 0) { throw new IllegalArgumentException(); }` | `assert args.length != 0;` |
| `if (args.length == 1) { throw new IllegalArgumentException("..."); }` | `assert args.length != 1 : "...";` |
| `if (args.length == 2) throw new IllegalArgumentException("...");` | `assert args.length != 2 : "...";` |
