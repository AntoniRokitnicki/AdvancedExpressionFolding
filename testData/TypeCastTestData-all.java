<fold text='📦' expand='false'>package</fold> data;

public <fold text='🏛️' expand='false'>class</fold> TypeCastTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>TypeCastTestData</fold> t = new TypeCastTestData();
        if <fold text='' expand='false'>(</fold>t.<fold text='object' expand='false'>getObject()</fold> <fold text='is' expand='false'>instanceof</fold> TypeCastTestData &&
                <fold text='' expand='false'>((TypeCastTestData) </fold>t.<fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).<fold text='object' expand='false'></fold>getObject()</fold> <fold text='is' expand='false'>instanceof</fold> TypeCastTestData<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='' expand='false'>System.out.</fold>println(<fold text='' expand='false'>((TypeCastTestData) </fold><fold text='' expand='false'>((TypeCastTestData) </fold>t.<fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold>);
        handle(<fold text='' expand='false'>((TypeCastTestData) </fold><fold text='' expand='false'>((TypeCastTestData) </fold>t.<fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold><fold text='' expand='false'>)</fold>);
        }</fold>
    }</fold>

    <fold text='🚫' expand='false'>private</fold> Object getObject()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this<fold text='' expand='true'></fold>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> handle(TypeCastTestData t)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='false'>System.out.</fold>println(t)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>
}
