package data;

public class DynamicTestData {

    <fold text='@Getter s' expand='false'>s</fold>tatic class Data <fold text='{...}' expand='true'>{
        String data;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>


    public static void <fold text='changedStaticMethod' expand='true'>staticMethod</fold>(Data data) <fold text='{...}' expand='true'>{
        new DynamicTestData()
                .<fold text='changedNormalMethod' expand='true'>normalMethod</fold>(
                        <fold text='changedStaticMethod' expand='true'>staticMethod</fold>(
                                new DynamicTestData()
                                        .<fold text='changedNormalMethod' expand='true'>normalMethod</fold>(
                                                new DynamicTestData().<fold text='changedStaticMethod' expand='true'>staticMethod</fold>(
                                                        data.<fold text='data' expand='false'>getData()</fold>
                                                )
                                        )
                        )
                );
        <fold text='changedStaticMethod' expand='true'>staticMethod</fold>(data.<fold text='data' expand='false'>getData()</fold>);
    }</fold>

    <fold text='' expand='true'>private String <fold text='changedNormalMethod' expand='true'>normalMethod</fold>(String args<fold text=' = args.substring(1))' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='changedNormalMethod' expand='true'>normalMethod</fold>(args<fold text='[' expand='false'>.substring(</fold>1<fold text=':]' expand='false'>)</fold>)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    private static String <fold text='changedStaticMethod' expand='true'>staticMethod</fold>(String args) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>System.out.</fold>println(<fold text='"DynamicTestData.staticMethod"' expand='false'>"DynamicTestData.staticMethod"</fold>);
        return "";
    }</fold>

}