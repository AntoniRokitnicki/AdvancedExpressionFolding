package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        System.out.println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}")' expand='false'>)</fold>;
        System.out.println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}' expand='false'> + "</fold>!");
        System.out.println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!");
        System.out.println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}")' expand='false'>)</fold>;
        String name = args[0];
        System.out.println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        System.out.println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + "</fold>!");
        System.out.println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, hello!");
        System.out.println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        System.out.println(<fold text='"' expand='false'>'"'</fold><fold text='\"$' expand='false'> + </fold>name<fold text='' expand='false'> + "</fold> says hi");
        System.out.println("Hi <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + </fold><fold text='\"' expand='false'>'"'</fold>);
        System.out.println("Unicode: <fold text='${' expand='false'>" + </fold>'\u0041'<fold text='}")' expand='false'>)</fold>;
        System.out.println("Next: <fold text='${' expand='false'>" + </fold>(char)('A' + 1)<fold text='}")' expand='false'>)</fold>;
        System.out.println("Length: <fold text='${' expand='false'>" + </fold>args.length<fold text='}")' expand='false'>)</fold>;
        System.out.println("Sum: <fold text='${' expand='false'>" + </fold>(2 + 3)<fold text='}")' expand='false'>)</fold>;
        System.out.println("Upper: <fold text='${' expand='false'>" + </fold>name.toUpperCase()<fold text='}")' expand='false'>)</fold>;
    }</fold>
}
