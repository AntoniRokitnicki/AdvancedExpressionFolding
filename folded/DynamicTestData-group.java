package data;

public class DynamicTestData {

    static class Data {
        String data;

        public String getData() {
            return data;
        }
    }


    public static void [0:"staticMethod"](Data data) {
        new DynamicTestData()
                .[1:"normalMethod"][9:"normalMethod"][11:"normalMethod"][13:"normalMethod"](
                        [2:"staticMethod"][3:"staticMethod"][9:"staticMethod"][11:"staticMethod"][13:"staticMethod"](
                                new DynamicTestData()
                                        .[2:"normalMethod"][3:"normalMethod"][4:"normalMethod"][9:"normalMethod"][11:"normalMethod"][13:"normalMethod"](
                                                new DynamicTestData().[2:"staticMethod"][3:"staticMethod"][4:"staticMethod"][5:"staticMethod"][9:"staticMethod"][11:"staticMethod"][13:"staticMethod"](
                                                        data.[3:"getData()"][4:"getData()"][5:"getData()"][6:"getData()"][7:"getData()"][10:"getData()"][12:"getData()"][14:"getData()"]
                                                )
                                        )
                        )
                );
        [7:"staticMethod"][10:"staticMethod"][12:"staticMethod"][14:"staticMethod"](data.[8:"getData()"][9:"getData()"][11:"getData()"][13:"getData()"][15:"getData()"]);
    }

    private String [16:"normalMethod"](String args) {
        return [16:"normalMethod"][16:"normalMethod"](args.substring(1));
    }

    private static String [17:"staticMethod"](String args) {
        System.out.println("DynamicTestData.staticMethod");
        return "";
    }

}