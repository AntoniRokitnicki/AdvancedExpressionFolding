package data;

public class DynamicTestData {

    <fold text='@Getter s' expand='false'>s</fold>tatic class Data <fold text='{...}' expand='true'>{
        Data data;

        String string;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public Data getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public static void aaa(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>var</fold> aa = new DynamicTestData()
                .<fold text='mainek1' expand='true'>main</fold>(
                        new DynamicTestData()
                                .<fold text='mainek2' expand='true'>main2</fold>(
                                        new DynamicTestData()
                                                .<fold text='mainek1' expand='true'>main</fold>(
                                                        main3(
                                                                new DynamicTestData()
                                                                        .<fold text='mainek3' expand='true'>main3</fold>(
                                                                                data.<fold text='data' expand='false'>getData()</fold>.<fold text='string' expand='false'>getString()</fold>
                                                                        )
                                                        )
                                                )
                                )
                );
        <fold text='' expand='false'>System.out.</fold>println(aa);
    }</fold>

    private String <fold text='mainek1' expand='true'>main</fold>(String args)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>""<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private String <fold text='mainek2' expand='true'>main2</fold>(String args)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>""<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private static String <fold text='mainek3' expand='true'>main3</fold>(String args) <fold text='{...}' expand='true'>{
        System.out.println("DynamicTestData.main3");
        return "";
    }</fold>

}