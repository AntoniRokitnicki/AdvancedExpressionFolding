package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        int[] emptyArray = [];
        int[] numbers = [1, 2, 3];
        List<String> emptyList = [];
        Set<String> emptySet = new HashSet<>();
        List<String> list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.getLast());
        System.out.println(args.last());
        HashMap<String, Integer> map = new HashMap<>();
        map["one"] = 1;
        System.out.println(map["two"]);
        List<String> singleton = ["one"];
        System.out.println(singleton);
        List<String> asList = ["one", "two"];
        System.out.println(asList);
        List<String> copy = ["one", "two"];
        System.out.println(copy);
        List<String> unmodifiable = ["one", "two"];
        System.out.println(unmodifiable);
        Set<String> set = ["one", "two"];
        System.out.println(set);
        Set<String> copyOfSet = ["one", "two"];
        System.out.println(copyOfSet);
        String[] strings = ["one", "two"];
        System.out.println(Arrays.toString(strings));
        System.out.println(System["user.dir"]);
        System.out.println(System.getProperty("user.dir", "c:/windows"));
        System.out.println(System.getenv("user.dir"));
        System.out.println(System.getenv()["user.dir"]);
    }
}
