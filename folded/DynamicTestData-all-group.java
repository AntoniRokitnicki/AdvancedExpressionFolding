package data;

public class DynamicTestData {

    [1:"s"]tatic class Data {
        String data;[1:"\n\n        "][1:"public String getData() {\n            return data;\n        }"]" "]data[2:";"][2:"\n        "]}
    }


    public static void [3:"staticMethod"](Data data) {
        new DynamicTestData()
                .[4:"normalMethod"][12:"normalMethod"][14:"normalMethod"][16:"normalMethod"](
                        [5:"staticMethod"][6:"staticMethod"][12:"staticMethod"][14:"staticMethod"][16:"staticMethod"](
                                new DynamicTestData()
                                        .[5:"normalMethod"][6:"normalMethod"][7:"normalMethod"][12:"normalMethod"][14:"normalMethod"][16:"normalMethod"](
                                                new DynamicTestData().[5:"staticMethod"][6:"staticMethod"][7:"staticMethod"][8:"staticMethod"][12:"staticMethod"][14:"staticMethod"][16:"staticMethod"](
                                                        data.[6:"getData()"][7:"getData()"][8:"getData()"][9:"getData()"][10:"getData()"][13:"getData()"][15:"getData()"][17:"getData()"]
                                                )
                                        )
                        )
                );
        [10:"staticMethod"][13:"staticMethod"][15:"staticMethod"][17:"staticMethod"](data.[11:"getData()"][12:"getData()"][14:"getData()"][16:"getData()"][18:"getData()"]);
    }

    [0:"private String normalMethod(String args) {\n        return normalMethod(args.substring(1));\n    }"]:"normalMethod"][22:"normalMethod"](args[21:".substring("][22:".substring("][23:".substring("]1[21:")"][22:")"][23:")"])[19:";"][19:"\n    "]}

    private static String [24:"staticMethod"](String args) {
        [25:"System.out."][27:"System.out."][28:"System.out."][30:"System.out."]println([26:"\"DynamicTestData.staticMethod\""][28:"\"DynamicTestData.staticMethod\""][29:"\"DynamicTestData.staticMethod\""][31:"\"DynamicTestData.staticMethod\""]);
        return "";
    }

}