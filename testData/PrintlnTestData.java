package data;

@SuppressWarnings("unused")
class PrintlnTestData {
    static final int CONST_VALUE = 0;

    void println(String string) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"Hello"' expand='false'>"Hello"</fold>);
        <fold text='' expand='false'>System.out.</fold>println
                (123);
        <fold text='' expand='false'>System.
                out.</fold>println(<fold text='"Spacing"' expand='false'>"Spacing"</fold>);
        <fold text='' expand='false'>System.out.
                </fold>println(3.14);
        <fold text='' expand='false'>System.out.</fold>println(string);
        <fold text='' expand='false'>System.out.</fold>println(true);
        <fold text='' expand='false'>System.out.</fold>println('A');
        <fold text='' expand='false'>System.out.</fold>println(CONST_VALUE);
        <fold text='' expand='false'>System.out.</fold>println("Divided: " + ""<fold text=' + ' expand='false'> +
                </fold>"into"<fold text=' + ' expand='false'> +
                </fold>" multiple" + " " + "strings");
        <fold text='' expand='false'>System.out.</fold>println("Passed as parameter: " + string);
        <fold text='' expand='false'>System.out.</fold>println("Passed as parameter: "<fold text=' + ' expand='false'> +
</fold>this.getClass());
        <fold text='' expand='false'>System.out.</fold>println("""
                text
                block
                """);
    }</fold>

}
