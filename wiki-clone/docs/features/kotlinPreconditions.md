# Kotlin Preconditions (State field: kotlinPreconditions)

### Preconditions as Kotlin require, check, requireNotNull and error
Shows argument and state checks the way Kotlin writes them.

- An `if` without `else` whose whole body is `throw new IllegalArgumentException(...)` becomes `require(...)`.
- The same with `IllegalStateException` becomes `check(...)`.
- Any other `throw new IllegalStateException(message)` becomes `error(message)`.
- `Objects.requireNonNull(value[, message])` becomes `requireNotNull(value)[ { message }]`.

The condition is shown in its positive form when that is exact (`!x` → `x`, `==` ↔ `!=`), otherwise as `!(...)`.
The message is kept as written, so it still folds into an interpolated string when that option is on.
Not folded: more statements in the `if`, an `else` branch, other exception types, a cause argument,
and a `Supplier` message for `requireNonNull`.

#### Example: KotlinPreconditionsTestData

examples/data/KotlinPreconditionsTestData.java:
```java
        if (order == null) {
            throw new IllegalArgumentException("order");
        }
        Objects.requireNonNull(customer, "customer");
        if (!connected) {
            throw new IllegalStateException("Not connected");
        }
        throw new IllegalStateException("Unknown state " + state);
```

folded/KotlinPreconditionsTestData-folded.java:
```java
        require(order != null) { "order" }
        requireNotNull(customer) { "customer" };
        check(connected) { "Not connected" }
        error("Unknown state " + state);
```

Takes precedence over Asserts (`assertsCollapse`) for `IllegalArgumentException` and `IllegalStateException`.

Default: Off
Controlled by: `kotlinPreconditions`
Related features: `assertsCollapse`, `concatenationExpressionsCollapse`
