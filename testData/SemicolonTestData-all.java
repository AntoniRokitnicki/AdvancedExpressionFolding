<fold text='📦' expand='false'>package</fold> data<fold text='' expand='false'>;</fold>

<fold text='🚢' expand='false'>import</fold> java.util.Arrays<fold text='' expand='false'>;</fold>

public class SemicolonTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(arg)<fold text='' expand='false'>;</fold>
            }</fold>
        }</fold>
                <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold>.forEach(System.out::println)<fold text='' expand='false'>;</fold>
    }</fold>
}
