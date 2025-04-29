package data;

import java.io.UnsupportedEncodingException;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
@SuppressWarnings("ALL")
public class ExperimentalTestData {

    public class SneakyThrowsExample implements Runnable <fold text='{...}' expand='true'>{

        public String utf8ToStringMultiline(byte[] bytes) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold> <fold text='{...}' expand='true'>{
                byte[] bytez = System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold>.getBytes();
                return new String(bytez, "UTF-8");
            }</fold> <fold text='' expand='true'>catch (UnsupportedEncodingException e) <fold text='{...}' expand='true'>{
                throw new RuntimeException(e);
            }</fold></fold>
        }</fold>
        public void runMultiline() <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold> <fold text='{...}' expand='true'>{
                var throwable = new Throwable();
                throw throwable;
            }</fold> <fold text='' expand='true'>catch (Throwable t) <fold text='{...}' expand='true'>{
                throw new IllegalStateException(t);
            }</fold></fold>
        }</fold>

        public String utf8ToString(byte[] bytes) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='' expand='true'>    </fold>return new String(System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold>.getBytes(), "UTF-8");<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold> <fold text='' expand='true'>catch (UnsupportedEncodingException e) <fold text='{...}' expand='true'>{
                throw new RuntimeException(e);
            }</fold></fold>
        }</fold>
        public void run() <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            throw new Throwable();<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold> <fold text='' expand='true'>catch (Throwable t) <fold text='{...}' expand='true'>{
                throw new IllegalStateException(t);
            }</fold></fold>
        }</fold>

        class Nagative <fold text='{...}' expand='true'>{
            public String utf8ToString(byte[] bytes) <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    return new String(bytes, "UTF-8");
                }</fold> catch (UnsupportedEncodingException e) <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }</fold>
            }</fold>
            public void run() <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    throw new Throwable();
                }</fold> catch (Throwable t) <fold text='{...}' expand='true'>{
                    throw new RuntimeException("", t);
                }</fold>
            }</fold>

            public String utf8ToStringMultiline(byte[] bytes) <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    String charsetName = "UTF-8";
                    return new String(bytes, charsetName);
                }</fold> catch (UnsupportedEncodingException e) <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }</fold>
            }</fold>
            public void runMultiline() <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    Throwable throwable = new Throwable();
                    throw throwable;
                }</fold> catch (Throwable t) <fold text='{...}' expand='true'>{
                    throw new RuntimeException("", t);
                }</fold>
            }</fold>
        }</fold>
    }</fold>

}
