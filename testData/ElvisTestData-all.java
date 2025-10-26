package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>ElvisTestData</fold> e = create();
        <fold text='' expand='false'>System.out.</fold>println(<fold text='' expand='false'>e != null ? </fold>e<fold text=' ?: ' expand='false'> : </fold>"");
        <fold text='' expand='false'>System.out.</fold>println(<fold text='' expand='false'>e != null ? </fold>e<fold text='?.' expand='false'>.</fold>sayHello()<fold text=' ?: ' expand='false'> : </fold>"");
        <fold text='' expand='false'>System.out.</fold>println(<fold text='e ?: ""' expand='false'>e == null ? "" : e</fold>); // Inverted Elvis should also fold to e ?: ""
        <fold text='' expand='false'>System.out.</fold>println(<fold text='e?.!! != null' expand='false'>e != null && e.<fold text='!!' expand='false'>get()</fold> != null</fold> ? e.<fold text='!!' expand='false'>get()</fold> : ""); // Should be System.out.println(e?.get ?: "")
        <fold text='' expand='false'>System.out.</fold>println(<fold text='e?.!! != null' expand='false'>e != null && e.<fold text='!!' expand='false'>get()</fold> != null</fold> ? e.<fold text='!!' expand='false'>get()</fold>.sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        if (e != null) <fold text='{...}' expand='true'>{
                e<fold text='?.' expand='false'>.</fold><fold text='!!' expand='false'>get()</fold>.sayHello();<fold text='' expand='false'>
        }</fold></fold>
        if (e.get() != null) <fold text='{...}' expand='true'>{
                e.<fold text='!!' expand='false'>get()</fold><fold text='?.' expand='false'>.</fold>sayHello();<fold text='' expand='false'>
        }</fold></fold>
        if (e != null && e.get() != null) <fold text='{...}' expand='true'>{
                e<fold text='?.' expand='false'>.</fold><fold text='!!' expand='false'>get()<fold text='?.' expand='false'></fold>.</fold>sayHello();<fold text='' expand='false'>
        }</fold></fold>
        if (e != null && e.get() != null && e.get().get() != null) <fold text='{...}' expand='true'>{
                e<fold text='?.' expand='false'>.</fold><fold text='!!' expand='false'>get()</fold><fold text='?.' expand='false'>.</fold><fold text='!!' expand='false'>get()</fold><fold text='?.' expand='false'>.</fold>sayHello();<fold text='' expand='false'>
        }</fold></fold>
    }</fold>

    private String sayHello()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"Hello"<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private static ElvisTestData create() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
        </fold>if <fold text='' expand='false'>(</fold>Math.random() > 0.5<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
        return new ElvisTestData();
        }</fold> else <fold text='{...}' expand='true'>{
        return null;
        }</fold><fold text=' ' expand='true'>
    </fold>}</fold>

    private ElvisTestData get()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>e<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>
}
