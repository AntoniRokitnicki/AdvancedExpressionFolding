package data;

@SuppressWarnings("unused")
class PrintlnTestData {
    static final int CONST_VALUE = 0;

    void println(String string) {
        [0:"System.out."]println([1:"\"Hello\""]);
        [2:"System.out."]println
                (123);
        [3:"System.\n                out."]println([4:"\"Spacing\""]);
        [5:"System.out.\n                "]println(3.14);
        [6:"System.out."]println(string);
        [7:"System.out."]println(true);
        [8:"System.out."]println('A');
        [9:"System.out."]println(CONST_VALUE);
        [10:"System.out."]println("Divided: " + ""[11:" +\n                "]"into"[11:" +\n                "]" multiple" + " " + "strings");
        [13:"System.out."]println("Passed as parameter: " + string);
        [14:"System.out."]println("Passed as parameter: "[15:" +\n"]this.getClass());
        [17:"System.out."]println("""
                text
                block
                """);
    }

}
