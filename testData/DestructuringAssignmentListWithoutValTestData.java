package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListWithoutValTestData {
    public void enter(Data data, List<Data> list) <fold text='{...}' expand='true'>{
        Data ignored1 = list.get(0);

        Data<fold text=' (' expand='true'> </fold>first<fold text=', ' expand='true'> = list.get(0);</fold><fold text='' expand='true'>
        Data </fold>second<fold text=', ' expand='true'> = list.get(1);</fold><fold text='' expand='true'>
        Data </fold>third<fold text=', ' expand='true'> = list.get(2);</fold><fold text='' expand='true'>
        Data </fold>fourth<fold text=') ' expand='true'> </fold>= list<fold text='' expand='true'>.get(3)</fold>;

        Data ignored21 = data.<fold text='list' expand='false'>getList()</fold>.get(4);
        Data ignored22 = data.<fold text='list' expand='false'>getList()</fold>.get(5);

        Data<fold text=' (' expand='true'> </fold>getter1<fold text=', ' expand='true'> = data.getList().get(0);</fold><fold text='' expand='true'>
        Data </fold>getter2<fold text=', ' expand='true'> = data.<fold text='list' expand='false'>getList()</fold>.get(1);</fold><fold text='' expand='true'>
        Data </fold>getter3<fold text=') ' expand='true'> </fold>= data.<fold text='list' expand='false'>getList()</fold><fold text='' expand='true'>.get(2)</fold>;
        getter1 = data;

        Data<fold text=' (' expand='true'> </fold>deepGetter1<fold text=', ' expand='true'> = data.getData().getList().get(0);</fold><fold text='' expand='true'>
        Data </fold>deepGetter2<fold text=') ' expand='true'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold><fold text='' expand='true'>.get(1)</fold>;
        deepGetter2 = data;

        Data wrongParent1 = data.<fold text='list' expand='false'>getList()</fold>.get(0);
        Data wrongParent2 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='list' expand='false'>getList()</fold>.get(1);

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
