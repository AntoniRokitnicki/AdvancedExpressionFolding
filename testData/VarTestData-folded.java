package data;

public class VarTestData {
    public static void main(String[] args) {
        val string = "Hello, world";
        System.out.println();
        var count = 0;
        for (val arg : args) {
                System.out.println(arg);
        count++;
        }
        for (var i = 0; i < args.length; i++) {
                val arg = args[i];
        System.out.println(arg);
        i++;
        }
    }
}
