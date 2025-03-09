package data;

public class DynamicTestData {

    static class Data <fold text='{...}' expand='true'>{
        String data;

        public String getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
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

    private String <fold text='changedNormalMethod' expand='true'>normalMethod</fold>(String args)<fold text=' { ' expand='false'> {
        </fold>return <fold text='changedNormalMethod' expand='true'>normalMethod</fold>(args.substring(1));<fold text=' }' expand='false'>
    }</fold>

    private static String <fold text='changedStaticMethod' expand='true'>staticMethod</fold>(String args) <fold text='{...}' expand='true'>{
        System.out.println("DynamicTestData.staticMethod");
        return "";
    }</fold>

}