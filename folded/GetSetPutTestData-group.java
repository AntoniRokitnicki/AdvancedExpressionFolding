package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        List<String> list = [0:"Arrays.asList("][24:"Arrays.asList("][1:"\"one\""][25:"\"one\""], [2:"\"two\""][26:"\"two\""][0:")"][24:")"];
        list[3:".set("][27:".set("]1[3:","][27:","]"three"[3:" )"][27:" )"];
        System.out.println(list[4:".get"][28:".get"]([4:"list.size() - 1"][28:"list.size() - 1"]));
        System.out.println(args[5:"[args.length - 1]"][29:"[args.length - 1]"]);
        HashMap<String, Integer> map = new HashMap<>();
        map[6:".put("][30:".put("]"one"[6:", "][30:", "]1[6:")"][30:")"];
        System.out.println(map[7:".get("][31:".get("]"two"[7:")"][31:")"]);
        List<String> singleton = [8:"java.util.Collections.singletonList("][32:"java.util.Collections.singletonList("][9:"\"one\""][33:"\"one\""][8:")"][32:")"];
        System.out.println(singleton);
        List<String> asList = [10:"Arrays.asList("][34:"Arrays.asList("][11:"\"one\""][35:"\"one\""], [12:"\"two\""][36:"\"two\""][10:")"][34:")"];
        System.out.println(asList);
        List<String> copy = [13:"new ArrayList<>(Arrays.asList("][37:"new ArrayList<>(Arrays.asList("][14:"\"one\""][38:"\"one\""], [15:"\"two\""][39:"\"two\""][13:"))"][37:"))"];
        System.out.println(copy);
        List<String> unmodifiable = [16:"Collections.unmodifiableList(Arrays.asList("][40:"Collections.unmodifiableList(Arrays.asList("][17:"\"one\""][41:"\"one\""], [18:"\"two\""][42:"\"two\""][16:"))"][40:"))"];
        System.out.println(unmodifiable);
        Set<String> set = [19:"new HashSet<String>() "][43:"new HashSet<String>() "][19:"{\n            "][43:"{\n            "][19:"{\n                add("][43:"{\n                add("]"one"[19:");\n                add("][43:");\n                add("]"two"[19:");\n            }"][43:");\n            }"][19:"\n        "][43:"\n        "][19:"}"][43:"}"];
        System.out.println(set);
        Set<String> copyOfSet = [20:"Collections.unmodifiableSet(new HashSet<String>() "][44:"Collections.unmodifiableSet(new HashSet<String>() "][20:"{\n            "][44:"{\n            "][20:"{\n                add("][44:"{\n                add("]"one"[20:");\n                add("][44:");\n                add("]"two"[20:");\n            }"][44:");\n            }"][20:"\n        "][44:"\n        "][20:"}"][44:"}"][20:")"][44:")"];
        System.out.println(copyOfSet);
        String[] strings = [21:"new String[] {"][45:"new String[] {"]"one", "two"[21:"}"][45:"}"];
        System.out.println(Arrays.toString(strings));
        System.out.println(System[22:".getProperty("][46:".getProperty("]"user.dir"[22:")"][46:")"]);
        System.out.println(System.getProperty("user.dir", "c:/windows"));
        System.out.println(System.getenv("user.dir"));
        System.out.println(System.getenv()[23:".get("][47:".get("]"user.dir"[23:")"][47:")"]);
    }
}
