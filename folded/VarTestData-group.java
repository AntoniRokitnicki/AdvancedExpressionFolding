package data;

public class VarTestData {
    public static void main(String[] args) {
        [0:"String"] string = "Hello, world";
        System.out.println();
        [1:"int"] count = 0;
        for ([2:"String"] arg : args) {
                System.out.println(arg);
        count++;
        }
        for ([3:"int"] i = 0; i < args.length; i++) {
                [4:"String"] arg = args[i];
        System.out.println(arg);
        i++;
        }
    }
}
