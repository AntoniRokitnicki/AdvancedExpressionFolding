package data;

@SuppressWarnings("unused")
class PrintlnTestData {
    [0:"static final "][0:"int"] CONST_VALUE = 0;

    void println(String string) {
        [1:"System.out."][25:"System.out."][32:"System.out."][40:"System.out."]println([2:"\"Hello\""][26:"\"Hello\""][33:"\"Hello\""][41:"\"Hello\""]);
        [3:"System.out."][26:"System.out."][33:"System.out."][42:"System.out."]println
                (123);
        [4:"System.\n                out."][26:"System.\n                out."][33:"System.\n                out."][43:"System.\n                out."]println([5:"\"Spacing\""][27:"\"Spacing\""][34:"\"Spacing\""][44:"\"Spacing\""]);
        [6:"System.out.\n                "][27:"System.out.\n                "][34:"System.out.\n                "][45:"System.out.\n                "]println(3.14);
        [7:"System.out."][27:"System.out."][34:"System.out."][46:"System.out."]println(string);
        [8:"System.out."][27:"System.out."][34:"System.out."][47:"System.out."]println(true);
        [9:"System.out."][27:"System.out."][34:"System.out."][48:"System.out."]println('A');
        [10:"System.out."][27:"System.out."][34:"System.out."][49:"System.out."]println(CONST_VALUE);
        [11:"System.out."][27:"System.out."][34:"System.out."][50:"System.out."]println("Divided: [12:"\" + \""][13:"\" + \""][28:"\" + \""][35:"\" + \""][51:"\" + \""][52:"\" + \""][12:"\" +\n                \""][13:"\" +\n                \""][28:"\" +\n                \""][35:"\" +\n                \""][51:"\" +\n                \""][52:"\" +\n                \""]into[12:"\" +\n                \""][13:"\" +\n                \""][28:"\" +\n                \""][35:"\" +\n                \""][51:"\" +\n                \""][52:"\" +\n                \""] multiple[12:"\" + \""][13:"\" + \""][28:"\" + \""][35:"\" + \""][51:"\" + \""][52:"\" + \""] [12:"\" + \""][13:"\" + \""][28:"\" + \""][35:"\" + \""][51:"\" + \""][52:"\" + \""]strings");
        [14:"System.out."][28:"System.out."][35:"System.out."][53:"System.out."]println("Passed as parameter: [15:"\" + "][15:"\" + "][16:"\" + "][16:"\" + "][29:"\" + "][29:"\" + "][36:"\" + "][36:"\" + "][54:"\" + "][54:"\" + "][55:"\" + "][55:"\" + "]string[15:")"][16:")"][29:")"][36:")"][54:")"][55:")"];
        [17:"System.out."][29:"System.out."][36:"System.out."][56:"System.out."]println("Passed as parameter: [18:"\" +\n"][18:"\" +\n"][20:"\" +\n"][20:"\" +\n"][30:"\" +\n"][30:"\" +\n"][37:"\" +\n"][37:"\" +\n"][57:"\" +\n"][57:"\" +\n"][59:"\" +\n"][59:"\" +\n"]this.[19:"getClass()"][21:"getClass()"][31:"getClass()"][38:"getClass()"][58:"getClass()"][60:"getClass()"][18:")"][20:")"][30:")"][37:")"][57:")"][59:")"];
        [22:"System.out."][31:"System.out."][38:"System.out."][61:"System.out."]println([23:"\"\"\"\n                text\n                block\n                \"\"\""][24:"\"\"\"\n                text\n                block\n                \"\"\""][32:"\"\"\"\n                text\n                block\n                \"\"\""][39:"\"\"\"\n                text\n                block\n                \"\"\""][62:"\"\"\"\n                text\n                block\n                \"\"\""][63:"\"\"\"\n                text\n                block\n                \"\"\""]);
    }

}
