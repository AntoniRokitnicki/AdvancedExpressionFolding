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
            [0:"BigDecimal"] a = [1:"new BigDecimal("][1:"\"10\""][1:")"];
            [2:"BigDecimal"] b = [3:"new BigDecimal("][3:"\"5\""][3:")"];
            blackhole = a[4:".add("]b[4:")"];
        }

        public void multiplyBigDecimal() {
            [10:"BigDecimal"] a = [11:"new BigDecimal("][11:"\"10\""][11:")"];
            [12:"BigDecimal"] b = [13:"new BigDecimal("][13:"\"5\""][13:")"];
            blackhole = a[14:".multiply("]b[14:")"];
        }

        public void divideBigDecimal() {
            [20:"BigDecimal"] a = [21:"new BigDecimal("][21:"\"10\""][21:")"];
            [22:"BigDecimal"] b = [23:"new BigDecimal("][23:"\"5\""][23:")"];
            blackhole = a[24:".divide("]b[24:")"];
        }

        public void subtractBigDecimal() {
            [30:"BigDecimal"] a = [31:"new BigDecimal("][31:"\"10\""][31:")"];
            [32:"BigDecimal"] b = [33:"new BigDecimal("][33:"\"5\""][33:")"];
            blackhole = a[34:".subtract("]b[34:")"];
        }

        public void remainderBigDecimal() {
            [40:"BigDecimal"] a = [41:"new BigDecimal("][41:"\"10\""][41:")"];
            [42:"BigDecimal"] b = [43:"new BigDecimal("][43:"\"5\""][43:")"];
            blackhole = a[44:".remainder("]b[44:")"];
        }

        public void powBigDecimal() {
            [50:"BigDecimal"] a = [51:"new BigDecimal("][51:"\"10\""][51:")"];
            blackhole = a[52:".pow(2)"];
        }

        public void minBigDecimal() {
            [56:"BigDecimal"] a = [57:"new BigDecimal("][57:"\"10\""][57:")"];
            [58:"BigDecimal"] b = [59:"new BigDecimal("][59:"\"5\""][59:")"];
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            [64:"BigDecimal"] a = [65:"new BigDecimal("][65:"\"10\""][65:")"];
            [66:"BigDecimal"] b = [67:"new BigDecimal("][67:"\"5\""][67:")"];
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            [72:"BigDecimal"] a = [73:"new BigDecimal("][73:"\"10\""][73:")"];
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            [76:"BigDecimal"] a = [77:"new BigDecimal("][77:"\"10\""][77:")"];
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            [80:"BigDecimal"] a = [81:"new BigDecimal("][81:"\"-10\""][81:")"];
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {[84:"\n            "]blackhole = [85:"BigDecimal.valueOf("]10[85:")"][84:";"][84:"\n        "]}

        public void equalsBigDecimal() {
            [89:"BigDecimal"] a = [90:"new BigDecimal("][90:"\"10\""][90:")"];
            [91:"BigDecimal"] b = [92:"new BigDecimal("][92:"\"10\""][92:")"];
            blackhole = a[93:".equals("]b[93:")"];
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            [99:"BigInteger"] a = [100:"new BigInteger("][100:"\"10\""][100:")"];
            [101:"BigInteger"] b = [102:"new BigInteger("][102:"\"5\""][102:")"];
            blackhole = a[103:".add("]b[103:")"];
        }

        public void multiplyBigInteger() {
            [109:"BigInteger"] a = [110:"new BigInteger("][110:"\"10\""][110:")"];
            [111:"BigInteger"] b = [112:"new BigInteger("][112:"\"5\""][112:")"];
            blackhole = a[113:".multiply("]b[113:")"];
        }

        public void divideBigInteger() {
            [119:"BigInteger"] a = [120:"new BigInteger("][120:"\"10\""][120:")"];
            [121:"BigInteger"] b = [122:"new BigInteger("][122:"\"5\""][122:")"];
            blackhole = a[123:".divide("]b[123:")"];
        }

        public void subtractBigInteger() {
            [129:"BigInteger"] a = [130:"new BigInteger("][130:"\"10\""][130:")"];
            [131:"BigInteger"] b = [132:"new BigInteger("][132:"\"5\""][132:")"];
            blackhole = a[133:".subtract("]b[133:")"];
        }

        public void remainderBigInteger() {
            [139:"BigInteger"] a = [140:"new BigInteger("][140:"\"10\""][140:")"];
            [141:"BigInteger"] b = [142:"new BigInteger("][142:"\"5\""][142:")"];
            blackhole = a[143:".remainder("]b[143:")"];
        }

        public void powBigInteger() {
            [149:"BigInteger"] a = [150:"new BigInteger("][150:"\"10\""][150:")"];
            blackhole = a[151:".pow(2)"];
        }

        public void minBigInteger() {
            [155:"BigInteger"] a = [156:"new BigInteger("][156:"\"10\""][156:")"];
            [157:"BigInteger"] b = [158:"new BigInteger("][158:"\"5\""][158:")"];
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            [163:"BigInteger"] a = [164:"new BigInteger("][164:"\"10\""][164:")"];
            [165:"BigInteger"] b = [166:"new BigInteger("][166:"\"5\""][166:")"];
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            [171:"BigInteger"] a = [172:"new BigInteger("][172:"\"10\""][172:")"];
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {[175:"\n            "]blackhole = [176:"BigInteger.valueOf("]10[176:")"][175:";"][175:"\n        "]}

        public void equalsBigInteger() {
            [180:"BigInteger"] a = [181:"new BigInteger("][181:"\"10\""][181:")"];
            [182:"BigInteger"] b = [183:"new BigInteger("][183:"\"10\""][183:")"];
            blackhole = a[184:".equals("]b[184:")"];
        }

        public void andBigInteger() {
            [190:"BigInteger"] a = [191:"new BigInteger("][191:"\"10\""][191:")"];
            [192:"BigInteger"] b = [193:"new BigInteger("][193:"\"5\""][193:")"];
            blackhole = a[194:".and("]b[194:")"];
        }

        public void gcdBigInteger() {
            [200:"BigInteger"] a = [201:"new BigInteger("][201:"\"10\""][201:")"];
            [202:"BigInteger"] b = [203:"new BigInteger("][203:"\"5\""][203:")"];
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            [208:"BigInteger"] a = [209:"new BigInteger("][209:"\"10\""][209:")"];
            blackhole = a.not();
        }

        public void orBigInteger() {
            [212:"BigInteger"] a = [213:"new BigInteger("][213:"\"10\""][213:")"];
            [214:"BigInteger"] b = [215:"new BigInteger("][215:"\"5\""][215:")"];
            blackhole = a[216:".or("]b[216:")"];
        }

        public void shiftLeftBigInteger() {
            [222:"BigInteger"] a = [223:"new BigInteger("][223:"\"10\""][223:")"];
            blackhole = a[224:".shiftLeft("]2[224:")"];
        }

        public void shiftRightBigInteger() {
            [228:"BigInteger"] a = [229:"new BigInteger("][229:"\"10\""][229:")"];
            blackhole = a[230:".shiftRight("]2[230:")"];
        }

        public void signumBigInteger() {
            [234:"BigInteger"] a = [235:"new BigInteger("][235:"\"10\""][235:")"];
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            [238:"BigInteger"] a = [239:"new BigInteger("][239:"\"10\""][239:")"];
            [240:"BigInteger"] b = [241:"new BigInteger("][241:"\"5\""][241:")"];
            blackhole = a[242:".xor("]b[242:")"];
        }

        public void andNotBigInteger() {
            [248:"BigInteger"] a = [249:"new BigInteger("][249:"\"10\""][249:")"];
            [250:"BigInteger"] b = [251:"new BigInteger("][251:"\"5\""][251:")"];
            blackhole = a[252:".andNot("]b[252:")"];
        }

        public void modBigInteger() {
            [258:"BigInteger"] a = [259:"new BigInteger("][259:"\"10\""][259:")"];
            [260:"BigInteger"] b = [261:"new BigInteger("][261:"\"5\""][261:")"];
            blackhole = a[262:".mod("]b[262:")"];
        }
    }

    class MathData {
        public void minMath() {
            [268:"double"] a = 10.0;
            [269:"double"] b = 5.0;
            blackhole = Math.min(a, b);
        }

        public void maxMath() {
            [272:"double"] a = 10.0;
            [273:"double"] b = 5.0;
            blackhole = Math.max(a, b);
        }

        public void powMath() {
            [276:"double"] a = 10.0;
            [277:"double"] b = 2.0;
            blackhole = Math.pow(a, b);
        }

        public void acosMath() {
            [280:"double"] a = 0.5;
            blackhole = Math.acos(a);
        }

        public void absMath() {
            [282:"double"] a = 0.5;
            blackhole = Math.abs(a);
        }

        public void asinMath() {
            [284:"double"] a = 0.5;
            blackhole = Math.asin(a);
        }

        public void atanMath() {
            [286:"double"] a = 0.5;
            blackhole = Math.atan(a);
        }

        public void atan2Math() {
            [288:"double"] a = 0.5;
            [289:"double"] b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            [292:"double"] a = 8;
            blackhole = Math.cbrt(a);
        }

        public void ceilMath() {
            [294:"double"] a = 8.5;
            blackhole = Math.ceil(a);
        }

        public void cosMath() {
            [296:"double"] a = 0.5;
            blackhole = Math.cos(a);
        }

        public void coshMath() {
            [298:"double"] a = 0.5;
            blackhole = Math.cosh(a);
        }

        public void floorMath() {
            [300:"double"] a = 8.5;
            blackhole = Math.floor(a);
        }

        public void logMath() {
            [302:"double"] a = 8.5;
            blackhole = Math.log(a);
        }

        public void log10Math() {
            [304:"double"] a = 8.5;
            blackhole = Math.log10(a);
        }

        public void randomMath() {[306:"\n            "]blackhole = Math.random()[306:";"][306:"\n        "]}

        public void rintMath() {
            [307:"double"] a = 8.5;
            blackhole = Math.rint(a);
        }

        public void roundMath() {
            [309:"float"] a = 8.5f;
            blackhole = Math.round(a);
        }

        public void sinMath() {
            [311:"double"] a = 0.5;
            blackhole = Math.sin(a);
        }

        public void sinhMath() {
            [313:"double"] a = 0.5;
            blackhole = Math.sinh(a);
        }

        public void sqrtMath() {
            [315:"double"] a = 4;
            blackhole = Math.sqrt(a);
        }

        public void tanMath() {
            [317:"double"] a = 0.5;
            blackhole = Math.tan(a);
        }

        public void tanhMath() {
            [319:"double"] a = 0.5;
            blackhole = Math.tanh(a);
        }

        public void toDegreesMath() {
            [321:"double"] a = 0.5;
            blackhole = Math.toDegrees(a);
        }

        public void toRadiansMath() {
            [323:"double"] a = 45;
            blackhole = Math.toRadians(a);
        }

        public void ulpMath() {
            [325:"double"] a = 45;
            blackhole = Math.ulp(a);
        }

        public void expMath() {
            [327:"double"] a = 2;
            blackhole = Math.exp(a);
        }
    }

    class LongData {
        public void valueOfLong() {
            [329:"String"] a = "10";
            blackhole = [330:"Long.valueOf("]a[330:")"];
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            [333:"String"] a = "10";
            blackhole = [334:"Integer.valueOf("]a[334:")"];
        }
    }

    class FloatData {
        public void valueOfFloat() {
            [337:"String"] a = "10.5";
            blackhole = [338:"Float.valueOf("]a[338:")"];
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            [341:"String"] a = "10.5";
            blackhole = [342:"Double.valueOf("]a[342:")"];
        }
    }


    class CollectionData {
        public void addAllAssign() {
            [345:"Collection<Integer>"] a = [346:"new ArrayList<>(Arrays.asList("]1, 2, 3[346:"))"];
            [347:"Collection<Integer>"] b = [348:"new ArrayList<>(Arrays.asList("]4, 5, 6[348:"))"];
            a[349:".addAll("]b[349:")"];
            blackhole = a;
        }

        public void remove() {
            [355:"Collection<Integer>"] a = [356:"new ArrayList<>(Arrays.asList("]1, 2, 3, 4, 5[356:"))"];
            a[357:".remove("][358:"Integer.valueOf("]3[358:")"][357:")"];
            blackhole = a;
        }

        public void removeAllAssign() {
            [363:"Collection<Integer>"] a = [364:"new ArrayList<>(Arrays.asList("]1, 2, 3, 4, 5[364:"))"];
            [365:"Collection<Integer>"] b = [366:"new ArrayList<>(Arrays.asList("]3, 4[366:"))"];
            a[367:".removeAll("]b[367:")"];
            blackhole = a;
        }

        public void removeAssign() {
            [373:"Collection<Integer>"] a = [374:"new ArrayList<>(Arrays.asList("]1, 2, 3, 4, 5[374:"))"];
            a[375:".remove("][376:"Integer.valueOf("]3[376:")"][375:")"];
            blackhole = a;
        }
    }

    class ArithmeticData {

        public void addAssign() {
            [381:"int"] a = 10;
            [382:"int"] b = 5;
            a += b;
            blackhole = a;
        }

        public void andAssign() {
            [385:"int"] a = 10;
            [386:"int"] b = 5;
            a &= b;
            blackhole = a;
        }

        public void divideAssign() {
            [389:"int"] a = 10;
            [390:"int"] b = 5;
            a /= b;
            blackhole = a;
        }

        public void multiplyAssign() {
            [393:"int"] a = 10;
            [394:"int"] b = 5;
            a *= b;
            blackhole = a;
        }

        public void orAssign() {
            [397:"int"] a = 10;
            [398:"int"] b = 5;
            a |= b;
            blackhole = a;
        }

        public void shiftLeftAssign() {
            [401:"int"] a = 10;
            [402:"int"] b = 2;
            a <<= b;
            blackhole = a;
        }

        public void shiftRightAssign() {
            [405:"int"] a = 10;
            [406:"int"] b = 2;
            a >>= b;
            blackhole = a;
        }

        public void subtractAssign() {
            [409:"int"] a = 10;
            [410:"int"] b = 5;
            a -= b;
            blackhole = a;
        }

        public void xorAssign() {
            [413:"int"] a = 10;
            [414:"int"] b = 5;
            a ^= b;
            blackhole = a;
        }


    }
}
