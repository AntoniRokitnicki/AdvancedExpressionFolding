package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        System.out.println(list[1:]);
        System.out.println(list[1:2]);
        System.out.println(list[1:]);
        System.out.println(list[:2]);
        System.out.println(list[1:-2]);
        System.out.println(list[:-2]);
        String f = args[0];
        System.out.println(f[1:]);
        System.out.println(f[1:2]);
        System.out.println(f[1:]);
        System.out.println(f[:2]);
        System.out.println(f[1:-2]);
        System.out.println(f[:-2]);
    }
}
