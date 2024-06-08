<fold text='📦' expand='false'>package</fold> data;

<fold text='@Getter @Setter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> GetterSetterTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>GetterSetterTestData</fold> d = <fold text='✨' expand='false'>new</fold> GetterSetterTestData();
        d.<fold text='parent = ' expand='false'>setParent(</fold>d<fold text='' expand='false'>)</fold>;
        d.<fold text='name = ' expand='false'>setName(</fold>"Hello"<fold text='' expand='false'>)</fold>;
        d.<fold text='parent' expand='false'>getParent()</fold>.<fold text='name = ' expand='false'>setName(</fold>"Pum!"<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(d.<fold text='parent' expand='false'>getParent()</fold>.<fold text='name' expand='false'>getName()</fold>);
    }</fold>

    <fold text='🚫' expand='false'>private</fold> GetterSetterTestData parent;
    <fold text='🚫' expand='false'>private</fold> String name;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private void setParent(GetterSetterTestData parent)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.parent = <fold text='<<' expand='false'>parent</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>private GetterSetterTestData getParent()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>parent<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>private String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>name<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>private void setName(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.name = <fold text='<<' expand='false'>name</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>
}
