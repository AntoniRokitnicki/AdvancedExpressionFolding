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
            BigDecimal a = [0:"new BigDecimal("][0:"\"10\""][0:")"];
            BigDecimal b = [1:"new BigDecimal("][1:"\"5\""][1:")"];
            blackhole = a[2:".add("]b[2:")"];
        }

        public void multiplyBigDecimal() {
            BigDecimal a = [6:"new BigDecimal("][6:"\"10\""][6:")"];
            BigDecimal b = [7:"new BigDecimal("][7:"\"5\""][7:")"];
            blackhole = a[8:".multiply("]b[8:")"];
        }

        public void divideBigDecimal() {
            BigDecimal a = [12:"new BigDecimal("][12:"\"10\""][12:")"];
            BigDecimal b = [13:"new BigDecimal("][13:"\"5\""][13:")"];
            blackhole = a[14:".divide("]b[14:")"];
        }

        public void subtractBigDecimal() {
            BigDecimal a = [18:"new BigDecimal("][18:"\"10\""][18:")"];
            BigDecimal b = [19:"new BigDecimal("][19:"\"5\""][19:")"];
            blackhole = a[20:".subtract("]b[20:")"];
        }

        public void remainderBigDecimal() {
            BigDecimal a = [24:"new BigDecimal("][24:"\"10\""][24:")"];
            BigDecimal b = [25:"new BigDecimal("][25:"\"5\""][25:")"];
            blackhole = a[26:".remainder("]b[26:")"];
        }

        public void powBigDecimal() {
            BigDecimal a = [30:"new BigDecimal("][30:"\"10\""][30:")"];
            blackhole = a[31:".pow(2)"];
        }

        public void minBigDecimal() {
            BigDecimal a = [34:"new BigDecimal("][34:"\"10\""][34:")"];
            BigDecimal b = [35:"new BigDecimal("][35:"\"5\""][35:")"];
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            BigDecimal a = [38:"new BigDecimal("][38:"\"10\""][38:")"];
            BigDecimal b = [39:"new BigDecimal("][39:"\"5\""][39:")"];
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            BigDecimal a = [42:"new BigDecimal("][42:"\"10\""][42:")"];
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            BigDecimal a = [44:"new BigDecimal("][44:"\"10\""][44:")"];
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            BigDecimal a = [46:"new BigDecimal("][46:"\"-10\""][46:")"];
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {
            blackhole = [48:"BigDecimal.valueOf("]10[48:")"];
        }

        public void equalsBigDecimal() {
            BigDecimal a = [50:"new BigDecimal("][50:"\"10\""][50:")"];
            BigDecimal b = [51:"new BigDecimal("][51:"\"10\""][51:")"];
            blackhole = a.equals(b);
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            BigInteger a = [54:"new BigInteger("][54:"\"10\""][54:")"];
            BigInteger b = [55:"new BigInteger("][55:"\"5\""][55:")"];
            blackhole = a[56:".add("]b[56:")"];
        }

        public void multiplyBigInteger() {
            BigInteger a = [60:"new BigInteger("][60:"\"10\""][60:")"];
            BigInteger b = [61:"new BigInteger("][61:"\"5\""][61:")"];
            blackhole = a[62:".multiply("]b[62:")"];
        }

        public void divideBigInteger() {
            BigInteger a = [66:"new BigInteger("][66:"\"10\""][66:")"];
            BigInteger b = [67:"new BigInteger("][67:"\"5\""][67:")"];
            blackhole = a[68:".divide("]b[68:")"];
        }

        public void subtractBigInteger() {
            BigInteger a = [72:"new BigInteger("][72:"\"10\""][72:")"];
            BigInteger b = [73:"new BigInteger("][73:"\"5\""][73:")"];
            blackhole = a[74:".subtract("]b[74:")"];
        }

        public void remainderBigInteger() {
            BigInteger a = [78:"new BigInteger("][78:"\"10\""][78:")"];
            BigInteger b = [79:"new BigInteger("][79:"\"5\""][79:")"];
            blackhole = a[80:".remainder("]b[80:")"];
        }

        public void powBigInteger() {
            BigInteger a = [84:"new BigInteger("][84:"\"10\""][84:")"];
            blackhole = a[85:".pow(2)"];
        }

        public void minBigInteger() {
            BigInteger a = [88:"new BigInteger("][88:"\"10\""][88:")"];
            BigInteger b = [89:"new BigInteger("][89:"\"5\""][89:")"];
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            BigInteger a = [92:"new BigInteger("][92:"\"10\""][92:")"];
            BigInteger b = [93:"new BigInteger("][93:"\"5\""][93:")"];
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            BigInteger a = [96:"new BigInteger("][96:"\"10\""][96:")"];
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {
            blackhole = [98:"BigInteger.valueOf("]10[98:")"];
        }

        public void equalsBigInteger() {
            BigInteger a = [100:"new BigInteger("][100:"\"10\""][100:")"];
            BigInteger b = [101:"new BigInteger("][101:"\"10\""][101:")"];
            blackhole = a.equals(b);
        }

        public void andBigInteger() {
            BigInteger a = [104:"new BigInteger("][104:"\"10\""][104:")"];
            BigInteger b = [105:"new BigInteger("][105:"\"5\""][105:")"];
            blackhole = a[106:".and("]b[106:")"];
        }

        public void gcdBigInteger() {
            BigInteger a = [110:"new BigInteger("][110:"\"10\""][110:")"];
            BigInteger b = [111:"new BigInteger("][111:"\"5\""][111:")"];
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            BigInteger a = [114:"new BigInteger("][114:"\"10\""][114:")"];
            blackhole = a.not();
        }

        public void orBigInteger() {
            BigInteger a = [116:"new BigInteger("][116:"\"10\""][116:")"];
            BigInteger b = [117:"new BigInteger("][117:"\"5\""][117:")"];
            blackhole = a[118:".or("]b[118:")"];
        }

        public void shiftLeftBigInteger() {
            BigInteger a = [122:"new BigInteger("][122:"\"10\""][122:")"];
            blackhole = a[123:".shiftLeft("]2[123:")"];
        }

        public void shiftRightBigInteger() {
            BigInteger a = [126:"new BigInteger("][126:"\"10\""][126:")"];
            blackhole = a[127:".shiftRight("]2[127:")"];
        }

        public void signumBigInteger() {
            BigInteger a = [130:"new BigInteger("][130:"\"10\""][130:")"];
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            BigInteger a = [132:"new BigInteger("][132:"\"10\""][132:")"];
            BigInteger b = [133:"new BigInteger("][133:"\"5\""][133:")"];
            blackhole = a[134:".xor("]b[134:")"];
        }

        public void andNotBigInteger() {
            BigInteger a = [138:"new BigInteger("][138:"\"10\""][138:")"];
            BigInteger b = [139:"new BigInteger("][139:"\"5\""][139:")"];
            blackhole = a[140:".andNot("]b[140:")"];
        }

        public void modBigInteger() {
            BigInteger a = [144:"new BigInteger("][144:"\"10\""][144:")"];
            BigInteger b = [145:"new BigInteger("][145:"\"5\""][145:")"];
            blackhole = a[146:".mod("]b[146:")"];
        }
    }

    class MathData {
        public void minMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = [150:"Math.min("]a, b[150:")"];
        }

        public void maxMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = [152:"Math.max("]a, b[152:")"];
        }

        public void powMath() {
            double a = 10.0;
            double b = 2.0;
            blackhole = [154:"Math.pow("]a[154:", b)"];
        }

        public void acosMath() {
            double a = 0.5;
            blackhole = [156:"Math.acos("]a[156:")"];
        }

        public void absMath() {
            double a = 0.5;
            blackhole = [158:"Math.abs("]a[158:")"];
        }

        public void asinMath() {
            double a = 0.5;
            blackhole = [160:"Math.asin("]a[160:")"];
        }

        public void atanMath() {
            double a = 0.5;
            blackhole = [162:"Math.atan("]a[162:")"];
        }

        public void atan2Math() {
            double a = 0.5;
            double b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            double a = 8;
            blackhole = [164:"Math.cbrt("]a[164:")"];
        }

        public void ceilMath() {
            double a = 8.5;
            blackhole = [166:"Math.ceil("]a[166:")"];
        }

        public void cosMath() {
            double a = 0.5;
            blackhole = [168:"Math.cos("]a[168:")"];
        }

        public void coshMath() {
            double a = 0.5;
            blackhole = [170:"Math.cosh("]a[170:")"];
        }

        public void floorMath() {
            double a = 8.5;
            blackhole = [172:"Math.floor("]a[172:")"];
        }

        public void logMath() {
            double a = 8.5;
            blackhole = [174:"Math.log("]a[174:")"];
        }

        public void log10Math() {
            double a = 8.5;
            blackhole = [176:"Math.log10("]a[176:")"];
        }

        public void randomMath() {
            blackhole = Math.random();
        }

        public void rintMath() {
            double a = 8.5;
            blackhole = [178:"Math.rint("]a[178:")"];
        }

        public void roundMath() {
            float a = 8.5f;
            blackhole = [180:"Math.round("]a[180:")"];
        }

        public void sinMath() {
            double a = 0.5;
            blackhole = [182:"Math.sin("]a[182:")"];
        }

        public void sinhMath() {
            double a = 0.5;
            blackhole = [184:"Math.sinh("]a[184:")"];
        }

        public void sqrtMath() {
            double a = 4;
            blackhole = [186:"Math.sqrt("]a[186:")"];
        }

        public void tanMath() {
            double a = 0.5;
            blackhole = [188:"Math.tan("]a[188:")"];
        }

        public void tanhMath() {
            double a = 0.5;
            blackhole = [190:"Math.tanh("]a[190:")"];
        }

        public void toDegreesMath() {
            double a = 0.5;
            blackhole = [192:"Math.toDegrees("]a[192:")"];
        }

        public void toRadiansMath() {
            double a = 45;
            blackhole = [194:"Math.toRadians("]a[194:")"];
        }

        public void ulpMath() {
            double a = 45;
            blackhole = [196:"Math.ulp("]a[196:")"];
        }

        public void expMath() {
            double a = 2;
            blackhole = [198:"Math.exp(a)"];
        }
    }

    class LongData {
        public void valueOfLong() {
            String a = "10";
            blackhole = [200:"Long.valueOf("]a[200:")"];
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            String a = "10";
            blackhole = [202:"Integer.valueOf("]a[202:")"];
        }
    }

    class FloatData {
        public void valueOfFloat() {
            String a = "10.5";
            blackhole = [204:"Float.valueOf("]a[204:")"];
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            String a = "10.5";
            blackhole = [206:"Double.valueOf("]a[206:")"];
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
            a.remove([208:"Integer.valueOf("]3[208:")"]);
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
            a.remove([210:"Integer.valueOf("]3[210:")"]);
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
