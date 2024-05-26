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
                .mainek1(
                        new DynamicTestData()
                                .mainek2(
                                        new DynamicTestData()
                                                .mainek1(
                                                        main3(
                                                                new DynamicTestData()
                                                                        .mainek3(
                                                                                data.getData().getString()
                                                                        )
                                                        )
                                                )
                                )
                );
        System.out.println(aa);
    }

    private String mainek1(String args) {
        return "";
    }

    private String mainek2(String args) {
        return "";
    }

    private static String mainek3(String args) {
        System.out.println("DynamicTestData.main3");
        return "";
    }

}
