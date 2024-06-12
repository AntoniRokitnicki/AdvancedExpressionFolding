package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayWithoutValTestData {
    public void enter(Data data, Data[] array) <fold text='{...}' expand='true'>{
        Data ignored1 = array[0];

        Data<fold text=' (' expand='true'> </fold>first<fold text=', ' expand='true'> = array[0];</fold><fold text='' expand='true'>

        Data </fold>second<fold text=', ' expand='true'> = array[1];</fold><fold text='' expand='true'>
        Data </fold>third<fold text=', ' expand='true'> = array[2];</fold><fold text='' expand='true'>
        Data </fold>fourth<fold text=') ' expand='true'> </fold>= array<fold text='' expand='true'>[3]</fold>;

        Data ignored21 = data.<fold text='array' expand='false'>getArray()</fold>[4];
        Data ignored22 = data.<fold text='array' expand='false'>getArray()</fold>[5];

        Data<fold text=' (' expand='true'> </fold>getter1<fold text=', ' expand='true'> = data.<fold text='array' expand='false'>getArray()</fold>[0];</fold><fold text='' expand='true'>
        Data </fold>getter2<fold text=') ' expand='true'> </fold>= data.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;
        getter1 = data;

        Data<fold text=' (' expand='true'> </fold>deepGetter1<fold text=', ' expand='true'> = data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold>[0];</fold><fold text='' expand='true'>
        Data </fold>deepGetter2<fold text=') ' expand='true'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;
        deepGetter2 = data;
        
        Data wrongParent1 = data.<fold text='array' expand='false'>getArray()</fold>[0];
        Data wrongParent2 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold>[1];

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
