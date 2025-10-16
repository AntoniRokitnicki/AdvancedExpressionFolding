package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        List<String> list = [0:"Arrays.asList("][1:"\"one\""], [2:"\"two\""][0:")"];
        list[3:".set("]1[3:","]"three"[3:" )"];
        System.out.println(list[4:".get"]([4:"list.size() - 1"]));
        System.out.println(args[5:"[args.length - 1]"]);
        HashMap<String, Integer> map = new HashMap<>();
        map[6:".put("]"one"[6:", "]1[6:")"];
        System.out.println(map[7:".get("]"two"[7:")"]);
        List<String> singleton = [8:"java.util.Collections.singletonList("][9:"\"one\""][8:")"];
        System.out.println(singleton);
        List<String> asList = [10:"Arrays.asList("][11:"\"one\""], [12:"\"two\""][10:")"];
        System.out.println(asList);
        List<String> copy = [13:"new ArrayList<>(Arrays.asList("][14:"\"one\""], [15:"\"two\""][13:"))"];
        System.out.println(copy);
        List<String> unmodifiable = [16:"Collections.unmodifiableList(Arrays.asList("][17:"\"one\""], [18:"\"two\""][16:"))"];
        System.out.println(unmodifiable);
        Set<String> set = [19:"new HashSet<String>() "][19:"{\n            "][19:"{\n                add("]"one"[19:");\n                add("]"two"[19:");\n            }"][19:"\n        "][19:"}"];
        System.out.println(set);
        Set<String> copyOfSet = [20:"Collections.unmodifiableSet(new HashSet<String>() "][20:"{\n            "][20:"{\n                add("]"one"[20:");\n                add("]"two"[20:");\n            }"][20:"\n        "][20:"}"][20:")"];
        System.out.println(copyOfSet);
        String[] strings = [21:"new String[] {"]"one", "two"[21:"}"];
        System.out.println(Arrays.toString(strings));
        System.out.println(System[22:".getProperty("]"user.dir"[22:")"]);
        System.out.println(System.getProperty("user.dir", "c:/windows"));
        System.out.println(System.getenv("user.dir"));
        System.out.println(System.getenv()[23:".get("]"user.dir"[23:")"]);
    }
}
