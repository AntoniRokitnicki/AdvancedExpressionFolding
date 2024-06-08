<fold text='📦' expand='false'>package</fold> data<fold text='' expand='false'>;</fold>

<fold text='🚢' expand='false'>import</fold> java.util.Arrays<fold text='' expand='false'>;</fold>

public <fold text='🏛️' expand='false'>class</fold> ControlFlowSingleStatementTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(Arrays.asList(args))<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> <fold text='🔄' expand='false'>else</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length == 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> <fold text='🔄' expand='false'>else</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>args.length > 0<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold> <fold text='🔄' expand='false'>else</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='🔁' expand='false'>for</fold> <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(arg)<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='🔁' expand='false'>for</fold> <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text='' expand='false'><fold text=', ' expand='false'>;</fold> i < </fold>args.length<fold text='' expand='false'><fold text=')' expand='false'>;</fold> i++</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(args[i])<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='🔁' expand='false'>for</fold> <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> i<fold text=' : [' expand='false'> = </fold>0<fold text='' expand='false'><fold text=', ' expand='false'>;</fold> i < </fold>args.length<fold text='' expand='false'><fold text=')' expand='false'>;</fold> i++</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(i)<fold text='' expand='false'>;</fold>
        <fold text='' expand='false'>System.out.</fold>println(args[i])<fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        <fold text='✋' expand='false'>break</fold><fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='✋' expand='false'>break</fold><fold text='' expand='false'>;</fold>
        }</fold>
        <fold text='▶️' expand='false'>do</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
        <fold text='✋' expand='false'>break</fold><fold text='' expand='false'>;</fold>
        }</fold> <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='▶️' expand='false'>do</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
                <fold text='' expand='false'>System.out.</fold>println(<fold text='"..."' expand='false'>"..."</fold>)<fold text='' expand='false'>;</fold>
        <fold text='✋' expand='false'>break</fold><fold text='' expand='false'>;</fold>
        }</fold> <fold text='♾️' expand='false'>while</fold> <fold text='' expand='false'>(</fold><fold text='✅' expand='false'>true</fold><fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
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
