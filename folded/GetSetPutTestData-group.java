package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        List<String> list = [0:"Arrays.asList("]{[0:"Arrays.asList("]}[0:""one""]{[0:""one""]}, [0:""two""]{[0:""two""]}[0:")"]{[0:")"]};
        list[0:".set("]{[0:".set("]}1[0:","]{[0:","]}"three"[0:" )"]{[0:" )"]};
        System.out.println(list[0:".get"]{[0:".get"]}([0:"list.size() - 1"]{[0:"list.size() - 1"]}));
        System.out.println(args[0:"[args.length - 1]"]{[0:"[args.length - 1]"]});
        HashMap<String, Integer> map = new HashMap<>();
        map[0:".put("]{[0:".put("]}"one"[0:", "]{[0:", "]}1[0:")"]{[0:")"]};
        System.out.println(map[0:".get("]{[0:".get("]}"two"[0:")"]{[0:")"]});
        List<String> singleton = [0:"java.util.Collections.singletonList("]{[0:"java.util.Collections.singletonList("]}[0:""one""]{[0:""one""]}[0:")"]{[0:")"]};
        System.out.println(singleton);
        List<String> asList = [0:"Arrays.asList("]{[0:"Arrays.asList("]}[0:""one""]{[0:""one""]}, [0:""two""]{[0:""two""]}[0:")"]{[0:")"]};
        System.out.println(asList);
        List<String> copy = [0:"new ArrayList<>(Arrays.asList("]{[0:"new ArrayList<>(Arrays.asList("]}[0:""one""]{[0:""one""]}, [0:""two""]{[0:""two""]}[0:"))"]{[0:"))"]};
        System.out.println(copy);
        List<String> unmodifiable = [0:"Collections.unmodifiableList(Arrays.asList("]{[0:"Collections.unmodifiableList(Arrays.asList("]}[0:""one""]{[0:""one""]}, [0:""two""]{[0:""two""]}[0:"))"]{[0:"))"]};
        System.out.println(unmodifiable);
        Set<String> set = [0:"new HashSet<String>() "]{[0:"new HashSet<String>() "]}[0:"{
            "]{[0:"{
            "]}[0:"{
                add("]{[0:"{
                add("]}"one"[0:");
                add("]{[0:");
                add("]}"two"[0:");
            }"]{[0:");
            }"]}[0:"
        "]{[0:"
        "]}[0:"}"]{[0:"}"]};
        System.out.println(set);
        Set<String> copyOfSet = [0:"Collections.unmodifiableSet(new HashSet<String>() "]{[0:"Collections.unmodifiableSet(new HashSet<String>() "]}[0:"{
            "]{[0:"{
            "]}[0:"{
                add("]{[0:"{
                add("]}"one"[0:");
                add("]{[0:");
                add("]}"two"[0:");
            }"]{[0:");
            }"]}[0:"
        "]{[0:"
        "]}[0:"}"]{[0:"}"]}[0:")"]{[0:")"]};
        System.out.println(copyOfSet);
        String[] strings = [0:"new String[] {"]{[0:"new String[] {"]}"one", "two"[0:"}"]{[0:"}"]};
        System.out.println(Arrays.toString(strings));
        System.out.println(System[0:".getProperty("]{[0:".getProperty("]}"user.dir"[0:")"]{[0:")"]});
        System.out.println(System.getProperty("user.dir", "c:/windows"));
        System.out.println(System.getenv("user.dir"));
        System.out.println(System.getenv()[0:".get("]{[0:".get("]}"user.dir"[0:")"]{[0:")"]});
    }
}
