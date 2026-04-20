package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        System.out.println("Hello, <fold text='${' expand='false'>" + </fold><fold text='args[0]}"' expand='false'>args[0]</fold>);
        System.out.println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}' expand='false'> + "</fold>!");
        System.out.println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!");
        System.out.println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, <fold text='${' expand='false'>" + </fold><fold text='args[0]}"' expand='false'>args[0]</fold>);
        String name = args[0];
        System.out.println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        System.out.println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + "</fold>!");
        System.out.println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, hello!");
        System.out.println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        System.out.println(<fold text='"' expand='false'>'"'</fold><fold text='\"$' expand='false'> + </fold>name<fold text='' expand='false'> + "</fold> says hi");
        System.out.println("Hi <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + </fold><fold text='\"' expand='false'>'"'</fold>);
        System.out.println("Unicode: " + <fold text=''\u0041'}"' expand='false'>'\u0041');
        System.out.println("Next: " + <fold text='(char)('A' + 1)}"' expand='false'>(char)('A' + 1));
        System.out.println("Length: <fold text='${' expand='false'>" + </fold><fold text='args.length}"' expand='false'>args.length</fold>);
        System.out.println("Sum: <fold text='${' expand='false'>" + </fold><fold text='(2 + 3)}"' expand='false'>(2 + 3)</fold>);
        System.out.println("Upper: <fold text='${' expand='false'>" + </fold><fold text='name.toUpperCase()}"' expand='false'>name.toUpperCase()</fold>);
    }</fold>
}
