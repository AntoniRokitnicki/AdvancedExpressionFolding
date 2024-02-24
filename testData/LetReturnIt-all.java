package data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
class LetReturnIt {
    static String buildExpression(String someString) <fold text='{...}' expand='true'>{
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
        System.out.println<fold text='("$' expand='false'>(</fold>var5<fold text='' expand='false'> + "</fold>1");


        <fold text='val' expand='false'>String</fold> var6 = getData(someString)<fold text=' ?: return null' expand='true'>;
        if (var6 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold></fold>
        while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>LocalDate.now()<fold text=' > ' expand='false'>.isAfter(</fold>LocalDate.now()<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                if <fold text='' expand='false'>(</fold>var6 == null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    System.out.println("1");
                }</fold>
            }</fold>
            break;
        }</fold>


        <fold text='val' expand='false'>String</fold> var7 = getData(someString)<fold text='?.let { return it }' expand='true'>;
        if (var7 != null) <fold text='{...}' expand='true'>{
            return var7;
        }</fold></fold>
        new Thread(<fold text='run() → { ' expand='false'>new Runnable() {
            @Override
            public void run() {
                </fold>System.out.println<fold text='("$' expand='false'>(</fold>var7<fold text='' expand='false'> + "</fold>1");<fold text=' }' expand='false'>
            }
        }</fold>);
        return null;
    }</fold>

    private static String getData(String someString) <fold text='{...}' expand='true'>{
        try <fold text='{...}' expand='true'>{
            return ClassLoader.getSystemResource("a").toString();
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            return null;
        }</fold>
    }</fold>


}