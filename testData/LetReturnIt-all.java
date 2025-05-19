package data;

import java.time.LocalDate;

<fold text='' expand='true'>@SuppressWarnings("ALL")</fold>
class LetReturnIt {
    static String buildExpression(String someString) <fold text='{...}' expand='true'>{
        String var1 = getData(someString);
        if (var1 != null) <fold text='{...}' expand='true'>{
            return var1;
        }</fold>
        String var2 = getData(someString);
        if (var2 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold>
        String var4 = getData(someString);
        if (var4 != null) <fold text='{...}' expand='true'>{
            return var4;
        }</fold>
        var4.toString();
        String var5 = getData(someString);
        if (var5 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold>
        System.out.println(var5 + "1");


        String var6 = getData(someString);
        if (var6 == null) <fold text='{...}' expand='true'>{
            return null;
        }</fold>
        while (true) <fold text='{...}' expand='true'>{
            if (LocalDate.now().isAfter(LocalDate.now())) <fold text='{...}' expand='true'>{
                if (var6 == null) <fold text='{...}' expand='true'>{
                    System.out.println("1");
                }</fold>
            }</fold>
            break;
        }</fold>


        String var7 = getData(someString);
        if (var7 != null) <fold text='{...}' expand='true'>{
            return var7;
        }</fold>
        new Thread(<fold text='run() → { ' expand='false'>new Runnable() {
            @Override
            public void run() {
                </fold>System.out.println(var7 + "1");<fold text=' }' expand='false'>
            }
        }</fold>);
        return null;
    }</fold>

    private static String getData(String someString) <fold text='{...}' expand='true'>{
        try <fold text='{...}' expand='true'>{
            return ClassLoader.getSystemResource("a").toString();
        }</fold> catch (Exception e) <fold text='{...}' expand='true'>{
            return null;
        }</fold>
    }</fold>


}