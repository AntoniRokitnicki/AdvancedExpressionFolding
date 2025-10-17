package data;

public class VarTestData {
    public static void main(String[] args) {
        [0:"String"][5:"String"] string = "Hello, world";
        System.out.println();
        [1:"int"][6:"int"] count = 0;
        for ([2:"String"][7:"String"] arg : args) {
                System.out.println(arg);
        count++;
        }
        for ([3:"int"][8:"int"] i = 0; i < args.length; i++) {
                [4:"String"][9:"String"] arg = args[i];
        System.out.println(arg);
        i++;
        }
    }
}
