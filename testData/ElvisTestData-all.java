<fold text='📦' expand='false'>package</fold> data;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> ElvisTestData {
    <fold text='🚫' expand='false'>private</fold> ElvisTestData e = create();

    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>ElvisTestData</fold> e = create();
        <fold text='' expand='false'>System.out.</fold>println(<fold text='' expand='false'>e != null ? </fold>e<fold text=' ?: ' expand='false'> : </fold>"");
        <fold text='' expand='false'>System.out.</fold>println(<fold text='' expand='false'>e != null ? </fold>e<fold text='?.' expand='false'>.</fold>sayHello()<fold text=' ?: ' expand='false'> : </fold>"");
        <fold text='' expand='false'>System.out.</fold>println(<fold text='e?.get != null' expand='false'>e != <fold text='🕳️' expand='false'>null</fold> && e.get() != <fold text='🕳️' expand='false'>null</fold></fold> ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        <fold text='' expand='false'>System.out.</fold>println(<fold text='e?.get != null' expand='false'>e != <fold text='🕳️' expand='false'>null</fold> && e.get() != <fold text='🕳️' expand='false'>null</fold></fold> ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        if (e != null) <fold text='{...}' expand='true'>{
                e<fold text='?.' expand='false'>.</fold>get().sayHello();<fold text='' expand='false'>
        }</fold></fold>
        if (e.get() != null) <fold text='{...}' expand='true'>{
                e.get()<fold text='?.' expand='false'>.</fold>sayHello();<fold text='' expand='false'>
        }</fold></fold>
        if <fold text='' expand='false'>(</fold><fold text='e?.get != null' expand='false'>e != <fold text='🕳️' expand='false'>null</fold> && e.get() != <fold text='🕳️' expand='false'>null</fold></fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                e.get().sayHello();
        }</fold>
    }</fold>

    <fold text='🚫' expand='false'>private</fold> String sayHello()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"Hello"<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> ElvisTestData create() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
        </fold>if <fold text='' expand='false'>(</fold>Math.random() > 0.5<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
        <fold text='🔙' expand='false'>return</fold> new ElvisTestData();
        }</fold> <fold text='🔄' expand='false'>else</fold> <fold text='{...}' expand='true'>{
        <fold text='🔙' expand='false'>return</fold> <fold text='🕳️' expand='false'>null</fold>;
        }</fold><fold text=' ' expand='true'>
    </fold>}</fold>

    <fold text='🚫' expand='false'>private</fold> ElvisTestData get()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>e<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>
}
