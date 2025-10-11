package data;

@SuppressWarnings("unused")
class PrintlnTestData {
    static final int CONST_VALUE = 0;

    void println(String string) {
        [0:"System.out."][18:"System.out."][22:"System.out."][27:"System.out."]println([1:"\"Hello\""][19:"\"Hello\""][23:"\"Hello\""][28:"\"Hello\""]);
        [2:"System.out."][19:"System.out."][23:"System.out."][29:"System.out."]println
                (123);
        [3:"System.\n                out."][19:"System.\n                out."][23:"System.\n                out."][30:"System.\n                out."]println([4:"\"Spacing\""][20:"\"Spacing\""][24:"\"Spacing\""][31:"\"Spacing\""]);
        [5:"System.out.\n                "][20:"System.out.\n                "][24:"System.out.\n                "][32:"System.out.\n                "]println(3.14);
        [6:"System.out."][20:"System.out."][24:"System.out."][33:"System.out."]println(string);
        [7:"System.out."][20:"System.out."][24:"System.out."][34:"System.out."]println(true);
        [8:"System.out."][20:"System.out."][24:"System.out."][35:"System.out."]println('A');
        [9:"System.out."][20:"System.out."][24:"System.out."][36:"System.out."]println(CONST_VALUE);
        [10:"System.out."][20:"System.out."][24:"System.out."][37:"System.out."]println("Divided: " + ""[11:" +\n                "][12:" +\n                "][21:" +\n                "][25:" +\n                "][38:" +\n                "][39:" +\n                "]"into"[11:" +\n                "][12:" +\n                "][21:" +\n                "][25:" +\n                "][38:" +\n                "][39:" +\n                "]" multiple" + " " + "strings");
        [13:"System.out."][21:"System.out."][25:"System.out."][40:"System.out."]println("Passed as parameter: " + string);
        [14:"System.out."][21:"System.out."][25:"System.out."][41:"System.out."]println("Passed as parameter: "[15:" +\n"][16:" +\n"][22:" +\n"][26:" +\n"][42:" +\n"][43:" +\n"]this.getClass());
        [17:"System.out."][22:"System.out."][26:"System.out."][44:"System.out."]println("""
                text
                block
                """);
    }

}
