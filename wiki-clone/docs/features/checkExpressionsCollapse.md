# Check Expressions Collapse (State field: checkExpressionsCollapse)

### Check Expressions Collapse
Folds null checks and Elvis patterns into safe-call expressions.

#### Example: ElvisTestData

examples/data/ElvisTestData.java:
```java
        System.out.println(e != null ? e : "");
        System.out.println(e != null ? e.sayHello() : "");
// ...
        if (e != null) {
                e.get().sayHello();
        }
        if (e.get() != null) {
                e.get().sayHello();
        }
```

folded/ElvisTestData-folded.java:
```java
        System.out.println(e ?: "");
        System.out.println(e?.sayHello() ?: "");
// ...
        e?.get().sayHello();
        e.get()?.sayHello();
```

Highlights ElvisTestData with check expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: IfNullSafeData

examples/data/IfNullSafeData.java:
```java
        var threeChains = data != null
                && data.getData1() != null
// ...
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

folded/IfNullSafeData-folded.java:
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
// ...
                && data?.data1?.active == true;
```

Highlights IfNullSafeData with check expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `checkExpressionsCollapse`
Related features: (none)
