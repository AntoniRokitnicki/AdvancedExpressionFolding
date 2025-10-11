package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        System.out.println(list[0:".subList("]{[0:".subList("]}1[0:", list.size())"]{[0:", list.size())"]});
        System.out.println(list[0:".subList("]{[0:".subList("]}1[0:", "]{[0:", "]}2[0:")"]{[0:")"]});
        System.out.println(list[0:".subList("]{[0:".subList("]}1[0:", list.size())"]{[0:", list.size())"]});
        System.out.println(list[0:".subList(0"]{[0:".subList(0"]}[0:", "]{[0:", "]}2[0:")"]{[0:")"]});
        System.out.println(list[0:".subList("]{[0:".subList("]}1[0:", list.size() "]{[0:", list.size() "]}-[0:" "]{[0:" "]}2[0:")"]{[0:")"]});
        System.out.println(list[0:".subList(0"]{[0:".subList(0"]}[0:", list.size() "]{[0:", list.size() "]}-[0:" "]{[0:" "]}2[0:")"]{[0:")"]});
        String f = args[0];
        System.out.println(f[0:".substring("]{[0:".substring("]}1[0:")"]{[0:")"]});
        System.out.println(f[0:".substring("]{[0:".substring("]}1[0:", "]{[0:", "]}2[0:")"]{[0:")"]});
        System.out.println(f[0:".substring("]{[0:".substring("]}1[0:", f.length())"]{[0:", f.length())"]});
        System.out.println(f[0:".substring(0"]{[0:".substring(0"]}[0:", "]{[0:", "]}2[0:")"]{[0:")"]});
        System.out.println(f[0:".substring("]{[0:".substring("]}1[0:", f.length() "]{[0:", f.length() "]}-[0:" "]{[0:" "]}2[0:")"]{[0:")"]});
        System.out.println(f[0:".substring(0"]{[0:".substring(0"]}[0:", f.length() "]{[0:", f.length() "]}-[0:" "]{[0:" "]}2[0:")"]{[0:")"]});
    }
}
