---
title: Arithmetic Expressions (State field: arithmeticExpressions)
option: arithmeticExpressions
source: wiki-clone/docs/features/arithmeticExpressions.md
---
# Arithmetic Expressions (State field: arithmeticExpressions)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/methodcall/math/AbstractMathMethodCall.kt:7` – `override fun canExecute() = arithmeticExpressions`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:84` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/KotlinLanguageState.kt:28` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:227` – `registerCheckbox(state::arithmeticExpressions, "BigDecimal, BigInteger and Math") {`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:13` – `::arithmeticExpressionsTestData to 14`
- `test/com/intellij/advancedExpressionFolding/folding/base/full/FullFoldingTest.kt:14` – `.enableAll(state::emojify, state::finalEmoji, state::arithmeticExpressions)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/GlobalSettingsFoldingTest.kt:29` – `fun arithmeticExpressionsTestData() = testCase.runFoldingTest(foldingState()::arithmeticExpressions)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

# Arithmetic Expressions (State field: arithmeticExpressions)

### Arithmetic Expressions
Folds BigDecimal, BigInteger, and Math operations into infix math.

Default: Off
Controlled by: `arithmeticExpressions`
Related features: (none)

---

#### Detailed Folding Reference

##### BigDecimalData

**addBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.add(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a + b;
```
Transforms `BigDecimal.add` into infix addition between the operands.

**multiplyBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.multiply(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a * b;
```
Collapses `BigDecimal.multiply` into infix multiplication.

**divideBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.divide(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a / b;
```
Rewrites `BigDecimal.divide` as infix division.

**subtractBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.subtract(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a - b;
```
Replaces `BigDecimal.subtract` with infix subtraction.

**remainderBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.remainder(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a % b;
```
Substitutes `BigDecimal.remainder` with the modulus operator.

**powBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.pow(2);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a²;
```
Folds `BigDecimal.pow` into a superscript exponent.

**minBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.min(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.min(b);
```
Leaves `BigDecimal.min` as-is while simplifying operand literals.

**maxBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.max(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.max(b);
```
Preserves `BigDecimal.max`, only collapsing constructor literals.

**negateBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.negate();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.negate();
```
Keeps `BigDecimal.negate` untouched aside from literal folding.

**plusBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.plus();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.plus();
```
Maintains `BigDecimal.plus` while collapsing instantiations.

**absBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.abs();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.abs();
```
Retains `BigDecimal.abs` with the literal simplified.

**valueOfBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = BigDecimal.valueOf(10);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = 10;
```
Collapses `BigDecimal.valueOf` into the literal value.

**equalsBigDecimal**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.equals(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.equals(b);
```
Preserves `BigDecimal.equals`, with operands folded to simple literals.

##### BigIntegerData

**addBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.add(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a + b;
```
Transforms `BigInteger.add` into infix addition.

**multiplyBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.multiply(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a * b;
```
Replaces `BigInteger.multiply` with infix multiplication.

**divideBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.divide(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a / b;
```
Rewrites `BigInteger.divide` as infix division.

**subtractBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.subtract(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a - b;
```
Collapses `BigInteger.subtract` to infix subtraction.

**remainderBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.remainder(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a % b;
```
Substitutes `BigInteger.remainder` with the modulus operator.

**powBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.pow(2);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a²;
```
Folds `BigInteger.pow` into an exponent glyph.

**minBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.min(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.min(b);
```
Leaves `BigInteger.min` untouched beyond literal folding.

**maxBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.max(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.max(b);
```
Keeps `BigInteger.max` in method-call form with simplified operands.

**negateBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.negate();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.negate();
```
Retains `BigInteger.negate`, folding only constructor literals.

**valueOfBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = BigInteger.valueOf(10);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = 10;
```
Collapses `BigInteger.valueOf` into the literal value.

**equalsBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.equals(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.equals(b);
```
Preserves `BigInteger.equals` with literal operands.

**andBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.and(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a & b;
```
Rewrites `BigInteger.and` as the infix bitwise AND operator.

**gcdBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.gcd(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.gcd(b);
```
Leaves `BigInteger.gcd` as a method call.

**notBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.not();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.not();
```
Keeps `BigInteger.not` unchanged.

**orBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.or(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a | b;
```
Transforms `BigInteger.or` into the infix bitwise OR operator.

**shiftLeftBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.shiftLeft(2);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a << 2;
```
Collapses `BigInteger.shiftLeft` to the left-shift operator.

**shiftRightBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.shiftRight(2);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a >> 2;
```
Replaces `BigInteger.shiftRight` with the right-shift operator.

**signumBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.signum();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a.signum();
```
Leaves `BigInteger.signum` untouched aside from literal folding.

**xorBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.xor(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a ^ b;
```
Rewrites `BigInteger.xor` as the infix XOR operator.

**andNotBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.andNot(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a & b;
```
Collapses `BigInteger.andNot` into a bitwise AND expression.

**modBigInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = a.mod(b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a % b;
```
Substitutes `BigInteger.mod` with the modulus operator.

##### MathData

**minMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.min(a, b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = min(a, b);
```
Rewrites `Math.min` to the short `min` intrinsic.

**maxMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.max(a, b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = max(a, b);
```
Rewrites `Math.max` to the short `max` intrinsic.

**powMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.pow(a, b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = aᵇ;
```
Collapses `Math.pow` into an exponent glyph.

**acosMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.acos(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = acos(a);
```
Shortens `Math.acos` to `acos`.

**absMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.abs(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = |a|;
```
Converts `Math.abs` into an absolute value bar.

**asinMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.asin(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = asin(a);
```
Shortens `Math.asin` to `asin`.

**atanMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.atan(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = atan(a);
```
Reduces `Math.atan` to `atan`.

**atan2Math**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.atan2(a, b);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = Math.atan2(a, b);
```
Leaves `Math.atan2` unchanged because no shorter form is applied.

**cbrtMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.cbrt(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = ∛a;
```
Turns `Math.cbrt` into a cube-root radical.

**ceilMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.ceil(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = ceil(a);
```
Shrinks `Math.ceil` to `ceil`.

**cosMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.cos(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = cos(a);
```
Shortens `Math.cos` to `cos`.

**coshMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.cosh(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = cosh(a);
```
Reduces `Math.cosh` to `cosh`.

**floorMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.floor(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = floor(a);
```
Shortens `Math.floor` to `floor`.

**logMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.log(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = ln(a);
```
Converts `Math.log` to the natural logarithm symbol.

**log10Math**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.log10(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = log(a);
```
Maps `Math.log10` to the common logarithm notation.

**randomMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.random();
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = Math.random();
```
Leaves `Math.random` unchanged.

**rintMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.rint(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = rint(a);
```
Shortens `Math.rint` to `rint`.

**roundMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.round(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = round(a);
```
Rewrites `Math.round` as `round`.

**sinMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.sin(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = sin(a);
```
Shortens `Math.sin` to `sin`.

**sinhMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.sinh(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = sinh(a);
```
Reduces `Math.sinh` to `sinh`.

**sqrtMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.sqrt(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = sqrt(a);
```
Shortens `Math.sqrt` to `sqrt`.

**tanMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.tan(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = tan(a);
```
Reduces `Math.tan` to `tan`.

**tanhMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.tanh(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = tanh(a);
```
Shortens `Math.tanh` to `tanh`.

**toDegreesMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.toDegrees(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = toDegrees(a);
```
Rewrites `Math.toDegrees` as `toDegrees`.

**toRadiansMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.toRadians(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = toRadians(a);
```
Rewrites `Math.toRadians` as `toRadians`.

**ulpMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.ulp(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = ulp(a);
```
Shortens `Math.ulp` to `ulp`.

**expMath**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Math.exp(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = 𝑒ᵃ;
```
Collapses `Math.exp` into an exponential expression.

##### LongData

**valueOfLong**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Long.valueOf(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a;
```
Replaces `Long.valueOf` with the original string value.

##### IntegerData

**valueOfInteger**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Integer.valueOf(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a;
```
Collapses `Integer.valueOf` to the source string.

##### FloatData

**valueOfFloat**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Float.valueOf(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a;
```
Replaces `Float.valueOf` with the original string literal.

##### DoubleData

**valueOfDouble**
examples/data/ArithmeticExpressionsTestData.java:
```java
            blackhole = Double.valueOf(a);
```
folded/ArithmeticExpressionsTestData-folded.java:
```java
            blackhole = a;
```
Collapses `Double.valueOf` to the source string.


---

#### Processor behaviour overview

The arithmetic method call processors target `BigDecimal` and `BigInteger` receivers via `AbstractArithmeticMethodCall`, which only exposes those class names to the folding pipeline. `Context.getOperands()` then feeds the qualifier and argument expressions into the math expression builders so the resulting infix form preserves operand order.

* `ArithmeticAddMethodCall`, `ArithmeticSubtractMethodCall`, `ArithmeticMultiplyMethodCall`, and `ArithmeticDivideMethodCall` wrap the operands in `Add`, `Subtract`, `Multiply`, and `Divide` respectively, yielding the familiar `+`, `-`, `*`, and `/` forms.
* `ArithmeticRemainderMethodCall` recognises both `remainder` and `mod`, collapsing the call to the `%` operator, while `ArithmeticPowMethodCall` expresses `.pow(...)` as the superscript exponent handled by `Pow`.
* `ArithmeticShiftLeftMethodCall` and `ArithmeticShiftRightMethodCall` emit `<<` and `>>`, and the bitwise processors (`ArithmeticAndMethodCall`, `ArithmeticOrMethodCall`, `ArithmeticXorMethodCall`) route through the corresponding `And`, `Or`, and `Xor` nodes.
* `ArithmeticAndNotMethodCall` builds a composite `qualifier & !argument` by creating a `Not` expression for the argument before passing both operands to `And`, mirroring the Java `andNot` semantics.
* `ArithmeticNotMethodCall`, `ArithmeticNegateMethodCall`, `ArithmeticPlusMethodCall`, `ArithmeticAbsMethodCall`, and `ArithmeticSignumMethodCall` expose unary operations, returning the qualifier unchanged for `.plus()` and wrapping it in `Not`, `Negate`, `Abs`, or `Signum` for the other cases.
* The comparison helpers `ArithmeticMinMethodCall` and `ArithmeticMaxMethodCall` emit `Min` and `Max` nodes except when invoked on a `Stream`, where folding is skipped, while `ArithmeticGcdMethodCall` and `ArithmeticAtan2MethodCall` translate the specialised `gcd` and `atan2` calls into their math-expression counterparts.

This implementation-centric view clarifies how each method name is mapped to an infix operator or symbolic expression without relying on standalone sample sources.

### Processor overview

The `processor/methodcall/arithmetic` package exposes one handler per BigDecimal/BigInteger method and rewrites each call into the math expression used by the folding engine:

- `add`, `subtract`, `multiply`, `divide`, and `remainder` wrap the qualifier/argument pair in `Add`, `Subtract`, `Multiply`, `Divide`, and `Remainder` respectively so they render as infix `+`, `-`, `*`, `/`, and `%` operators.【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticAddMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticSubtractMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticMultiplyMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticDivideMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticRemainderMethodCall.kt†L9-L21】
- `pow` emits a `Pow` expression so exponents appear as superscripts, while `abs`, `negate`, `plus`, and `signum` fold to `Abs`, `Negate`, a bare qualifier, and `Signum` for unary absolute, negative, identity, and sign operations.【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticPowMethodCall.kt†L9-L20】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticAbsMethodCall.kt†L9-L20】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticNegateMethodCall.kt†L9-L20】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticPlusMethodCall.kt†L9-L15】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticSignumMethodCall.kt†L9-L20】
- `min`, `max`, and `gcd` translate into `Min`, `Max`, and `Gcd` expressions, with `min` bailing out when the qualifier is a stream to avoid hiding `Stream.min` collectors.【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticMinMethodCall.kt†L9-L26】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticMaxMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticGcdMethodCall.kt†L9-L21】
- Bitwise helpers (`and`, `or`, `xor`, `not`, `andNot`, `shiftLeft`, `shiftRight`) map straight to their `And`, `Or`, `Xor`, `Not`, composite `And(Not(...))`, `ShiftLeft`, and `ShiftRight` expressions, turning fluent BigInteger calls into symbolic bitwise operators.【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticAndMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticOrMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticXorMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticNotMethodCall.kt†L9-L18】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticAndNotMethodCall.kt†L9-L31】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticShiftLeftMethodCall.kt†L9-L21】【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticShiftRightMethodCall.kt†L9-L21】
- `atan2` stays available via a dedicated `Atan2` expression so two-argument trigonometric calls present as a single folded token.【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/ArithmeticAtan2MethodCall.kt†L9-L21】

Every processor inherits from `AbstractArithmeticMethodCall`, ensuring the matcher only fires for `java.math.BigDecimal` and `java.math.BigInteger` qualifiers so these folds never affect unrelated APIs.【F:src/com/intellij/advancedExpressionFolding/processor/methodcall/arithmetic/AbstractArithmeticMethodCall.kt†L4-L13】
