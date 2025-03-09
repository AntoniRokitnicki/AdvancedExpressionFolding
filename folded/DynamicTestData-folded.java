package data;

public class DynamicTestData {

    static class Data {
        String data;

        public String getData() {
            return data;
        }
    }


    public static void changedStaticMethod(Data data) {
        new DynamicTestData()
                .changedNormalMethod(
                        changedStaticMethod(
                                new DynamicTestData()
                                        .changedNormalMethod(
                                                new DynamicTestData().changedStaticMethod(
                                                        data.data
                                                )
                                        )
                        )
                );
        changedStaticMethod(data.data);
    }

    private String changedNormalMethod(String args) {
        return changedNormalMethod(args.substring(1));
    }

    private static String changedStaticMethod(String args) {
        System.out.println("DynamicTestData.staticMethod");
        return "";
    }

}