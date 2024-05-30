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
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' + ' expand='false'>.add(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void multiplyBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' * ' expand='false'>.multiply(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void divideBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"<fold text='' expand='false'></fold>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' / ' expand='false'>.divide(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void subtractBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' - ' expand='false'>.subtract(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void remainderBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.remainder(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void powBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text='²' expand='false'>.pow(2)</fold>;
        }</fold>

        public void minBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.min(b);
        }</fold>

        public void maxBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.max(b);
        }</fold>

        public void negateBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"<fold text='' expand='false'></fold>)</fold>;
            blackhole = a.negate();
        }</fold>

        public void plusBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.plus();
        }</fold>

        public void absBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='-10' expand='false'>"-10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.abs();
        }</fold>

        public void valueOfBigDecimal()<fold text=' { ' expand='false'> {
            </fold>blackhole = <fold text='' expand='false'>BigDecimal.valueOf(</fold>10<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>

        public void equalsBigDecimal() <fold text='{...}' expand='true'>{
            BigDecimal a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigDecimal b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.equals(b);
        }</fold>
    }</fold>

    class BigIntegerData <fold text='{...}' expand='true'>{
        public void addBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(<fold text='10' expand='false'></fold>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' + ' expand='false'>.add(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void multiplyBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' * ' expand='false'>.multiply(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void divideBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' / ' expand='false'>.divide(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void subtractBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' - ' expand='false'>.subtract(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void remainderBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.remainder(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void powBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"<fold text='' expand='false'></fold>)</fold>;
            blackhole = a<fold text='²' expand='false'>.pow(2)</fold>;
        }</fold>

        public void minBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.min(b);
        }</fold>

        public void maxBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.max(b);
        }</fold>

        public void negateBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.negate();
        }</fold>

        public void valueOfBigInteger()<fold text=' { ' expand='false'> {
            </fold>blackhole = <fold text='' expand='false'>BigInteger.valueOf(</fold>10<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>

        public void equalsBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.equals(b);
        }</fold>

        public void andBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' & ' expand='false'>.and(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void gcdBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.gcd(b);
        }</fold>

        public void notBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.not();
        }</fold>

        public void orBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' | ' expand='false'>.or(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void shiftLeftBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' << ' expand='false'>.shiftLeft(</fold>2<fold text='' expand='false'>)</fold>;
        }</fold>

        public void shiftRightBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"<fold text='' expand='false'></fold>)</fold>;
            blackhole = a<fold text=' >> ' expand='false'>.shiftRight(</fold>2<fold text='' expand='false'>)</fold>;
        }</fold>

        public void signumBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.signum();
        }</fold>

        public void xorBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ^ ' expand='false'>.xor(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void andNotBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' & ' expand='false'>.andNot(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public void modBigInteger() <fold text='{...}' expand='true'>{
            BigInteger a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            BigInteger b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.mod(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class MathData <fold text='{...}' expand='true'>{
        public void minMath() <fold text='{...}' expand='true'>{
            double a = 10.0;
            double b = 5.0;
            blackhole = <fold text='min(' expand='false'>Math.min(</fold>a, b<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void maxMath() <fold text='{...}' expand='true'>{
            double a = 10.0;
            double b = 5.0;
            blackhole = <fold text='max(' expand='false'>Math.max(</fold>a, b<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void powMath() <fold text='{...}' expand='true'>{
            double a = 10.0;
            double b = 2.0;
            blackhole = <fold text='' expand='false'>Math.pow(</fold>a<fold text='ᵇ' expand='false'>, b)</fold>;
        }</fold>

        public void acosMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='acos(' expand='false'>Math.acos(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void absMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='|' expand='false'>Math.abs(</fold>a<fold text='|' expand='false'>)</fold>;
        }</fold>

        public void asinMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='asin(' expand='false'>Math.asin(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void atanMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='atan(' expand='false'>Math.atan(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void atan2Math() <fold text='{...}' expand='true'>{
            double a = 0.5;
            double b = 0.5;
            blackhole = Math.atan2(a, b);
        }</fold>

        public void cbrtMath() <fold text='{...}' expand='true'>{
            double a = 8;
            blackhole = <fold text='∛' expand='false'>Math.cbrt(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>

        public void ceilMath() <fold text='{...}' expand='true'>{
            double a = 8.5;
            blackhole = <fold text='ceil(' expand='false'>Math.ceil(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void cosMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='cos(' expand='false'>Math.cos(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void coshMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='cosh(' expand='false'>Math.cosh(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void floorMath() <fold text='{...}' expand='true'>{
            double a = 8.5;
            blackhole = <fold text='floor(' expand='false'>Math.floor(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void logMath() <fold text='{...}' expand='true'>{
            double a = 8.5;
            blackhole = <fold text='ln(' expand='false'>Math.log(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void log10Math() <fold text='{...}' expand='true'>{
            double a = 8.5;
            blackhole = <fold text='log(' expand='false'>Math.log10(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void randomMath()<fold text=' { ' expand='false'> {
            </fold>blackhole = Math.random();<fold text=' }' expand='false'>
        }</fold>

        public void rintMath() <fold text='{...}' expand='true'>{
            double a = 8.5;
            blackhole = <fold text='rint(' expand='false'>Math.rint(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void roundMath() <fold text='{...}' expand='true'>{
            float a = 8.5f;
            blackhole = <fold text='round(' expand='false'>Math.round(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void sinMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='sin(' expand='false'>Math.sin(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void sinhMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='sinh(' expand='false'>Math.sinh(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void sqrtMath() <fold text='{...}' expand='true'>{
            double a = 4;
            blackhole = <fold text='sqrt(' expand='false'>Math.sqrt(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void tanMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='tan(' expand='false'>Math.tan(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void tanhMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='tanh(' expand='false'>Math.tanh(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void toDegreesMath() <fold text='{...}' expand='true'>{
            double a = 0.5;
            blackhole = <fold text='toDegrees(' expand='false'>Math.toDegrees(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void toRadiansMath() <fold text='{...}' expand='true'>{
            double a = 45;
            blackhole = <fold text='toRadians(' expand='false'>Math.toRadians(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void ulpMath() <fold text='{...}' expand='true'>{
            double a = 45;
            blackhole = <fold text='ulp(' expand='false'>Math.ulp(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void expMath() <fold text='{...}' expand='true'>{
            double a = 2;
            blackhole = <fold text='𝑒ᵃ' expand='false'>Math.exp(a)</fold>;
        }</fold>
    }</fold>

    class LongData <fold text='{...}' expand='true'>{
        public void valueOfLong() <fold text='{...}' expand='true'>{
            String a = "10";
            blackhole = <fold text='' expand='false'>Long.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class IntegerData <fold text='{...}' expand='true'>{
        public void valueOfInteger() <fold text='{...}' expand='true'>{
            String a = "10";
            blackhole = <fold text='' expand='false'>Integer.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class FloatData <fold text='{...}' expand='true'>{
        public void valueOfFloat() <fold text='{...}' expand='true'>{
            String a = "10.5";
            blackhole = <fold text='' expand='false'>Float.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class DoubleData <fold text='{...}' expand='true'>{
        public void valueOfDouble() <fold text='{...}' expand='true'>{
            String a = "10.5";
            blackhole = <fold text='' expand='false'>Double.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>


    class CollectionData <fold text='{...}' expand='true'>{
        public void addAllAssign() <fold text='{...}' expand='true'>{
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
            Collection<Integer> b = new ArrayList<>(Arrays.asList(4, 5, 6));
            a.addAll(b);
            blackhole = a;
        }</fold>

        public void remove() <fold text='{...}' expand='true'>{
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            a.remove(<fold text='' expand='false'>Integer.valueOf(</fold>3<fold text='' expand='false'>)</fold>);
            blackhole = a;
        }</fold>

        public void removeAllAssign() <fold text='{...}' expand='true'>{
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            Collection<Integer> b = new ArrayList<>(Arrays.asList(3, 4));
            a.removeAll(b);
            blackhole = a;
        }</fold>

        public void removeAssign() <fold text='{...}' expand='true'>{
            Collection<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            a.remove(<fold text='' expand='false'>Integer.valueOf(</fold>3<fold text='' expand='false'>)</fold>);
            blackhole = a;
        }</fold>
    }</fold>

    class ArithmeticData <fold text='{...}' expand='true'>{

        public void addAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a += b;
            blackhole = a;
        }</fold>

        public void andAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a &= b;
            blackhole = a;
        }</fold>

        public void divideAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a /= b;
            blackhole = a;
        }</fold>

        public void multiplyAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a *= b;
            blackhole = a;
        }</fold>

        public void orAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a |= b;
            blackhole = a;
        }</fold>

        public void shiftLeftAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 2;
            a <<= b;
            blackhole = a;
        }</fold>

        public void shiftRightAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 2;
            a >>= b;
            blackhole = a;
        }</fold>

        public void subtractAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a -= b;
            blackhole = a;
        }</fold>

        public void xorAssign() <fold text='{...}' expand='true'>{
            int a = 10;
            int b = 5;
            a ^= b;
            blackhole = a;
        }</fold>


    }</fold>
}
