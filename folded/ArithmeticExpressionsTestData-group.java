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
            BigDecimal a = [0:"new BigDecimal("][3:"new BigDecimal("][0:"\"10\""][3:"\"10\""][0:")"][3:")"];
            BigDecimal b = [1:"new BigDecimal("][4:"new BigDecimal("][1:"\"5\""][4:"\"5\""][1:")"][4:")"];
            blackhole = a[2:".add("][5:".add("]b[2:")"][5:")"];
        }

        public void multiplyBigDecimal() {
            BigDecimal a = [6:"new BigDecimal("][9:"new BigDecimal("][6:"\"10\""][9:"\"10\""][6:")"][9:")"];
            BigDecimal b = [7:"new BigDecimal("][10:"new BigDecimal("][7:"\"5\""][10:"\"5\""][7:")"][10:")"];
            blackhole = a[8:".multiply("][11:".multiply("]b[8:")"][11:")"];
        }

        public void divideBigDecimal() {
            BigDecimal a = [12:"new BigDecimal("][15:"new BigDecimal("][12:"\"10\""][15:"\"10\""][12:")"][15:")"];
            BigDecimal b = [13:"new BigDecimal("][16:"new BigDecimal("][13:"\"5\""][16:"\"5\""][13:")"][16:")"];
            blackhole = a[14:".divide("][17:".divide("]b[14:")"][17:")"];
        }

        public void subtractBigDecimal() {
            BigDecimal a = [18:"new BigDecimal("][21:"new BigDecimal("][18:"\"10\""][21:"\"10\""][18:")"][21:")"];
            BigDecimal b = [19:"new BigDecimal("][22:"new BigDecimal("][19:"\"5\""][22:"\"5\""][19:")"][22:")"];
            blackhole = a[20:".subtract("][23:".subtract("]b[20:")"][23:")"];
        }

        public void remainderBigDecimal() {
            BigDecimal a = [24:"new BigDecimal("][27:"new BigDecimal("][24:"\"10\""][27:"\"10\""][24:")"][27:")"];
            BigDecimal b = [25:"new BigDecimal("][28:"new BigDecimal("][25:"\"5\""][28:"\"5\""][25:")"][28:")"];
            blackhole = a[26:".remainder("][29:".remainder("]b[26:")"][29:")"];
        }

        public void powBigDecimal() {
            BigDecimal a = [30:"new BigDecimal("][32:"new BigDecimal("][30:"\"10\""][32:"\"10\""][30:")"][32:")"];
            blackhole = a[31:".pow(2)"][33:".pow(2)"];
        }

        public void minBigDecimal() {
            BigDecimal a = [34:"new BigDecimal("][36:"new BigDecimal("][34:"\"10\""][36:"\"10\""][34:")"][36:")"];
            BigDecimal b = [35:"new BigDecimal("][37:"new BigDecimal("][35:"\"5\""][37:"\"5\""][35:")"][37:")"];
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            BigDecimal a = [38:"new BigDecimal("][40:"new BigDecimal("][38:"\"10\""][40:"\"10\""][38:")"][40:")"];
            BigDecimal b = [39:"new BigDecimal("][41:"new BigDecimal("][39:"\"5\""][41:"\"5\""][39:")"][41:")"];
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            BigDecimal a = [42:"new BigDecimal("][43:"new BigDecimal("][42:"\"10\""][43:"\"10\""][42:")"][43:")"];
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            BigDecimal a = [44:"new BigDecimal("][45:"new BigDecimal("][44:"\"10\""][45:"\"10\""][44:")"][45:")"];
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            BigDecimal a = [46:"new BigDecimal("][47:"new BigDecimal("][46:"\"-10\""][47:"\"-10\""][46:")"][47:")"];
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {
            blackhole = [48:"BigDecimal.valueOf("][49:"BigDecimal.valueOf("]10[48:")"][49:")"];
        }

        public void equalsBigDecimal() {
            BigDecimal a = [50:"new BigDecimal("][52:"new BigDecimal("][50:"\"10\""][52:"\"10\""][50:")"][52:")"];
            BigDecimal b = [51:"new BigDecimal("][53:"new BigDecimal("][51:"\"10\""][53:"\"10\""][51:")"][53:")"];
            blackhole = a.equals(b);
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            BigInteger a = [54:"new BigInteger("][57:"new BigInteger("][54:"\"10\""][57:"\"10\""][54:")"][57:")"];
            BigInteger b = [55:"new BigInteger("][58:"new BigInteger("][55:"\"5\""][58:"\"5\""][55:")"][58:")"];
            blackhole = a[56:".add("][59:".add("]b[56:")"][59:")"];
        }

        public void multiplyBigInteger() {
            BigInteger a = [60:"new BigInteger("][63:"new BigInteger("][60:"\"10\""][63:"\"10\""][60:")"][63:")"];
            BigInteger b = [61:"new BigInteger("][64:"new BigInteger("][61:"\"5\""][64:"\"5\""][61:")"][64:")"];
            blackhole = a[62:".multiply("][65:".multiply("]b[62:")"][65:")"];
        }

        public void divideBigInteger() {
            BigInteger a = [66:"new BigInteger("][69:"new BigInteger("][66:"\"10\""][69:"\"10\""][66:")"][69:")"];
            BigInteger b = [67:"new BigInteger("][70:"new BigInteger("][67:"\"5\""][70:"\"5\""][67:")"][70:")"];
            blackhole = a[68:".divide("][71:".divide("]b[68:")"][71:")"];
        }

        public void subtractBigInteger() {
            BigInteger a = [72:"new BigInteger("][75:"new BigInteger("][72:"\"10\""][75:"\"10\""][72:")"][75:")"];
            BigInteger b = [73:"new BigInteger("][76:"new BigInteger("][73:"\"5\""][76:"\"5\""][73:")"][76:")"];
            blackhole = a[74:".subtract("][77:".subtract("]b[74:")"][77:")"];
        }

        public void remainderBigInteger() {
            BigInteger a = [78:"new BigInteger("][81:"new BigInteger("][78:"\"10\""][81:"\"10\""][78:")"][81:")"];
            BigInteger b = [79:"new BigInteger("][82:"new BigInteger("][79:"\"5\""][82:"\"5\""][79:")"][82:")"];
            blackhole = a[80:".remainder("][83:".remainder("]b[80:")"][83:")"];
        }

        public void powBigInteger() {
            BigInteger a = [84:"new BigInteger("][86:"new BigInteger("][84:"\"10\""][86:"\"10\""][84:")"][86:")"];
            blackhole = a[85:".pow(2)"][87:".pow(2)"];
        }

        public void minBigInteger() {
            BigInteger a = [88:"new BigInteger("][90:"new BigInteger("][88:"\"10\""][90:"\"10\""][88:")"][90:")"];
            BigInteger b = [89:"new BigInteger("][91:"new BigInteger("][89:"\"5\""][91:"\"5\""][89:")"][91:")"];
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            BigInteger a = [92:"new BigInteger("][94:"new BigInteger("][92:"\"10\""][94:"\"10\""][92:")"][94:")"];
            BigInteger b = [93:"new BigInteger("][95:"new BigInteger("][93:"\"5\""][95:"\"5\""][93:")"][95:")"];
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            BigInteger a = [96:"new BigInteger("][97:"new BigInteger("][96:"\"10\""][97:"\"10\""][96:")"][97:")"];
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {
            blackhole = [98:"BigInteger.valueOf("][99:"BigInteger.valueOf("]10[98:")"][99:")"];
        }

        public void equalsBigInteger() {
            BigInteger a = [100:"new BigInteger("][102:"new BigInteger("][100:"\"10\""][102:"\"10\""][100:")"][102:")"];
            BigInteger b = [101:"new BigInteger("][103:"new BigInteger("][101:"\"10\""][103:"\"10\""][101:")"][103:")"];
            blackhole = a.equals(b);
        }

        public void andBigInteger() {
            BigInteger a = [104:"new BigInteger("][107:"new BigInteger("][104:"\"10\""][107:"\"10\""][104:")"][107:")"];
            BigInteger b = [105:"new BigInteger("][108:"new BigInteger("][105:"\"5\""][108:"\"5\""][105:")"][108:")"];
            blackhole = a[106:".and("][109:".and("]b[106:")"][109:")"];
        }

        public void gcdBigInteger() {
            BigInteger a = [110:"new BigInteger("][112:"new BigInteger("][110:"\"10\""][112:"\"10\""][110:")"][112:")"];
            BigInteger b = [111:"new BigInteger("][113:"new BigInteger("][111:"\"5\""][113:"\"5\""][111:")"][113:")"];
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            BigInteger a = [114:"new BigInteger("][115:"new BigInteger("][114:"\"10\""][115:"\"10\""][114:")"][115:")"];
            blackhole = a.not();
        }

        public void orBigInteger() {
            BigInteger a = [116:"new BigInteger("][119:"new BigInteger("][116:"\"10\""][119:"\"10\""][116:")"][119:")"];
            BigInteger b = [117:"new BigInteger("][120:"new BigInteger("][117:"\"5\""][120:"\"5\""][117:")"][120:")"];
            blackhole = a[118:".or("][121:".or("]b[118:")"][121:")"];
        }

        public void shiftLeftBigInteger() {
            BigInteger a = [122:"new BigInteger("][124:"new BigInteger("][122:"\"10\""][124:"\"10\""][122:")"][124:")"];
            blackhole = a[123:".shiftLeft("][125:".shiftLeft("]2[123:")"][125:")"];
        }

        public void shiftRightBigInteger() {
            BigInteger a = [126:"new BigInteger("][128:"new BigInteger("][126:"\"10\""][128:"\"10\""][126:")"][128:")"];
            blackhole = a[127:".shiftRight("][129:".shiftRight("]2[127:")"][129:")"];
        }

        public void signumBigInteger() {
            BigInteger a = [130:"new BigInteger("][131:"new BigInteger("][130:"\"10\""][131:"\"10\""][130:")"][131:")"];
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            BigInteger a = [132:"new BigInteger("][135:"new BigInteger("][132:"\"10\""][135:"\"10\""][132:")"][135:")"];
            BigInteger b = [133:"new BigInteger("][136:"new BigInteger("][133:"\"5\""][136:"\"5\""][133:")"][136:")"];
            blackhole = a[134:".xor("][137:".xor("]b[134:")"][137:")"];
        }

        public void andNotBigInteger() {
            BigInteger a = [138:"new BigInteger("][141:"new BigInteger("][138:"\"10\""][141:"\"10\""][138:")"][141:")"];
            BigInteger b = [139:"new BigInteger("][142:"new BigInteger("][139:"\"5\""][142:"\"5\""][139:")"][142:")"];
            blackhole = a[140:".andNot("][143:".andNot("]b[140:")"][143:")"];
        }

        public void modBigInteger() {
            BigInteger a = [144:"new BigInteger("][147:"new BigInteger("][144:"\"10\""][147:"\"10\""][144:")"][147:")"];
            BigInteger b = [145:"new BigInteger("][148:"new BigInteger("][145:"\"5\""][148:"\"5\""][145:")"][148:")"];
            blackhole = a[146:".mod("][149:".mod("]b[146:")"][149:")"];
        }
    }

    class MathData {
        public void minMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = [150:"Math.min("][151:"Math.min("]a, b[150:")"][151:")"];
        }

        public void maxMath() {
            double a = 10.0;
            double b = 5.0;
            blackhole = [152:"Math.max("][153:"Math.max("]a, b[152:")"][153:")"];
        }

        public void powMath() {
            double a = 10.0;
            double b = 2.0;
            blackhole = [154:"Math.pow("][155:"Math.pow("]a[154:", b)"][155:", b)"];
        }

        public void acosMath() {
            double a = 0.5;
            blackhole = [156:"Math.acos("][157:"Math.acos("]a[156:")"][157:")"];
        }

        public void absMath() {
            double a = 0.5;
            blackhole = [158:"Math.abs("][159:"Math.abs("]a[158:")"][159:")"];
        }

        public void asinMath() {
            double a = 0.5;
            blackhole = [160:"Math.asin("][161:"Math.asin("]a[160:")"][161:")"];
        }

        public void atanMath() {
            double a = 0.5;
            blackhole = [162:"Math.atan("][163:"Math.atan("]a[162:")"][163:")"];
        }

        public void atan2Math() {
            double a = 0.5;
            double b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            double a = 8;
            blackhole = [164:"Math.cbrt("][165:"Math.cbrt("]a[164:")"][165:")"];
        }

        public void ceilMath() {
            double a = 8.5;
            blackhole = [166:"Math.ceil("][167:"Math.ceil("]a[166:")"][167:")"];
        }

        public void cosMath() {
            double a = 0.5;
            blackhole = [168:"Math.cos("][169:"Math.cos("]a[168:")"][169:")"];
        }

        public void coshMath() {
            double a = 0.5;
            blackhole = [170:"Math.cosh("][171:"Math.cosh("]a[170:")"][171:")"];
        }

        public void floorMath() {
            double a = 8.5;
            blackhole = [172:"Math.floor("][173:"Math.floor("]a[172:")"][173:")"];
        }

        public void logMath() {
            double a = 8.5;
            blackhole = [174:"Math.log("][175:"Math.log("]a[174:")"][175:")"];
        }

        public void log10Math() {
            double a = 8.5;
            blackhole = [176:"Math.log10("][177:"Math.log10("]a[176:")"][177:")"];
        }

        public void randomMath() {
            blackhole = Math.random();
        }

        public void rintMath() {
            double a = 8.5;
            blackhole = [178:"Math.rint("][179:"Math.rint("]a[178:")"][179:")"];
        }

        public void roundMath() {
            float a = 8.5f;
            blackhole = [180:"Math.round("][181:"Math.round("]a[180:")"][181:")"];
        }

        public void sinMath() {
            double a = 0.5;
            blackhole = [182:"Math.sin("][183:"Math.sin("]a[182:")"][183:")"];
        }

        public void sinhMath() {
            double a = 0.5;
            blackhole = [184:"Math.sinh("][185:"Math.sinh("]a[184:")"][185:")"];
        }

        public void sqrtMath() {
            double a = 4;
            blackhole = [186:"Math.sqrt("][187:"Math.sqrt("]a[186:")"][187:")"];
        }

        public void tanMath() {
            double a = 0.5;
            blackhole = [188:"Math.tan("][189:"Math.tan("]a[188:")"][189:")"];
        }

        public void tanhMath() {
            double a = 0.5;
            blackhole = [190:"Math.tanh("][191:"Math.tanh("]a[190:")"][191:")"];
        }

        public void toDegreesMath() {
            double a = 0.5;
            blackhole = [192:"Math.toDegrees("][193:"Math.toDegrees("]a[192:")"][193:")"];
        }

        public void toRadiansMath() {
            double a = 45;
            blackhole = [194:"Math.toRadians("][195:"Math.toRadians("]a[194:")"][195:")"];
        }

        public void ulpMath() {
            double a = 45;
            blackhole = [196:"Math.ulp("][197:"Math.ulp("]a[196:")"][197:")"];
        }

        public void expMath() {
            double a = 2;
            blackhole = [198:"Math.exp(a)"][199:"Math.exp(a)"];
        }
    }

    class LongData {
        public void valueOfLong() {
            String a = "10";
            blackhole = [200:"Long.valueOf("][201:"Long.valueOf("]a[200:")"][201:")"];
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            String a = "10";
            blackhole = [202:"Integer.valueOf("][203:"Integer.valueOf("]a[202:")"][203:")"];
        }
    }

    class FloatData {
        public void valueOfFloat() {
            String a = "10.5";
            blackhole = [204:"Float.valueOf("][205:"Float.valueOf("]a[204:")"][205:")"];
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            String a = "10.5";
            blackhole = [206:"Double.valueOf("][207:"Double.valueOf("]a[206:")"][207:")"];
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
            a.remove([208:"Integer.valueOf("][209:"Integer.valueOf("]3[208:")"][209:")"]);
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
            a.remove([210:"Integer.valueOf("][211:"Integer.valueOf("]3[210:")"][211:")"]);
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
