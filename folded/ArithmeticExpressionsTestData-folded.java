package data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("ALL")
public class ArithmeticExpressionsTestData {

    private Object blackhole;

    class BigDecimalData {

        public void addBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a + b;
        }

        public void multiplyBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a * b;
        }

        public void divideBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a / b;
        }

        public void subtractBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a - b;
        }

        public void remainderBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a % b;
        }

        public void powBigDecimal() {
            BigDecimal a = 10;
            blackhole = a²;
        }

        public void minBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 5;
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            BigDecimal a = 10;
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            BigDecimal a = 10;
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            BigDecimal a = -10;
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {
            blackhole = 10;
        }

        public void equalsBigDecimal() {
            BigDecimal a = 10;
            BigDecimal b = 10;
            blackhole = a.equals(b);
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a + b;
        }

        public void multiplyBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a * b;
        }

        public void divideBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a / b;
        }

        public void subtractBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a - b;
        }

        public void remainderBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a % b;
        }

        public void powBigInteger() {
            BigInteger a = 10;
            blackhole = a²;
        }

        public void minBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            BigInteger a = 10;
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {
            blackhole = 10;
        }

        public void equalsBigInteger() {
            BigInteger a = 10;
            BigInteger b = 10;
            blackhole = a.equals(b);
        }

        public void andBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a & b;
        }

        public void gcdBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            BigInteger a = 10;
            blackhole = a.not();
        }

        public void orBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a | b;
        }

        public void shiftLeftBigInteger() {
            BigInteger a = 10;
            blackhole = a << 2;
        }

        public void shiftRightBigInteger() {
            BigInteger a = 10;
            blackhole = a >> 2;
        }

        public void signumBigInteger() {
            BigInteger a = 10;
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a ^ b;
        }

        public void andNotBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a & b;
        }

        public void modBigInteger() {
            BigInteger a = 10;
            BigInteger b = 5;
            blackhole = a % b;
        }
    }

    class MathData {
        public void minMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = min(a, b);
        }

        public void maxMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = max(a, b);
        }

        public void powMath() {
            double a = 10.0;
            double b = 2.0;
            blackhole = aᵇ;
        }

        public void acosMath() {
            double a = 0.5;
            blackhole = acos(a);
        }

        public void absMath() {
            double a = 0.5;
            blackhole = |a|;
        }

        public void asinMath() {
            double a = 0.5;
            blackhole = asin(a);
        }

        public void atanMath() {
            double a = 0.5;
            blackhole = atan(a);
        }

        public void atan2Math() {
            double a = 0.5;
            double b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            double a = 8;
            blackhole = ∛a;
        }

        public void ceilMath() {
            double a = 8.5;
            blackhole = ceil(a);
        }

        public void cosMath() {
            double a = 0.5;
            blackhole = cos(a);
        }

        public void coshMath() {
            double a = 0.5;
            blackhole = cosh(a);
        }

        public void floorMath() {
            double a = 8.5;
            blackhole = floor(a);
        }

        public void logMath() {
            double a = 8.5;
            blackhole = ln(a);
        }

        public void log10Math() {
            double a = 8.5;
            blackhole = log(a);
        }

        public void randomMath() {
            blackhole = Math.random();
        }

        public void rintMath() {
            double a = 8.5;
            blackhole = rint(a);
        }

        public void roundMath() {
            float a = 8.5f;
            blackhole = round(a);
        }

        public void sinMath() {
            double a = 0.5;
            blackhole = sin(a);
        }

        public void sinhMath() {
            double a = 0.5;
            blackhole = sinh(a);
        }

        public void sqrtMath() {
            double a = 4;
            blackhole = sqrt(a);
        }

        public void tanMath() {
            double a = 0.5;
            blackhole = tan(a);
        }

        public void tanhMath() {
            double a = 0.5;
            blackhole = tanh(a);
        }

        public void toDegreesMath() {
            double a = 0.5;
            blackhole = toDegrees(a);
        }

        public void toRadiansMath() {
            double a = 45;
            blackhole = toRadians(a);
        }

        public void ulpMath() {
            double a = 45;
            blackhole = ulp(a);
        }

        public void expMath() {
            double a = 2;
            blackhole = 𝑒ᵃ;
        }
    }

    class LongData {
        public void valueOfLong() {
            String a = "10";
            blackhole = a;
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            String a = "10";
            blackhole = a;
        }
    }

    class FloatData {
        public void valueOfFloat() {
            String a = "10.5";
            blackhole = a;
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            String a = "10.5";
            blackhole = a;
        }
    }


    class CollectionData {
        public void addAllAssign() {
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
            Collection<Integer> b = new ArrayList<>(Arrays.asList(4, 5, 6));
            a.addAll(b);
            blackhole = a;
        }

        public void remove() {
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            a.remove(3);
            blackhole = a;
        }

        public void removeAllAssign() {
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            Collection<Integer> b = new ArrayList<>(Arrays.asList(3, 4));
            a.removeAll(b);
            blackhole = a;
        }

        public void removeAssign() {
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            a.remove(3);
            blackhole = a;
        }
    }

    class ArithmeticData {

        public void addAssign() {
            int a = 10;
            int b = 5;
            a += b;
            blackhole = a;
        }

        public void andAssign() {
            int a = 10;
            int b = 5;
            a &= b;
            blackhole = a;
        }

        public void divideAssign() {
            int a = 10;
            int b = 5;
            a /= b;
            blackhole = a;
        }

        public void multiplyAssign() {
            int a = 10;
            int b = 5;
            a *= b;
            blackhole = a;
        }

        public void orAssign() {
            int a = 10;
            int b = 5;
            a |= b;
            blackhole = a;
        }

        public void shiftLeftAssign() {
            int a = 10;
            int b = 2;
            a <<= b;
            blackhole = a;
        }

        public void shiftRightAssign() {
            int a = 10;
            int b = 2;
            a >>= b;
            blackhole = a;
        }

        public void subtractAssign() {
            int a = 10;
            int b = 5;
            a -= b;
            blackhole = a;
        }

        public void xorAssign() {
            int a = 10;
            int b = 5;
            a ^= b;
            blackhole = a;
        }


    }
}
