package data;

@SuppressWarnings("unused")
public class PrintlnTestData {
    static final int CONST_VALUE = 0;

    void println(String string) {
        println("Hello");
        println
                (123);
        println("Spacing");
        println(3.14);
        println(string);
        println(true);
        println('A');
        println(CONST_VALUE);
        println("Divided: " + "" + "into" + " multiple" + " " + "strings");
        println("Passed as parameter: " + string);
        println("Passed as parameter: " + this.getClass());
        println("""
                text
                block
                """);
    }

}
