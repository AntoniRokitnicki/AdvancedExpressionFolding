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
                .[1:"normalMethod"](
                        [2:"staticMethod"](
                                new DynamicTestData()
                                        .[2:"normalMethod"](
                                                new DynamicTestData().[2:"staticMethod"](
                                                        data.[3:"getData()"]
                                                )
                                        )
                        )
                );
        [7:"staticMethod"](data.[8:"getData()"]);
    }

    private String [16:"normalMethod"](String args) {
        return [16:"normalMethod"](args.substring(1));
    }

    private static String [17:"staticMethod"](String args) {
        System.out.println("DynamicTestData.staticMethod");
        return "";
    }

}