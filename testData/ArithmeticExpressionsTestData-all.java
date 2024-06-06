<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.math.BigDecimal;
<fold text='🚢' expand='false'>import</fold> java.math.BigInteger;
<fold text='🚢' expand='false'>import</fold> java.util.ArrayList;
<fold text='🚢' expand='false'>import</fold> java.util.Arrays;
<fold text='🚢' expand='false'>import</fold> java.util.Collection;</fold>

@SuppressWarnings("ALL")
public class ArithmeticExpressionsTestData {

    private Object blackhole;

    class BigDecimalData <fold text='{...}' expand='true'>{

        public <fold text='💀' expand='false'>void</fold> addBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' + ' expand='false'>.add(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> multiplyBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' * ' expand='false'>.multiply(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> divideBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' / ' expand='false'>.divide(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> subtractBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' - ' expand='false'>.subtract(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> remainderBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.remainder(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> powBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text='²' expand='false'>.pow(2)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> minBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.min(b);
        }</fold>

        public <fold text='💀' expand='false'>void</fold> maxBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(<fold text='10' expand='false'></fold>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.max(b);
        }</fold>

        public <fold text='💀' expand='false'>void</fold> negateBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.negate();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> plusBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.plus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> absBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='-10' expand='false'>"-10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.abs();
        }</fold>

        public void valueOfBigDecimal()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>blackhole = <fold text='' expand='false'>BigDecimal.valueOf(</fold>10<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> equalsBigDecimal() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigDecimal</fold> a = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigDecimal</fold> b = <fold text='' expand='false'>new BigDecimal(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ≡ ' expand='false'>.equals(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class BigIntegerData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> addBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' + ' expand='false'>.add(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> multiplyBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' * ' expand='false'>.multiply(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> divideBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' / ' expand='false'>.divide(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> subtractBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' - ' expand='false'>.subtract(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> remainderBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.remainder(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> powBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text='²' expand='false'>.pow(2)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> minBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.min(b);
        }</fold>

        public <fold text='💀' expand='false'>void</fold> maxBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(<fold text='10' expand='false'></fold>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.max(b);
        }</fold>

        public <fold text='💀' expand='false'>void</fold> negateBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.negate();
        }</fold>

        public void valueOfBigInteger()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>blackhole = <fold text='' expand='false'>BigInteger.valueOf(</fold>10<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> equalsBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ≡ ' expand='false'>.equals(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> andBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' & ' expand='false'>.and(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> gcdBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.gcd(b);
        }</fold>

        public <fold text='💀' expand='false'>void</fold> notBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.not();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> orBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' | ' expand='false'>.or(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> shiftLeftBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"<fold text='' expand='false'></fold>)</fold>;
            blackhole = a<fold text=' << ' expand='false'>.shiftLeft(</fold>2<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> shiftRightBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' >> ' expand='false'>.shiftRight(</fold>2<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> signumBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a.signum();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> xorBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' ^ ' expand='false'>.xor(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> andNotBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' & ' expand='false'>.andNot(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> modBigInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>BigInteger</fold> a = <fold text='' expand='false'>new BigInteger(</fold><fold text='10' expand='false'>"10"</fold><fold text='' expand='false'>)</fold>;
            <fold text='val' expand='false'>BigInteger</fold> b = <fold text='' expand='false'>new BigInteger(</fold><fold text='5' expand='false'>"5"</fold><fold text='' expand='false'>)</fold>;
            blackhole = a<fold text=' % ' expand='false'>.mod(</fold>b<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class MathData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> minMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 10.0;
            <fold text='val' expand='false'>double</fold> b = 5.0;
            blackhole = <fold text='min(' expand='false'>Math.min(</fold>a, b<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> maxMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 10.0;
            <fold text='val' expand='false'>double</fold> b = 5.0;
            blackhole = <fold text='max(' expand='false'>Math.max(</fold>a, b<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> powMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 10.0;
            <fold text='val' expand='false'>double</fold> b = 2.0;
            blackhole = <fold text='' expand='false'>Math.pow(</fold>a<fold text='ᵇ' expand='false'>, b)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> acosMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='acos(' expand='false'>Math.acos(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> absMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='|' expand='false'>Math.abs(</fold>a<fold text='|' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> asinMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='asin(' expand='false'>Math.asin(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> atanMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='atan(' expand='false'>Math.atan(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> atan2Math() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            <fold text='val' expand='false'>double</fold> b = 0.5;
            blackhole = Math.atan2(a, b);
        }</fold>

        public <fold text='💀' expand='false'>void</fold> cbrtMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8;
            blackhole = <fold text='∛' expand='false'>Math.cbrt(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> ceilMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = <fold text='ceil(' expand='false'>Math.ceil(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> cosMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='cos(' expand='false'>Math.cos(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> coshMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='cosh(' expand='false'>Math.cosh(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> floorMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = <fold text='floor(' expand='false'>Math.floor(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> logMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = <fold text='ln(' expand='false'>Math.log(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> log10Math() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = <fold text='log(' expand='false'>Math.log10(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public void randomMath()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>blackhole = Math.random()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> rintMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 8.5;
            blackhole = <fold text='rint(' expand='false'>Math.rint(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> roundMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>float</fold> a = 8.5f;
            blackhole = <fold text='round(' expand='false'>Math.round(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> sinMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='sin(' expand='false'>Math.sin(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> sinhMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='sinh(' expand='false'>Math.sinh(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> sqrtMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 4;
            blackhole = <fold text='sqrt(' expand='false'>Math.sqrt(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> tanMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='tan(' expand='false'>Math.tan(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> tanhMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='tanh(' expand='false'>Math.tanh(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> toDegreesMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 0.5;
            blackhole = <fold text='toDegrees(' expand='false'>Math.toDegrees(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> toRadiansMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 45;
            blackhole = <fold text='toRadians(' expand='false'>Math.toRadians(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> ulpMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 45;
            blackhole = <fold text='ulp(' expand='false'>Math.ulp(</fold>a<fold text=')' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> expMath() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>double</fold> a = 2;
            blackhole = <fold text='𝑒ᵃ' expand='false'>Math.exp(a)</fold>;
        }</fold>
    }</fold>

    class LongData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> valueOfLong() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10";
            blackhole = <fold text='' expand='false'>Long.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class IntegerData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> valueOfInteger() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10";
            blackhole = <fold text='' expand='false'>Integer.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class FloatData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> valueOfFloat() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10.5";
            blackhole = <fold text='' expand='false'>Float.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    class DoubleData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> valueOfDouble() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> a = "10.5";
            blackhole = <fold text='' expand='false'>Double.valueOf(</fold>a<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>


    class CollectionData <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> addAllAssign() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3<fold text=']' expand='false'>))</fold>;
            <fold text='val' expand='false'>Collection<Integer></fold> b = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>4, 5, 6<fold text=']' expand='false'>))</fold>;
            a<fold text=' += ' expand='false'>.addAll(</fold>b<fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> remove() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3, 4, 5<fold text=']' expand='false'>))</fold>;
            a<fold text=' -= ' expand='false'>.remove(</fold><fold text='' expand='false'>Integer.valueOf(</fold>3<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> removeAllAssign() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3, 4, 5<fold text=']' expand='false'>))</fold>;
            <fold text='val' expand='false'>Collection<Integer></fold> b = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>3, 4<fold text=']' expand='false'>))</fold>;
            a<fold text=' -= ' expand='false'>.removeAll(</fold>b<fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> removeAssign() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Collection<Integer></fold> a = <fold text='[' expand='false'>new ArrayList<>(Arrays.asList(</fold>1, 2, 3, 4, 5<fold text=']' expand='false'>))</fold>;
            a<fold text=' -= ' expand='false'>.remove(</fold><fold text='' expand='false'>Integer.valueOf(</fold>3<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold>;
            blackhole = a;
        }</fold>
    }</fold>

    class ArithmeticData <fold text='{...}' expand='true'>{

        public <fold text='💀' expand='false'>void</fold> addAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a += b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> andAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a &= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> divideAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a /= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> multiplyAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a *= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> orAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a |= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> shiftLeftAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 2;
            a <<= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> shiftRightAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 2;
            a >>= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> subtractAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a -= b;
            blackhole = a;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> xorAssign() <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> a = 10;
            <fold text='val' expand='false'>int</fold> b = 5;
            a ^= b;
            blackhole = a;
        }</fold>


    }</fold>
}
