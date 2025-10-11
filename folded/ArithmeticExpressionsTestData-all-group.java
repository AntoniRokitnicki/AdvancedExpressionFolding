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
            [0:"BigDecimal"][5:"BigDecimal"] a = [1:"new BigDecimal("][6:"new BigDecimal("][1:"\"10\""][6:"\"10\""][1:")"][6:")"];
            [2:"BigDecimal"][7:"BigDecimal"] b = [3:"new BigDecimal("][8:"new BigDecimal("][3:"\"5\""][8:"\"5\""][3:")"][8:")"];
            blackhole = a[4:".add("][9:".add("]b[4:")"][9:")"];
        }

        public void multiplyBigDecimal() {
            [10:"BigDecimal"][15:"BigDecimal"] a = [11:"new BigDecimal("][16:"new BigDecimal("][11:"\"10\""][16:"\"10\""][11:")"][16:")"];
            [12:"BigDecimal"][17:"BigDecimal"] b = [13:"new BigDecimal("][18:"new BigDecimal("][13:"\"5\""][18:"\"5\""][13:")"][18:")"];
            blackhole = a[14:".multiply("][19:".multiply("]b[14:")"][19:")"];
        }

        public void divideBigDecimal() {
            [20:"BigDecimal"][25:"BigDecimal"] a = [21:"new BigDecimal("][26:"new BigDecimal("][21:"\"10\""][26:"\"10\""][21:")"][26:")"];
            [22:"BigDecimal"][27:"BigDecimal"] b = [23:"new BigDecimal("][28:"new BigDecimal("][23:"\"5\""][28:"\"5\""][23:")"][28:")"];
            blackhole = a[24:".divide("][29:".divide("]b[24:")"][29:")"];
        }

        public void subtractBigDecimal() {
            [30:"BigDecimal"][35:"BigDecimal"] a = [31:"new BigDecimal("][36:"new BigDecimal("][31:"\"10\""][36:"\"10\""][31:")"][36:")"];
            [32:"BigDecimal"][37:"BigDecimal"] b = [33:"new BigDecimal("][38:"new BigDecimal("][33:"\"5\""][38:"\"5\""][33:")"][38:")"];
            blackhole = a[34:".subtract("][39:".subtract("]b[34:")"][39:")"];
        }

        public void remainderBigDecimal() {
            [40:"BigDecimal"][45:"BigDecimal"] a = [41:"new BigDecimal("][46:"new BigDecimal("][41:"\"10\""][46:"\"10\""][41:")"][46:")"];
            [42:"BigDecimal"][47:"BigDecimal"] b = [43:"new BigDecimal("][48:"new BigDecimal("][43:"\"5\""][48:"\"5\""][43:")"][48:")"];
            blackhole = a[44:".remainder("][49:".remainder("]b[44:")"][49:")"];
        }

        public void powBigDecimal() {
            [50:"BigDecimal"][53:"BigDecimal"] a = [51:"new BigDecimal("][54:"new BigDecimal("][51:"\"10\""][54:"\"10\""][51:")"][54:")"];
            blackhole = a[52:".pow(2)"][55:".pow(2)"];
        }

        public void minBigDecimal() {
            [56:"BigDecimal"][60:"BigDecimal"] a = [57:"new BigDecimal("][61:"new BigDecimal("][57:"\"10\""][61:"\"10\""][57:")"][61:")"];
            [58:"BigDecimal"][62:"BigDecimal"] b = [59:"new BigDecimal("][63:"new BigDecimal("][59:"\"5\""][63:"\"5\""][59:")"][63:")"];
            blackhole = a.min(b);
        }

        public void maxBigDecimal() {
            [64:"BigDecimal"][68:"BigDecimal"] a = [65:"new BigDecimal("][69:"new BigDecimal("][65:"\"10\""][69:"\"10\""][65:")"][69:")"];
            [66:"BigDecimal"][70:"BigDecimal"] b = [67:"new BigDecimal("][71:"new BigDecimal("][67:"\"5\""][71:"\"5\""][67:")"][71:")"];
            blackhole = a.max(b);
        }

        public void negateBigDecimal() {
            [72:"BigDecimal"][74:"BigDecimal"] a = [73:"new BigDecimal("][75:"new BigDecimal("][73:"\"10\""][75:"\"10\""][73:")"][75:")"];
            blackhole = a.negate();
        }

        public void plusBigDecimal() {
            [76:"BigDecimal"][78:"BigDecimal"] a = [77:"new BigDecimal("][79:"new BigDecimal("][77:"\"10\""][79:"\"10\""][77:")"][79:")"];
            blackhole = a.plus();
        }

        public void absBigDecimal() {
            [80:"BigDecimal"][82:"BigDecimal"] a = [81:"new BigDecimal("][83:"new BigDecimal("][81:"\"-10\""][83:"\"-10\""][81:")"][83:")"];
            blackhole = a.abs();
        }

        public void valueOfBigDecimal() {[84:"\n            "]blackhole = [85:"BigDecimal.valueOf("][86:"BigDecimal.valueOf("][87:"BigDecimal.valueOf("][88:"BigDecimal.valueOf("]10[85:")"][86:")"][87:")"][88:")"][84:";"][84:"\n        "]}

        public void equalsBigDecimal() {
            [89:"BigDecimal"][94:"BigDecimal"] a = [90:"new BigDecimal("][95:"new BigDecimal("][90:"\"10\""][95:"\"10\""][90:")"][95:")"];
            [91:"BigDecimal"][96:"BigDecimal"] b = [92:"new BigDecimal("][97:"new BigDecimal("][92:"\"10\""][97:"\"10\""][92:")"][97:")"];
            blackhole = a[93:".equals("][98:".equals("]b[93:")"][98:")"];
        }
    }

    class BigIntegerData {
        public void addBigInteger() {
            [99:"BigInteger"][104:"BigInteger"] a = [100:"new BigInteger("][105:"new BigInteger("][100:"\"10\""][105:"\"10\""][100:")"][105:")"];
            [101:"BigInteger"][106:"BigInteger"] b = [102:"new BigInteger("][107:"new BigInteger("][102:"\"5\""][107:"\"5\""][102:")"][107:")"];
            blackhole = a[103:".add("][108:".add("]b[103:")"][108:")"];
        }

        public void multiplyBigInteger() {
            [109:"BigInteger"][114:"BigInteger"] a = [110:"new BigInteger("][115:"new BigInteger("][110:"\"10\""][115:"\"10\""][110:")"][115:")"];
            [111:"BigInteger"][116:"BigInteger"] b = [112:"new BigInteger("][117:"new BigInteger("][112:"\"5\""][117:"\"5\""][112:")"][117:")"];
            blackhole = a[113:".multiply("][118:".multiply("]b[113:")"][118:")"];
        }

        public void divideBigInteger() {
            [119:"BigInteger"][124:"BigInteger"] a = [120:"new BigInteger("][125:"new BigInteger("][120:"\"10\""][125:"\"10\""][120:")"][125:")"];
            [121:"BigInteger"][126:"BigInteger"] b = [122:"new BigInteger("][127:"new BigInteger("][122:"\"5\""][127:"\"5\""][122:")"][127:")"];
            blackhole = a[123:".divide("][128:".divide("]b[123:")"][128:")"];
        }

        public void subtractBigInteger() {
            [129:"BigInteger"][134:"BigInteger"] a = [130:"new BigInteger("][135:"new BigInteger("][130:"\"10\""][135:"\"10\""][130:")"][135:")"];
            [131:"BigInteger"][136:"BigInteger"] b = [132:"new BigInteger("][137:"new BigInteger("][132:"\"5\""][137:"\"5\""][132:")"][137:")"];
            blackhole = a[133:".subtract("][138:".subtract("]b[133:")"][138:")"];
        }

        public void remainderBigInteger() {
            [139:"BigInteger"][144:"BigInteger"] a = [140:"new BigInteger("][145:"new BigInteger("][140:"\"10\""][145:"\"10\""][140:")"][145:")"];
            [141:"BigInteger"][146:"BigInteger"] b = [142:"new BigInteger("][147:"new BigInteger("][142:"\"5\""][147:"\"5\""][142:")"][147:")"];
            blackhole = a[143:".remainder("][148:".remainder("]b[143:")"][148:")"];
        }

        public void powBigInteger() {
            [149:"BigInteger"][152:"BigInteger"] a = [150:"new BigInteger("][153:"new BigInteger("][150:"\"10\""][153:"\"10\""][150:")"][153:")"];
            blackhole = a[151:".pow(2)"][154:".pow(2)"];
        }

        public void minBigInteger() {
            [155:"BigInteger"][159:"BigInteger"] a = [156:"new BigInteger("][160:"new BigInteger("][156:"\"10\""][160:"\"10\""][156:")"][160:")"];
            [157:"BigInteger"][161:"BigInteger"] b = [158:"new BigInteger("][162:"new BigInteger("][158:"\"5\""][162:"\"5\""][158:")"][162:")"];
            blackhole = a.min(b);
        }

        public void maxBigInteger() {
            [163:"BigInteger"][167:"BigInteger"] a = [164:"new BigInteger("][168:"new BigInteger("][164:"\"10\""][168:"\"10\""][164:")"][168:")"];
            [165:"BigInteger"][169:"BigInteger"] b = [166:"new BigInteger("][170:"new BigInteger("][166:"\"5\""][170:"\"5\""][166:")"][170:")"];
            blackhole = a.max(b);
        }

        public void negateBigInteger() {
            [171:"BigInteger"][173:"BigInteger"] a = [172:"new BigInteger("][174:"new BigInteger("][172:"\"10\""][174:"\"10\""][172:")"][174:")"];
            blackhole = a.negate();
        }

        public void valueOfBigInteger() {[175:"\n            "]blackhole = [176:"BigInteger.valueOf("][177:"BigInteger.valueOf("][178:"BigInteger.valueOf("][179:"BigInteger.valueOf("]10[176:")"][177:")"][178:")"][179:")"][175:";"][175:"\n        "]}

        public void equalsBigInteger() {
            [180:"BigInteger"][185:"BigInteger"] a = [181:"new BigInteger("][186:"new BigInteger("][181:"\"10\""][186:"\"10\""][181:")"][186:")"];
            [182:"BigInteger"][187:"BigInteger"] b = [183:"new BigInteger("][188:"new BigInteger("][183:"\"10\""][188:"\"10\""][183:")"][188:")"];
            blackhole = a[184:".equals("][189:".equals("]b[184:")"][189:")"];
        }

        public void andBigInteger() {
            [190:"BigInteger"][195:"BigInteger"] a = [191:"new BigInteger("][196:"new BigInteger("][191:"\"10\""][196:"\"10\""][191:")"][196:")"];
            [192:"BigInteger"][197:"BigInteger"] b = [193:"new BigInteger("][198:"new BigInteger("][193:"\"5\""][198:"\"5\""][193:")"][198:")"];
            blackhole = a[194:".and("][199:".and("]b[194:")"][199:")"];
        }

        public void gcdBigInteger() {
            [200:"BigInteger"][204:"BigInteger"] a = [201:"new BigInteger("][205:"new BigInteger("][201:"\"10\""][205:"\"10\""][201:")"][205:")"];
            [202:"BigInteger"][206:"BigInteger"] b = [203:"new BigInteger("][207:"new BigInteger("][203:"\"5\""][207:"\"5\""][203:")"][207:")"];
            blackhole = a.gcd(b);
        }

        public void notBigInteger() {
            [208:"BigInteger"][210:"BigInteger"] a = [209:"new BigInteger("][211:"new BigInteger("][209:"\"10\""][211:"\"10\""][209:")"][211:")"];
            blackhole = a.not();
        }

        public void orBigInteger() {
            [212:"BigInteger"][217:"BigInteger"] a = [213:"new BigInteger("][218:"new BigInteger("][213:"\"10\""][218:"\"10\""][213:")"][218:")"];
            [214:"BigInteger"][219:"BigInteger"] b = [215:"new BigInteger("][220:"new BigInteger("][215:"\"5\""][220:"\"5\""][215:")"][220:")"];
            blackhole = a[216:".or("][221:".or("]b[216:")"][221:")"];
        }

        public void shiftLeftBigInteger() {
            [222:"BigInteger"][225:"BigInteger"] a = [223:"new BigInteger("][226:"new BigInteger("][223:"\"10\""][226:"\"10\""][223:")"][226:")"];
            blackhole = a[224:".shiftLeft("][227:".shiftLeft("]2[224:")"][227:")"];
        }

        public void shiftRightBigInteger() {
            [228:"BigInteger"][231:"BigInteger"] a = [229:"new BigInteger("][232:"new BigInteger("][229:"\"10\""][232:"\"10\""][229:")"][232:")"];
            blackhole = a[230:".shiftRight("][233:".shiftRight("]2[230:")"][233:")"];
        }

        public void signumBigInteger() {
            [234:"BigInteger"][236:"BigInteger"] a = [235:"new BigInteger("][237:"new BigInteger("][235:"\"10\""][237:"\"10\""][235:")"][237:")"];
            blackhole = a.signum();
        }

        public void xorBigInteger() {
            [238:"BigInteger"][243:"BigInteger"] a = [239:"new BigInteger("][244:"new BigInteger("][239:"\"10\""][244:"\"10\""][239:")"][244:")"];
            [240:"BigInteger"][245:"BigInteger"] b = [241:"new BigInteger("][246:"new BigInteger("][241:"\"5\""][246:"\"5\""][241:")"][246:")"];
            blackhole = a[242:".xor("][247:".xor("]b[242:")"][247:")"];
        }

        public void andNotBigInteger() {
            [248:"BigInteger"][253:"BigInteger"] a = [249:"new BigInteger("][254:"new BigInteger("][249:"\"10\""][254:"\"10\""][249:")"][254:")"];
            [250:"BigInteger"][255:"BigInteger"] b = [251:"new BigInteger("][256:"new BigInteger("][251:"\"5\""][256:"\"5\""][251:")"][256:")"];
            blackhole = a[252:".andNot("][257:".andNot("]b[252:")"][257:")"];
        }

        public void modBigInteger() {
            [258:"BigInteger"][263:"BigInteger"] a = [259:"new BigInteger("][264:"new BigInteger("][259:"\"10\""][264:"\"10\""][259:")"][264:")"];
            [260:"BigInteger"][265:"BigInteger"] b = [261:"new BigInteger("][266:"new BigInteger("][261:"\"5\""][266:"\"5\""][261:")"][266:")"];
            blackhole = a[262:".mod("][267:".mod("]b[262:")"][267:")"];
        }
    }

    class MathData {
        public void minMath() {
            [268:"double"][270:"double"] a = 10.0;
            [269:"double"][271:"double"] b = 5.0;
            blackhole = Math.min(a, b);
        }

        public void maxMath() {
            [272:"double"][274:"double"] a = 10.0;
            [273:"double"][275:"double"] b = 5.0;
            blackhole = Math.max(a, b);
        }

        public void powMath() {
            [276:"double"][278:"double"] a = 10.0;
            [277:"double"][279:"double"] b = 2.0;
            blackhole = Math.pow(a, b);
        }

        public void acosMath() {
            [280:"double"][281:"double"] a = 0.5;
            blackhole = Math.acos(a);
        }

        public void absMath() {
            [282:"double"][283:"double"] a = 0.5;
            blackhole = Math.abs(a);
        }

        public void asinMath() {
            [284:"double"][285:"double"] a = 0.5;
            blackhole = Math.asin(a);
        }

        public void atanMath() {
            [286:"double"][287:"double"] a = 0.5;
            blackhole = Math.atan(a);
        }

        public void atan2Math() {
            [288:"double"][290:"double"] a = 0.5;
            [289:"double"][291:"double"] b = 0.5;
            blackhole = Math.atan2(a, b);
        }

        public void cbrtMath() {
            [292:"double"][293:"double"] a = 8;
            blackhole = Math.cbrt(a);
        }

        public void ceilMath() {
            [294:"double"][295:"double"] a = 8.5;
            blackhole = Math.ceil(a);
        }

        public void cosMath() {
            [296:"double"][297:"double"] a = 0.5;
            blackhole = Math.cos(a);
        }

        public void coshMath() {
            [298:"double"][299:"double"] a = 0.5;
            blackhole = Math.cosh(a);
        }

        public void floorMath() {
            [300:"double"][301:"double"] a = 8.5;
            blackhole = Math.floor(a);
        }

        public void logMath() {
            [302:"double"][303:"double"] a = 8.5;
            blackhole = Math.log(a);
        }

        public void log10Math() {
            [304:"double"][305:"double"] a = 8.5;
            blackhole = Math.log10(a);
        }

        public void randomMath() {[306:"\n            "]blackhole = Math.random()[306:";"][306:"\n        "]}

        public void rintMath() {
            [307:"double"][308:"double"] a = 8.5;
            blackhole = Math.rint(a);
        }

        public void roundMath() {
            [309:"float"][310:"float"] a = 8.5f;
            blackhole = Math.round(a);
        }

        public void sinMath() {
            [311:"double"][312:"double"] a = 0.5;
            blackhole = Math.sin(a);
        }

        public void sinhMath() {
            [313:"double"][314:"double"] a = 0.5;
            blackhole = Math.sinh(a);
        }

        public void sqrtMath() {
            [315:"double"][316:"double"] a = 4;
            blackhole = Math.sqrt(a);
        }

        public void tanMath() {
            [317:"double"][318:"double"] a = 0.5;
            blackhole = Math.tan(a);
        }

        public void tanhMath() {
            [319:"double"][320:"double"] a = 0.5;
            blackhole = Math.tanh(a);
        }

        public void toDegreesMath() {
            [321:"double"][322:"double"] a = 0.5;
            blackhole = Math.toDegrees(a);
        }

        public void toRadiansMath() {
            [323:"double"][324:"double"] a = 45;
            blackhole = Math.toRadians(a);
        }

        public void ulpMath() {
            [325:"double"][326:"double"] a = 45;
            blackhole = Math.ulp(a);
        }

        public void expMath() {
            [327:"double"][328:"double"] a = 2;
            blackhole = Math.exp(a);
        }
    }

    class LongData {
        public void valueOfLong() {
            [329:"String"][331:"String"] a = "10";
            blackhole = [330:"Long.valueOf("][332:"Long.valueOf("]a[330:")"][332:")"];
        }
    }

    class IntegerData {
        public void valueOfInteger() {
            [333:"String"][335:"String"] a = "10";
            blackhole = [334:"Integer.valueOf("][336:"Integer.valueOf("]a[334:")"][336:")"];
        }
    }

    class FloatData {
        public void valueOfFloat() {
            [337:"String"][339:"String"] a = "10.5";
            blackhole = [338:"Float.valueOf("][340:"Float.valueOf("]a[338:")"][340:")"];
        }
    }

    class DoubleData {
        public void valueOfDouble() {
            [341:"String"][343:"String"] a = "10.5";
            blackhole = [342:"Double.valueOf("][344:"Double.valueOf("]a[342:")"][344:")"];
        }
    }


    class CollectionData {
        public void addAllAssign() {
            [345:"Collection<Integer>"][350:"Collection<Integer>"] a = [346:"new ArrayList<>(Arrays.asList("][351:"new ArrayList<>(Arrays.asList("]1, 2, 3[346:"))"][351:"))"];
            [347:"Collection<Integer>"][352:"Collection<Integer>"] b = [348:"new ArrayList<>(Arrays.asList("][353:"new ArrayList<>(Arrays.asList("]4, 5, 6[348:"))"][353:"))"];
            a[349:".addAll("][354:".addAll("]b[349:")"][354:")"];
            blackhole = a;
        }

        public void remove() {
            [355:"Collection<Integer>"][359:"Collection<Integer>"] a = [356:"new ArrayList<>(Arrays.asList("][360:"new ArrayList<>(Arrays.asList("]1, 2, 3, 4, 5[356:"))"][360:"))"];
            a[357:".remove("][361:".remove("][358:"Integer.valueOf("][362:"Integer.valueOf("]3[358:")"][362:")"][357:")"][361:")"];
            blackhole = a;
        }

        public void removeAllAssign() {
            [363:"Collection<Integer>"][368:"Collection<Integer>"] a = [364:"new ArrayList<>(Arrays.asList("][369:"new ArrayList<>(Arrays.asList("]1, 2, 3, 4, 5[364:"))"][369:"))"];
            [365:"Collection<Integer>"][370:"Collection<Integer>"] b = [366:"new ArrayList<>(Arrays.asList("][371:"new ArrayList<>(Arrays.asList("]3, 4[366:"))"][371:"))"];
            a[367:".removeAll("][372:".removeAll("]b[367:")"][372:")"];
            blackhole = a;
        }

        public void removeAssign() {
            [373:"Collection<Integer>"][377:"Collection<Integer>"] a = [374:"new ArrayList<>(Arrays.asList("][378:"new ArrayList<>(Arrays.asList("]1, 2, 3, 4, 5[374:"))"][378:"))"];
            a[375:".remove("][379:".remove("][376:"Integer.valueOf("][380:"Integer.valueOf("]3[376:")"][380:")"][375:")"][379:")"];
            blackhole = a;
        }
    }

    class ArithmeticData {

        public void addAssign() {
            [381:"int"][383:"int"] a = 10;
            [382:"int"][384:"int"] b = 5;
            a += b;
            blackhole = a;
        }

        public void andAssign() {
            [385:"int"][387:"int"] a = 10;
            [386:"int"][388:"int"] b = 5;
            a &= b;
            blackhole = a;
        }

        public void divideAssign() {
            [389:"int"][391:"int"] a = 10;
            [390:"int"][392:"int"] b = 5;
            a /= b;
            blackhole = a;
        }

        public void multiplyAssign() {
            [393:"int"][395:"int"] a = 10;
            [394:"int"][396:"int"] b = 5;
            a *= b;
            blackhole = a;
        }

        public void orAssign() {
            [397:"int"][399:"int"] a = 10;
            [398:"int"][400:"int"] b = 5;
            a |= b;
            blackhole = a;
        }

        public void shiftLeftAssign() {
            [401:"int"][403:"int"] a = 10;
            [402:"int"][404:"int"] b = 2;
            a <<= b;
            blackhole = a;
        }

        public void shiftRightAssign() {
            [405:"int"][407:"int"] a = 10;
            [406:"int"][408:"int"] b = 2;
            a >>= b;
            blackhole = a;
        }

        public void subtractAssign() {
            [409:"int"][411:"int"] a = 10;
            [410:"int"][412:"int"] b = 5;
            a -= b;
            blackhole = a;
        }

        public void xorAssign() {
            [413:"int"][415:"int"] a = 10;
            [414:"int"][416:"int"] b = 5;
            a ^= b;
            blackhole = a;
        }


    }
}
