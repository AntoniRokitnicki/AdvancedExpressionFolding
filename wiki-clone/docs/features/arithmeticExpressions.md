# Arithmetic Expressions (State field: arithmeticExpressions)

### Arithmetic Expressions
Folds BigDecimal, BigInteger, and Math operations into infix math.

#### Example: ArithmeticExpressionsTestData

examples/data/ArithmeticExpressionsTestData.java:
```java
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.add(b);
// ...
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.multiply(b);
```

folded/ArithmeticExpressionsTestData-folded.java:
```java
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a + b;
// ...
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a * b;
```

Highlights ArithmeticExpressionsTestData with arithmetic expressions.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `arithmeticExpressions`
Related features: (none)
