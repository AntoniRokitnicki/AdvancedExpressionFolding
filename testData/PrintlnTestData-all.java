package data;

<fold text='' expand='true'>@SuppressWarnings("unused")</fold>
class PrintlnTestData {
    static final int CONST_VALUE = 0;

    void println(String string) <fold text='{...}' expand='true'>{
        System.out.println("Hello");
        System.out.println
                (123);
        System.
                out.println("Spacing");
        System.out.
                println(3.14);
        System.out.println(string);
        System.out.println(true);
        System.out.println('A');
        System.out.println(CONST_VALUE);
        System.out.println("Divided: " + "" +
                "into" +
                " multiple" + " " + "strings");
        System.out.println("Passed as parameter: " + string);
        System.out.println("Passed as parameter: " +
this.getClass());
        System.out.println("""
                text
                block
                """);
    }</fold>

}
