package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        val list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.last());
        System.out.println(args.last());
        val map = new HashMap<>();
        map["one"] = 1;
        System.out.println(map["two"]);
        val singleton = ["one"];
        System.out.println(singleton);
        val asList = ["one", "two"];
        System.out.println(asList);
        val copy = ["one", "two"];
        System.out.println(copy);
        val unmodifiable = ["one", "two"];
        System.out.println(unmodifiable);
        val set = ["one", "two"];
        System.out.println(set);
        val copyOfSet = ["one", "two"];
        System.out.println(copyOfSet);
        val strings = ["one", "two"];
        System.out.println(Arrays.toString(strings));
    }
}
