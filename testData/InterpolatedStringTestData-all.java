<fold text='📦' expand='false'>package</fold> data;

public <fold text='🏛️' expand='false'>class</fold> InterpolatedStringTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}' expand='false'> + "</fold>!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, hello!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("${' expand='false'>(</fold>args[0]<fold text='}' expand='false'> + "</fold>, <fold text='${' expand='false'>" + </fold>args[0]<fold text='}")' expand='false'>)</fold>;
        <fold text='val' expand='false'>String</fold> name = args[0];
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println("Hello, <fold text='$' expand='false'>" + </fold>name<fold text='' expand='false'> + "</fold>!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, hello!");
        <fold text='' expand='false'>System.out.</fold>println<fold text='("$' expand='false'>(</fold>name<fold text='' expand='false'> + "</fold>, <fold text='$' expand='false'>" + </fold>name<fold text='")' expand='false'>)</fold>;
    }</fold>
}
