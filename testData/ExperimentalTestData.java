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
        public String utf8ToString(byte[] bytes) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='' expand='true'>    </fold>return new String(bytes, "UTF-8");<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold> <fold text='' expand='true'>catch (UnsupportedEncodingException e) <fold text='{...}' expand='true'>{
                throw new RuntimeException(e);
            }</fold></fold>
        }</fold>
        public void run() <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='true'>try<fold text='' expand='true'></fold> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
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
        }</fold>
    }</fold>



}
