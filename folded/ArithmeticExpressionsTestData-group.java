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
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".add("]{[0:".add("]}b[0:")"]{[0:")"]};
        }

        public void multiplyBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".multiply("]{[0:".multiply("]}b[0:")"]{[0:")"]};
        }

        public void divideBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".divide("]{[0:".divide("]}b[0:")"]{[0:")"]};
        }

        public void subtractBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".subtract("]{[0:".subtract("]}b[0:")"]{[0:")"]};
        }

        public void remainderBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".remainder("]{[0:".remainder("]}b[0:")"]{[0:")"]};
        }

        public void powBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a[0:".pow(2)"]{[0:".pow(2)"]};
        }

        public void minBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""-10""]{[0:""-10""]}[0:")"]{[0:")"]};
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {
            blackhole = [0:"BigDecimal.valueOf("]{[0:"BigDecimal.valueOf("]}10[0:")"]{[0:")"]};
        }

        public void equalsBigDecimal() {
            BigDecimal a = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigDecimal b = [0:"new BigDecimal("]{[0:"new BigDecimal("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.equals(b);
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".add("]{[0:".add("]}b[0:")"]{[0:")"]};
        }

        public void multiplyBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".multiply("]{[0:".multiply("]}b[0:")"]{[0:")"]};
        }

        public void divideBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".divide("]{[0:".divide("]}b[0:")"]{[0:")"]};
        }

        public void subtractBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".subtract("]{[0:".subtract("]}b[0:")"]{[0:")"]};
        }

        public void remainderBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".remainder("]{[0:".remainder("]}b[0:")"]{[0:")"]};
        }

        public void powBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a[0:".pow(2)"]{[0:".pow(2)"]};
        }

        public void minBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {
            blackhole = [0:"BigInteger.valueOf("]{[0:"BigInteger.valueOf("]}10[0:")"]{[0:")"]};
        }

        public void equalsBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.equals(b);
        }

        public void andBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".and("]{[0:".and("]}b[0:")"]{[0:")"]};
        }

        public void gcdBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.not();
        }

        public void orBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".or("]{[0:".or("]}b[0:")"]{[0:")"]};
        }

        public void shiftLeftBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a[0:".shiftLeft("]{[0:".shiftLeft("]}2[0:")"]{[0:")"]};
        }

        public void shiftRightBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a[0:".shiftRight("]{[0:".shiftRight("]}2[0:")"]{[0:")"]};
        }

        public void signumBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".xor("]{[0:".xor("]}b[0:")"]{[0:")"]};
        }

        public void andNotBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".andNot("]{[0:".andNot("]}b[0:")"]{[0:")"]};
        }

        public void modBigInteger() {
            BigInteger a = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""10""]{[0:""10""]}[0:")"]{[0:")"]};
            BigInteger b = [0:"new BigInteger("]{[0:"new BigInteger("]}[0:""5""]{[0:""5""]}[0:")"]{[0:")"]};
            blackhole = a[0:".mod("]{[0:".mod("]}b[0:")"]{[0:")"]};
        }
    }

    class MathData {
        public void minMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = [0:"Math.min("]{[0:"Math.min("]}a, b[0:")"]{[0:")"]};
        }

        public void maxMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = [0:"Math.max("]{[0:"Math.max("]}a, b[0:")"]{[0:")"]};
        }

        public void powMath() {
            double a = 10.0;
            double b = 2.0;
            blackhole = [0:"Math.pow("]{[0:"Math.pow("]}a[0:", b)"]{[0:", b)"]};
        }

        public void acosMath() {
            double a = 0.5;
            blackhole = [0:"Math.acos("]{[0:"Math.acos("]}a[0:")"]{[0:")"]};
        }

        public void absMath() {
            double a = 0.5;
            blackhole = [0:"Math.abs("]{[0:"Math.abs("]}a[0:")"]{[0:")"]};
        }

        public void asinMath() {
            double a = 0.5;
            blackhole = [0:"Math.asin("]{[0:"Math.asin("]}a[0:")"]{[0:")"]};
        }

        public void atanMath() {
            double a = 0.5;
            blackhole = [0:"Math.atan("]{[0:"Math.atan("]}a[0:")"]{[0:")"]};
        }

        public void atan2Math() {
            double a = 0.5;
            double b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            double a = 8;
            blackhole = [0:"Math.cbrt("]{[0:"Math.cbrt("]}a[0:")"]{[0:")"]};
        }

        public void ceilMath() {
            double a = 8.5;
            blackhole = [0:"Math.ceil("]{[0:"Math.ceil("]}a[0:")"]{[0:")"]};
        }

        public void cosMath() {
            double a = 0.5;
            blackhole = [0:"Math.cos("]{[0:"Math.cos("]}a[0:")"]{[0:")"]};
        }

        public void coshMath() {
            double a = 0.5;
            blackhole = [0:"Math.cosh("]{[0:"Math.cosh("]}a[0:")"]{[0:")"]};
        }

        public void floorMath() {
            double a = 8.5;
            blackhole = [0:"Math.floor("]{[0:"Math.floor("]}a[0:")"]{[0:")"]};
        }

        public void logMath() {
            double a = 8.5;
            blackhole = [0:"Math.log("]{[0:"Math.log("]}a[0:")"]{[0:")"]};
        }

        public void log10Math() {
            double a = 8.5;
            blackhole = [0:"Math.log10("]{[0:"Math.log10("]}a[0:")"]{[0:")"]};
        }

        public void randomMath() {
            blackhole = Math.random();
        }

        public void rintMath() {
            double a = 8.5;
            blackhole = [0:"Math.rint("]{[0:"Math.rint("]}a[0:")"]{[0:")"]};
        }

        public void roundMath() {
            float a = 8.5f;
            blackhole = [0:"Math.round("]{[0:"Math.round("]}a[0:")"]{[0:")"]};
        }

        public void sinMath() {
            double a = 0.5;
            blackhole = [0:"Math.sin("]{[0:"Math.sin("]}a[0:")"]{[0:")"]};
        }

        public void sinhMath() {
            double a = 0.5;
            blackhole = [0:"Math.sinh("]{[0:"Math.sinh("]}a[0:")"]{[0:")"]};
        }

        public void sqrtMath() {
            double a = 4;
            blackhole = [0:"Math.sqrt("]{[0:"Math.sqrt("]}a[0:")"]{[0:")"]};
        }

        public void tanMath() {
            double a = 0.5;
            blackhole = [0:"Math.tan("]{[0:"Math.tan("]}a[0:")"]{[0:")"]};
        }

        public void tanhMath() {
            double a = 0.5;
            blackhole = [0:"Math.tanh("]{[0:"Math.tanh("]}a[0:")"]{[0:")"]};
        }

        public void toDegreesMath() {
            double a = 0.5;
            blackhole = [0:"Math.toDegrees("]{[0:"Math.toDegrees("]}a[0:")"]{[0:")"]};
        }

        public void toRadiansMath() {
            double a = 45;
            blackhole = [0:"Math.toRadians("]{[0:"Math.toRadians("]}a[0:")"]{[0:")"]};
        }

        public void ulpMath() {
            double a = 45;
            blackhole = [0:"Math.ulp("]{[0:"Math.ulp("]}a[0:")"]{[0:")"]};
        }

        public void expMath() {
            double a = 2;
            blackhole = [0:"Math.exp(a)"]{[0:"Math.exp(a)"]};
        }
    }

    class LongData {
        public void valueOfLong() {
            String a = "10";
            blackhole = [0:"Long.valueOf("]{[0:"Long.valueOf("]}a[0:")"]{[0:")"]};
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            String a = "10";
            blackhole = [0:"Integer.valueOf("]{[0:"Integer.valueOf("]}a[0:")"]{[0:")"]};
        }
    }

    class FloatData {
        public void valueOfFloat() {
            String a = "10.5";
            blackhole = [0:"Float.valueOf("]{[0:"Float.valueOf("]}a[0:")"]{[0:")"]};
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            String a = "10.5";
            blackhole = [0:"Double.valueOf("]{[0:"Double.valueOf("]}a[0:")"]{[0:")"]};
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
            a.remove([0:"Integer.valueOf("]{[0:"Integer.valueOf("]}3[0:")"]{[0:")"]});
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
            a.remove([0:"Integer.valueOf("]{[0:"Integer.valueOf("]}3[0:")"]{[0:")"]});
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
