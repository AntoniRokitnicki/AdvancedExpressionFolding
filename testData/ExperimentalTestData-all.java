<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.io.UnsupportedEncodingException;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> ExperimentalTestData {

    public <fold text='🏛️' expand='false'>class</fold> SneakyThrowsExample implements Runnabl<fold text='e(1-run)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        public String utf8ToString(<fold text='💾' expand='false'>byte</fold>[] bytes) <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='false'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='' expand='true'>    </fold><fold text='🔙' expand='false'>return</fold> new String(System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold>.<fold text='bytes' expand='false'>getBytes()</fold>, "UTF-8");<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold> <fold text='🎣' expand='false'><fold text='' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>UnsupportedEncodingException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='🪃' expand='false'>throw</fold> new RuntimeException(e);
            }</fold></fold>
        }</fold>
        public <fold text='💀' expand='false'>void</fold> run() <fold text='{...}' expand='true'>{
            <fold text='@SneakyThrows' expand='false'>try</fold><fold text='' expand='true'> </fold><fold text='' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='🪃' expand='false'>throw</fold> new Throwable();<fold text='' expand='true'>
            </fold><fold text='' expand='true'>}</fold></fold> <fold text='🎣' expand='false'><fold text='' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>Throwable t<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='🪃' expand='false'>throw</fold> new IllegalStateException(t);
            }</fold></fold>
        }</fold>

        <fold text='🏛️' expand='false'>class</fold> Nagative <fold text='{...}' expand='true'>{
            public String utf8ToString(<fold text='💾' expand='false'>byte</fold>[] bytes) <fold text='{...}' expand='true'>{
                <fold text='🤞' expand='false'>try</fold> <fold text='{...}' expand='true'>{
                    <fold text='🔙' expand='false'>return</fold> new String(bytes, "UTF-8");
                }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>UnsupportedEncodingException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                    <fold text='🪃' expand='false'>throw</fold> new RuntimeException(e);
                }</fold>
            }</fold>
            public <fold text='💀' expand='false'>void</fold> run() <fold text='{...}' expand='true'>{
                <fold text='🤞' expand='false'>try</fold> <fold text='{...}' expand='true'>{
                    <fold text='🪃' expand='false'>throw</fold> new Throwable();
                }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>Throwable t<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    <fold text='🪃' expand='false'>throw</fold> new RuntimeException("", t);
                }</fold>
            }</fold>
        }</fold>
    }</fold>



}
