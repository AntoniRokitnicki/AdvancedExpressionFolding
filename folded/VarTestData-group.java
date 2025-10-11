package data;

public class VarTestData {
    public static void main(String[] args) {
        [0:"String"]{[0:"String"]} string = "Hello, world";
        System.out.println();
        [0:"int"]{[0:"int"]} count = 0;
        for ([0:"String"]{[0:"String"]} arg : args) {
                System.out.println(arg);
        count++;
        }
        for ([0:"int"]{[0:"int"]} i = 0; i < args.length; i++) {
                [0:"String"]{[0:"String"]} arg = args[i];
        System.out.println(arg);
        i++;
        }
    }
}
