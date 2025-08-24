package data;

public class StringBuilderTestData {
    public static void main(String[] args) {
        StringBuilder sb1 = "[";
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb1 += arg;
        if (i < args.length - 1) {
                sb1 += ",";
            }
        }
                System.out.println(sb1 + "]");

        StringBuilder sb2 = "[";
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb2 += arg;
        if (i < args.length - 1) {
                sb2 += ",";
            }
        }
                System.out.println(sb2 + "]");

        StringBuilder sb3 = "";
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb3 += arg;
        if (i < args.length - 1) {
                sb3 += "," + " ";
            }
        }
                System.out.println(sb3);
        String str = "hello";
        System.out.println(str[0]);
        int i = 2;
        System.out.println(str[i]);
        System.out.println(str[str.length() - 1]);
    }
}
