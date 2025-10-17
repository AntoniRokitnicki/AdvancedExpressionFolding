package data;

import java.util.*;

public class GetSetPutTestData {
    public static void main(String[] args) {
        [0:"List<String>"][52:"List<String>"] list = [1:"Arrays.asList("][53:"Arrays.asList("][2:"\"one\""][54:"\"one\""], [3:"\"two\""][55:"\"two\""][1:")"][53:")"];
        list[4:".set("][56:".set("]1[4:","][56:","]"three"[4:" )"][56:" )"];
        [5:"System.out."][57:"System.out."]println(list[6:".get"][7:".get"][58:".get"][59:".get"]([6:"list.size() - 1"][7:"list.size() - 1"][58:"list.size() - 1"][59:"list.size() - 1"]));
        [8:"System.out."][60:"System.out."]println(args[9:"[args.length - 1]"][10:"[args.length - 1]"][61:"[args.length - 1]"][62:"[args.length - 1]"]);
        [11:"HashMap<String, Integer>"][63:"HashMap<String, Integer>"] map = new HashMap<>();
        map[12:".put("][64:".put("]"one"[12:", "][64:", "]1[12:")"][64:")"];
        [13:"System.out."][65:"System.out."]println(map[14:".get("][15:".get("][66:".get("][67:".get("]"two"[14:")"][15:")"][66:")"][67:")"]);
        [16:"List<String>"][68:"List<String>"] singleton = [17:"java.util.Collections.singletonList("][69:"java.util.Collections.singletonList("][18:"\"one\""][70:"\"one\""][17:")"][69:")"];
        [19:"System.out."][71:"System.out."]println(singleton);
        [20:"List<String>"][72:"List<String>"] asList = [21:"Arrays.asList("][73:"Arrays.asList("][22:"\"one\""][74:"\"one\""], [23:"\"two\""][75:"\"two\""][21:")"][73:")"];
        [24:"System.out."][76:"System.out."]println(asList);
        [25:"List<String>"][77:"List<String>"] copy = [26:"new ArrayList<>(Arrays.asList("][78:"new ArrayList<>(Arrays.asList("][27:"\"one\""][79:"\"one\""], [28:"\"two\""][80:"\"two\""][26:"))"][78:"))"];
        [29:"System.out."][81:"System.out."]println(copy);
        [30:"List<String>"][82:"List<String>"] unmodifiable = [31:"Collections.unmodifiableList(Arrays.asList("][83:"Collections.unmodifiableList(Arrays.asList("][32:"\"one\""][84:"\"one\""], [33:"\"two\""][85:"\"two\""][31:"))"][83:"))"];
        [34:"System.out."][86:"System.out."]println(unmodifiable);
        [35:"Set<String>"][87:"Set<String>"] set = [36:"new HashSet<String>() "][88:"new HashSet<String>() "][36:"{\n            "][88:"{\n            "][36:"{\n                add("][88:"{\n                add("]"one"[36:");\n                add("][88:");\n                add("]"two"[36:");\n            }"][88:");\n            }"][36:"\n        "][88:"\n        "][36:"}"][88:"}"];
        [37:"System.out."][89:"System.out."]println(set);
        [38:"Set<String>"][90:"Set<String>"] copyOfSet = [39:"Collections.unmodifiableSet(new HashSet<String>() "][91:"Collections.unmodifiableSet(new HashSet<String>() "][39:"{\n            "][91:"{\n            "][39:"{\n                add("][91:"{\n                add("]"one"[39:");\n                add("][91:");\n                add("]"two"[39:");\n            }"][91:");\n            }"][39:"\n        "][91:"\n        "][39:"}"][91:"}"][39:")"][91:")"];
        [40:"System.out."][92:"System.out."]println(copyOfSet);
        [41:"String[]"][93:"String[]"] strings = [42:"new String[] {"][94:"new String[] {"]"one", "two"[42:"}"][94:"}"];
        [43:"System.out."][95:"System.out."]println(Arrays.toString(strings));
        [44:"System.out."][96:"System.out."]println(System[45:".getProperty("][46:".getProperty("][97:".getProperty("][98:".getProperty("]"user.dir"[45:")"][46:")"][97:")"][98:")"]);
        [47:"System.out."][99:"System.out."]println(System.getProperty("user.dir", "c:/windows"));
        [48:"System.out."][100:"System.out."]println(System.getenv("user.dir"));
        [49:"System.out."][101:"System.out."]println(System.getenv()[50:".get("][51:".get("][102:".get("][103:".get("]"user.dir"[50:")"][51:")"][102:")"][103:")"]);
    }
}
