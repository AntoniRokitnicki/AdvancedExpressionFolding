package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentTestData {
    public void enter(Data data, Data[] array) <fold text='{...}' expand='true'>{
        System.out.println();
        <fold text='val' expand='false'><fold text='def (' expand='false'>Data</fold> </fold>a1<fold text=', ' expand='false'> = array[0];</fold><fold text='' expand='false'>
        <fold text='val' expand='false'>Data</fold> </fold>a2<fold text=') ' expand='false'> </fold>= array<fold text='' expand='true'>[1]</fold>;
        <fold text='val' expand='false'>Data</fold> a4 = data.<fold text='array' expand='false'>getArray()</fold>[4];
        <fold text='val' expand='false'>Data</fold> a5 = data.<fold text='array' expand='false'>getArray()</fold>[5];

        <fold text='val' expand='false'><fold text='def (' expand='false'>Data</fold> </fold>a6<fold text=', ' expand='false'> = data.<fold text='array' expand='false'>getArray()</fold>[0];</fold><fold text='' expand='false'>
        <fold text='val' expand='false'>Data</fold> </fold>a7<fold text=') ' expand='false'> </fold>= data.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;

        <fold text='val' expand='false'><fold text='def (' expand='false'>Data</fold> </fold>a8<fold text=', ' expand='false'> = data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold>[0];</fold><fold text='' expand='false'>
        <fold text='val' expand='false'>Data</fold> </fold>a9<fold text=') ' expand='false'> </fold>= data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold><fold text='' expand='true'>[1]</fold>;

        // wrong "parent"
        <fold text='val' expand='false'>Data</fold> a10 = data.<fold text='array' expand='false'>getArray()</fold>[0];
        <fold text='val' expand='false'>Data</fold> a11 = data.<fold text='data' expand='false'>getData()</fold>.<fold text='array' expand='false'>getArray()</fold>[1];

        blackhole(a1, a2, a4, a5, a6, a7, a8, a9, a10, a11);
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
