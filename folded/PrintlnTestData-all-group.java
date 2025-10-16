package data;

@SuppressWarnings("unused")
class PrintlnTestData {
    [0:"static final "][0:"int"] CONST_VALUE = 0;

    void println(String string) {
        [1:"System.out."]println([2:"\"Hello\""]);
        [3:"System.out."]println
                (123);
        [4:"System.\n                out."]println([5:"\"Spacing\""]);
        [6:"System.out.\n                "]println(3.14);
        [7:"System.out."]println(string);
        [8:"System.out."]println(true);
        [9:"System.out."]println('A');
        [10:"System.out."]println(CONST_VALUE);
        [11:"System.out."]println("Divided: [12:"\" + \""][12:"\" +\n                \""]into[12:"\" +\n                \""] multiple[12:"\" + \""] [12:"\" + \""]strings");
        [14:"System.out."]println("Passed as parameter: [15:"\" + "]string[15:")"];
        [17:"System.out."]println("Passed as parameter: [18:"\" +\n"]this.[19:"getClass()"][18:")"];
        [22:"System.out."]println([23:"\"\"\"\n                text\n                block\n                \"\"\""]);
    }

}
