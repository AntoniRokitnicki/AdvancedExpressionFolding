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
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.add(b);
        }

        public void multiplyBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.multiply(b);
        }

        public void divideBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.divide(b);
        }

        public void subtractBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.subtract(b);
        }

        public void remainderBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.remainder(b);
        }

        public void powBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            blackhole = a.pow(2);
        }

        public void minBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("5");
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            BigDecimal a = new BigDecimal("-10");
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {
            blackhole = BigDecimal.valueOf(10);
        }

        public void equalsBigDecimal() {
            BigDecimal a = new BigDecimal("10");
            BigDecimal b = new BigDecimal("10");
            blackhole = a.equals(b);
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.add(b);
        }

        public void multiplyBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.multiply(b);
        }

        public void divideBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.divide(b);
        }

        public void subtractBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.subtract(b);
        }

        public void remainderBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.remainder(b);
        }

        public void powBigInteger() {
            BigInteger a = new BigInteger("10");
            blackhole = a.pow(2);
        }

        public void minBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            BigInteger a = new BigInteger("10");
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {
            blackhole = BigInteger.valueOf(10);
        }

        public void equalsBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("10");
            blackhole = a.equals(b);
        }

        public void andBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.and(b);
        }

        public void gcdBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            BigInteger a = new BigInteger("10");
            blackhole = a.not();
        }

        public void orBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.or(b);
        }

        public void shiftLeftBigInteger() {
            BigInteger a = new BigInteger("10");
            blackhole = a.shiftLeft(2);
        }

        public void shiftRightBigInteger() {
            BigInteger a = new BigInteger("10");
            blackhole = a.shiftRight(2);
        }

        public void signumBigInteger() {
            BigInteger a = new BigInteger("10");
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.xor(b);
        }

        public void andNotBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.andNot(b);
        }

        public void modBigInteger() {
            BigInteger a = new BigInteger("10");
            BigInteger b = new BigInteger("5");
            blackhole = a.mod(b);
        }
    }

    class MathData {
        public void minMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = Math.min(a, b);
        }

        public void maxMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = Math.max(a, b);
        }

        public void powMath() {
            double a = 10.0;
            double b = 2.0;
            blackhole = Math.pow(a, b);
        }

        public void acosMath() {
            double a = 0.5;
            blackhole = Math.acos(a);
        }

        public void absMath() {
            double a = 0.5;
            blackhole = Math.abs(a);
        }

        public void asinMath() {
            double a = 0.5;
            blackhole = Math.asin(a);
        }

        public void atanMath() {
            double a = 0.5;
            blackhole = Math.atan(a);
        }

        public void atan2Math() {
            double a = 0.5;
            double b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            double a = 8;
            blackhole = Math.cbrt(a);
        }

        public void ceilMath() {
            double a = 8.5;
            blackhole = Math.ceil(a);
        }

        public void cosMath() {
            double a = 0.5;
            blackhole = Math.cos(a);
        }

        public void coshMath() {
            double a = 0.5;
            blackhole = Math.cosh(a);
        }

        public void floorMath() {
            double a = 8.5;
            blackhole = Math.floor(a);
        }

        public void logMath() {
            double a = 8.5;
            blackhole = Math.log(a);
        }

        public void log10Math() {
            double a = 8.5;
            blackhole = Math.log10(a);
        }

        public void randomMath() {
            blackhole = Math.random();
        }

        public void rintMath() {
            double a = 8.5;
            blackhole = Math.rint(a);
        }

        public void roundMath() {
            float a = 8.5f;
            blackhole = Math.round(a);
        }

        public void sinMath() {
            double a = 0.5;
            blackhole = Math.sin(a);
        }

        public void sinhMath() {
            double a = 0.5;
            blackhole = Math.sinh(a);
        }

        public void sqrtMath() {
            double a = 4;
            blackhole = Math.sqrt(a);
        }

        public void tanMath() {
            double a = 0.5;
            blackhole = Math.tan(a);
        }

        public void tanhMath() {
            double a = 0.5;
            blackhole = Math.tanh(a);
        }

        public void toDegreesMath() {
            double a = 0.5;
            blackhole = Math.toDegrees(a);
        }

        public void toRadiansMath() {
            double a = 45;
            blackhole = Math.toRadians(a);
        }

        public void ulpMath() {
            double a = 45;
            blackhole = Math.ulp(a);
        }

        public void expMath() {
            double a = 2;
            blackhole = Math.exp(a);
        }
    }

    class LongData {
        public void valueOfLong() {
            String a = "10";
            blackhole = Long.valueOf(a);
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            String a = "10";
            blackhole = Integer.valueOf(a);
        }
    }

    class FloatData {
        public void valueOfFloat() {
            String a = "10.5";
            blackhole = Float.valueOf(a);
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            String a = "10.5";
            blackhole = Double.valueOf(a);
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
            a.remove(Integer.valueOf(3));
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
            a.remove(Integer.valueOf(3));
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
