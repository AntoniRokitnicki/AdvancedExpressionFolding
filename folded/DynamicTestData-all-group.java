package data;

public class DynamicTestData {

    [1:"s"]tatic class Data {
        String data;[1:"\n\n        "][1:"public String getData() {\n            return data;\n        }"]
    }


    public static void [3:"staticMethod"](Data data) {
        new DynamicTestData()
                .[4:"normalMethod"](
                        [5:"staticMethod"](
                                new DynamicTestData()
                                        .[5:"normalMethod"](
                                                new DynamicTestData().[5:"staticMethod"](
                                                        data.[6:"getData()"]
                                                )
                                        )
                        )
                );
        [10:"staticMethod"](data.[11:"getData()"]);
    }

    [0:"private String normalMethod(String args) {\n        return normalMethod(args.substring(1));\n    }"]

    private static String [24:"staticMethod"](String args) {
        [25:"System.out."]println([26:"\"DynamicTestData.staticMethod\""]);
        return "";
    }

}