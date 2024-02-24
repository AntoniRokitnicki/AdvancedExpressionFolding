package data<fold text='' expand='false'>;</fold>

import java.util.Arrays<fold text='' expand='false'>;</fold>

public class SemicolonTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        for <fold text='' expand='false'>(<fold text='val' expand='false'></fold>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                System.out.println(arg)<fold text='' expand='false'>;</fold>
            }</fold>
        }</fold>
                <fold text='' expand='false'>Arrays.stream(</fold>args<fold text='' expand='false'>)</fold>.forEach(System.out::println)<fold text='' expand='false'>;</fold>
    }</fold>
}
