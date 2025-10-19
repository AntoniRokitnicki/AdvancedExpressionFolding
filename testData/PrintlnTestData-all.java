package data;

@SuppressWarnings("unused")
class PrintlnTestData {
    <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>int</fold> CONST_VALUE = 0;

    void println(String string) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"Hello"' expand='true'>"Hello"</fold>);
        <fold text='' expand='false'>System.out.</fold>println
                (123);
        <fold text='' expand='false'>System.
                out.</fold>println(<fold text='"Spacing"' expand='true'>"Spacing"</fold>);
        <fold text='' expand='false'>System.out.
                </fold>println(3.14);
        <fold text='' expand='false'>System.out.</fold>println(string);
        <fold text='' expand='false'>System.out.</fold>println(true);
        <fold text='' expand='false'>System.out.</fold>println('A');
        <fold text='' expand='false'>System.out.</fold>println(CONST_VALUE);
        <fold text='' expand='false'>System.out.</fold>println("Divided: <fold text='' expand='false'>" + "</fold><fold text='' expand='false'>" +
                "</fold>into<fold text='' expand='false'>" +
                "</fold> multiple<fold text='' expand='false'>" + "</fold> <fold text='' expand='false'>" + "</fold>strings");
        <fold text='' expand='false'>System.out.</fold>println("Passed as parameter: <fold text='$' expand='false'>" + </fold>string<fold text='")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Passed as parameter: <fold text='${' expand='false'>" +
</fold>this.<fold text='class' expand='false'>getClass()</fold><fold text='}")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"text
block
"' expand='false'>"""
                text
                block
                """</fold>);
    }</fold>

}
