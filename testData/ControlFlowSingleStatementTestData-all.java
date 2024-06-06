<fold text='📦' expand='false'>package</fold> data<fold text='' expand='false'>;</fold>

<fold text='🚢' expand='false'>import</fold> java.util.Arrays<fold text='' expand='false'>;</fold>

public class ControlFlowSingleStatementTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(Arrays.asList(args))<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> else <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(arg)<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(<fold text='val' expand='false'></fold>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text='' expand='false'><fold text=', ' expand='false'>;</fold> i < </fold>args.length<fold text='' expand='false'><fold text=')' expand='false'>;</fold> i++</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(args[i])<fold text='' expand='false'>;</fold>
        }</fold>
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text='' expand='false'><fold text=', ' expand='false'>;</fold> i < </fold>args.length<fold text='' expand='false'><fold text=')' expand='false'>;</fold> i++</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(i)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(args[i])<fold text='' expand='false'>;</fold>
        }</fold>
        while <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold>
        while <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold>
        do <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold> while <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        do <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        break<fold text='' expand='false'>;</fold>
        }</fold> while <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='🤞' expand='false'>try</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='🤞' expand='false'>try</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='🤞' expand='false'>try</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
    }</fold>
}
