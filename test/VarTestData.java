public class VarTestData {
    public static void main(String[] args) {
        String string = "Hello, world";
        System.out.println();
        int count = 0;
        for (String arg : args) {
                System.out.println(arg);
        count++;
        }
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        System.out.println(arg);
        i++;
        }
    }
}
