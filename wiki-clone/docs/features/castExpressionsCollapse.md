# Cast Expressions Collapse (State field: castExpressionsCollapse)

### Cast Expressions Collapse
Folds explicit type cast calls into concise Kotlin-style expressions.

#### Example: TypeCastTestData

examples/data/TypeCastTestData.java:
```java
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
```

folded/TypeCastTestData-folded.java:
```java
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
```

Highlights TypeCastTestData with cast expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `castExpressionsCollapse`
Related features: (none)
