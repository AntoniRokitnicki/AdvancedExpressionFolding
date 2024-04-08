package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        val list = ["one", "two"];
        list[1] = "three";
        println(list.last());
        println(args.last());
        val map = new HashMap<>();
        map["one"] = 1;
        println(map["two"]);
        val singleton = ["one"];
        println(singleton);
        val asList = ["one", "two"];
        println(asList);
        val copy = ["one", "two"];
        println(copy);
        val unmodifiable = ["one", "two"];
        println(unmodifiable);
        val set = ["one", "two"];
        println(set);
        val copyOfSet = ["one", "two"];
        println(copyOfSet);
        val strings = ["one", "two"];
        println(Arrays.toString(strings));
    }
}
