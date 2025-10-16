package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        [0:"List<String>"] list = Arrays.asList(args);
        list[1:".add("]"one"[1:")"];
        list[2:".remove("]"one"[2:")"];
        [3:"System.out."]println(list.add("two"));
        [4:"System.out."]println(list.remove("two"));
        [5:"List<String>"] singleton = Collections.emptyList();
        list[6:".addAll("]singleton[6:")"];
        list[7:".removeAll("]singleton[7:")"];
        [8:"Collections.addAll("]list[8:", "]args[8:")"];
        [9:"Set<String>"] set = new HashSet<>();
        set[10:".add("]"three"[10:")"];
        set[11:".remove("]"three"[11:")"];
        [12:"System.out."]println(set);
        [13:"Set<String>"] copyOfSet = new HashSet<>();
        set[14:".addAll("]copyOfSet[14:")"];
        [15:"System.out."]println(copyOfSet);
        [16:"List<String>"] streamToList = [19:"Arrays.stream("]args[19:")"][18:".map("][20:"String::toUpperCase"][18:")"][17:".collect(Collectors."]toList()[17:")"];
        [26:"System.out."]println(streamToList);
        streamToList = [29:"Arrays.stream("]args[29:")"][28:".map("][30:"String::toUpperCase"][28:")"][27:".collect(Collectors."]toList()[27:")"];
        [36:"System.out."]println(streamToList);
        streamToList = [39:"Arrays.stream("]args[39:")"][38:".map("][40:"String::toUpperCase"][38:")"][37:".collect(Collectors."]toList()[37:")"];
        [46:"System.out."]println(streamToList);
        streamToList = [49:"Arrays.stream("]args[49:")"][48:".map("][50:"String::toUpperCase"][48:")"][47:".collect(Collectors."]toList()[47:")"];
        [56:"System.out."]println(streamToList);
        streamToList = [59:"Arrays.stream("]args[59:")"][58:".map("][60:"String::toUpperCase"][58:")"][57:".collect(Collectors."]toList()[57:")"];
        [66:"System.out."]println(streamToList);
        streamToList = [69:"Arrays.stream("]args[69:")"][68:".map("][70:"String::toUpperCase"][68:")"][67:".collect(Collectors."]toList()[67:")"];
        [76:"System.out."]println(streamToList);
        streamToList = [79:"Arrays.stream("]args[79:")"][78:".map("][80:"String::toUpperCase"][78:")"][77:".collect(Collectors."]toList()[77:")"];
        [86:"System.out."]println(streamToList);
        streamToList = [89:"Arrays.stream("]args[89:")"][88:".map("][90:"String::toUpperCase"][88:")"][87:".collect(Collectors."]toList()[87:")"];
        [96:"System.out."]println(streamToList);
        streamToList = [99:"Arrays.stream("]args[99:")"][98:".map("][100:"String::toUpperCase"][98:")"][97:".collect(Collectors."]toList()[97:")"];
        [106:"System.out."]println(streamToList);
        streamToList = [109:"Arrays.stream("]args[109:")"][108:".map("][110:"String::toUpperCase"][108:")"][107:".collect(Collectors."]toList()[107:")"];
        [116:"System.out."]println(streamToList);

        streamToList = list[119:".stream()."]map([120:"String::toUpperCase"][118:")"][117:".collect(Collectors."]toList()[117:")"];
        [126:"System.out."]println(streamToList);
        streamToList = list[129:".stream()."]map([130:"String::toUpperCase"][128:")"][127:".collect(Collectors."]toList()[127:")"];
        [136:"System.out."]println(streamToList);
        streamToList = list[139:".stream()."]map([140:"String::toUpperCase"][138:")"][137:".collect(Collectors."]toList()[137:")"];
        [146:"System.out."]println(streamToList);
        streamToList = list[149:".stream()."]map([150:"String::toUpperCase"][148:")"][147:".collect(Collectors."]toList()[147:")"];
        [156:"System.out."]println(streamToList);
        streamToList = list[159:".stream()."]map([160:"String::toUpperCase"][158:")"][157:".collect(Collectors."]toList()[157:")"];
        [166:"System.out."]println(streamToList);
        streamToList = list[169:".stream()."]map([170:"String::toUpperCase"][168:")"][167:".collect(Collectors."]toList()[167:")"];
        [176:"System.out."]println(streamToList);
        streamToList = list[179:".stream()."]map([180:"String::toUpperCase"][178:")"][177:".collect(Collectors."]toList()[177:")"];
        [186:"System.out."]println(streamToList);
        streamToList = list[189:".stream()."]map([190:"String::toUpperCase"][188:")"][187:".collect(Collectors."]toList()[187:")"];
        [196:"System.out."]println(streamToList);
        streamToList = list[199:".stream()."]map([200:"String::toUpperCase"][198:")"][197:".collect(Collectors."]toList()[197:")"];
        [206:"System.out."]println(streamToList);
        streamToList = list[209:".stream()."]map([210:"String::toUpperCase"][208:")"][207:".collect(Collectors."]toList()[207:")"];
        [216:"System.out."]println(streamToList);

        [217:"long"] count = streamToList[218:".stream()."]distinct().count();
        [219:"System.out."]println(count);
    }
}
