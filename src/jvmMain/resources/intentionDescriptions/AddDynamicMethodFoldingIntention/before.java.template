package data;

public class DynamicTestData {

    static class Data {
        String data;

        public String getData() {
            return data;
        }
    }


    public static void staticMethod(Data data) {
        new DynamicTestData()
                .normalMethod(
                        staticMethod(
                                new DynamicTestData()
                                        .normalMethod(
                                                new DynamicTestData().staticMethod(
                                                        data.getData()
                                                )
                                        )
                        )
                );
        staticMethod(data.getData());
    }

    private String normalMethod(String args) {
        return normalMethod(args.substring(1));
    }

    private static String staticMethod(String args) {
        System.out.println("DynamicTestData.staticMethod");
        return "";
    }

}