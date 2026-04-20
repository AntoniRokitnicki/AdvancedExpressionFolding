package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='${' expand='false'>" + </fold><fold text='args[0]}"' expand='false'>args[0]</fold>);
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}' expand='false'> + "</fold>!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, <fold text='${' expand='false'>" + </fold><fold text='args[0]}"' expand='false'>args[0]</fold>);
        <fold text='val' expand='false'>String</fold> name = args[0];
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + "</fold>!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, hello!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"' expand='false'>'"'</fold><fold text='\"$' expand='false'> + </fold>name<fold text='' expand='false'> + "</fold> says hi");
        <fold text='' expand='false'>System.out.</fold>println("Hi <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + </fold><fold text='\"' expand='false'>'"'</fold>);
        System.out.println("Unicode: " + <fold text=''\u0041'}"' expand='false'>'\u0041');
        System.out.println("Next: " + <fold text='(char)('A' + 1)}"' expand='false'><fold text='' expand='false'>(char)(</fold>'A' + 1<fold text='' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println("Length: <fold text='${' expand='false'>" + </fold><fold text='args.length}"' expand='false'>args.length</fold>);
        <fold text='' expand='false'>System.out.</fold>println("Sum: <fold text='${' expand='false'>" + (</fold><fold text='2 + 3}"' expand='false'>2 + 3</fold>));
        <fold text='' expand='false'>System.out.</fold>println("Upper: <fold text='${' expand='false'>" + </fold><fold text='name.toUpperCase()}"' expand='false'>name.toUpperCase()</fold>);
    }</fold>
}
