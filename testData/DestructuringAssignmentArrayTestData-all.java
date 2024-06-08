<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.util.List;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> DestructuringAssignmentArrayTestData {
    public <fold text='💀' expand='false'>void</fold> enter(Data data, Data[] array) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> ignored1 = array[0];

        <fold text='val (' expand='true'>Data </fold>first<fold text=', ' expand='true'> = array[0];</fold><fold text='' expand='true'>

        <fold text='val' expand='false'>Data</fold> </fold>second<fold text=', ' expand='true'> = array[1];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>third<fold text=', ' expand='true'> = array[2];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>fourth<fold text=') ' expand='true'> </fold>= array<fold text='' expand='true'>[3]</fold>;

        <fold text='val' expand='false'>Data</fold> ignored21 = data.<fold text='array' expand='false'>getArray()</fold>[4];
        <fold text='val' expand='false'>Data</fold> ignored22 = data.<fold text='array' expand='false'>getArray()</fold>[5];

        <fold text='var (' expand='true'>Data </fold>getter1<fold text=', ' expand='true'> = data.getArray()[0];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>getter2<fold text=', ' expand='true'> = data.<fold text='array' expand='false'>getArray()</fold>[1];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>getter3<fold text=') ' expand='true'> </fold>= data.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[2]</fold>;
        getter1 = data;

        <fold text='var (' expand='true'>Data </fold>deepGetter1<fold text=', ' expand='true'> = data.getData().getArray()[0];</fold><fold text='' expand='true'>
        <fold text='var' expand='false'>Data</fold> </fold>deepGetter2<fold text=') ' expand='true'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;
        deepGetter2 = data;
        
        <fold text='val' expand='false'>Data</fold> wrongParent1 = data.<fold text='array' expand='false'>getArray()</fold>[0];
        <fold text='val' expand='false'>Data</fold> wrongParent2 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold>[1];

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{
        public Data getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public Data[] getArray()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public List<Data> getList()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='💀' expand='false'>void</fold> blackhole(Data... datas) {}

}
