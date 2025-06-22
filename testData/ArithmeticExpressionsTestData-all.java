package data;

import <fold text='...' expand='false'>java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;</fold>

@SuppressWarnings("ALL")
public class ArithmeticExpressionsTestData {

    private Object blackhole;

    class BigDecimalData <fold text='{...}' expand='true'>{

        public void addBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' + ' expand='false'>.add(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void multiplyBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' * ' expand='false'>.multiply(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void divideBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' / ' expand='false'>.divide(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void subtractBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' - ' expand='false'>.subtract(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void remainderBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.remainder(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void powBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text='²' expand='false'>.pow(2)</fold>;
        }</fold>

        public void minBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.min(b);
        }</fold>

        public void maxBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.max(b);
        }</fold>

        public void negateBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.negate();
        }</fold>

        public void plusBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.plus();
        }</fold>

        public void absBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='-10' expand='false'>"-10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.abs();
        }</fold>

        public void valueOfBigDecimal()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>blackhole = <fold text='' expand='false'>BigDecimal.valueOf(</fold>10<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
        </fold>}</fold>

        public void equalsBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ≡ ' expand='false'>.equals(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class BigIntegerData <fold text='{...}' expand='true'>{
        public void addBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' + ' expand='false'>.add(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void multiplyBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' * ' expand='false'>.multiply(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void divideBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"<fold text='' expand='false'></fold>)</fold>;
            blackhole = a<fold text=' / ' expand='false'>.divide(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void subtractBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' - ' expand='false'>.subtract(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void remainderBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.remainder(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void powBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"<fold text='' expand='false'></fold>)</fold>;
            blackhole = a<fold text='²' expand='false'>.pow(2)</fold>;
        }</fold>

        public void minBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.min(b);
        }</fold>

        public void maxBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.max(b);
        }</fold>

        public void negateBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.negate();
        }</fold>

        public void valueOfBigInteger()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>blackhole = <fold text='' expand='false'>BigInteger.valueOf(</fold>10<fold text='' expand='false'>)<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void equalsBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ≡ ' expand='false'>.equals(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void andBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' & ' expand='false'>.and(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void gcdBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.gcd(b);
        }</fold>

        public void notBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.not();
        }</fold>

        public void orBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' | ' expand='false'>.or(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void shiftLeftBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' << ' expand='false'>.shiftLeft(</fold>2<fold text='' expand='false'>)</fold>;
        }</fold>

        public void shiftRightBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' >> ' expand='false'>.shiftRight(</fold>2<fold text='' expand='false'>)</fold>;
        }</fold>

        public void signumBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.signum();
        }</fold>

        public void xorBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ^ ' expand='false'>.xor(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void andNotBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(<fold text='5' expand='false'></fold>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' & ' expand='false'>.andNot(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void modBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.mod(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class MathData <fold text='{...}' expand='true'>{
        public void minMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 10.0;
            <fold text='val' expand='false'>double</fold> b = 5.0;
            blackhole = Math.min(a, b);
        }</fold>

        public void maxMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 10.0;
            <fold text='val' expand='false'>double</fold> b = 5.0;
            blackhole = Math.max(a, b);
        }</fold>

        public void powMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 10.0;
            <fold text='val' expand='false'>double</fold> b = 2.0;
            blackhole = Math.pow(a, b);
        }</fold>

        public void acosMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.acos(a);
        }</fold>

        public void absMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.abs(a);
        }</fold>

        public void asinMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.asin(a);
        }</fold>

        public void atanMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.atan(a);
        }</fold>

        public void atan2Math() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            <fold text='val' expand='false'>double</fold> b = 0.5;
            blackhole = Math.atan2(a, b);
        }</fold>

        public void cbrtMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8;
            blackhole = Math.cbrt(a);
        }</fold>

        public void ceilMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = Math.ceil(a);
        }</fold>

        public void cosMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.cos(a);
        }</fold>

        public void coshMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.cosh(a);
        }</fold>

        public void floorMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = Math.floor(a);
        }</fold>

        public void logMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = Math.log(a);
        }</fold>

        public void log10Math() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = Math.log10(a);
        }</fold>

        public void randomMath()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>blackhole = Math.random()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void rintMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = Math.rint(a);
        }</fold>

        public void roundMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>float</fold> a = 8.5f;
            blackhole = Math.round(a);
        }</fold>

        public void sinMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.sin(a);
        }</fold>

        public void sinhMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.sinh(a);
        }</fold>

        public void sqrtMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 4;
            blackhole = Math.sqrt(a);
        }</fold>

        public void tanMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.tan(a);
        }</fold>

        public void tanhMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.tanh(a);
        }</fold>

        public void toDegreesMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = Math.toDegrees(a);
        }</fold>

        public void toRadiansMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 45;
            blackhole = Math.toRadians(a);
        }</fold>

        public void ulpMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 45;
            blackhole = Math.ulp(a);
        }</fold>

        public void expMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 2;
            blackhole = Math.exp(a);
        }</fold>
    }</fold>

    class LongData <fold text='{...}' expand='true'>{
        public void valueOfLong() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10";
            blackhole = <fold text='' expand='false'>Long.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class IntegerData <fold text='{...}' expand='true'>{
        public void valueOfInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10";
            blackhole = <fold text='' expand='false'>Integer.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class FloatData <fold text='{...}' expand='true'>{
        public void valueOfFloat() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10.5";
            blackhole = <fold text='' expand='false'>Float.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class DoubleData <fold text='{...}' expand='true'>{
        public void valueOfDouble() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10.5";
            blackhole = <fold text='' expand='false'>Double.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>


    class CollectionData <fold text='{...}' expand='true'>{
        public void addAllAssign() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3<fold text=']' expand='false'>))</fold>;
            <fold text='val' expand='false'>Collection<Integer></fold> b = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>4, 5, 6<fold text=']' expand='false'>))</fold>;
            a<fold text=' += ' expand='false'>.addAll(</fold>b<fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>

        public void remove() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3, 4, 5<fold text=']' expand='false'>))</fold>;
            a<fold text=' -= ' expand='false'>.remove(</fold><fold text='' expand='false'>Integer.valueOf(</fold>3<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>

        public void removeAllAssign() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3, 4, 5<fold text=']' expand='false'>))</fold>;
            <fold text='val' expand='false'>Collection<Integer></fold> b = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>3, 4<fold text=']' expand='false'>))</fold>;
            a<fold text=' -= ' expand='false'>.removeAll(</fold>b<fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>

        public void removeAssign() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3, 4, 5<fold text=']' expand='false'>))</fold>;
            a<fold text=' -= ' expand='false'>.remove(</fold><fold text='' expand='false'>Integer.valueOf(</fold>3<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>
    }</fold>

    class ArithmeticData <fold text='{...}' expand='true'>{

        public void addAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a += b;
            blackhole = a;
        }</fold>

        public void andAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a &= b;
            blackhole = a;
        }</fold>

        public void divideAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a /= b;
            blackhole = a;
        }</fold>

        public void multiplyAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a *= b;
            blackhole = a;
        }</fold>

        public void orAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a |= b;
            blackhole = a;
        }</fold>

        public void shiftLeftAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 2;
            a <<= b;
            blackhole = a;
        }</fold>

        public void shiftRightAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 2;
            a >>= b;
            blackhole = a;
        }</fold>

        public void subtractAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a -= b;
            blackhole = a;
        }</fold>

        public void xorAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a ^= b;
            blackhole = a;
        }</fold>


    }</fold>
}
