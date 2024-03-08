package data

import java.util.Arrays

public class SemicolonTestData {
    public static void main(String[] args) {
        if (args.length > 0) {
        for (String arg : args) {
                System.out.println(arg)
            }
        }
                Arrays.stream(args).forEach(System.out::println)
    }
}
