package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentTestData {
    public void enter(Data data, Data[] array) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Data</fold> ignored1 = array[0];

        <fold text='val (' expand='false'>Data </fold>first<fold text=', ' expand='false'> = array[0];</fold><fold text='' expand='true'>

        <fold text='val' expand='false'>Data</fold> </fold>second<fold text=', ' expand='true'> = array[1];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>third<fold text=', ' expand='true'> = array[2];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>fourth<fold text=') ' expand='true'> </fold>= array<fold text='' expand='true'>[3]</fold>;

        <fold text='val' expand='false'>Data</fold> ignored21 = data.<fold text='array' expand='false'>getArray()</fold>[4];
        <fold text='val' expand='false'>Data</fold> ignored22 = data.<fold text='array' expand='false'>getArray()</fold>[5];

        <fold text='var (' expand='false'>Data </fold>getter1<fold text=', ' expand='false'> = data.getArray()[0];</fold><fold text='' expand='true'>
        <fold text='val' expand='false'>Data</fold> </fold>getter2<fold text=') ' expand='true'> </fold>= data.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;
        getter1 = data;

        <fold text='var (' expand='false'>Data </fold>deepGetter1<fold text=', ' expand='false'> = data.getData().getArray()[0];</fold><fold text='' expand='true'>
        <fold text='var' expand='false'>Data</fold> </fold>deepGetter2<fold text=') ' expand='true'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;
        deepGetter2 = data;
        
        <fold text='val' expand='false'>Data</fold> wrongParent1 = data.<fold text='array' expand='false'>getArray()</fold>[0];
        <fold text='val' expand='false'>Data</fold> wrongParent2 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold>[1];

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
