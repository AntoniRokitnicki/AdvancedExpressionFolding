package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        list[0:".add("]"one"[0:")"];
        list[1:".remove("]"one"[1:")"];
        System.out.println(list.add("two"));
        System.out.println(list.remove("two"));
        List<String> singleton = Collections.emptyList();
        list[2:".addAll("]singleton[2:")"];
        list[3:".removeAll("]singleton[3:")"];
        [4:"Collections.addAll("]list[4:", "]args[4:")"];
        Set<String> set = new HashSet<>();
        set[5:".add("]"three"[5:")"];
        set[6:".remove("]"three"[6:")"];
        System.out.println(set);
        Set<String> copyOfSet = new HashSet<>();
        set[7:".addAll("]copyOfSet[7:")"];
        System.out.println(copyOfSet);
        List<String> streamToList = [10:"Arrays.stream("]args[10:")"][9:".map("][11:"String::toUpperCase"][9:")"][8:".collect(Collectors."]toList()[8:")"];
        System.out.println(streamToList);
        streamToList = [19:"Arrays.stream("]args[19:")"][18:".map("][20:"String::toUpperCase"][18:")"][17:".collect(Collectors."]toList()[17:")"];
        System.out.println(streamToList);
        streamToList = [28:"Arrays.stream("]args[28:")"][27:".map("][29:"String::toUpperCase"][27:")"][26:".collect(Collectors."]toList()[26:")"];
        System.out.println(streamToList);
        streamToList = [37:"Arrays.stream("]args[37:")"][36:".map("][38:"String::toUpperCase"][36:")"][35:".collect(Collectors."]toList()[35:")"];
        System.out.println(streamToList);
        streamToList = [46:"Arrays.stream("]args[46:")"][45:".map("][47:"String::toUpperCase"][45:")"][44:".collect(Collectors."]toList()[44:")"];
        System.out.println(streamToList);
        streamToList = [55:"Arrays.stream("]args[55:")"][54:".map("][56:"String::toUpperCase"][54:")"][53:".collect(Collectors."]toList()[53:")"];
        System.out.println(streamToList);
        streamToList = [64:"Arrays.stream("]args[64:")"][63:".map("][65:"String::toUpperCase"][63:")"][62:".collect(Collectors."]toList()[62:")"];
        System.out.println(streamToList);
        streamToList = [73:"Arrays.stream("]args[73:")"][72:".map("][74:"String::toUpperCase"][72:")"][71:".collect(Collectors."]toList()[71:")"];
        System.out.println(streamToList);
        streamToList = [82:"Arrays.stream("]args[82:")"][81:".map("][83:"String::toUpperCase"][81:")"][80:".collect(Collectors."]toList()[80:")"];
        System.out.println(streamToList);
        streamToList = [91:"Arrays.stream("]args[91:")"][90:".map("][92:"String::toUpperCase"][90:")"][89:".collect(Collectors."]toList()[89:")"];
        System.out.println(streamToList);

        streamToList = list.stream()[99:".map("][101:"String::toUpperCase"][99:")"][98:".collect(Collectors."]toList()[98:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[108:".map("][110:"String::toUpperCase"][108:")"][107:".collect(Collectors."]toList()[107:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[117:".map("][119:"String::toUpperCase"][117:")"][116:".collect(Collectors."]toList()[116:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[126:".map("][128:"String::toUpperCase"][126:")"][125:".collect(Collectors."]toList()[125:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[135:".map("][137:"String::toUpperCase"][135:")"][134:".collect(Collectors."]toList()[134:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[144:".map("][146:"String::toUpperCase"][144:")"][143:".collect(Collectors."]toList()[143:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[153:".map("][155:"String::toUpperCase"][153:")"][152:".collect(Collectors."]toList()[152:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[162:".map("][164:"String::toUpperCase"][162:")"][161:".collect(Collectors."]toList()[161:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[171:".map("][173:"String::toUpperCase"][171:")"][170:".collect(Collectors."]toList()[170:")"];
        System.out.println(streamToList);
        streamToList = list.stream()[180:".map("][182:"String::toUpperCase"][180:")"][179:".collect(Collectors."]toList()[179:")"];
        System.out.println(streamToList);

        long count = streamToList[188:".stream()."]distinct().count();
        System.out.println(count);
    }
}
