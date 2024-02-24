package data;

public class TypeCastTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>TypeCastTestData</fold> t = new TypeCastTestData();
        if <fold text='' expand='false'>(</fold>t.<fold text='object' expand='false'>getObject()</fold> instanceof TypeCastTestData &&
                <fold text='' expand='false'>((TypeCastTestData) </fold>t.<fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold> instanceof TypeCastTestData<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                System.out.println(<fold text='' expand='false'>((TypeCastTestData) </fold><fold text='' expand='false'>((TypeCastTestData) </fold>t.<fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold>);
        handle(<fold text='' expand='false'>((TypeCastTestData) </fold><fold text='' expand='false'>((TypeCastTestData) </fold>t.<fold text='object' expand='false'>getObject()</fold><fold text='.' expand='false'>).</fold><fold text='object' expand='false'>getObject()</fold><fold text='' expand='false'>)</fold>);
        }</fold>
    }</fold>

    private Object getObject()<fold text=' { ' expand='false'> {
        </fold>return this;<fold text=' }' expand='false'>
    }</fold>

    private static void handle(TypeCastTestData t)<fold text=' { ' expand='false'> {
        </fold>System.out.println(t);<fold text=' }' expand='false'>
    }</fold>
}
