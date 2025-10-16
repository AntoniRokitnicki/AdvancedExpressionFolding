package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        [0:"List<String>"] list = [1:"Arrays.asList("][2:"\"one\""], [3:"\"two\""][1:")"];
        list[4:".set("]1[4:","]"three"[4:" )"];
        [5:"System.out."]println(list[6:".get"]([6:"list.size() - 1"]));
        [8:"System.out."]println(args[9:"[args.length - 1]"]);
        [11:"HashMap<String, Integer>"] map = new HashMap<>();
        map[12:".put("]"one"[12:", "]1[12:")"];
        [13:"System.out."]println(map[14:".get("]"two"[14:")"]);
        [16:"List<String>"] singleton = [17:"java.util.Collections.singletonList("][18:"\"one\""][17:")"];
        [19:"System.out."]println(singleton);
        [20:"List<String>"] asList = [21:"Arrays.asList("][22:"\"one\""], [23:"\"two\""][21:")"];
        [24:"System.out."]println(asList);
        [25:"List<String>"] copy = [26:"new ArrayList<>(Arrays.asList("][27:"\"one\""], [28:"\"two\""][26:"))"];
        [29:"System.out."]println(copy);
        [30:"List<String>"] unmodifiable = [31:"Collections.unmodifiableList(Arrays.asList("][32:"\"one\""], [33:"\"two\""][31:"))"];
        [34:"System.out."]println(unmodifiable);
        [35:"Set<String>"] set = [36:"new HashSet<String>() "][36:"{\n            "][36:"{\n                add("]"one"[36:");\n                add("]"two"[36:");\n            }"][36:"\n        "][36:"}"];
        [37:"System.out."]println(set);
        [38:"Set<String>"] copyOfSet = [39:"Collections.unmodifiableSet(new HashSet<String>() "][39:"{\n            "][39:"{\n                add("]"one"[39:");\n                add("]"two"[39:");\n            }"][39:"\n        "][39:"}"][39:")"];
        [40:"System.out."]println(copyOfSet);
        [41:"String[]"] strings = [42:"new String[] {"]"one", "two"[42:"}"];
        [43:"System.out."]println(Arrays.toString(strings));
        [44:"System.out."]println(System[45:".getProperty("]"user.dir"[45:")"]);
        [47:"System.out."]println(System.getProperty("user.dir", "c:/windows"));
        [48:"System.out."]println(System.getenv("user.dir"));
        [49:"System.out."]println(System.getenv()[50:".get("]"user.dir"[50:")"]);
    }
}
