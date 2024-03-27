package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListTestData {
    public void enter(Data data, List<Data> list) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> ignored1 = list.get(0);

        <fold text='val (' expand='false'>Data </fold>first<fold text=', ' expand='false'> = list.get(0);</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>second<fold text=', ' expand='true'> = list<fold text='[' expand='false'>.get(</fold>1<fold text=']' expand='false'>)</fold>;<fold text='' expand='true'></fold>
        <fold text='val' expand='false'>Data</fold> </fold>third<fold text=', ' expand='true'> = list<fold text='[' expand='false'>.get(</fold>2<fold text=']' expand='false'>)</fold>;</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>fourth<fold text=') ' expand='true'> </fold>= list<fold text='[' expand='false'><fold text='' expand='true'>.get(</fold>3<fold text=']' expand='false'>)</fold></fold>;

        <fold text='val' expand='false'>Data</fold> ignored21 = data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>4<fold text=']' expand='false'>)</fold>;
        <fold text='val' expand='false'>Data</fold> ignored22 = data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>5<fold text=']' expand='false'>)</fold>;

        <fold text='var (' expand='false'>Data </fold>getter1<fold text=', ' expand='false'> = data.getList().get(0);</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>getter2<fold text=', ' expand='true'> = data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>1<fold text=']' expand='false'>)</fold>;<fold text='' expand='true'></fold>
        <fold text='val' expand='false'>Data</fold> </fold>getter3<fold text=') ' expand='true'> </fold>= data.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'><fold text='' expand='true'>.get(</fold>2<fold text=']' expand='false'>)</fold></fold>;
        getter1 = data;

        <fold text='var (' expand='false'>Data </fold>deepGetter1<fold text=', ' expand='false'> = data.getData().getList().get(0);</fold><fold text='' expand='true'>
        <fold text='var' expand='false'>Data</fold> </fold>deepGetter2<fold text=') ' expand='true'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'><fold text='' expand='true'>.get(</fold>1<fold text=']' expand='false'>)</fold></fold>;
        deepGetter2 = data;

        <fold text='val' expand='false'>Data</fold> wrongParent1 = data.<fold text='list' expand='false'>getList()</fold>.get(0);
        <fold text='val' expand='false'>Data</fold> wrongParent2 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold><fold text='[' expand='false'>.get(</fold>1<fold text=']' expand='false'>)</fold>;

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }</fold>

    static class Data <fold text='{...}' expand='true'>{
        public Data getData()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>

        public Data[] getArray()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>

        public List<Data> getList()<fold text=' { ' expand='false'> {
            </fold>return null;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    void blackhole(Data... datas) {}

}
