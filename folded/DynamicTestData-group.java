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
                .[0:"normalMethod"]{[0:"normalMethod"]{[0:"normalMethod"]{[0:"normalMethod"]}}}(
                        [0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]}}}}(
                                new DynamicTestData()
                                        .[0:"normalMethod"]{[0:"normalMethod"]{[0:"normalMethod"]{[0:"normalMethod"]{[0:"normalMethod"]{[0:"normalMethod"]}}}}}(
                                                new DynamicTestData().[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]}}}}}}(
                                                        data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}}}}}}
                                                )
                                        )
                        )
                );
        [0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]{[0:"staticMethod"]}}}(data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}}});
    }

    private String [0:"normalMethod"](String args) {
        return [0:"normalMethod"]{[0:"normalMethod"]}(args.substring(1));
    }

    private static String [0:"staticMethod"](String args) {
        System.out.println("DynamicTestData.staticMethod");
        return "";
    }

}