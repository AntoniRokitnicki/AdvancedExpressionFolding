package data<fold text='' expand='false'>;</fold>

public class ControlFlowMultiStatementTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'><fold text='' expand='false'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"Success"' expand='true'>"Success"</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"Terminating"' expand='true'>"Terminating"</fold>)<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"Terminating"' expand='true'>"Terminating"</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(arg)<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='var' expand='false'>int</fold> i = 0<fold text='' expand='false'>;</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(i++)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(arg)<fold text='' expand='false'>;</fold>
        }</fold>
        while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold>
        while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold>
        try <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                e.printStackTrace()<fold text='' expand='false'>;</fold>
        }</fold>
        try <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        e.printStackTrace()<fold text='' expand='false'>;</fold>
        }</fold>
        do <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='true'>"..."</fold>)<fold text='' expand='false'>;</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold> while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        do <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold> while <fold text='' expand='false'>(</fold>true<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
    }</fold>
}
