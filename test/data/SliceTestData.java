package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(1, 2));
        System.out.println(list.subList(1, list.size()));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, list.size() - 2));
        System.out.println(list.subList(0, list.size() - 2));
        String f = args[0];
        System.out.println(f.substring(1));
        System.out.println(f.substring(1, 2));
        System.out.println(f.substring(1, f.length()));
        System.out.println(f.substring(0, 2));
        System.out.println(f.substring(1, f.length() - 2));
        System.out.println(f.substring(0, f.length() - 2));
    }
}
