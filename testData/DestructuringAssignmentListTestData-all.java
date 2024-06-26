<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.util.List;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> DestructuringAssignmentListTestData {
    public <fold text='💀' expand='false'>void</fold> enter(Data data, List<Data> list) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> ignored1 = list<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>);

        <fold text='val' expand='false'><fold text='val (' expand='true'>Data</fold> </fold>first<fold text=', ' expand='true'> = list<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>);</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>second<fold text=', ' expand='true'> = list<fold text='[' expand='false'>.get(</fold>1<fold text=']' expand='false'>)</fold>;</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>third<fold text=', ' expand='true'> = list<fold text='[' expand='false'>.get(</fold>2<fold text=']' expand='false'>)</fold>;</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>fourth<fold text=') ' expand='true'> </fold>= list<fold text='[' expand='false'><fold text='' expand='true'>.get(</fold>3<fold text=']' expand='false'>)</fold></fold>;

        <fold text='val' expand='false'>Data</fold> ignored21 = data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>4<fold text=']' expand='false'>)</fold>;
        <fold text='val' expand='false'>Data</fold> ignored22 = data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>5<fold text=']' expand='false'>)</fold>;

        <fold text='var' expand='false'><fold text='var (' expand='true'>Data</fold> </fold>getter1<fold text=', ' expand='true'> = data.<fold text='list' expand='false'>getList()</fold><fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>);<fold text='' expand='true'></fold>
        <fold text='val' expand='false'>Data</fold> </fold>getter2<fold text=', ' expand='true'> = data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>1<fold text=']' expand='false'>)</fold>;</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>getter3<fold text=') ' expand='true'> </fold>= data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'><fold text='' expand='true'>.get(</fold>2<fold text=']' expand='false'>)</fold></fold>;
        getter1 = data;

        <fold text='val' expand='false'><fold text='var (' expand='true'>Data</fold> </fold>deepGetter1<fold text=', ' expand='true'> = data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold><fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>);</fold><fold text='' expand='true'>
        <fold text='var' expand='false'>Data</fold> </fold>deepGetter2<fold text=') ' expand='true'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'><fold text='' expand='true'>.get(</fold>1<fold text=']' expand='false'>)</fold></fold>;
        deepGetter2 = data;

        <fold text='val' expand='false'>Data</fold> wrongParent1 = data.<fold text='list' expand='false'>getList()</fold><fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>);
        <fold text='val' expand='false'>Data</fold> wrongParent2 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>1<fold text=']' expand='false'>)</fold>;

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{
        public Data getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data[] getArray()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<Data> getList()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='💀' expand='false'>void</fold> blackhole(Data... datas) {}

}
