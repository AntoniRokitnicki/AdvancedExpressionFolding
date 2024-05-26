package data;

public class DynamicTestData {

    static class Data <fold text='{...}' expand='true'>{
        Data data;

        String string;

        public String getString()<fold text=' { ' expand='false'> {
            </fold>return string;<fold text=' }' expand='false'>
        }</fold>

        public Data getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public static void aaa(Data data) <fold text='{...}' expand='true'>{
        var aa = new DynamicTestData()
                .<fold text='mainek1' expand='true'>main</fold>(
                        new DynamicTestData()
                                .<fold text='mainek2' expand='true'>main2</fold>(
                                        new DynamicTestData()
                                                .<fold text='mainek1' expand='true'>main</fold>(
                                                        main3(
                                                                new DynamicTestData()
                                                                        .<fold text='mainek3' expand='true'>main3</fold>(
                                                                                data.getData().getString()
                                                                        )
                                                        )
                                                )
                                )
                );
        System.out.println(aa);
    }</fold>

    private String <fold text='mainek1' expand='true'>main</fold>(String args)<fold text=' { ' expand='false'> {
        </fold>return "";<fold text=' }' expand='false'>
    }</fold>

    private String <fold text='mainek2' expand='true'>main2</fold>(String args)<fold text=' { ' expand='false'> {
        </fold>return "";<fold text=' }' expand='false'>
    }</fold>

    private static String <fold text='mainek3' expand='true'>main3</fold>(String args) <fold text='{...}' expand='true'>{
        System.out.println("DynamicTestData.main3");
        return "";
    }</fold>

}
