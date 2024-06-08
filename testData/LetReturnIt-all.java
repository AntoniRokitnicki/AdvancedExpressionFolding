<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.time.LocalDate;

@SuppressWarnings("ALL")
<fold text='🏛️' expand='false'>class</fold> LetReturnIt {
    <fold text='⚡' expand='false'>static</fold> String buildExpression(String someString) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'><fold text='' expand='true'>String</fold> var1 = </fold>getData(someString)<fold text='?.let { return it }' expand='true'>;
        if (var1 != null) <fold text='{...}' expand='true'>{
            return var1;
        }</fold></fold>
        <fold text='val' expand='false'><fold text='' expand='true'>String</fold> var2 = </fold>getData(someString)<fold text=' ?: return null' expand='true'>;
        if (var2 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold></fold>
        <fold text='val' expand='false'>String</fold> var4 = getData(someString)<fold text='?.let { return it }' expand='true'>;
        if (var4 != null) <fold text='{...}' expand='true'>{
            return var4;
        }</fold></fold>
        var4<fold text='' expand='false'>.toString()</fold>;
        <fold text='val' expand='false'>String</fold> var5 = getData(someString)<fold text=' ?: return null' expand='true'>;
        if (var5 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold></fold>
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>var5<fold text='' expand='false'> + "</fold>1");


        <fold text='val' expand='false'>String</fold> var6 = getData(someString)<fold text=' ?: return null' expand='true'>;
        if (var6 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold></fold>
        <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>LocalDate.now()<fold text=' > ' expand='false'>.isAfter(</fold>LocalDate.now()<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>var6 == <fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    <fold text='' expand='false'>System.out.</fold>println(<fold text='"1"' expand='false'>"1"</fold>);
                }</fold>
            }</fold>
            <fold text='✋' expand='false'>break</fold>;
        }</fold>


        <fold text='val' expand='false'>String</fold> var7 = getData(someString)<fold text='?.let { return it }' expand='true'>;
        if (var7 != null) <fold text='{...}' expand='true'>{
            return var7;
        }</fold></fold>
        <fold text='✨' expand='false'>new</fold> Thread(<fold text='✨' expand='false'><fold text='run() → { ' expand='false'>new</fold> Runnable() {
            <fold text='' expand='true'>@Override</fold>
            public void run() {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>var7<fold text='' expand='false'> + "</fold>1")<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}
        }</fold>);
        <fold text='🔙' expand='false'>return</fold> <fold text='🕳️' expand='false'>null</fold>;
    }</fold>

    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String getData(String someString) <fold text='{...}' expand='true'>{
        <fold text='🤞' expand='false'>try</fold> <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> ClassLoader.getSystemResource("a").toString();
        }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> <fold text='🕳️' expand='false'>null</fold>;
        }</fold>
    }</fold>


}