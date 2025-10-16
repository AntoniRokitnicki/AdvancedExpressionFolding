package data[0:";"]

import java.util.Arrays[1:";"]

public class SemicolonTestData {
    public static void main(String[] args) {
        if (args.length > 0) {
        for (String arg : args) {
                System.out.println(arg)[3:";"]
            }
        }
                Arrays.stream(args).forEach(System.out::println)[2:";"]
    }
}
