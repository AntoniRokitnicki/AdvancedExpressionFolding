package data;

public class DynamicTestData {

    static class Data {
        Data data;

        String string;

        public String getString() {
            return string;
        }

        public Data getData() {
            return data;
        }
    }

    public static void aaa(Data data) {
        var aa = new DynamicTestData()
                .main(
                        new DynamicTestData()
                                .main2(
                                        new DynamicTestData()
                                                .main(
                                                        main3(
                                                                new DynamicTestData()
                                                                        .main3(
                                                                                data.getData().getString()
                                                                        )
                                                        )
                                                )
                                )
                );
        System.out.println(aa);
    }

    private String main(String args) {
        return "";
    }

    private String main2(String args) {
        return "";
    }

    private static String main3(String args) {
        System.out.println("DynamicTestData.main3");
        return "";
    }

}
