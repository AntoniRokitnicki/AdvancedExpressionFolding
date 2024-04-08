package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        val list = Arrays.asList(args);
        println(list[1:]);
        println(list[1:2]);
        println(list[1:]);
        println(list[:2]);
        println(list[1:-2]);
        println(list[:-2]);
        val f = args[0];
        println(f[1:]);
        println(f[1:2]);
        println(f[1:]);
        println(f[:2]);
        println(f[1:-2]);
        println(f[:-2]);
    }
}
