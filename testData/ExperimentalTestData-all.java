package data;

import <fold text='...' expand='false'>java.io.UnsupportedEncodingException;
import java.util.function.Function;</fold>

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
@SuppressWarnings("ALL")
public class ExperimentalTestData {

    public class SneakyThrowsExample implements Runnabl<fold text='e(1-run)' expand='true'>e</fold> <fold text='{...}' expand='true'>{

        public String utf8ToStringMultiline(byte[] bytes) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>byte[]</fold> bytez = System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold>.<fold text='bytes' expand='false'>getBytes()</fold>;
                return new String(bytez, "UTF-8");
            }</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>UnsupportedEncodingException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                throw new RuntimeException(e);
            }</fold></fold>
        }</fold>

        public int parseInteger(String value) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows(IllegalArgumentException)' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='' expand='true'>    </fold>return Integer.parseInt(value);<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>NumberFormatException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                throw new IllegalArgumentException(e);
            }</fold></fold>
        }</fold>

        public long parseLong(String value) <fold text='{...}' expand='true'>{
            long longValue;
            <fold text='@SneakyThrows(IllegalArgumentException)' expand='true'>try</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Function<String, Long></fold> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>NumberFormatException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                throw new IllegalArgumentException(e);
            }</fold></fold>
            <fold text='' expand='false'>System.out.</fold>println("longValue = <fold text='$' expand='false'>" + </fold>longValue<fold text='")' expand='false'>)</fold>;
            return longValue;
        }</fold>

        public void runMultiline() <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>var</fold> throwable = new Throwable();
                throw throwable;
            }</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>Throwable t<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                throw new IllegalStateException(t);
            }</fold></fold>
        }</fold>

        public String utf8ToString(byte[] bytes) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='' expand='true'>    </fold>return new String(System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold>.<fold text='bytes' expand='false'>getBytes()</fold>, "UTF-8");<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>UnsupportedEncodingException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                throw new RuntimeException(e);
            }</fold></fold>
        }</fold>
        public void run() <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            throw new Throwable();<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold><fold text='' expand='true'> </fold><fold text='' expand='true'>catch <fold text='' expand='false'>(</fold>Throwable t<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                throw new IllegalStateException(t);
            }</fold></fold>
        }</fold>

        class Nagative <fold text='{...}' expand='true'>{
            public String utf8ToString(byte[] bytes) <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    return new String(bytes, "UTF-8");
                }</fold> catch <fold text='' expand='false'>(</fold>UnsupportedEncodingException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }</fold>
            }</fold>
            public void run() <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    throw new Throwable();
                }</fold> catch <fold text='' expand='false'>(</fold>Throwable t<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    throw new RuntimeException("", t);
                }</fold>
            }</fold>

            public String utf8ToStringMultiline(byte[] bytes) <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    <fold text='val' expand='false'>String</fold> charsetName = "UTF-8";
                    return new String(bytes, charsetName);
                }</fold> catch <fold text='' expand='false'>(</fold>UnsupportedEncodingException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }</fold>
            }</fold>
            public void runMultiline() <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    <fold text='val' expand='false'>Throwable</fold> throwable = new Throwable();
                    throw throwable;
                }</fold> catch <fold text='' expand='false'>(</fold>Throwable t<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    throw new RuntimeException("", t);
                }</fold>
            }</fold>
        }</fold>
    }</fold>


    static class NamelessAccessorExample <fold text='{...}' expand='true'>{

        private String state;

        void set(String value)<fold text=' { ' expand='false'> {
            </fold>this.state = value;<fold text=' }' expand='false'>
        }</fold>

        String get()<fold text=' { ' expand='false'> {
            </fold>return state;<fold text=' }' expand='false'>
        }</fold>

        void demo() <fold text='{...}' expand='true'>{
            NamelessAccessorExample example = new NamelessAccessorExample();
            example<fold text='!! = ' expand='false'>.set(</fold>"ok"<fold text='' expand='false'>)</fold>;
            String current = example<fold text='!!' expand='false'>.get()</fold>;
            System.out.println(example<fold text='!!' expand='false'>.get()</fold>.<fold text='empty' expand='false'>isEmpty()</fold>);
            example<fold text='!! = ' expand='false'>.set(</fold>example<fold text='!!' expand='false'>.get()</fold><fold text='' expand='false'>)</fold>;
            String duplicate = example<fold text='!!' expand='false'>.get()</fold> + example<fold text='!!' expand='false'>.get()</fold>;
        }</fold>
    }</fold>

}
