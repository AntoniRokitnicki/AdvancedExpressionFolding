package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}' expand='false'> + "</fold>!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}")' expand='false'>)</fold>;
        <fold text='val' expand='false'>String</fold> name = args[0];
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + "</fold>!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, hello!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(<fold text='\"' expand='false'>'"'</fold><fold text='$' expand='false'> + </fold>name<fold text='' expand='false'> + "</fold> says hi");
        <fold text='' expand='false'>System.out.</fold>println("Hi <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + </fold><fold text='\"' expand='false'>'"'</fold>);
        <fold text='' expand='false'>System.out.</fold>println("Unicode: <fold text='${' expand='false'>" + </fold>'\u0041'<fold text='}")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Next: <fold text='${' expand='false'>" + </fold><fold text='' expand='false'>(char)(</fold>'A' + 1<fold text='' expand='false'>)</fold><fold text='}")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Length: <fold text='${' expand='false'>" + </fold>args.length<fold text='}")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Sum: <fold text='${' expand='false'>" + (</fold>2 + 3<fold text='}")' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println("Upper: <fold text='${' expand='false'>" + </fold>name.toUpperCase()<fold text='}")' expand='false'>)</fold>;
    }</fold>
}
